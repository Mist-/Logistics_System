package data.vo;

import data.po.VehicleInfoPO;

public class TruckInfoVO {
	   String ID;
	   String license;
	    // Format: yyyy/mm/dd
	    String dutyDate;
	    String engaged;
	    
	    
	public TruckInfoVO(VehicleInfoPO po){
		ID = po.getSerialNum()+"";
		license = po.getLicense();
		dutyDate = po.getDutyDate();
		engaged = po.getEngagedString();
	}
}
