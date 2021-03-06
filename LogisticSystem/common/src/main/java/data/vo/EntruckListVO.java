package data.vo;

import data.po.EntruckPO;

import java.util.ArrayList;

/**
 * 
 * @author xu
 *
 */
public class EntruckListVO extends DeliveryListVO {
	public long escortID;
	public String[][] info;
	public String[] header = {"订单编号","重量（kg）"};
    public String loadingDate;
    public String fromName;
    public String fromID;
    public String driverID;
    public String driverName;
    public String entruckListID;//装车单编号

	public String destID;
    public String vehicleID;  // 车辆编号
         
    
    public String 	monitorName,
    		escortName,
    		destName;
    
    public String fee;
	
    public EntruckListVO(){
    	
    }
	public EntruckListVO(EntruckPO po){
		entruckListID = po.getSerialNum()+"";
		fromID = po.getFromID()+"";
		fromName = po.getFromName();
		destID = po.getDestID()+"";
		escortID = po.getEscortID();
		loadingDate = po.getLoadingDate();
		vehicleID = po.getVehicleID()+"";
		monitorName = po.getMonitorName();
		escortName = po.getEscortName();
		destName = po.getDestName();
		ArrayList<Long> order = po.getOrderList();
		info = new String[order.size()][2];
		for(int i = 0 ;i < order.size();i++){
			String[] in = {order.get(i)+"",""};
			info[i] = in;
 		}
	}

	//  wyc
	public long getEntruckListID() {
		return Long.valueOf(entruckListID);
	}

	public void setEntruckListID(String entruckListID) {
		this.entruckListID = entruckListID;
	}
}
