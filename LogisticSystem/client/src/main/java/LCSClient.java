import javax.swing.*;

import data.enums.DataType;
import data.enums.UserRole;

import data.factory.DataServiceFactory;
import data.po.DataPO;
import data.po.UserPO;
import data.service.DataService;
import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import data.message.LoginMessage;
import data.message.ResultMessage;
import presentation.company.companyManage;
import presentation.order.OrderUI;
import presentation.order.SimplifiedOrderUI;
import presentation.storage.StorageFrame;
import presentation.transfer.center.TransferCenterFrame;
import presentation.user.login.LoginDlg;
import presentation.user.userMngUI.UserMngUI;
import utils.Connection;

import java.rmi.RemoteException;


/**
 *
 * Created by mist on 2015/11/13 0013.
 */
public class LCSClient extends JFrame{

    public static void main(String[] args) {

        // ���ݳ�ʼ����������


        try {
			UIManager.setLookAndFeel(new Windows2LookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // ����һ�����Ӽ���߳�
        Connection.startConnectionCheck();

        // һ��Ĭ�ϵĽ����Ƕ��������棬��ΪloginDlg��owner
        SimplifiedOrderUI simplifiedOrderUI = new SimplifiedOrderUI();


		LoginMessage loginMessage = new LoginMessage(ResultMessage.FAILED);

        // ��ʾ��¼����
		LoginDlg loginDlg = new LoginDlg(simplifiedOrderUI, loginMessage);
        loginDlg.setVisible(true);

        // �����ǣ���¼������ɺ�Ľ�����ת��
        if (loginMessage.getResult() == ResultMessage.FAILED || loginMessage.getResult() == ResultMessage.NOTEXIST) System.exit(1);
        else {
            if (loginMessage.getUserSN() == 0) {                        // �����û������붩����ѯ����
                simplifiedOrderUI.setVisible(true);
            }
            if (loginMessage.getUserRole() == UserRole.���Ա) {       // ���Ա��¼
                OrderUI orderUI = new OrderUI(loginMessage);
                orderUI.setVisible(true);
            } else
            if (loginMessage.getUserRole() == UserRole.�ֿ����Ա) {   // �ֿ����Ա��¼
                StorageFrame storageUI = new StorageFrame(loginMessage);
                storageUI.setVisible(true);
            } else
            if (loginMessage.getUserRole() == UserRole.Ӫҵ��ҵ��Ա) {      // Ӫҵ��ҵ��Ա��¼
                TransferCenterFrame transferUI = new TransferCenterFrame(loginMessage);
                transferUI.setVisible(true);
            } else
            if (loginMessage.getUserRole() == UserRole.ϵͳ����Ա) {     // ϵͳ����Ա
                UserMngUI userMngUI = new UserMngUI();
                userMngUI.setVisible(true);
            }
            if (loginMessage.getUserRole() == UserRole.�ܾ���) {      // �ܾ������
                companyManage companyUI = new companyManage();
                companyUI.setVisible(true);
            }
        }
    }
}
