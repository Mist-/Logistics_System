package mock;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import data.enums.POType;
import data.enums.StockStatus;
import data.message.ResultMessage;
import data.po.ArrivalPO;
import data.po.DataPO;
import data.po.EntruckPO;
import data.po.StorageOutListPO;
import data.po.TransferListPO;
import data.service.DataService;
import data.service.TransferDataService;

public class MockTransferDataService implements TransferDataService {

	@Override
	public ResultMessage add(DataPO data) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public DataPO search(POType type, long key) throws RemoteException {
		// TODO Auto-generated method stub
		if(type == POType.TRANSFERLIST){
		return new TransferListPO();
		}
		
		else if(type == POType.ENTRUCK){
			EntruckPO e =  new EntruckPO();
			ArrayList<Long> o = new ArrayList<>();
			o.add(1001L);
			o.add(1002L);
			o.add(1003L);
			e.setOrderList(o);
			e.setFrom(1000000L);
			return e;
		}
		
		else return null;
	}

	@Override
	public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage getPOListFromFile(POType type, String version) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage savePOListToFile(POType type, String version) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApproved() throws RemoteException {
		return null;
	}



	public ArrayList<DataPO> searchNewArriveList(long InstitutionCode)
			throws RemoteException {
		// TODO Auto-generated method stub
		DataPO a1 = new ArrivalPO();
		DataPO a2 = new ArrivalPO();
		ArrayList<DataPO> arrivals = new ArrayList<DataPO>();
		arrivals.add(a1);
		arrivals.add(a2);
		
 		return arrivals;
	}

	public ArrayList<DataPO> searchNewTransferList(long institutionCode)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DataPO> searchNewEntruckList(long institutionCode)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> searchUncountedList(POType type, long institution)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> searchList(POType type, long institutionID)
			throws RemoteException {
		if(type == POType.ARRIVAL){
		ArrayList<DataPO> a = new ArrayList<>();
		for(int i = 0 ; i < 5;i++){
			ArrivalPO a1 = new ArrivalPO();
			a1.setDate("2015/11/11");
			ArrayList<Long> order = new ArrayList<>();
			order.add(1000L);
			order.add(1001L);
			order.add(1002L);
			ArrayList<StockStatus> s = new ArrayList<>();
			s.add(StockStatus.ROUND);
			s.add(StockStatus.ROUND);
			s.add(StockStatus.ROUND);
			a1.setStockStatus(s);
			a1.setOrder(order);
			a.add(a1);
			}
		return a;
		}else if(type == POType.TRANSFERLIST){
			TransferListPO out1 = new TransferListPO();
			TransferListPO ou2 = new TransferListPO();
			ArrayList<DataPO> a = new ArrayList<>();
			a.add(ou2);
			a.add(out1);
			return a;
		}
		
		return null;
	}

	@Override
	public ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) {
		return null;
	}

}
