package mock;

import java.nio.file.StandardCopyOption;

import data.enums.StorageArea;
import data.po.OrderPO;

public class MockOrderPO extends OrderPO {
	
	public long getSender(){
		return 10001;
	}
	public MockOrderPO(long num) {
		this.serialNum = num;
		this.setFee(10);
	}
	
	public long getInsititution(){
		return 1025001;
	}

	@Override
	public StorageArea getTransferType() {
		// TODO Auto-generated method stub
		return StorageArea.PLANE;
	}

	@Override
	public long getDestID() {
		// TODO Auto-generated method stub
		return 1025001L;
	}


	
	

}
