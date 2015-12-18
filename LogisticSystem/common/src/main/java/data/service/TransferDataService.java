package data.service;


import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;
import data.po.TransferListPO;

public interface TransferDataService extends DataService {
	
	
	ArrayList<DataPO> searchUncountedList(POType type, long institution) throws RemoteException;
	/**
	 * ����������һ���Ѿ���Ч��PO
	 * @param type
	 * @param institutionID
	 * @return
	 * @throws RemoteException
	 */
	ArrayList<DataPO> searchList(POType type, long institutionID) throws RemoteException;

    /**
     * �ֻ����ţ���ȡ�������ݡ�������<code>getNewlyApprovedPO</code>��ͬ
     * @param type
     * @param institution
     * @return
     */
	ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException;
}
