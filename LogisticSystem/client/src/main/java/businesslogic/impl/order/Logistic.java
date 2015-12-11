package businesslogic.impl.order;

import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.LogisticInfoPO;
import data.service.OrderDataService;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * 物流信息模型对象
 *
 * Created by mist on 2015/11/16 0016.
 */
public class Logistic {
    OrderDataService orderDataService = null;

    public Logistic() {
        orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
    }

    /**
     * 返回查询的订单的物流信息
     * @param sn 需要查询的订单号
     * @return 物流信息数组。纯字符串形式。
     */
    public String[] enquire(long sn) {
        orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        if (orderDataService == null) {
            return null;
        }
        LogisticInfoPO logisticInfoPO = null;
        try {
            logisticInfoPO = (LogisticInfoPO)orderDataService.search(POType.LOGISTICINFO, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //*********************** test code **********************

        if (logisticInfoPO == null) {
            return null;
        }

        String result[] = logisticInfoPO.toString().split("\\n\\n");

        return result;
    }

    public ResultMessage addLogisticInfo(long sn, String location) {
        if (!Connection.connected) {
            System.err.println("尚未连接到服务器，无法添加物流信息" + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }

        // 获取该订单的物流信息持久化对象
        LogisticInfoPO logisticInfoPO = null;
        try {
            logisticInfoPO = (LogisticInfoPO) orderDataService.search(POType.LOGISTICINFO, sn);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            return ResultMessage.SUCCESS;
        }
        if (logisticInfoPO == null) return ResultMessage.NOTEXIST;

        // 向持久化数据增加物流信息项
        String time = "";
        Calendar present = Calendar.getInstance();
        time += present.get(Calendar.YEAR) + "/" + present.get(Calendar.MONTH) + "/" + present.get(Calendar.DATE) + " ";
        time += present.get(Calendar.HOUR) + ":" + present.get(Calendar.MINUTE) + ":" + present.get(Calendar.SECOND);
        logisticInfoPO.addInfo(time, location);

        // 将修改后的数据写回服服务器
        try {
            orderDataService.modify(logisticInfoPO);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }
}
