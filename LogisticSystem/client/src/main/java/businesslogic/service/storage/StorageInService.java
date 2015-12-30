package businesslogic.service.storage;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;

public interface StorageInService {
	/**
	 * ȷ�ϵ���޸ĵ��ﵥ��һ��������״̬��Ϣ
	 * @return �������
	 * @throws RemoteException
	 */
	public ResultMessage doArrive() throws RemoteException;
	
	/**
	 * ��ȡ�������ĵ��ﵥ����ⵥ�б�
	 * @return �������
	 * @throws RemoteException
	 */
	public BriefArrivalAndStorageInVO newStorageIn() throws RemoteException;

	/**
	 * ѡ��һ���������ĵ��ﵥ
	 * @param arrivalID
	 * @return ���ﵥ��ϸ
	 */
	public ArrivalVO getArriveList(long arrivalID);

	/**
	 * ѡ��һ������������ⵥ
	 * @param storageInID
	 * @return ��ⵥ��ϸ
	 */
	public StorageInVO getStorageIn(long storageInID);

	/**
	 * �Ե��ﵥ��һ������������ּ�
	 * @param arrival
	 * @return Ĭ����ⵥ
	 */
	public StorageInVO sort(ArrivalVO arrival);

	/**
	 * ȷ����⣬�޸���ⵥ�϶���������Ϣ
	 * @param storageInID
	 * @return �������
	 * @throws RemoteException
	 */
	public ResultMessage doStorageIn(long storageInID) throws RemoteException;

	/**
	 * ������ⵥ
	 * @param vo
	 * @return ������
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInList(StorageInVO vo) throws RemoteException;
}
