package data.po;

import java.util.ArrayList;

import data.enums.POType;
import data.enums.StorageArea;
import data.vo.StorageOutVO;

/**
 * 
 * @author xu
 *
 */
public class StorageOutListPO extends StorageListPO {
	
	
	//������
    long[] order;
    String[][] position;
    public long[] getOrder() {
		return order;
	}
	public void setOrder(long[] order) {
		this.order = order;
	}
	//����
    int year;
    int month;
    int day;
    //װ�����ͣ��ɻ����𳵣�������
    String transferType;
    //��ת����װ������
    long DeliveryListNum;
	// ��ת���ı��
	long transferNum;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public String getDate(){
		return year+"/"+month+"/"+day;
	}

	public long getDeliveryListNum() {
		return DeliveryListNum;
	}
	public void setDeliveryListNum(long deliveryListNum) {
		DeliveryListNum = deliveryListNum;
	}
	public StorageOutListPO() {
        super(POType.STORAGEINLIST);
    }

	public long getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(long transferNum) {
		this.transferNum =  transferNum;
	}

	public StorageOutListPO(StorageOutVO vo){
		super(POType.STORAGEOUTLIST);
		String s[] = vo.getDate().split("[/]");
		
		transferType = vo.getTransferType();
		year = Integer.parseInt(s[0]);
		month = Integer.parseInt(s[1]);
		day = Integer.parseInt(s[2]);
		
		String[][] info = vo.getOrderAndPosition();
		order = new long[info.length];
		position = new String[info.length][4];
		for(int i = 0 ; i < info.length;i++){
			order[i] = Long.parseLong(info[i][0]);
			position[i][0] = info[i][1];
			position[i][1] = info[i][2];
			position[i][2] = info[i][3];
			position[i][3] = info[i][4];
		}
	}
	
	public int getOrderNum(){
		return order.length;
	}
	public String[][] getPosition() {
		return position;
	}
	public void setPosition(String[][] position) {
		this.position = position;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}


}
