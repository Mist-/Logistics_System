package data.service;

import data.po.DataPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 订单信息接口
 * Created by Mouse on 2015/10/22 0022.
 */
public interface OrderDataService extends DataService {

    /**
     * 根据起止地址查找订单
     * @param key 包含了地址信息的字符串。格式为<code>[addr] [addr]</code>
     *            其中[addr]的格式为<code>[city]-[block]-[detailed]</code>，
     *            如果不作限制，则内容是'*'.
     * @return 起止地址完全匹配的所有订单的 <code>ArrayList</code>，不会为空
     * @throws RemoteException 网络连接错误
     */
    ArrayList<DataPO> searchByLoc(String key) throws RemoteException;

    /**
     * 根据订单信息搜索签收单字符串.
     *
     * @param ordersn 需要搜索的订单号
     * @return 符合条件的签收单的引用。如果没有查询到订单，则返回<code>null</code>
     * @throws RemoteException
     */
    DataPO searchSignInfo(long ordersn) throws RemoteException;

    /**
     * 返回某个快递员在某一天揽件的所有订单。
     *
     * @param sn 快递员的人员编号
     * @param date 需要返回的日期
     * @return 该快递员签收的所有订单。如果没有搜索到结果，将会返回一个大小为0的<code>ArrayList</code>
     */
    ArrayList<DataPO> searchByCourier(long sn, String date) throws RemoteException;
}
