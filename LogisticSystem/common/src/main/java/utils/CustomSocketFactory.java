package utils;

import data.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * 自定义Socket工厂
 *
 * Created by mist on 2015/12/13 0013.
 */
public class CustomSocketFactory extends RMISocketFactory {

    int port = Configuration.getInstance().dataTransPort;

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0)
            port = this.port;
        return new ServerSocket(port);
    }
}
