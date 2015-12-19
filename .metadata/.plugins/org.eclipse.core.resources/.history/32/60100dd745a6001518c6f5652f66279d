package data.po;

import data.enums.POType;
import data.vo.StorageInVO;

import java.util.ArrayList;

/**
 * 
 * @author xu
 *
 */
public class StorageInListPO extends StorageListPO {
	private static final long serialVersionUID = 19;

	//中转中心编号
	long transferNum;
	//入库日期
	int year;
	int month;
	int day;
	String date;

	public void setDate(String date) {
		this.date = date;
	}

	//订单号
	//库存位置(area-row-shelf-num)
	ArrayList<Long> order;
    ArrayList<String> storagePosition;

    public StorageInListPO(StorageInVO storageInVO) {
        super(POType.STORAGEINLIST);
        transferNum = storageInVO.centerID;
        order = new ArrayList<>();
        storagePosition = new ArrayList<>();
        String[] date = storageInVO.getDate().split("/");
        year = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        day = Integer.parseInt(date[2]);
        
        String[][] info = storageInVO.getInfo();
        for(int i = 0 ; i <info.length;i++){
        	order.add(Long.parseLong(info[i][0]));
        	switch (info[i][1]){
        	case "航运区":info[i][1] = "0";break;
        	case "铁运区":info[i][1] = "1";break;
        	case "汽运区":info[i][1] = "2";break;
        	default:info[i][1] = "4";
        	}
        	storagePosition.add(info[i][1]+"-"+info[i][2]+"-"+info[i][3]+"-"+info[i][4]);
        }
    }
    
    public StorageInListPO() {
		// TODO Auto-generated constructor stub
    	super(POType.STORAGEINLIST);
	}

	public int getOrderNum(){
    	return order.size();
    }
    
    public String getDate(){
    	return year+"/"+month+"/"+day;
    }

	public ArrayList<Long> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Long> order) {
		this.order = order;
	}

	public ArrayList<String> getStoragePosition() {
		return storagePosition;
	}

	public void setStoragePosition(ArrayList<String> storagePosition) {
		this.storagePosition = storagePosition;
	}


    public long getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(long transferNum) {
		this.transferNum = transferNum;
	}

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

	public long[] getOrderList(){
		long[] result = new long[order.size()];
		for (int i = 0; i < result.length; ++i) {
			result[i] = order.get(i);
		}
		return result;
	}

}
