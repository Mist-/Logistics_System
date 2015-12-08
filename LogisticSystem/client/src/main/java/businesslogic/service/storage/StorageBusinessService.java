package businesslogic.service.storage;

import java.rmi.RemoteException;




/**
 * �ֿ���� ����
 * @author xu
 *
 */
public interface StorageBusinessService {

	/**
	 * ����һ��������
	 * @return
	 */
	public StorageInService startStorageIn();
	/**
	 * ����һ�γ������
	 * @return
	 */
	public StorageOutService startStorageOut();
	/**
	 * ����һ�βֿ����
	 * @return
	 * @throws RemoteException
	 */
	public StorageOperateService startStorageOperate() throws RemoteException;

}
