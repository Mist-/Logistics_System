package businesslogic.service.storage;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.vo.BriefTransferAndStorageOutVO;
import data.vo.StorageOutVO;
import data.vo.TransferListVO;

/**
 * 出库相关服务
 * @author xu
 *
 */
public interface StorageOutService {
	public StorageOutVO getStorageOut(long id) throws RemoteException;
	
	/**
	 * 确认出库
	 * @param storageOutID 出库单号
	 * @return 操作结果
	 * @throws RemoteException 
	 */
	public ResultMessage doStorageOut() throws RemoteException;
	
	/**
	 * 保存新生成的出库单
	 * @param vo 出库单详细
	 * @return 保存结果
	 */
	public ResultMessage saveStroageOut(StorageOutVO vo);
	
	/**
	 * 获取中转单信息
	 * @param listID 中转单号
	 * @return 中转单详细
	 */
	public TransferListVO getTransferList(long listID);
	
	/**
	 * 发起一次新的出库操作
	 * @return 审批过的中转单和出库单
	 */
	public BriefTransferAndStorageOutVO  newStorageOut();
	
	/**
	 * 生成新的出库单
	 * @return 出库单详细信息
	 */
	public StorageOutVO createStorageOutList() ;
}
