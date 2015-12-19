package main;

import data.enums.DataType;
import data.enums.POType;
import data.po.DataPO;
import data.po.OrderPO;
import data.po.StaffPO;
import data.service.DataService;
import utils.DataServiceFactory;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/18 0018.
 */
public class Test {
    public static void main(String[] args) {
        DataService orderds = DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        DataService companyds = DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        try {
            // ��ӡ���ж�����·��
            for (DataPO data: orderds.getPOList(POType.ORDER)) {
                System.out.println("");
                for (long sn: ((OrderPO) data).getRoutine()) {
                    System.out.println(sn);
                }
            }

            // ��ӡ����Ա�������Ļ�����
            for (DataPO data: companyds.getPOList(POType.STAFF)) {
                System.out.println(data.getSerialNum() + " - " + ((StaffPO) data).getInstitution());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
