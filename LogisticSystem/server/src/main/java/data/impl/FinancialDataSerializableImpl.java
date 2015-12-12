package data.impl;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.AccountPO;
import data.po.DataPO;
import data.po.ReceiptPO;
import data.service.FinancialDataService;
import data.vo.AccountVO;
import utils.FileIOHelper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

/**
 *
 * Created by mist on 2015/11/8 0008.
 */
public class FinancialDataSerializableImpl extends UnicastRemoteObject implements FinancialDataService {
    HashMap<POType, ArrayList<DataPO>> poLists = new HashMap<>();
    ArrayList<DataPO> newlyApproved = new ArrayList<>();
    public FinancialDataSerializableImpl() throws RemoteException {
        super();
        poLists.put(POType.ACCOUNT, new ArrayList<DataPO>());
        poLists.put(POType.COSTBENEFIT, new ArrayList<DataPO>());
        poLists.put(POType.PAYMENT, new ArrayList<DataPO>());
        poLists.put(POType.RECEIPT, new ArrayList<DataPO>());
        init();
    }

    public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
        if (!poLists.containsKey(type)) return null;
        return poLists.get(type);
    }

    @Override
    public HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException {
        return poLists;
    }

    @Override
    public ArrayList<DataPO> getNewlyApproved() throws RemoteException {
        return newlyApproved;
    }

    @Override
    public ArrayList<DataPO> search(String name) {
        ArrayList<DataPO> result = new ArrayList<>();
        for (DataPO account: poLists.get(POType.ACCOUNT)) {
            if (((AccountPO)account).getName().equals(name)) {
                result.add(account);
            }
        }
        return result;
    }


    @Override
    public ArrayList<DataPO> searchReceipt(String begin, String end) throws RemoteException {
        ArrayList<DataPO> result = new ArrayList<>();
        for (DataPO receipt: poLists.get(POType.RECEIPT)) {
            if (begin.compareTo(((ReceiptPO)receipt).getDate()) <= 0 && ((ReceiptPO)receipt).getDate().compareTo(end) <= 0) {
                result.add(receipt);
            }
        }
        return result;
    }

	@Override
	public ArrayList<DataPO> searchRecFromAddress(String info)
			throws RemoteException {
		  ArrayList<DataPO> result = new ArrayList<>();
	        for (DataPO receipt: poLists.get(POType.RECEIPT)) {
	            if (((ReceiptPO)receipt).getInstitution().equals(info)) {
	                result.add(receipt);
	            }
	        }
	        return result;
	}

	@Override
	public ArrayList<DataPO> searchRecFromDate(String info)
			throws RemoteException {
		ArrayList<DataPO> result = new ArrayList<>();
        for (DataPO receipt: poLists.get(POType.RECEIPT)) {
            if (((ReceiptPO)receipt).getDate().equals(info)) {
                result.add(receipt);
            }
        }
        return result;
	}

	@Override
	public ResultMessage delete(long num) throws RemoteException {
        ArrayList<DataPO> list = getPOList(POType.ACCOUNT);
        for (DataPO dat : list) {
            if (dat.getSerialNum() == num) {
                list.remove(dat);
                return ResultMessage.SUCCESS;
            }
        }
        return ResultMessage.NOTEXIST;
    }

    public ResultMessage modifyAccount(AccountVO accountVO) throws RemoteException {
		ArrayList<DataPO> list = getPOList(POType.ACCOUNT);
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getSerialNum() == accountVO.getAccountNum()) {
            	
            	AccountPO po = (AccountPO)list.get(i);
            	po.setMoney(accountVO.getMoney());
            	po.setName(accountVO.getName());
            	 list.remove(i);
                 list.add(po);
                 return ResultMessage.SUCCESS;
            }
        }
		return ResultMessage.FAILED;
	}



	    
}
