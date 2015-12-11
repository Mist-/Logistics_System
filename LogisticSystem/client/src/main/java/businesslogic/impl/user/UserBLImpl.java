package businesslogic.impl.user;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import businesslogic.service.user.UserBLService;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.UserPO;
import data.service.UserDataService;
import data.vo.UserVO;
import utils.PasswordHelper;

/**
 *
 * Created by mist on 2015/11/13 0013.
 */
public class UserBLImpl implements UserBLService {

    UserDataService userDataService = null;

    public UserBLImpl() {
        userDataService = (UserDataService) DataServiceFactory.getDataServiceByType(DataType.UserDataService);
    }

    @Override
    public LoginMessage login(long id, String pswd) {

        if (userDataService == null) userDataService = (UserDataService) DataServiceFactory.getDataServiceByType(DataType.UserDataService);
        if (userDataService == null) return new LoginMessage(ResultMessage.NOTCONNECTED);

    	UserPO userPO = null;
  
    	try {
			userPO = (UserPO) userDataService.search(POType.USER, id);
		} catch (RemoteException e) {
            System.err.println("网络连接失败，无法登陆");
            return new LoginMessage(ResultMessage.NOTCONNECTED);
		}
    	
    	if (userPO == null || userPO.isDeleted()) {
    		return new LoginMessage(ResultMessage.NOTEXIST);
    	}
    	
    	String pswd_md5 = PasswordHelper.generateMD5Checksum(pswd);
    	if (!userPO.getPassword().equals(pswd_md5)) {
    		return new LoginMessage(ResultMessage.FAILED);
    	}

        try {
            userPO = (UserPO) userDataService.search(POType.USER, id);
        } catch (RemoteException e) {
            System.err.println("网络连接失败");
            return new LoginMessage(ResultMessage.NOTCONNECTED);
        }

        return new LoginMessage(ResultMessage.SUCCESS, id, userPO.getRole());
    }

    public ArrayList<UserVO> getDisplayData() {

        ArrayList<UserVO> result = new ArrayList<>();
        ArrayList<DataPO> staffInfo = null;
        try {
            staffInfo = userDataService.getPOList(POType.USER);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (staffInfo == null) return null;
        for (DataPO data: staffInfo) {
            result.add(new UserVO((UserPO)data));
        }
        return result;
    }

    @Override
    public ResultMessage modifyUser(long id, String pswd) {

        if (userDataService == null) userDataService = (UserDataService) DataServiceFactory.getDataServiceByType(DataType.UserDataService);
        if (userDataService == null) return ResultMessage.NOTCONNECTED;

        UserPO userPO = null;

        try {
            userPO = (UserPO)userDataService.search(POType.USER, id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NOTCONNECTED;
        }

        if (userPO == null) {
            return ResultMessage.NOTEXIST;
        }

        userPO.setPassword(pswd);

        try {
            userDataService.modify(userPO);
        } catch (RemoteException e) {
            System.err.println("网络连接失败，无法修改用户信息");
            return ResultMessage.NOTCONNECTED;
        }

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage register(long staffSN) {

        if (userDataService == null) userDataService = (UserDataService) DataServiceFactory.getDataServiceByType(DataType.UserDataService);
        if (userDataService == null) return ResultMessage.NOTCONNECTED;

        UserPO userPO = null;
        try {
            userPO = (UserPO) userDataService.search(POType.USER, staffSN);
        } catch (RemoteException e) {
            System.err.println("无法连接网络，查询员工信息失败 - " + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }
        if (userPO == null) {
            return ResultMessage.NOTEXIST;
        }

        UserPO user = new UserPO(userPO.getStaffsn(), userPO.getName(), "123456", userPO.getRole());
        try {
            userDataService.add(user);
        } catch (RemoteException e) {
            System.err.println("无法连接网络，添加用户信息失败 - " + Calendar.getInstance().getTime());
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public LoginMessage loginWithMD5(long id, String pswd_md5) {

        if (userDataService == null) userDataService = (UserDataService) DataServiceFactory.getDataServiceByType(DataType.UserDataService);
        if (userDataService == null) return new LoginMessage(ResultMessage.NOTCONNECTED);

        UserPO staffPO = null;

        try {
            staffPO = (UserPO) userDataService.search(POType.USER, id);
        } catch (RemoteException e) {
            System.err.println("网络连接失败，无法登陆（通过已保存的密码）");
            return new LoginMessage(ResultMessage.NOTCONNECTED);
        }

        if (staffPO == null || staffPO.isDeleted()) {
            return new LoginMessage(ResultMessage.NOTEXIST);
        }

        if (!staffPO.getPassword().equals(pswd_md5)) {
            return new LoginMessage(ResultMessage.FAILED);
        }

        return new LoginMessage(ResultMessage.SUCCESS, id, staffPO.getRole());
    }

    @Override
    public ResultMessage deleteUser(long id) {
        ArrayList<DataPO> users = null;
        try {
            users = userDataService.getPOList(POType.USER);
        } catch (RemoteException e) {
            System.err.println("网络连接失败，无法获取用户数据 - " + Calendar.getInstance().getTime());
            return ResultMessage.NOTCONNECTED;
        }
        UserPO userToDelete = null;
        for (DataPO data: users) {
            if (data.getSerialNum() == id) {
                userToDelete = (UserPO) data;
                userToDelete.setDeleted(true);
                try {
                    userDataService.modify(userToDelete);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        return ResultMessage.SUCCESS;
    }

    public ResultMessage revertUser(long id) {
        ArrayList<DataPO> users = null;
        try {
            users = userDataService.getPOList(POType.USER);
        } catch (RemoteException e) {
            System.err.println("网络连接失败，无法获取用户数据 - " + Calendar.getInstance().getTime());
            return ResultMessage.NOTCONNECTED;
        }
        UserPO userToRevert = null;
        for (DataPO data: users) {
            if (((UserPO) data).getSerialNum() == id) {
                userToRevert = (UserPO) data;
                userToRevert.setDeleted(false);
                try {
                    userDataService.modify(userToRevert);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        return ResultMessage.SUCCESS;
    }
}
