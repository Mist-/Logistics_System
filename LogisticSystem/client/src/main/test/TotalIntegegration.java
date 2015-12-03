
import static org.junit.Assert.*;
import mock.MockPaymentPO;
import mock.MockReceiptPO;

import org.junit.Test;

import businesslogic.impl.financialbl.FundsManage;

public class TotalIntegegration {

	@Test
	public void test() {
		MockReceiptPO mockReceiptPO1 = new MockReceiptPO(500);
		MockReceiptPO mockReceiptPO2 = new MockReceiptPO(20);
		MockPaymentPO mockPaymentPO1 = new MockPaymentPO(29.9);
		MockPaymentPO mockPaymentPO2 = new MockPaymentPO(30.3);
		
		FundsManage fundsManage = new FundsManage();
		fundsManage.addReciptItem(mockReceiptPO1);
		fundsManage.addPaymentItem(mockPaymentPO1);
		
		assertEquals(520,fundsManage.total(fundsManage.addReciptItem(mockReceiptPO2)),1);
		assertEquals(60.2,fundsManage.total(fundsManage.addPaymentItem(mockPaymentPO2)),1);
	}

}
