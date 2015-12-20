import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import mock.MockAccountPO;
import mock.MockOrderPO;
import mock.MockStaffPO;

import org.junit.Test;

import data.po.OrderPO;
import data.po.StaffPO;
import data.vo.ReceiptVO;

public class ReceiveMoneyTest {

	@Test
	public void test() {
		ArrayList<StaffPO> senders = new ArrayList<StaffPO>();
		ArrayList<OrderPO> orders = new ArrayList<OrderPO>();
		orders.add(new MockOrderPO(001));
		orders.add(new MockOrderPO(002));
		orders.add(new MockOrderPO(003));
		senders.add(new MockStaffPO(10001, 1025001));
		senders.add(new MockStaffPO(10002, 1025001));
		senders.add(new MockStaffPO(10003, 1025001));
	}


}
