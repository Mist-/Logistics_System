package data.vo;

import data.po.StorageOutListPO;

/**
 * 
 * @author xu
 *

 */
public class StorageOutVO {
	//入库单编号
	String id;
	//订单号+position
    String[][] orderAndPosition;
    //日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String date;
    //装运类型（飞机，火车，汽车）
    String transferType;
    //中转单或装车单号
    String transferListNum;
	//中转中心编号
	String transferNum;
	public String[][] getOrderAndPosition() {
		return orderAndPosition;
	}
	public void setOrderAndPosition(String[][] orderAndPosition) {
		this.orderAndPosition = orderAndPosition;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public String getTransferListNum() {
		return transferListNum;
	}
	public void setTransferListNum(String deliveryListNum) {
		transferListNum = deliveryListNum;
	}
	
	public StorageOutVO(StorageOutListPO po){
		long[] order = po.getOrder();
		String[][] postion = po.getPosition();
		String[][] info = new String[order.length][5];
		
		for(int i = 0 ; i < order.length;i++){
			switch (info[i][0]){
			case "0":info[i][0] = "航运区";break;
			case "1":info[i][0] = "铁运区";break;
			case "2":info[i][0] = "汽运区";break;
			default: info[i][0] = "机动区";
			}
			
			String[] in = {order[i]+"",info[i][0],info[i][1],info[i][2],info[i][3]};
			info[i] = in;
		}
		
		this.orderAndPosition = info;
		this.transferListNum = po.getDeliveryListNum()+"";
		this.transferType = po.getTransferType();
		
	}
	
	public StorageOutVO(){
		
	}

	public String getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(String transferNum) {
		this.transferNum = transferNum;
	}
}
