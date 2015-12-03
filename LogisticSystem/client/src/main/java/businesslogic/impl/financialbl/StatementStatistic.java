package businesslogic.impl.financialbl;

import businesslogic.impl.company.StatisticsCheckBLImpl;
import data.enums.DataType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.service.FinancialDataService;
public class StatementStatistic {
	
	FinancialDataService financialDataService = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
	StatisticsCheckBLImpl statisticsCheckBLImpl = new StatisticsCheckBLImpl();
	
	public ResultMessage businessConditionExcel(String dateStartYear,String dateStartMonth,String dateStartDay, String dateEndYear,String dateEndMonth,String dateEndDay) {
		statisticsCheckBLImpl.searchPaymentVO(dateStartYear, dateStartMonth, dateStartDay, dateEndYear, dateEndMonth, dateEndDay);
		statisticsCheckBLImpl.searchReceiptVO(dateStartYear, dateStartMonth, dateStartDay, dateEndYear, dateEndMonth, dateEndDay);
		return null;
	}

	public ResultMessage printBusinessConditionExcel(String dateStart,
			String dateEnd) {
		
		return null;
	}

	public ResultMessage costBenefitExcel(String dateEnd) {
		statisticsCheckBLImpl.searchCostBenefitVO();
		return null;
	}
	
	public ResultMessage printCostBenefitExcel(String dateEnd) {
		return null;
	}
}
