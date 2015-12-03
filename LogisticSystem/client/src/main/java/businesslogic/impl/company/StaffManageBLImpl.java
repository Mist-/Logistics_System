package businesslogic.impl.company;

import businesslogic.service.company.StaffManageBLService;
import data.enums.DataType;
import data.enums.POType;
import data.factory.DataServiceFactory;
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
        ArrayList<StaffVO> vlist = new ArrayList<StaffVO>();
        ArrayList<DataPO> plist = null;
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
                    staffVO.setPhoneNum(staffPO.getPhoneNum());
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
            staffVO.setPhoneNum(staffPO.getPhoneNum());
            return staffVO;
        }
        return null;
    }

    @Override
    public ResultMessage addStaff(StaffVO staffVO, long id) {
        try {
            staffPO = (StaffPO) company.search(POType.STAFF,id);
            if(staffPO==null){
                staffPO = new StaffPO();
                staffPO.setInstitution(staffVO.getInstitution());
                staffPO.setPhoneNum(staffVO.getPhoneNum());
                staffPO.setGender(staffVO.getGender());
                staffPO.setName(staffVO.getName());
                staffPO.setSerialNum(staffVO.getId());
                staffPO.setIdcardNum(staffVO.getIdcardNum());
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
            staffPO = (StaffPO) company.search(POType.STAFF, id);
            if (staffPO != null) {
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
    public ResultMessage moveStaff(long fromInstitution, long toInstitution, long id) {
        try {
            StaffPO staff = (StaffPO) company.search(POType.STAFF,id);
            resultMessage = this.deleteStaff(fromInstitution,id);
            if(resultMessage == ResultMessage.SUCCESS){
                staffVO = new StaffVO();
                staffVO.setId(staffPO.getSerialNum());
                staffVO.setInstitution(staffPO.getInstitution());
                staffVO.setGender(staffPO.getGender());
                staffVO.setPhoneNum(staffPO.getPhoneNum());
                staffVO.setIdcardNum(staffPO.getIdcardNum());
                staffVO.setName(staffPO.getName());
                resultMessage = this.addStaff(staffVO,staffVO.getId());
                return resultMessage;
            }
            else{
                return ResultMessage.FAILED;
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
        }
        return ResultMessage.FAILED;
    }

    @Override
    public void endstaffmanage() {
      //TODO
    }
}
