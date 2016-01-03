package utils;

import data.enums.POType;
import data.po.DataPO;
import data.service.DataService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class IDGenerator {

    static HashMap<POType, Long> tmpMaxSN = new HashMap<>();

    protected IDGenerator() { }

    public static long getNextID(POType type) {

        // TODO: µÈ´ýÍê³É
        DataService ds = DataServiceFactory.getDataServiceByPO(type);
        if (ds == null) {
            return new Random().nextInt(10000) + 1000000000000L;
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
                    maxSN = 1000000000;
                    break;
                case STAFF:
                    maxSN = 10000;
                    break;
            }
        }
        if (tmpMaxSN.containsKey(type) && tmpMaxSN.get(type) > maxSN) {
            maxSN = tmpMaxSN.get(type);
        }
        tmpMaxSN.remove(type);
        tmpMaxSN.put(type, maxSN + 1);
        return 1 + maxSN;
    }
}
