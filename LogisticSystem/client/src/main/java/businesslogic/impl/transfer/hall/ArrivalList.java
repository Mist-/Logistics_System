package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.enums.StockStatus;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.DataPO;
import data.po.TransferListPO;
import data.service.TransferDataService;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.EntruckListVO;
import data.vo.TransferListVO;

public class ArrivalList {
	TransferDataService transferData;
	ArrayList<DataPO> checkedArrivals;
	ArrivalPO choosenArrival;
	
	

	public long[]  doArrive() throws RemoteException{
//		choosenArrival.setOperated(true);
//		transferData.modify(choosenArrival);
//		
		return choosenArrival.getOrder();
		
	}

	/**
	 * 修改到达单状态为已入库
	 */
	public void modifyArriveListState() {
		if (choosenArrival != null) {
			choosenArrival.setOperated(true);
		}
	}
	
	public ArrivalList(TransferDataService transferData) {
		this.transferData = transferData;
	}

	public ArrivalListVO getCheckedArrivals(long institutionID)
			throws RemoteException {
		// TODO Auto-generated method stub
		checkedArrivals = transferData.searchCheckedList(POType.ARRIVAL,
				institutionID);
		String[][] info = new String[checkedArrivals.size()][2];
		for (int i = 0; i < checkedArrivals.size(); i++) {
			ArrivalPO arrival = (ArrivalPO) checkedArrivals.get(i);
			String[] in = { arrival.getSerialNum() + "", arrival.getDate() };
			info[i] = in;
		}
		return new ArrivalListVO(info);
	}

	public ArrivalPO chooseArrival(long ID) {
		if (checkedArrivals != null) {
			for (DataPO a : checkedArrivals) {
				if(a.getSerialNum() == ID){
					choosenArrival = (ArrivalPO) a;
					return choosenArrival;
				}
			}
			
			return null;
		}

		else
			return null;

	}

	public long[] getOrderID(ArrivalVO vo){
		String[][] info = vo.getOrderAndStatus();
		long[] id = new long[info.length];
		for(int i = 0 ; i < info.length;i++){
			id[i] = Long.parseLong(info[i][0]);
		}
		
		return id;
	}
	
	public ArrivalVO createArrival(TransferListVO transferList) {
		// TODO Auto-generated method stub
		ArrivalVO vo = new ArrivalVO();
		vo.setDate(transferList.date);
		vo.setDeliveryListNum(transferList.transferListID);
		vo.setFromName(transferList.transferCenter);
		vo.setFromNum(transferList.transferCenterID);

		String[][] info = transferList.orderAndPosition;
		String[][] orderAndStatus = new String[info.length][2];
		ArrayList<StockStatus> status = new ArrayList<>();
		for (int i = 0; i < info.length; i++) {
			String[] in = { info[i][0], "完整" };
			orderAndStatus[i] = in;
			status.add(StockStatus.ROUND);
		}

		vo.setOrderAndStatus(orderAndStatus);
		vo.setStatus(status);

		return vo;
	}

	public ArrivalVO createArrival(EntruckListVO entruck) {
		ArrivalVO vo = new ArrivalVO();
		vo.setDate(entruck.loadingDate);
		vo.setDeliveryListNum(entruck.entruckListID);
		vo.setFromName(entruck.fromName);
		vo.setFromNum(entruck.fromID);
		String[][] orders = entruck.info;
		ArrayList<StockStatus> status = new ArrayList<>();
		for (int i = 0; i < orders.length; i++) {
			orders[i][1] = "完整";
			status.add(StockStatus.ROUND);
		}
		vo.setStatus(status);
		vo.setOrderAndStatus(orders);
		return vo;
	}

	public ResultMessage saveArrival(ArrivalVO vo) throws RemoteException {
		ArrivalPO arrivalPO = new ArrivalPO();
		arrivalPO.setDate(vo.getDate());
		arrivalPO.setFrom(Long.parseLong(vo.getFromNum()));
		arrivalPO.setFromName(vo.getFromName());
		ArrayList<Long> order = new ArrayList<>();
		String[][] orders = vo.getOrderAndStatus();
		for (int i = 0; i < orders.length; i++) {
			long ID = Long.parseLong(orders[i][0]);
			order.add(ID);

		}
		arrivalPO.setOrder(order);
		arrivalPO.setStockStatus(vo.getStatus());
		return transferData.add(arrivalPO);
	}
}
