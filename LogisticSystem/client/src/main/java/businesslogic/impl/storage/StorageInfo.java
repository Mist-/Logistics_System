package businesslogic.impl.storage;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.text.Position;

import data.enums.POType;
import data.enums.StorageArea;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.po.StorageInfoPO;
import data.po.StorageOutListPO;
import data.po.TransferListPO;
import data.service.StorageDataService;
import data.vo.StorageInVO;
import data.vo.StorageOutVO;
import data.vo.StoragePositionAndOrderID;

public class StorageInfo {
	StorageDataService storageData;
	StorageInfoPO storageInfo;

	/**
	 * 根据中转单生成出库单
	 * 
	 * @param transfer
	 * @return
	 */
	public StorageOutVO createStorageOutList(TransferListPO transfer) {
		StorageOutVO vo = new StorageOutVO();
		vo.setDate(transfer.getDate());
		vo.setTransferListNum(transfer.getSerialNum() + "");
		switch (transfer.getTransferType()) {
		case PLANE:
			vo.setTransferType("航运");
			break;
		case TRAIN:
			vo.setTransferType("铁运");
			break;
		default:
			vo.setTransferType("汽运");
			break;
		}

		long[] orderNum = transfer.getOrder();
		String[] position = transfer.getStoragePosition();

		String[][] info = new String[orderNum.length][5];
		for (int i = 0; i < orderNum.length; i++) {
			String[] pos = position[i].split("-");
			switch (pos[0]) {
			case "0":
				pos[0] = "航运区";
				break;
			case "1":
				pos[0] = "铁运区";
				break;
			case "2":
				pos[0] = "汽运区";
				break;
			default:
				pos[0] = "机动区";
			}

			String[] in = { orderNum[i] + "", pos[0], pos[1], pos[2], pos[3] };
			info[i] = in;
		}
		return vo;
	}

	public StorageInfo(StorageDataService storageData, long centerID) throws RemoteException
			{
		this.storageData = storageData;
		storageInfo = (StorageInfoPO) storageData.search(POType.STORAGEINFO,centerID);
	}
	
	
	/**
	 * 判断仓库是否存在
	 * @return
	 */
	public boolean isStorageExist(){
		if(storageInfo == null)
			return false;
		else {
			return true;
		}
	}

	/**
	 * 给订单分配入库位置
	 * 
	 * 
	 * @param institute
	 * @return 入库单展示信息
	 * @throws RemoteException
	 */
	public StorageInVO allocateSpace(ArrayList<OrderPO> orders)
			throws RemoteException {
		StorageInVO storageInVO = null;

		ArrayList<OrderPO> order = orders;
		String[][] sortResult = new String[order.size()][5];

		if (checkSpace(order) == false)
			return null;
		else {
			for (int i = 0; i < order.size(); i++) {
				OrderPO o = order.get(i);
				StorageArea area = o.getTransferType();
				String[] s = getPosition(area, o.getSerialNum(), storageInfo).split("-");

				switch (s[1]) {
				case "0":
					s[1] = "航运区";
					break;
				case "1":
					s[1] = "铁运区";
					break;
				case "2":
					s[1] = "汽运区";
					break;
				default:
					s[1] = "机动区";
					break;
				}
				sortResult[i] = s;
			}

			storageInVO = new StorageInVO(sortResult);
		}
		return storageInVO;
	}

	/**
	 * 检查库存剩余空间是否足够入库
	 * 
	 * @return 检查结果
	 */
	private boolean checkSpace(ArrayList<OrderPO> order) {
		// 订单类型统计
		int oplane = 0;
		int otrain = 0;
		int otruck = 0;
		ArrayList<long[][][]> storage = storageInfo.getStorage();
		for (OrderPO o : order) {
			if (o.getTransferType() == StorageArea.PLANE)
				oplane++;
			else if (o.getTransferType() == StorageArea.TRAIN)
				otrain++;
			else
				otruck++;
		}

		// 库存各区剩余空间统计
		int lplane = storageInfo.getPlane();
		int ltrain = storageInfo.getTrain();
		int ltruck = storageInfo.getTruck();
		for (int i = 0; i < 3; i++) {
			long[][][] info = storage.get(i);
			int row = 0;
			if (i == 0)
				row = storageInfo.getPlaneRow();
			else if (i == 2)
				row = storageInfo.getTrainRow();
			else
				row = storageInfo.getTruckRow();

			for (int j = 0; j < row; j++) {
				for (int k = 0; k < storageInfo.getShelf(); k++) {
					for (int n = 0; n < storageInfo.getNum(); n++) {
						if (info[j][k][n] != 0)
							switch (i) {
							case 0:
								lplane--;
								break;
							case 1:
								ltrain--;
								break;
							case 2:
								ltruck--;
								break;
							}
					}
				}
			}
		}

		switch (storageInfo.getEnlargeArea()) {
		case PLANE: {
			lplane = lplane + storageInfo.getFlexible();
			long[][][] info = storage.get(storageInfo.getPlaneIndex());
			for (int j = 0; j < storageInfo.getRow(); j++) {
				for (int k = 0; k < storageInfo.getShelf(); k++) {
					for (int n = 0; n < storageInfo.getNum(); n++) {
						if (info[j][k][n] != 0)
							lplane--;
					}
				}
			}
			break;
		}

		case TRAIN: {
			ltrain = ltrain + storageInfo.getFlexible();
			long[][][] info = storage.get(storageInfo.getTrainIndex());
			for (int j = 0; j < storageInfo.getRow(); j++) {
				for (int k = 0; k < storageInfo.getShelf(); k++) {
					for (int n = 0; n < storageInfo.getNum(); n++) {
						if (info[j][k][n] != 0)
							ltrain--;
					}
				}
			}
			break;
		}

		case TRUCK: {
			ltruck = ltruck + storageInfo.getFlexible();
			long[][][] info = storage.get(storageInfo.getTruckIndex());
			for (int j = 0; j < storageInfo.getRow(); j++) {
				for (int k = 0; k < storageInfo.getShelf(); k++) {
					for (int n = 0; n < storageInfo.getNum(); n++) {
						if (info[j][k][n] != 0)
							ltruck--;
					}
				}
			}
		}
			break;
		default:
			break;

		}

		if (oplane > lplane || otrain > ltrain || otruck > ltruck) {
			return false;
		} else
			return true;
	}

