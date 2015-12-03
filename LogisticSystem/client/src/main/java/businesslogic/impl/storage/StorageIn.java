package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import mock.MockStorageDataService;
import mock.MockTransferDataService;
import businesslogic.impl.order.OrderBLController;
import businesslogic.impl.transfer.center.OrderList;
import businesslogic.impl.transfer.hall.ArrivalList;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.storage.StorageInService;
import data.enums.POType;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.OrderPO;
import data.po.StorageInListPO;
import data.service.StorageDataService;
import data.service.TransferDataService;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;

public class StorageIn implements StorageInService{

	// 需接口
	TransferDataService transferData;
	StorageDataService storageData;
	InstitutionInfo user;
	StorageInfo storageInfo;
	ArrivalList arrivalList;
	StorageList storageInList;
	OrderList orderList;
	/**
	 * 
	 * 一次新的入库活动，1.新建一个入库单，2.完成所有已审批入库单的入库操作
	 * @return 到达单列表，已审批的入库单列表
	 */
	public BriefArrivalAndStorageInVO newStorageIn() {
		// listID+date
		String[][] arrivelistInfo = null;
		String[][] storageInListInfo = null;
		storageInListInfo = storageInList.getBriefStorageList();
		try {
			ArrivalListVO arrivals = arrivalList.getCheckedArrivals(user.getCenterID());
			arrivelistInfo = arrivals.info;
		} catch (RemoteException e) {
			System.out.println("网络连接中断");
			e.printStackTrace();
			return null;
		}

		return new BriefArrivalAndStorageInVO(arrivelistInfo, storageInListInfo);

	}

	/**
	 * 
	 * 要求查看一个到达单详细信息
	 * 
	 * @param arriveListcode
	 * @return 到达单显示信息
	 * @throws RemoteException
	 */
	public ArrivalVO getArriveList(long arriveListcode){
		ArrivalPO a=  arrivalList.chooseArrival(arriveListcode);
		return new ArrivalVO(a);
	}
	
	
	

	/**
	 * 
	 *
	 * 要求查看一个入库单信息
	 * 
	 * @param storageInCode
	 * @return 入库单显示信息
	 * @throws RemoteException
	 */
	public StorageInVO getStorageIn(long storageInID)  {
	try {
		StorageInListPO storageInListPO = (StorageInListPO) storageInList.getCheckedStorageList(storageInID);
		return new StorageInVO(storageInListPO);
	} catch (RemoteException e) {
		System.out.println("网络连接中断");
		e.printStackTrace();
		return null;
	}
	}

	/**
	 * 给订单分配入库位置
	 * 
	 * 
	 * @param institute
	 * @return 入库单展示信息
	 * @throws RemoteException
	 */
	public StorageInVO sort(ArrivalVO arrival) {
		long[] orderID = arrivalList.getOrderID(arrival);
		ArrayList<OrderPO> order = orderList.getOrderList(orderID);
		try {
			return storageInfo.allocateSpace(order);
		} catch (RemoteException e) {
			System.out.println("网络连接中断");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改到达单状态为已入库
	 */
	private void modifyArriveListState() {
		arrivalList.modifyArriveListState();
	}

	/**
	 * 修改订单物流信息(未完)
	 */
	private void modifyOrder(ArrayList<Long> orderID) {
		orderList.modifyOrder(orderID);
	}

	/**
	 * 
	 * 对当前所有入库单进行入库操作
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage doStorageIn(long StorageInID){
			StorageInListPO storageIn = null;
			try {
				storageIn = (StorageInListPO) storageInList.getStorageList(StorageInID);
			} catch (RemoteException e) {
				e.printStackTrace();
				return ResultMessage.FAILED;
			}
			modifyOrder(storageIn.getOrder());
		return ResultMessage.SUCCESS;
	}

	/**
	 * 确认入库后，更新库存信息，将到达单状态修改为已入库
	 * @return result
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInList(StorageInVO vo){
		modifyArriveListState();
		try {
			return storageInList.saveStorageInList(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}

	public StorageIn(InstitutionInfo user,StorageInfo storageInfo,StorageDataService storageData) throws RemoteException {
//		transferData = (TransferDataService) DataServiceFactory
//				.getDataServiceByType(DataType.TransferDataService);
		transferData = new MockTransferDataService();
		this.storageData = storageData;
		this.user = user;
		this.storageInfo = storageInfo;
		orderList = new OrderList();
		arrivalList = new ArrivalList(transferData);
		storageInList = new StorageList(storageData, user.getCenterID(), POType.STORAGEINLIST);
	}



}
