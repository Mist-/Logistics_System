package businesslogic.impl.order;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.service.order.OrderBLService;
import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.po.DataPO;
import data.po.OrderPO;
import data.service.OrderDataService;


public class OrderList {

	public ArrayList<OrderPO> getOrderList(long[] orderID) {
		OrderBLService orderData = new OrderBLController();
		ArrayList<OrderPO> order = orderData.search(orderID);
		return order;
	}

	public void modifyOrder(ArrayList<Long> orderID) {
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

	public ArrayList<OrderPO> getFreshOrder(long institution) {
		ArrayList<OrderPO> result = new ArrayList<>();
		OrderDataService orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
		if (orderDataService == null) return null;
		try {
			for (DataPO data: orderDataService.getPOList(POType.ORDER)) {
    			OrderPO order = (OrderPO) data;
				if (order.isFresh() && order.getPresentLocation() == institution) {
					result.add(order);
				}
            }
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
}
