package businesslogic.service.storage;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.vo.BriefTransferAndStorageOutVO;
import data.vo.StorageOutVO;
import data.vo.TransferListVO;

/**
 * ������ط���
 * @author xu
 *
 */
public interface StorageOutService {
	public StorageOutVO getStorageOut(long id) throws RemoteException;
	
	/**
	 * ȷ�ϳ���
	 * @param storageOutID ���ⵥ��
	 * @return �������
	 * @throws RemoteException 
	 */
	public ResultMessage doStorageOut() throws RemoteException;
	
	/**
	 * ���������ɵĳ��ⵥ
	 * @param vo ���ⵥ��ϸ
	 * @return ������
	 */
	public ResultMessage saveStroageOut(StorageOutVO vo);
	
	/**
	 * ��ȡ��ת����Ϣ
	 * @param listID ��ת����
	 * @return ��ת����ϸ
	 */
	public TransferListVO getTransferList(long listID);
	
	/**
	 * ����һ���µĳ������
	 * @return ����������ת���ͳ��ⵥ
	 */
	public BriefTransferAndStorageOutVO  newStorageOut();
	
	/**
	 * �����µĳ��ⵥ
	 * @return ���ⵥ��ϸ��Ϣ
	 */
	public StorageOutVO createStorageOutList() ;
}
