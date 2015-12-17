package businesslogic.impl.financialbl;

import data.enums.DataType;
import data.enums.POType;
import data.po.DataPO;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.service.*;
import utils.Timestamper;

import javax.swing.*;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class InitialBuild {
	
	FinancialDataService financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);


	/**
	 * 鏈熷垵寤鸿处銆傛湁寰呭畬鍠勶紝甯屾湜鍙互鍐嶈闈腑閫夋嫨鍙互鍒犻櫎鐨勯」鐩��
	 *
	 * @param type
	 * @return
     */
	public ResultMessage initAll(POType type) {
		DataService ds = DataServiceFactory.getDataServiceByPO(type);
		try {
			if (ds == null) throw new RemoteException();
			ds.savePOListToFile(type, Timestamper.getDir());
			initByType(POType.ACCOUNT);
		} catch (RemoteException e) {
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCESS;
	}

	public void initByType(POType type) {
		DataService ds = DataServiceFactory.getDataServiceByPO(type);
		try {
			ds.delete(type);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<?> getHistoryDisplayData(POType type, String date) {
		ArrayList<DataPO> dataPOs = null;
		try {
			 dataPOs = financialDataService.getPOListFromFile(type, date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return dataPOs;
	}

	public ResultMessage buildStaffExcel(String info) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = jFileChooser.getSelectedFile();

		return null;
	}

	public ResultMessage buildInstitutionExcel(String info) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return null;
	}

	public ResultMessage buildVehicleInfoExcel(String info) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return null;
	}

	public ResultMessage buildStorageInfoExcel(String info) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return null;
	}

	public ResultMessage buildBankAccountExcel(String info, String[] name,
			double[] money) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return null;
	}

}
