package data.vo;

import data.po.VehicleInfoPO;

public class TruckListVO {
	public String[] header = {"�������","״̬"};
	public String[][] info;
	public TruckListVO(String[][] info) {
		this.info = info;
	}
}
