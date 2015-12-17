package data.vo;

import data.po.VehicleInfoPO;

public class TruckInfoVO {
	   public long serialNum;
	   public String ID;
	   public String license;
	    // Format: yyyy/mm/dd
	    public String dutyDate;
	    public String year;
	    public String month;
	    public String day;
	    public String engaged;
	    
	    public TruckInfoVO(){
	    	
	    }
	    
	public TruckInfoVO(VehicleInfoPO po){
		serialNum = po.getSerialNum();
		ID = po.getSerialNum()+"";
		license = po.getLicense();
		dutyDate = po.getDutyDate();
		engaged = po.getEngagedString();
		String[] date = dutyDate.split("/");
		year = date[0];
		month = date[1];
		day = date[2];
	}
}
