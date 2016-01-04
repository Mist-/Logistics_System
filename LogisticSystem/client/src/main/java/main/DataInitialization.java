package main;

import data.enums.DataType;
import data.enums.POType;
import data.enums.UserRole;
import data.po.*;
import utils.DataServiceFactory;
import data.service.CompanyDataService;

import java.rmi.RemoteException;

/**
 *
 * Created by mist on 2015/12/5 0005.
 */
public class DataInitialization {
    public static void main(String[] args) {
        CityInfoPO[] city = new CityInfoPO[4];
        city[0] = new CityInfoPO(1025);
        city[0].setName("�Ͼ���");
        city[0].addBusinessOffice(1025001);
        city[0].addBusinessOffice(1025002);
        city[0].addBusinessOffice(1025003);
        city[0].addBusinessOffice(1025004);
        city[0].setTransferCenter(1025);
        city[1] = new CityInfoPO(1021);
        city[1].setName("�Ϻ���");
        city[1].addBusinessOffice(1021001);
        city[1].addBusinessOffice(1021002);
        city[1].addBusinessOffice(1021003);
        city[1].addBusinessOffice(1021004);
        city[1].setTransferCenter(1021);
        city[2] = new CityInfoPO(1010);
        city[2].setName("������");
        city[2].addBusinessOffice(1010001);
        city[2].addBusinessOffice(1010002);
        city[2].addBusinessOffice(1010003);
        city[2].addBusinessOffice(1010004);
        city[2].setTransferCenter(1010);
        city[3] = new CityInfoPO(1020);
        city[3].setName("������");
        city[3].addBusinessOffice(1020001);
        city[3].addBusinessOffice(1020002);
        city[3].addBusinessOffice(1020003);
        city[3].addBusinessOffice(1020004);
        city[3].setTransferCenter(10020);


        InstitutionPO[] institution = new InstitutionPO[24];

        // �Ͼ�
        institution[0] = new InstitutionPO(1025001);
        institution[0].setName("��¥��");
        institution[0].setTargetCenter(1025);
        institution[1] = new InstitutionPO(1025002);
        institution[1].setName("��ϼ��");
        institution[1].setTargetCenter(1025);
        institution[2] = new InstitutionPO(1025003);
        institution[2].setName("������");
        institution[2].setTargetCenter(1025);
        institution[3] = new InstitutionPO(1025004);
        institution[3].setName("�ػ���");
        institution[3].setTargetCenter(1025);
        institution[4] = new InstitutionPO(1025);
        institution[4].setName("�Ͼ���");

        // ����
        institution[5] = new InstitutionPO(1010001);
        institution[5].setName("������");
        institution[5].setTargetCenter(1010);
        institution[6] = new InstitutionPO(1010002);
        institution[6].setName("������");
        institution[6].setTargetCenter(1010);
        institution[7] = new InstitutionPO(1010003);
        institution[7].setName("������");
        institution[7].setTargetCenter(1010);
        institution[8] = new InstitutionPO(1010004);
        institution[8].setName("������");
        institution[8].setTargetCenter(1010);
        institution[9] = new InstitutionPO(1010);
        institution[9].setName("������");

        // �Ϻ�
        institution[10] = new InstitutionPO(1021001);
        institution[10].setName("������");
        institution[10].setTargetCenter(1021);
        institution[11] = new InstitutionPO(1021002);
        institution[11].setName("������");
        institution[11].setTargetCenter(1021);
        institution[12] = new InstitutionPO(1021003);
        institution[12].setName("������");
        institution[12].setTargetCenter(1021);
        institution[13] = new InstitutionPO(1021004);
        institution[13].setName("�ζ���");
        institution[13].setTargetCenter(1021);
        institution[14] = new InstitutionPO(1021);
        institution[14].setName("�Ϻ���");

        // ����
        institution[15] = new InstitutionPO(1020001);
        institution[15].setName("������");
        institution[15].setTargetCenter(1020);
        institution[16] = new InstitutionPO(1020002);
        institution[16].setName("�ܸ���");
        institution[16].setTargetCenter(1020);
        institution[17] = new InstitutionPO(1020003);
        institution[17].setName("�����");
        institution[17].setTargetCenter(1020);
        institution[18] = new InstitutionPO(1020004);
        institution[18].setName("������");
        institution[18].setTargetCenter(1020);
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
        staff1.setName("���");
        staff1.setGender(false);
        staff1.setInstitution(1021001);
        staff1.setUserRole(UserRole.���Ա);

        StaffPO staff2 = new StaffPO();
        staff2.setSerialNum(10001);
        staff2.setName("�����");
        staff2.setGender(false);
        staff2.setInstitution(1021001);
        staff2.setUserRole(UserRole.Ӫҵ��ҵ��Ա);

        StaffPO staff8 = new StaffPO();
        staff8.setSerialNum(10001);
        staff8.setName("���");
        staff8.setGender(false);
        staff8.setInstitution(1021003);
        staff8.setUserRole(UserRole.Ӫҵ��ҵ��Ա);

        StaffPO staff9 = new StaffPO();
        staff9.setSerialNum(10008);
        staff9.setName("���");
        staff9.setGender(false);
        staff9.setInstitution(1025001);
        staff9.setUserRole(UserRole.Ӫҵ��ҵ��Ա);
        
        UserPO user9 = new UserPO(10008, "sb", "123456", UserRole.Ӫҵ��ҵ��Ա);
        try {
			DataServiceFactory.getDataServiceByPO(POType.USER).add(user9);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        StaffPO staff3 = new StaffPO();
        staff3.setSerialNum(10002);
        staff3.setName("����");
        staff3.setGender(false);
        staff3.setInstitution(1021);
        staff3.setUserRole(UserRole.��ת����ҵ��Ա);

        StaffPO staff4 = new StaffPO();
        staff4.setSerialNum(10003);
        staff4.setName("�����");
        staff4.setGender(false);
        staff4.setInstitution(1021);
        staff4.setUserRole(UserRole.�ֿ����Ա);

        StaffPO staff5 = new StaffPO();
        staff5.setSerialNum(10004);
        staff5.setName("����");
        staff5.setGender(false);
        staff5.setInstitution(1021);
        staff5.setUserRole(UserRole.��ͨ������Ա);

        StaffPO staff6 = new StaffPO();
        staff6.setSerialNum(10005);
        staff6.setName("�����");
        staff6.setGender(false);
        staff6.setInstitution(1021);
        staff6.setUserRole(UserRole.�ܾ���);

        StaffPO staff7 = new StaffPO();
        staff7.setSerialNum(10006);
        staff7.setName("�����");
        staff7.setGender(false);
        staff7.setInstitution(1021);
        staff7.setUserRole(UserRole.ϵͳ����Ա);
        
        StaffPO staff10 = new StaffPO();
        staff10.setSerialNum(10010);
        staff10.setName("������");
        staff10.setGender(false);
        staff10.setInstitution(1025);
        staff10.setUserRole(UserRole.��ת����ҵ��Ա);
        
        StaffPO staff11 = new StaffPO();
        staff11.setSerialNum(10011);
        staff11.setName("������");
        staff11.setGender(false);
        staff11.setInstitution(1025);
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
            
            UserPO user10 = new UserPO(10010, "��ת����ҵ��Ա", "123456", UserRole.��ת����ҵ��Ա);
            UserPO user11 = new UserPO(10011, "��ת����ҵ��Ա", "123456", UserRole.�ֿ����Ա);
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

        CityTransPO bs = new CityTransPO("������", "�Ϻ���", 1064.7, 10, 20, 30);
        CityTransPO sb = new CityTransPO("�Ϻ���", "������", 1064.7, 10, 20, 30);
        CityTransPO bg = new CityTransPO("������", "������", 1888.8, 10, 20, 30);
        CityTransPO gb = new CityTransPO("������", "������", 1888.8, 10, 20, 30);
        CityTransPO bn = new CityTransPO("������", "�Ͼ���", 900, 10, 20, 30);
        CityTransPO nb = new CityTransPO("�Ͼ���", "������", 900, 10, 20, 30);
        CityTransPO sg = new CityTransPO("�Ϻ���", "������", 1213, 10, 20, 30);
        CityTransPO gs = new CityTransPO("������", "�Ϻ���", 1213, 10, 20, 30);
        CityTransPO sn = new CityTransPO("�Ϻ���", "�Ͼ���", 266, 10, 20, 30);
        CityTransPO ns = new CityTransPO("�Ͼ���", "�Ϻ���", 266, 10, 20, 30);
        CityTransPO gn = new CityTransPO("������", "�Ͼ���", 1132, 10, 20, 30);
        CityTransPO ng = new CityTransPO("�Ͼ���", "������", 1132, 10, 20, 30);

        try {
            companyDataService.add(bs);
            companyDataService.add(sb);
            companyDataService.add(bg);
            companyDataService.add(gb);
            companyDataService.add(bn);
            companyDataService.add(nb);
            companyDataService.add(sg);
            companyDataService.add(gs);
            companyDataService.add(sn);
            companyDataService.add(ns);
            companyDataService.add(gn);
            companyDataService.add(ng);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
