package data.vo;

import data.enums.UserRole;

public class StaffVO {

    public long institution;
    public String name;
    public boolean gender /* 0 - male , 1 - female */;
    public String phoneNum;
    public UserRole userRole;
    public long id;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String idcardNum;

    public StaffVO() {

    }

    public long getInstitution() {
        return institution;
    }

    public void setInstitution(long institution) {
        this.institution = institution;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdcardNum() {
        return idcardNum;
    }

    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum;
    }
}
