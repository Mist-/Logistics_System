package main;

import data.enums.POType;
import data.po.DataPO;
import data.po.EntruckPO;
import data.po.OrderPO;
import data.service.DataService;
import utils.DataServiceFactory;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        DataService ds = DataServiceFactory.getDataServiceByPO(POType.ORDER);
        try {
            ds.add(new OrderPO());
            ds.add(new OrderPO());
            ds.add(new OrderPO());
            ds.add(new OrderPO());
            ds.add(new OrderPO());
            ds.add(new OrderPO());
            for (DataPO data: ds.getUnapprovedPO(POType.ORDER)) {
                System.out.println(data.getSerialNum());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
