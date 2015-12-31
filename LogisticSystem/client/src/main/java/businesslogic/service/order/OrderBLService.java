package businesslogic.service.order;

import java.util.ArrayList;

import data.message.ResultMessage;
import data.po.OrderPO;
import data.vo.OrderVO;

/**
 *
 * Created by Mouse on 2015/10/23 0023.
 */
public interface OrderBLService {

    /**
     * ���ݶ�����Ϣ��������
     * @param order ��Ҫ����Ķ�����Ϣ
     * @return SUCCESS��ʾ����ɹ���FALIED��ʾ����������ȣ�����ʧ��
     */
    ResultMessage createOrder(OrderVO order);

    /**
     * ���ݶ����ţ�����������Ϣ
     * @param sn ��Ҫ����������Ϣ�Ķ�����
     * @return ������Ϣ�ַ������顣ÿһ��Ԫ�ذ���һ��������Ϣ������ʱ�����������
     */
    String[] enquire(long sn);

    /**
     * ���ݶ�����ϢԤ���ʹ�ʱ��
     * @param orderVO ��ҪԤ���Ķ�����Ϣ
     * @return Ԥ����ʱ�䡣����������롣���û����ʷ����
     *          ������������ʧ���򷵻�0
     */
    int evaluateTime(OrderVO orderVO);

    /**
     * ���ݶ�����Ϣ���ɱ���
     * @param orderVO ��Ҫ���ɱ��۵Ķ�����Ϣ
     * @return ���ۡ���������ڱ��������򷵻�0
     */
    double generateFee(OrderVO orderVO);

    /**
     * ����ջ������������Ӫҵ�����ﵥ
     * @param sn ��Ҫ����ջ������Ķ�����
     * @return SUCCESS��ʾ�ջ��ɹ���FAILED��ʾ�ջ�ʧ�ܡ�NOTEXIST��ʾ���������������
     */
    ResultMessage receive(long sn);

    /**
     * ���װ������
     * @param orderSN ��Ҫװ���Ķ�����
     * @param destID ����װ����Ŀ�ĵ�
     * @return װ���Ƿ�ɹ�
     */
    ResultMessage entruck(long orderSN, long destID);

    /**
     * ǩ�ն�����Ϊ�����������ǩ����Ϣ��
     * @param sn �������
     * @param name ǩ����������
     * @param phone ǩ���˵ĵ绰
     * @return ǩ���Ƿ�ɹ�
     */
    ResultMessage sign(long sn, String name, long phone);

    /**
     * ������еĶ�������ʾ����
     * @return
     */
    ArrayList<OrderVO> getDisplayData();
}
