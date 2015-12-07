package data.po;

/**
 * �����˻���Ϣ
 */

import data.enums.POType;

public class AccountPO extends DataPO {

    /**
     * 银行账户
     */
    private static final long serialVersionUID = 1;

    String name;
    double money;


    public AccountPO(String name, double money) {
        super(POType.ACCOUNT);
        this.money = money;
        this.name = name;
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
