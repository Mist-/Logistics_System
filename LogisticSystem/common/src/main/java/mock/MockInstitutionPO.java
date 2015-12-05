package mock;

import java.util.ArrayList;

import data.po.InstitutionPO;

public class MockInstitutionPO extends InstitutionPO {
	public MockInstitutionPO(long Institution){
		super(Institution);
		hall = new ArrayList<Long>();
		serialNum = Institution;
		hall.add(1025001L);
		hall.add(1025002L);
		hall.add(1025003L);
	}
	
	public ArrayList<Long> getInstitution() {
		return hall;
	}
	

	

	ArrayList<Long> hall;
}
