package businesslogic.service.company;


import data.enums.UserRole;
import data.message.ResultMessage;
import data.vo.StaffVO;

import java.util.ArrayList;

public interface StaffManageBLService {

    //�ҵ�Ա����Ϣ
    //���ݻ����ҵ�����Ա��
    public ArrayList<StaffVO> getStaffByInstitution(long institution);

    //����Ա��ID�ҵ�����Ա��
    public StaffVO getstaffByID(long id);

    //Ա����������Ĺ���

    public ResultMessage addStaff(StaffVO staffVO, long id);

    public ResultMessage deleteStaff(long institution, long id);

    public ResultMessage moveStaff(long fromInstitution, long toInstitution, long id, UserRole userRole);

    public void endstaffmanage();
}