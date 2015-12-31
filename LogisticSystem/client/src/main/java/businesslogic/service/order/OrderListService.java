package businesslogic.service.order;

import data.po.OrderPO;
import data.vo.BriefOrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by mist on 2015/12/11 0011.
 */
public interface OrderListService {
	/**
	 * ��ȡ�¶�����Ӫҵ����
	 * @param institution ��ǰӪҵ�����
	 * @param destID Ŀ��ر��
	 * @return �����б�
	 */
	public BriefOrderVO getFreshOrder(long institution,long destID) ;

	/**
	 * �޸Ķ���
	 * @param orderID
	 * @param info
	 * @throws RemoteException
     */
	public void modifyOrder(long[] orderID,String info) throws RemoteException;

	/**
	 * �޸Ķ���λ��
	 * @param orderID
	 * @throws RemoteException
     */
	public void modifyOrderPosition(long[] orderID) throws RemoteException;

	/**
	 * ��������
	 * @param order
	 * @return
     */
    ArrayList<OrderPO> search(long[] order);

    /**
	 * ��ȡ�����ͱ���
	 * @param hallID
	 * @param date
	 * @return
     */
    public String[][] getOrderAndFee(long hallID,String date);
}
