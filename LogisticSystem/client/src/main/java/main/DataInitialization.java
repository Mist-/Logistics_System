package main;

import data.enums.DataType;
import data.enums.UserRole;
import utils.DataServiceFactory;
import data.po.CityInfoPO;
import data.po.InstitutionPO;
import data.po.StaffPO;
import data.po.UserPO;
import data.service.CompanyDataService;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/5 0005.
 */
public class DataInitialization {
    public static void main(String[] args) {
        CityInfoPO[] city = new CityInfoPO[4];
        city[0] = new CityInfoPO(025);
        city[0].setName("南京市");
        city[0].addBusinessOffice(1002500001);
        city[0].addBusinessOffice(1002500002);
        city[0].addBusinessOffice(1002500003);
        city[0].addBusinessOffice(1002500004);
        city[0].setTransferCenter(10025);
        city[1] = new CityInfoPO(021);
        city[1].setName("上海市");
        city[1].addBusinessOffice(1002100001);
        city[1].addBusinessOffice(1002100002);
        city[1].addBusinessOffice(1002100003);
        city[1].addBusinessOffice(1002100004);
        city[1].setTransferCenter(10021);
        city[2] = new CityInfoPO(010);
        city[2].setName("北京市");
        city[2].addBusinessOffice(1001000001);
        city[2].addBusinessOffice(1001000002);
        city[2].addBusinessOffice(1001000003);
        city[2].addBusinessOffice(1001000004);
        city[2].setTransferCenter(10010);
        city[3] = new CityInfoPO(020);
        city[3].setName("广州市");
        city[3].addBusinessOffice(1002000001);
        city[3].addBusinessOffice(1002000002);
        city[3].addBusinessOffice(1002000003);
        city[3].addBusinessOffice(1002000004);
        city[3].setTransferCenter(10020);


        InstitutionPO[] institution = new InstitutionPO[24];

        // 南京
        institution[0] = new InstitutionPO(1002500001);
        institution[0].setName("鼓楼区");
        institution[0].setTargetCenter(10025);
        institution[1] = new InstitutionPO(1002500002);
        institution[1].setName("栖霞区");
        institution[1].setTargetCenter(10025);
        institution[2] = new InstitutionPO(1002500003);
        institution[2].setName("玄武区");
        institution[2].setTargetCenter(10025);
        institution[3] = new InstitutionPO(1002500004);
        institution[3].setName("秦淮区");
        institution[3].setTargetCenter(10025);
        institution[4] = new InstitutionPO(10025);
        institution[4].setName("南京市");

        // 北京
        institution[5] = new InstitutionPO(1001000001);
        institution[5].setName("东城区");
        institution[5].setTargetCenter(10010);
        institution[6] = new InstitutionPO(1001000002);
        institution[6].setName("西城区");
        institution[6].setTargetCenter(10010);
        institution[7] = new InstitutionPO(1001000003);
        institution[7].setName("海淀区");
        institution[7].setTargetCenter(10010);
        institution[8] = new InstitutionPO(1001000004);
        institution[8].setName("朝阳区");
        institution[8].setTargetCenter(10010);
        institution[9] = new InstitutionPO(10010);
        institution[9].setName("北京市");

        // 上海
        institution[10] = new InstitutionPO(1002100001);
        institution[10].setName("闵行区");
        institution[10].setTargetCenter(10021);
        institution[11] = new InstitutionPO(1002100002);
        institution[11].setName("青浦区");
        institution[11].setTargetCenter(10021);
        institution[12] = new InstitutionPO(1002100003);
        institution[12].setName("黄浦区");
        institution[12].setTargetCenter(10021);
        institution[13] = new InstitutionPO(1002100004);
        institution[13].setName("嘉定区");
        institution[13].setTargetCenter(10021);
        institution[14] = new InstitutionPO(10021);
        institution[14].setName("上海市");

        // 广州
        institution[15] = new InstitutionPO(1002000001);
        institution[15].setName("番禹区");
        institution[15].setTargetCenter(10020);
        institution[16] = new InstitutionPO(1002000002);
        institution[16].setName("萝岗区");
        institution[16].setTargetCenter(10020);
        institution[17] = new InstitutionPO(1002000003);
        institution[17].setName("天河区");
        institution[17].setTargetCenter(10020);
        institution[18] = new InstitutionPO(1002000004);
        institution[18].setName("黄埔区");
        institution[18].setTargetCenter(10020);
        institution[19] = new InstitutionPO(10020);
        institution[19].setName("北京市");

        //
        institution[20] = new InstitutionPO(11111);
        institution[20].setName("快递员");
        institution[21] = new InstitutionPO(22222);
        institution[21].setName("普通财务人员");
        institution[22] = new InstitutionPO(33333);
        institution[22].setName("货车驾驶员");
        institution[23] = new InstitutionPO(44444);
        institution[23].setName("高级财务人员");

        CompanyDataService companyDataService = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);

