package businesslogic.service.company;


import data.enums.UserRole;
import data.message.ResultMessage;
import data.vo.StaffVO;

import java.util.ArrayList;

public interface StaffManageBLService {

    //找到员工信息
    //根据机构找到所有员工
    public ArrayList<StaffVO> getStaffByInstitution(long institution);

    //根据员工ID找到具体员工
    public StaffVO getstaffByID(long id);

    //员工机构管理的过程

    public ResultMessage addStaff(StaffVO staffVO, long id);

    public ResultMessage deleteStaff(long institution, long id);

    public ResultMessage moveStaff(long fromInstitution, long toInstitution, long id, UserRole userRole);

    public void endstaffmanage();
}