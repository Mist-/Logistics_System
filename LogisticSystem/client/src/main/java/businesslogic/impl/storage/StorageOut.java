package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import mock.MockTransferDataService;
import businesslogic.impl.transfer.center.OrderList;
import businesslogic.impl.transfer.center.TransferList;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.storage.StorageOutService;
import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.StorageInfoPO;
import data.po.StorageOutListPO;
import data.po.TransferListPO;
import data.service.StorageDataService;
import data.service.TransferDataService;
import data.vo.BriefTransferAndStorageOutVO;
import data.vo.StorageOutVO;
import data.vo.TransferListVO;

public class StorageOut implements StorageOutService{
	TransferDataService transferData;
	StorageDataService storageData ;
	
	InstitutionInfo user;
	TransferList transferList;
	StorageList storageOutList;
	StorageInfo storageInfo;
	OrderList orderList;
	
	/**
	 * 对当前所有已审批出库单进行出库操作
	 * @return
	 */
	public ResultMessage doStorageOut(long storageOutID){
		StorageOutListPO out = null;
		try {
			out = (StorageOutListPO) storageOutList.getStorageList(storageOutID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		long[] o = out.getOrder();
		ArrayList<Long> order = new ArrayList<>();
		for(int i = 0 ;i < o.length;i++){
			order.add(o[i]);
		}
		orderList.modifyOrder(order);
		return ResultMessage.SUCCESS;
	}
	


	/**
	 * 修改仓库信息
	 * @param storageOut
	 * @param institution
	 * @return
	 * @throws RemoteException
	 */
	private ResultMessage modifyStorageInfo(StorageOutListPO storageOut) throws RemoteException{
		return storageInfo.modifyStorageInfo(storageOut);
	}
	
	/**
	 * 保存出库单，修改仓库信息
	 * @param vo
	 * @param institution
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage saveStroageOut(StorageOutVO vo){
		StorageOutListPO storageOut = new StorageOutListPO(vo);

		try {
			modifyStorageInfo(storageOut);
			return storageData.add(storageOut);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		
		
	}

	/**
	 * 查看中转单
	 * 
	 * @param listCode
	 * @return 中转单详细信息
	 * @throws RemoteException
	 */
	public TransferListVO getTransferList(long listID){
		return transferList.getCheckedTransferList(listID);
	}
	/**
	 * 发起一次新的出库活动，显示新中转单
	 * @param institutionCode
	 * @return 装车单，中转单列表
	 * @throws RemoteException 
	 */
	public BriefTransferAndStorageOutVO  newStorageOut() {
		String[][] transferInfo;
		try {
			transferInfo = transferList.getBriefTranserList(user.getCenterID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		String[][] storageOutInfo = storageOutList.getBriefStorageList();

		return new BriefTransferAndStorageOutVO(transferInfo,storageOutInfo);	
	}
	
	/**
	 * 由选中的中转单生成默认出库单
	 * 
	 * @return StorageOutvo
	 */
	public StorageOutVO createStorageOutList() {
		TransferListPO transfer = transferList.getTransferList();
		return storageInfo.createStorageOutList(transfer);
	}
	

	public StorageOut(InstitutionInfo user,StorageInfo storageInfo,StorageDataService storageData) throws RemoteException{
		this.user = user;
		//transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		transferData = new MockTransferDataService();
		this.storageData = storageData;
		transferList = new TransferList(transferData);
		orderList = new OrderList();
		this.storageInfo = storageInfo;
		storageOutList = new StorageList(storageData, user.getCenterID(), POType.STORAGEOUTLIST);
	}




}
