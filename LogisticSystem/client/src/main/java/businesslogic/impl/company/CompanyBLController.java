package businesslogic.impl.company;

import businesslogic.service.company.*;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.*;
import data.vo.*;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wyc on 2015/11/22.
 * 主要用来对单据审批进行控制
 */
public class CompanyBLController {
    DataServiceFactory dataServiceFactory= null;
    SalaryManageBLService salary = null;
    StaffManageBLService staff = null;
    DataApproveBLService data = null;
    CityManageBLService city = null;
    StatisticsCheckBLService statistics = null;
    StaffVO staffVO = null;
    ArrayList<DataPO> dataPOs = null;
    String []institutionS = null;
    POType[] poTypes = null;
    String [] dataTypes = null;
    long []institutionL = null;
    long institution = 0;
    long fromInstitution = 0;
    long toInstitution = 0;

    public CompanyBLController(){
        //TODO 数组中的初始化数据
        salary = new SalaryManageBLImpl();
        staff = new StaffManageBLImpl();
        data = new DataApproveBLImpl();
        city = new CityManageBLImpl();
        statistics = new StatisticsCheckBLImpl();
        dataPOs = new ArrayList<DataPO>();
        institutionS = new String[]{""};
        institutionL = new long[]{};
        dataServiceFactory = new DataServiceFactory();
        staffVO = new StaffVO();
        //单据的名称与类型一一对应,进行查找
        poTypes = new POType[]{POType.ORDER, POType.RECEIPT, POType.PAYMENT, POType.ENTRUCK, POType.ARRIVAL, POType.ARRIVAL,
                               POType.STORAGEINLIST, POType.STORAGEOUTLIST, POType.SEND, POType.TRANSFERLIST};
        dataTypes = new String[]{"寄件单","收款单","付款单","装车单","营业厅到达单","中转到达单","入库单","出库单","派件单","中转单"};
    }

    //对审批单据的控制
    public ResultMessage approveData(String dataType, String ID){
        long id = Long.valueOf(ID);
        for(int i=0;i<dataTypes.length;i++){
            if(dataType.equals(dataTypes[i])){
                return data.approveData(poTypes[i],id);
            }
        }
        return ResultMessage.FAILED;
    }

    public ResultMessage approveAll(String dataType){
       for(int i=0;i<dataTypes.length;i++){
           if(dataType.equals(dataTypes[i])){
              return data.approveAll(poTypes[i]);
          }
       }
        return ResultMessage.FAILED;
    }

    //下面10个方法是根据不同的单据类型显示同类型单据
    public ArrayList<StorageInListPO> getUnapprovedStorageInList(){
        ArrayList<StorageInListPO> storageInListPOs = new ArrayList<StorageInListPO>();
        dataPOs = data.getUnapprovedData(POType.STORAGEINLIST);
        for(int i=0;i<dataPOs.size();i++){
            storageInListPOs.add((StorageInListPO) dataPOs.get(i));
        }
        return storageInListPOs;
    }

    public ArrayList<StorageOutListPO> getUnapprovedStoragedOutList(){
        ArrayList<StorageOutListPO> storageOutListPOs = new ArrayList<StorageOutListPO>();
        dataPOs = data.getUnapprovedData(POType.STORAGEOUTLIST);
        for(int i=0;i<dataPOs.size();i++){
            storageOutListPOs.add((StorageOutListPO)dataPOs.get(i));
        }
        return storageOutListPOs;
    }

    public ArrayList<ArrivalPO> getUnapprovedBusinessArrivalList(){
        ArrayList<ArrivalPO> arrivalPOs = new ArrayList<ArrivalPO>();
        dataPOs = data.getUnapprovedData(POType.ARRIVAL);
        for(int i=0;i<dataPOs.size();i++){
            if((dataPOs.get(i).getSerialNum()/1000)<10)
            arrivalPOs.add((ArrivalPO)dataPOs.get(i));
        }
        return arrivalPOs;
    }

