/*
 * Created by JFormDesigner on Mon Dec 07 17:34:34 CST 2015
 */

package presentation.financial;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import data.message.ResultMessage;
import data.vo.AccountVO;
import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.service.Financial.FinancialBLService;

/**
 * @author wanghui
 */
public class ModifyAccount extends JDialog {
	public ModifyAccount() {
		initComponents();
		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		haha();
	}
	
    FinancialBLService financialBL;
    long accountNum;
	String name;
	double money;
	
	public ModifyAccount(long accountNum, String name, double money) {
		financialBL = new FinancialBLController();
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
		initComponents();
		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		haha();
	}
	
	
	private void haha(){
		tfName.setText(name);
		tfMoney.setText(Double.toString(money));
	}

	//对确认的监听
	private void btYesMouseReleased(MouseEvent e) {
        String theName  = tfName.getText();
        
        if (tfMoney.getText() == null || !tfMoney.getText().matches("[0-9]*[.]?[0-9]*")) {
			JOptionPane.showMessageDialog(this, "您输入的金额数据格式错误");
			tfMoney.requestFocus();
			return;
		}
        double theMoney = Double.valueOf(tfMoney.getText());
        AccountVO accountvo = new AccountVO();
        accountvo.setAccountNum(accountNum);
        accountvo.setName(theName);
        accountvo.setMoney(theMoney);
        
        ResultMessage a = financialBL.changeAccount(accountvo);
        if(a == ResultMessage.SUCCESS)
        JOptionPane.showMessageDialog(this, "修改成功");
        else 
        	JOptionPane.showMessageDialog(this, "false");
        this.setVisible(false);
	}
	
	//窗口关闭提醒
	private void thisWindowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(this, "确定要退出吗？所有信息将不被更改。", "LCS物流管理系统", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.setVisible(false);
		}
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}
		});
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			//---- lbName ----
			lbName.setText("\u8d26\u6237\u540d\u79f0\uff1a");

			//---- lbMoney ----
			lbMoney.setText("\u8d26\u6237\u4f59\u989d\uff1a");

			//---- btYes ----
			btYes.setText("\u786e\u8ba4");
			btYes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btYesMouseReleased(e);
				}
			});

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
