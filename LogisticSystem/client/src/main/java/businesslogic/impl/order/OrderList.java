package businesslogic.impl.order;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.service.order.OrderListService;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.po.DataPO;
import data.po.OrderPO;
import data.service.OrderDataService;


public class OrderList implements OrderListService{

	public ArrayList<OrderPO> getOrderList(long[] orderID) {
		ArrayList<OrderPO> order = new Order().search(orderID);
		return order;
	}

	public void modifyOrder(ArrayList<Long> orderID) {
		ArrayList<Long> orderNum = orderID;
		long[] orderNumL = new long[orderNum.size()];
		for (int i = 0; i < orderNum.size(); i++) {
			orderNumL[i] = orderNum.get(i);
		}
		ArrayList<OrderPO> order = new Order().search(orderNumL);
		for (int i = 0; i < order.size(); i++) {
			// �޸Ķ���������Ϣ
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

	@Override
	public ArrayList<OrderPO> search(long[] order) {
		ArrayList<OrderPO> result = new ArrayList<>();
		for (long sn: order) {
			OrderPO tmp = search(sn);
			if (tmp != null) {
				result.add(tmp);
			}
		}
		return result;
	}

	public OrderPO search(long sn) {
		OrderDataService orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
		if (orderDataService == null) return null;
		OrderPO result = null;
		try {
			result = (OrderPO) orderDataService.search(POType.ORDER, sn);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

}
