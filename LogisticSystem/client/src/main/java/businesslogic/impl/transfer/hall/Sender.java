package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;
import data.po.SenderPO;
import data.service.TransferDataService;

/**
 * ¿ìµÝÔ±Àà
 * @author xu
 *
 */
public class Sender {
	long hall;
	TransferDataService transferData;
	ArrayList<DataPO> sender;
	public Sender(TransferDataService transferData ,long institutionID){
		this.transferData = transferData;
		this.hall = institutionID;
	}
	
	public long getSenderID(String name){
		for (int i = 0 ; i < sender.size();i++) {
			SenderPO s = (SenderPO) sender.get(i);
			if (s.name ==  name) {
				return s.getSerialNum();
			}
		}
		return -1;
	}
	
	public String[] getAvailableSender() throws RemoteException{
		sender = transferData.searchList(POType.SENDER, hall);
		String[] name = new String[sender.size()];
		for (int i = 0 ; i < sender.size();i++) {
			SenderPO s = (SenderPO) sender.get(i);
			name[i] = s.name;
		}
		return name;
	}
		
	
}
