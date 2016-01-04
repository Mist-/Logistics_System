package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import data.enums.DataType;
import data.message.LoginMessage;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.OrderPO;
import data.service.OrderDataService;
import data.vo.BriefEntruckListVO;
import data.vo.BriefOrderVO;
import data.vo.EntruckListVO;
import businesslogic.impl.order.OrderList;
import businesslogic.impl.user.CityInfo;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.LoadAndSortService;
import businesslogic.service.order.OrderListService;

public class OrderSort implements LoadAndSortService {
	DriverManagement drivers;
	TruckManagement trucks;
	InstitutionInfo user; // ����ʱ����
	CityInfo city;// getDestinationʱnew,������ط���
	EntruckList entruckList;//װ������ط���
	String desName;//Ŀ�ĵ���
	OrderListService orderData;//z������ط���
	long desID;//Ŀ�ĵ�ID

	@Override
	public ResultMessage doEntruck() throws RemoteException {
		long[] order = entruckList.getOrder();
		System.out.println("orderlist lenth:"+order.length);
		// �޸Ķ���������Ϣ
		orderData.modifyOrder(order, "�Ѵ�"+user.getInstitutionName()+"Ӫҵ��"+"����");//����������Ϣ
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
		try {
			city = new CityInfo(user.getCenterID());

		} catch (RemoteException e) {
			System.out.println("δ�ܻ�ȡ������Ϣ");
			e.printStackTrace();
			return null;
		}

		
		ArrayList<String> halls = city.getHalls();//��ȡ��������Ͻ����Ӫҵ���������Լ���
		halls.add(user.getCenterName());// ���ӱ�Ӫҵ��Ŀ����ת����
		for (int i = 0; i < halls.size(); i++) {
			if (halls.get(i).equals(user.getInstitutionName())) {
				halls.remove(i);// ɾ����Ӫҵ��
				break;
			}
		}
		String[] h = new String[halls.size()];
		halls.toArray(h);//ת������
		return h;
	}

	@Override
	public BriefOrderVO chooseDestination(String des) {
		desName = des;
		desID = city.getHallID(des);
		if (desID == -1) {
			desID = user.getCenterID();
		}
		return orderData.getFreshOrder(user.getInstitutionID(),desID);//����Ӫҵ����������¶���
	}

	@Override
	//����װ����
	public EntruckListVO createEntruckList(String[][] orders) throws RemoteException {
		drivers= null;
		trucks = null;
		try {
			drivers = new DriverManagement(user);
			trucks = new TruckManagement(user);
		} catch (RemoteException e) {
			System.err.println("���������ж�");
			e.printStackTrace();
			return null;
		}
		String s = drivers.getAvailableDriver();
		if (s == null) {
			return null;
		}
		String[] driverNameAndID = s.split("-");
		if (driverNameAndID == null) {
			return null;
		}
		String driverID = driverNameAndID[0];
		String driverName = driverNameAndID[1];
		long vehicle = trucks.getAvailableTruck();
		if (vehicle == -1) {
			return null;
		}else {
			String vehicleID = vehicle+"";
			return entruckList.createEntruckList(orders, user, desName, desID+"",
					driverID, driverName, vehicleID);
		}
		
	}

	@Override
	//����װ����
	public ResultMessage saveEntruckList(EntruckListVO entruckList)
			throws RemoteException {
		String[][] info = entruckList.info;
		long[] order = new long[info.length];
		for(int i = 0 ; i < info.length;i++){
			long id = Long.parseLong(info[i][0]);
			order[i] = id;
		}
		drivers.useDriver(Long.parseLong(entruckList.driverID));
		trucks.useTruck(Long.parseLong(entruckList.vehicleID));
		orderData.modifyOrderPosition(order);//�޸Ķ���λ����Ϣ
		return this.entruckList.saveEntruckList(entruckList);

	}
	
	public OrderSort(InstitutionInfo user) {
		entruckList = new EntruckList();
		orderData = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		this.user = user;
	}


}
