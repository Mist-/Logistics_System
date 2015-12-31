package businesslogic.impl.company;

import businesslogic.service.company.StaffManageBLService;
import data.enums.DataType;
import data.enums.POType;
import data.enums.UserRole;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.StaffPO;
import data.service.CompanyDataService;
import data.vo.StaffVO;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wyc on 2015/11/15.
 */
public class StaffManageBLImpl implements StaffManageBLService {

    private CompanyDataService company = null;
    private ResultMessage resultMessage = null;
    private StaffVO staffVO = null;
    private StaffPO staffPO = null;

    public StaffManageBLImpl(){
        company = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
    }

    @Override
    public ArrayList<StaffVO> getStaffByInstitution(long institution) {
        ArrayList<StaffVO> vlist = new ArrayList<>();
        ArrayList<DataPO> plist;
        try {
            plist = company.getPOList(POType.STAFF);
            for(int i=0;i<plist.size();i++){
                staffPO = (StaffPO) plist.get(i);
                if(staffPO.getInstitution()==institution){
                    staffVO = new StaffVO();
                    staffVO.setInstitution(staffPO.getInstitution());
                    staffVO.setId(staffPO.getSerialNum());
                    staffVO.setName(staffPO.getName());
                    staffVO.setGender(staffPO.getGender());
                    staffVO.setPhoneNum(staffPO.getPhoneNum()+"");
                    staffVO.setIdcardNum(staffPO.getIdcardNum());
                    staffVO.setUserRole(staffPO.getUserRole());
                    vlist.add(staffVO);
                }
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return vlist;
    }

    @Override
    public StaffVO getstaffByID(long id) {
        try {
            staffPO = (StaffPO) company.search(POType.STAFF,id);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        if(staffPO!=null){
            staffVO = new StaffVO();
            staffVO.setInstitution(staffPO.getInstitution());
            staffVO.setId(staffPO.getSerialNum());
            staffVO.setName(staffPO.getName());
            staffVO.setGender(staffPO.getGender());
            staffVO.setPhoneNum(staffPO.getPhoneNum()+"");
            return staffVO;
        }
        return null;
    }

    public StaffVO getStaff(long sn) {

        StaffPO staffPO = null;
        try {
            staffPO = (StaffPO) company.search(POType.STAFF,staffVO.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        StaffVO staffVO = new StaffVO();
        staffVO.setName(staffPO.getName());
        staffVO.setUserRole(staffPO.getUserRole());
        return staffVO;
    }

    @Override
    public ResultMessage addStaff(StaffVO staffVO) {
        try {
            staffPO = (StaffPO) company.search(POType.STAFF,staffVO.getId());
            if(staffPO==null){
                staffPO = new StaffPO();
                staffPO.setInstitution(staffVO.getInstitution());
                staffPO.setPhoneNum(Long.valueOf(staffVO.getPhoneNum()));
                staffPO.setGender(staffVO.getGender());
                staffPO.setName(staffVO.getName());
                staffPO.setSerialNum(staffVO.getId());
                staffPO.setIdcardNum(staffVO.getIdcardNum());
                staffPO.setUserRole(staffVO.getUserRole());
                resultMessage = company.add(staffPO);
                return  resultMessage;
            }
            else{
                return  ResultMessage.EXIST;
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return ResultMessage.FAILED;

    }

    @Override
    public ResultMessage deleteStaff(long institution, long id) {
        try {
            staffPO = (StaffPO) company.search(POType.STAFF,staffVO.getId());
            if(staffPO==null){
                resultMessage = company.delete(staffPO);
                return resultMessage;
            } else {
                return ResultMessage.NOTEXIST;
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }

        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage moveStaff(long fromInstitution, long toInstitution, long id, UserRole userRole) {
        try {
            StaffPO staff = (StaffPO) company.search(POType.STAFF,id);
            resultMessage = this.deleteStaff(fromInstitution,id);
            if(resultMessage == ResultMessage.SUCCESS){
                staffVO = new StaffVO();
                staffVO.setId(staff.getSerialNum());
                staffVO.setInstitution(toInstitution);
                staffVO.setGender(staff.getGender());
                staffVO.setPhoneNum(staff.getPhoneNum()+"");
                staffVO.setIdcardNum(staff.getIdcardNum());
                staffVO.setName(staff.getName());
                staffVO.setUserRole(userRole);
                resultMessage = this.addStaff(staffVO);
                return resultMessage;
            }
                return ResultMessage.SUCCESS;

        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        return ResultMessage.NOTCONNECTED;
    }

}
