package businesslogic.impl.financialbl;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;














//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
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
	//����ȫ���ĸ��
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
					receiptVO.setPeople(receipt.getSender());
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
			
			//�ɱ������������ڡ�����������ˡ������˺š���Ŀ(�˷ѣ����μ��㣩)����ע(�˵���)
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
			
			//�ɱ������������ڡ�����������ˡ������˺š���Ŀ(�˷ѣ����μ��㣩)����ע(�˵���)
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
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
		
    	
		try {
			financialDataService.add(payment);
		} catch (RemoteException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		return paymentVO;
		
	}
	
	public ResultMessage printPayment(ArrayList<PaymentVO> payList) {
		try{

		//���ļ�

		WritableWorkbook book = Workbook.createWorkbook(new File("���.xls"));

		//������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ

		WritableSheet sheet = book.createSheet("��һҳ",0);

		//��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
    	//������õĵ�Ԫ����ӵ���������
	   Label label = new Label(0,0,"��������");
	   sheet.addCell(label);
       label = new Label(1,0,"������");
       sheet.addCell(label);
       label = new Label(2,0,"������");
       sheet.addCell(label);
       label = new Label(3,0,"�����˺�");
       sheet.addCell(label);
       label = new Label(4,0,"��Ŀ");
       sheet.addCell(label);
       label = new Label(5,0,"��ע");
       sheet.addCell(label);
		
       //����i��ʾ�У�j��ʾ��
       
       for(PaymentVO pay:payList){
    	   for(int j = 1;j <= payList.size();j++){
    		   label = new Label(0,j,pay.getDate());
               sheet.addCell(label);
               label = new Label(1,j,Double.toString(pay.getMoney()));
               sheet.addCell(label);
               label = new Label(2,j,pay.getName());
               sheet.addCell(label);
               label = new Label(3,j,pay.getAccount());
               sheet.addCell(label);
               label = new Label(4,j,pay.getInfo());
               sheet.addCell(label);
               label = new Label(5,j,pay.getExInfo());
               sheet.addCell(label);
    	   }
    	   
       }
		//д�����ݲ��ر��ļ�

		book.write();

		book.close();

		}catch(Exception e)

		{

		System.out.println(e);

		}
		return null;
	}
	public ArrayList<ReceiptVO> checkFromAddress(String institution) {
	
		receiptVOList = new ArrayList<ReceiptVO>();
		try {
			ArrayList<DataPO> receiptList = financialDataService.searchRecFromAddress(institution);
			for(DataPO po:receiptList){
				
				receipt = (ReceiptPO) po;
				receiptVO = new ReceiptVO(); 
				receiptVO.setAddress(receipt.getInstitution());
				receiptVO.setPeople(receipt.getSender());
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
				receiptVO.setPeople(receipt.getSender());
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
	public ResultMessage printReceipt(ArrayList<ReceiptVO> recList) {
		try{

			//���ļ�

			WritableWorkbook book = Workbook.createWorkbook(new File("�տ.xls"));

			//������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ

			WritableSheet sheet = book.createSheet("��һҳ",0);

			//��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
	    	//������õĵ�Ԫ����ӵ���������
		   Label label = new Label(0,0,"�տ�����");
		   sheet.addCell(label);
	       label = new Label(1,0,"�տλ");
	       sheet.addCell(label);
	       label = new Label(2,0,"�տ���");
	       sheet.addCell(label);
	       label = new Label(3,0,"�տ");
	       sheet.addCell(label);
	       label = new Label(4,0,"�տ���");
	       sheet.addCell(label);
	       label = new Label(5,0,"�տ�ص�");
	       sheet.addCell(label);
			
	       //����i��ʾ�У�j��ʾ��
	       
	       for(ReceiptVO rec:recList){
	    	   for(int j = 1;j <= recList.size();j++){
	    		   label = new Label(0,j,rec.getDate());
	               sheet.addCell(label);
	               label = new Label(1,j,rec.getInstitution());
	               sheet.addCell(label);
	               label = new Label(2,j,rec.getPeople());
	               sheet.addCell(label);
	               label = new Label(3,j,rec.getSender());
	               sheet.addCell(label);
	               label = new Label(4,j,Double.toString(rec.getMoney()));
	               sheet.addCell(label);
	               label = new Label(5,j,rec.getAddress());
	               sheet.addCell(label);
	    	   }
	    	   
	       }
			//д�����ݲ��ر��ļ�

			book.write();

			book.close();

			}catch(Exception e)

			{

			System.out.println(e);

			}
		return null;
	}
	public FundsManage(){
		transferDataService = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);
		financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
		timestamper = new Timestamper();
		}


	

}
