package businesslogic.impl.transfer.center;

import java.rmi.RemoteException;

import businesslogic.impl.user.CityInfo;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.Transfer.center.TransferReceiveService;
import businesslogic.service.Transfer.hall.TransferCenterService;
import data.message.LoginMessage;

public class TransferCenterController implements TransferCenterService{
	InstitutionInfo center;
	@Override
	public TransferLoadService startTransferLoad() {
		
		try {
			CityInfo city = new CityInfo( center.getCenterID());
			return new TransferLoad(center,city);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public TransferReceiveService startTransferReceiver() {
		return new TransferReceive(center);
	}


	public TransferCenterController(LoginMessage login) throws Exception {
		center = new InstitutionInfo(login);
	}
	
	@Override
	public String getUserInfo() throws RemoteException {
		return center.getStaffName()+"-"+center.getCenterName()+"中转中心";
	}

}
