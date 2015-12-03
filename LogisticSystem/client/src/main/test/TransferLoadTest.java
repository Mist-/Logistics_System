package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import mock.MockInstitutionPO;
import mock.MockStorageInfoPO;

import org.junit.Test;

import businesslogic.impl.transfer.TransferLoad;
import data.vo.StorageShowVO;

public class TransferLoadTest {

	@Test
	public void test() {
		TransferLoad load = new TransferLoad(new MockInstitutionPO(1025001));
		load.setStorage(new MockStorageInfoPO());
		StorageShowVO storageShowVO = load.showCenterInfo(100000);
		ArrayList<Long> ins = storageShowVO.getInstitution();
		int[] amount = storageShowVO.getAmount();
		assertEquals("1025001-3", ins.get(0)+"-"+amount[0]);
		assertEquals("1025002-0", ins.get(1)+"-"+amount[1]);
		assertEquals("1025003-0", ins.get(2)+"-"+amount[2]);
	}
	

}
