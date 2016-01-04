package businesslogic.impl.company;


import businesslogic.service.company.SalaryManageBLService;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.SalaryPO;
import data.service.CompanyDataService;
import data.vo.SalaryVO;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wyc on 2015/11/12.
 */
public class SalaryManageBLImpl implements SalaryManageBLService {

    private CompanyDataService company = null;
    private SalaryVO salaryVO = null;
    private SalaryPO salaryPO = null;
    private ResultMessage resultMessage = null;

    public SalaryManageBLImpl(){
        company = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
    }

    public ArrayList<SalaryVO> searchAllSalary() {
        ArrayList<DataPO> plist;
        ArrayList<SalaryVO> vlist= new ArrayList<>();
        try {
            plist = company.getPOList(POType.SALARY);
            for(int i=0;i<plist.size();i++){
                salaryPO = (SalaryPO)plist.get(i);
                vlist.add(new SalaryVO(salaryPO.getInstitution(),salaryPO.getSalary(),salaryPO.getType()));
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.getRmiPrefix() + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return vlist;
    }

    public SalaryVO getsalaryByString(String institution) {
        ArrayList<SalaryVO> vsalary = this.searchAllSalary();
        for(int i=0;i<vsalary.size();i++){
            if(vsalary.get(i).getInstitution().equals(institution))
                return vsalary.get(i);
        }
        return null;
    }


    public ResultMessage modifySalary(String institution, double salary) {
            try {
                salaryPO = company.searchByInstitution(institution);
                salaryPO.setSalary(salary);
                resultMessage = company.modify(salaryPO);
            } catch (RemoteException e) {
                System.err.println("与服务器(" + Connection.getRmiPrefix() + ")的连接断开 -" + Calendar.getInstance().getTime());
                resultMessage = ResultMessage.NOTCONNECTED;
            }
        return resultMessage;
    }

    public ResultMessage addSalary(String institution, double salary, String type) {
        try {
            salaryPO = company.searchByInstitution(institution);
            if(salaryPO==null){
                salaryPO = new SalaryPO(salary,institution,type);
                resultMessage = company.add(salaryPO);
            }
            else{
                resultMessage = ResultMessage.EXIST;
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.getRmiPrefix() + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.NOTCONNECTED;
        }
        return resultMessage;
    }
}
