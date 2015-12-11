package data.vo;
/**
 * pass
 * @author xu
 *
 */
public class DriverListVO {
	public String[] header = {"±àºÅ","ÐÕÃû","×´Ì¬"};
	public String[][] info;
	
	public DriverListVO(String[][] info){
		this.info = info;
	}
	
	public int getIDRow(){
		return 0;
	}
}
