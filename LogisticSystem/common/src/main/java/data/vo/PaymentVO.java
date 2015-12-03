package data.vo;

/**
 * Created by apple on 2015/11/15.
 */
public class PaymentVO {

    long id;
    String date;
    double money;
    String name;
    String account;
    String info;
    String exInfo;

    public PaymentVO(){
    	
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
