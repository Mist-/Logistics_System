package data.vo;

import data.po.DriverInfoPO;

/**
 * 
 * @author xu
 *
 */
public class DriverInfoVO {
	String driverID;
    String IDCard;
    String name;
    String engaged;
    String gender /* true = female, false = male */;
    String phoneNum;
    String bornDate /* yyyy/mm/dd */;
    String timeLimit /* yyyy/mm/dd */;
	public DriverInfoVO(DriverInfoPO po){
		IDCard = po.getIDCard()+"";
		name = po.getName();
		engaged = po.getEngagedString();
		gender = po.getGenderString();
		phoneNum = po.getPhoneNum();
		bornDate = po.getBornDate();
		timeLimit = po.getTimeLimit();
		driverID = po.getSerialNum()+"";
	}
}
