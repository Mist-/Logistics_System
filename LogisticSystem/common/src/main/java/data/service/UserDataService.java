package data.service;

import data.enums.POType;
import data.po.DataPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * Created by mist on 2015/11/12 0012.
 */
public interface UserDataService extends DataService {
    ArrayList<DataPO> search(POType type, String key) throws RemoteException;
}
