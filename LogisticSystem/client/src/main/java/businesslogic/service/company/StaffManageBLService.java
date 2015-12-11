package businesslogic.service.company;


import data.enums.UserRole;
import data.message.ResultMessage;
import data.vo.StaffVO;

import java.util.ArrayList;

public interface StaffManageBLService {

    /**
     * ���ݲ������ƻ�øò�������Ա����ϢStaffVO��
     *
     * @param institution  ��������
     * @return  �������
     */
    ArrayList<StaffVO> getStaffByInstitution(long institution);

    /**
     * ����Ա��id���Ա����ϢStaffVO
     *
     * @param id  Ա��id
     * @return  StaffVO������
     */
    StaffVO getstaffByID(long id);

    /**
     * ��ӵ���Ա��
     *
     * @param staffVO  ��Ա��������Ϣ
     * @return  ���������NOTCONNECTED,������������ݲ����
     */
    ResultMessage addStaff(StaffVO staffVO, long id);

    /**
     * ɾ������Ա��
     *
     * @param institution  Ա�����ڻ�������
     * @param id  Ա����id
     * @return  ���������NOTCONNECTED,������������ݲ����
     */
    ResultMessage deleteStaff(long institution, long id);

    /**
     * �ƶ�Ա�����ڻ���
     * @param fromInstitution  Ա����ǰ���ڻ�������
     * @param toInstitution  Ա����Ҫ���ƶ����Ĳ�������
     * @param id  Ա����id
     * @param userRole  Ա�����ƶ������ŵĽ�ɫ
     * @return  ���������NOTCONNECTED,������������ݲ����
     */
    ResultMessage moveStaff(long fromInstitution, long toInstitution, long id, UserRole userRole);

    void endstaffmanage();
}