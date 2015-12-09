package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import data.enums.POType;
import data.enums.StorageArea;
import data.message.ResultMessage;
import data.po.DataPO;
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
	InstitutionInfo center;
	StorageInfoPO storageInfoPO;
	StorageInfo storageInfo;
	ArrayList<DataPO> storageList;
	double[] actualRate;

	/**
	 * 库存报警
	 */
	public ArrayList<String> storageAlarm() {
		// 前提是已经调用过showspace
		ArrayList<String> result = new ArrayList<String>();
		double planeRate = actualRate[0];
		double trainRate = actualRate[1];
		double truckRate = actualRate[2];
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
	public double[] showSpace() {
		int plane = 0;
		int train = 0;
		int truck = 0;
		if(storageInfoPO == null){
			return null;
		}
		ArrayList<long[][][]> storageInfo = storageInfoPO.getStorage();

		for (int i = 0; i < 3; i++) {
			long[][][] info = storageInfo.get(i);
			int row = 0;
			if (i == 0)
				row = storageInfoPO.getPlaneRow();
			else if (i == 1)
				row = storageInfoPO.getTrainRow();
			else
				row = storageInfoPO.getTruckRow();
			for (int j = 0; j < row; j++) {
				for (int k = 0; k < storageInfoPO.getShelf(); k++) {
					for (int n = 0; n < storageInfoPO.getNum(); n++) {
						if (i == 0 && info[j][k][n] != 0)
							plane++;
						else if (i == 1 && info[j][k][n] != 0)
							train++;
						else if (i == 2 && info[j][k][n] != 0)
							truck++;
						else
							;
					}
				}
			}
		}
		double planeRate = ((double) plane) / storageInfoPO.getPlane();
		double trainRate = ((double) train) / storageInfoPO.getTrain();
		double truckRate = ((double) truck) / storageInfoPO.getTruck();

		double[] result = { planeRate, trainRate, truckRate };
		actualRate = result;
		return result;
	}

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
	 * @throws ParseException 
	 * 
	 * @throws RemoteException
	 */
	@Override
	public StorageListVO getStorageInList(String startTime, String endTime,
			POType type) throws ParseException, RemoteException {
		ArrayList<String> date = getTime(startTime, endTime);
		if (date == null) {
			return null;
		}
		for(String d:date){
			DataPO i = storageData.searchByDate(type,d);
			if (i != null) {
				storageList.add(i);
			}
		}
		int all = 0;
		String[][] List = new String[storageList.size()][2];
		for (int i = 0; i < List.length; i++) {
			StorageInListPO in = (StorageInListPO) storageList.get(i);
			all += in.getOrderNum();
			String[] s = { in.getSerialNum() + "", in.getDate() };
			List[i] = s;
		}
		return new StorageListVO(List, all);
	}

	/**
	 * 获取时间段内所有日期
	 * @param start 起始
	 * @param end 终止
	 * @return 所有日期
	 * @throws ParseException
	 */
	private ArrayList<String> getTime(String start, String end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ArrayList<String> date = new ArrayList<String>();
		try {
			Date s = sdf.parse(start);
			Date e = sdf.parse(end);
			date.add(sdf.format(s));
			Calendar begin = Calendar.getInstance();
			begin.setTime(s);
			Calendar over = Calendar.getInstance();
			over.setTime(e);
			if (begin.after(end)) {
				return null;
			}
			while (over.after(begin.getTime())) {
				begin.add(Calendar.DAY_OF_MONTH, 1);
				date.add(sdf.format(begin));
			}
			return date;
			
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * 查看一个入库单
	 * 
	 * @param listCode
	 * @return
	 */
	public StorageInVO getStorageIn(long listCode) {
		for (DataPO in : storageList) {
			if (in.getSerialNum() == listCode) {
				return new StorageInVO((StorageInListPO) in);
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
		for (DataPO out : storageList) {
			if (out.getSerialNum() == listCode) {
				return new StorageOutVO((StorageOutListPO) out);
			}
		}

		return null;
	}

	/**
	 * 库存盘点
	 */
	public StorageInfoVO storageCheck() {
		if (storageInfoPO != null) {
			return new StorageInfoVO(storageInfoPO);
		}else{
			return null;
		}
	}

	/**
	 * 储存库存快照
	 * 
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInfo()
			throws RemoteException {
		if(storageInfoPO != null){
		storageInfoPO.setPoType(POType.STORAGECHECK);
		return storageData.add(storageInfoPO);
		}else{
			return ResultMessage.FAILED;
		}
	}

	/**
	 * 导出库存快照到excel
	 */
	public void storageCheckOutput(StorageInfoPO info) {
		// TODO Auto-generated method stub

	}

	/**
	 * 输入初始化信息
	 */
	public ResultMessage inputStorageInitInfo(int num, int shelf, int planeR,
			int trainR, int truckR, int flexibleR, double alarmPercent) {
		if (storageInfoPO != null) {
			try {
				storageData.delete(storageInfoPO);
			} catch (RemoteException e) {
				e.printStackTrace();

				return ResultMessage.FAILED;
			}
		}
		storageInfoPO = new StorageInfoPO(center.getCenterID(), shelf, num,
				planeR, trainR, truckR, flexibleR, alarmPercent);
		try {
			return storageData.add(storageInfoPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

	}

	public StorageOperate(InstitutionInfo center,
			StorageDataService storageData, StorageInfo storageInfo)
			throws RemoteException {
		this.storageData = storageData;
		this.center = center;
		this.storageInfo = storageInfo;
		storageInfoPO = storageInfo.getStorageInfoPO();
		storageList = new ArrayList<DataPO>();
	}

}
