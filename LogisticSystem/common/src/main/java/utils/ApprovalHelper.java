package utils;

import data.po.DataPO;
import data.service.DataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * Created by mist on 2015/11/25 0025.
 */
public class ApprovalHelper extends Thread {
    ArrayList<DataPO> toBeAdded = null;
    public ApprovalHelper() {
        toBeAdded = new ArrayList<>();
    }

    class ApprovalSeekingRunnable implements Runnable {
        @Override
        public void run() {
            while (!toBeAdded.isEmpty()) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (DataPO data : toBeAdded) {
                    DataService ds = DataServiceFactory.getDataServiceByPO(data.getPOType());
                    try {
                        ds.search(data.getPOType(), data.getSerialNum());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