	public StoragePositionAndOrderID getOrderID(StorageArea transferType) {
		ArrayList<long[][][]> orders = storageInfo.getStorage();
		ArrayList<Long> order = new ArrayList<>();
		ArrayList<String> position = new ArrayList<>();
		String areas = "航运区";

		int area = 0, row = storageInfo.getPlaneRow();
		if (transferType == StorageArea.TRAIN) {
			area = 1;
			row = storageInfo.getTrainRow();
			areas = "铁运区";
		} else {
			area = 2;
			row = storageInfo.getTruckRow();
			areas = "汽运区";
		}
		// 固定区域搜索订单
		long[][][] info = orders.get(area);
		for (int r = 0; r < row; r++) {
			for (int s = 0; s < storageInfo.getShelf(); s++) {
				for (int n = 0; n < storageInfo.getNum(); n++) {
					order.add(info[r][s][n]);
					position.add(areas + "-" + "-" + r + "-" + s + "-" + n);
				}
			}
		}

		// 扩充区域搜索订单
		info = orders.get(3);
		if (storageInfo.getEnlargeArea() == transferType) {
			for (int r = 0; r < storageInfo.getFlexibleRow(); r++) {
				for (int s = 0; s < storageInfo.getShelf(); s++) {
					for (int n = 0; n < storageInfo.getNum(); n++) {
						order.add(info[r][s][n]);
						position.add("机动区" + "-" + r + "-" + s + "-" + n);
					}
				}
			}
		}

		return new StoragePositionAndOrderID(position, order);
	}

	/**
	 * 
	 * 给订单查找一个入库位置
	 * 
	 * @param area
	 *            区域
	 * @param order
	 *            订单号
	 * @param storageInfo
	 *            库存信息
	 */
	private String getPosition(StorageArea area, long order,
			StorageInfoPO storageInfo) {
		ArrayList<long[][][]> p = storageInfo.getStorage();
		int a = 0;
		int shelf = 0;
		int row = 0;
		int num = 0;

		switch (area) {
		case PLANE:
			a = 0;
			break;
		case TRAIN:
			a = 1;
			break;
		case TRUCK:
			a = 2;
			break;
		default:
			break;
		}

		int rowNum = 0;
		if (a == 0)
			rowNum = storageInfo.getPlaneRow();
		else if (a == 1)
			rowNum = storageInfo.getTrainRow();
		else
			rowNum = storageInfo.getTruckRow();

		long[][][] info = p.get(a);
		// 先在固定区域查找
		while (shelf < rowNum) {
			while (row < storageInfo.getShelf()) {
				while (num < storageInfo.getNum()) {
					if (info[shelf][row][num] == 0) {
						info[shelf][row][num] = order;
						return a + "-" + shelf + "-" + row + "-" + num;
					}
					num++;
				}
				shelf++;
			}
			row++;
		}

		// 检查目标区域是否扩充过，若扩充过，在机动区找
		if (storageInfo.getEnlargeArea() == area) {
			a = 3;
			info = p.get(a);
			while (shelf < storageInfo.getFlexibleRow()) {
				while (row < storageInfo.getShelf()) {
					while (num < storageInfo.getNum()) {
						if (info[shelf][row][num] == 0) {
							info[shelf][row][num] = order;
							return a + "-" + shelf + "-" + row + "-" + num;
						}
						num++;
					}
					shelf++;
				}
				row++;
			}
		}

		return null;
	}

	
	/**
	 * 修改仓库信息
	 * @param storageOut
	 * @param institution
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage modifyStorageInfo(StorageOutListPO storageOut) throws RemoteException{
		if(storageInfo == null) return ResultMessage.FAILED;
		
		ArrayList<long[][][]> info = storageInfo.getStorage();
		String[][] position = storageOut.getPosition();
		
		for(int i = 0 ; i < position.length;i++){
			info.get(Integer.parseInt(position[i][0]))[Integer.parseInt(position[i][1])][Integer.parseInt(position[i][2])][Integer.parseInt(position[i][3])] = 0;
			//storageInfo[Integer.parseInt(position[i][0])][Integer.parseInt(position[i][1])][Integer.parseInt(position[i][2])][Integer.parseInt(position[i][3])] = 0;
		}
		storageInfo.setStorage(info);
		storageData.modify(storageInfo);
		return ResultMessage.SUCCESS;
	}
}
