package presentation.company;

import data.enums.POType;
import data.enums.UserRole;
import data.factory.DataServiceFactory;
import data.po.StaffPO;
import data.service.CompanyDataService;

import java.rmi.RemoteException;

/**
 * Created by apple on 2015/12/3.
 */
public class AddManager {
    public void add(){
        CompanyDataService c = (CompanyDataService) DataServiceFactory.getDataServiceByPO(POType.STAFF);
        StaffPO staffPO = new StaffPO();
        staffPO.setSerialNum(10004);
        staffPO.setUserRole(UserRole.×Ü¾­Àí);
        try {
            c.add(staffPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
