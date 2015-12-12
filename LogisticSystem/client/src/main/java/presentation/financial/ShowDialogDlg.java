/*
 * Created by JFormDesigner on Fri Dec 11 23:38:12 CST 2015
 */

package presentation.financial;

import data.enums.POType;
import data.po.DataPO;
import utils.FileIOHelper;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class ShowDialogDlg extends JDialog {
    public ShowDialogDlg(Frame owner) {
        super(owner);
        initComponents();
    }

    public ShowDialogDlg(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        tp = new JTabbedPane();
        scrollPane2 = new JScrollPane();
        table4 = new JTable();
        scrollPane3 = new JScrollPane();
        table5 = new JTable();
        scrollPane4 = new JScrollPane();
        table6 = new JTable();
        scrollPane6 = new JScrollPane();
        table7 = new JTable();
        scrollPane8 = new JScrollPane();
        table8 = new JTable();
        scrollPane9 = new JScrollPane();
        table9 = new JTable();
        scrollPane10 = new JScrollPane();
        table10 = new JTable();
        scrollPane11 = new JScrollPane();
        table11 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== tp ========
            {

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(table4);
                }
                tp.addTab("\u673a\u6784\u4eba\u5458\u8868", scrollPane2);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table5);
                }
                tp.addTab("\u8f66\u8f86\u4fe1\u606f\u8868", scrollPane3);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(table6);
                }
                tp.addTab("\u53f8\u673a\u4fe1\u606f\u8868", scrollPane4);

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(table7);
                }
                tp.addTab("\u5165\u5e93\u5355", scrollPane6);

                //======== scrollPane8 ========
                {
                    scrollPane8.setViewportView(table8);
                }
                tp.addTab("\u51fa\u5e93\u5355", scrollPane8);

                //======== scrollPane9 ========
                {
                    scrollPane9.setViewportView(table9);
                }
                tp.addTab("\u4ed8\u6b3e\u5355", scrollPane9);

                //======== scrollPane10 ========
                {
                    scrollPane10.setViewportView(table10);
                }
                tp.addTab("\u6536\u6b3e\u5355", scrollPane10);

                //======== scrollPane11 ========
                {
                    scrollPane11.setViewportView(table11);
                }
                tp.addTab("\u8d26\u6237\u4fe1\u606f\u8868", scrollPane11);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tp, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tp, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                        .addContainerGap())
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
        this.setModal(true);
    }

    public void showHistoryData(String data) {
        ArrayList<DataPO>   institution, vehicleInfo, driverInfo, storageIn, storageOut, payment, receipt;

        String dirPath = null;
        for (int i = 0; i < data.length(); ++i) {
            if (data.charAt(i) == '/') dirPath += '_';
            else dirPath += data.charAt(i);
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            JOptionPane.showMessageDialog(this, "数据文件不存在！", "LCS物流管理系统", JOptionPane.WARNING_MESSAGE);
            return;
        }
        setTitle("data " + "期初数据");
        institution = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.INSTITUTION + ".DAT");
        vehicleInfo = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.VEHICLEINFO + ".DAT");
        driverInfo = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.DRIVERINFO + ".DAT");
        storageIn = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.STORAGEINLIST + ".DAT");
        storageOut = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.STORAGEOUTLIST + ".DAT");
        payment = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.PAYMENT + ".DAT");
        receipt = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.VEHICLEINFO + ".DAT");

        // TODO 将读取出来的数据添加到表格中显示。记得判断是否为空。
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTabbedPane tp;
    private JScrollPane scrollPane2;
    private JTable table4;
    private JScrollPane scrollPane3;
    private JTable table5;
    private JScrollPane scrollPane4;
    private JTable table6;
    private JScrollPane scrollPane6;
    private JTable table7;
    private JScrollPane scrollPane8;
    private JTable table8;
    private JScrollPane scrollPane9;
    private JTable table9;
    private JScrollPane scrollPane10;
    private JTable table10;
    private JScrollPane scrollPane11;
    private JTable table11;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
