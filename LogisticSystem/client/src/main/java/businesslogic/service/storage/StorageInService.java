package businesslogic.service.storage;

import data.message.ResultMessage;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;

public interface StorageInService {
	
	public BriefArrivalAndStorageInVO newStorageIn();

	public ArrivalVO getArriveList(long arrivalID);

	public StorageInVO getStorageIn(long storageInID);

	public StorageInVO sort(ArrivalVO arrival);

	public ResultMessage doStorageIn(long storageInID);

	public ResultMessage saveStorageInList(StorageInVO vo);
}
