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
	 * ֱ�Ӹ������ݲ����ͷ������ݲ���������
	 *
	 * @param type ϣ����õ����ݷ�������
	 * @return
     */
	public static DataService getDataServiceByType(@NotNull DataType type) {
		Connection.startConnectionCheck();
		try {
			DataService ds = (DataService) Naming.lookup("rmi://127.0.0.1:32000/" + type.name());
			return ds;
		} catch (MalformedURLException e) {
			/* �����ⲻ�ᷢ���� */
			//e.printStackTrace();
		} catch (RemoteException e) {
			// ������������
			System.err.println("Connection error while fetching " + type.name());

			//e.printStackTrace();
		} catch (NotBoundException e) {
			/* �����ⲻ�ᷢ���� */
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ͨ���־û����ݵ������������Ӧ�����ݲ����
	 *
	 * @param type ϣ����õ����ݲ�����¹����ĳ�ֳ־û���������
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
