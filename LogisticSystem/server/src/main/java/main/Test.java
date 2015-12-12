package main;

import data.enums.POType;
import data.po.DataPO;
import data.po.EntruckPO;
import data.service.DataService;
import utils.DataServiceFactory;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        DataService ds = DataServiceFactory.getDataServiceByPO(POType.ENTRUCK);

        try {
            for (DataPO dataPO: ds.getUnapprovedPO(POType.ENTRUCK)) {
                System.out.println(dataPO.getSerialNum());
                ds.approveOf(dataPO);
            }

            for (DataPO dataPO: ds.getNewlyApproved()) {
                System.out.println(dataPO.getSerialNum());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
