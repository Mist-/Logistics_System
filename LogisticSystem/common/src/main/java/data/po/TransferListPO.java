package data.po;

import java.util.ArrayList;
import data.enums.POType;
import data.enums.StorageArea;

/**
 * 
 * @author xu
 *
 */
public class TransferListPO extends DataPO {
	private static final long serialVersionUID = 21;
	String date;// ����
	long staff;// ��װԱ
	String staffName;
	long transferListID;// ��ת�����
	long transferCenter;// ��ת���ı��
	String transferCenterName;
	String vehicleID;// ���
	long targetCenter;// Ŀ����ת���ı��
	String targetCenterName;
	long[] order;// ������
	String[] storagePosition;// area-row-shelf-num
	StorageArea transferType;// װ������
	double fee = 0;// ����
	boolean isStorageOut;// �Ƿ���ɳ���
	boolean account;// �Ƿ�����

	String driverName;// ��ת����װ��ʱ��Ч

	public long getTransferListID() {
		return transferListID;
	}

	public void setTransferListID(long transferListID) {
		this.transferListID = transferListID;
		serialNum = transferListID;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public boolean isStorageOut() {
		return isStorageOut;
	}

	public ArrayList<Long> getOrderArray() {
		ArrayList<Long> orderNum = new ArrayList<Long>();
		for (int i = 0; i < order.length; i++)
			orderNum.add(order[i]);

		return orderNum;
	}

	public void setStorageOut(boolean isStorageOut) {
		this.isStorageOut = isStorageOut;
	}

	public long getStaff() {
		return staff;
	}

	public void setStaff(long staff) {
		this.staff = staff;
	}

	public long getTargetCenter() {
		return targetCenter;
	}

	public void setTargetCenter(long targetCenter) {
		this.targetCenter = targetCenter;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getTarget() {
		return targetCenter;
	}

	public void setTarget(long target) {
		this.targetCenter = target;
	}

	public String getTransferCenterName() {
		return transferCenterName;
	}

	public void setTransferCenterName(String transferCenterName) {
		this.transferCenterName = transferCenterName;
	}

	public String getTargetCenterName() {
		return targetCenterName;
	}

	public void setTargetCenterName(String targetCenterName) {
		this.targetCenterName = targetCenterName;
	}

	public long[] getOrder() {
		return order;
	}

	public void setOrder(long[] order) {
		this.order = order;
	}

	public StorageArea getTransferType() {
		return transferType;
	}

	public void setTransferType(StorageArea transferType) {
		this.transferType = transferType;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public TransferListPO() {
		super(POType.TRANSFERLIST);
		this.account = false;
		isStorageOut = false;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public boolean isAccount() {
		return account;
	}

	public void setAccount(boolean account) {
		this.account = account;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTransferCenter() {
		return transferCenter;
	}

	public void setTransferCenter(long transferCenter) {
		this.transferCenter = transferCenter;
	}

	public String getVehicleCode() {
		return vehicleID;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleID = vehicleCode;
	}

	public String[] getStoragePosition() {
		return storagePosition;
	}

	public void setStoragePosition(String[] storagePosition) {
		this.storagePosition = storagePosition;
	}

}
