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
            for (int i = 0; i < ds.getUnapprovedPO(POType.ENTRUCK).size(); ++i) {
                System.out.println(ds.getPOList(POType.ENTRUCK).get(i).getSerialNum());
                System.out.println(ds.getPOList(POType.ENTRUCK).get(i).getState());
                ds.approveOf(ds.getPOList(POType.ENTRUCK).get(i));
                System.out.println(ds.getPOList(POType.ENTRUCK).get(i).getState());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
