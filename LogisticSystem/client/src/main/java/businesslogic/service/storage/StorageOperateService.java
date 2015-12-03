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
	//��汨��
	public ArrayList<String> storageAlarm();
	
	//������
	public double[] showSpace();
	public ResultMessage inputChoice(StorageArea target);
	
	
	//���鿴
	public StorageListVO inputTime(int[] startTime,int[] endTime) throws RemoteException;

	
	//����̵�
	public StorageInfoVO storageCheck(String date);
	public ResultMessage saveStorageInfo(StorageInfoPO info) throws RemoteException;
	public void storageCheckOutput(StorageInfoPO info);//excel 
	
	//����ʼ��
	public ResultMessage inputStorageInitInfo(int num,int shelf,int planeR,int trainR,int truckR,int flexibleR,double alarmPercent);
	
	
	
	

	
	


	
	
}
