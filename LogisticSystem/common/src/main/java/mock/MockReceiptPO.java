package mock;

import data.po.ReceiptPO;

/**
 * Created by apple on 2015/11/15.
 */
public class MockReceiptPO extends ReceiptPO {

    private double price;
    public MockReceiptPO(double price){
        this.price = price;
    }
	public double getPrice() {
		// TODO 自动生成的方法存根
		return price;
	}
}
