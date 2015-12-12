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
import javax.swing.table.TableColumn;

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
		tbInstitution = new JTable();
		scrollPane3 = new JScrollPane();
		tbVehicleInfo = new JTable();
		scrollPane4 = new JScrollPane();
		tbDriverInfo = new JTable();
		scrollPane6 = new JScrollPane();
		tbStorageIn = new JTable();
		scrollPane8 = new JScrollPane();
		tbStorageOut = new JTable();
		scrollPane9 = new JScrollPane();
		tbPayment = new JTable();
		scrollPane10 = new JScrollPane();
		tbReceipt = new JTable();
		scrollPane11 = new JScrollPane();
		tbAccount = new JTable();
		scrollPane5 = new JScrollPane();
		tbStaff = new JTable();

		//======== this ========
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			//======== tp ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(tbInstitution);
				}
				tp.addTab("\u673a\u6784\u8868", scrollPane2);

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(tbVehicleInfo);
				}
				tp.addTab("\u8f66\u8f86\u4fe1\u606f\u8868", scrollPane3);

				//======== scrollPane4 ========
				{
					scrollPane4.setViewportView(tbDriverInfo);
				}
				tp.addTab("\u53f8\u673a\u4fe1\u606f\u8868", scrollPane4);

				//======== scrollPane6 ========
				{
					scrollPane6.setViewportView(tbStorageIn);
				}
				tp.addTab("\u5165\u5e93\u5355", scrollPane6);

				//======== scrollPane8 ========
				{
					scrollPane8.setViewportView(tbStorageOut);
				}
				tp.addTab("\u51fa\u5e93\u5355", scrollPane8);

				//======== scrollPane9 ========
				{
					scrollPane9.setViewportView(tbPayment);
				}
				tp.addTab("\u4ed8\u6b3e\u5355", scrollPane9);

				//======== scrollPane10 ========
				{
					scrollPane10.setViewportView(tbReceipt);
				}
				tp.addTab("\u6536\u6b3e\u5355", scrollPane10);

				//======== scrollPane11 ========
				{
					scrollPane11.setViewportView(tbAccount);
				}
				tp.addTab("\u8d26\u6237\u4fe1\u606f\u8868", scrollPane11);

				//======== scrollPane5 ========
				{
					scrollPane5.setViewportView(tbStaff);
				}
				tp.addTab("\u4eba\u5458\u8868", scrollPane5);
			}

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tp, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addContainerGap())
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tp, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
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
        String names[] = {"付款日期", "付款金额", "付款人", "付款账号", "条目", "备注"};
        for (int i = 0; i < names.length; i++) {
        	tbPayment.addColumn(new TableColumn(i));
        	tbPayment.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }
        tbPayment.setRowHeight(50);
        
        String names1[] = {"编号","机构名称"};
        for (int i = 0; i < names1.length; i++) {
        	tbInstitution.addColumn(new TableColumn(i));
        	tbInstitution.getColumnModel().getColumn(i).setHeaderValue(names1[i]);
        }
        tbInstitution.setRowHeight(50);
        
        String names2[] = {"编号","上岗日期","证件有效期至"};
        for (int i = 0; i < names2.length; i++) {
        	tbVehicleInfo.addColumn(new TableColumn(i));
        	tbVehicleInfo.getColumnModel().getColumn(i).setHeaderValue(names2[i]);
        }
        tbVehicleInfo.setRowHeight(50);
        
        String names3[] = {"编号","姓名","性别","出生日期","电话号码","证件有效期至"};
        for (int i = 0; i < names3.length; i++) {
        	tbDriverInfo.addColumn(new TableColumn(i));
        	tbDriverInfo.getColumnModel().getColumn(i).setHeaderValue(names3[i]);
        }
        tbDriverInfo.setRowHeight(50);
        
        String names4[] = {"编号","入库日期","中转中心编号"};
        for (int i = 0; i < names4.length; i++) {
        	tbStorageIn.addColumn(new TableColumn(i));
        	tbStorageIn.getColumnModel().getColumn(i).setHeaderValue(names4[i]);
        }
        tbStorageIn.setRowHeight(50);
        
        String names5[] = {"入库单编号","出库日期","中转中心编号","装运类型","中转单或装车单号"};
        for (int i = 0; i < names5.length; i++) {
        	tbStorageOut.addColumn(new TableColumn(i));
        	tbStorageOut.getColumnModel().getColumn(i).setHeaderValue(names5[i]);
        }
        tbStorageOut.setRowHeight(50);
        
        String names6[] = {"收款日期", "收款单位", "收款人", "收款方", "收款金额", "收款地点"};
        for (int i = 0; i < names6.length; i++) {
        	tbReceipt.addColumn(new TableColumn(i));
        	tbReceipt.getColumnModel().getColumn(i).setHeaderValue(names6[i]);
        }
        tbReceipt.setRowHeight(50);
        
        String names7[] = {"ID", "账户名称", "账户余额"};
        for (int i = 0; i < names7.length; i++) {
        	tbAccount.addColumn(new TableColumn(i));
        	tbAccount.getColumnModel().getColumn(i).setHeaderValue(names7[i]);
        }
        tbAccount.setRowHeight(50);
        
        String names8[] = {"编号", "机构号", "名字","性别", "电话号码", "身份证号","职位"};
        for (int i = 0; i < names8.length; i++) {
        	tbStaff.addColumn(new TableColumn(i));
        	tbStaff.getColumnModel().getColumn(i).setHeaderValue(names8[i]);
        }
        tbStaff.setRowHeight(50);
    }

    public void showHistoryData(String data) {
        ArrayList<DataPO>   institution, vehicleInfo, driverInfo, storageIn, storageOut, payment, receipt, account, staff;

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
        account = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.ACCOUNT + ".DAT");
        staff = (ArrayList<DataPO>) FileIOHelper.getFromFile(dirPath + POType.STAFF + ".DAT");
        // TODO 将读取出来的数据添加到表格中显示。记得判断是否为空。
        
    }
    

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JTabbedPane tp;
	private JScrollPane scrollPane2;
	private JTable tbInstitution;
	private JScrollPane scrollPane3;
	private JTable tbVehicleInfo;
	private JScrollPane scrollPane4;
	private JTable tbDriverInfo;
	private JScrollPane scrollPane6;
	private JTable tbStorageIn;
	private JScrollPane scrollPane8;
	private JTable tbStorageOut;
	private JScrollPane scrollPane9;
	private JTable tbPayment;
	private JScrollPane scrollPane10;
	private JTable tbReceipt;
	private JScrollPane scrollPane11;
	private JTable tbAccount;
	private JScrollPane scrollPane5;
	private JTable tbStaff;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
