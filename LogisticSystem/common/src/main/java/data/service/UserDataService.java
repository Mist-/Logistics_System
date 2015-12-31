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
     * �����ַ��������û���Ϣ��
     * @param type ��Ҫ�����ĵ�������
     * @param key �����ؼ��ַ���
     * @return ���з���������PO
     * @throws RemoteException
     */
    ArrayList<DataPO> search(POType type, String key) throws RemoteException;
}
