package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.SendListPO;
import data.service.TransferDataService;
import data.vo.SendListVO;
import data.vo.SenderVO;

/**
 * ÅÉ¼þµ¥Àà
 * @author xu
 *
 */
public class SendList {
	TransferDataService transferData;
	Sender sender;
	long[] order;
	public SendList(TransferDataService transferDataService, long institutionID) throws RemoteException {
		transferData = transferDataService;
		sender = new Sender(transferDataService, institutionID);
	}

	public SendListVO createSendList(ArrivalPO arrival) throws RemoteException {
		SenderVO availableSender = sender.getAvailableSender();
		
		this.order = arrival.getOrder();
		String[] o = new String[order.length];
		for (int i = 0; i < order.length; i++) {
			o[i] = Long.toString(order[i]);
		}
		SendListVO sendList = new SendListVO(o, arrival.getDate(), availableSender.name, availableSender.ID+ "", 0);

		return sendList;
	}
	
	public ResultMessage saveSendList(SendListVO sendList) throws RemoteException{
		SendListPO s = new SendListPO();
		s.setOrder(order);
		s.setArriveDate(sendList.date);
		s.setSender(sendList.senderName);
		s.setSenderID(Long.parseLong(sendList.senderID));
		
		return transferData.add(s);
	}

}
