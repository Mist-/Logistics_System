package data.vo;

/**
 * pass
 * @author xu
 *
 */
public class ArrivalListVO {
	public String[] header = {"到达单号","日期"};
	public String[][] info ;
	public ArrivalListVO(String[][] info){
		this.info = info;
	}

}
