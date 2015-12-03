package businesslogic.service.Transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.message.ResultMessage;
import data.vo.BriefEntruckListVO;
import data.vo.BriefOrderVO;
import data.vo.EntruckListVO;

/**
 * 快递分拣和装车
 * @author xu
 *
 */
public interface LoadAndSortService {
	
	
	public ResultMessage doEntruck();
	
	
	public EntruckListVO chooseEntruckList(long id);
	/**
	 * 获得已审批的装车单
	 * @return
	 * @throws RemoteException 
	 */
	public BriefEntruckListVO getEntruckList() throws RemoteException;

	
	
	/**
	 * 获得目标机构名称列表，用来设置combobox
	 * @return
	 * @throws RemoteException
	 */
	public String[]  getDestination() throws RemoteException;
	
	/**
	 * 根据机构名分拣快递，获取以此机构为目的地的订单列表
	 * @param des 机构名
	 * @return
	 */
	public BriefOrderVO chooseDestination(String des);
	
	/**
	 * 自动生成装车单
	 * @param orders
	 * @return
	 */
	public EntruckListVO createEntruckList(String[][] orders);
	
	/**
	 * 保存装车单
	 * @param entruckList
	 * @return
	 * @throws RemoteException 
	 */
	public ResultMessage saveEntruckList(EntruckListVO entruckList) throws RemoteException;
	
}
