package businesslogic.impl.storage;

import java.rmi.RemoteException;
import data.message.LoginMessage;
import data.enums.DataType;
import utils.DataServiceFactory;
import data.service.StorageDataService;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.storage.StorageInService;
import businesslogic.service.storage.StorageOperateService;
import businesslogic.service.storage.StorageOutService;

/**
 *
 * @author xu
 *
 */
public class StorageBusinessController{
	InstitutionInfo center;
	StorageInfo storageInfo;
	StorageDataService storageData;

	public StorageInService startStorageIn() {
		if (!isStorageExist()) {
			return null;
		}

		try {
			return new StorageIn(center, storageInfo, storageData);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}

	public StorageOutService startStorageOut() {
		if (!isStorageExist()) {
			return null;
		}
		try {
			return new StorageOut(center, storageInfo, storageData);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * ≤÷ø‚ «∑Ò¥Ê‘⁄
	 * @return
	 */
	private boolean isStorageExist() {
		return storageInfo.isStorageExist();
	}

	public StorageBusinessController(LoginMessage user) throws Exception {
		storageData = (StorageDataService) DataServiceFactory
				.getDataServiceByType(DataType.StorageDataService);
		this.center = new InstitutionInfo(user);
		System.out.println(center.getCenterName());
		storageInfo = new StorageInfo(storageData, center.getCenterID());
	}

	public StorageOperateService startStorageOperate() throws RemoteException {
		return new StorageOperate(center, storageData,storageInfo);
	}

}
