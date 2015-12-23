package businesslogic.impl.transfer.hall;
import java.rmi.RemoteException;

import businesslogic.impl.order.OrderList;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.ReceiveMoneyService;
import businesslogic.service.order.OrderListService;
import utils.DataServiceFactory;
import utils.Timestamper;
import data.enums.DataType;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.ReceiptPO;
import data.service.DataService;
import data.vo.ReceiptVO;

/**
 * øÏµ›‘±Ω·À„
 * @author xu
 *
 */
public class ReceiveMoney implements ReceiveMoneyService{

	InstitutionInfo user;
	Sender sender;
	OrderListService orders;
	String date;
	
	public String[] getSenders() throws RemoteException{
		return sender.getAvailableSender();
	}
	
	public String[][] getOrderAndFee(String name,String date){
		long senderID = sender.getSenderID(name);
		this.date = date;
		return orders.getOrderAndFee(senderID, date);
	}
	
	public ReceiptVO createReceipt(String[][] orderAndMoney){
		double money = 0;
		for(int i = 0 ; i < orderAndMoney.length;i++){
			money += Double.parseDouble(orderAndMoney[i][1]);
		}
		
		ReceiptVO receipt = new ReceiptVO();
		receipt.setSender(sender.getChosenSenderName());
		receipt.setSenderID(sender.getChosenSenderID());
		receipt.setMoney(money);
		receipt.setAddress("");
		receipt.setDate(date);
		receipt.setInstitution(user.getInstitutionName());
		receipt.setSender(receipt.getSender());
		receipt.setPeople(receipt.getSender());
		receipt.setCount(false);
		receipt.setHallID(user.getInstitutionID());
		return receipt;

	}
	
	public ResultMessage saveReceipt(ReceiptVO r) throws RemoteException{
		DataService finance = DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
		return finance.add(new ReceiptPO(r));
	}
	

	
	public ReceiveMoney(InstitutionInfo user){
		this.user = user;
		sender = new Sender(user.getInstitutionID());
		orders = new OrderList(new LoginMessage(ResultMessage.SUCCESS));
	}
}
