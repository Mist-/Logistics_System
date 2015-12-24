package presentation.company;

import data.message.ResultMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by wyc on 2015/12/24.
 */
public class DialogDeleteStaff extends JDialog{

    //������Ҫ�����
    JDialog jDialog = null;
    JPanel jPanel = null;
    JButton buttonEnsure = null;
    JButton buttonExit = null;
    JLabel jLabel = null;

    public DialogDeleteStaff(final companyManage company, final String institution, final String id, String name){
        jDialog = new JDialog(company,"ɾ��Ա��");
        jPanel = new JPanel();
        buttonEnsure = new JButton("ȷ��");
        buttonExit = new JButton("ȡ��");
        jLabel = new JLabel("",JLabel.CENTER);
        jLabel.setText("�Ƿ�ȷ��ɾ�� "+name+" ?");
        jLabel.setBounds(0,10,300,40);
        buttonEnsure.setBounds(60,80,55,35);
        buttonExit.setBounds(160,80,55,35);
        //�������ӵ�jDialog��
        jDialog.setSize(300,200);
        jPanel.setLayout(null);
        jDialog.add(jPanel, BorderLayout.CENTER);
        jPanel.add(jLabel);
        jPanel.add(buttonEnsure);
        jPanel.add(buttonExit);
        jDialog.setModal(true);
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(null);
        buttonEnsure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               company.buttonEnsureDeleteStaff(institution,id,e);
                jDialog.dispose();
            }
        });
        buttonExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jDialog.dispose();
            }
        });
        jDialog.setVisible(true);
    }
}
