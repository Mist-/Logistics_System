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
        if (ds == null) {
            return new Random().nextInt(100) + 1000000000000L;
        }
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

        if (maxSN == 0) {
            switch (type) {
                case ORDER:
                    return 1000000001;
                case STAFF:
                    return 10001;
            }
        }
        return 1 + maxSN;
    }


}
