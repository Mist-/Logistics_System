package mock;

import data.enums.ServiceType;
import data.vo.OrderVO;

/**
 *
 * Created by mist on 2015/11/16 0016.
 */
public class MockOrderVO extends OrderVO {

    public MockOrderVO(String sname, String saddress, String scompany, String sphone, String rname, String raddress, String rcompany, String rphone, int stockNum, double weight, double volume, String[] stockType, ServiceType type, double fee, long id) {
        super(sname, saddress, scompany, sphone, rname, raddress, rcompany, rphone, stockNum, weight, volume, stockType, type, fee, id);
    }
}
