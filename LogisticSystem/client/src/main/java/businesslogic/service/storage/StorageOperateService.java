package businesslogic.service.storage;

import data.enums.POType;
import data.enums.StorageArea;
import data.enums.TransferCenter;
import data.message.ResultMessage;
import data.po.StorageInfoPO;
import data.vo.StorageInVO;
import data.vo.StorageInfoVO;
import data.vo.StorageListVO;
import data.vo.StorageOutVO;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

public interface StorageOperateService {
	//��汨��
	public ArrayList<String> storageAlarm();
	
	//������
	public double[] showSpace();
	public ResultMessage inputChoice(StorageArea target);
	
	
	//���鿴
	StorageListVO getStorageInList(String startTime, String endTime, POType type) throws RemoteException, ParseException;	

	//����̵�
	public StorageInfoVO storageCheck();
	public ResultMessage saveStorageInfo() throws RemoteException;
	public void storageCheckOutput(StorageInfoPO info);//excel 
	
	//����ʼ��
	public ResultMessage inputStorageInitInfo(int num, int shelf, int planeR, int trainR, int truckR, int flexibleR, double alarmPercent);
	
	public StorageInVO getStorageIn(long id);

	public StorageOutVO getStorageOut(long id);


	
	


	
	
}
