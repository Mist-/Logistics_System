package utils;

import data.service.DataService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * Created by mist on 2015/12/1 0001.
 */
public class FileSaver implements Runnable {

    /**
     * 希望自动保存内存数据的委托者
     */
    private ArrayList<DataService> dataServices = null;

    public FileSaver() {
        this.dataServices = new ArrayList<>();
    }

    public FileSaver(DataService dataService) {
        this.dataServices = new ArrayList<>();
        dataServices.add(dataService);
    }

    public FileSaver(DataService[] dataServices) {
        this.dataServices = new ArrayList<>();
        Collections.addAll(this.dataServices, dataServices);
    }

    public void addDataService(DataService dataService) {
        this.dataServices.add(dataService);
    }

    /**
     * 持续自动写入文件。
     */
    @Override
    public void run() {
        while (true) {
            for (DataService ds : dataServices) {
                try {
                    ds.finish();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("服务器被关闭 - " + Calendar.getInstance().getTime());
            }
        }
    }
}
