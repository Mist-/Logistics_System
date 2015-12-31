package data.service;


import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.POType;
import data.po.DataPO;

public interface StorageDataService extends DataService {

	/**
	 * 根据日期搜索单据。
	 * @param type 指定单据种类
	 * @param date 希望搜索的日期列表
	 * @return 所有满足条件的单据
	 * @throws RemoteException
     */
	ArrayList<DataPO> searchByDate(POType type, ArrayList<String> date) throws RemoteException;

	/**
	 * 根据日期搜索库存信息
	 * @param date 指定的日期
	 * @return 符合条件的库存信息
	 * @throws RemoteException
     */
	DataPO searchStorageInfo(String date) throws RemoteException;

	/**
	 * 按机构号返回刚审批过的订单
	 * @param type 需要搜索的单据类型
	 * @param institution 需要过去的单据的机构编号
	 * @return 所有符合条件的PO
	 * @throws RemoteException
     */
	ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) throws RemoteException;

}

