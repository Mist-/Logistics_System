/*
 * Created by JFormDesigner on Sun Dec 13 18:33:39 CST 2015
 */

package presentation;

import controller.ConfigController;
import data.Configuration;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class MonitorUI extends JFrame {
    ConfigController controller = new ConfigController();

    public MonitorUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        textRegPort = new JTextField();
        label2 = new JLabel();
        textTransPort = new JTextField();
        label3 = new JLabel();
        textFileUpdateFreq = new JTextField();

        //======== this ========
        setTitle("\u670d\u52a1\u76d1\u89c6\u5668");
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {

                //---- label1 ----
                label1.setText("\u670d\u52a1\u7aef\u53e3\uff1a");

                //---- label2 ----
                label2.setText("\u6570\u636e\u7aef\u53e3\uff1a");

                //---- label3 ----
                label3.setText("\u6587\u4ef6\u66f4\u65b0\u9891\u7387\uff1a");

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textRegPort, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label2)
                                        .addGap(6, 6, 6)
                                        .addComponent(textTransPort, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addGap(6, 6, 6)
                                    .addComponent(textFileUpdateFreq, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(565, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(textRegPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(label2))
                                .addComponent(textTransPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(label3))
                                .addComponent(textFileUpdateFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(394, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("\u8fde\u63a5\u8bbe\u7f6e", panel1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        Configuration configuration = controller.getConfigToDisplay();
        textRegPort.setText(String.valueOf(configuration.regPort));
        textTransPort.setText(String.valueOf(configuration.dataTransPort));
        textFileUpdateFreq.setText(String.valueOf(configuration.autoSavingFreq));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textRegPort;
    private JLabel label2;
    private JTextField textTransPort;
    private JLabel label3;
    private JTextField textFileUpdateFreq;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
