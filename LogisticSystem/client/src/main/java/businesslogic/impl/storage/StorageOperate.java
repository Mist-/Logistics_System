package businesslogic.impl.storage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import data.enums.POType;
import data.enums.StorageArea;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.StorageInListPO;
import data.po.StorageInfoPO;
import data.po.StorageOutListPO;
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
	 * ��汨��
	 */
	public ArrayList<String> storageAlarm() {
		// ǰ�����Ѿ����ù�showspace
		if(storageInfoPO.getFlexibleNum() == 0){
			storageInfoPO.setEnlargeArea(StorageArea.FLEXIBLE);
		}
		ArrayList<String> result = new ArrayList<String>();
		double planeRate = actualRate[0];
		double trainRate = actualRate[1];
		double truckRate = actualRate[2];
		if (planeRate >= storageInfoPO.getAlarmPercent())
			result.add("����������ѳ����������!");
		else {
			result.add("�����������㣡");
		}
		if (trainRate >= storageInfoPO.getAlarmPercent())
			result.add("����������ѳ������������");
		else {
			result.add("�����������㣡");
		}
		if (truckRate >= storageInfoPO.getAlarmPercent())
			result.add("����������ѳ������������");
		else {
			result.add("�����������㣡");
		}

		return result;
	}

	/**
	 * ��ʾ�ֿ����
	 */
	public double[] showSpace() {
		int plane = 0;
		int train = 0;
		int truck = 0;
		int flexible = 0;
		if (storageInfoPO == null) {
			return null;
		}
		ArrayList<long[][][]> storageInfo = storageInfoPO.getStorage();

		for (int i = 0; i < 4; i++) {
			long[][][] info = storageInfo.get(i);
			int row = 0;
			if (i == 0)
				row = storageInfoPO.getPlaneRow();
			else if (i == 1)
				row = storageInfoPO.getTrainRow();
			else if( i == 2)
				row = storageInfoPO.getTruckRow();
			else {
				row = storageInfoPO.getFlexibleRow();
			}
			for (int j = 0; j < row; j++) {
				for (int k = 0; k < storageInfoPO.getShelf(); k++) {
					for (int n = 0; n < storageInfoPO.getNum(); n++) {
						if (i == 0 && info[j][k][n] != 0)
							plane++;
						else if (i == 1 && info[j][k][n] != 0)
							train++;
						else if (i == 2 && info[j][k][n] != 0)
							truck++;
						else if(i== 3&& info[j][k][n]!= 0)
							flexible ++;
						else {
							;
						}
					}
				}
			}
		}
		
		int planeall = storageInfoPO.getPlane();
		int trainall = storageInfoPO.getTrain();
		int truckall = storageInfoPO.getTruck();
		
		switch (storageInfoPO.getEnlargeArea()) {
		case PLANE:
			planeall += storageInfoPO.getFlexible();
			plane += flexible;
			break;
		case TRAIN:
			trainall += storageInfoPO.getFlexible();
			train += flexible;
			break;
		case TRUCK:
			trainall += storageInfoPO.getFlexible();
			train += flexible;
		default:
			break;
		}
		

		double planeRate = ((double) plane) / planeall;
		double trainRate = ((double) train) / trainall;
		double truckRate = ((double) truck) / truckall;
		double percent = storageInfoPO.getAlarmPercent();
		double[] result = { planeRate, trainRate, truckRate, percent };
		actualRate = result;
		return result;
	}

	/**
	 * ȷ�ϵ���
	 */
	public ResultMessage inputChoice(StorageArea target) {

		if (storageInfoPO.getEnlargeArea() == StorageArea.FLEXIBLE) {
			storageInfoPO.setEnlargeArea(target);
			return ResultMessage.SUCCESS;
		} else
			return ResultMessage.FAILED;
	}

	/**
	 * ����鿴ʱ�䷶Χ��������ⵥ�����ⵥ
	 * 
	 * @throws ParseException
	 * 
	 * @throws RemoteException
	 */
	@Override
	public StorageListVO getStorageList(String startTime, String endTime,
			POType type) throws ParseException, RemoteException {
		ArrayList<String> date = getTime(startTime, endTime);
		if (date == null) {
			return null;
		}
		storageList = storageData.searchByDate(type, date);
		int all = 0;
		String[][] List = new String[storageList.size()][2];
		for (int i = 0; i < List.length; i++) {
			if (type == POType.STORAGEINLIST) {
				StorageInListPO in = (StorageInListPO) storageList.get(i);
				all += in.getOrderNum();
				String[] s = { in.getSerialNum() + "", in.getDate(),
						in.getOrderNum() + "" };
				List[i] = s;
			} else {
				StorageOutListPO out = (StorageOutListPO) storageList.get(i);
				all += out.getOrderNum();
				String[] s = { out.getSerialNum() + "", out.getDate(),
						out.getOrderNum() + "" };
				List[i] = s;
			}
		}
		return new StorageListVO(List, all);
	}

	/**
	 * ��ȡʱ�������������
	 * 
	 * @param start
	 *            ��ʼ
	 * @param end
	 *            ��ֹ
	 * @return ��������
	 * @throws ParseException
	 */
	private ArrayList<String> getTime(String start, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ArrayList<String> date = new ArrayList<String>();
		try {
			Date s = sdf.parse(start);
			Date e = sdf.parse(end);
			date.add(sdf.format(s));
			System.out.println(sdf.format(s));
			Calendar begin = Calendar.getInstance();
			begin.setTime(s);
			Calendar over = Calendar.getInstance();
			over.setTime(e);
			if (begin.after(over)) {
				return null;
			}
			while (over.after(begin)) {
				begin.add(Calendar.DAY_OF_MONTH, 1);
				System.out.println(sdf.format(begin.getTime()));
				date.add(sdf.format(begin.getTime()));
			}
			return date;

		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * �鿴һ����ⵥ
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
	 * �鿴һ�����ⵥ
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
	 * ����̵�
	 */
	public StorageInfoVO storageCheck() {
		if (storageInfoPO != null) {
			return new StorageInfoVO(storageInfoPO);
		} else {
			return null;
		}
	}

	/**
	 * ���������
	 * 
	 * @throws RemoteException
	 */
	public ResultMessage saveStorageInfo() throws RemoteException {
		if (storageInfoPO != null) {
			storageInfoPO.setPoType(POType.STORAGECHECK);
			return storageData.add(storageInfoPO);
		} else {
			return ResultMessage.FAILED;
		}
	}

	/**
	 * ���������յ�excel
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public void storageCheckOutput(StorageInfoVO info) throws IOException, RowsExceededException, WriteException {
		String fileName = "�����Ϣ.xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
		WritableSheet sheet = workbook.createSheet("�����Ϣ", 0);
		//���ñ�ͷ
		Label l1 = new Label(0,0,"������");
		sheet.addCell(l1);
		Label l2 = new Label(1,0,"����");
		sheet.addCell(l2);
		Label l3 = new Label(2,0,"�ź�");
		sheet.addCell(l3);
		Label l4 = new Label(3,0,"�ܺ�");
		sheet.addCell(l4);
		Label l5 = new Label(4,0,"λ��");
		sheet.addCell(l5);
		//��������
		String[][] in = info.orderAndPostitionArray;
		for(int i = 0 ; i < in.length;i++){
			String[] line = in[i];
			for(int j = 0 ; j <line.length;j++){
				Label x = new Label(j,i+1,line[j]);
				System.out.println("��"+i+1+"��");
				sheet.addCell(x);
			}
		}
		
		workbook.write();
		workbook.close();
		
	}

	/**
	 * �����ʼ����Ϣ
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
		storageInfoPO.setSerialNum(center.getCenterID());// �ֿ����к�Ϊ��ת���ı��
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

	@Override
	public ResultMessage enlarge(StorageArea area) {
		storageInfoPO.setEnlargeArea(area);
		try {
			storageData.modify(storageInfoPO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

}
