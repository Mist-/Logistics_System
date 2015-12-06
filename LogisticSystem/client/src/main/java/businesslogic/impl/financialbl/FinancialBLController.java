package businesslogic.impl.financialbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.service.Financial.FinancialBLService;
import data.enums.POType;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.InstitutionPO;
import data.po.StaffPO;
import data.service.FinancialDataService;
import data.vo.AccountVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

public class FinancialBLController implements FinancialBLService {
	StaffPO staff;
	InstitutionPO institution;
	LoginMessage user;
	FinancialDataService financialData;
	
	AccountManage accountManage;
	FundsManage fundsManage;
	InitialBuild initialBuild;
	StatementStatistic statementStatistic;
	
	public  void getInfo(long userID) {

		try {
			staff = (StaffPO) financialData.search(POType.STAFF,userID);
			institution = (InstitutionPO) financialData.search(POType.INSTITUTION, staff.getInstitution());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public FinancialBLController() {
		
		accountManage = new AccountManage() ;
		fundsManage = new FundsManage() ;
		initialBuild = new InitialBuild() ;
		statementStatistic = new StatementStatistic() ;
	}


	@Override
	public ResultMessage buildStaffExcel(String info) {
		return initialBuild.buildStaffExcel(info);
	}

	@Override
	public ResultMessage buildInstitutionExcel(String info) {
		return initialBuild.buildInstitutionExcel(info);
	}

	@Override
	public ResultMessage buildVehicleInfoExcel(String info) {
		return initialBuild.buildVehicleInfoExcel(info);
	}

	@Override
	public ResultMessage buildStorageInfoExcel(String info) {
		return initialBuild.buildStorageInfoExcel(info);
	}

	@Override
	public ResultMessage buildBankAccountExcel(String info, String[] name,
			double[] money) {
		return initialBuild.buildBankAccountExcel(info, name, money);
	}

//	@Override
//	public ResultMessage businessConditionExcel(String dateStartYear,String dateStartMonth,String dateStartDay, String dateEndYear,String dateEndMonth,String dateEndDay) {
//		return statementStatistic.businessConditionExcel(dateStartYear,dateStartMonth,dateStartDay,dateEndYear,dateEndMonth,dateEndDay);
//	}
//
//	@Override
//	public ResultMessage printBusinessConditionExcel(String dateStart,
//			String dateEnd) {
//		return statementStatistic.printBusinessConditionExcel(dateStart, dateEnd);
//	}
//
//	@Override
//	public ResultMessage costBenefitExcel(String dateEnd) {
//		return statementStatistic.costBenefitExcel(dateEnd);
//	}
//
//	@Override
//	public ResultMessage printCostBenefitExcel(String dateEnd) {
//		return statementStatistic.printCostBenefitExcel(dateEnd);
//	}

//	@Override
//	public ResultMessage acIdentity(String name, String password) {
//		return accountManage.acIdentity(name, password);
//	}
	@Override
	public ArrayList<AccountVO> searchAllAccounts() {
		return accountManage.searchAllAccounts();
	}
	@Override
	public ArrayList<AccountVO> findAccount(String name) {
		return accountManage.findAccount(name);
	}

	@Override
	public AccountVO addAccount(String name, double money) {
		return accountManage.addAccount(name, money);
	}

	@Override
	public ResultMessage changeAccount(AccountVO accountVO) {
		return accountManage.changeAccount(accountVO);
	}

	@Override
	public ResultMessage deleteAccount(long num) {
		return accountManage.deleteAccount(num);
	}

//	@Override
//	public ResultMessage recordPayDate(String info) {
//		return fundsManage.recordPayDate(info);
//	}
//
//	@Override
//	public ResultMessage recordPayMoney(String info) {
//		return fundsManage.recordPayMoney(info);
//	}
	@Override
	public ArrayList<PaymentVO> searchAllPayment() {
		return fundsManage.searchAllPayment();
	}

	@Override
	public ArrayList<ReceiptVO> searchAllReceipt() {
		return fundsManage.searchAllReceipt();
	}
	@Override
	public PaymentVO buildPaymentFromEntruck(PaymentVO pay,long institution) {
		try {
			return fundsManage.buildPaymentFromEntruck(pay,institution);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentVO buildPaymentFromTransfer(PaymentVO pay,long institution) {
		try {
			return fundsManage.buildPaymentFromTransfer(pay,institution);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage buildPaymentFromRent(PaymentVO pay) {
		try {
			return fundsManage.buildPaymentFromRent(pay);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentVO buildPaymentFromWages(PaymentVO pay,String institution) {
		return fundsManage.buildPaymentFromWages(pay,institution);
	}
	
	
	
	@Override
	public ResultMessage printPayment(ArrayList<PaymentVO> payList) {
		return null;
		//return fundsManage.printPayment(payList);
	
	}

//	@Override
//	public ResultMessage recordRecDate(String info) {
//		return fundsManage.recordRecDate(info);
//	}
//
//	@Override
//	public ResultMessage recordRecMoney(String info) {
//		return fundsManage.recordRecMoney(info);
//	}

	@Override
	public ArrayList<ReceiptVO> checkFromAddress(String institution) {
		return fundsManage.checkFromAddress(institution);
	}

	@Override
	public ArrayList<ReceiptVO> checkFromDate(String info) {
		return fundsManage.checkFromDate(info);
	}

	@Override
	public ResultMessage printReceipt(ArrayList<ReceiptVO> recList) {
		return null;
		//return fundsManage.printReceipt(recList);
	}

	@Override
	public double total(ArrayList<ReceiptVO> re) {
		return fundsManage.total(re);
	}

	@Override
	public void endFinancial() {
		
	}
}
