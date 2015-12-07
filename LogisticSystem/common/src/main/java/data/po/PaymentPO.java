package data.po;

import data.enums.POType;
//（付款日期、付款金额、付款人、付款账号、条目（租金（按年收）运费（按次计算）人员工资（按月统计）奖励（一次性）），
//备注（租金年份、运单号、标注工资月份）。（快递员提成、司机计次、业务员月薪）
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
