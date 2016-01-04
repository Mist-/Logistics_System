package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;

import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.DriverManagementService;
import businesslogic.service.Transfer.hall.EntruckReceiveService;
import businesslogic.service.Transfer.hall.LoadAndSortService;
import businesslogic.service.Transfer.hall.ReceiveMoneyService;
import businesslogic.service.Transfer.hall.TransferHallService;
import businesslogic.service.Transfer.hall.TruckManagementService;
import data.message.LoginMessage;

public class TransferHallController implements TransferHallService {
	InstitutionInfo user;
	
	public TransferHallController(LoginMessage login) throws Exception {
			user = new InstitutionInfo(login);
			
	}
	@Override
	public TruckManagementService startTruckManagement() {
		try {
			return new TruckManagement(user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DriverManagementService startDriverManagement() {
		
		
		try {
			return new DriverManagement(user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EntruckReceiveService startEntruckReceive() throws Exception {
		return new EntruckReceive(user);
	}
	@Override
	public LoadAndSortService startOrderSort() {
		return new OrderSort(user);
	}
	@Override
	public ReceiveMoneyService startReceive() {
		return new ReceiveMoney(user);
	}
	@Override
	public String getUserInfo() {
		return user.getStaffName()+"-"+user.getInstitutionName()+"ÓªÒµÌü";
	}
	
	
	


}
