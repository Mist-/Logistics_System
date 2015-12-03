package utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SayHelloImpl extends UnicastRemoteObject implements SayHelloService{

	public SayHelloImpl() throws RemoteException {
		super();
	}

}
