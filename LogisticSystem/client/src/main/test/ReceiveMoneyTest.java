import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import mock.MockAccountPO;
import mock.MockOrderPO;
import mock.MockStaffPO;

import org.junit.Test;

import businesslogic.impl.transfer.ReceiveMoney;
import data.po.OrderPO;
import data.po.StaffPO;
import data.vo.ReceiptVO;

public class ReceiveMoneyTest {

	@Test
	public void test() {
		ReceiveMoney receiveMoney = new ReceiveMoney(1025001);
		ArrayList<StaffPO> senders = new ArrayList<StaffPO>();
		ArrayList<OrderPO> orders = new ArrayList<OrderPO>();
		orders.add(new MockOrderPO(001));
		orders.add(new MockOrderPO(002));
		orders.add(new MockOrderPO(003));
		senders.add(new MockStaffPO(10001, 1025001));
		senders.add(new MockStaffPO(10002, 1025001));
		senders.add(new MockStaffPO(10003, 1025001));
		receiveMoney.setSenders(senders);
		receiveMoney.getSenders(1025001);
		receiveMoney.setAccount(new MockAccountPO("NanjingBank", 100));
		ReceiptVO receiptVO = receiveMoney.chooseSender(10001);
		assertEquals(130.0, receiptVO.getMoney(),1);
	}


}
