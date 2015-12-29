package data.vo;

public class StorageListVO {
	public String[] header = {"单号","日期","数量"};
	public String[][] ListInfo;
	public int all;
	public StorageListVO(String[][] ListInfo,int all){
		this.ListInfo = ListInfo;
		this.all = all;
	}
	
}
