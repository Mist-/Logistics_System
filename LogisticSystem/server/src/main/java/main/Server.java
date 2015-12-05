package main;

import data.enums.DataType;
import data.factory.DataImplFactory;
import data.service.DataService;
import utils.FileSaver;
import utils.SayHelloImpl;
import utils.SayHelloService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;

/**
 * ���������߳�
 *
 * Created by mist on 2015/11/12 0012.
 */
public class Server {

    static SayHelloService hello = null;

    public static void main(String[] args) {

        FileSaver fileSaver = new FileSaver();

        // ���������ݲ�ʵ��
        try {
            Registry reg = LocateRegistry.createRegistry(32000);
            for (DataType type: DataType.values()) {
                DataService tmp = DataImplFactory.getDataImpl(type);
                fileSaver.addDataService(tmp);
                reg.rebind(type.name(), tmp);
            }

            hello = new SayHelloImpl();
            reg.rebind("hello", hello);

            System.out.println("��������ʼ���ɹ� - " + Calendar.getInstance().getTime());
        } catch (RemoteException e) {
            System.err.println("��������ʼ��ʧ�� - " + Calendar.getInstance().getTime());
            e.printStackTrace();
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ����һ���Զ�������߳�
        Thread fileAutoSaver = new Thread(fileSaver);
        fileAutoSaver.start();

    }
}
