package businesslogic.service.storage;

import java.rmi.RemoteException;

import data.message.ResultMessage;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;

public interface StorageInService {
	/**
	 * 确认到达，修改到达单上一批订单的状态信息
	 * @return 操作结果
	 * @throws RemoteException
	 */
	public ResultMessage doArrive() throws RemoteException;
	
	/**
	 * 获取刚审批的到达单和入库单列表
	 * @return 操作结果
	 * @throws RemoteException
	 */
	public BriefArrivalAndStorageInVO newStorageIn() throws RemoteException;

	/**
	 * 选择一个已审批的到达单
	 * @param arrivalID
	 * @return 到达单详细
	 */
	public ArrivalVO getArriveList(long arrivalID);

	/**
	 * 选择一个已审批的入库单
	 * @param storageInID
	 * @return 入库单详细
	 */
	public StorageInVO getStorageIn(long storageInID);

	/**
	 * 对到达单上一批货物进行入库分拣
	 * @param arrival
	 * @return 默认入库单
	 */
	public StorageInVO sort(ArrivalVO arrival);

	/**
	 * 确认入库，修改入库单上订单物流信息
	 * @param storageInID
	 * @return 操作结果
	 * @throws RemoteException
	 */
	public ResultMessage doStorageIn(long storageInID) throws RemoteException;

	/**
	 * 保存入库单
	 * @param vo
	 * @return 保存结果
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInList(StorageInVO vo) throws RemoteException;
}
