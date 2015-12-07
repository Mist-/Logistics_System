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
     * ϣ���Զ������ڴ����ݵ�ί����
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
     * �����Զ�д���ļ���
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
                System.err.println("���������ر� - " + Calendar.getInstance().getTime());
            }
        }
    }
}
