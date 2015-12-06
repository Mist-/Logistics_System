package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;

import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.EntruckPO;
import data.po.TransferListPO;
import data.service.TransferDataService;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.DeliveryListVO;
import data.vo.EntruckListVO;
import data.vo.SendListVO;
import data.vo.TransferListVO;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.EntruckReceiveService;

/**
 * 营业厅接收
 * @author xu
 *
 */
public class EntruckReceive implements EntruckReceiveService{
	InstitutionInfo user;
	ArrivalList arrivalList;
	SendList sendList;
	TransferDataService transferData;
	POType listType;//transfer or entruck
	
	public EntruckReceive(InstitutionInfo user) throws Exception{
		this.user = user;
		transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		if(transferData == null){
			throw new Exception();
		}
		arrivalList = new ArrivalList(transferData);
	}

	@Override
	public ArrivalListVO getCheckedArrivals() throws RemoteException {
		return arrivalList.getCheckedArrivals(user.getInstitutionID());
	}
	
	
	
	public ArrivalVO chooseArrival(long arrivalID){
		ArrivalPO  a = arrivalList.chooseArrival(arrivalID);
		if(a != null)
		return new ArrivalVO(a);
		else return null;
	}
	


	@Override
	public EntruckListVO searchEntruckList(long listID) throws RemoteException {
		listType = POType.ENTRUCK;
		EntruckPO entruck = (EntruckPO) transferData.search(POType.ENTRUCK, listID);
		if(entruck != null)
		return new EntruckListVO(entruck);
		else return null;
	}
	
	
	@Override
	public TransferListVO searchTransferList(long listID) throws RemoteException {
		listType = POType.TRANSFERLIST;
		TransferListPO transfer = (TransferListPO) transferData.search(POType.TRANSFERLIST, listID);
		return new TransferListVO(transfer);
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
		// TODO Auto-generated method stub
		
		try {
			return arrivalList.saveArrival(arrival);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}




	@Override
	public ResultMessage saveSendList(SendListVO sendList) {
		// TODO Auto-generated method stub
		try {
			this.sendList.saveSendList(sendList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public SendListVO createSendList(long ArrivalID) {
		try {
			sendList = new SendList(transferData, user.getInstitutionID());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			System.out.println("未能新建派件单");
			e1.printStackTrace();
		}

		ArrivalPO arrival = arrivalList.chooseArrival(ArrivalID);
		try {
			return sendList.createSendList(arrival);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage doArrive() {
	try {
		long[] order = 	arrivalList.doArrive();
		//修改订单物流信息
	} catch (RemoteException e) {
		e.printStackTrace();
		return ResultMessage.FAILED;
	}
		return ResultMessage.SUCCESS;
	}
	
	


}
