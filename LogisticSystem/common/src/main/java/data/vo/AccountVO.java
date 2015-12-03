package data.vo;


public class AccountVO {
    String name;
    double money;

    long accountNum;

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }

    public long getAccountNum() {
        return accountNum;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
