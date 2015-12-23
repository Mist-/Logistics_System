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
        city[0].setName("�Ͼ���");
        city[0].addBusinessOffice(1002500001);
        city[0].addBusinessOffice(1002500002);
        city[0].addBusinessOffice(1002500003);
        city[0].addBusinessOffice(1002500004);
        city[0].setTransferCenter(10025);
        city[1] = new CityInfoPO(021);
        city[1].setName("�Ϻ���");
        city[1].addBusinessOffice(1002100001);
        city[1].addBusinessOffice(1002100002);
        city[1].addBusinessOffice(1002100003);
        city[1].addBusinessOffice(1002100004);
        city[1].setTransferCenter(10021);
        city[2] = new CityInfoPO(010);
        city[2].setName("������");
        city[2].addBusinessOffice(1001000001);
        city[2].addBusinessOffice(1001000002);
        city[2].addBusinessOffice(1001000003);
        city[2].addBusinessOffice(1001000004);
        city[2].setTransferCenter(10010);
        city[3] = new CityInfoPO(020);
        city[3].setName("������");
        city[3].addBusinessOffice(1002000001);
        city[3].addBusinessOffice(1002000002);
        city[3].addBusinessOffice(1002000003);
        city[3].addBusinessOffice(1002000004);
        city[3].setTransferCenter(10020);


        InstitutionPO[] institution = new InstitutionPO[24];

        // �Ͼ�
        institution[0] = new InstitutionPO(1002500001);
        institution[0].setName("��¥��");
        institution[0].setTargetCenter(10025);
        institution[1] = new InstitutionPO(1002500002);
        institution[1].setName("��ϼ��");
        institution[1].setTargetCenter(10025);
        institution[2] = new InstitutionPO(1002500003);
        institution[2].setName("������");
        institution[2].setTargetCenter(10025);
        institution[3] = new InstitutionPO(1002500004);
        institution[3].setName("�ػ���");
        institution[3].setTargetCenter(10025);
        institution[4] = new InstitutionPO(10025);
        institution[4].setName("�Ͼ���");

        // ����
        institution[5] = new InstitutionPO(1001000001);
        institution[5].setName("������");
        institution[5].setTargetCenter(10010);
        institution[6] = new InstitutionPO(1001000002);
        institution[6].setName("������");
        institution[6].setTargetCenter(10010);
        institution[7] = new InstitutionPO(1001000003);
        institution[7].setName("������");
        institution[7].setTargetCenter(10010);
        institution[8] = new InstitutionPO(1001000004);
        institution[8].setName("������");
        institution[8].setTargetCenter(10010);
        institution[9] = new InstitutionPO(10010);
        institution[9].setName("������");

        // �Ϻ�
        institution[10] = new InstitutionPO(1002100001);
        institution[10].setName("������");
        institution[10].setTargetCenter(10021);
        institution[11] = new InstitutionPO(1002100002);
        institution[11].setName("������");
        institution[11].setTargetCenter(10021);
        institution[12] = new InstitutionPO(1002100003);
        institution[12].setName("������");
        institution[12].setTargetCenter(10021);
        institution[13] = new InstitutionPO(1002100004);
        institution[13].setName("�ζ���");
        institution[13].setTargetCenter(10021);
        institution[14] = new InstitutionPO(10021);
        institution[14].setName("�Ϻ���");

        // ����
        institution[15] = new InstitutionPO(1002000001);
        institution[15].setName("������");
        institution[15].setTargetCenter(10020);
        institution[16] = new InstitutionPO(1002000002);
        institution[16].setName("�ܸ���");
        institution[16].setTargetCenter(10020);
        institution[17] = new InstitutionPO(1002000003);
        institution[17].setName("�����");
        institution[17].setTargetCenter(10020);
        institution[18] = new InstitutionPO(1002000004);
        institution[18].setName("������");
        institution[18].setTargetCenter(10020);
        institution[19] = new InstitutionPO(10020);
        institution[19].setName("������");

        //
        institution[20] = new InstitutionPO(11111);
        institution[20].setName("���Ա");
        institution[21] = new InstitutionPO(22222);
        institution[21].setName("��ͨ������Ա");
        institution[22] = new InstitutionPO(33333);
        institution[22].setName("������ʻԱ");
        institution[23] = new InstitutionPO(44444);
        institution[23].setName("�߼�������Ա");

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
        staff1.setName("�����");
        staff1.setGender(false);
        staff1.setInstitution(1002100001);
        staff1.setUserRole(UserRole.���Ա);

        StaffPO staff2 = new StaffPO();
        staff2.setSerialNum(10001);
        staff2.setName("�����");
        staff2.setGender(false);
        staff2.setInstitution(1002100001);
        staff2.setUserRole(UserRole.Ӫҵ��ҵ��Ա);

        StaffPO staff8 = new StaffPO();
        staff8.setSerialNum(10001);
        staff8.setName("�����");
        staff8.setGender(false);
        staff8.setInstitution(1002100003);
        staff8.setUserRole(UserRole.Ӫҵ��ҵ��Ա);

        StaffPO staff9 = new StaffPO();
        staff9.setSerialNum(10008);
        staff9.setName("�����");
        staff9.setGender(false);
        staff9.setInstitution(1002100003);
        staff9.setUserRole(UserRole.Ӫҵ��ҵ��Ա);

        StaffPO staff3 = new StaffPO();
        staff3.setSerialNum(10002);
        staff3.setName("�����");
        staff3.setGender(false);
        staff3.setInstitution(10021);
        staff3.setUserRole(UserRole.��ת����ҵ��Ա);

        StaffPO staff4 = new StaffPO();
        staff4.setSerialNum(10003);
        staff4.setName("�����");
        staff4.setGender(false);
        staff4.setInstitution(10021);
        staff4.setUserRole(UserRole.�ֿ����Ա);

        StaffPO staff5 = new StaffPO();
        staff5.setSerialNum(10004);
        staff5.setName("�����");
        staff5.setGender(false);
        staff5.setInstitution(10021);
        staff5.setUserRole(UserRole.��ͨ������Ա);

        StaffPO staff6 = new StaffPO();
        staff6.setSerialNum(10005);
        staff6.setName("�����");
        staff6.setGender(false);
        staff6.setInstitution(10021);
        staff6.setUserRole(UserRole.�ܾ���);

        StaffPO staff7 = new StaffPO();
        staff7.setSerialNum(10006);
        staff7.setName("�����");
        staff7.setGender(false);
        staff7.setInstitution(10021);
        staff7.setUserRole(UserRole.ϵͳ����Ա);
        
        StaffPO staff10 = new StaffPO();
        staff10.setSerialNum(10010);
        staff10.setName("����");
        staff10.setGender(false);
        staff10.setInstitution(10025);
        staff10.setUserRole(UserRole.��ת����ҵ��Ա);
        
        StaffPO staff11 = new StaffPO();
        staff11.setSerialNum(10011);
        staff11.setName("����ɵ��");
        staff11.setGender(false);
        staff11.setInstitution(10025);
        staff11.setUserRole(UserRole.�ֿ����Ա);
        

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
            
            UserPO user10 = new UserPO(10010, "��ת����ҵ��Ա", "123456"	, UserRole.��ת����ҵ��Ա);
            UserPO user11 = new UserPO(10011, "��ת����ҵ��Ա", "123456"	, UserRole.�ֿ����Ա);
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
