package main;

import data.enums.DataType;
import data.enums.POType;
import data.po.DataPO;
import data.service.DataService;
import utils.DataServiceFactory;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        DataService ds = DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        try {
            for (DataPO data: ds.getPOList(POType.ORDER)) {
                System.out.println(data.getSerialNum());
                System.out.println(data.getState());
                ds.approveOf(data);
                System.out.println(data.getState());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
