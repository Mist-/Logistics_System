package data.message;

import data.enums.UserRole;

/**
 * µÇÂ¼ÐÅÏ¢
 * Created by Mouse on 2015/10/23 0023.
 */
public class LoginMessage {

    ResultMessage result;
    long userSN;
    UserRole userRole;

    public void setUserRole(UserRole role) {
        this.userRole = role;
    }

    public UserRole getUserRole() {
        return userRole;
    }


    // Constructors
    public LoginMessage(ResultMessage result) {
        this.result = result;
    }

    public LoginMessage(ResultMessage result, long sn) {
        this.result = result;
        userSN = sn;
    }

    public LoginMessage(ResultMessage result, long sn, UserRole role) {
        this.result = result;
        userSN = sn;
        this.userRole = role;
    }

    // Getters
    public long getUserSN() {
        return userSN;
    }

    public void setUserSN(long userSN) {
        this.userSN = userSN;
    }

    public ResultMessage getResult() {
        return result;
    }

    // Setters
    public void setResult(ResultMessage result) {
        this.result = result;
    }
}
