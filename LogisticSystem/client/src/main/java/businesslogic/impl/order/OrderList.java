package businesslogic.impl.order;

import java.util.ArrayList;

import businesslogic.service.order.OrderBLService;
import data.po.OrderPO;


public class OrderList {
	
	public ArrayList<OrderPO> getOrderList(long [] orderID){
		OrderBLService orderData = new OrderBLController();
		ArrayList<OrderPO> order = orderData.search(orderID);
		return order;
	}
	
	public  void modifyOrder(ArrayList<Long> orderID) {
		OrderBLService orderData = new OrderBLController();
		ArrayList<Long> orderNum = orderID;
		long[] orderNumL = new long[orderNum.size()];
		for (int i = 0; i < orderNum.size(); i++) {
			orderNumL[i] = orderNum.get(i);
		}
		ArrayList<OrderPO> order = orderData.search(orderNumL);
		for (int i = 0; i < order.size(); i++) {
			// 修改订单物流信息
		}
	}

}
