package data.properties;

import utils.PasswordHelper;

import java.io.Serializable;

/**
 * Remembered user account's id & pswd
 * Created by mist on 2015/11/26 0026.
 */
public class RememberedUserAccount implements Serializable{

    long sn;
    String pswd_md5;

    public long getSn() {
        return sn;
    }

    public String getPswd_md5() {
        return pswd_md5;
    }

    public void setPswd_md5(String pswd) {
        pswd_md5 = pswd;
    }

    public void setSN(long sn) {
        this.sn = sn;
    }
}
