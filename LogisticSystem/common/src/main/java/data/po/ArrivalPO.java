package data.po;
import data.enums.DataState;
import data.enums.POType;
import data.enums.StockStatus;

import java.util.ArrayList;

/**
 * 
 * @author xu
 *
 */
public class ArrivalPO extends DataPO {

	private static final long serialVersionUID = 2;





	//到达单号
	//中转中心编号
	//订单号
	//订单状态
	//日期：yyyy-mm-dd
	//出发机构编号
    long destID;
    String date;
    String destName;
	ArrayList<Long> order;
    ArrayList<StockStatus> stockStatus;
    long from;//ID
    String fromName;
    long deliveryListID;
    
    
	
	public long getDeliveryListID() {
		return deliveryListID;
	}

	public void setDeliveryListID(long deliveryListID) {
		this.deliveryListID = deliveryListID;
	}

	public ArrivalPO(TransferListPO transfer){
		super(POType.ARRIVAL);
		destID = transfer.getSerialNum();
		order = transfer.getOrderArray();
		stockStatus = new ArrayList<StockStatus>();
		for(int i = 0 ; i < order.size();i++){
			stockStatus.add(StockStatus.ROUND);
		}
		from = transfer.getTransferCenter();
		isOperated = false;
	
		//date
	}
	
	public void setOperated(boolean isOperated) {
		this.isOperated = isOperated;
	}
	
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}


	public ArrivalPO(EntruckPO entruck){
		super(POType.ARRIVAL);
		destID = entruck.getDestID();
		order = entruck.getOrderList();
		stockStatus = new ArrayList<StockStatus>();
		for(int i = 0 ; i < order.size();i++){
			stockStatus.add(StockStatus.ROUND);
		}
		from = entruck.getFromID();
		isOperated = false;

		//date
	}



	public boolean isOperated() {
		return isOperated;
	}


    boolean isOperated = false;//是否入库（仓库），是否修改订单信息（营业厅，中转中心）

    public ArrivalPO() {
    	
        super(POType.ARRIVAL);
    }
    
    
    
    public long getDestID() {
		return destID;
	}

	public void setDestID(long destID) {
		this.destID = destID;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}


    public long getTransferList() {
        return destID;
    }

    public void setTransferList(long transferList) {
        this.destID = transferList;
    }



    public long[] getOrder(StockStatus status) {
    	ArrayList<Long> o = new ArrayList<Long>();
    	for (int i = 0; i < order.size(); ++i) {
    		if (stockStatus.get(i) == status) {
    			o.add(order.get(i));
			}
    	}
    	long[] result = new long[o.size()];
    	for (int i = 0; i < o.size(); i++) {
			result[i] = o.get(i);
		}
        return result;
    }
    
    public long[] getOrder(){
    	long[] allOrders = new long[order.size()];
    	for(int i = 0 ; i <allOrders.length;i++){
    		allOrders[i] = order.get(i);
    	}
    	
    	return allOrders;
    }
    
    
    
 

    public void setOrder(ArrayList<Long> order) {
        this.order = order;
    }


    public ArrayList<StockStatus> getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(ArrayList<StockStatus> stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void addStckStatus(StockStatus stockStatus) {
        this.stockStatus.add(stockStatus);
    }

    public StockStatus getStockStatus(long code) {
        StockStatus state = StockStatus.ROUND;
        return state;
    }

    public void setStockStatus(long code, StockStatus state) {

    }

    public String getDate() {
        return date;
    }


    
    public long getFrom() {
  		return from;
  	}

  	public void setFrom(long from) {
  		this.from = from;
  	}

  	public void setDate(String date) {
  		this.date = date;
  	}

	public void addOrder(long order) {
		this.order.add(order);
	}


}
