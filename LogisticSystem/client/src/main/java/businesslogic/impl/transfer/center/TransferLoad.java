package businesslogic.impl.transfer.center;

import java.rmi.RemoteException;
import java.util.ArrayList;
import businesslogic.impl.order.OrderBLController;
import businesslogic.impl.storage.StorageInfo;
import businesslogic.impl.transfer.TransferInfo;
import businesslogic.impl.user.CityInfo;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.order.OrderBLService;
import data.enums.DataType;
import data.enums.StorageArea;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.po.TransferListPO;
import data.service.CompanyDataService;
import data.service.StorageDataService;
import data.service.TransferDataService;
import data.vo.StoragePositionAndOrderID;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;

/**
 * 中转装运 实现类
 * @author xu
 *
 */
public class TransferLoad implements TransferLoadService {
	CityInfo city;
	InstitutionInfo center;
	StorageArea transferType;
	StorageInfoService storageInfo;
	TransferListPO transferList;// 确定
	ArrayList<OrderPO> orders;
	String targetInstitutionName;
	long desID;
	double transferFee;

	/**
	 * 根据所选运输类型 确定目的地列表
	 * 
	 * @param type
	 * @return 目的地列表 name+ID
	 * @throws RemoteException
	 */
	public ArrayList<String> chooseTransferType(StorageArea type) {
		transferType = type;

		// 类型为空运或铁运，返回其他中转中心信息
		if (type == StorageArea.PLANE || type == StorageArea.TRAIN) {
			return city.getOtherCitys();
		} else {// 返回本中转中心下属营业厅信息 !!!!!!!!!!!!!!!!!!!!!!!!!!!!以及所有中转中心
			ArrayList<String> dest = new ArrayList<String>();
			ArrayList<String> otherCitys = city.getOtherCitys();
			ArrayList<String> halls;
			try {
				halls = city.getHalls();
			} catch (RemoteException e) {
				e.printStackTrace();
				return null;
			}
			dest.addAll(otherCitys);
			dest.addAll(halls);
			return dest;
		}
	}

	public TransferLoad(InstitutionInfo user,CityInfo city) throws RemoteException {
		this.center = user;
		StorageDataService storageData = (StorageDataService) DataServiceFactory
				.getDataServiceByType(DataType.StorageDataService);
		this.city = city;
		storageInfo = new StorageInfo(storageData, user.getCenterID());
		orders = new ArrayList<OrderPO>();
	}

	/**
	 * 根据目的地获取订单列表
	 * 
	 * @param des
	 * @return
	 * @throws RemoteException
	 */
	public TransferLoadVO getOrder(String desName) {
		targetInstitutionName = desName + "中转中心";
		StoragePositionAndOrderID positionAndOrderID = storageInfo
				.getOrderID(transferType); // 固定区域搜索订单
		OrderBLService orderBL = new OrderBLController();
		if (positionAndOrderID == null) {
			return null;
		}
		ArrayList<Long> orderID = positionAndOrderID.order;// 某区域所有订单
		ArrayList<String> positionInfo = positionAndOrderID.position;// 仓库位置信息
		// 转换
		long[] orderIDArray = new long[orderID.size()];
		for (int i = 0; i < orderID.size(); i++) {
			orderIDArray[i] = orderID.get(i);
		}
		ArrayList<OrderPO> order = orderBL.search(orderIDArray);// 搜索
		ArrayList<String> orderVO = new ArrayList<String>();// 符合要求的订单信息
		desID = city.getDestID(desName);
		// 扫描订单（取出符合目的地的）
		int index = 0;
		for (OrderPO o : order) {
			long[] routine = o.getRoutine();
			if (routine[0] == desID) {
				orderVO.add(o.getSerialNum() + "-" + positionInfo.get(index)
						+ "-" + o.getWeight());
				index++;
			}
		}
		return new TransferLoadVO(orderVO);
	}

	/**
	 * 根据当前选中订单检查是否超过单次运送数量上限
	 * 
	 * @param vo
	 * @return
	 */
	public boolean checkCapacity(TransferLoadVO vo) {
		double weight = 0;
		int counter = 0;
		String[][] order = vo.getOrderInfo();
		for (int i = 0; i < order.length; i++) {
			if (order[i][1] == "true") {
				long orderID = Long.parseLong(order[i][0]);
				for (OrderPO o : orders) {
					if (o.getSerialNum() == orderID)
						counter++;
					weight += o.getWeight();
				}
			}
		}
		// 检查个数
		if (transferType == StorageArea.PLANE)
			if (counter > TransferInfo.planeCapacity)
				return false;
			else
				;
		else if (transferType == StorageArea.TRAIN)
			if (counter > TransferInfo.trainCapacity)
				return false;
			else
				;
		else if (transferType == StorageArea.TRUCK)
			if (counter > TransferInfo.truckCapacity)
				return false;
			else
				;

		// 检查重量
		if (transferType == StorageArea.PLANE)
			if (weight <= (TransferInfo.planeCapacity * TransferInfo.Eachweight))
				return true;
			else
				return false;
		else if (transferType == StorageArea.TRAIN)
			if (weight <= (TransferInfo.trainCapacity * TransferInfo.Eachweight))
				return true;
			else
				return false;
		else {
			if (weight <= (TransferInfo.truckCapacity * TransferInfo.Eachweight))
				return true;
			else
				return false;
		}
	}

	/**
	 * 根据当前选中订单生生中转单
	 * 
	 * @param load
	 * @return
	 */
	private TransferListVO createTransferList(TransferLoadVO load,
			String staffName, long staffID, String centerName, long centerID) {
		transferList = new TransferListPO();
		try {
			transferFee = city.getTransferFee(targetInstitutionName,
					transferType);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		transferList.setAccount(false);
		transferList.setDate("");// 时间未设置
		transferList.setFee(0.0);// 金额未设置
		transferList.setStaffName(staffName);
		transferList.setStaff(staffID);
		transferList.setStorageOut(false);
		transferList.setTarget(desID);
		transferList.setTransferType(transferType);
		transferList.setTransferCenter(centerID);
		transferList.setTransferCenterName(centerName);
		String[][] info = load.getOrderInfo();
		long[] order = new long[info.length];
		String[] position = new String[info.length];
		String area = "0";
		if (transferType == StorageArea.TRAIN)
			area = "1";
		else
			area = "2";
		for (int i = 0; i < info.length; i++) {
			order[i] = Long.parseLong(info[i][0]);
			if (info[i][1].equals("机动区"))
				area = "3";
			position[i] = area + "-" + info[i][2] + "-" + info[i][3] + "-"
					+ info[i][4];
		}
		transferList.setOrder(order);
		transferList.setStoragePosition(position);

		return new TransferListVO(transferList);
	}

	/**
	 * 确认保存中转单
	 * 
	 * @param input
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage saveTransferList(TransferListVO vo) {
		TransferDataService transferData = (TransferDataService) DataServiceFactory
				.getDataServiceByType(DataType.TransferDataService);
		try {
			return transferData.add(transferList);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}

	@Override
	public TransferListVO createTransferList(TransferLoadVO load) {
		return createTransferList(load, center.getStaffName(),
				center.getStaffID(), center.getInstitutionName(),
				center.getCenterID());
	}
}
