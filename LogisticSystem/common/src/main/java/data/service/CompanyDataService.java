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
 * 公司相关数据接口。
 *
 * @author Mouse
 */
public interface CompanyDataService extends DataService {

    DataPO searchCity(String cityName) throws RemoteException;
    //由部门为关键字查询此部门工资详情
    SalaryPO searchByInstitution(String department) throws RemoteException;
    //由始末城市为关键字查询城市之间物流的信息详情
    CityTransPO searchByCityName(String fromCity, String toCity) throws RemoteException;
    
    long searchBusinessOffice(String boName) throws RemoteException;
}
