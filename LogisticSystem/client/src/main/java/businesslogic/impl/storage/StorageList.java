package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import utils.Timestamper;
import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.StorageInListPO;
import data.po.StorageListPO;
import data.po.StorageOutListPO;
import data.service.StorageDataService;
import data.vo.StorageInVO;

public class StorageList {
	long centerID;
	StorageDataService storageData;
	ArrayList<DataPO> checkedstorageList;
	POType storageListType;
	StorageListPO chosenStorageList;
	
	public StorageListPO getCheckedStorageList(long listID) throws RemoteException{
		for(DataPO s : checkedstorageList){
			if(s.getSerialNum() == listID){
				chosenStorageList = (StorageListPO) s;
				return (StorageListPO) s;
			}
		}
		return null;
	}
	
	public ResultMessage saveStorageInList(StorageInVO vo,long center) throws RemoteException {
		StorageInListPO storageInPO = new StorageInListPO(vo);
		storageInPO.setSerialNum(center*10000+storageInPO.getSerialNum());
		return storageData.add(storageInPO);
	}
	
	public String[][] getBriefStorageList() throws RemoteException{
		checkedstorageList = storageData.getNewlyApprovedPO(storageListType, centerID);
		if(checkedstorageList == null) return null;
		System.out.println("��ⵥ������"+checkedstorageList.size());
		String[][] storageListInfo = new String[checkedstorageList.size()][2];
		for (int i = 0; i < checkedstorageList.size(); i++) {
			DataPO s =  checkedstorageList.get(i);
			String[] info = new String[2];
			info[0] = s.getSerialNum() + "";
			info[1] = Timestamper.getTimeByDate(s.getGenDate());
			storageListInfo[i] = info;
		}
		return storageListInfo;

			
		
	}
	
	public StorageList(StorageDataService storageData, long centerID,POType storageListType) throws RemoteException {
		this.storageData = storageData;
		this.storageListType = storageListType;
		this.centerID = centerID;
	}
	
	public long[] getOrderID(){
	
		if(storageListType == POType.STORAGEINLIST){
			StorageInListPO in = (StorageInListPO) chosenStorageList;
			ArrayList<Long> id = in.getOrder();
			long[] ids = new long[id.size()];
			for(int i = 0 ; i < ids.length;i++){
				ids[i] = id.get(i);
			}
			return ids;
		}else{
			StorageOutListPO out = (StorageOutListPO) chosenStorageList;
			return out.getOrder();
		}
	}

	public StorageListPO getStorageList(long storageListID) throws RemoteException {
		if(storageListType == POType.STORAGEINLIST){
			chosenStorageList = (StorageListPO) storageData.search(POType.STORAGEINLIST, storageListID);
		return chosenStorageList;
		}
		else {
			chosenStorageList =  (StorageListPO) storageData.search(POType.STORAGEOUTLIST, storageListID);
			return chosenStorageList;
		}
	}
}
