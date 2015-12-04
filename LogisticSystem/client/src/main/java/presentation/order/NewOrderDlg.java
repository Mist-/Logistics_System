/*
 * Created by JFormDesigner on Fri Dec 04 20:50:09 CST 2015
 */

package presentation.order;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class NewOrderDlg extends JDialog {
    public NewOrderDlg(Frame owner) {
        super(owner);
        initComponents();
    }

    public NewOrderDlg(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        lbRname = new JLabel();
        lbRphone = new JLabel();
        lbRaddress = new JLabel();
        lbRcompany = new JLabel();
        lbSname = new JLabel();
        lbSphone = new JLabel();
        lbSaddress = new JLabel();
        lbScompany = new JLabel();
        textField9 = new JTextField();
        textField10 = new JTextField();
        textField11 = new JTextField();
        textField13 = new JTextField();
        textField14 = new JTextField();
        textField15 = new JTextField();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        comboBox3 = new JComboBox();
        comboBox4 = new JComboBox();
        lbRaddress2 = new JLabel();
        textField12 = new JTextField();
        textField16 = new JTextField();
        lbSaddress2 = new JLabel();
        lbReceiver = new JLabel();
        lbSender = new JLabel();

        //======== this ========
        setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRname ----
            lbRname.setText("\u59d3\u540d\uff1a");
            lbRname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRphone ----
            lbRphone.setText("\u7535\u8bdd\uff1a");
            lbRphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRaddress ----
            lbRaddress.setText("\u5730\u5740\uff1a");
            lbRaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRcompany ----
            lbRcompany.setText("\u5355\u4f4d\uff1a");
            lbRcompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSname ----
            lbSname.setText("\u59d3\u540d\uff1a");
            lbSname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSphone ----
            lbSphone.setText("\u7535\u8bdd\uff1a");
            lbSphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress ----
            lbSaddress.setText("\u5730\u5740\uff1a");
            lbSaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany ----
            lbScompany.setText("\u5355\u4f4d\uff1a");
            lbScompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField9 ----
            textField9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField10 ----
            textField10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField11 ----
            textField11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField13 ----
            textField13.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField14 ----
            textField14.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField15 ----
            textField15.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBox1 ----
            comboBox1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBox2 ----
            comboBox2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBox3 ----
            comboBox3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBox4 ----
            comboBox4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRaddress2 ----
            lbRaddress2.setText("\u8be6\u7ec6\u5730\u5740\uff1a");
            lbRaddress2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField12 ----
            textField12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textField16 ----
            textField16.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress2 ----
            lbSaddress2.setText("\u8be6\u7ec6\u5730\u5740\uff1a");
            lbSaddress2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbReceiver ----
            lbReceiver.setText("\u6536\u4ef6\u65b9\uff1a");
            lbReceiver.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSender ----
            lbSender.setText("\u5bc4\u4ef6\u65b9\uff1a");
            lbSender.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lbSaddress2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField16, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lbSender))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(lbSname)
                                        .addGap(6, 6, 6)
                                        .addComponent(textField13, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(lbSphone)
                                        .addGap(6, 6, 6)
                                        .addComponent(textField14, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(lbScompany)
                                        .addGap(6, 6, 6)
                                        .addComponent(textField15, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(lbSaddress)
                                        .addGap(6, 6, 6)
                                        .addComponent(comboBox4, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbRaddress2)
                                .addGap(6, 6, 6)
                                .addComponent(textField12, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(lbReceiver)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbRname)
                                            .addGap(6, 6, 6)
                                            .addComponent(textField9, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbRphone)
                                            .addGap(6, 6, 6)
                                            .addComponent(textField10, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbRcompany)
                                            .addGap(6, 6, 6)
                                            .addComponent(textField11, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbRaddress)
                                            .addGap(6, 6, 6)
                                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(108, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbReceiver)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRname)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRphone))
                                    .addComponent(textField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRcompany)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbRaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRaddress2))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbSender)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textField13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbSname)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lbSphone, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textField14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textField15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbScompany)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbSaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSaddress2)
                                    .addComponent(textField16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(147, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel lbRname;
    private JLabel lbRphone;
    private JLabel lbRaddress;
    private JLabel lbRcompany;
    private JLabel lbSname;
    private JLabel lbSphone;
    private JLabel lbSaddress;
    private JLabel lbScompany;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JLabel lbRaddress2;
    private JTextField textField12;
    private JTextField textField16;
    private JLabel lbSaddress2;
    private JLabel lbReceiver;
    private JLabel lbSender;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
