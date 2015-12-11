package businesslogic.service.company;


import data.enums.UserRole;
import data.message.ResultMessage;
import data.vo.StaffVO;

import java.util.ArrayList;

public interface StaffManageBLService {

    /**
     * 根据部门名称获得该部门所有员工信息StaffVO表
     *
     * @param institution  部门名称
     * @return  表的引用
     */
    ArrayList<StaffVO> getStaffByInstitution(long institution);

    /**
     * 根据员工id获得员工信息StaffVO
     *
     * @param id  员工id
     * @return  StaffVO的引用
     */
    StaffVO getstaffByID(long id);

    /**
     * 添加单个员工
     *
     * @param staffVO  该员工所有信息
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage addStaff(StaffVO staffVO, long id);

    /**
     * 删除单个员工
     *
     * @param institution  员工所在机构名称
     * @param id  员工的id
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage deleteStaff(long institution, long id);

    /**
     * 移动员工所在机构
     * @param fromInstitution  员工当前所在机构名称
     * @param toInstitution  员工将要被移动到的部门名称
     * @param id  员工的id
     * @param userRole  员工被移动到部门的角色
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage moveStaff(long fromInstitution, long toInstitution, long id, UserRole userRole);

    void endstaffmanage();
}