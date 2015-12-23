package businesslogic.impl.transfer;

import data.enums.StorageArea;

public class TransferInfo {
	//capacity
	public static final int planeCapacity = 500,trainCapacity = 200000,truckCapacity = 1000;//����
	public static final int Eachweight = 10;//kg
	public static final String[] transferType = {"��ѡ������","����","����","����"};
	public static StorageArea getTypeByString(String in){
		if (in.equals(transferType[1])) {
			return StorageArea.PLANE;
		}else if(in.equals(transferType[2])){
			return StorageArea.TRAIN;
		}else if(in.equals(transferType[3])){
			return StorageArea.TRUCK;
		}else{
			return null;
		}
	}
	
}
