package data.service;

import data.po.DataPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ������Ϣ�ӿ�
 * Created by Mouse on 2015/10/22 0022.
 */
public interface OrderDataService extends DataService {
    public ArrayList<DataPO> searchByLoc(String key) throws RemoteException;

    public DataPO searchSignInfo(long ordersn) throws RemoteException;
    
}
