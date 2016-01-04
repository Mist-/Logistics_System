package businesslogic.service.Transfer.hall;
import java.rmi.RemoteException;

import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.Transfer.center.TransferReceiveService;

/**
 * ��ת���ķ��� ����
 * @author xu
 *
 */
public interface TransferCenterService {

	/**
	 * ����һ����תװ�˷���
	 * @return
	 */
	public TransferLoadService startTransferLoad();
	
	/**
	 * ����һ����ת���շ���
	 * @return
	 */
	public TransferReceiveService startTransferReceiver();
	
	
	public String getUserInfo() throws RemoteException;
	
	
}