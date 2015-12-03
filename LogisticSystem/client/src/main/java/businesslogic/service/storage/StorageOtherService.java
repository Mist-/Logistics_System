package businesslogic.service.storage;

import data.enums.TransferCenter;
import data.po.StorageInfoPO;

public interface StorageOtherService {
	public StorageInfoPO getStorageInfo(TransferCenter center);
	
}
