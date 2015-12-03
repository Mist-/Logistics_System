package businesslogic.service.Transfer.hall;

import data.message.ResultMessage;
import data.vo.TruckInfoVO;
import data.vo.TruckListVO;

public interface TruckManagementService {
	public TruckListVO getTrucks();
	public TruckInfoVO chooseTruck(long truckID);
	public ResultMessage addTruck(TruckInfoVO newtruck);
	public ResultMessage modifyTruck(TruckInfoVO truck);
	public ResultMessage deleteTruck(long truckID);
}
