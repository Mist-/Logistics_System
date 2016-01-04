package businesslogic.service.storage;

import data.enums.POType;
import data.enums.StorageArea;
import data.message.ResultMessage;
import data.vo.StorageInfoVO;
import data.vo.StorageListVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public interface StorageOperateService {
	//��汨��
	public ArrayList<String> storageAlarm();
	
	//������
	public double[] showSpace();
	public ResultMessage inputChoice(StorageArea target);
	
	
	//���鿴
	StorageListVO getStorageList(String startTime, String endTime, POType type) throws RemoteException, ParseException;	

	//����̵�
	public StorageInfoVO storageCheck();
	public ResultMessage saveStorageInfo() throws RemoteException;
	public void storageCheckOutput(StorageInfoVO info) throws IOException, RowsExceededException, WriteException;//excel 
	
	//����ʼ��
	public ResultMessage inputStorageInitInfo(int num, int shelf, int planeR, int trainR, int truckR, int flexibleR, double alarmPercent);
	
	//����
	public ResultMessage enlarge(StorageArea area);


	
	


	
	
}
