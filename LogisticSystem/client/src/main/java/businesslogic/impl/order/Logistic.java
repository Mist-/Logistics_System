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
 * ������Ϣģ�Ͷ���
 *
 * Created by mist on 2015/11/16 0016.
 */
public class Logistic {
    OrderDataService orderDataService = null;

    public Logistic() {
        orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
    }

    /**
     * ���ز�ѯ�Ķ�����������Ϣ
     * @param sn ��Ҫ��ѯ�Ķ�����
     * @return ������Ϣ���顣���ַ�����ʽ��
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
            System.err.println("��δ���ӵ����������޷����������Ϣ" + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }

        // ��ȡ�ö�����������Ϣ�־û�����
        LogisticInfoPO logisticInfoPO = null;
        try {
            logisticInfoPO = (LogisticInfoPO) orderDataService.search(POType.LOGISTICINFO, sn);
        } catch (RemoteException e) {
            System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
            return ResultMessage.SUCCESS;
        }
        if (logisticInfoPO == null) return ResultMessage.NOTEXIST;

        // ��־û���������������Ϣ��
        String time = "";
        Calendar present = Calendar.getInstance();
        time += present.get(Calendar.YEAR) + "/" + present.get(Calendar.MONTH) + "/" + present.get(Calendar.DATE) + " ";
        time += present.get(Calendar.HOUR) + ":" + present.get(Calendar.MINUTE) + ":" + present.get(Calendar.SECOND);
        logisticInfoPO.addInfo(time, location);

        // ���޸ĺ������д�ط�������
        try {
            orderDataService.modify(logisticInfoPO);
        } catch (RemoteException e) {
            System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }
}
