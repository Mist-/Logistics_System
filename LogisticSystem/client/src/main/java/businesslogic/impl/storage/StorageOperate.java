package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding;

import mock.MockCompanyDataService;
import mock.MockStorageDataService;
import data.enums.POType;
import data.enums.StorageArea;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.InstitutionPO;
import data.po.StaffPO;
import data.po.StorageInListPO;
import data.po.StorageInfoPO;
import data.po.StorageOutListPO;
import data.service.DataService;
import data.service.StorageDataService;
import data.vo.StorageInVO;
import data.vo.StorageInfoVO;
import data.vo.StorageListVO;
import data.vo.StorageOutVO;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.storage.StorageOperateService;

public class StorageOperate implements StorageOperateService {
	StorageDataService storageData;
	DataService companyData;
	InstitutionInfo center;
	StorageInfo storageInfo;
	StorageInfoPO storageInfoPO;
	ArrayList<StorageInListPO> storageInList;
	ArrayList<StorageOutListPO> storageOutList;

	double[] rate;

	/**
	 * 库存报警
	 */
	public ArrayList<String> storageAlarm() {
		// 前提是已经调用过showspace
		ArrayList<String> result = new ArrayList<String>();
		double planeRate = rate[0];
		double trainRate = rate[1];
		double truckRate = rate[2];
		StorageArea enlargeArea = storageInfoPO.getEnlargeArea();
		if (planeRate >= storageInfoPO.getAlarmPercent()
				&& enlargeArea != StorageArea.PLANE)
			result.add("航空区库存已超过警戒比例!");
		else if (trainRate >= storageInfoPO.getAlarmPercent()
				&& enlargeArea != StorageArea.TRAIN)
			result.add("铁运区库存已超过警戒比例！");
		else if (truckRate >= storageInfoPO.getAlarmPercent()
				&& enlargeArea != StorageArea.TRUCK)
			result.add("汽运区库存已超过警戒比例！");
		else
			;

		return result;
	}

	/**
	 * 显示仓库比例
	 */

	// public double[] showSpace() {
	// int plane = 0;
	// int train = 0;
	// int truck = 0;
	// long[][][][] storageInfo = storageInfoPO.getOrder();
	//
	// for (int i = 0; i < 3; i++) {
	// int row = 0;
	// if (i == 0)
	// row = storageInfoPO.getPlaneRow();
	// else if (i == 1)
	// row = storageInfoPO.getTrainRow();
	// else
	// row = storageInfoPO.getTruckRow();
	// for (int j = 0; j < row; j++) {
	// for (int k = 0; k < storageInfoPO.getShelf(); k++) {
	// for (int n = 0; n < storageInfoPO.getNum(); n++) {
	// if (i == 0 && storageInfo[i][j][k][n] != 0)
	// plane++;
	// else if (i == 1 && storageInfo[i][j][k][n] != 0)
	// train++;
	// else if (i == 2 && storageInfo[i][j][k][n] != 0)
	// truck++;
	// else
	// ;
	// }
	// }
	// }
	// }
	//
	//
	// double planeRate = ((double) plane) / storageInfoPO.getPlane(),
	// trainRate = ((double) train)/ storageInfoPO.getTrain(),
	// truckRate = ((double) truck)/ storageInfoPO.getTruck();
	//
	// double[] result = {planeRate,trainRate,truckRate};
	// rate = result;
	// return result;
	//
	// }

	/**
	 * 确认调整
	 */
	public ResultMessage inputChoice(StorageArea target) {

		if (storageInfoPO.getEnlargeArea() == StorageArea.FLEXIBLE) {
			storageInfoPO.setEnlargeArea(target);
			return ResultMessage.SUCCESS;
		} else
			return ResultMessage.FAILED;
	}

