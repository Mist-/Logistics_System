package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.StorageInListPO;
import data.po.StorageListPO;
import data.po.StorageOutListPO;
import data.service.StorageDataService;
import data.vo.StorageInVO;

public class StorageList {
	StorageDataService storageData;
	ArrayList<DataPO> checkedstorageList;
	POType storageListType;
	public StorageListPO getCheckedStorageList(long listID) throws RemoteException{
		for(DataPO s : checkedstorageList){
			if(s.getSerialNum() == listID){
				return (StorageListPO) s;
			}
		}
		return null;
	}
	
	
	public ResultMessage saveStorageInList(StorageInVO vo) throws RemoteException {
		StorageInListPO storageInPO = new StorageInListPO(vo);
		return storageData.add(storageInPO);
	}
	

	
	public String[][] getBriefStorageList(){
		if(storageListType == POType.STORAGEINLIST){
		String[][] storageListInfo = new String[checkedstorageList.size()][2];
		for (int i = 0; i < checkedstorageList.size(); i++) {
			StorageInListPO s = (StorageInListPO) checkedstorageList.get(i);
			String[] info = new String[2];
			info[0] = s.getSerialNum() + "";
			info[1] = s.getDate();
			storageListInfo[i] = info;
		}
		return storageListInfo;
		}else{
			String[][] storageListInfo = new String[checkedstorageList.size()][2];
			for (int i = 0; i < checkedstorageList.size(); i++) {
				StorageOutListPO s = (StorageOutListPO) checkedstorageList.get(i);
				String[] info = new String[2];
				info[0] = s.getSerialNum() + "";
				info[1] = s.getDate();
				storageListInfo[i] = info;
			}
			return storageListInfo;
		}
	}
	
	
	public StorageList(StorageDataService storageData, long centerID,POType storageListType) throws RemoteException {
		this.storageData = storageData;
		this.storageListType = storageListType;
		if(storageListType == POType.STORAGEINLIST)
		checkedstorageList = storageData.searchNewStorageInList(centerID);
		else 
			checkedstorageList = storageData.searchNewStorageOutList(centerID);
	}


	public StorageListPO getStorageList(long storageListID) throws RemoteException {
		if(storageListType == POType.STORAGEINLIST)
		return (StorageListPO) storageData.search(POType.STORAGEINLIST, storageListID);
		else 
			return (StorageListPO) storageData.search(POType.STORAGEOUTLIST, storageListID);
	}

}
