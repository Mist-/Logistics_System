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
        //���ݵ�����������һһ��Ӧ,���в���
        poTypes = new POType[]{POType.ORDER, POType.RECEIPT, POType.PAYMENT, POType.ENTRUCK, POType.ARRIVAL,
                               POType.STORAGEINLIST, POType.STORAGEOUTLIST, POType.SEND, POType.TRANSFERLIST};
        dataTypes = new String[]{"�ļ���","�տ","���","װ����","���ﵥ","��ⵥ","���ⵥ","�ɼ���","��ת��"};
    }

    /**
     * ������������
     *
     * @param dataType �ȴ������ĵ�������
     * @param ID �ȴ��������ݵ�id
     * @return  �����ɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
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
     * ����ͬһ���͵����е���
     *
     * @param dataType �ȴ������ĵ�������
     * @return  �����ɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
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
     * ���δ��������ⵥ
     *
     * @return  δ��������ⵥ
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
     * ���δ�����ĳ��ⵥ
     *
     * @return  δ��������ⵥ
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
     * ���δ�����ĵ��ﵥ
     *
     * @return  δ�����ĵ��ﵥ
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
     * ���δ������װ����
     *
     * @return  δ������װ����
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
     * ���δ�����ļļ���
     *
     * @return  δ�����ļļ���
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
     * ���δ�����ĸ��
     *
     * @return  δ�����ĸ��
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
     * ���δ��������
     *
     * @return  δ��������
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
     * ���δ�������ɼ���
     *
     * @return  δ�������ɼ���
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
     * ���δ��������ת��
     *
     * @return  δ��������ת��
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
     * �޸���ⵥPO������
     *
     * @param storageInVO ������ⵥ��VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED
     */
    public ResultMessage modifyStorageInList(StorageInVO storageInVO){
        return data.modifyStorageInList(storageInVO);
    }

    /**
     * �޸ĳ��ⵥPO������
     *
     * @param storageOutVO �������ⵥ��VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED
     */
    public ResultMessage modifyStorageOutList(StorageOutVO storageOutVO){
        return data.modifyStorageOutList(storageOutVO);
    }

    /**
     * �޸ĵ��ﵥPO������
     *
     * @param arrivalVO �������ﵥ��VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyArrival(ArrivalVO arrivalVO){
        return data.modifyArrival(arrivalVO);
    }

    /**
     * �޸�װ����PO������
     *
     * @param entruckListVO ����װ������VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyEntruck(EntruckListVO entruckListVO){
        return data.modifyEntruck(entruckListVO);
    }

    /**
     * �޸ļļ���PO������
     *
     * @param orderVO �����ļ�����VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyOrder(OrderVO orderVO){
        return data.modifyOrder(orderVO);
    }

    /**
     * �޸ĸ��PO������
     *
     * @param paymentVO ���������VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyPayment(PaymentVO paymentVO){
        return  data.modifyPayment(paymentVO);
    }

    /**
     * �޸��տPO������
     *
     * @param receiptVO �����տ��VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyReceipt(ReceiptVO receiptVO){
        return data.modifyReceipt(receiptVO);
    }

    /**
     * �޸��ɼ���PO������
     *
     * @param sendListVO �����ɼ�����VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifySend(SendListVO sendListVO) {
        return data.modifySend(sendListVO);
    }

    /**
     * �޸���ת��PO������
     *
     * @param transferListVO ������ת����VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,����������NOTEXIST
     */
    public ResultMessage modifyTransferList(TransferListVO transferListVO) {
       return data.modifyTransferList(transferListVO);
    }

     /*   public OrderPO getOrderData(long id){
        try {
            return (OrderPO) dataServiceFactory.getDataServiceByPO(POType.ORDER).search(POType.ORDER,id);
        } catch (RemoteException e) {
            System.err.println("�������(" + Connection.RMI_PREFIX + ")�����ӶϿ� -" + Calendar.getInstance().getTime());
        }
        return null;
    }*/

    /**
     * ������в��ŵĹ��ʵ�SalaryVO��
     *
     * @return  �������
     */
    public ArrayList<SalaryVO> searchAllSalary() {
        return salary.searchAllSalary();
    }

    /**
     * ��õ������ŵ�SalaryVO����
     *
     * @param institution  ���ŵ�����
     * @return  �˲���SalaryVO������
     */
    public SalaryVO getsalaryByString(String institution) {
        return salary.getsalaryByString(institution);
    }

    /**
     * �޸ĵ������ŵĹ�������
     *
     * @param institution  ���ŵ�����
     * @param salary  ���ʵ���ֵ
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,���Ų�������NOTEXSIT
     */
    public ResultMessage modifySalary(String institution, String salary){
        double sal = Double.valueOf(salary);
        return this.salary.modifySalary(institution,sal);
    }

    /**
     * ��ӵ������Ź��ʵ�����
     *
     * @param institution  ��������
     * @param salary  ���ʵ���ֵ
     * @param type  ���㹤�ʵķ�ʽ
     * @return  ��ӳɹ���SUCCESS,���������NOTCONNECTED,�����Ѿ�������EXIST
     */
    public ResultMessage addSalary(String institution, String salary, String type){
        double sal = Double.valueOf(salary);
        return this.salary.addSalary(institution,sal,type);
    }

    /**
     * ��ó���֮��������ϢCityTransInfoVO����
     *
     * @param fromCity ��ʼ���е�����
     * @param toCity  �������е�����
     * @return  ���CityTransInfoVO������
     */
    public CityTransVO getCityTransInfo(String fromCity, String toCity){
        return city.getCityTransInfo(fromCity,toCity);
    }

    /**
     * �޸ĳ���֮��������Ϣ
     *
     * @param cityTransVO  ����֮��������Ϣ��VO
     * @return  �޸ĳɹ���SUCCESS,���������NOTCONNECTED,���в�������NOTEXSIT
     */
    public ResultMessage modifyCityInfo(CityTransVO cityTransVO){
        return city.modifyCityInfo(cityTransVO);
    }

    /**
     * ��ӳ���֮��������Ϣ
     *
     * @param cityTransVO  ����֮��������Ϣ��VO
     * @return  ��ӳɹ���SUCCESS,���������NOTCONNECTED,����������Ϣ�Ѿ�������EXIST
     */
    public ResultMessage addCityTransInfo(CityTransVO cityTransVO){
        return city.addCityTransInfo(cityTransVO);
    }

    /**
     * ����ָ������֮���������ReceiptVO��
     *
     * @param fromYear  ��ʼ���
     * @param fromMonth  ��ʼ�·�
     * @param fromDay  ��ʼ��
     * @param toYear  �������
     * @param toMonth  �����·�
     * @param toDay  ������
     * @return  �������
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
     * ����ָ������֮������и��PaymentVO��
     *
     * @param fromYear  ��ʼ���
     * @param fromMonth  ��ʼ�·�
     * @param fromDay  ��ʼ��
     * @param toYear  �������
     * @param toMonth  �����·�
     * @param toDay  ������
     * @return  �������
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
     * ���ҳɱ������(CostBenefitVO)
     *
     * @return VO������
     */
    public CostBenefitVO searchCostBenefitVO(){
        return statistics.searchCostBenefitVO();
    }

    /**
     * ���ݲ������ƻ�øò�������Ա����ϢStaffVO��
     *
     * @param institution  ��������
     * @return  �������
     */
    public ArrayList<StaffVO> getStaffByInstitution(String institution){
        return staff.getStaffByInstitution(longInstitution(institution));
    }

    /**
     * ����Ա��id���Ա����ϢStaffVO
     *
     * @param id  Ա��id
     * @return  StaffVO������
     */
    public StaffVO getstaffByID(long id){
        return staff.getstaffByID(id);
    }

    /**
     * ��ӵ���Ա��
     *
     * @param staffVO  ��Ա��������Ϣ
     * @return  ��ӳɹ���SUCCESS,���������NOTCONNECTED,Ա��id�Ѿ�������EXIST
     */
    public ResultMessage addStaff(StaffVO staffVO){
        return staff.addStaff(staffVO,staffVO.getId());
    }

    /**
     * ɾ������Ա��
     *
     * @param institution  Ա�����ڻ�������
     * @param ID  Ա����id
     * @return  ɾ���ɹ���SUCCESS,���������NOTCONNECTED,Ա����������NOTEXIST
     */
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

    /**
     * �ƶ�Ա�����ڻ���
     * @param fromInstitution  Ա����ǰ���ڻ�������
     * @param toInstitution  Ա����Ҫ���ƶ����Ĳ�������
     * @param ID  Ա����id
     * @param stringUserRole  Ա�����ƶ������ŵĽ�ɫ
     * @return  �ƶ��ɹ���SUCCESS,���������NOTCONNECTED,������������EXIST
     */
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

    /**
     * �����������Ƹ�Ϊ���
     *
     * @param institution ����������
     * @return  �����ı��
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
     * ����Ա��id���Ա������
     *
     * @param id  Ա��id
     * @return  Ա������
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
     * ��ȡ���г��е���������
     *
     * @return  ���������
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
     * ���ݳ������ֻ�ȡ�ó���������Ӫҵ�����Ʊ�
     *
     * @param city  ��������
     * @return  ��ñ������
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
     * �ж������Ƿ������ֲ����ַ�
     *
     * @param str  ָ���ַ���
     * @return  �Ƿ���int����double�Ͳ���>=0
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
     * �ж����֤�����Ƿ���ȷ
     *
     * @param idCardNum
     * @return  �Ƿ���Ϲ淶
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
     * �жϿ�������Ƿ����
     *
     * @param serviceType
     * @return  �Ƿ���Ϲ淶
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
