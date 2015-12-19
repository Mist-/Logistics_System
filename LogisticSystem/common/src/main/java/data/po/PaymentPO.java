package data.po;

import data.enums.POType;
public class PaymentPO extends DataPO {
	private static final long serialVersionUID = 11;

    public PaymentPO() {
        super(POType.PAYMENT);
    }
    private String date;
	private double money;
	private String name;
	private String account;
	private String info;
	private  String exInfo;
    private boolean isCount = false;
	
    public boolean isCount() {
		return isCount;
	}
	public void setCount(boolean isCount) {
		this.isCount = isCount;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getExInfo() {
		return exInfo;
	}
	public void setExInfo(String exInfo) {
		this.exInfo = exInfo;
	}
}
