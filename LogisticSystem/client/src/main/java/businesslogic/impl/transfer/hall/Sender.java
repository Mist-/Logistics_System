package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

import presentation.company.companyManage;
import utils.DataServiceFactory;
import data.enums.DataType;
import data.enums.POType;
import data.po.DataPO;
import data.po.SenderPO;
import data.po.StaffPO;
import data.service.CompanyDataService;
import data.service.TransferDataService;

/**
 * ¿ìµÝÔ±Àà
 * @author xu
 *
 */
public class Sender {
	long hall;
	CompanyDataService companyData;
	ArrayList<StaffPO> sender;
	StaffPO chosenSender;
	public Sender(long institutionID){
		companyData = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
		this.hall = institutionID;
	}
	
	public long getChosenSenderID(){
		if (chosenSender != null) {
			return chosenSender.getSerialNum();
		}
		
		else return -1;
	}
	
	public String getChosenSenderName(){
		if (chosenSender != null) {
			return chosenSender.getName();
		}
		
		else return null;
	}
	
	
	public long chooseSender(String name){
		long id = -1;
		for (int i = 0; i < sender.size(); i++) {
			if (sender.get(i).getName().equals(name)) {
				chosenSender = sender.get(i);
				id = chosenSender.getSerialNum();
			}
		}
		
		return id;
	}
	
	public long getSenderID(String name){
		for (int i = 0 ; i < sender.size();i++) {
			StaffPO s = (StaffPO) sender.get(i);
			if (s.getName().equals(name)) {
				return s.getSerialNum();
			}
		}
		return -1;
	}
	
	public String[] getAvailableSender() throws RemoteException{
		sender = companyData.searchSenders( hall);
		String[] name = new String[sender.size()];
		for (int i = 0 ; i < sender.size();i++) {
			StaffPO s = (StaffPO) sender.get(i);
			name[i] = s.getName();
		}
		return name;
	}
		
	
}
