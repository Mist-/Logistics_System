package businesslogic.impl.transfer.center;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.impl.user.InstitutionInfo;
import utils.Timestamper;
import data.enums.DataState;
import data.enums.DataType;
import data.enums.POType;
import data.enums.StorageArea;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.TransferListPO;
import data.service.TransferDataService;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;

public class TransferList {
	TransferListPO transfer;
	TransferDataService transferData;
	ArrayList<DataPO> transferList;
	
	public ResultMessage saveTransferList(TransferListVO vo) throws RemoteException{
		String[][] info = vo.orderAndPosition;
		String[] position = new String[info.length];
		long[] order = new long[info.length];
		String area = "0";
		for (int i = 0; i < info.length; i++) {
			order[i] = Long.parseLong(info[i][0]);
			if (info[i][1].equals("机动区"))
				area = "3";
			position[i] = area + "-" + info[i][2] + "-" + info[i][3] + "-"
					+ info[i][4];
		}
		transfer = new TransferListPO();
		transfer.setAccount(false);
		transfer.setDate(vo.date);
		transfer.setFee(Double.parseDouble(vo.fee));
		transfer.setOrder(order);
		transfer.setStaff(vo.staffID);
		transfer.setStaffName(vo.staff);
		transfer.setState(DataState.DRAFT);
		transfer.setStorageOut(false);
		transfer.setStorageOut(false);
		transfer.setStoragePosition(position);
		transfer.setTarget(Long.parseLong(vo.target));
		transfer.setTargetCenterName(vo.targetName);
		transfer.setTransferCenter(Long.parseLong(vo.transferCenterID));
		transfer.setTransferCenterName(vo.transferCenter);
		transfer.setTransferListID(-1);//有问题！！！！！！！！！！！！！
		transfer.setVehicleCode(Long.parseLong(vo.vehicleCode));
		return transferData.add(transfer);
	}
	
	public  TransferListVO createTransferList(TransferLoadVO load,
			InstitutionInfo center,double fee,StorageArea transferType) {
		TransferListVO transferList = new TransferListVO();
		transferList.date = Timestamper.getTimeByDate();
		transferList.fee = fee+"";
		transferList.staff = center.getStaffName();
		transferList.transferCenter = center.getInstitutionName();
		transferList.transferCenterID = center.getCenterID()+"";
		String[][] info = load.getOrderInfo();
		long[] order = new long[info.length];
		String[] position = new String[info.length];
		String area = "0";
		if (transferType == StorageArea.TRAIN)
			area = "1";
		else
			area = "2";
		for (int i = 0; i < info.length; i++) {
			order[i] = Long.parseLong(info[i][0]);
			if (info[i][1].equals("机动区"))
				area = "3";
			position[i] = area + "-" + info[i][2] + "-" + info[i][3] + "-"
					+ info[i][4];
		}
		transferList.orderAndPosition = info;

		return transferList;
	}
	
	public TransferListVO searchTransferList(long listID) throws RemoteException{
		transfer =  (TransferListPO) transferData.search(POType.TRANSFERLIST, listID);
		return new TransferListVO(transfer);
	}
	
	public TransferListPO getTransferList(){
		return transfer;
	}
	
	public TransferListVO getCheckedTransferList(long listID){
		TransferListPO transfer = null;
		for(DataPO d : transferList){
			if(d.getSerialNum() == listID){
				transfer = (TransferListPO) d;
				return new TransferListVO(transfer);
			}
		}
		
		return null;
	}
	
	
	public String[][] getBriefTranserList(long center) throws RemoteException{
		transferList = transferData.getNewlyApprovedPO(POType.TRANSFERLIST, center);
		String[][] transferInfo = new String[2][transferList.size()];
		for(int i = 0;i < transferList.size();i++){
			TransferListPO transfer = (TransferListPO) transferList.get(i);
			String[] in ={transfer.getSerialNum()+"",transfer.getDate()};
			transferInfo[i] = in;
		}	
		return transferInfo;
	}
	
	public TransferList(TransferDataService transferData){
		this.transferData = transferData;
	}
	
	public TransferList(){
		this.transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
	}
}
