package data.po;

import data.enums.POType;
import data.enums.UserRole;
import utils.IDGenerator;
import utils.PasswordHelper;

public class UserPO extends DataPO {

    private static final long serialVersionUID = 2205631303533979093L;

    String name = "";

    /**
     * store password's MD5 checksum
     */
    String password_signature = "";

    UserRole role = null;

    long staffsn = 0;
    boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UserPO(long sn, String name, String pswd, UserRole role) {
        super(POType.USER);
        this.serialNum = IDGenerator.getNextID(POType.USER);
        this.name = name;
        this.password_signature = PasswordHelper.generateMD5Checksum(pswd);
        this.role = role;
        this.staffsn = sn;
    }

    public long getStaffsn() {
        return staffsn;
    }

    public long getSerialNum() {
        return serialNum;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password_signature;
    }

    //
    public void setPassword(String pswd) {
        this.password_signature = PasswordHelper.generateMD5Checksum(pswd);
    }
}
