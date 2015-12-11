package businesslogic.service.order;

import java.util.ArrayList;

import data.message.ResultMessage;
import data.po.OrderPO;
import data.vo.OrderVO;

/**
 *
 * Created by Mouse on 2015/10/23 0023.
 */
public interface OrderBLService {


    ResultMessage createOrder(OrderVO order);


    String[] enquire(long sn);


    int evaluateTime(OrderVO orderVO);


    double generateFee(OrderVO orderVO);


    ResultMessage receive(long sn);


    ResultMessage entruck(long orderSN, long destID);


    ResultMessage sign(long sn, String name, long phone);


    ArrayList<OrderVO> getDisplayData();
}
