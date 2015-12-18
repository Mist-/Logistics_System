package data.impl;

import data.enums.POType;
import data.po.DataPO;
import data.po.UserPO;
import data.service.UserDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by mist on 2015/11/12 0012.
 */
public class UserDataSerializableImpl extends UnicastRemoteObject implements UserDataService {
    HashMap<POType, ArrayList<DataPO>> poLists = new HashMap<>();
    ArrayList<DataPO> newlyApproved = new ArrayList<>();

    public UserDataSerializableImpl() throws RemoteException {
        super();
        poLists.put(POType.USER, new ArrayList<DataPO>());
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
    public ArrayList<DataPO> search(POType type, String key) {
        ArrayList<DataPO> result = new ArrayList<>();
        if (type == POType.USER) {
            for (DataPO user: poLists.get(POType.USER)) {
                if (((UserPO)user).getName().equals(key)) result.add(user);
            }
            return result;
        }
        return null;
    }

    @Override
    public DataPO search(POType type, long staffsn) {
        ArrayList<DataPO> users = poLists.get(POType.USER);
        for (DataPO data: users) {
            if (((UserPO) data).getStaffsn() == staffsn) return data;
        }
        return null;
    }
}