	/**
	 * 输入查看时间范围内所有入库单，出库单
	 * @throws RemoteException 
	 */
	public StorageListVO inputTime(int[] startTime, int[] endTime) throws RemoteException {
		// 查找
		for (int y = startTime[0]; y <= endTime[0]; y++) {
			int m = 1;
			if (y == startTime[0])
				m = startTime[1];

			for (; m <= 12; m++) {
				if (m == 13)
					break;

				int d = 1;
				if (m == startTime[2])
					d = startTime[3];

				for (; d < 31; d++) {
					if (d == 32)
						break;

					ArrayList<DataPO> in = null;
					try {
						in = storageData.searchByDate(POType.STORAGEINLIST, y
								+ "/" + m + "/" + d);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					for (DataPO po : in)
						storageInList.add((StorageInListPO) po);
					ArrayList<DataPO> out = null;
					try {
						out = storageData.searchByDate(POType.STORAGEOUTLIST, y
								+ "/" + m + "/" + d);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					for (DataPO po : out)
						storageOutList.add((StorageOutListPO) po);
				}
			}
		}

		String[][] outList = new String[storageInList.size()][2];
		String[][] inList = new String[storageOutList.size()][2];
		int inAll = 0;
		int outAll = 0;

		for (int i = 0; i < inList.length; i++) {
			StorageInListPO in = storageInList.get(i);
			inAll += in.getOrderNum();
			String[] s = { in.getSerialNum() + "", in.getDate() };
			inList[i] = s;
		}

		for (int i = 0; i < outList.length; i++) {
			StorageOutListPO out = storageOutList.get(i);
			outAll += out.getOrderNum();
			String[] s = { out.getSerialNum() + "", out.getDate() };
			outList[i] = s;
		}

		return new StorageListVO(inList, outList, center.getCenterID(),
				center.getCenterName(), inAll, outAll);
	}

	/**
	 * 查看一个入库单
	 * 
	 * @param listCode
	 * @return
	 */
	public StorageInVO getStorageIn(long listCode) {
		for (StorageInListPO in : storageInList) {
			if (in.getSerialNum() == listCode) {
				return new StorageInVO(in);
			}
		}

		return null;
	}

	/**
	 * 查看一个出库单
	 * 
	 * @param listCode
	 * @return
	 */
	public StorageOutVO getStorageOut(long listCode) {
		for (StorageOutListPO out : storageOutList) {
			if (out.getSerialNum() == listCode) {
				return new StorageOutVO(out);
			}
		}

		return null;
	}

	/**
	 * 库存盘点
	 */
	public StorageInfoVO storageCheck(String date) {
		try {
			storageInfoPO = (StorageInfoPO) storageData.searchStorageInfo(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new StorageInfoVO(storageInfoPO);
	}

	/**
	 * 储存库存快照
	 * 
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInfo(StorageInfoPO info)
			throws RemoteException {
		storageInfoPO.setPoType(POType.STORAGECHECK);
		return storageData.add(info);
	}

	/**
	 * 导出库存快照到excel
	 */
	public void storageCheckOutput(StorageInfoPO info) {
		// TODO Auto-generated method stub

	}

	public StorageOperate(InstitutionInfo center,StorageDataService storageData,StorageInfo storageInfo) throws RemoteException {
		this.storageData = storageData;
		this.center = center;
		this.storageInfo =storageInfo;
		storageInList = new ArrayList<StorageInListPO>();
		storageOutList = new ArrayList<StorageOutListPO>();
	}

	/**
	 * 输入初始化信息
	 */
	public ResultMessage inputStorageInitInfo(long centerID, int num,
			int shelf, int planeR, int trainR, int truckR, int flexibleR,
			double alarmPercent) {
		if (storageInfoPO != null) {
			try {
				storageData.delete(storageInfoPO);
			} catch (RemoteException e) {
				e.printStackTrace();
				
				return ResultMessage.FAILED;
			}
		}
		storageInfoPO = new StorageInfoPO(centerID, shelf, num, planeR, trainR,
				truckR, flexibleR, alarmPercent);
		try {
			return storageData.add(storageInfoPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

	}

	@Override
	public double[] showSpace() {
		// TODO Auto-generated method stub
		return null;
	}

}
