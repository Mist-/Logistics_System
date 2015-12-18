package data.service;


import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;
import data.po.TransferListPO;

public interface TransferDataService extends DataService {
	
	
	ArrayList<DataPO> searchUncountedList(POType type, long institution) throws RemoteException;
	/**
	 * 按机构搜索一批已经生效的PO
	 * @param type
	 * @param institutionID
	 * @return
	 * @throws RemoteException
	 */
	ArrayList<DataPO> searchList(POType type, long institutionID) throws RemoteException;

    /**
     * 分机构号，获取审批单据。其余与<code>getNewlyApprovedPO</code>相同
     * @param type
     * @param institution
     * @return
     */
	ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException;
}
