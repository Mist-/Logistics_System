package businesslogic.impl.transfer.center;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import businesslogic.impl.order.OrderList;
import businesslogic.impl.storage.StorageInfo;
import businesslogic.impl.transfer.TransferInfo;
import businesslogic.impl.user.CityInfo;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.order.OrderListService;
import data.enums.StorageArea;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.OrderPO;
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
	TransferList transferList;
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
		} else {// 返回本中转中心下属营业厅信息以及所有中转中心
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
		this.city = city;
		storageInfo = new StorageInfo(user.getCenterID());
		transferList = new TransferList();
	}

	/**
	 * 根据目的地获取订单列表
	 *
	 * @return
	 * @throws RemoteException
	 */
	public TransferLoadVO getOrder(String desName) {
		targetInstitutionName = desName;
		StoragePositionAndOrderID positionAndOrderID = storageInfo
				.getOrderID(transferType); // 固定区域搜索订单
		OrderListService orderBL = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
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
			if (o.getNextDestination() == desID) {
				orderVO.add(o.getSerialNum() + "-" + positionInfo.get(index)+ "-" + o.getWeight());
				System.out.println(orderVO.get(index));
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
		Vector<Vector<String>> order = vo.getOrderInfo();
		long[] id = new long[order.size()];
		for(int i = 0; i < order.size();i++){
			id[i] = Long.parseLong(order.get(i).get(0));
		}
		OrderList o = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		weight = o.getWeight(id);
		counter = o.getNum();

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
	public  TransferListVO createTransferList(TransferLoadVO load) {
		try {
			transferFee = city.getTransferFee(targetInstitutionName,
					transferType);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		if(checkCapacity(load)){
		TransferListVO t =  transferList.createTransferList(load, transferType);
		t.fee = transferFee+"";
		t.staff = center.getStaffName();
		t.transferCenter = center.getInstitutionName();
		t.transferCenterID = center.getCenterID()+"";
		t.target = desID+"";
		t.targetName = targetInstitutionName;
		return t;
		}
		else{
			return null;
		}
	}

	/**
	 * 确认保存中转单
	 *
	 * @return
	 * @throws RemoteException
	 */
	public long saveTransferList(TransferListVO vo) throws RemoteException {
		storageInfo.modifyStorageInfo(vo);
		OrderListService order = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		String[][] info = vo.orderAndPosition;
		long[] id = new long[info.length];
		for(int i = 0 ; i < info.length; i++){
			id[i] = Long.parseLong(info[i][0]);
		}
		System.out.println("修改订单位置信息 数量："+id.length);
		order.modifyOrderPosition(id);//第二次，第三次修改位置信息
		return transferList.saveTransferList(vo,center.getCenterID());
	}

	@Override
	public void refreshStorageInfo() throws RemoteException {
		storageInfo.refreshStorageInfo();
		
	}


}
