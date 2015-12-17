package data.po;

import java.util.ArrayList;

import data.enums.POType;
import data.enums.StockStatus;
import data.enums.StorageArea;
import data.vo.StorageInfoVO;

/**
 * 
 * @author xu
 *
 */
public class StorageInfoPO extends DataPO {

	private static final long serialVersionUID = 18;
//物理库存信息
//默认大小
	private int planeRow = 5,
			trainRow = 5,
			truckRow = 5,
			flexibleRow = 5;
//总数为ROW
	private int area = 4,//固定
				row = planeRow+trainRow+truckRow+flexibleRow, //设置，总排数
				shelf = 5, //设置，每排的架数
				num = 10;//设置，每架的位数
//物理库存信息
	
	
	
	private double alarmPercent = 0.9;//警戒比例
	
	private StorageArea enlargeArea = StorageArea.FLEXIBLE;

	long transferCenterNum;


	
	ArrayList<long[][][]> storage;

	
	public int getArea() {
		return area;
	}



	public void setArea(int area) {
		this.area = area;
	}



	public int getPlaneRow() {
		return planeRow;
	}



	public int getTrainRow() {
		return trainRow;
	}



	public int getTruckRow() {
		return truckRow;
	}

	public int getFlexibleRow() {
		return flexibleRow;
	}

	public int getPlane() {
		return shelf*planeRow*num;
		
	}

	public int getTrain() {
		return shelf*trainRow*num;
	}

	public int getTruck() {
		return shelf*truckRow*num;
	}

	public int getFlexible() {
		return shelf*flexibleRow*num;
	}

	public int getPlaneIndex(){
		return 0;
	}
	
	public int getTrainIndex(){
		return 1;
	}
	
	public int getTruckIndex(){
		return 2;
	}
	
	public int getFlexibleIndex(){
		return 3;
	}

	public long getTransferNum() {
		return transferCenterNum;
	}

	public void setTransferNum(long transferNum) {
		this.transferCenterNum = transferNum;
	}

	public StorageInfoPO() {
		super(POType.STORAGEINFO);
		
	}
	
	public int getRow() {
		return row;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPlaneRow(int planeRow) {
		this.planeRow = planeRow;
	}

	public void setTrainRow(int trainRow) {
		this.trainRow = trainRow;
	}

	public void setTruckRow(int truckRow) {
		this.truckRow = truckRow;
	}


	public void setFlexibleRow(int flexibleRow) {
		this.flexibleRow = flexibleRow;
	}

	public double getAlarmPercent() {
		return alarmPercent;
	}

	public void setAlarmPercent(double alarmPercent) {
		this.alarmPercent = alarmPercent;
	}

	public StorageArea getEnlargeArea() {
		return enlargeArea;
	}

	public void setEnlargeArea(StorageArea enlargeArea) {
		this.enlargeArea = enlargeArea;
	}

	public long getTransferCenterNum() {
		return transferCenterNum;
	}

	public void setTransferCenterNum(long transferCenterNum) {
		this.transferCenterNum = transferCenterNum;
	}
	
	public ArrayList<long[][][]> getStorage() {
		return storage;
	}

	public void setStorage(ArrayList<long[][][]> storage) {
		this.storage = storage;
	}

	public StorageInfoPO(long ins,int shelf,int num,int planeR,int trainR,int truckR,int flexibleR,double percent){
		super(POType.STORAGEINFO);
		this.transferCenterNum = ins;
		this.num = num;
		this.shelf = shelf;
		this.planeRow = planeR;
		this.trainRow = trainR;
		this.truckRow = truckR;
		this.flexibleRow = row - planeR- trainR - truckR;
		this.alarmPercent = percent;
		this.enlargeArea = StorageArea.FLEXIBLE;
		int max = planeR;
		if(trainR > max) max = trainR;
		if(truckR > max) max = truckR;
		storage = new ArrayList<>();
		long[][][] plane = new long[planeR][shelf][num];
		long[][][] train = new long[trainR][shelf][num];
		long[][][] truck = new long[truckR][shelf][num];
		long[][][] flexible = new long[flexibleRow][shelf][num];
		storage.add(plane);
		storage.add(train);
		storage.add(truck);
		storage.add(flexible);
	}



}
