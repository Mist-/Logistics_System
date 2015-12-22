package businesslogic.impl.company;

import businesslogic.service.company.*;
import data.enums.POType;
import data.enums.ServiceType;
import data.enums.UserRole;
import utils.DataServiceFactory;
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

    public CompanyBLController(){
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
        poTypes = new POType[]{POType.ORDER, POType.RECEIPT, POType.PAYMENT, POType.ENTRUCK, POType.ARRIVAL,
                               POType.STORAGEINLIST, POType.STORAGEOUTLIST, POType.SEND, POType.TRANSFERLIST};
        dataTypes = new String[]{"寄件单","收款单","付款单","装车单","到达单","入库单","出库单","派件单","中转单"};
    }

    /**
     * 审批单条单据
     *
     * @param dataType 等待审批的单据类型
     * @param ID 等待审批单据的id
     * @return  审批成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage approveData(String dataType, String ID){
        System.out.println(ID);
        long id = Long.parseLong(ID);
        for(int i=0;i<dataTypes.length;i++){
            if(dataType.equals(dataTypes[i])){
                return data.approveData(poTypes[i],id);
            }
        }
        return ResultMessage.NOTEXIST;
    }

    /**
     * 审批同一类型的所有单据
     *
     * @param dataType 等待审批的单据类型
     * @return  审批成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage approveAll(String dataType){
       for(int i=0;i<dataTypes.length;i++){
           if(dataType.equals(dataTypes[i])){
              return data.approveAll(poTypes[i]);
          }
       }
        return ResultMessage.NOTEXIST;
    }

    /**
     * 获得未审批的入库单
     *
     * @return  未审批的入库单
     */
    public ArrayList<StorageInListPO> getUnapprovedStorageInList(){
        ArrayList<StorageInListPO> storageInListPOs = new ArrayList<StorageInListPO>();
        dataPOs = data.getUnapprovedData(POType.STORAGEINLIST);
        for(int i=0;i<dataPOs.size();i++){
            storageInListPOs.add((StorageInListPO) dataPOs.get(i));
        }
        return storageInListPOs;
    }

    /**
     * 获得未审批的出库单
     *
     * @return  未审批的入库单
     */
    public ArrayList<StorageOutListPO> getUnapprovedStoragedOutList(){
        ArrayList<StorageOutListPO> storageOutListPOs = new ArrayList<StorageOutListPO>();
        dataPOs = data.getUnapprovedData(POType.STORAGEOUTLIST);
        for(int i=0;i<dataPOs.size();i++){
            storageOutListPOs.add((StorageOutListPO)dataPOs.get(i));
        }
        return storageOutListPOs;
    }

    /**
     * 获得未审批的到达单
     *
     * @return  未审批的到达单
     */
    public ArrayList<ArrivalPO> getUnapprovedArrivalList(){
        ArrayList<ArrivalPO> arrivalPOs = new ArrayList<ArrivalPO>();
        dataPOs = data.getUnapprovedData(POType.ARRIVAL);
        for(int i=0;i<dataPOs.size();i++){
            arrivalPOs.add((ArrivalPO)dataPOs.get(i));
        }
        return arrivalPOs;
    }

    /**
     * 获得未审批的装车单
     *
     * @return  未审批的装车单
     */
    public ArrayList<EntruckPO> getUnapprovedEntruckList(){
        ArrayList<EntruckPO> entruckPOs = new ArrayList<EntruckPO>();
        dataPOs = data.getUnapprovedData(POType.ENTRUCK);
        for(int i=0;i<dataPOs.size();i++){
            entruckPOs.add((EntruckPO)dataPOs.get(i));
        }
        return entruckPOs;
    }

    /**
     * 获得未审批的寄件单
     *
     * @return  未审批的寄件单
     */
    public ArrayList<OrderPO> getUnapprovedOrderList(){
        ArrayList<OrderPO> orderPOs = new ArrayList<OrderPO>();
        dataPOs = data.getUnapprovedData(POType.ORDER);
        for(int i=0;i<dataPOs.size();i++){
            orderPOs.add((OrderPO)dataPOs.get(i));
        }
        return orderPOs;
    }

    /**
     * 获得未审批的付款单
     *
     * @return  未审批的付款单
     */
    public ArrayList<PaymentPO> getUnapprovedPaymentList(){
        ArrayList<PaymentPO> paymentPOs = new ArrayList<PaymentPO>();
        dataPOs = data.getUnapprovedData(POType.PAYMENT);
        for(int i=0;i<dataPOs.size();i++){
            paymentPOs.add((PaymentPO)dataPOs.get(i));
        }
        return paymentPOs;
    }

    /**
     * 获得未审批的入款单
     *
     * @return  未审批的入款单
     */
    public ArrayList<ReceiptPO> getUnapprovedReceiptList(){
        ArrayList<ReceiptPO> receiptPOs = new ArrayList<ReceiptPO>();
        dataPOs = data.getUnapprovedData(POType.RECEIPT);
        for(int i=0;i<dataPOs.size();i++){
            receiptPOs.add((ReceiptPO)dataPOs.get(i));
        }
        return receiptPOs;
    }

    /**
     * 获得未审批的派件单
     *
     * @return  未审批的派件单
     */
    public ArrayList<SendListPO> getUnapprovedSendList(){
        ArrayList<SendListPO> sendPOs = new ArrayList<SendListPO>();
        dataPOs = data.getUnapprovedData(POType.SEND);
        for(int i=0;i<dataPOs.size();i++){
            sendPOs.add((SendListPO)dataPOs.get(i));
        }
        return sendPOs;
    }

    /**
     * 获得未审批的中转单
     *
     * @return  未审批的中转单
     */
    public ArrayList<TransferListPO> getUnapprovedTransferList(){
        ArrayList<TransferListPO> transferListPOs = new ArrayList<TransferListPO>();
        dataPOs = data.getUnapprovedData(POType.TRANSFERLIST);
        for(int i=0;i<dataPOs.size();i++){
            transferListPOs.add((TransferListPO)dataPOs.get(i));
        }
        return transferListPOs;
    }

    /**
     * 修改入库单PO的数据
     *
     * @param storageInVO 单个入库单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED
     */
    public ResultMessage modifyStorageInList(StorageInVO storageInVO){
        return data.modifyStorageInList(storageInVO);
    }

    /**
     * 修改出库单PO的数据
     *
     * @param storageOutVO 单个出库单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED
     */
    public ResultMessage modifyStorageOutList(StorageOutVO storageOutVO){
        return data.modifyStorageOutList(storageOutVO);
    }

    /**
     * 修改到达单PO的数据
     *
     * @param arrivalVO 单个到达单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyArrival(ArrivalVO arrivalVO){
        return data.modifyArrival(arrivalVO);
    }

    /**
     * 修改装车单PO的数据
     *
     * @param entruckListVO 单个装车单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyEntruck(EntruckListVO entruckListVO){
        return data.modifyEntruck(entruckListVO);
    }

    /**
     * 修改寄件单PO的数据
     *
     * @param orderVO 单个寄件单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyOrder(OrderVO orderVO){
        return data.modifyOrder(orderVO);
    }

    /**
     * 修改付款单PO的数据
     *
     * @param paymentVO 单个付款单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyPayment(PaymentVO paymentVO){
        return  data.modifyPayment(paymentVO);
    }

    /**
     * 修改收款单PO的数据
     *
     * @param receiptVO 单个收款单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyReceipt(ReceiptVO receiptVO){
        return data.modifyReceipt(receiptVO);
    }

    /**
     * 修改派件单PO的数据
     *
     * @param sendListVO 单个派件单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifySend(SendListVO sendListVO) {
        return data.modifySend(sendListVO);
    }

    /**
     * 修改中转单PO的数据
     *
     * @param transferListVO 单个中转单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    public ResultMessage modifyTransferList(TransferListVO transferListVO) {
       return data.modifyTransferList(transferListVO);
    }

     /*   public OrderPO getOrderData(long id){
        try {
            return (OrderPO) dataServiceFactory.getDataServiceByPO(POType.ORDER).search(POType.ORDER,id);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        return null;
    }*/

    /**
     * 获得所有部门的工资的SalaryVO表
     *
     * @return  表的引用
     */
    public ArrayList<SalaryVO> searchAllSalary() {
        return salary.searchAllSalary();
    }

    /**
     * 获得单个部门的SalaryVO数据
     *
     * @param institution  部门的名称
     * @return  此部门SalaryVO的引用
     */
    public SalaryVO getsalaryByString(String institution) {
        return salary.getsalaryByString(institution);
    }

    /**
     * 修改单个部门的工资数据
     *
     * @param institution  部门的名称
     * @param salary  工资的数值
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,部门不存在是NOTEXSIT
     */
    public ResultMessage modifySalary(String institution, String salary){
        double sal = Double.valueOf(salary);
        return this.salary.modifySalary(institution,sal);
    }

    /**
     * 添加单个部门工资的数据
     *
     * @param institution  部门名称
     * @param salary  工资的数值
     * @param type  结算工资的方式
     * @return  添加成功是SUCCESS,网络错误是NOTCONNECTED,部门已经存在是EXIST
     */
    public ResultMessage addSalary(String institution, String salary, String type){
        double sal = Double.valueOf(salary);
        return this.salary.addSalary(institution,sal,type);
    }

    /**
     * 获得城市之间物流信息CityTransInfoVO数据
     *
     * @param fromCity 起始城市的名称
     * @param toCity  结束城市的名称
     * @return  获得CityTransInfoVO的引用
     */
    public CityTransVO getCityTransInfo(String fromCity, String toCity){
        return city.getCityTransInfo(fromCity,toCity);
    }

    /**
     * 修改城市之间物流信息
     *
     * @param cityTransVO  城市之间物流信息的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,城市不存在是NOTEXSIT
     */
    public ResultMessage modifyCityInfo(CityTransVO cityTransVO){
        return city.modifyCityInfo(cityTransVO);
    }

    /**
     * 添加城市之间物流信息
     *
     * @param cityTransVO  城市之间物流信息的VO
     * @return  添加成功是SUCCESS,网络错误是NOTCONNECTED,城市物流信息已经存在是EXIST
     */
    public ResultMessage addCityTransInfo(CityTransVO cityTransVO){
        return city.addCityTransInfo(cityTransVO);
    }

    /**
     * 查找指定日期之间的所有入款单ReceiptVO表
     *
     * @param fromYear  起始年份
     * @param fromMonth  起始月份
     * @param fromDay  起始日
     * @param toYear  结束年份
     * @param toMonth  结束月份
     * @param toDay  结束日
     * @return  表的引用
     */
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

    /**
     * 查找指定日期之间的所有付款单PaymentVO表
     *
     * @param fromYear  起始年份
     * @param fromMonth  起始月份
     * @param fromDay  起始日
     * @param toYear  结束年份
     * @param toMonth  结束月份
     * @param toDay  结束日
     * @return  表的引用
     */
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

    /**
     * 查找成本收益表(CostBenefitVO)
     *
     * @return VO的引用
     */
    public CostBenefitVO searchCostBenefitVO(){
        return statistics.searchCostBenefitVO();
    }

    /**
     * 根据部门名称获得该部门所有员工信息StaffVO表
     *
     * @param institution  部门名称
     * @return  表的引用
     */
    public ArrayList<StaffVO> getStaffByInstitution(String institution){
        return staff.getStaffByInstitution(longInstitution(institution));
    }

    /**
     * 根据员工id获得员工信息StaffVO
     *
     * @param id  员工id
     * @return  StaffVO的引用
     */
    public StaffVO getstaffByID(long id){
        return staff.getstaffByID(id);
    }

    /**
     * 添加单个员工
     *
     * @param staffVO  该员工所有信息
     * @return  添加成功是SUCCESS,网络错误是NOTCONNECTED,员工id已经存在是EXIST
     */
    public ResultMessage addStaff(StaffVO staffVO){
        return staff.addStaff(staffVO,staffVO.getId());
    }

    /**
     * 删除单个员工
     *
     * @param institution  员工所在机构名称
     * @param ID  员工的id
     * @return  删除成功是SUCCESS,网络错误是NOTCONNECTED,员工不存在是NOTEXIST
     */
    public ResultMessage deleteStaff(String institution, String ID) {
        //判断网络状况和机构是否存在
        if(longInstitution(institution)==0){
            return ResultMessage.NOTEXIST;
        }
        else if(longInstitution(institution)==1){
            return ResultMessage.NOTCONNECTED;
        }
        else {
            long id = Long.valueOf(ID);
            return staff.deleteStaff(this.institution,id);
        }
    }

    /**
     * 移动员工所在机构
     * @param fromInstitution  员工当前所在机构名称
     * @param toInstitution  员工将要被移动到的部门名称
     * @param ID  员工的id
     * @param stringUserRole  员工被移动到部门的角色
     * @return  移动成功是SUCCESS,网络错误是NOTCONNECTED,机构不存在是EXIST
     */
    public ResultMessage moveStaff(String fromInstitution, String toInstitution, String ID, String stringUserRole){
        //判断网络状况和机构是否存在
        if(longInstitution(fromInstitution)==0){
            return ResultMessage.NOTEXIST;
        }
        else if(longInstitution(fromInstitution)==1){
            return ResultMessage.NOTCONNECTED;
        }
        else {
            //判断网络状况和机构是否存在
            if(longInstitution(toInstitution)==0){
                return ResultMessage.NOTEXIST;
            }
            else if(longInstitution(toInstitution)==1){
                return ResultMessage.NOTCONNECTED;
            }
            else {
                long id;
                id = Long.valueOf(ID);
                UserRole userRole = null;
                if(stringUserRole!=null) {
                    userRole = UserRole.valueOf(stringUserRole);
                }
                return staff.moveStaff(longInstitution(fromInstitution),longInstitution(toInstitution),id,userRole);
            }
        }

    }

    /**
     * 将部门由名称改为编号
     *
     * @param institution 机构的名称
     * @return  机构的编号
     */
    public long longInstitution(String institution){
        try {
            ArrayList<DataPO> institutionPOs = DataServiceFactory.getDataServiceByPO(POType.INSTITUTION).getPOList(POType.INSTITUTION);
            for(int i=0;i<institutionPOs.size();i++){
                InstitutionPO institutionPO = (InstitutionPO) institutionPOs.get(i);
                if(institutionPO.getName().equals(institution)){
                    return institutionPO.getSerialNum();
                }
            }
            return 0;
        } catch (RemoteException e) {
           return 1;
        }
    }

    /**
     * 根据员工id获得员工姓名
     *
     * @param id  员工id
     * @return  员工姓名
     */
    public String getNameById(long id){
        try {
            ArrayList<DataPO> staffs = DataServiceFactory.getDataServiceByPO(POType.STAFF).getPOList(POType.STAFF);
            for(int i=0;i<staffs.size();i++){
                StaffPO staffPO = (StaffPO) staffs.get(i);
                if(staffPO.getSerialNum() == id){
                    return staffPO.getName();
                }
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    /**
     * 获取所有城市的名称数组
     *
     * @return  数组的引用
     */
    public String[] getCitys(){
        try {
            ArrayList<DataPO> cityInfoPOs = DataServiceFactory.getDataServiceByPO(POType.CITYINFO).getPOList(POType.CITYINFO);
            String [] citys = new String[cityInfoPOs.size()];
            for(int i=0;i<cityInfoPOs.size();i++){
                CityInfoPO cityInfoPO = (CityInfoPO) cityInfoPOs.get(i);
                citys[i] = cityInfoPO.getName();
            }
            return citys;
        } catch (RemoteException e) {
            return null;
        }

    }

    /**
     * 根据城市名字获取该城市下所有营业厅名称表
     *
     * @param city  城市名称
     * @return  获得表的引用
     */
    public ArrayList<String> getBusinessOffices(String city){
        try {
            ArrayList<DataPO> cityInfoPOs = DataServiceFactory.getDataServiceByPO(POType.CITYINFO).getPOList(POType.CITYINFO);
            ArrayList<String> businessOffices = new ArrayList<>();
            for(int i=0;i<cityInfoPOs.size();i++){
                CityInfoPO cityInfoPO = (CityInfoPO) cityInfoPOs.get(i);
                if(cityInfoPO.getName().equals(city)){
                    ArrayList<Long> businessOfficesId = cityInfoPO.getBusinessOfficeList();
                    ArrayList<DataPO> institutions = DataServiceFactory.getDataServiceByPO(POType.INSTITUTION).getPOList(POType.INSTITUTION);
                    for(int k=0;k<businessOfficesId.size();k++){
                        for(int j=0;j<institutions.size();j++){
                            InstitutionPO institutionPO = (InstitutionPO) institutions.get(j);
                            if(institutionPO.getSerialNum() == businessOfficesId.get(k)){
                                businessOffices.add(institutionPO.getName());
                            }
                        }
                    }
                    break;
                }
            }
            return businessOffices;
        } catch (RemoteException e) {
            return null;
        }
    }

    /**
     * 判断输入是否是数字不是字符
     *
     * @param str  指定字符串
     * @return  是否是int或者double型并且>=0
     */
    public boolean isValidNum(String str){
        boolean isValid = true;
        for(int i=0;i<str.length();i++){
            if((str.charAt(i)<='9'&&str.charAt(i)>='0')||str.charAt(i)=='.'){}
            else{
                isValid = false;
                break;
            }
        }
        if(isValid){
            long num = Long.valueOf(str);
            if(num <= 0)
                isValid = false;
        }
        return isValid;
    }

    /**
     * 判断身份证号码是否正确
     *
     * @param idCardNum
     * @return  是否符合规范
     */
    public boolean isValidIdNum(String idCardNum){
        boolean isValid = true;
        for(int i=0;i<idCardNum.length()-1;i++){
            if(idCardNum.charAt(i)<='9'&&idCardNum.charAt(i)>='0'){}
            else{
                return false;
            }
        }
        if(idCardNum.charAt(idCardNum.length()-1)!='X'&&idCardNum.charAt(idCardNum.length()-1)<'0'&&idCardNum.charAt(idCardNum.length()-1)>'9'){
            isValid = false;
        }
        return isValid;
    }

    /**
     * 判断快递类型是否符合
     *
     * @param serviceType
     * @return  是否符合规范
     */
    public boolean isValidType(String serviceType){
        ServiceType [] servicesTypes = ServiceType.values();
        for(int i=0;i<servicesTypes.length;i++){
            if(serviceType.equals(servicesTypes[i].toString()))
                return true;
        }
        return false;
    }
}
