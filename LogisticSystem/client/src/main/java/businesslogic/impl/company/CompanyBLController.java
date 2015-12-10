package businesslogic.impl.company;

import businesslogic.service.company.*;
import data.enums.POType;
import data.enums.UserRole;
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
 * ��Ҫ�����Ե����������п���
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
        //TODO �����еĳ�ʼ������
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
        //���ݵ�����������һһ��Ӧ,���в���
        poTypes = new POType[]{POType.ORDER, POType.RECEIPT, POType.PAYMENT, POType.ENTRUCK, POType.ARRIVAL,
                               POType.STORAGEINLIST, POType.STORAGEOUTLIST, POType.SEND, POType.TRANSFERLIST};
        dataTypes = new String[]{"�ļ���","�տ","���","װ����","���ﵥ","��ⵥ","���ⵥ","�ɼ���","��ת��"};
    }

    //���������ݵĿ���
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

    //����10�������Ǹ��ݲ�ͬ�ĵ���������ʾͬ���͵���
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

    //�޸ĵ�������
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
            System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
        }
        return null;
    }

    //��SalaryManage�Ŀ���,��Ҫ�Ǻ����������͵�ת��
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

    //��CityManage�Ŀ��ƣ���Ҫ�Ǻ����������͵�ת��
    public CityTransVO getCityTransInfo(String fromCity, String toCity){
        return city.getCityTransInfo(fromCity,toCity);
    }

    public ResultMessage modifyCityInfo(CityTransVO cityTransVO){
        return city.modifyCityInfo(cityTransVO);
    }

    public ResultMessage addCityTransInfo(CityTransVO cityTransVO){
        return city.addCityTransInfo(cityTransVO);
    }

    //��StatisticsCheck�Ŀ���,��Ҫ���ж������Ƿ�Ϸ�(�Ƿ���������յĴ�С)
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

    //��StaffManage�Ŀ��ƣ���Ҫ�ǽ�������String��תΪlong��
    public ArrayList<StaffVO> getStaffByInstitution(String institution){
        return staff.getStaffByInstitution(longInstitution(institution));
    }

    public StaffVO getstaffByID(long id){
        return staff.getstaffByID(id);
    }

    public ResultMessage addStaff(StaffVO staffVO){
        return staff.addStaff(staffVO,staffVO.getId());
    }

    public ResultMessage deleteStaff(String institution, String ID) {
        //�ж�����״���ͻ����Ƿ����
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

    public ResultMessage moveStaff(String fromInstitution, String toInstitution, String ID, String stringUserRole){
        //�ж�����״���ͻ����Ƿ����
        if(longInstitution(fromInstitution)==0){
            return ResultMessage.NOTEXIST;
        }
        else if(longInstitution(fromInstitution)==1){
            return ResultMessage.NOTCONNECTED;
        }
        else {
            //�ж�����״���ͻ����Ƿ����
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

    //��institution��String��Ϊlong
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

    //����Ա��id��ȡԱ������
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

    //��ȡ���г�������
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

    //���ݳ������ֻ�ȡӪҵ������
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

    //�ж������Ƿ������ֲ����ַ�
    public boolean isNum(String str){
        for(int i=0;i<str.length();i++){
            if((str.charAt(i)<='9'&&str.charAt(i)>='0')||str.charAt(i)=='.'){
                return true;
            }
        }
        return false;
    }
}