        try {
            for (CityInfoPO cityInfoPO: city) {
                companyDataService.add(cityInfoPO);
            }
            for (InstitutionPO institutionPO: institution) {
                companyDataService.add(institutionPO);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        StaffPO staff1 = new StaffPO();
        staff1.setSerialNum(10000);
        staff1.setName("孙九日");
        staff1.setGender(false);
        staff1.setInstitution(1002100001);
        staff1.setUserRole(UserRole.快递员);

        StaffPO staff2 = new StaffPO();
        staff2.setSerialNum(10001);
        staff2.setName("孙九日");
        staff2.setGender(false);
        staff2.setInstitution(1002100001);
        staff2.setUserRole(UserRole.营业厅业务员);

        StaffPO staff8 = new StaffPO();
        staff8.setSerialNum(10001);
        staff8.setName("孙九日");
        staff8.setGender(false);
        staff8.setInstitution(1002100003);
        staff8.setUserRole(UserRole.营业厅业务员);

        StaffPO staff9 = new StaffPO();
        staff9.setSerialNum(10008);
        staff9.setName("孙九日");
        staff9.setGender(false);
        staff9.setInstitution(1002100003);
        staff9.setUserRole(UserRole.营业厅业务员);

        StaffPO staff3 = new StaffPO();
        staff3.setSerialNum(10002);
        staff3.setName("孙九日");
        staff3.setGender(false);
        staff3.setInstitution(10021);
        staff3.setUserRole(UserRole.中转中心业务员);

        StaffPO staff4 = new StaffPO();
        staff4.setSerialNum(10003);
        staff4.setName("孙九日");
        staff4.setGender(false);
        staff4.setInstitution(10021);
        staff4.setUserRole(UserRole.仓库管理员);

        StaffPO staff5 = new StaffPO();
        staff5.setSerialNum(10004);
        staff5.setName("孙九日");
        staff5.setGender(false);
        staff5.setInstitution(10021);
        staff5.setUserRole(UserRole.普通财务人员);

        StaffPO staff6 = new StaffPO();
        staff6.setSerialNum(10005);
        staff6.setName("孙九日");
        staff6.setGender(false);
        staff6.setInstitution(10021);
        staff6.setUserRole(UserRole.总经理);

        StaffPO staff7 = new StaffPO();
        staff7.setSerialNum(10006);
        staff7.setName("孙九日");
        staff7.setGender(false);
        staff7.setInstitution(10021);
        staff7.setUserRole(UserRole.系统管理员);
        
        StaffPO staff10 = new StaffPO();
        staff10.setSerialNum(10010);
        staff10.setName("基佬");
        staff10.setGender(false);
        staff10.setInstitution(10025);
        staff10.setUserRole(UserRole.中转中心业务员);
        
        StaffPO staff11 = new StaffPO();
        staff11.setSerialNum(10011);
        staff11.setName("我是傻逼");
        staff11.setGender(false);
        staff11.setInstitution(10025);
        staff11.setUserRole(UserRole.仓库管理员);
        

        try {
            companyDataService.add(staff1);
            companyDataService.add(staff2);
            companyDataService.add(staff3);
            companyDataService.add(staff4);
            companyDataService.add(staff5);
            companyDataService.add(staff6);
            companyDataService.add(staff7);
            companyDataService.add(staff8);
            companyDataService.add(staff9);
            companyDataService.add(staff10);
            companyDataService.add(staff11);
            
            UserPO user10 = new UserPO(10010, "中转中心业务员", "123456"	, UserRole.中转中心业务员);
            UserPO user11 = new UserPO(10011, "中转中心业务员", "123456"	, UserRole.仓库管理员);
            DataServiceFactory.getDataServiceByType(DataType.UserDataService).add(user10);
            DataServiceFactory.getDataServiceByType(DataType.UserDataService).add(user11);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (UserRole role: UserRole.values()) {
            UserPO user = new UserPO(10000 + i++, role.name(), "123456", role);
            try {
                DataServiceFactory.getDataServiceByType(DataType.UserDataService).add(user);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
      
    }
}