    public ArrayList<ArrivalPO> getUnapprovedCenterArrivalList(){
        ArrayList<ArrivalPO> arrivalPOs = new ArrayList<ArrivalPO>();
        dataPOs = data.getUnapprovedData(POType.ARRIVAL);
        for(int i=0;i<dataPOs.size();i++){
            if((dataPOs.get(i).getSerialNum()/1000)>10)
            arrivalPOs.add((ArrivalPO)dataPOs.get(i));
        }
        return arrivalPOs;
    }

    public ArrayList<EntruckPO> getUnapprovedEntruckList(){
        ArrayList<EntruckPO> entruckPOs = new ArrayList<EntruckPO>();
        dataPOs = data.getUnapprovedData(POType.ENTRUCK);
        for(int i=0;i<dataPOs.size();i++){
            entruckPOs.add((EntruckPO)dataPOs.get(i));
        }
        return entruckPOs;
    }

    public ArrayList<OrderPO> getUnapprovedOrderList(){
        ArrayList<OrderPO> orderPOs = new ArrayList<OrderPO>();
        dataPOs = data.getUnapprovedData(POType.ORDER);
        for(int i=0;i<dataPOs.size();i++){
            orderPOs.add((OrderPO)dataPOs.get(i));
        }
        return orderPOs;
    }

    public ArrayList<PaymentPO> getUnapprovedPaymentList(){
        ArrayList<PaymentPO> paymentPOs = new ArrayList<PaymentPO>();
        dataPOs = data.getUnapprovedData(POType.PAYMENT);
        for(int i=0;i<dataPOs.size();i++){
            paymentPOs.add((PaymentPO)dataPOs.get(i));
        }
        return paymentPOs;
    }

    public ArrayList<ReceiptPO> getUnapprovedReceiptList(){
        ArrayList<ReceiptPO> receiptPOs = new ArrayList<ReceiptPO>();
        dataPOs = data.getUnapprovedData(POType.RECEIPT);
        for(int i=0;i<dataPOs.size();i++){
            receiptPOs.add((ReceiptPO)dataPOs.get(i));
        }
        return receiptPOs;
    }

    public ArrayList<SendListPO> getUnapprovedSendList(){
        ArrayList<SendListPO> sendPOs = new ArrayList<SendListPO>();
        dataPOs = data.getUnapprovedData(POType.SEND);
        for(int i=0;i<dataPOs.size();i++){
            sendPOs.add((SendListPO)dataPOs.get(i));
        }
        return sendPOs;
    }

    public ArrayList<TransferListPO> getUnapprovedTransferList(){
        ArrayList<TransferListPO> transferListPOs = new ArrayList<TransferListPO>();
        dataPOs = data.getUnapprovedData(POType.TRANSFERLIST);
        for(int i=0;i<dataPOs.size();i++){
            transferListPOs.add((TransferListPO)dataPOs.get(i));
        }
        return transferListPOs;
    }

    //修改单据数据
    public ResultMessage modifyStorageInList(StorageInVO storageInVO){
        return data.modifyStorageInList(storageInVO);
    }

    public ResultMessage modifyStorageOutList(StorageOutVO storageOutVO){
        return data.modifyStorageOutList(storageOutVO);
    }

    public ResultMessage modifyArrival(ArrivalVO arrivalVO){
        return data.modifyArrival(arrivalVO);
    }

    public ResultMessage modifyEntruck(EntruckListVO entruckListVO){
        return data.modifyEntruck(entruckListVO);
    }

    public ResultMessage modifyOrder(OrderVO orderVO){
        return data.modifyOrder(orderVO);
    }

    public ResultMessage modifyPayment(PaymentVO paymentVO){
        return  data.modifyPayment(paymentVO);
    }

    public ResultMessage modifyReceipt(ReceiptVO receiptVO){
        return data.modifyReceipt(receiptVO);
    }

