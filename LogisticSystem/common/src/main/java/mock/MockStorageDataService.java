package mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.StorageInListPO;
import data.po.StorageInfoPO;
import data.po.StorageOutListPO;
import data.service.StorageDataService;

public class MockStorageDataService implements StorageDataService {

	@Override
	public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage getPOListFromFile(POType type, String version) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage savePOListToFile(POType type, String version) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApproved() throws RemoteException {
		return null;
	}


	@Override
	public ArrayList<DataPO> searchNewStorageInList(long institution) {
		// TODO Auto-generated method stub
		
		StorageInListPO s1 = new StorageInListPO();
		StorageInListPO s2 = new StorageInListPO();
		ArrayList<DataPO> s = new ArrayList<DataPO>();
		s.add(s1);
		s.add(s2);
		return s;
	}

	@Override
	public ArrayList<DataPO> searchNewStorageOutList(long institution) {
		
			StorageOutListPO s1 = new StorageOutListPO();
			StorageOutListPO s2 = new StorageOutListPO();
			ArrayList<DataPO> a = new ArrayList<>();
			a.add(s1);
			a.add(s2);
			return a;
	}

	@Override
	public DataPO searchByDate(POType type, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataPO searchStorageInfo(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException {
		if(type == POType.STORAGEOUTLIST){
			StorageOutListPO s1 = new StorageOutListPO();
			StorageOutListPO s2 = new StorageOutListPO();
			ArrayList<DataPO> a = new ArrayList<>();
			a.add(s1);
			a.add(s2);
			return a;
		}
		return null;
	}

	@Override
	public DataPO search(POType type, long key) throws RemoteException {
		// TODO Auto-generated method stub
		return new StorageInfoPO();
	}
	
	

}
