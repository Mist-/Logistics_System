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
	String theName;
	
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
		lbMoney.setText(Double.toString(money));
		
	}

//	//计算余额
//	private void account(){
//		theName  = tfName.getText();
//        theMoney = Double.valueOf(lbMoney.getText());
//        if (!tfMoneyAdd.getText().matches("[0-9]*[.]?[0-9]*")) {
//			JOptionPane.showMessageDialog(this, "您输入的金额数据格式错误,请重新输入");
//			tfMoneyAdd.requestFocus();
//			return;
//		}
//        if (!tfMoneyDe.getText().matches("[0-9]*[.]?[0-9]*")) {
//			JOptionPane.showMessageDialog(this, "您输入的金额数据格式错误,请重新输入");
//			tfMoneyDe.requestFocus();
//			return;
//		}
//        if(tfMoneyAdd.getText().equals("") && tfMoneyDe.getText().equals("")){
//        	return;
//        }
//        else if(tfMoneyAdd.getText().equals("")){
//        	if(!tfMoneyDe.getText().equals("")){
//        		theMoney = theMoney - Double.valueOf(tfMoneyDe.getText());
//        	}
//        }
//            else{
//        	   if(tfMoneyDe.getText().equals("")){
//        		theMoney = theMoney + Double.valueOf(tfMoneyAdd.getText());
//        	   }
//        	   else if(!tfMoneyDe.getText().equals("")){
//        		   theMoney = theMoney + Double.valueOf(tfMoneyAdd.getText()) - Double.valueOf(tfMoneyDe.getText());
//        	   }
//            }
//        
//	}
	
	//对确认的监听
	private void btYesMouseReleased(MouseEvent e) {
//		account();
//		if (!tfMoneyAdd.getText().matches("[0-9]*[.]?[0-9]*")) {
//			tfMoneyAdd.requestFocus();
//			return;
//		}
//        if (!tfMoneyDe.getText().matches("[0-9]*[.]?[0-9]*")) {
//			tfMoneyDe.requestFocus();
//			return;
//		}
		if(tfName.getText().equals("")){
			return;
		}
		theName  = tfName.getText();
		if(theName.equals(name)){
			this.setVisible(false);
		}

        AccountVO accountvo = new AccountVO();
        accountvo.setAccountNum(accountNum);
        accountvo.setName(theName);
        accountvo.setMoney(money);
        
        ResultMessage a = financialBL.changeAccount(accountvo);
        if(a == ResultMessage.SUCCESS)
        JOptionPane.showMessageDialog(this, "修改成功");
        else 
        	JOptionPane.showMessageDialog(this, "修改失败");
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
		tfName = new JTextField();
		btYes = new JButton();
		labelMon = new JLabel();
		lbMoney = new JLabel();

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
			lbName.setText("\u8d26 \u6237 \u540d \u79f0  \uff1a");
			lbName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- tfName ----
			tfName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- btYes ----
			btYes.setText("\u786e\u8ba4");
			btYes.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btYes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btYesMouseReleased(e);
				}
			});

			//---- labelMon ----
			labelMon.setText("\u8d26 \u6237 \u4f59 \u989d  \uff1a");
			labelMon.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- lbMoney ----
			lbMoney.setText("text");
			lbMoney.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(123, 123, 123)
								.addComponent(btYes))
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(48, 48, 48)
								.addGroup(panel1Layout.createParallelGroup()
									.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addComponent(labelMon))
								.addGap(18, 18, 18)
								.addGroup(panel1Layout.createParallelGroup()
									.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addComponent(tfName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(112, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(54, 54, 54)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(labelMon)
							.addComponent(lbMoney))
						.addGap(39, 39, 39)
						.addComponent(btYes)
						.addContainerGap(75, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JLabel lbName;
	private JTextField tfName;
	private JButton btYes;
	private JLabel labelMon;
	private JLabel lbMoney;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
