package businesslogic.impl.order;

import java.util.ArrayList;

import businesslogic.service.order.OrderBLService;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.vo.OrderVO;

/**
 *
 * Created by mist on 2015/11/14 0014.
 */
public class OrderBLController implements OrderBLService {

    Order order = null;

    public OrderBLController() {
        order = new Order();
    }

    @Override
    public ResultMessage createOrder(OrderVO order) {
        return this.order.createOrder(order);
    }

    @Override
    public String[] enquire(long sn) {
        return new Logistic().enquire(sn);
    }

    @Override
    public int evaluateTime(OrderVO orderVO) {
        return order.evaluateTime(orderVO);
    }

    @Override
    public double generateFee(OrderVO orderVO) {
        return generateFee(orderVO);
    }

    @Override
    public ResultMessage receive(long sn) {
        return null;
    }

    @Override
    public ResultMessage entruck(long orderSN, long destID) {
        return null;
    }

    @Override
    public ResultMessage sign(long sn, String name, long phone) {
        return null;
    }

	@Override
	public ArrayList<OrderPO> search(long[] order) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public ArrayList<OrderVO> getDisplayData() {
        return new Order().getDisplayData();
    }
}
