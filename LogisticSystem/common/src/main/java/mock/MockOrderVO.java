package mock;

import data.enums.ServiceType;
import data.vo.OrderVO;

/**
 *
 * Created by mist on 2015/11/16 0016.
 */
public class MockOrderVO extends OrderVO {
    public MockOrderVO() {
        super("孙浩", "南京市-额呵呵呵", "鼓楼区-额呵呵", "as-呵呵", "adasd-123", "上海市-ehehe", "4123-431243", "1234-124", 1, 2, 3, new String[1], ServiceType.FAST);
    }
}
