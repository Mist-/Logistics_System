package data.vo;

import javax.swing.text.Position;

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
	String date;
    //装运类型（飞机，火车，汽车）
    String transferType;
    //中转单或装车单号
    String transferListNum;
	//中转中心编号
	String transferNum;

	String[] header= {"订单号","区域","排号","架号","位号"};
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
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
			String area = "";
//			switch (postion[i][0]){
//			case "0":area = "航运区";break;
//			case "1":area = "铁运区";break;
//			case "2":area = "汽运区";break;
//			default: area = "机动区";
//			}
			
			String[] in = {order[i]+"",postion[i][0],postion[i][1],postion[i][2],postion[i][3]};
			info[i] = in;
		}
		
		this.orderAndPosition = info;
		this.transferListNum = po.getDeliveryListNum()+"";
		this.transferType = po.getTransferType();
	}
	
	public StorageOutVO(){
		id = "保存后生成";
	}

	public String getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(String transferNum) {
		this.transferNum = transferNum;
	}
}
