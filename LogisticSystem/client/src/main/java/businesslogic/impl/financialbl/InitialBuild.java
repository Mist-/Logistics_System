package businesslogic.impl.financialbl;

import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.service.*;
import utils.Timestamper;

import javax.swing.*;
import java.io.File;
import java.rmi.RemoteException;

public class InitialBuild {
	
	FinancialDataService financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);


	/**
	 * 期初建账。有待完善，希望可以再见面中选择可以删除的项目。
	 *
	 * @param type
	 * @return
     */
	public ResultMessage initAll(POType type) {
		DataService ds = DataServiceFactory.getDataServiceByPO(type);
		try {
			if (ds == null) throw new RemoteException();
			ds.savePOListToFile(type, Timestamper.getTimeByDate());
			initByType(POType.ACCOUNT);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "期初建账失败，因为网络连接断开。在连接上网络后重试。", "LCS物流管理系统", JOptionPane.WARNING_MESSAGE);
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCESS;
	}

	private void initByType(POType type) {
		DataService ds = DataServiceFactory.getDataServiceByPO(type);
		try {
			ds.delete(type);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public ResultMessage buildStaffExcel(String info) {
		// TODO 自动生成的方法存根
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = jFileChooser.getSelectedFile();

		return null;
	}

	public ResultMessage buildInstitutionExcel(String info) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ResultMessage buildVehicleInfoExcel(String info) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ResultMessage buildStorageInfoExcel(String info) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ResultMessage buildBankAccountExcel(String info, String[] name,
			double[] money) {
		// TODO 自动生成的方法存根
		return null;
	}

}
