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
    /**
     * 根据字符串搜索用户信息。
     * @param type 需要搜索的单据类型
     * @param key 搜索关键字符串
     * @return 所有符合条件的PO
     * @throws RemoteException
     */
    ArrayList<DataPO> search(POType type, String key) throws RemoteException;
}
