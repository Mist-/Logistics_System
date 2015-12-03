package businesslogic.impl.transfer.hall;

import java.util.ArrayList;

import utils.Timestamper;
import data.message.ResultMessage;
import data.po.AccountPO;
import data.po.OrderPO;
import data.po.ReceiptPO;
import data.po.StaffPO;
import data.service.CompanyDataService;
import data.service.FinancialDataService;
import data.vo.ReceiptVO;

/**
 * 快递员结算
 * 
 * 未完成！！！
 * 
 * @author xu
 *
 */
public class ReceiveMoney {
	//>>>>>>>>>>>>>>测试用方法
	public void setSenders(ArrayList<StaffPO> senders) {
		this.senders = senders;
	}
	public void setReceipt(ReceiptPO receipt) {
		this.receipt = receipt;
	}
	/*public void setOrderService(MockOrderBLService orderService) {
		this.orderService = orderService;
	}*/
	public void setAccount(AccountPO account) {
		this.account = account;
	}

	//>>>>>>>>>>>>>>测试用方法
	
	


	ArrayList<StaffPO> senders;
	StaffPO sender;
	ReceiptVO receiptVO;
	ReceiptPO receipt;
	//MockOrderBLService orderService;
	AccountPO account;
	CompanyDataService companyData;
	FinancialDataService financialDataService;


	public ArrayList<String> getSenders(long hall){
		//senders = companyData.search(POType.STAFF, hall);王毅承添加
		ArrayList<String> sender = new ArrayList<String>();
		for(StaffPO s:senders){
			sender.add(s.getSerialNum()+"-"+s.getName());
		}
		return sender;
	}
	public ReceiptVO chooseSender(long senderNum){
		for(StaffPO s:senders){
			if(s.getSerialNum() == senderNum)
				sender = s;
		}
		
		ArrayList<OrderPO> orders = new ArrayList<>();
		long[] orderNum = new long[orders.size()];
		double all = 0;
		for(int i = 0 ; i < orders.size();i++){
			OrderPO o = orders.get(i);
			orderNum[i] = o.getSerialNum();
			all += o.getFee();
		}
		receipt = new ReceiptPO();
		receipt.setSender(sender.getName());
		receipt.setMoney(account.getMoney()+all);
		ReceiptVO rec = new ReceiptVO();
		rec.setAddress(receipt.getInstitution());
		rec.setCourierName(receipt.getSender());
		rec.setDate(receipt.getDate());
		rec.setInstitution(receipt.getInstitution());
		rec.setMoney(receipt.getMoney());
		rec.setSender(receipt.getSender());
		return rec;

	}
	
	public ResultMessage receiveMoney(){
		return ResultMessage.SUCCESS;
	}
	
	public ReceiveMoney(long bank){
	//	companyData = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
	//	financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
	//	account = (AccountPO) financialDataService.search(POType.ACCOUNT, bank);
	}
}
