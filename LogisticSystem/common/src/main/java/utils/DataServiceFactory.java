package utils;

import com.sun.istack.internal.NotNull;
import data.enums.DataType;
import data.enums.POType;
import data.service.DataService;
import utils.Connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/11/12 0012.
 */
public class DataServiceFactory {

	/**
	 * 直接根据数据层类型返回数据层对象的引用
	 *
	 * @param type 希望获得的数据服务类型
	 * @return
     */
	public static DataService getDataServiceByType(@NotNull DataType type) {
		Connection.startConnectionCheck();
		try {
			DataService ds = (DataService) Naming.lookup("rmi://127.0.0.1:32000/" + type.name());
			return ds;
		} catch (MalformedURLException e) {
			/* 假设这不会发生！ */
			//e.printStackTrace();
		} catch (RemoteException e) {
			// 网络连接问题
			System.err.println("Connection error while fetching " + type.name());

			//e.printStackTrace();
		} catch (NotBoundException e) {
			/* 假设这不会发生！ */
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过持久化数据的类型来获得相应的数据层服务
	 *
	 * @param type 希望获得的数据层服务下管理的某种持久化数据类型
	 * @return
     */
	public static DataService getDataServiceByPO(@NotNull POType type) {
		switch (type) {
			case SALARY:
			case STAFF:
			case INSTITUTION:
			case CITYINFO:
			case CITYTRANS:
				return getDataServiceByType(DataType.CompanyDataService);
			case ACCOUNT:
			case COSTBENEFIT:
			case PAYMENT:
			case RECEIPT:
				return getDataServiceByType(DataType.FinancialDataService);
			case ORDER:
			case LOGISTICINFO:
			case SIGN:
				return getDataServiceByType(DataType.OrderDataService);
			case STORAGEINFO:
			case STORAGEINLIST:
			case STORAGEOUTLIST:
				return getDataServiceByType(DataType.StorageDataService);
			case ARRIVAL:
			case ENTRUCK:
			case SEND:
			case TRANSFERLIST:
				return getDataServiceByType(DataType.TransferDataService);
			case USER:
				return getDataServiceByType(DataType.UserDataService);
		}
		return null;
	}
}
