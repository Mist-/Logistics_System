package businesslogic.impl.financialbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.AccountPO;
import data.po.DataPO;
import data.service.FinancialDataService;
import data.vo.AccountVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

public class AccountManage {
	private FinancialDataService financialDataService ;
	private ArrayList<DataPO> pList ;
	private AccountVO account ;
	private ArrayList<AccountVO> accountVOList;
	private ArrayList<PaymentVO> paymentVOList;
	private ArrayList<ReceiptVO> receiptVOList;
	private FundsManage fundsManage;
	
	public AccountManage(){
	financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
	pList = new ArrayList<DataPO>();
	paymentVOList = new ArrayList<PaymentVO>();
	receiptVOList = new ArrayList<ReceiptVO>();
	fundsManage = new FundsManage();
	}
	
	public ResultMessage acIdentity(String name, String password) {
		
		return ResultMessage.SUCCESS;
	}
	//更新余额
	public ArrayList<AccountVO> updateMoney(){
		accountVOList = searchAllAccounts();
		paymentVOList = fundsManage.searchAllPayment();
		receiptVOList = fundsManage.searchAllReceipt();
		double money = 0;
		for(AccountVO ac:accountVOList){
			money = ac.getMoney();
			for(PaymentVO pa:paymentVOList){
				if(ac.getName().equals(pa.getAccount())){
					money -= pa.getMoney();
				}
			}
			for(ReceiptVO re:receiptVOList){
				if(ac.getName().equals(re.getSender())){
					money += re.getMoney();
				}
			}
			if(money != ac.getMoney()){
				ac.setMoney(money);
				changeAccount(ac);
			}
		}
		return 	accountVOList;
	}
    public ArrayList<AccountVO> searchAllAccounts (){
    	accountVOList = new ArrayList<AccountVO>();
    	try {
			pList = financialDataService.getPOList(POType.ACCOUNT);
			for(DataPO po: pList){
    				AccountPO a = (AccountPO) po;
    				account = new AccountVO(); 
    				account.setMoney(a.getMoney());
    				account.setName(a.getName());
                    account.setAccountNum(a.getSerialNum());
    				accountVOList.add(account);
    			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    		return 	accountVOList;
    }
	public ArrayList<AccountVO> findAccount(String name) {
	    accountVOList = new ArrayList<AccountVO>();
		
		try {
			pList = financialDataService.search(name);
			for(DataPO po: pList){
				AccountPO a = (AccountPO) po;
				account = new AccountVO(); 
				account.setMoney(a.getMoney());
				account.setName(a.getName());
				account.setAccountNum(a.getSerialNum());
				accountVOList.add(account);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Not Found!");
		}
		return accountVOList;
	}

	public AccountVO addAccount(String name, double money) {
		AccountPO po=new AccountPO(name, money);
		account = new AccountVO(); 
		try {
			financialDataService.add(po);
			account.setMoney(po.getMoney());
			account.setName(po.getName());
			account.setAccountNum(po.getSerialNum());
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}
		return account;
	}

	public ResultMessage changeAccount(AccountVO accountVO) {
		
		try {
			AccountPO account = (AccountPO) financialDataService.search(POType.ACCOUNT, accountVO.getAccountNum());
			account.setName(accountVO.getName());
			account.setMoney(accountVO.getMoney());
			return financialDataService.modify(account);
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}
		return null;
	}

	public ResultMessage deleteAccount(long num) {
		
		
		
		try {
			financialDataService.delete(num);
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return null;
	}
}
