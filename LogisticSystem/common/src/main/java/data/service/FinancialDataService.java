package data.service;

import data.message.ResultMessage;
import data.po.DataPO;
import data.vo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * �������ݽӿ�
 */

public interface FinancialDataService extends DataService {

	ArrayList<DataPO> search(String name) throws RemoteException;
	
	ArrayList<DataPO> searchReceipt(String begin, String end) throws RemoteException;

	ArrayList<DataPO> searchRecFromAddress(String info) throws RemoteException;

	ArrayList<DataPO> searchRecFromDate(String info) throws RemoteException;

	ResultMessage modifyAccount(AccountVO accountVO) throws RemoteException;

	ResultMessage delete(long num) throws RemoteException;
	
}
