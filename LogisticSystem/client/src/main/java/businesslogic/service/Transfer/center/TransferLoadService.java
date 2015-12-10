package businesslogic.service.Transfer.center;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.StorageArea;
import data.message.ResultMessage;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;

public interface TransferLoadService {
	public ArrayList<String> chooseTransferType(StorageArea type);
	
	public TransferLoadVO getOrder(String desName) ;
	
	public boolean checkCapacity(TransferLoadVO vo);
	
	public TransferListVO createTransferList(TransferLoadVO load);
	
	public ResultMessage saveTransferList(TransferListVO vo) throws RemoteException;
}
