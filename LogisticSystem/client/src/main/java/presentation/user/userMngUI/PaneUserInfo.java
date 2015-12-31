/*
 * Created by JFormDesigner on Thu Dec 31 10:05:11 CST 2015
 */

package presentation.user.userMngUI;

import businesslogic.impl.company.StaffManageBLImpl;
import data.message.LoginMessage;
import data.vo.StaffVO;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class PaneUserInfo extends JPanel {
    LoginMessage loginMessage = null;
    StaffVO staff = null;
    public PaneUserInfo(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
        staff = new StaffManageBLImpl().getstaffByID(loginMessage.getUserSN());
        this.labelUserID.setText(String.valueOf(loginMessage.getUserSN()));
        this.labelUserName.setText(staff.name + " " + staff.getUserRole());
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        btModifyPswd = new JButton();
        labelUserName = new JLabel();
        labelUserID = new JLabel();

        //======== this ========

        //---- btModifyPswd ----
        btModifyPswd.setText("\u4fee\u6539\u5bc6\u7801");

        //---- labelUserName ----
        labelUserName.setText("text");

        //---- labelUserID ----
        labelUserID.setText("text");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelUserID)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelUserName)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btModifyPswd)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btModifyPswd)
                        .addComponent(labelUserName, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelUserID, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(91, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton btModifyPswd;
    private JLabel labelUserName;
    private JLabel labelUserID;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
