package data.po;

import data.enums.POType;
import data.enums.ServiceType;
import data.enums.StorageArea;
import data.vo.OrderVO;

import java.util.ArrayList;

public class OrderPO extends DataPO {

    private static final long serialVersionUID = 10;

    public static final int SNAME = 1, SADDRESS = 2, SCOMPANY = 3, SPHONE = 4, RNAME = 5, RADDRESS = 6, RCOMPANY = 7, RPHONE = 8;
    //新增：运输方式
    StorageArea transferType;
    String sname, saddress, scompany, sphone,
            rname, rcompany, rphone;
    /**
     * raadress的格式要求：
     * 城市-区-详细地址
     */
    String raddress;
    /**
     * 物流路径。最多包含四站
     */
    ArrayList<Long> routine;
    // 目标营业厅的编号
    long destID;

    // 货物数量
    int stockNum;

    // 货物重量和体积
    double weight, volume;

    // 货物种类
    String stockType[];

    // 快递服务类型
    ServiceType serviceType;

    // 快递费用
    double fee;

    // 预计送达时间
    int evaluatedTime;

    // 揽件的快递员
    long courier;

    public long getCourier() {
        return courier;
    }

    public void setCourier(long sn) {
        courier = sn;
    }

    public String[] getStockType() {
        return stockType;
    }

    public String getRcompany() {
        return rcompany;
    }

    public String getScompany() {
        return scompany;
    }

    public OrderPO() {
        super(POType.ORDER);
        routine = new ArrayList<>();
    }


    public OrderPO(int stockNum) {
        super(POType.ORDER);
        stockType = new String[stockNum];
        routine = new ArrayList<>();
    }

    public OrderPO(OrderVO order) {
        super(POType.ORDER);
        saddress = order.saddress;
        sname = order.sname;
        scompany = order.scompany;
        sphone = order.sphone;
        raddress = order.raddress;
        rname = order.rname;
        rcompany = order.rcompany;
        rphone = order.rphone;
        serviceType = order.serviceType;
        fee = order.fee;
        evaluatedTime = order.evaluatedTime;
        volume = order.volume;
        weight = order.weight;
        setState(order.dataState);
    }

    public StorageArea getTransferType() {
        return transferType;
    }

    public void setTransferType(StorageArea transferType) {
        this.transferType = transferType;
    }

    public void setRoutine(long s1, long s2, long s3, long s4) {
        routine.add(s1);
        routine.add(s2);
        routine.add(s3);
        routine.add(s4);
    }

    public void setRoutine(long s1, long s2) {
        routine.add(s1);
        routine.add(s2);
    }

    public ArrayList<Long> getRoutine() {
        return routine;
    }

    public void setRoutine(ArrayList<Long> routine) {
        this.routine = routine;
    }

    /**
     * 获取下一站的目的地。
     * 这回自动将这一站从路线中删除。
     *
     * @return
     */
    public long getNextDestination() {
        long sn = routine.get(0);
        routine.remove(0);
        return sn;
    }

    public long getDestID() {
        return destID;
    }

    public void setDestID(long destID) {
        this.destID = destID;
    }


    public long getSerialNum() {
        return serialNum;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getSInfo(int infoType) {
        switch (infoType) {
            case SNAME:
                return sname;
            case SADDRESS:
                return saddress;
            case SCOMPANY:
                return scompany;
            case SPHONE:
                return sphone;
            case RNAME:
                return rname;
            case RADDRESS:
                return raddress;
            case RCOMPANY:
                return rcompany;
            case RPHONE:
                return rphone;
            default:
                return "";
        }
    }

    public void setInfo(int infoType, String info) {
        switch (infoType) {
            case SNAME:
                sname = info;
            case SADDRESS:
                saddress = info;
            case SCOMPANY:
                scompany = info;
            case SPHONE:
                sphone = info;
            case RNAME:
                rname = info;
            case RADDRESS:
                raddress = info;
            case RCOMPANY:
                rcompany = info;
            case RPHONE:
                rphone = info;
            default:
        }
    }

    public void setInfo(OrderVO order) {
        this.sname = order.sname;
        this.saddress = order.saddress;
        this.scompany = order.scompany;
        this.sphone = order.sphone;
        this.rname = order.rname;
        this.raddress = order.raddress;
        this.rcompany = order.rcompany;
        this.rphone = order.rphone;
    }

    /**
     * @param num  number of the stocks
     * @param type a String array that stores the name of the stocks. be adviced that the length of <code>type</code> must equal <code>IDCard</code>
     */
    public void setStockNum(int num, String type[]) {
        this.stockNum = num;
        stockType = new String[num];
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getEvaluatedTime() {
        return evaluatedTime;
    }

    public void setEvaluatedTime(int time) {
        evaluatedTime = time;
    }

    public String getRname(){
        return rname;
    }

    public String getRphone(){
        return rphone;
    }

    public String getSname(){
        return sname;
    }

    public String getSphone(){
        return sphone;
    }

    public String getSaddress(){
        return saddress;
    }

    public String getRaddress(){
        return raddress;
    }

    public int getStockNum(){
        return stockNum;
    }

    public void setStockNum(int stockNum){
        this.stockNum = stockNum;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public void setScompany(String scompany) {
        this.scompany = scompany;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setRcompany(String rcompany) {
        this.rcompany = rcompany;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

    public void fastModify(OrderVO order) {
        saddress = order.saddress;
        sname = order.sname;
        scompany = order.scompany;
        sphone = order.sphone;
        raddress = order.raddress;
        rname = order.rname;
        rcompany = order.rcompany;
        rphone = order.rphone;
        serviceType = order.serviceType;
        fee = order.fee;
        evaluatedTime = order.evaluatedTime;
        volume = order.volume;
        weight = order.weight;
        setState(order.dataState);
    }
}

