/*
 * Created by JFormDesigner on Thu Dec 03 15:28:21 CST 2015
 */

package presentation.storage;

import java.awt.*;

import javax.swing.*;

import businesslogic.service.storage.StorageOperateService;

/**
 * @author sunxu
 */
public class StorageOperatePanel extends JPanel {
	StorageOperateService storageOperate;
	
	public StorageOperatePanel(StorageOperateService storageOperate) {
		this.storageOperate = storageOperate;
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		operatePane = new JTabbedPane();
		showPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		label9 = new JLabel();
		label10 = new JLabel();
		textField7 = new JTextField();
		label11 = new JLabel();
		label12 = new JLabel();
		textField8 = new JTextField();
		label13 = new JLabel();
		textField9 = new JTextField();
		label14 = new JLabel();
		textField10 = new JTextField();
		label15 = new JLabel();
		textField11 = new JTextField();
		label16 = new JLabel();
		textField12 = new JTextField();
		button3 = new JButton();
		button4 = new JButton();
		checkPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		table2 = new JTable();
		button5 = new JButton();
		button6 = new JButton();
		adjustPanel = new JPanel();
		label17 = new JLabel();
		label18 = new JLabel();
		label19 = new JLabel();
		planePercent = new JTextField();
		trainPercent = new JTextField();
		truckPercent = new JTextField();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();
		initPanel = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		numInput = new JTextField();
		label3 = new JLabel();
		label4 = new JLabel();
		shelfInput = new JTextField();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		planeInput = new JTextField();
		trainInput = new JTextField();
		truckInput = new JTextField();
		flexibleInput = new JTextField();
		button1 = new JButton();
		button2 = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== operatePane ========
		{

			//======== showPanel ========
			{

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(table1);
				}

				//---- label9 ----
				label9.setText("\u8d77\u59cb\u65e5\u671f");

				//---- label10 ----
				label10.setText("\u7ec8\u6b62\u65e5\u671f");

				//---- label11 ----
				label11.setText("\u5e74");

				//---- label12 ----
				label12.setText("\u6708");

				//---- label13 ----
				label13.setText("\u65e5");

				//---- label14 ----
				label14.setText("\u5e74");

				//---- label15 ----
				label15.setText("\u6708");

				//---- label16 ----
				label16.setText("\u65e5");

				//---- button3 ----
				button3.setText("\u67e5\u627e");

				//---- button4 ----
				button4.setText("\u67e5\u770b");

				GroupLayout showPanelLayout = new GroupLayout(showPanel);
				showPanel.setLayout(showPanelLayout);
				showPanelLayout.setHorizontalGroup(
					showPanelLayout.createParallelGroup()
						.addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
						.addGroup(showPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label9, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
								.addComponent(label10, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label11, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addComponent(label14, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(textField7, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(textField10, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label12, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addComponent(label15, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(textField8, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(textField11, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(showPanelLayout.createSequentialGroup()
									.addComponent(label13)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textField9, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addGroup(showPanelLayout.createSequentialGroup()
									.addComponent(label16)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textField12)))
							.addGap(71, 71, 71)
							.addComponent(button3)
							.addGap(48, 48, 48)
							.addComponent(button4, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(134, Short.MAX_VALUE))
				);
				showPanelLayout.setVerticalGroup(
					showPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, showPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(showPanelLayout.createParallelGroup()
								.addComponent(label9)
								.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label11)
									.addComponent(label12)
									.addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label13)
									.addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label10)
								.addComponent(label14)
								.addComponent(textField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label15)
								.addComponent(textField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label16)
								.addComponent(textField12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button3)
								.addComponent(button4))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
				);
			}
			operatePane.addTab("\u5e93\u5b58\u67e5\u770b", showPanel);

			//======== checkPanel ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(table2);
				}

				//---- button5 ----
				button5.setText("\u4fdd\u5b58");

				//---- button6 ----
				button6.setText("\u5bfc\u51fa");

				GroupLayout checkPanelLayout = new GroupLayout(checkPanel);
				checkPanel.setLayout(checkPanelLayout);
				checkPanelLayout.setHorizontalGroup(
					checkPanelLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
						.addGroup(checkPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button5, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(button6, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(604, Short.MAX_VALUE))
				);
				checkPanelLayout.setVerticalGroup(
					checkPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, checkPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(checkPanelLayout.createParallelGroup()
								.addComponent(button5, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(button6, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
							.addGap(18, 18, 18)
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
				);
			}
			operatePane.addTab("\u5e93\u5b58\u76d8\u70b9", checkPanel);

			//======== adjustPanel ========
			{

				//---- label17 ----
				label17.setText("\u822a\u8fd0\u533a\uff1a");

				//---- label18 ----
				label18.setText("\u94c1\u8fd0\u533a\uff1a");

				//---- label19 ----
				label19.setText("\u6c7d\u8fd0\u533a\uff1a");

				//---- button7 ----
				button7.setText("\u6269\u5145");

				//---- button8 ----
				button8.setText("\u6269\u5145");

				//---- button9 ----
				button9.setText("\u6269\u5145");

				GroupLayout adjustPanelLayout = new GroupLayout(adjustPanel);
				adjustPanel.setLayout(adjustPanelLayout);
				adjustPanelLayout.setHorizontalGroup(
					adjustPanelLayout.createParallelGroup()
						.addGroup(adjustPanelLayout.createSequentialGroup()
							.addGap(235, 235, 235)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label17, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(label18, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(label19, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(planePercent, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(trainPercent, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addComponent(truckPercent, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup()
								.addComponent(button7)
								.addComponent(button8)
								.addComponent(button9))
							.addContainerGap(324, Short.MAX_VALUE))
				);
				adjustPanelLayout.setVerticalGroup(
					adjustPanelLayout.createParallelGroup()
						.addGroup(adjustPanelLayout.createSequentialGroup()
							.addGap(63, 63, 63)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label17, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(planePercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button7))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label18, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(trainPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button8))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label19, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(truckPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button9))
							.addContainerGap(190, Short.MAX_VALUE))
				);
			}
			operatePane.addTab("\u5e93\u5b58\u8c03\u6574", adjustPanel);

			//======== initPanel ========
			{

				//---- label1 ----
				label1.setText("\u533a\u57df\u6570\u91cf");

				//---- textField1 ----
				textField1.setText("4");
				textField1.setEditable(false);

				//---- label2 ----
				label2.setText("\u8d27\u67b6\u89c4\u683c\uff08\u4f4d\uff09");

				//---- label4 ----
				label4.setText("\u6bcf\u6392\u67b6\u6570\uff08\u67b6\uff09");

				//---- label5 ----
				label5.setText("\u822a\u8fd0\u533a");

				//---- label6 ----
				label6.setText("\u94c1\u8fd0\u533a");

				//---- label7 ----
				label7.setText("\u6c7d\u8fd0\u533a");

				//---- label8 ----
				label8.setText("\u673a\u52a8\u533a");

				//---- button1 ----
				button1.setText("\u786e\u8ba4");

				//---- button2 ----
				button2.setText("\u91cd\u65b0\u8f93\u5165");

				GroupLayout initPanelLayout = new GroupLayout(initPanel);
				initPanel.setLayout(initPanelLayout);
				initPanelLayout.setHorizontalGroup(
					initPanelLayout.createParallelGroup()
						.addGroup(initPanelLayout.createSequentialGroup()
							.addGap(231, 231, 231)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(30, 30, 30)
									.addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(initPanelLayout.createParallelGroup()
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(11, 11, 11)
									.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addComponent(numInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
									.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(initPanelLayout.createParallelGroup()
										.addComponent(shelfInput)
										.addComponent(planeInput)
										.addComponent(trainInput)
										.addComponent(truckInput)
										.addComponent(flexibleInput)))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(399, 399, 399))
				);
				initPanelLayout.setVerticalGroup(
					initPanelLayout.createParallelGroup()
						.addGroup(initPanelLayout.createSequentialGroup()
							.addGap(16, 16, 16)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(numInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label2))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(shelfInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label3)
							.addGap(37, 37, 37)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(planeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label6)
								.addComponent(trainInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label7)
								.addComponent(truckInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label8)
								.addComponent(flexibleInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(37, 37, 37)
							.addGroup(initPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(button1)
								.addComponent(button2))
							.addContainerGap(50, Short.MAX_VALUE))
				);
			}
			operatePane.addTab("\u521d\u59cb\u5316", initPanel);
		}
		add(operatePane, BorderLayout.NORTH);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane operatePane;
	private JPanel showPanel;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JLabel label9;
	private JLabel label10;
	private JTextField textField7;
	private JLabel label11;
	private JLabel label12;
	private JTextField textField8;
	private JLabel label13;
	private JTextField textField9;
	private JLabel label14;
	private JTextField textField10;
	private JLabel label15;
	private JTextField textField11;
	private JLabel label16;
	private JTextField textField12;
	private JButton button3;
	private JButton button4;
	private JPanel checkPanel;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JButton button5;
	private JButton button6;
	private JPanel adjustPanel;
	private JLabel label17;
	private JLabel label18;
	private JLabel label19;
	private JTextField planePercent;
	private JTextField trainPercent;
	private JTextField truckPercent;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JPanel initPanel;
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JTextField numInput;
	private JLabel label3;
	private JLabel label4;
	private JTextField shelfInput;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JTextField planeInput;
	private JTextField trainInput;
	private JTextField truckInput;
	private JTextField flexibleInput;
	private JButton button1;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
