package businesslogic.service.storage;

import data.enums.StorageArea;
import data.enums.TransferCenter;
import data.message.ResultMessage;
import data.po.StorageInfoPO;
import data.vo.StorageInfoVO;
import data.vo.StorageListVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface StorageOperateService {
	//库存报警
	public ArrayList<String> storageAlarm();
	
	//库存调整
	public double[] showSpace();
	public ResultMessage inputChoice(StorageArea target);
	
	
	//库存查看
	public StorageListVO inputTime(int[] startTime,int[] endTime) throws RemoteException;

	
	//库存盘点
	public StorageInfoVO storageCheck(String date);
	public ResultMessage saveStorageInfo(StorageInfoPO info) throws RemoteException;
	public void storageCheckOutput(StorageInfoPO info);//excel 
	
	//库存初始化
	public ResultMessage inputStorageInitInfo(int num,int shelf,int planeR,int trainR,int truckR,int flexibleR,double alarmPercent);
	
	
	
	

	
	


	
	
}
