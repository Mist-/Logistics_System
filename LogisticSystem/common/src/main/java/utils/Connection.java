package utils;

import data.Configuration;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * 检查连接状态
 * Created by mist on 2015/11/13 0013.
 */
public class Connection {

    static Thread connectionCheckThread = null;
    public static boolean connected = false;

    public static String getRmiPrefix() {
        return "rmi://" + Configuration.getInstance().ip + ":" + Configuration.getInstance().regPort + "/";
    }

    public static void checkConnection() {
        try {
            SayHelloService hello = (SayHelloService) Naming.lookup(getRmiPrefix() + "hello");
            connected = true;
            return;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            System.err.println("连接到服务器失败 - " + Calendar.getInstance().getTime());
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

        // 每秒钟检查连接状态
        while (true) {
            Connection.checkConnection();
            if (!Connection.connected) {
            	System.err.println("与服务器的连接断开 - " + Calendar.getInstance().getTime());

                // 60秒后重新尝试
                ++tries;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                }

                // 尝试3次后，5分钟后尝试
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