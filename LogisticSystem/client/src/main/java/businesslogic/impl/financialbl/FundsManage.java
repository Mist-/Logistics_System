package businesslogic.impl.financialbl;

import java.rmi.RemoteException;
import java.util.ArrayList;













import utils.Timestamper;
import businesslogic.impl.company.SalaryManageBLImpl;
import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.AccountPO;
import data.po.DataPO;
import data.po.EntruckPO;
import data.po.PaymentPO;
import data.po.ReceiptPO;
import data.po.SalaryPO;
import data.po.TransferListPO;
import data.service.FinancialDataService;
import data.service.TransferDataService;
import data.vo.AccountVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;
import data.vo.SalaryVO;


public class FundsManage {
	TransferDataService transferDataService;
	FinancialDataService financialDataService;
	Timestamper timestamper ;
	ArrayList<PaymentVO> paymentVOList;
	ArrayList<ReceiptVO> receiptVOList;
	private ArrayList<DataPO> pList ;
	PaymentVO paymentVO;
	PaymentPO payment;
	ReceiptVO receiptVO;
	ReceiptPO receipt;
	
//	public ResultMessage recordPayDate(String info) {
//		return null;
//	}
//
//	public ResultMessage recordPayMoney(String info) {
//		
//		return null;
//	}
	//搜索全部的付款单
	 public ArrayList<PaymentVO> searchAllPayment (){
		 paymentVOList = new ArrayList<PaymentVO>();
	    	try {
				pList = financialDataService.getPOList(POType.PAYMENT);
				for(DataPO po: pList){
					payment = (PaymentPO) po;
					paymentVO = new PaymentVO();
					paymentVO.setAccount(payment.getAccount());
					paymentVO.setDate(payment.getDate());
					paymentVO.setExInfo(payment.getExInfo());
					paymentVO.setInfo(payment.getInfo());
					paymentVO.setMoney(payment.getMoney());
					paymentVO.setName(payment.getName());
	                paymentVOList.add(paymentVO);
	    			}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	    		return 	paymentVOList;
	    }
	 public ArrayList<ReceiptVO> searchAllReceipt() {
		 receiptVOList = new ArrayList<ReceiptVO>();
	    	try {
				pList = financialDataService.getPOList(POType.RECEIPT);
				for(DataPO po: pList){
					receipt = (ReceiptPO) po;
					receiptVO = new ReceiptVO(); 
					receiptVO.setAddress(receipt.getInstitution());
					receiptVO.setCourierName(receipt.getSender());
					receiptVO.setDate(receipt.getDate());
					receiptVO.setInstitution(receipt.getInstitution());
					receiptVO.setMoney(receipt.getMoney());
					receiptVO.setSender(receipt.getSender());
					receiptVOList.add(receiptVO);
	    			}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	    		return 	receiptVOList;
		}
	
	 //this four methord need write!!!!
	public PaymentVO buildPaymentFromEntruck(PaymentVO pay,long institution) throws RemoteException {
		// if each , then not use all; else , use all to add continue
		
		ArrayList<DataPO> entruckList = transferDataService.searchUncountedList(POType.ENTRUCK, institution);
		
        payment = new PaymentPO();
        double sum = 0;
		for(DataPO po1: entruckList){
			
			//成本管理（付款日期、付款金额、付款人、付款账号、条目(运费（按次计算）)、备注(运单号)
			EntruckPO en = (EntruckPO) po1;
			double fee = en.getFee();
			en.setIscounted(true);
			transferDataService.modify(en);
			sum = sum + fee ; 
			
			
		}
		payment.setMoney(sum);
		//continue add the element
		payment.setDate(pay.getDate());
	    payment.setAccount(pay.getAccount());
	    payment.setExInfo(pay.getExInfo());
	    payment.setInfo(pay.getInfo());
	    payment.setName(pay.getName());
	    
		financialDataService.add(payment);
		
		paymentVO = pay;
		paymentVO.setMoney(sum);
		
		return paymentVO;
	}
	public PaymentVO buildPaymentFromTransfer(PaymentVO pay,long institution) throws RemoteException {
		// if each , then not use all; else , use all to add continue
		ArrayList<DataPO> TransferList = transferDataService.searchUncountedList(POType.TRANSFERLIST, institution);
		
		double sum = 0;
        payment = new PaymentPO();
		for(DataPO po1: TransferList){
			
			//成本管理（付款日期、付款金额、付款人、付款账号、条目(运费（按次计算）)、备注(运单号)
			TransferListPO tr = (TransferListPO) po1;
			double fee = tr.getFee();
			tr.setAccount(true);
			transferDataService.modify(tr);
			sum = sum + fee ; 
			
			
		}
		payment.setMoney(sum);
		//continue add the element
		payment.setDate(pay.getDate());
		payment.setAccount(pay.getAccount());
	    payment.setExInfo(pay.getExInfo());
	    payment.setInfo(pay.getInfo());
	    payment.setName(pay.getName());
		
		financialDataService.add(payment);
		
		paymentVO = pay;
		paymentVO.setMoney(sum);
		
		
		return paymentVO;
	}
	public ResultMessage buildPaymentFromRent(PaymentVO pay) throws RemoteException  {
		PaymentPO payment = new PaymentPO();
		//continue add the element
		payment.setDate(pay.getDate());
		payment.setAccount(pay.getAccount());
	    payment.setExInfo(pay.getExInfo());
	    payment.setInfo(pay.getInfo());
	    payment.setName(pay.getName());
	    payment.setMoney(pay.getMoney());
	    
		financialDataService.add(payment);
			
			
			
			return null;
		
	}
	
	
	public PaymentVO buildPaymentFromWages(PaymentVO pay,String institution){
		payment = new PaymentPO();
		paymentVO = pay;
		try {
			ArrayList<DataPO> a = DataServiceFactory.getDataServiceByPO(POType.SALARY).getPOList(POType.SALARY);
		for(DataPO po:a){
			SalaryPO salaryPO = (SalaryPO) po; 
			if(salaryPO.getInstitution().equals(institution)){
				 payment.setMoney(salaryPO.getSalary());
				 payment.setInfo(pay.getInfo());
				 payment.setExInfo(salaryPO.getType());
				 payment.setDate(pay.getDate());
				 payment.setName(pay.getName());
				 payment.setAccount(pay.getAccount());
				 
				 paymentVO.setMoney(salaryPO.getSalary());
				 payment.setExInfo(salaryPO.getType());
			}
		}
		
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		
    	
		try {
			financialDataService.add(payment);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return paymentVO;
		
	}
	
	public ResultMessage printPayment(String info) {
		// print Excel
		return null;
	}

//	public ResultMessage recordRecDate(String info) {
//		return null;
//	}
//
//	public ResultMessage recordRecMoney(String info) {
//		return null;
//	}

//	public double total(double[] po){
//		double sum=0;
//		for(int i=0;i<po.length;i++)
//		sum += po[i];
//		
//		return sum;
//		
//	}
	
	public ArrayList<ReceiptVO> checkFromAddress(String institution) {
	
		receiptVOList = new ArrayList<ReceiptVO>();
		try {
			ArrayList<DataPO> receiptList = financialDataService.searchRecFromAddress(institution);
			for(DataPO po:receiptList){
				
				receipt = (ReceiptPO) po;
				receiptVO = new ReceiptVO(); 
				receiptVO.setAddress(receipt.getInstitution());
				receiptVO.setCourierName(receipt.getSender());
				receiptVO.setDate(receipt.getDate());
				receiptVO.setInstitution(receipt.getInstitution());
				receiptVO.setMoney(receipt.getMoney());
				receiptVO.setSender(receipt.getSender());
				receiptVOList.add(receiptVO);
				
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}
		return receiptVOList;
	}

	public ArrayList<ReceiptVO> checkFromDate(String date) {
		
		receiptVOList = new ArrayList<ReceiptVO>();
		try {
			ArrayList<DataPO> receiptList = financialDataService.searchRecFromDate(date);
			for(DataPO po:receiptList){
			
				receipt = (ReceiptPO) po;
				receiptVO = new ReceiptVO(); 
				receiptVO.setAddress(receipt.getInstitution());
				receiptVO.setCourierName(receipt.getSender());
				receiptVO.setDate(receipt.getDate());
				receiptVO.setInstitution(receipt.getInstitution());
				receiptVO.setMoney(receipt.getMoney());
				receiptVO.setSender(receipt.getSender());
				receiptVOList.add(receiptVO);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}
	return receiptVOList;
	}
   
	public double total (ArrayList<ReceiptVO> rec){
		double sum = 0;
		
			for(ReceiptVO vo:rec){
				
				double money = vo.getMoney();
				sum = sum + money;
				
			}
			
		
		return sum;
		
	}
	
	public ResultMessage printReceipt(String info) {
		return null;
	}

//	public double[] addReciptItem(MockReceiptPO receiptPO) {
//		po1[i]=receiptPO.getPrice();
//		i++;
//		return po1;
//	}
//	public double[] addPaymentItem(MockPaymentPO paymentPO) {
//		po2[j]=paymentPO.getPrice();
//		j++;
//		return po2;
//	}
	
	public FundsManage(){
		transferDataService = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
		timestamper = new Timestamper();
		}


	

}
