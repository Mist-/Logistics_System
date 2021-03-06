package data.vo;

import data.enums.StockStatus;
import data.po.ArrivalPO;

import java.util.ArrayList;

/**
 * pass
 * @author xu
 *
 */
public class ArrivalVO {
	public String[] statusList = {"完整","丢失","破损"};
	//到达日期、中转单编号、出发地、货物到达状态（损坏、完整、丢失）
	String deliveryListNum;//中转单或装车单号
	String[][] orderAndStatus;//订单号+状态
    String date;//日期
    String fromName;//出发地
    String  fromNum;//出发地编号
	String destName;//到达地
	String destID;
	String[] header = {"订单编号","订单状态"};
	long id;
	long transferList;//中转中心编号
    public String getDestID() {
		return destID;
	}


	public void setDestID(String destID) {
		this.destID = destID;
	}


	public ArrivalVO(){
	}


	public ArrivalVO(ArrivalPO arrival){
		id = arrival.getSerialNum();
		deliveryListNum = arrival.getDeliveryListID()+"";
		long[] order = arrival.getOrder();
		ArrayList<StockStatus> status = arrival.getStockStatus();
		orderAndStatus = new String[order.length][2];
		for(int i = 0 ; i < order.length;i++){
			String[] info = new String[2];
			info[0] = order[i]+"";
			info[1] = status.get(i)+"";
			orderAndStatus[i] = info;
		}
		
		fromName = arrival.getFromName();
		date = arrival.getDate();
		fromNum = arrival.getFrom()+"";
		
	}

	public String[][] getOrderAndStatus() {
		return orderAndStatus;
	}
	public void setOrderAndStatus(String[][] orderAndStatus) {
		this.orderAndStatus = orderAndStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getFromNum() {
		return fromNum;
	}
	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
	}
	public String[] getHeader() {
		return header;
	}
	
	
	public String getDeliveryListNum() {
		return deliveryListNum;
	}


	public void setDeliveryListNum(String deliveryListNum) {
		this.deliveryListNum = deliveryListNum;
	}

	public String getFromName() {
		return fromName;
	}


	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public long getTransferList() {
		return transferList;
	}

	public void setTransferList(long transferList) {
		this.transferList = transferList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}
	

}
