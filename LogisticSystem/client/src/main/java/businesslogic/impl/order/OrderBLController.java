package businesslogic.impl.order;

import java.util.ArrayList;

import businesslogic.service.order.OrderBLService;
import com.sun.istack.internal.NotNull;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.vo.OrderVO;

/**
 *
 * Created by mist on 2015/11/14 0014.
 */
public class OrderBLController implements OrderBLService {

    LoginMessage loginMessage = null;

    public OrderBLController(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
    }

    Order order = null;

    public OrderBLController() {
        order = new Order(loginMessage);
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



    public ResultMessage deleteOrder(long sn) {
        return new Order(loginMessage).deleteOrder(sn);
    }

    public ResultMessage modify(long sn, OrderVO orderInfo) {
        return new Order(loginMessage).modify(sn, orderInfo);
    }

    public ArrayList<String> getCityList() {
        return new Order(loginMessage).getCityList();
    }

    public ArrayList<Long> getRoutine(@NotNull String depart, @NotNull String dest) {
        return new Order(loginMessage).getRoutine(depart, dest);
    }

    @Override
    public ArrayList<OrderVO> getDisplayData() {
        return new Order(loginMessage).getDisplayData();
    }

    public OrderPO search(long sn) {
        return order.search(sn);
    }

    public ArrayList<Long> routine(OrderVO orderVO) {
        return new Order(loginMessage).getRoutine(orderVO.saddress, orderVO.raddress);
    }
}
