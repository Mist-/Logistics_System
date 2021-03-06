package data.vo;

import data.po.TransferListPO;

/**
 * 
 * @author xu
 *
 */
public class TransferListVO extends DeliveryListVO {
	public String[] header = { "订单编号", "区域", "排号", "架号", "位号" };
	public String transferListID;// 中转单ID
	public String date;// 日期
	public String staff;// 监装员
	public String driver;// 汽运时，司机姓名
	public long staffID;
	public String transferCenter;// 中转中心(from)
	public String transferCenterID;
	public String vehicleCode;// 班次，汽运时为车辆代号
	public String target;// 目标机构
	public String targetName;// 目标机构名称
	public String[][] orderAndPosition;
	public String transferType;// 装运类型
	public String fee;// 费用

	public TransferListVO() {

	}

	public TransferListVO(TransferListPO po) {
		this.target = po.getTarget()+"";
		this.targetName = po.getTargetCenterName();
		this.vehicleCode = po.getVehicleCode();
		this.date = po.getDate();
		this.staff = po.getStaffName();
		this.staffID = po.getStaff();
		this.transferCenter = po.getTransferCenterName();
		this.transferListID = po.getTransferListID() + "";
		this.transferCenterID = po.getTransferCenter() + "";
		this.fee = po.getFee() + "";
		switch (po.getTransferType()) {
		case PLANE: {
			transferType = "航运";
			break;
		}
		case TRAIN: {
			transferType = "铁运";
			break;
		}
		default: {
			transferType = "汽运";
			this.driver = "";
		}
		}

		long[] order = po.getOrder();
		String[] position = po.getStoragePosition();
		orderAndPosition = new String[order.length][5];
		for (int i = 0; i < order.length; i++) {
			String[] pos = position[i].split("-");
			String[] in = { order[i] + "", pos[0], pos[1], pos[2], pos[3] };
			orderAndPosition[i] = in;
		}
	}

}
