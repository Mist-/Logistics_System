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
            JOptionPane.showMessageDialog(null, "网络连接失败", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
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

        // 创建物流信息
        LogisticInfoPO logisticInfoPO =  new LogisticInfoPO(newOrder.getSerialNum());
        logisticInfoPO.addInfo(Timestamper.getTimeBySecond(), "快递员已揽件。");
        newOrder.setRoutine(getRoutine(order.saddress, order.raddress));
        newOrder.setTransferType(getTransportType(order));
        // 提交订单
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
                case 经济快递:
                    return StorageArea.TRUCK;
                case 标准快递:
                    return StorageArea.TRAIN;
                case 特快快递:
                    return StorageArea.PLANE;
            }
        }
        return StorageArea.TRUCK;
    }

    /**
     * 获得显示数据
     *
     * @return 包含所有OrderVO的ArrayList
     */
    public ArrayList<OrderVO> getDisplayData() {
        ArrayList<OrderVO> result = new ArrayList<>();
        ArrayList<DataPO> data = null;
        try {
            data = orderDataService.getPOList(POType.ORDER);
        } catch (RemoteException e) {
        }
        if (data == null) {
            System.err.println("获取订单数据时发生错误");
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
            System.err.println("获取城市列表时网络连接失败");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 根据起止地址，计算出一条物流路线
     *
     * @param depart 出发地地址信息。格式为[City]-[Block]-[Address]
     * @param dest 目的地地址信息。格式为[City]-[Block]-[Address]
     * @return 包含路线的ArrayList。最多包含四站。如果网络连接失败，则返回null
     */
    public ArrayList<Long> getRoutine(@NotNull String depart, @NotNull String dest) {
        ArrayList<Long> routine = new ArrayList<>();

        String[] from = depart.split("[-]");
        String[] to = dest.split("[-]");

        // 获取当前营业厅编号
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
     * 估计某订单的送达时间
     *
     * @param orderVO 需要估计的订单的信息
     * @return 估计出的时间。是估计结果向上取整的整数。如果网络断开或者没有数据，则返回0.
     */
    public int evaluateTime(@NotNull OrderVO orderVO) {
        ArrayList<DataPO> orders = null;
        float time = 0.0f;
        if (!Connection.connected) {
            System.err.println("尚未连接到服务器，无法预计送达时间" + Calendar.getInstance().getTime());
            return 0;
        }
        try {
            orders = orderDataService.searchByLoc(orderVO.saddress + " " + orderVO.raddress);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
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
                System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
                return 0;
            }
            if (signPO == null) continue;
            Calendar signDate = signPO.getGenDate();
            time += (signDate.getTimeInMillis() - sendDate.getTimeInMillis()) / 1000.0f / 60.0f / 60.0f / 24.0f;
        }
        time /= orders.size();
        time *= 10;

        // 四舍五入
        if ((int) time % 10 >= 5) time = ((int) time) / 10 * 10 + 10;
        else time = ((int) time) / 10 * 10;
        return (int) time;
    }

    /**
     * 根据订单信息产生订单号
     *
     * @param orderVO 需要计算费用的订单号
     * @return 计算出的费用
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
            res.add("暂时无法获取分区信息");
        }
        ArrayList<String> result = new ArrayList<>();
        for (long bosn: cityInfo.getBusinessOfficeList()) {
            try {
                result.add(((InstitutionPO) ds.search(POType.INSTITUTION, bosn)).getName());
            } catch (RemoteException e) {
                System.err.println("网络连接错误。无法获取城区信息 - " + Calendar.getInstance().getTime());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 签收操作
     *
     * @param sn    需要签收的订单号
     * @param name  签收者姓名
     * @param phone 签收者电话
     * @return SUCCESS表示签收成功。FAILED表示订单已经被签收。NOTEXIST表示订单号不存在。
     */
    public ResultMessage sign(long sn, String name, String phone) {
        LogisticInfoPO logisticInfo = null;
        OrderPO order = null;
        try {
            // 获取订单和物流信息
            order = (OrderPO) orderDataService.search(POType.ORDER, sn);
            logisticInfo = (LogisticInfoPO) orderDataService.search(POType.LOGISTICINFO, sn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (order == null) return ResultMessage.NOTEXIST;
        logisticInfo.addInfo(Timestamper.getTimeBySecond(), "快件已被 " + name + " 签收, 电话：" + phone);

        // 生成签收信息
        SignPO signPO = new SignPO(sn);
        signPO.setSdate(name);
        signPO.setSphone(phone);
        try {
            ResultMessage result = orderDataService.modify(logisticInfo);
            if (result == ResultMessage.FAILED) {
                JOptionPane.showMessageDialog(null, "更新物流信息时发生错误。签收失败");
                return ResultMessage.FAILED;
            }
             result = orderDataService.add(signPO);
            if (result == ResultMessage.FAILED) {
                JOptionPane.showMessageDialog(null, "添加签收信息时发生错误。签收失败");
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
                System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            }
        }
        return result;
    }
}
