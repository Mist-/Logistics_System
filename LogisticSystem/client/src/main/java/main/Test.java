package main;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.po.DataPO;
import data.po.DriverInfoPO;
import data.service.TransferDataService;

public class Test {
	
	public static void main(String[] args) throws RemoteException {
		TransferDataService t = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		ArrayList<DataPO> d = t.getPOList(POType.DRIVERINFO);
		for(int i = 0; i < d.size();i++){
			System.out.println(d.get(i).getSerialNum());
		}
		
		DriverInfoPO dd = new DriverInfoPO();
		System.out.println(dd.getSerialNum());
	}


}
