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
	
	
	String[] arrivalTittle = {"���ﵥ��","��������"};
	String[][] arrivalListInfo;
	
	String[] storageInTittle = {"��ⵥ�ţ���������","��������"};
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
