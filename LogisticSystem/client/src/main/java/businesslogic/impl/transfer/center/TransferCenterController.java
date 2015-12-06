package businesslogic.impl.transfer.center;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.Transfer.center.TransferReceiveService;
import businesslogic.service.Transfer.hall.TransferCenterService;
import data.enums.DataType;
import data.enums.POType;
import data.enums.StorageArea;
import data.enums.TransferCenter;
import data.factory.DataServiceFactory;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.InstitutionPO;
import data.po.StaffPO;
import data.po.TransferListPO;
import data.service.CompanyDataService;
import data.service.TransferDataService;
import data.vo.ArrivalVO;
import data.vo.BriefOrderVO;
import data.vo.TransferListVO;

public class TransferCenterController implements TransferCenterService{
	InstitutionInfo center;
	TransferDataService transferData;
	CompanyDataService companyData;
	@Override
	public TransferLoadService startTransferLoad() {
		
		try {
			return new TransferLoad(center);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public TransferReceiveService startTransferReceiver() {
		return new TransferReceive(center,transferData);
	}


	public TransferCenterController(LoginMessage login) throws Exception {
		transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		companyData = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
		center = new InstitutionInfo(login);
	}

}
