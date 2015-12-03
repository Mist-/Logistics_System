package mock;

import data.po.PaymentPO;

/**
 * Created by apple on 2015/11/15.
 */
public class MockPaymentPO extends PaymentPO {

    private double price;
    public MockPaymentPO(double price){
        this.price = price;
    }
	public double getPrice() {
		// TODO 自动生成的方法存根
		return price;
	}
}
