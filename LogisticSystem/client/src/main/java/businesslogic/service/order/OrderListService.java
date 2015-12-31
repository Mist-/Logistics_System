package businesslogic.service.order;

import data.po.OrderPO;
import data.vo.BriefOrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by mist on 2015/12/11 0011.
 */
public interface OrderListService {
	/**
	 * 获取新订单（营业厅）
	 * @param institution 当前营业厅编号
	 * @param destID 目标地编号
	 * @return 订单列表
	 */
	public BriefOrderVO getFreshOrder(long institution,long destID) ;

	/**
	 * 修改订单
	 * @param orderID
	 * @param info
	 * @throws RemoteException
     */
	public void modifyOrder(long[] orderID,String info) throws RemoteException;

	/**
	 * 修改订单位置
	 * @param orderID
	 * @throws RemoteException
     */
	public void modifyOrderPosition(long[] orderID) throws RemoteException;

	/**
	 * 搜索订单
	 * @param order
	 * @return
     */
    ArrayList<OrderPO> search(long[] order);

    /**
	 * 获取订单和报价
	 * @param hallID
	 * @param date
	 * @return
     */
    public String[][] getOrderAndFee(long hallID,String date);
}
