package businesslogic.impl.order;

import com.sun.istack.internal.NotNull;
import data.enums.*;
import data.message.LoginMessage;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.*;
import data.service.*;
import data.vo.OrderVO;
import data.vo.SignVO;
import utils.Connection;
import utils.Timestamper;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.*;

/**
 *
 * Created by mist on 2015/11/15 0015.
 */
public class Order {

    LoginMessage loginMessage = null;

    OrderDataService orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);

    public Order(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
    }

    public OrderPO search(long sn) {
        OrderPO result = null;
        try {
            result = (OrderPO) orderDataService.search(POType.ORDER, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultMessage deleteOrder(long sn) {
        OrderPO orderToDelete = null;
        try {
            orderToDelete = (OrderPO) orderDataService.search(POType.ORDER, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NOTCONNECTED;
        }
        if (orderToDelete == null) {
            return ResultMessage.NOTEXIST;
        }
        ResultMessage result = ResultMessage.FAILED;
        try {
            result = orderDataService.delete(orderToDelete);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NOTCONNECTED;
        }
        return result;
    }

    public ResultMessage modify(long sn, OrderVO orderInfo) {
        orderDataService = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        if (orderDataService == null) {
            JOptionPane.showMessageDialog(null, "��������ʧ��", "LCS��������ϵͳ", JOptionPane.INFORMATION_MESSAGE);
            return ResultMessage.NOTCONNECTED;
        }
        OrderPO orderToModify = null;
        try {
            orderToModify = (OrderPO) orderDataService.search(POType.ORDER, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        orderToModify.modify(orderInfo);
        try {
            orderDataService.modify(orderToModify);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }

    public ResultMessage createOrder(OrderVO order) {
        OrderPO newOrder = new OrderPO(order);

        // ����������Ϣ
        LogisticInfoPO logisticInfoPO =  new LogisticInfoPO(newOrder.getSerialNum());
        logisticInfoPO.addInfo(Timestamper.getTimeBySecond(), "���Ա��������");
        newOrder.setRoutine(getRoutine(order.saddress, order.raddress));
        newOrder.setTransferType(getTransportType(order));
        // �ύ����
        try {
            orderDataService.add(newOrder);
            orderDataService.add(logisticInfoPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    public StorageArea getTransportType(OrderVO orderVO) {
        if (orderVO.saddress.split("[-]")[0].equals(orderVO.raddress.split("[-]")[0])) {
            return StorageArea.TRUCK;
        }
        else {
            switch (orderVO.serviceType) {
                case ���ÿ��:
                    return StorageArea.TRUCK;
                case ��׼���:
                    return StorageArea.TRAIN;
                case �ؿ���:
                    return StorageArea.PLANE;
            }
        }
        return StorageArea.TRUCK;
    }

    /**
     * �����ʾ����
     *
     * @return ��������OrderVO��ArrayList
     */
    public ArrayList<OrderVO> getDisplayData() {
        ArrayList<OrderVO> result = new ArrayList<>();
        ArrayList<DataPO> data = null;
        try {
            data = orderDataService.getPOList(POType.ORDER);
        } catch (RemoteException e) {
        }
        if (data == null) {
            System.err.println("��ȡ��������ʱ��������");
            return null;
        }
        for (DataPO dat: data) {
            result.add(new OrderVO((OrderPO) dat));
        }
        return result;
    }

    public ArrayList<String> getCityList() {
        ArrayList<String> result = new ArrayList<>();
        DataService ds = utils.DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        try {
            for (DataPO dataPO: ds.getPOList(POType.CITYINFO)) {
                result.add(((CityInfoPO) dataPO).getName());
            }
        } catch (RemoteException e) {
            System.err.println("��ȡ�����б�ʱ��������ʧ��");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * ������ֹ��ַ�������һ������·��
     *
     * @param depart �����ص�ַ��Ϣ����ʽΪ[City]-[Block]-[Address]
     * @param dest Ŀ�ĵص�ַ��Ϣ����ʽΪ[City]-[Block]-[Address]
     * @return ����·�ߵ�ArrayList����������վ�������������ʧ�ܣ��򷵻�null
     */
    public ArrayList<Long> getRoutine(@NotNull String depart, @NotNull String dest) {
        ArrayList<Long> routine = new ArrayList<>();

        String[] from = depart.split("[-]");
        String[] to = dest.split("[-]");

        // ��ȡ��ǰӪҵ�����
        CompanyDataService ds = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        CityInfoPO fromCity = null;
        CityInfoPO toCity = null;
        try {
            fromCity = (CityInfoPO) ds.searchCity(from[0]);
            toCity = (CityInfoPO) ds.searchCity(to[0]);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        InstitutionPO fromBO = null, toBO = null;
        for (long bosn: fromCity.getBusinessOfficeList()) {
            InstitutionPO tmp = null;
            try {
                tmp = (InstitutionPO) ds.search(POType.INSTITUTION, bosn);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (tmp.getName().equals(from[1])) {
                fromBO = tmp;
            }
        }
        for (long bosn: toCity.getBusinessOfficeList()) {
            InstitutionPO tmp = null;
            try {
                tmp = (InstitutionPO) ds.search(POType.INSTITUTION, bosn);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (tmp.getName().equals(to[1])) {
                toBO = tmp;
            }
        }
        if (fromCity.getSerialNum() == toCity.getSerialNum()) {
            routine.add(fromBO.getSerialNum());
            routine.add(toBO.getSerialNum());
        }
        else {
            routine.add(fromBO.getSerialNum());
            routine.add(fromCity.getTransferCenterID());
            routine.add(toCity.getTransferCenterID());
            routine.add(toBO.getSerialNum());
        }

        return routine;
    }

    /**
     * ����ĳ�������ʹ�ʱ��
     *
     * @param orderVO ��Ҫ���ƵĶ�������Ϣ
     * @return ���Ƴ���ʱ�䡣�ǹ��ƽ������ȡ�����������������Ͽ�����û�����ݣ��򷵻�0.
     */
    public int evaluateTime(@NotNull OrderVO orderVO) {
        ArrayList<DataPO> orders = null;
        float time = 0.0f;
        if (!Connection.connected) {
            System.err.println("��δ���ӵ����������޷�Ԥ���ʹ�ʱ��" + Calendar.getInstance().getTime());
            return 0;
        }
        try {
            orders = orderDataService.searchByLoc(orderVO.saddress + " " + orderVO.raddress);
        } catch (RemoteException e) {
            System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
            return 0;
        }

        if (orders.size() == 0) return 0;

        for (DataPO data : orders) {
            OrderPO orderPO = (OrderPO) data;
            Calendar sendDate = orderPO.getGenDate();
            SignPO signPO = null;
            try {
                signPO = (SignPO) orderDataService.searchSignInfo(orderPO.getSerialNum());
            } catch (RemoteException e) {
                System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
                return 0;
            }
            if (signPO == null) continue;
            Calendar signDate = signPO.getGenDate();
            time += (signDate.getTimeInMillis() - sendDate.getTimeInMillis()) / 1000.0f / 60.0f / 60.0f / 24.0f;
        }
        time /= orders.size();
        time *= 10;

        // ��������
        if ((int) time % 10 >= 5) time = ((int) time) / 10 * 10 + 10;
        else time = ((int) time) / 10 * 10;
        return (int) time;
    }

    /**
     * ���ݶ�����Ϣ����������
     *
     * @param orderVO ��Ҫ������õĶ�����
     * @return ������ķ���
     */
    public double generateFee(OrderVO orderVO) {
        return 0;
    }

    public ArrayList<String> getBlockByCity(String city) {
        CompanyDataService ds = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        CityInfoPO cityInfo = null;
        try {
            cityInfo = (CityInfoPO) ds.searchCity(city);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (cityInfo == null) {
            ArrayList<String> res = new ArrayList<>();
            res.add("��ʱ�޷���ȡ������Ϣ");
        }
        ArrayList<String> result = new ArrayList<>();
        for (long bosn: cityInfo.getBusinessOfficeList()) {
            try {
                result.add(((InstitutionPO) ds.search(POType.INSTITUTION, bosn)).getName());
            } catch (RemoteException e) {
                System.err.println("�������Ӵ����޷���ȡ������Ϣ - " + Calendar.getInstance().getTime());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ǩ�ղ���
     *
     * @param sn    ��Ҫǩ�յĶ�����
     * @param name  ǩ��������
     * @param phone ǩ���ߵ绰
     * @return SUCCESS��ʾǩ�ճɹ���FAILED��ʾ�����Ѿ���ǩ�ա�NOTEXIST��ʾ�����Ų����ڡ�
     */
    public ResultMessage sign(long sn, String name, String phone) {
        LogisticInfoPO logisticInfo = null;
        OrderPO order = null;
        try {
            // ��ȡ������������Ϣ
            order = (OrderPO) orderDataService.search(POType.ORDER, sn);
            logisticInfo = (LogisticInfoPO) orderDataService.search(POType.LOGISTICINFO, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (order == null) return ResultMessage.NOTEXIST;
        logisticInfo.addInfo(Timestamper.getTimeBySecond(), "����ѱ� " + name + " ǩ��, �绰��" + phone);

        // ����ǩ����Ϣ
        SignPO signPO = new SignPO(sn);
        signPO.setSdate(name);
        signPO.setSphone(phone);
        try {
            ResultMessage result = orderDataService.modify(logisticInfo);
            if (result == ResultMessage.FAILED) {
                JOptionPane.showMessageDialog(null, "����������Ϣʱ��������ǩ��ʧ��");
                return ResultMessage.FAILED;
            }
             result = orderDataService.add(signPO);
            if (result == ResultMessage.FAILED) {
                JOptionPane.showMessageDialog(null, "���ǩ����Ϣʱ��������ǩ��ʧ��");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }

    public boolean isSigned(long sn) {
        try {
            return !(orderDataService.search(POType.SIGN, sn) == null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    public ResultMessage sign(SignVO signVO) {
        return sign(signVO.sn, signVO.sname, signVO.sphone);
    }

    public ArrayList<OrderPO> search(long[] order) {
        ArrayList<OrderPO> result = new ArrayList<>();
        for (long ordersn : order) {
            try {
                OrderPO orderPO = (OrderPO) orderDataService.search(POType.ORDER, ordersn);
                if (orderPO != null) result.add(orderPO);
            } catch (RemoteException e) {
                System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
            }
        }
        return result;
    }
}
