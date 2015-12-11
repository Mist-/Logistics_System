package businesslogic.service.company;

/**
 * Created by wyc on 2015/11/11.
 */


import data.message.ResultMessage;
import data.vo.SalaryVO;

import java.util.ArrayList;

public interface SalaryManageBLService {
    /**
     * 获得所有部门的工资的SalaryVO表
     *
     * @return  表的引用
     */
    ArrayList<SalaryVO> searchAllSalary();

    /**
     * 获得单个部门的SalaryVO数据
     *
     * @param institution  部门的名称
     * @return  此部门SalaryVO的引用
     */
    SalaryVO getsalaryByString(String institution);

    /**
     * 修改单个部门的工资数据
     *
     * @param institution  部门的名称
     * @param salary  工资的数值
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage modifySalary(String institution, double salary);

    /**
     * 添加单个部门工资的数据
     *
     * @param institution  部门名称
     * @param salary  工资的数值
     * @param type  结算工资的方式
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage addSalary(String institution, double salary, String type);
}
