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

	/**
	 * 根据名称搜索银行账户信息
	 * @param name 银行账户名称
	 * @return 如果存在则返回银行账户信息，否则返回长度为0的ArrayList
	 * @throws RemoteException
     */
	ArrayList<DataPO> search(String name) throws RemoteException;

    /**
     * 根据指定的日期时间段，返回收款单信息
     * @param begin 开始日期
     * @param end 结束日期
     * @return 所有符合条件的PO组成的List
     * @throws RemoteException
     */
	ArrayList<DataPO> searchReceipt(String begin, String end) throws RemoteException;

    /**
     * 根据地址搜索付款单
     * @param info 地址信息
     * @return 所有符合条件的PO
     * @throws RemoteException
     */
	ArrayList<DataPO> searchRecFromAddress(String info) throws RemoteException;

    /**
     * 根据日期搜索付款单
     * @param info 日期信息
     * @return 返回所有符合条件的PO
     * @throws RemoteException
     */
	ArrayList<DataPO> searchRecFromDate(String info) throws RemoteException;

    /**
     * 修改银行账户信息
     * @param accountVO 需要修改为的银行账户信息
     * @return SUCCESS表示修改成功。NOT EXIST表示修改失败。FAILED表示其他原因修改失败
     * @throws RemoteException
     */
	ResultMessage modifyAccount(AccountVO accountVO) throws RemoteException;

    /**
     * 根据编号删除银行账户
     * @param num 需要删除的银行账户编号
     * @return SUCCESS表示删除成功。FAILED表示删除失败。NOT_EXIST表示不存在该单据。
     * @throws RemoteException
     */
	ResultMessage delete(long num) throws RemoteException;
}
