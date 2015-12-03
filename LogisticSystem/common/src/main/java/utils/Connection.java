package utils;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * �������״̬
 * Created by mist on 2015/11/13 0013.
 */
public class Connection {

    static Thread connectionCheckThread = null;
    public static String RMI_PREFIX = "rmi://127.0.0.1:32000/";
    public static boolean connected = false;

    public static void checkConnection() {
        try {
            SayHelloService hello = (SayHelloService) Naming.lookup(RMI_PREFIX + "hello");
            connected = true;
            return;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            System.err.println("���ӵ�������ʧ�� - " + Calendar.getInstance().getTime());
        }
        connected = false;
    }

    public static void startConnectionCheck() {
    	checkConnection();
        if (connectionCheckThread != null) connectionCheckThread.interrupt();
        connectionCheckThread = new Thread(new CheckConnectionRunnable());
        connectionCheckThread.start();
    }
}

class CheckConnectionRunnable implements Runnable {

    public void run() {

        int tries = 0;

        // ÿ���Ӽ������״̬
        while (true) {
            Connection.checkConnection();
            if (!Connection.connected) {
            	System.err.println("������������ӶϿ� -" + Calendar.getInstance().getTime());

                // 60������³���
                ++tries;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                }

                // ����3�κ�5���Ӻ���
                if (tries == 3) {
                    tries = 0;
                    try {
                        Thread.sleep(300000);
                    } catch (InterruptedException e) {
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}