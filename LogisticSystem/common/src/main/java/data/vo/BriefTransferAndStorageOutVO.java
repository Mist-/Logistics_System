package data.vo;

/**
 * pass
 * @author xu
 *
 */
public class BriefTransferAndStorageOutVO {
	String[] transferListHeader = {"中转单号","日期"};
	String[] storageOutListHeader = {"出库单号","日期"};

	String[][] transferList;
	String[][] storageOutList;
	
	public String[] getTransferListHeader() {
		return transferListHeader;
	}
	public String[] getStorageOutListHeader() {
		return storageOutListHeader;
	}
	public BriefTransferAndStorageOutVO(String[][] transferList,String[][] storageOutList){
		this.transferList = transferList;
		this.storageOutList = storageOutList;
	}
	public String[][] getTransferList() {
		return transferList;
	}
	public void setTransferList(String[][] transferList) {
		this.transferList = transferList;
	}
	public String[][] getStorageOutList() {
		return storageOutList;
	}
	public void setStorageOutList(String[][] storageOutList) {
		this.storageOutList = storageOutList;
	}
}
