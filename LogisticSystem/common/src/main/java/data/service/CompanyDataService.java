package data.service;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.CityInfoPO;
import data.po.CityTransPO;
import data.po.DataPO;
import data.po.SalaryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ��˾������ݽӿڡ�
 *
 * @author Mouse
 */
public interface CompanyDataService extends DataService {

    DataPO searchCity(String cityName) throws RemoteException;
    //�ɲ���Ϊ�ؼ��ֲ�ѯ�˲��Ź�������
    SalaryPO searchByInstitution(String department) throws RemoteException;
    //��ʼĩ����Ϊ�ؼ��ֲ�ѯ����֮����������Ϣ����
    CityTransPO searchByCityName(String fromCity, String toCity) throws RemoteException;
    
    long searchBusinessOffice(String boName) throws RemoteException;
}
