package businesslogic.impl.user;

import java.rmi.RemoteException;

import businesslogic.impl.financialbl.InitialBuild;
import mock.MockCompanyDataService;
import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
import data.message.LoginMessage;
import data.po.InstitutionPO;
import data.po.StaffPO;
import data.service.DataService;

/**
 * 
 * 保存用户登录后的机构和人员信息
 * 
 * @author xu
 *
 */
public class InstitutionInfo {
	protected DataService companyData;
	protected InstitutionPO institution;
	protected StaffPO staff;
	boolean  institutionType;//true 为中转中心  || false 为营业厅
	
	/**
	 * 1. 中转中心 ：返回本中转中心ID
	 * 2. 营业厅 ： 返回目标中转中心ID
	 * @return
	 */
	public long getCenterID(){
		if (institutionType == false) {
			return institution.getTargetCenter();
		}else return getInstitutionID();
	}
	
	public long getStaffID(){
		return staff.getSerialNum();
	}
	public String getStaffName(){
		return staff.getName();
	}
	
	/**
	 * 1. 中转中心： 返回本中转中心名
	 * 2. 营业厅： 返回目标中转中心名
	 * @return
	 * @throws RemoteException
	 */
	public String getCenterName() throws RemoteException{
		if(institutionType == false){
			InstitutionPO center = (InstitutionPO) companyData.search(POType.INSTITUTION, institution.getTargetCenter());
		
			return  center.getName();
		}
		else return institution.getName();
	}

	/**
	 * 返回机构编号
	 * @return
	 */
	public long getInstitutionID() {
		return institution.getSerialNum();
	}

	public InstitutionInfo(LoginMessage login) throws Exception {
		//companyData = DataServiceFactory
		//		.getDataServiceByType(DataType.CompanyDataService);
		companyData = new MockCompanyDataService();
		
		if(companyData == null){
			throw new Exception();
		}
		else {
			init(login.getUserSN());
		}

		
	}
	
	private void init(long user) throws RemoteException{
		staff = (StaffPO) companyData.search(POType.STAFF, user);
		institution = (InstitutionPO) companyData.search(POType.INSTITUTION,
  				staff.getInstitution());
		if(institution.getTargetCenter() == -1){
			institutionType = true;
		}
		else 
			institutionType = false;
	}
	
	/**
	 * 返回机构名
	 * @return
	 */
	public String getInstitutionName(){
		return institution.getName();
	}


}
