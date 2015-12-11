package data.service;

import data.po.DataPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ������Ϣ�ӿ�
 * Created by Mouse on 2015/10/22 0022.
 */
public interface OrderDataService extends DataService {

    /**
     * ������ֹ��ַ���Ҷ���
     * @param key �����˵�ַ��Ϣ���ַ�������ʽΪ<code>[addr] [addr]</code>
     *            ����[addr]�ĸ�ʽΪ<code>[city]-[block]-[detailed]</code>��
     *            ����������ƣ���������'*'.
     * @return ��ֹ��ַ��ȫƥ������ж����� <code>ArrayList</code>������Ϊ��
     * @throws RemoteException �������Ӵ���
     */
    ArrayList<DataPO> searchByLoc(String key) throws RemoteException;

    /**
     * ���ݶ�����Ϣ����ǩ�յ��ַ���.
     *
     * @param ordersn ��Ҫ�����Ķ�����
     * @return ����������ǩ�յ������á����û�в�ѯ���������򷵻�<code>null</code>
     * @throws RemoteException
     */
    DataPO searchSignInfo(long ordersn) throws RemoteException;

    /**
     * ����ĳ�����Ա��ĳһ�����������ж�����
     *
     * @param sn ���Ա����Ա���
     * @param date ��Ҫ���ص�����
     * @return �ÿ��Աǩ�յ����ж��������û����������������᷵��һ����СΪ0��<code>ArrayList</code>
     */
    ArrayList<DataPO> searchByCourier(long sn, String date) throws RemoteException;
}
