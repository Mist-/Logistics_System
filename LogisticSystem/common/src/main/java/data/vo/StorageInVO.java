package data.vo;

import data.po.StorageInListPO;

import java.util.ArrayList;

/**
 * not complete
 * @author xu
 *
 */
public class StorageInVO {
	
	
	String[] header = {"订单号","区域","排","架","位"};
	String date = null;
	String[][] info ;
	public long id;
	public long centerID;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[][] getInfo() {
		return info;
	}

	public void setInfo(String[][] info) {
		this.info = info;
	}

	public StorageInVO(String[][] info){
		this.info = info;
	}
	
	public StorageInVO(StorageInListPO po){
		date = po.getDate();
		ArrayList<Long> order = po.getOrder();
		ArrayList<String> position = po.getStoragePosition();
		
		String[][] info = new String[order.size()][5];
		for(int i = 0 ;i < order.size();i++){
			String[] pos = position.get(i).split("-");
			switch (pos[0]) {
			case "0":pos[0] = "航运区";break;
			case "1":pos[0] = "铁运区";break;
			case "2":pos[0] = "汽运区";break;
			default:pos[0] = "机动区";break;
			}
			
			String[] in = {order.get(i)+"",pos[0],pos[1],pos[2],pos[3]};
			info[i] = in;
		}
		this.id = po.getSerialNum();
		this.info = info;
		
	}
	
	public String[] getHeader() {
		return header;
	}
	public StorageInVO(String[][] info, String date){
		this.info = info;
		this.date = date;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
