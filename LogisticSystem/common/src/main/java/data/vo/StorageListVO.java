package data.vo;

public class StorageListVO {
	public String[] header = {"����","����","����"};
	public String[][] ListInfo;
	public int all;
	public StorageListVO(String[][] ListInfo,int all){
		this.ListInfo = ListInfo;
		this.all = all;
	}
	
}