    public ResultMessage modifySend(SendListVO sendListVO) {
        return data.modifySend(sendListVO);
    }

    public ResultMessage modifyTransferList(TransferListVO transferListVO) {
       return data.modifyTransferList(transferListVO);
    }

    public OrderPO getOrderData(long id){
        try {
            return (OrderPO) dataServiceFactory.getDataServiceByPO(POType.ORDER).search(POType.ORDER,id);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        return null;
    }

    //对SalaryManage的控制,主要是函数输入类型的转换
    public ArrayList<SalaryVO> searchAllSalary() {
        return salary.searchAllSalary();
    }

    public SalaryVO getsalaryByString(String institution) {
        return salary.getsalaryByString(institution);
    }

    public ResultMessage modifySalary(String institution, String salary){
        double sal = Double.valueOf(salary);
        return this.salary.modifySalary(institution,sal);
    }

    public ResultMessage addSalary(String institution, String salary, String type){
        double sal = Double.valueOf(salary);
        return this.salary.addSalary(institution,sal,type);
    }

    //对CityManage的控制，主要是函数输入类型的转换
    public CityTransVO getCityTransInfo(String fromCity, String toCity){
        return city.getCityTransInfo(fromCity,toCity);
    }

    public ResultMessage modifyCityInfo(CityTransVO cityTransVO){
        return city.modifyCityInfo(cityTransVO);
    }

    public ResultMessage addCityTransInfo(CityTransVO cityTransVO){
        return city.addCityTransInfo(cityTransVO);
    }

    //对StatisticsCheck的控制,主要是判断输入是否合法(是否符合年月日的大小)
    public ArrayList<ReceiptVO> searchReceiptVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay){
        int frommonth = Integer.parseInt(fromMonth);
        int tomonth = Integer.parseInt(toMonth);
        if(tomonth<=12&&tomonth>=1&&frommonth>=1&&frommonth<=12){
            ArrayList<ReceiptVO> receiptVOs = statistics.searchReceiptVO(fromYear, fromMonth, fromDay, toYear, toMonth, toDay);
            return receiptVOs;
        }
        else
            return null;
    }

    public ArrayList<PaymentVO> searchPaymentVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay){
        int frommonth = Integer.parseInt(fromMonth);
        int tomonth = Integer.parseInt(toMonth);
        if(tomonth<=12&&tomonth>=1&&frommonth>=1&&frommonth<=12){
            ArrayList<PaymentVO> paymentVOs = statistics.searchPaymentVO(fromYear, fromMonth, fromDay,toYear, toMonth, toDay);
            return paymentVOs;
        }
        else
            return null;
    }

    public CostBenefitVO searchCostBenefitVO(){
        return statistics.searchCostBenefitVO();
    }

    //对StaffManage的控制，主要是将机构的String型转为long型
    public ArrayList<StaffVO> getStaffByInstitution(String institution){
        return staff.getStaffByInstitution(this.institution);
    }

    public StaffVO getstaffByID(long id){
        return staff.getstaffByID(id);
    }

    public ResultMessage addStaff(String institution, String id, boolean gender, String name, String phoneNum){
      //TODO
        StaffVO staffVO = new StaffVO();
      //  staffVO.setInstitution(institution);
        staffVO.setId(Long.valueOf(id));
        staffVO.setPhoneNum(Long.valueOf(phoneNum));
        staffVO.setGender(gender);
        return staff.addStaff(staffVO,Long.valueOf(id));
    }

    public ResultMessage deleteStaff(String institution, String ID) {
        long id = Long.valueOf(ID);
        return staff.deleteStaff(this.institution,id);
    }

    public ResultMessage moveStaff(String fromInstitution, String toInstitution, String ID){
        long id;
        id = Long.valueOf(ID);
        return staff.moveStaff(this.fromInstitution,this.toInstitution,id);
    }

}
