package businesslogic.service.Transfer.hall;

import data.message.ResultMessage;
import data.vo.DriverInfoVO;
import data.vo.DriverListVO;

public interface DriverManagementService {
	public DriverListVO getDrivers();
	public DriverInfoVO chooseDriver(long driverID);
	public ResultMessage addDriver(DriverInfoVO newDriver);
	public ResultMessage modifyDriver(DriverInfoVO driver);
	public ResultMessage deleteDriver(long driverID);
}
