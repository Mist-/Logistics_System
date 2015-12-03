package data.po;

import data.enums.POType;
public class ReceiptPO extends DataPO {

    public ReceiptPO() {
        super(POType.RECEIPT);
    }

	private String date;
	private double money;
	private String sender;
	private long courierID;
	private String institution;
	private long institutionID;
    

    public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}

	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public long getCourierID() {
		return courierID;
	}
	public void setCourierID(long courierID) {
		this.courierID = courierID;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public long getInstitutionID() {
		return institutionID;
	}
	public void setInstitutionID(long institutionID) {
		this.institutionID = institutionID;
	}
	



}
