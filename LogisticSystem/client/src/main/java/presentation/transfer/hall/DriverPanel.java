/*
 * Created by JFormDesigner on Mon Nov 30 22:38:57 CST 2015
 */

package presentation.transfer.hall;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.vo.DriverListVO;
import businesslogic.service.Transfer.hall.DriverManagementService;

/**
 * @author sunxu
 */
public class DriverPanel extends JPanel {
	DriverManagementService driverManagement;
	public DriverPanel() {
		initComponents();
	}

	public DriverPanel(DriverManagementService driverManagement) {
		this.driverManagement = driverManagement;
		showDriverButton.setEnabled(false);
	}
	
	private void setList(){
		DriverListVO driverList = driverManagement.getDrivers();
		DefaultTableModel model = new DefaultTableModel(driverList.info,driverList.header);
		driverTable.setModel(model);
		driverTable.validate();
		driverTable.updateUI();
		driverTable.repaint();
	}

	private void driverTableMouseClicked(MouseEvent e) {
		showDriverButton.setEnabled(true);
	}

	private void showDriverButtonMouseClicked(MouseEvent e) {
		int row = driverTable.getSelectedRow();
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		driverPane = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		driverTable = new JTable();
		addDriverButton = new JButton();
		showDriverButton = new JButton();
		tabbedPane2 = new JTabbedPane();
		panel2 = new JPanel();

		//======== this ========
		setLayout(new BorderLayout());

		//======== driverPane ========
		{

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{

					//---- driverTable ----
					driverTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							driverTableMouseClicked(e);
						}
					});
					scrollPane1.setViewportView(driverTable);
				}

				//---- addDriverButton ----
				addDriverButton.setText("\u6dfb\u52a0");

				//---- showDriverButton ----
				showDriverButton.setText("\u67e5\u770b");
				showDriverButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showDriverButtonMouseClicked(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel1Layout.createParallelGroup()
								.addComponent(addDriverButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addComponent(showDriverButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
							.addContainerGap())
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap(284, Short.MAX_VALUE)
							.addComponent(showDriverButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(addDriverButton)
							.addContainerGap())
				);
			}
			driverPane.addTab("\u53f8\u673a\u5217\u8868", panel1);
		}
		add(driverPane, BorderLayout.CENTER);

		//======== tabbedPane2 ========
		{

			//======== panel2 ========
			{

				GroupLayout panel2Layout = new GroupLayout(panel2);
				panel2.setLayout(panel2Layout);
				panel2Layout.setHorizontalGroup(
					panel2Layout.createParallelGroup()
						.addGap(0, 795, Short.MAX_VALUE)
				);
				panel2Layout.setVerticalGroup(
					panel2Layout.createParallelGroup()
						.addGap(0, 341, Short.MAX_VALUE)
				);
			}
			tabbedPane2.addTab("\u53f8\u673a\u8be6\u7ec6\u4fe1\u606f", panel2);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane driverPane;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable driverTable;
	private JButton addDriverButton;
	private JButton showDriverButton;
	private JTabbedPane tabbedPane2;
	private JPanel panel2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
