package data.impl;

import data.enums.POType;
import data.po.*;
import data.service.StorageDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Log;
import utils.Timestamper;

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
	public HashMap<POType, ArrayList<DataPO>> hkasfkjhkjash() throws RemoteException {
		return poLists;
	}

	@Override
	public ArrayList<DataPO> asdfghjkl() throws RemoteException {
		return newlyApproved;
	}




	@Override
	public ArrayList<DataPO> searchByDate(POType type, ArrayList<String> date) throws RemoteException {
		Log.log("调用" + this.getClass().getSimpleName());
		ArrayList<DataPO> list = getPOList(type);
		ArrayList<DataPO> result = new ArrayList<DataPO>();
		for(int i = 0 ; i < date.size();i++){
			for(int j = 0 ; j < list.size(); j++){
				String d = Timestamper.getTimeByDate(list.get(j).getGenDate());
				if(d.equals(date.get(i))){
					result.add(list.get(j));
				}
				
			}
		}
		return result;
	}



	@Override
	public DataPO searchStorageInfo(String date) throws RemoteException {
		Log.log("调用" + this.getClass().getSimpleName());
		ArrayList<DataPO> result = getPOList(POType.STORAGEINFO);
		if (result == null) return null;
		for (DataPO dataPO: result) {
			StorageInfoPO storageInfoPO = (StorageInfoPO) dataPO;

		}
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) {
		Log.log("调用" + this.getClass().getSimpleName());
		ArrayList<DataPO> result = new ArrayList<>();
		for (int i = 0; i < newlyApproved.size(); ++i) {
			if (newlyApproved.get(i).getPOType() == type) {
				switch (type) {
					case STORAGEINLIST:
						if (((StorageInListPO) newlyApproved.get(i)).getTransferNum() == institution) {
							result.add(newlyApproved.get(i));
							newlyApproved.remove(i);
							--i;
						}
						break;
					case STORAGEOUTLIST:
						if (((StorageOutListPO) newlyApproved.get(i)).getTransferNum() == institution) {
							result.add(newlyApproved.get(i));
							newlyApproved.remove(i);
							--i;
						}
						break;
                    case STORAGEINFO:
                        if (((StorageInfoPO) newlyApproved.get(i)).getTransferCenterNum() == institution) {
                            result.add(newlyApproved.get(i));
							newlyApproved.remove(i);
							--i;
                        }
                        break;
				}
			}
		}
		return result;
	}



}
