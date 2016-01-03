package main;

import data.enums.DataType;
import data.enums.POType;
import data.po.DataPO;
import data.service.DataService;
import data.service.OrderDataService;
import utils.DataServiceFactory;

import java.rmi.RemoteException;

/**
 * Created by mist on 2015/12/17 0017.
 */
public class Test {
    public static void main(String[] args) {
        OrderDataService ds = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        try {
            for (DataPO data: ds.searchByLoc("* 上海市-青浦区-1234")) {
                System.out.println(data.getSerialNum());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
