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

    /**
     * 根据订单信息创建订单
     * @param order 需要保存的订单信息
     * @return SUCCESS表示保存成功。FALIED表示（网络问题等）保存失败
     */
    ResultMessage createOrder(OrderVO order);

    /**
     * 根据订单号，返回物流信息
     * @param sn 需要搜索物流信息的订单号
     * @return 物流信息字符串数组。每一个元素包含一条物流信息。按照时间从晚到早排列
     */
    String[] enquire(long sn);

    /**
     * 根据订单信息预估送达时间
     * @param orderVO 需要预估的订单信息
     * @return 预估的时间。结果四舍五入。如果没有历史数据
     *          或者网络连接失败则返回0
     */
    int evaluateTime(OrderVO orderVO);

    /**
     * 根据订单信息生成报价
     * @param orderVO 需要生成报价的订单信息
     * @return 报价。如果不存在报价依据则返回0
     */
    double generateFee(OrderVO orderVO);

    /**
     * 完成收获操作，并生成营业厅到达单
     * @param sn 需要完成收货操作的订单号
     * @return SUCCESS表示收货成功。FAILED表示收货失败。NOTEXIST表示不存在这个订单号
     */
    ResultMessage receive(long sn);

    /**
     * 完成装车操作
     * @param orderSN 需要装车的订单号
     * @param destID 本次装车的目的地
     * @return 装车是否成功
     */
    ResultMessage entruck(long orderSN, long destID);

    /**
     * 签收订单，为这个订单生成签收信息、
     * @param sn 订单编号
     * @param name 签收者姓名。
     * @param phone 签收人的电话
     * @return 签收是否成功
     */
    ResultMessage sign(long sn, String name, long phone);

    /**
     * 获得所有的订单的显示数据
     * @return
     */
    ArrayList<OrderVO> getDisplayData();
}
