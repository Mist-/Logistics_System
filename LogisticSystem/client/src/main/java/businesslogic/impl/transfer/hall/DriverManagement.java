package businesslogic.impl.transfer.hall;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.DriverInfoPO;
import data.service.TransferDataService;
import data.vo.DriverInfoVO;
import data.vo.DriverListVO;
import businesslogic.impl.user.InstitutionInfo;
import businesslogic.service.Transfer.hall.DriverManagementService;

public class DriverManagement implements DriverManagementService {
	TransferDataService transferData;
	ArrayList<DriverInfoPO> drivers;
	InstitutionInfo user;
	
	/**
	 * 修改一个司机的状态信息为 货运
	 * @param driverID
	 * @return
	 */
	public ResultMessage useDriver(long driverID){
		for(DriverInfoPO d: drivers){
			if(d.getSerialNum() == driverID){
				d.setEngaged(true);
				try {
					return transferData.modify(d);
				} catch (RemoteException e) {
					e.printStackTrace();
					return ResultMessage.FAILED;
				}
			}
		}
		
		return ResultMessage.NOTEXIST;
	}
	
	/**
	 * 修改一个司机状态信息为 空闲
	 * @param driverID
	 * @return
	 */
	public ResultMessage freeDriver(long driverID){
		for(DriverInfoPO d: drivers){
			if(d.getSerialNum() == driverID){
				d.setEngaged(false);
				try {
					return transferData.modify(d);
				} catch (RemoteException e) {
					e.printStackTrace();
					return ResultMessage.FAILED;
				}
			}
		}
		
		return ResultMessage.NOTEXIST;
	}
	
	public String getAvailableDriver(){
		getDrivers();
		for(DriverInfoPO d:drivers){
			if(!d.getEngaged()){
				return d.getSerialNum()+"-"+d.getName();
			}
		}
		
		return null;
	}
	
	public  DriverManagement(InstitutionInfo user) throws RemoteException {
		this.user = user;
		transferData = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
	}
	@Override
	public DriverListVO getDrivers() {
		ArrayList<DataPO> drivers;
		String[][] info;
		try {
			System.out.println(user.getInstitutionID());
			drivers = transferData.searchList(POType.DRIVERINFO,user.getInstitutionID());
			if(drivers == null){
				return null;
			}
			info = new String[drivers.size()][3];
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "网络连接中断，请稍后再试", "提示", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		this.drivers = new ArrayList<DriverInfoPO>();
		int index = 0;
		for(DataPO po: drivers){
			DriverInfoPO d = (DriverInfoPO)po;
			this.drivers.add(d);
			String[] driver = {d.getSerialNum()+"",d.getName(),d.getEngagedString()};
			info[index] = driver;
			index++;
		}
		
		return new DriverListVO(info);
	}
	
	@Override
	public DriverInfoVO chooseDriver(long driverID) {
		for(DriverInfoPO d: drivers){
			if(d.getSerialNum() == driverID)
				return new DriverInfoVO(d);
		}
		return null;
	}

	@Override
	public ResultMessage addDriver(DriverInfoVO newDriver) {
		DriverInfoPO driver = new DriverInfoPO(newDriver);
		driver.setSerialNum(driver.getSerialNum()+ user.getInstitutionID()*10000);
		System.out.println(driver.getSerialNum());
		try {
			return transferData.add(driver);
		} catch (RemoteException e) {
			return ResultMessage.FAILED;
		}
	}

	@Override
	public ResultMessage modifyDriver(DriverInfoVO driver) {
		DriverInfoPO d = new DriverInfoPO(driver);
		d.setSerialNum(Long.parseLong(driver.driverID));
		
		try {
			return transferData.modify(d);
		} catch (RemoteException e) {
			return ResultMessage.FAILED;
		}
	}

	@Override
	public ResultMessage deleteDriver(long driverID) {
		for(DriverInfoPO d:drivers){
			if(d.getSerialNum() == driverID){
				try {
					return transferData.delete(d);
				} catch (RemoteException e) {
					return ResultMessage.FAILED;
				}
				
			}
		}
		return ResultMessage.FAILED;
	}

}
