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

    //定义需要的组件
    JDialog jDialog = null;
    JPanel jPanel = null;
    JButton buttonEnsure = null;
    JButton buttonExit = null;
    JLabel jLabel = null;

    public DialogDeleteStaff(final companyManage company, final String institution, final String id, String name){
        jDialog = new JDialog(company,"删除员工");
        jPanel = new JPanel();
        buttonEnsure = new JButton("确认");
        buttonExit = new JButton("取消");
        jLabel = new JLabel("",JLabel.CENTER);
        jLabel.setText("是否确认删除 "+name+" ?");
        jLabel.setBounds(0,10,300,40);
        buttonEnsure.setBounds(60,80,55,35);
        buttonExit.setBounds(160,80,55,35);
        //将组件添加到jDialog中
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
