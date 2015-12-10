package main;

import data.impl.OrderDataSerializableImpl;
import data.po.DataPO;
import data.service.OrderDataService;

import java.rmi.RemoteException;

/**
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        try {
            OrderDataService orderDataService = new OrderDataSerializableImpl();
            for (DataPO data: orderDataService.searchByLoc("南京市 上海市")) {
                System.out.println(data.getSerialNum());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
