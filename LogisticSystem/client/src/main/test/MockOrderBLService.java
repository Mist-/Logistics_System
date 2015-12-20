import java.util.ArrayList;

import mock.MockOrderPO;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.vo.OrderVO;
import businesslogic.service.order.OrderBLService;

public class MockOrderBLService implements OrderBLService {
	
	public ArrayList<OrderPO> getNewList(long hall){
		ArrayList<OrderPO> order = new ArrayList<OrderPO>();
		long[] code = {001,002,003};
		order = search(code);
		return order;
	}
	
	public ArrayList<OrderPO> getOrders(long hall){
		ArrayList<OrderPO> order = new ArrayList<OrderPO>();
		long[] code = {001,002,003};
		order = search(code);
		return order;
	}
	
	
	
	@Override
	public ResultMessage createOrder(OrderVO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] enquire(long sn) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public ResultMessage receive(long sn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage entruck(long orderSN, long destID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage sign(long sn, String name, long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderVO> getDisplayData() {
		return null;
	}

	public ArrayList<OrderPO> search(long[] order) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> orders = new ArrayList<OrderPO>();
		for(int i = 0; i < order.length;i++){
			orders.add(new MockOrderPO(order[i]));
		}
		return orders;
	}
	


	@Override
	public int evaluateTime(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double generateFee(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
