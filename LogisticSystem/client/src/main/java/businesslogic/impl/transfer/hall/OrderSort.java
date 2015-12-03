package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	InstitutionInfo user; //构造时传入
	CityInfo city;//getDestination时new
	
	EntruckList entruckList;
	ArrayList<OrderPO> order;
	DriverManagement drivers;
	TruckManagement trucks;
	String desName;
	long desID;
	
	public ResultMessage doEntruck(){
		OrderBLService orderData = new OrderBLController();
		long[] order = entruckList.getOrder();
		ArrayList<OrderPO> orderPOs = orderData.search(order);
		//修改订单物流信息
		return ResultMessage.SUCCESS;
	}
	
	public OrderSort(InstitutionInfo user) {
		transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		entruckList = new EntruckList(transferData);
		this.user = user;
	}
	
	public BriefEntruckListVO getEntruckList() throws RemoteException{
		return entruckList.getEntruckList(user.getInstitutionID());
		
	}
	
	public EntruckListVO chooseEntruckList(long id){
		return entruckList.chooseEntruckList(id);
	}

	@Override
	public String[] getDestination() throws RemoteException {
		CompanyDataService companyData = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
		try {
			city = new CityInfo(companyData, user.getCenterID());
			
		} catch (RemoteException e) {
			System.out.println("未能获取城市信息");
			e.printStackTrace();
			return null;
		}
		
		ArrayList<String> halls = city.getHalls();
		for(int i = 0 ; i < halls.size();i++){
			if(halls.get(i).equals(user.getInstitutionName())){
				halls.remove(i);//删掉本营业厅
				break;
			}
		}
		halls.add(user.getCenterName());//增加本营业厅目标中转中心
		String[] h = (String[]) halls.toArray();
		return h;
	}

	@Override
	public BriefOrderVO chooseDestination(String des) {
		desName = des;
		desID = city.getHallID(des);
		if(desID == -1){
			desID = user.getCenterID();
		}
		OrderDataService orderData = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
		try {
			ArrayList<DataPO> o = orderData.searchByLoc(user.getInstitutionName());
			for(DataPO d:o){
				OrderPO or = (OrderPO)d;
				if(or.getNextDestination() == desID)
				order.add(or);
			}
		} catch (RemoteException e) {
			System.err.println("网络连接中断");
			e.printStackTrace();
			return null;
		}
		
		String[][] orderInfo = new String[order.size()][2];
		for(int i = 0 ; i < order.size();i++){
			OrderPO o = order.get(i);
			String[] in = {o.getSerialNum()+"",o.getNextDestination()+""};
			orderInfo[i] = in;
		}
		
		return new BriefOrderVO(orderInfo);
	}

	@Override
	public EntruckListVO createEntruckList(String[][] orders) {
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
		String vehicleID = trucks.getAvailableTruck()+"";
		
		return entruckList.createEntruckList(orders, user, desName, driverID, driverName, vehicleID);
	}

	@Override
	public ResultMessage saveEntruckList(EntruckListVO entruckList) throws RemoteException {
		return this.entruckList.saveEntruckList(entruckList);

	}

}
