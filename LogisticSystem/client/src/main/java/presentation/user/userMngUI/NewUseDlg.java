/*
 * Created by JFormDesigner on Thu Dec 03 00:58:39 CST 2015
 */

package presentation.user.userMngUI;

import data.vo.StaffVO;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class NewUseDlg extends JDialog {
    public NewUseDlg(Frame owner) {
        super(owner);
        initComponents();
    }

    public NewUseDlg(Dialog owner) {
        super(owner);
        initComponents();
    }

    public StaffVO getNewUser() {
        return null;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        Container contentPane = getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 509, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 266, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
