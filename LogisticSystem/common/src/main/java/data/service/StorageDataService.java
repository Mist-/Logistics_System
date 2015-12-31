package data.service;


import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;

public interface StorageDataService extends DataService {

	/**
	 * ���������������ݡ�
	 * @param type ָ����������
	 * @param date ϣ�������������б�
	 * @return �������������ĵ���
	 * @throws RemoteException
     */
	ArrayList<DataPO> searchByDate(POType type, ArrayList<String> date) throws RemoteException;

	/**
	 * �����������������Ϣ
	 * @param date ָ��������
	 * @return ���������Ŀ����Ϣ
	 * @throws RemoteException
     */
	DataPO searchStorageInfo(String date) throws RemoteException;

	/**
	 * �������ŷ��ظ��������Ķ���
	 * @param type ��Ҫ�����ĵ�������
	 * @param institution ��Ҫ��ȥ�ĵ��ݵĻ������
	 * @return ���з���������PO
	 * @throws RemoteException
     */
	ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException;

}

