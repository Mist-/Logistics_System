/*
 * Created by JFormDesigner on Wed Dec 02 18:37:14 CST 2015
 */

package presentation.financial;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.service.Financial.FinancialBLService;
import utils.Timestamper;
import data.vo.PaymentVO;

/**
 * @author wanghui
 */
public class PaymentAdd extends JDialog {
	FinancialBLService financialBL = new FinancialBLController();
	
	public PaymentAdd() {
		initComponents();
		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		hiahia();
	}
	
	
	String [] infos ;
	String [] occupation;
	String [] list;
    PaymentVO payment = new PaymentVO();
	
	private void hiahia(){
    infos = new String[] {"租金","运费","人员工资","奖励"};
	for (String name: infos) {
		cbInfo.addItem(name);
	}
	occupation = new String[] {"快递员","财务人员","货车驾驶","营业厅业务员","中转中心业务员"};
	
	list = new String[] {"装车单","中转单"};
	lbInstitution.setVisible(false);
	tfInstitution.setVisible(false);
	cbOccupatiom.setVisible(false);
	
	cbInfo.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(cbInfo.getSelectedIndex()==2){
				cbOccupatiom.removeAllItems();
			    for(int i=0;i<occupation.length;i++){
			    	cbOccupatiom.addItem(occupation[i]);
			    	
			}
			    lbMoney.setVisible(false);
				tfMoney.setVisible(false);
				lbInstitution.setVisible(false);
				tfInstitution.setVisible(false);
		}
			else if(cbInfo.getSelectedIndex()==1){
				cbOccupatiom.removeAllItems();
				for(int i=0;i<list.length;i++){
					cbOccupatiom.addItem(list[i]);
				}
				lbMoney.setVisible(false);
				tfMoney.setVisible(false);
				lbInstitution.setVisible(true);
				tfInstitution.setVisible(true);
			}
			else if(cbInfo.getSelectedIndex()==0||cbInfo.getSelectedIndex()==3){
				cbOccupatiom.setVisible(false);
				lbMoney.setVisible(true);
				tfMoney.setVisible(true);
				lbInstitution.setVisible(false);
				tfInstitution.setVisible(false);
			}
		}
	});
	}
	
	//新建付款单确定的监听
	public void button1MouseReleased(MouseEvent e) {
		
		if(tfPeople.getText().equals("") || tfAccount.getText().equals("") || tfExinfo.getText().equals("")){
			JOptionPane.showMessageDialog(this, "您的数据输入不全，请继续输入");
			return;
		}
		
		String people = tfPeople.getText();
		String account = tfAccount.getText();
		String info; 
		String exInfo = tfExinfo.getText();
		
		payment.setName(people);
		payment.setAccount(account);
		payment.setDate(Timestamper.getTimeByDate());
		payment.setExInfo(exInfo);
		
		//租金或者奖励
		if(cbInfo.getSelectedIndex()==0||cbInfo.getSelectedIndex()==3){
			if (tfMoney.getText().equals("") || !tfMoney.getText().matches("[0-9]*[.]?[0-9]*")) {
				JOptionPane.showMessageDialog(this, "您输入的金额数据格式错误");
				tfMoney.requestFocus();
				return;
			}
			if(cbInfo.getSelectedIndex()==0){
			info = infos[0];
			if(!tfExinfo.getText().matches("[0-9]*")){
				JOptionPane.showMessageDialog(this, "您输入的备注有错，请输入租金年份");
				return;
			}
			}
			else{
				info = infos[3];
			}
			double money = Double.valueOf(tfMoney.getText());	
			payment.setInfo(info);
			payment.setMoney(money);
			
			financialBL.buildPaymentFromRent(payment);
			this.setVisible(false);
		   
		}
		//运费
		else if(cbInfo.getSelectedIndex()==1){
			info = infos[1];
			
			payment.setMoney(0);
			long institution = Long.valueOf(tfInstitution.getText());
			if(cbOccupatiom.getSelectedIndex()==0){
			 info = info + ":" + list[0];
			 payment.setInfo(info);
			 payment = financialBL.buildPaymentFromEntruck(payment, institution);
			}
			else if(cbOccupatiom.getSelectedIndex()==1){
			 info = info + ":" + list[1];
			 payment.setInfo(info);
				this.setVisible(false);

			payment = financialBL.buildPaymentFromTransfer(payment, institution);
			}
		}
		//人员工资
		else if(cbInfo.getSelectedIndex()==2){
			String ins = (String) cbOccupatiom.getSelectedItem();
			info = infos[2] + ":" + ins ;
			payment.setInfo(info);
			payment.setMoney(0);
			this.setVisible(false);

			payment = financialBL.buildPaymentFromWages(payment, ins);
			
		}
		
		this.setVisible(false);
		
	}

	private void thisWindowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(this, "确定要退出吗？所有信息将不被保存。", "LCS物流管理系统", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.setVisible(false);
		}
	}
	
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		tfAccount = new JTextField();
		lbPeople = new JLabel();
		lbAccount = new JLabel();
		tfPeople = new JTextField();
		lbInfo = new JLabel();
		cbOccupatiom = new JComboBox();
		cbInfo = new JComboBox();
		lbExinfo = new JLabel();
		tfExinfo = new JTextField();
		lbMoney = new JLabel();
		tfMoney = new JTextField();
		btYes = new JButton();
		lbInstitution = new JLabel();
		tfInstitution = new JTextField();

		//======== this ========
		setTitle("\u65b0\u5efa\u4ed8\u6b3e\u5355");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}
		});
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			//---- lbPeople ----
			lbPeople.setText("  \u4ed8\u6b3e\u4eba\uff1a");

			//---- lbAccount ----
			lbAccount.setText("\u4ed8\u6b3e\u8d26\u53f7\uff1a");

			//---- lbInfo ----
			lbInfo.setText("    \u6761\u76ee\uff1a");

			//---- lbExinfo ----
			lbExinfo.setText("    \u5907\u6ce8\uff1a");

			//---- lbMoney ----
			lbMoney.setText("    \u91d1\u989d\uff1a");

			//---- btYes ----
			btYes.setText("\u786e\u8ba4");
			btYes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					button1MouseReleased(e);
				}
			});

			//---- lbInstitution ----
			lbInstitution.setText("    \u673a\u6784ID\uff1a");

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(89, 89, 89)
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbPeople, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(tfPeople, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbAccount, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbInfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(6, 6, 6)
								.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(tfMoney, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbInstitution, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(tfInstitution, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(tfExinfo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(97, 97, 97)
								.addComponent(btYes)))
						.addContainerGap(88, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addGap(40, 40, 40)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbPeople, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfPeople, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6, 6, 6)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbAccount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(2, 2, 2)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbInfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6, 6, 6)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6, 6, 6)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbInstitution, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfInstitution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8, 8, 8)
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfExinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(3, 3, 3)
						.addComponent(btYes)
						.addContainerGap(41, Short.MAX_VALUE))
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
	private JTextField tfAccount;
	private JLabel lbPeople;
	private JLabel lbAccount;
	private JTextField tfPeople;
	private JLabel lbInfo;
	private JComboBox cbOccupatiom;
	private JComboBox cbInfo;
	private JLabel lbExinfo;
	private JTextField tfExinfo;
	private JLabel lbMoney;
	private JTextField tfMoney;
	private JButton btYes;
	private JLabel lbInstitution;
	private JTextField tfInstitution;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
