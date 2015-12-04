package utils;

import data.enums.POType;
import data.po.DataPO;
import data.service.DataService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class IDGenerator {

    protected IDGenerator() { }

    public static long getNextID(POType type) {
        // TODO: µÈ´ýÍê³É
        DataService ds = DataServiceFactory.getDataServiceByPO(type);
        ArrayList<DataPO> pos = null;
        try {
            pos = ds.getPOList(type);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (pos == null) return 0;

        long maxSN = 0;

        for (DataPO po: pos) {
            if (po.getSerialNum() > maxSN) {
                maxSN = po.getSerialNum();
            }
        }

        return 1 + maxSN;
    }


}
