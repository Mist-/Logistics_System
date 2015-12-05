package data.po;

/**
 * 装车单
 *
 * @author Mouse
 */

import data.enums.DataState;
import data.enums.POType;

import java.util.ArrayList;

public class EntruckPO extends DataPO{


	
	public static final int TRANS = 1, DESTINATION = 2, MONITOR = 3, ESCORT = 4;

	
	long entruckListID;

	ArrayList<Long> orderList ;
	// 装车日期 格式 yyyy/mm/dd
    String loadingDate;
    long staff;//监装员
    String staffName;

    long    transID,    // 运转单编号
            destID,     // 目标单位的编号
            vehicleID,  // 车辆编号
            escortID,   // 押运员ID//司机
    		from;		//出发地ID
    
    String 	monitorName,//监装员 同staff
    		escortName,//押运员 （司机）
    		destName;
    
    double fee;
    boolean iscounted;
    
    public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getEscortName() {
		return escortName;
	}

	public void setEscortName(String escortName) {
		this.escortName = escortName;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public double getFee() {
		return fee;
	}


	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(String loadingDate) {
		this.loadingDate = loadingDate;
	}


    public long getEntruckListID() {
		return entruckListID;
	}

	public void setEntruckListID(long entruckListID) {
		this.entruckListID = entruckListID;
	}

    public EntruckPO() {
        super(POType.ENTRUCK);
        super.setState(DataState.APPROVING);
        iscounted = false;
    }

    public boolean isIscounted() {
		return iscounted;
	}

	public void setIscounted(boolean iscounted) {
		this.iscounted = iscounted;
	}

	public long getID(int type) {
        switch (type) {
            case TRANS:
                return transID;
            case DESTINATION:
                return destID;
            case MONITOR:
                return staff;
            case ESCORT:
                return escortID;
            default:
                return 0;
        }
    }

    public void setID(int type, long ID) {
        switch (type) {
            case TRANS:
                transID = ID;
            case DESTINATION:
                destID = ID;
            case MONITOR:
                staff = ID;
            case ESCORT:
                escortID = ID;
            default:
        }
    }

    public long getTransID() {
        return transID;
    }

    public void setTransID(long transID) {
        this.transID = transID;
    }

    public long getDestID() {
        return destID;
    }

    public void setDestID(long destID) {
        this.destID = destID;
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public long getMonitorID() {
        return staff;
    }

    public void setMonitorID(long monitorID) {
        this.staff = monitorID;
    }

    public long getEscortID() {
        return escortID;
    }

    public void setEscortID(long escortID) {
        this.escortID = escortID;
    }

    public ArrayList<Long> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(ArrayList<Long> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(long orderID) {
        this.orderList.add(orderID);
    }
    
    public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

    public long[] getOrder(){
        long[] result = new long[orderList.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = orderList.get(i);
        }
        return result;
    }
}
