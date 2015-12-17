package data.impl;

import data.enums.POType;
import data.po.*;
import data.service.StorageDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 *
 * Created by mist on 2015/11/12 0012.
 */
public class StorageDataSerializableImpl extends UnicastRemoteObject implements StorageDataService {
	HashMap<POType, ArrayList<DataPO>> poLists = new HashMap<>();
	ArrayList<DataPO> newlyApproved = new ArrayList<>();

    public StorageDataSerializableImpl() throws RemoteException {
        super();
		poLists.put(POType.STORAGEINFO, new ArrayList<DataPO>());
		poLists.put(POType.STORAGEINLIST, new ArrayList<DataPO>());
		poLists.put(POType.STORAGEOUTLIST, new ArrayList<DataPO>());
		poLists.put(POType.STORAGECHECK, new ArrayList<DataPO>());
		init();
    }

    @Override
    public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
		if (!poLists.containsKey(type)) return null;
		else return poLists.get(type);
	}

	@Override
	public HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException {
		return poLists;
	}

	@Override
	public ArrayList<DataPO> asdfghjkl() throws RemoteException {
		return newlyApproved;
	}

	@Override
	public ArrayList<DataPO> searchNewStorageInList(long institution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> searchNewStorageOutList(long institution) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataPO searchByDate(POType type, String date) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataPO searchStorageInfo(String date) throws RemoteException {
		ArrayList<DataPO> result = getPOList(POType.STORAGEINFO);
		if (result == null) return null;
		for (DataPO dataPO: result) {
			StorageInfoPO storageInfoPO = (StorageInfoPO) dataPO;

		}
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) {
		ArrayList<DataPO> result = new ArrayList<>();
		for (DataPO data: newlyApproved) {
			if (data.getPOType() == type) {
				switch (type) {
					case STORAGEINLIST:
						if (((StorageInListPO)data).getTransferNum() == institution) {
							result.add(data);
						}
						break;
					case STORAGEOUTLIST:
						if (((StorageOutListPO)data).getTransferNum() == institution) {
							result.add(data);
						}
						break;
                    case STORAGEINFO:
                        if (((StorageInfoPO)data).getTransferCenterNum() == institution) {
                            result.add(data);
                        }
                        break;
				}
			}
		}
		return result;
	}

}
