package businesslogic.service.Financial;

/**
 * Created by mist on 15-10-24.
 */

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.message.ResultMessage;
import data.po.PaymentPO;
import data.vo.AccountVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

public interface FinancialBLService {
//Initial
    ResultMessage buildStaffExcel(String info);

    ResultMessage buildInstitutionExcel(String info);

    ResultMessage buildVehicleInfoExcel(String info);

    ResultMessage buildStorageInfoExcel (String info);

    //Account
    ResultMessage buildBankAccountExcel(String info, String[] name, double[] money);

    
//Statement    
//    ResultMessage businessConditionExcel(String dateStartYear,String dateStartMonth,String dateStartDay, String dateEndYear,String dateEndMonth,String dateEndDay);
//
//    ResultMessage printBusinessConditionExcel (String dateStart,String dateEnd);
//    
//    ResultMessage costBenefitExcel (String dateEnd);
//    
//    ResultMessage printCostBenefitExcel (String dateEnd);

    
//Account    
//    ResultMessage acIdentity(String name, String password);
    ArrayList<AccountVO> searchAllAccounts ();
    
    ArrayList<AccountVO> findAccount(String name);

    AccountVO addAccount(String name, double money);

    ResultMessage changeAccount(String name, double money);

    ResultMessage deleteAccount(long num);
    
    
//Funds
    
//    ResultMessage recordPayDate (String info);
//    
//    ResultMessage recordPayMoney (String info);
    
    ArrayList<PaymentVO> searchAllPayment ();
    
    ArrayList<ReceiptVO> searchAllReceipt ();
    
    PaymentVO buildPaymentFromEntruck(PaymentVO pay, long institution);
    
    PaymentVO buildPaymentFromTransfer(PaymentVO pay, long institution);
    
    ResultMessage buildPaymentFromRent(PaymentVO pay);
    
    PaymentVO buildPaymentFromWages(PaymentVO pay,String institution);
    
    ResultMessage printPayment(String info);

//    ResultMessage recordRecDate (String info);
//
//    ResultMessage recordRecMoney (String info);

    ArrayList<ReceiptVO> checkFromAddress (String institution);
    
    ArrayList<ReceiptVO> checkFromDate (String info);
    
    double total (ArrayList<ReceiptVO> re);
    
    ResultMessage printReceipt (String info);

    void endFinancial();

	

	
}
