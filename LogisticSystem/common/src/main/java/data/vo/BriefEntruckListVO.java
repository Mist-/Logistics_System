package data.vo;

public class BriefEntruckListVO {
	public String[] header = {"������","����"};
	public String[][] info;
	public BriefEntruckListVO(String[][] info){
		this.info = info;
	}
}
