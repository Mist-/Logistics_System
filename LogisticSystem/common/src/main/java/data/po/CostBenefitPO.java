package data.po;

import data.enums.POType;

/**
 * 成本收益表
 */

public class CostBenefitPO extends DataPO {

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
