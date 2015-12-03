package data.vo;

import data.enums.UserRole;
import data.po.StaffPO;
import data.po.UserPO;

import javax.swing.*;

/**
 * ���ڵ�½�����userBL֮����û����ݴ���
 * Created by Mouse on 2015/10/23 0023.
 */
public class UserVO {
    public long serialNum;

    public long institution;
    public String name;
    public boolean gender; /* 0 - male , 1 - female */
    public long phoneNum;
    public String idcardNum; /* ���֤���� */
    public String pswd_md5;
    public UserRole userRole;
    public boolean deleted;
    public long staffsn;
    public UserVO() {}

    public UserVO(StaffPO staffPO) {
        serialNum = staffPO.getSerialNum();
        institution = staffPO.getInstitution();
        name = staffPO.getName();
        gender = staffPO.getGender();
        phoneNum = staffPO.getPhoneNum();
        idcardNum = staffPO.getIdcardNum();
        pswd_md5 = staffPO.getPassword();
        userRole = staffPO.getUserRole();
        deleted = staffPO.isDeleted();
        staffsn = staffPO.getSerialNum();
    }

    public UserVO(UserPO userPO) {
        this.staffsn = userPO.getStaffsn();
        this.name = userPO.getName();
        this.userRole = userPO.getRole();
    }
}