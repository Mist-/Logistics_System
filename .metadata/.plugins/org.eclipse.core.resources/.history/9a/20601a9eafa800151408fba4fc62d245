package mock;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.CityInfoPO;
import data.po.CityTransPO;
import data.po.DataPO;
import data.po.SalaryPO;
import data.service.CompanyDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class MockCompanyDataService implements CompanyDataService {

	HashMap<POType, ArrayList<DataPO>> poLists = new HashMap<>();

	@Override
	public DataPO search(POType type, long key) throws RemoteException {
		// TODO Auto-generated method stub
		if (type == POType.INSTITUTION)
			return new MockInstitutionPO(1000);
		else if (type == POType.STAFF)
			return new MockStaffPO(1000, 1000);
		else if (type == POType.CITYTRANS)
			return new MockCityTransPO("南京", "上海", 100, 10, 20, 30);
		else if (type == POType.ARRIVAL)
			return new MockDataPO(POType.RECEIPT);
		else
			return null;
	}

	@Override
	public ArrayList<DataPO> getPOList(POType type) throws RemoteException {
		if (type == POType.CITYINFO) {
			System.out.println("mockcity");
			ArrayList<DataPO> city = new ArrayList<>();
			CityInfoPO c1 = new CityInfoPO(1001);
			c1.setName("北京");
			city.add(c1);
			CityInfoPO c2 = new CityInfoPO(1002);
			c2.setName("南京");
			city.add(c2);
			return city;
		}
		return null;
	}

	@Override
	public ArrayList<DataPO> getPOListFromFile(POType type, String version)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage savePOListToFile(POType type, String version)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DataPO> searchAll(POType type) {
		ArrayList<DataPO> dlist = null;
		if (type == POType.SALARY)
			dlist.add(new MockSalaryPO(10, "快递员", "计次"));
		else if (type == POType.STAFF)
			dlist.add(new MockStaffPO(1000, 1000));
		else if (type == POType.COSTBENEFIT)
			dlist.add(new MockCostBenefitPO(1000, 500, 500));
		else if (type == POType.RECEIPT)
			dlist.add(new MockReceiptPO(500));
		else if (type == POType.PAYMENT)
			dlist.add(new MockPaymentPO(500));
		return dlist;
	}

	public ResultMessage modify(POType type, DataPO dataPO) {
		return ResultMessage.SUCCESS;
	}

	public ResultMessage add(POType type, DataPO dataPO) {
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(POType type, DataPO dataPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DataPO searchCity(String cityName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long searchBusinessOffice(String boName) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SalaryPO searchByInstitution(String department)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityTransPO searchByCityName(String fromCity, String toCity)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataPO> getUnapprovedPO(POType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approveOf(DataPO dataPO) {
		return null;

	}

	@Override
	public HashMap<POType, ArrayList<DataPO>> hkasfkjhkjash()
			throws RemoteException {
		return poLists;
	}

	@Override
	public ArrayList<DataPO> asdfghjkl() throws RemoteException {
		return null;
	}
}
