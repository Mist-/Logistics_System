package businesslogic.service.storage;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;

public interface StorageInService {
	
	public ResultMessage doArrive() throws RemoteException;
	
	public BriefArrivalAndStorageInVO newStorageIn() throws RemoteException;

	public ArrivalVO getArriveList(long arrivalID);

	public StorageInVO getStorageIn(long storageInID);

	public StorageInVO sort(ArrivalVO arrival);

	public ResultMessage doStorageIn(long storageInID) throws RemoteException;

	public ResultMessage saveStorageInList(StorageInVO vo) throws RemoteException;
}
