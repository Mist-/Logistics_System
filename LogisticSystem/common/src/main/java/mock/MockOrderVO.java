package mock;

import data.enums.ServiceType;
import data.vo.OrderVO;

/**
 *
 * Created by mist on 2015/11/16 0016.
 */
public class MockOrderVO extends OrderVO {
    public MockOrderVO() {
        super("���", "�Ͼ���-��ǺǺ�", "��¥��-��Ǻ�", "as-�Ǻ�", "adasd-123", "�Ϻ���-ehehe", "4123-431243", "1234-124", 1, 2, 3, new String[1], ServiceType.FAST);
    }
}
