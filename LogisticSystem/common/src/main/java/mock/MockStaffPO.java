package mock;

import data.po.StaffPO;

public class MockStaffPO extends StaffPO{
	public MockStaffPO(long staffID,long insID){
		serialNum = staffID;
		institution = insID;
	}
}
