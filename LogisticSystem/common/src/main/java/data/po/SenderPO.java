package data.po;

import data.enums.POType;

public class SenderPO extends DataPO{

	public String name;
	public long phoneNum;
	public long id;
	
	public SenderPO() {
		super(POType.SENDER);
	}

	private static final long serialVersionUID = 14;


	
	
}
