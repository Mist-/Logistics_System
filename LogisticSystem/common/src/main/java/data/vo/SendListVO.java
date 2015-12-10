package data.vo;
/**
 * pass
 * @author xu
 *
 */
public class SendListVO {
	//到达日期、托运订单条形码号、派送员
	public String[] orderID;
	public String date;
	public String senderName;
	public long id;
	public String[] senders;
	public SendListVO(String[] orderID, String date, String[] senders,long id) {
		this.orderID = orderID;
		this.date = date;
		this.senders = senders;
		this.id = id;
	}
	
	public SendListVO(String name,long id){
		senderName = name;
		this.id = id;
	}
	
}
