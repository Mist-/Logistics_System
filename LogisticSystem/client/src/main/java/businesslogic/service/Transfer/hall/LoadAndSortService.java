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
	
	/**
	 * 总经理审批后确认装车，修改订单物流信息即可
	 * @return 修改结果
	 * @throws RemoteException 
	 */
	public ResultMessage doEntruck() throws RemoteException;
	
	
	public EntruckListVO chooseEntruckList(long id);
	/**
	 * 获得已审批的装车单
	 * @return 简要装车单列表
	 * @throws RemoteException 
	 */
	public BriefEntruckListVO getEntruckList() throws RemoteException;

	
	
	/**
	 * 获得目标机构名称列表，用来设置combobox
	 * @return 目的地名称列表
	 * @throws RemoteException
	 */
	public String[]  getDestination() throws RemoteException;
	
	/**
	 * 根据机构名分拣快递，获取以此机构为目的地的订单列表
	 * @param des 机构名
	 * @return 符合要求的订单信息列表
	 */
	public BriefOrderVO chooseDestination(String des);
	
	/**
	 * 自动生成装车单
	 * @param orders
	 * @return 装车单vo
	 * @throws RemoteException 
	 */
	public EntruckListVO createEntruckList(String[][] orders) throws RemoteException;
	
	/**
	 * 保存装车单
	 * @param entruckList
	 * @return 保存结果
	 * @throws RemoteException 
	 */
	public ResultMessage saveEntruckList(EntruckListVO entruckList) throws RemoteException;
	
}
