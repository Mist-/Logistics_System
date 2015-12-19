package businesslogic.impl.financialbl;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.AccountPO;
import data.po.DataPO;
import data.po.ReceiptPO;
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
	
	public ResultMessage printAccount(ArrayList<AccountVO> accounts) {
		try{

		//打开文件

		WritableWorkbook book = Workbook.createWorkbook(new File("银行账户.xls"));

		//生成名为“第一页”的工作表，参数0表示这是第一页

		WritableSheet sheet = book.createSheet("第一页",0);

		//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
    	//将定义好的单元格添加到工作表中
	   Label label = new Label(0,0,"ID");
	   sheet.addCell(label);
       label = new Label(1,0,"账户名称");
       sheet.addCell(label);
       label = new Label(2,0,"账户余额");
       sheet.addCell(label);
       
		
       //其中i表示列，j表示行
       int j =1;
       for(AccountVO ac:accounts){
    	   if(j <= accounts.size()){
    		   label = new Label(0,j,Double.toString(ac.getAccountNum()));
               sheet.addCell(label);
               label = new Label(1,j,ac.getName());
               sheet.addCell(label);
               label = new Label(2,j,Double.toString(ac.getMoney()));
               sheet.addCell(label);
               j++;
    	   }
    	   
       }
		//写入数据并关闭文件

		book.write();

		book.close();

		}catch(Exception e)

		{

		System.out.println(e);

		}
		return null;
	}
	
		
}
