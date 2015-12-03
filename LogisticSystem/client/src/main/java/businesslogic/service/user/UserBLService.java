package businesslogic.service.user;

import data.message.LoginMessage;
import data.message.ResultMessage;

/**
 * ����һ���������·
 * Created by mist on 15-10-25.
 */
public interface UserBLService {

    public LoginMessage login(long id, String pswd);

    public ResultMessage modifyUser(long id, String pswd);

    public ResultMessage register(long staffSN);

    public LoginMessage loginWithMD5(long id, String pswd_md5);
}
