package data.vo;

import java.util.ArrayList;

public class StoragePositionAndOrderID {
	public ArrayList<String> position;
	public ArrayList<Long> order;
	
	public StoragePositionAndOrderID (ArrayList<String> position,ArrayList<Long> order){
		this.order = order;
		this.position = position;
	}
}
