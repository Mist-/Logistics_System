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
public class PaymentAdd extends JFrame {
	FinancialBLService financialBL = new FinancialBLController();
	
	public PaymentAdd() {
		initComponents();
		hiahia();
	}
	
	
	String [] infos ;
	String [] occupation;
	String [] list;
	PaymentVO payment = new PaymentVO();
	
	private void hiahia(){
	
	
	
	infos = new String[] {"���","�˷�","��Ա����","����"};
	for (String name: infos) {
		cbInfo.addItem(name);
	}
	occupation = new String[] {"���Ա","������Ա","������ʻ","Ӫҵ��ҵ��Ա","��ת����ҵ��Ա"};
	
	list = new String[] {"װ����","��ת��"};
	lbInstitution.setVisible(false);
	tfInstitution.setVisible(false);
	
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
				cbOccupatiom.removeAllItems();
				lbMoney.setVisible(true);
				tfMoney.setVisible(true);
				lbInstitution.setVisible(false);
				tfInstitution.setVisible(false);
			}
		}
	});
	}
//�½����ȷ���ļ���
	public void button1MouseReleased(MouseEvent e) {
		
		String people = tfPeople.getText();
		String account = tfAccount.getText();
		String info; 
		String exInfo = tfExinfo.getText();
		
		payment.setName(people);
		payment.setAccount(account);
		payment.setDate(Timestamper.getTimeByDate());
		payment.setExInfo(exInfo);
		
		//�����߽���
		if(cbInfo.getSelectedIndex()==0||cbInfo.getSelectedIndex()==3){
			if(cbInfo.getSelectedIndex()==0){
			info = infos[0];
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
		//�˷�
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
		//��Ա����
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
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
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
		Container contentPane = getContentPane();

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

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addComponent(btYes)
					.addGap(161, 161, 161))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(69, 69, 69)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(lbInstitution, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbInfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(lbPeople, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(lbAccount, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
						.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(tfAccount)
							.addComponent(tfPeople)
							.addComponent(tfMoney)
							.addComponent(tfExinfo))
						.addComponent(tfInstitution, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbPeople, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfPeople, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbAccount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2, 2, 2)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbInfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbMoney, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbInstitution, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfInstitution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8, 8, 8)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfExinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3, 3, 3)
					.addComponent(btYes)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
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
