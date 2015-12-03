package data.po;

import data.enums.POType;
import data.vo.SendListVO;

/**
 * 
 * @author xu
 *
 */
public class SendListPO extends DataPO {
	/**
	 * 
	 */
	private long[] order;
	private String arriveDate;
	private String sender;
	private long senderID;

	public SendListPO() {
		super(POType.SEND);
	}

	public void setOrder(long[] order) {
		this.order = order;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getSenderID() {
		return senderID;
	}

	public void setSenderID(long senderID) {
		this.senderID = senderID;
	}

	public long[] getOrder() {
		return order;
	}

	public String getArriveDate() {
		return arriveDate;
	}



}
