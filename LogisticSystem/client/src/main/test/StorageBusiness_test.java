package presentation.storage;


import data.message.LoginMessage;
import data.message.ResultMessage;
import data.vo.BriefArrivalAndStorageInVO;

public class StorageBusiness_test {
	LoginMessage user = new LoginMessage(ResultMessage.SUCCESS, 1000000001L);
	//StorageBusinessService storageBusiness = new StorageBusinessController(user);
	BriefArrivalAndStorageInVO breifArriveList= null;
	
	
	ResultMessage testNewStorageIn(){	
		//breifArriveList =  storageBusiness.newStorageIn();
		return ResultMessage.SUCCESS;
	}
	
	
	
}
