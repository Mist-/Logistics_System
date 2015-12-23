package data.impl;

import data.enums.POType;
import data.po.*;
import data.service.TransferDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by mist on 2015/11/12 0012.
 */
public class TransferDataSerializableImpl extends UnicastRemoteObject implements TransferDataService {

    HashMap<POType, ArrayList<DataPO>> poLists = new HashMap<>();
    ArrayList<DataPO> newlyApproved = new ArrayList<>();
    public TransferDataSerializableImpl() throws RemoteException {
        super();
        poLists.put(POType.ARRIVAL, new ArrayList<DataPO>());
        poLists.put(POType.DRIVERINFO, new ArrayList<DataPO>());
        poLists.put(POType.ENTRUCK, new ArrayList<DataPO>());
        poLists.put(POType.SEND, new ArrayList<DataPO>());
        poLists.put(POType.TRANSFERLIST, new ArrayList<DataPO>());
        poLists.put(POType.VEHICLEINFO, new ArrayList<DataPO>());
        init();
    }


    @Override
    public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
        if (!poLists.containsKey(type)) return null;
        else return poLists.get(type);
    }

    @Override
    public HashMap<POType, ArrayList<DataPO>> hkasfkjhkjash() throws RemoteException {
        return poLists;
    }

    @Override
    public ArrayList<DataPO> asdfghjkl() throws RemoteException {
        return newlyApproved;
    }


    @Override
	public ArrayList<DataPO> searchUncountedList(POType type, long institution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> searchList(POType type, long institutionID)
			throws RemoteException {//司机，车辆，快递员专用
		ArrayList<DataPO> all = getPOList(type);
		ArrayList<DataPO> drivers = new ArrayList<DataPO>();
		if (all == null) {
			return null;
		}else{
			System.out.println(all.size());
			for(DataPO d: all){
				long hall = d.getSerialNum()/10000;
				System.out.println(hall);
				if (hall == institutionID) {
					drivers.add(d);
				}
			}
			
			return drivers;
		}
	}



    @Override
    public ArrayList<DataPO> getNewlyApprovedPO(POType type, long institution) {
        ArrayList<DataPO> result = new ArrayList<>();
        System.out.println(institution);
        for (int i = 0; i < newlyApproved.size(); ++i) {
        	System.out.println(newlyApproved.get(i).getSerialNum());
            if (newlyApproved.get(i).getPOType() == type) {
                switch (type) {
                    case ARRIVAL:
                        if (((ArrivalPO)newlyApproved.get(i)).getDestID() == institution) {
                            result.add(newlyApproved.get(i));
                            newlyApproved.remove(i);
                        }
                        break;
                    case TRANSFERLIST:
                        if (((TransferListPO)newlyApproved.get(i)).getTransferCenter() == institution) {
                            result.add(newlyApproved.get(i));
                            newlyApproved.remove(i);
                        }
                        break;
                    case ENTRUCK:
                        if (((EntruckPO)newlyApproved.get(i)).getFromID() == institution) {
                            result.add(newlyApproved.get(i));
                            newlyApproved.remove(i);
                        }
                        break;
                }
            }
        }
        return result;
    }
}
