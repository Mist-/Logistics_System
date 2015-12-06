package businesslogic.service.Transfer.center;

import java.util.ArrayList;

import data.enums.StorageArea;
import data.message.ResultMessage;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;

public interface TransferLoadService {
	public ArrayList<String> chooseTransferType(StorageArea type);
	
	public TransferLoadVO getOrder(String desName) ;
	
	public boolean checkCapacity(TransferLoadVO vo);
	
	public TransferListVO createTransferList(TransferLoadVO load,String staffName,long staffID,String centerName,long centerID);
	
	public ResultMessage saveTransferList(TransferListVO vo);
}
