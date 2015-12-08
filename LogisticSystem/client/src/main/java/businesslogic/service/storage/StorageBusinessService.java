package businesslogic.service.storage;

import java.rmi.RemoteException;




/**
 * 仓库服务 工厂
 * @author xu
 *
 */
public interface StorageBusinessService {

	/**
	 * 发起一次入库操作
	 * @return
	 */
	public StorageInService startStorageIn();
	/**
	 * 发起一次出库操作
	 * @return
	 */
	public StorageOutService startStorageOut();
	/**
	 * 发起一次仓库操作
	 * @return
	 * @throws RemoteException
	 */
	public StorageOperateService startStorageOperate() throws RemoteException;

}
