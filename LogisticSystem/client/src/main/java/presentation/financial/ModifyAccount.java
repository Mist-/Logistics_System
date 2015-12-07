/*
 * Created by JFormDesigner on Mon Dec 07 17:34:34 CST 2015
 */

package presentation.financial;

import java.awt.*;

import javax.swing.*;

import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.service.Financial.FinancialBLService;

/**
 * @author wanghui
 */
public class ModifyAccount extends JDialog {
	public ModifyAccount() {
		initComponents();
	}

	public ModifyAccount(long accountNum, String name, double money) {
		FinancialBLService financialBL = new FinancialBLController();
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}
	long accountNum = 0;
	String name = null;
	double money = 0;
	
	private void haha(){
		tfName.setText(name);
		tfMoney.setText(Double.toString(money));
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		lbName = new JLabel();
		lbMoney = new JLabel();
		tfName = new JTextField();
		tfMoney = new JTextField();
		btYes = new JButton();

		//======== this ========
		setTitle("\u4fee\u6539\u8d26\u6237");
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			//---- lbName ----
			lbName.setText("\u8d26\u6237\u540d\u79f0\uff1a");

			//---- lbMoney ----
			lbMoney.setText("\u8d26\u6237\u4f59\u989d\uff1a");

			//---- btYes ----
			btYes.setText("\u786e\u8ba4");

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(27, 27, 27)
								.addGroup(panel1Layout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
										.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(tfMoney, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
									.addGroup(panel1Layout.createSequentialGroup()
										.addGap(49, 49, 49)
										.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(tfName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(134, 134, 134)
								.addComponent(btYes)))
						.addContainerGap(138, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(34, 34, 34)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(tfMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(66, 66, 66)
						.addComponent(btYes)
						.addContainerGap(84, Short.MAX_VALUE))
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
	private JLabel lbName;
	private JLabel lbMoney;
	private JTextField tfName;
	private JTextField tfMoney;
	private JButton btYes;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
