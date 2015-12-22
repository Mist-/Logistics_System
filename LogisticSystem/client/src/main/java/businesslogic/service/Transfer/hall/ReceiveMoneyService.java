package businesslogic.service.Transfer.hall;
import java.rmi.RemoteException;
import data.message.ResultMessage;
import data.vo.ReceiptVO;
/**
 * 
 * @author xu
 *
 */
public interface ReceiveMoneyService {
	
	
	public String[] getSenders() throws RemoteException;
	
	public String[][] getOrderAndFee(String name,String date);
	
	public ReceiptVO createReceipt(String[][] orderAndMoney);
	
	public ResultMessage saveReceipt(ReceiptVO r) throws RemoteException;
}
