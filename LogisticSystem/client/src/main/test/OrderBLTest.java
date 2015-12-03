
import businesslogic.impl.order.OrderBLController;
import businesslogic.service.order.OrderBLService;
import data.message.ResultMessage;
import mock.MockOrderVO;
import org.junit.Test;
import utils.Connection;


public class OrderBLTest {

	@Test
	public void testEnquire() {
        Connection connection = new Connection();
        connection.startConnectionCheck();
		OrderBLService orderBLController = new OrderBLController();
		String results[] = orderBLController.enquire(1234567890);

        assert results[0].equals("2015/11/16 12:00:00： 货物到达 南京市-栖霞区营业厅");
        assert results[1].equals("2015/11/17 13:00:00： 货物到达 南京市—鼓楼区中转中心");
        assert results[2].equals("2015/11/18 13:00:00： 货物到达 北京市-海淀区中转中心");
        assert results[3].equals("2015/12/00 12:34:56： 货物到达 北京市-海淀区营业厅");
        assert results[4].equals("2015/12/12 12:12:12： 货物到达 北京市-海淀区-惠新西街南口 姥姥已签收");

    }

    @Test
    public void testNewOrder() {
        String[] ss = "11345-13456".split("\\-");
        System.out.println(ss[0] + "*" + ss[1]);
        Connection connection = new Connection();
        connection.startConnectionCheck();
        OrderBLController orderBLController = new OrderBLController();
        ResultMessage result = orderBLController.createOrder(new MockOrderVO());
        assert (result == ResultMessage.SUCCESS);
    }

}
