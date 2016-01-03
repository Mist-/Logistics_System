package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;

import data.enums.POType;
import data.enums.StockStatus;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.DeliveryListVO;
import data.vo.EntruckListVO;
import data.vo.SendListVO;
import data.vo.TransferListVO;
import businesslogic.impl.order.OrderList;
import businesslogic.impl.transfer.center.TransferList;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.EntruckReceiveService;
import businesslogic.service.order.OrderListService;

/**
 * 营业厅接收
 * @author xu
 *
 */
public class EntruckReceive implements EntruckReceiveService{
	InstitutionInfo user;
	ArrivalList arrivalList;
	SendList sendList;
	POType listType;//transfer or entruck
	
	public EntruckReceive(InstitutionInfo user) throws Exception{
		this.user = user;
		arrivalList = new ArrivalList();
	}

	@Override
	public ArrivalListVO getCheckedArrivals() throws RemoteException {
		return arrivalList.getCheckedArrivals(user.getInstitutionID());
	}
	
	
	
	public ArrivalVO chooseArrival(long arrivalID){
		ArrivalPO  a = arrivalList.chooseArrival(arrivalID);
		if(a != null){
		return new ArrivalVO(a);
		}
		else {
			return null;
		}
	}
	


	@Override
	public EntruckListVO searchEntruckList(long listID) throws RemoteException {
		listType = POType.ENTRUCK;
		EntruckList entruckList = new EntruckList();
		return entruckList.searchEntruck(listID);
	}
	
	
	@Override
	public TransferListVO searchTransferList(long listID) throws RemoteException {
		listType = POType.TRANSFERLIST;
		TransferList transferList = new TransferList();
		return transferList.searchTransferList(listID);
	}
	


	@Override
	public ArrivalVO createArrival(DeliveryListVO vo) {
		if(listType == POType.ENTRUCK)
			return arrivalList.createArrival((EntruckListVO)vo);
		else 
			return arrivalList.createArrival((TransferListVO) vo); 
	}

	@Override
	public ResultMessage saveArrival(ArrivalVO arrival) {
		
		try {
			return arrivalList.saveArrival(arrival,user.getInstitutionID());
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}




	@Override
	public ResultMessage saveSendList(SendListVO sendList) {
		try {
			this.sendList.saveSendList(sendList);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public SendListVO createSendList(long ArrivalID) {
		try {
			sendList = new SendList(user.getInstitutionID());
		} catch (RemoteException e1) {
			System.out.println("未能新建派件单");
			e1.printStackTrace();
		}

		ArrivalPO arrival = arrivalList.chooseArrival(ArrivalID);
		try {
			return sendList.createSendList(arrival);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage doArrive() throws RemoteException {
		long[] roundOrder = null;
		long[] lostOrder = null;
		long[] damageOrder = null;
		try {
		roundOrder =  arrivalList.getOrder(StockStatus.ROUND);
		lostOrder = arrivalList.getOrder(StockStatus.LOST);
		damageOrder = arrivalList.getOrder(StockStatus.DAMAGED);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		//修改订单信息
		OrderListService orderList = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		orderList.modifyOrder(damageOrder, "订单于"+user.getInstitutionName()+"营业厅丢失");
		orderList.modifyOrder(roundOrder, "由"+user.getInstitutionName()+"营业厅接收");
		orderList.modifyOrder(lostOrder, "订单于"+user.getInstitutionName()+"营业厅丢失");
		//orderList.modifyOrderPosition(roundOrder);
		return ResultMessage.SUCCESS;
	}
	
	


}
