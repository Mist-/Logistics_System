package data.vo;


/**
 * pass
 * @author xu
 *
 */
public class BriefArrivalAndStorageInVO {
	
	public String[] getArrivalTittle() {
		return arrivalTittle;
	}


	public String[] getStorageInTittle() {
		return storageInTittle;
	}


	public BriefArrivalAndStorageInVO(String[][] arrivalList,String[][] storageInList){
		storageInListInfo = storageInList;
		arrivalListInfo = arrivalList;
	}
	
	
	String[] arrivalTittle = {"到达单号","到达日期"};
	String[][] arrivalListInfo;
	
	String[] storageInTittle = {"入库单号（已审批）","处理日期"};
	String[][] storageInListInfo;


	public String[][] getArrivalListInfo() {
		return arrivalListInfo;
	}


	public void setArrivalListInfo(String[][] arrivalListInfo) {
		this.arrivalListInfo = arrivalListInfo;
	}


	public String[][] getStorageInListInfo() {
		return storageInListInfo;
	}


	public void setStorageInListInfo(String[][] storageInListInfo) {
		this.storageInListInfo = storageInListInfo;
	}
	

}
