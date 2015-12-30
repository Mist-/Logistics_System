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
 * ��תװ�� ʵ����
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
	 * ������ѡ�������� ȷ��Ŀ�ĵ��б�
	 * 
	 * @param type
	 * @return Ŀ�ĵ��б� name+ID
	 * @throws RemoteException
	 */
	public ArrayList<String> chooseTransferType(StorageArea type) {
		transferType = type;

		// ����Ϊ���˻����ˣ�����������ת������Ϣ
		if (type == StorageArea.PLANE || type == StorageArea.TRAIN) {
			return city.getOtherCitys();
		} else {// ���ر���ת��������Ӫҵ����Ϣ�Լ�������ת����
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
	 * ����Ŀ�ĵػ�ȡ�����б�
	 *
	 * @return
	 * @throws RemoteException
	 */
	public TransferLoadVO getOrder(String desName) {
		targetInstitutionName = desName;
		StoragePositionAndOrderID positionAndOrderID = storageInfo
				.getOrderID(transferType); // �̶�������������
		OrderListService orderBL = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		if (positionAndOrderID == null) {
			return null;
		}
		ArrayList<Long> orderID = positionAndOrderID.order;// ĳ�������ж���
		ArrayList<String> positionInfo = positionAndOrderID.position;// �ֿ�λ����Ϣ
		// ת��
		long[] orderIDArray = new long[orderID.size()];
		for (int i = 0; i < orderID.size(); i++) {
			orderIDArray[i] = orderID.get(i);
		}
		ArrayList<OrderPO> order = orderBL.search(orderIDArray);// ����
		ArrayList<String> orderVO = new ArrayList<String>();// ����Ҫ��Ķ�����Ϣ
		desID = city.getDestID(desName);
		// ɨ�趩����ȡ������Ŀ�ĵصģ�
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
	 * ���ݵ�ǰѡ�ж�������Ƿ񳬹�����������������
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

		// ������
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

		// �������
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
	 * ���ݵ�ǰѡ�ж���������ת��
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
	 * ȷ�ϱ�����ת��
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
		System.out.println("�޸Ķ���λ����Ϣ ������"+id.length);
		order.modifyOrderPosition(id);//�ڶ��Σ��������޸�λ����Ϣ
		return transferList.saveTransferList(vo,center.getCenterID());
	}

	@Override
	public void refreshStorageInfo() throws RemoteException {
		storageInfo.refreshStorageInfo();
		
	}


}
