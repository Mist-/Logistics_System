package businesslogic.service.company;

import data.message.ResultMessage;
import data.vo.CostBenefitVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

import java.util.ArrayList;

public interface StatisticsCheckBLService {

    //查看统计报表
    //经营情况表(收款单)
    public ArrayList<ReceiptVO> searchReceiptVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay);
    //经营情况表(付款单)
    public ArrayList<PaymentVO> searchPaymentVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay);

    //成本收益表
    public CostBenefitVO searchCostBenefitVO();

    //查看统计报表的过程
    public ResultMessage endStatisticsFormCheck();

}
