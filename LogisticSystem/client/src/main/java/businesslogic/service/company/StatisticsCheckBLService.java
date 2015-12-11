package businesslogic.service.company;

import data.message.ResultMessage;
import data.vo.CostBenefitVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

import java.util.ArrayList;

public interface StatisticsCheckBLService {

    //查看统计报表
    /**
     * 查找指定日期之间的所有入款单ReceiptVO表
     *
     * @param fromYear  起始年份
     * @param fromMonth  起始月份
     * @param fromDay  起始日
     * @param toYear  结束年份
     * @param toMonth  结束月份
     * @param toDay  结束日
     * @return  表的引用
     */
    ArrayList<ReceiptVO> searchReceiptVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay);
    /**
     * 查找指定日期之间的所有付款单PaymentVO表
     *
     * @param fromYear  起始年份
     * @param fromMonth  起始月份
     * @param fromDay  起始日
     * @param toYear  结束年份
     * @param toMonth  结束月份
     * @param toDay  结束日
     * @return  表的引用
     */
    ArrayList<PaymentVO> searchPaymentVO(String fromYear, String fromMonth, String fromDay,
                                                String toYear, String toMonth, String toDay);

    /**
     * 查找成本收益表(CostBenefitVO)
     *
     * @return VO的引用
     */
    CostBenefitVO searchCostBenefitVO();


}
