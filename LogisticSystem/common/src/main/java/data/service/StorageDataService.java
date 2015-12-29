package data.service;


import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;

public interface StorageDataService extends DataService {


	ArrayList<DataPO> searchByDate(POType type, ArrayList<String> date) throws RemoteException;

	DataPO searchStorageInfo(String date) throws RemoteException;

	ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException;

}

