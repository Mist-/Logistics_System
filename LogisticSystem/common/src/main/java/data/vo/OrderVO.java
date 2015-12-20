package data.vo;


import data.enums.DataState;
import data.enums.ServiceType;
import data.po.OrderPO;
import utils.Timestamper;

import java.util.ArrayList;

/**
 *
 * Created by Mouse on 2015/10/23 0023.
 */
public class OrderVO {

    public OrderVO(){}

    public String date;
    // 收/寄件人信息，s开头表示寄件人（sender），r开头表示收件人（receiver）
    public String sname, saddress, scompany, sphone,
            rname, raddress, rcompany, rphone;

    // 货物数量
    public int stockNum;

    public int evaluatedTime;

    //金额
    public double fee;

    //单号
    public long id;

    public ServiceType serviceType;

    // 重量/kg、体积/cm^3
    public double weight, volume;

    // 字符串保存每一件货品的类型（名称）
    public String stockType[];

    public ArrayList<Long> routine;

    // 单据的审批状态
    public DataState dataState = DataState.APPROVING;

    public long courier;

    // 构造函数。因为是VO，所以在构造函数里面一次性完成啦。
    public OrderVO(String sname, String saddress, String scompany, String sphone,
                   String rname, String raddress, String rcompany, String rphone,
                   int stockNum, double weight, double volume, String stockType[], ServiceType type, double fee, long id) {
        this.sname = sname;
        this.saddress = saddress;
        this.scompany = scompany;
        this.sphone = sphone;
        this.rname = rname;
        this.raddress = raddress;
        this.rcompany = rcompany;
        this.rphone = rphone;
        this.stockNum = stockNum;
        this.weight = weight;
        this.volume = volume;
        this.stockType = stockType;
        this.serviceType = type;
        this.fee = fee;
        this.id = id;
    }

    public  OrderVO(OrderPO orderPO) {
        this.sname = orderPO.getSname();
        this.saddress = orderPO.getSaddress();
        this.scompany = orderPO.getScompany();
        this.sphone = orderPO.getSphone();
        this.rname = orderPO.getRname();
        this.raddress = orderPO.getRaddress();
        this.rcompany = orderPO.getRcompany();
        this.rphone = orderPO.getRphone();
        this.stockNum = orderPO.getStockNum();
        this.weight = orderPO.getWeight();
        this.volume = orderPO.getVolume();
        this.stockType = orderPO.getStockType();
        this.serviceType = orderPO.getServiceType();
        this.fee = orderPO.getFee();
        this.id = orderPO.getSerialNum();
        this.date = Timestamper.getTimeByDate(orderPO.getGenDate());
        this.dataState = orderPO.getState();
        this.courier =  orderPO.getCourier();
        this.routine = orderPO.getRoutine();
    }

    public OrderVO(int stockNum, double weight,  ServiceType type, double fee, long id){
        this.stockNum = stockNum;
        this.weight = weight;
        this.serviceType = type;
        this.fee = fee;
        this.id = id;
    }
}
