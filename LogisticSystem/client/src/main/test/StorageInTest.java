import static org.junit.Assert.*;
import java.util.ArrayList;
import mock.MockArrivalPO;
import mock.MockStorageInfoPO;
import org.junit.Test;
import data.po.ArrivalPO;
import data.vo.ArriveListVO;
import data.vo.StorageInVO;
import businesslogic.impl.storage.StorageIn;

public class StorageInTest {

	@Test
	public void test1() {
		//获取一个到达单信息
		StorageIn storageIn;
		ArrivalPO a1 = new MockArrivalPO(100001);
		ArrivalPO a2 = new MockArrivalPO(100002);
		ArrayList<ArrivalPO> arrivals = new ArrayList<ArrivalPO>();
		arrivals.add(a1);
		arrivals.add(a2);
		
	}
	
	@Test
	public void test2() {
		//对到达单上的两个订单进行入库操作
		StorageIn storageIn = new StorageIn();
		storageIn.setStorageInfo(new MockStorageInfoPO());
		storageIn.setOrderBLService(new MockOrderBLService());
		ArrivalPO a = new MockArrivalPO(1000001);
		storageIn.setArrival(a);
		StorageInVO storageInVO = storageIn.sort(100000);
		ArrayList<String> postion = storageInVO.getPosition();
		assertEquals(postion.get(0), "0-0-0-3");
		assertEquals(postion.get(1), "0-0-0-4");
	}
	
	
	

}
