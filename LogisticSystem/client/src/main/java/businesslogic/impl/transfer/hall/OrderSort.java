package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import data.enums.DataType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.EntruckPO;
import data.po.OrderPO;
import data.service.CompanyDataService;
import data.service.OrderDataService;
import data.service.TransferDataService;
import data.vo.BriefEntruckListVO;
import data.vo.BriefOrderVO;
import data.vo.EntruckListVO;
import businesslogic.impl.order.OrderBLController;
import businesslogic.impl.user.CityInfo;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.LoadAndSortService;
import businesslogic.service.order.OrderBLService;

public class OrderSort implements LoadAndSortService {
	TransferDataService transferData;
	InstitutionInfo user; // 构造时传入
	CityInfo city;// getDestination时new,城市相关服务
	EntruckList entruckList;//装车单相关服务
	String desName;//目的地名
	long desID;//目的地ID

	@Override
	public ResultMessage doEntruck() {
		OrderBLService orderData = new OrderBLController();
		long[] order = entruckList.getOrder();
		ArrayList<OrderPO> orderPOs = orderData.search(order);
		// 修改订单物流信息
		return ResultMessage.SUCCESS;
	}

	@Override
	public BriefEntruckListVO getEntruckList() throws RemoteException {
		return entruckList.getEntruckList(user.getInstitutionID());

	}

	@Override
	public EntruckListVO chooseEntruckList(long id) {
		return entruckList.chooseEntruckList(id);
	}

	@Override
	public String[] getDestination() throws RemoteException {
		CompanyDataService companyData = (CompanyDataService) DataServiceFactory
				.getDataServiceByType(DataType.CompanyDataService);
		try {
			city = new CityInfo(companyData, user.getCenterID());

		} catch (RemoteException e) {
			System.out.println("未能获取城市信息");
			e.printStackTrace();
			return null;
		}

		
		ArrayList<String> halls = city.getHalls();//获取本城市下辖所有营业厅（包括自己）
		halls.add(user.getCenterName());// 增加本营业厅目标中转中心
		for (int i = 0; i < halls.size(); i++) {
			if (halls.get(i).equals(user.getInstitutionName())) {
				halls.remove(i);// 删掉本营业厅
				break;
			}
		}
		String[] h = new String[halls.size()];
		halls.toArray(h);//转成数组
		return h;
	}

	@Override
	public BriefOrderVO chooseDestination(String des) {
		desName = des;
		desID = city.getHallID(des);
		ArrayList<OrderPO> order;//符合目的地要求的订单
		if (desID == -1) {
			desID = user.getCenterID();
		}
		OrderDataService orderData = (OrderDataService) DataServiceFactory
				.getDataServiceByType(DataType.OrderDataService);
		try {
			ArrayList<DataPO> o = orderData.searchByLoc(user
					.getInstitutionName());//根据目的地搜索订单
			if(o != null){//如果正确搜索到订单
				order = new ArrayList<OrderPO>();
				for (DataPO d : o) {
					OrderPO or = (OrderPO) d;
					if (or.getNextDestination() == desID)
						order.add(or);
				}
				
				Vector<Vector<String>> info = new Vector<Vector<String>>();
				for(int i = 0 ; i < order.size();i++){
					Vector<String> row = new Vector<String>();
					OrderPO oo = order.get(i);
					row.add(oo.getSerialNum()+"");
					row.add(oo.getWeight()+"");
					info.add(row);
				}
				return new BriefOrderVO(info);
			}else {//未能正确搜索到订单，返回空
				return null;
			}
		} catch (RemoteException e) {
			System.err.println("网络连接中断");
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	//生成装车单
	public EntruckListVO createEntruckList(String[][] orders) {
		DriverManagement drivers= null;
		TruckManagement trucks = null;
		try {
			drivers = new DriverManagement(user);
			trucks = new TruckManagement(user);
		} catch (RemoteException e) {
			System.err.println("网络连接中断");
			e.printStackTrace();
			return null;
		}

		String[] driverNameAndID = drivers.getAvailableDriver().split("-");
		String driverID = driverNameAndID[0];
		String driverName = driverNameAndID[1];
		String vehicleID = trucks.getAvailableTruck() + "";

		return entruckList.createEntruckList(orders, user, desName, driverID,
				driverName, vehicleID);
	}

	@Override
	//保存装车单
	public ResultMessage saveEntruckList(EntruckListVO entruckList)
			throws RemoteException {
		return this.entruckList.saveEntruckList(entruckList);

	}
	
	public OrderSort(InstitutionInfo user) {
		transferData = (TransferDataService) DataServiceFactory
				.getDataServiceByType(DataType.TransferDataService);
		entruckList = new EntruckList(transferData);
		this.user = user;
	}


}
