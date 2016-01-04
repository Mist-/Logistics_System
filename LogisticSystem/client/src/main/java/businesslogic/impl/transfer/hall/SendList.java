package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;

import businesslogic.impl.order.OrderList;
import data.enums.DataType;
import utils.DataServiceFactory;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.SendListPO;
import data.service.TransferDataService;
import data.vo.SendListVO;

/**
 * 派件单类
 * @author xu
 *
 */
public class SendList {
	TransferDataService transferData;
	Sender sender;
	long[] order;
	public SendList(long institutionID) throws RemoteException {
		transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		sender = new Sender(institutionID);
	}

	public SendListVO createSendList(ArrivalPO arrival) throws RemoteException {
		
		this.order = arrival.getOrder();
		String[] o = new String[order.length];
		for (int i = 0; i < order.length; i++) {
			o[i] = Long.toString(order[i]);
		}
		SendListVO sendList = new SendListVO(o, arrival.getDate(),sender.getAvailableSender(), 0);

		return sendList;
	}
	
	public ResultMessage saveSendList(SendListVO sendList) throws RemoteException{
		SendListPO s = new SendListPO();
		s.setOrder(order);
		s.setArriveDate(sendList.date);
		s.setSender(sendList.senderName);
		s.setSenderID(sender.getSenderID(sendList.senderName));
		OrderList o = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
		o.modifyOrder(order, "快递员正在派件...");
		return transferData.add(s);
	}

}
