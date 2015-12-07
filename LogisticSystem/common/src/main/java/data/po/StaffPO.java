package data.po;

import data.enums.POType;
import data.enums.UserRole;
import utils.PasswordHelper;

/**
 *
 * Created by Mouse on 2015/10/24 0024.
 */
public class StaffPO extends DataPO {

    private static final long serialVersionUID = 17;

    protected long institution;
    protected String name;
    protected boolean gender /* 0 - male , 1 - female */;
    protected long phoneNum;
    protected String idcardNum; /* 身份证号码 */
    protected String pswd_md5;
    protected UserRole userRole;
    protected String deleted = "否";   /* 是否已经被删除*/

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return (deleted.equals("是"));
    }

    public void delete() {
        this.deleted = "否";
    }

    public void setUserRole(UserRole role) {
        userRole = role;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getPassword() {
        return pswd_md5;
    }

    public void setRawPassword(String pswd) {
        this.pswd_md5 = PasswordHelper.generateMD5Checksum(pswd);
    }

    public void setPasswordMD5(String pswd_md5) {
        this.pswd_md5 = pswd_md5;
    }

    public StaffPO() {
        super(POType.STAFF);
    }

    public long getInstitution() {
        return institution;
    }

    public void setInstitution(long institution) {
        this.institution = institution;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
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

    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    public String getIdcardNum() {
        return idcardNum;
    }

    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum;
    }
}
