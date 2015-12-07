package data.po;

import data.enums.POType;

/**
 * �ɱ������
 */

public class CostBenefitPO extends DataPO {

    private static final long serialVersionUID = 5;

    double allIncome;
    double allPay;
    double allProfit;

    public CostBenefitPO() {
        super(POType.COSTBENEFIT);
    }

    public double getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(double allIncome) {
        this.allIncome = allIncome;
    }

    public double getAllPay() {
        return allPay;
    }

    public void setAllPay(double allPay) {
        this.allPay = allPay;
    }

    public double getAllProfit() {
        return allProfit;
    }

    public void setAllProfit(double allProfit) {
        this.allPay = allProfit;
    }

}
