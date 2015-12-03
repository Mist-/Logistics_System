package businesslogic.service.company;

/**
 * Created by wyc on 2015/11/11.
 */


import data.message.ResultMessage;
import data.vo.SalaryVO;

import java.util.ArrayList;

public interface SalaryManageBLService {
    //获取工资信息
    public ArrayList<SalaryVO> searchAllSalary();

    public SalaryVO getsalaryByString(String department);
    //工资管理的过程
    public ResultMessage modifySalary(String department, double salary);

    public ResultMessage addSalary(String department, double salary, String type);
}
