package businesslogic.impl.company;

import businesslogic.service.company.StatisticsCheckBLService;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.CostBenefitPO;
import data.po.DataPO;
import data.po.PaymentPO;
import data.po.ReceiptPO;
import data.service.CompanyDataService;
import data.vo.CostBenefitVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wyc on 2015/11/15.
 */
public class StatisticsCheckBLImpl implements StatisticsCheckBLService {

    private CompanyDataService company = null;
    private ArrayList<DataPO> plist = null;
    private ReceiptPO receiptPO = null;
    private PaymentPO paymentPO = null;
    private ReceiptVO receiptVO = null;
    private PaymentVO paymentVO = null;
    private CostBenefitPO costBenefitPO = null;
    private CostBenefitVO costBenefitVO = null;

    public StatisticsCheckBLImpl(){
        company = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        plist = new ArrayList<DataPO>();
    }

    @Override
    public ArrayList<ReceiptVO> searchReceiptVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay) {
        ArrayList<ReceiptVO> receiptList = new ArrayList<ReceiptVO>();
        //保存从每个ReceiptPO中获得的日期用来比较
        String receiptDate = null;
        //保存分隔每个从ReceiptPO中获得的日期,以"年 月 日"形式保存
        String [] receiptDates = new String[3];
        try {
            plist.clear();
            plist = company.getPOList(POType.RECEIPT);
            for(int i=0;i<plist.size();i++){
                receiptPO = (ReceiptPO)plist.get(i);
                receiptDate = receiptPO.getDate();
                receiptDates = receiptDate.split("[/]");
                //判断所有ReceiptPO中的日期是否在指定日期之中
                if((receiptDates[0].compareTo(toYear))<=0&&(receiptDates[0].compareTo(fromYear))>=0){
                    if((receiptDates[1].compareTo(toMonth))<=0&&(receiptDates[1].compareTo(fromMonth))>=0){
                        if((receiptDates[1].compareTo(toDay))<=0&&(receiptDates[1].compareTo(fromDay))>=0){
                            //创建一个新的ReceiptVO加到List中
                            receiptVO = new ReceiptVO();
                            receiptVO.setMoney(receiptPO.getMoney());
                            receiptVO.setDate(receiptPO.getDate());
                            receiptVO.setInstitution(receiptPO.getInstitution());
                            receiptVO.setAddress(receiptPO.getInstitution());
                            receiptVO.setPeople(receiptPO.getSender());
                            receiptVO.setSender(receiptPO.getSender());
                            receiptList.add(receiptVO);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        return receiptList;
    }

    @Override
    public ArrayList<PaymentVO> searchPaymentVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay) {
        ArrayList<PaymentVO> paymentList = new ArrayList<PaymentVO>();
        //保存从每个PaymentPO中获得的日期用来比较
        String paymentDate = null;
        //保存分隔每个从PaymentPO中获得的日期,以"年 月 日"形式保存
        String [] paymentDates = new String[3];
        try {
            plist.clear();
            plist= company.getPOList(POType.PAYMENT);
            for(int i=0;i<plist.size();i++) {
                paymentPO = (PaymentPO) plist.get(i);
                paymentDate = paymentPO.getDate();
                paymentDates = paymentDate.split("[/]");
                //判断所有PaymentPO中的日期是否在指定日期之中
                if((paymentDates[0].compareTo(fromYear)>=0)&&(paymentDates[0].compareTo(toYear)<=0)){
                    if((paymentDates[1].compareTo(fromMonth)>=0)&&(paymentDates[1].compareTo(toMonth)<=0)){
                        if((paymentDates[2].compareTo(fromDay)>=0)&&(paymentDates[2].compareTo(toDay)<=0)){
                            //创建一个新的PaymentVO加到List中
                            paymentVO = new PaymentVO();
                            paymentVO.setMoney(paymentPO.getMoney());
                            paymentVO.setDate(paymentPO.getDate());
                            paymentVO.setAccount(paymentPO.getAccount());
                            paymentVO.setExInfo(paymentPO.getExInfo());
                            paymentVO.setInfo(paymentPO.getInfo());
                            paymentVO.setName(paymentPO.getName());
                            paymentList.add(paymentVO);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return paymentList;
    }

    @Override
    public CostBenefitVO searchCostBenefitVO() {
        double allIn = 0,allOut = 0,allProfit = 0;
        try {
            //获得ReceiptPO和PaymentPO来计算总收入总支出
            plist = company.getPOList(POType.RECEIPT);
            for(int i=0;i<plist.size();i++){
                receiptPO = (ReceiptPO) plist.get(i);
                allIn += receiptPO.getMoney();
            }
            plist = company.getPOList(POType.PAYMENT);
            for(int i=0;i<plist.size();i++){
                paymentPO = (PaymentPO) plist.get(i);
                allOut += paymentPO.getMoney();
            }
            //根据总收入总支出来计算总利润
            allProfit = allIn - allOut;
            //新建一个CostBenefitVO
            costBenefitVO = new CostBenefitVO();
            costBenefitVO.setAllIncome(allIn);
            costBenefitVO.setAllPay(allOut);
            costBenefitVO.setAllProfit(allProfit);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return costBenefitVO;
    }

//    @Override
//    public ResultMessage endStatisticsFormCheck() {
//        return null;
//    }
}
