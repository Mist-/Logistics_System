/*
 * Created by JFormDesigner on Wed Dec 02 18:37:14 CST 2015
 */

package presentation.financial;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

/**
 * @author wanghui
 */
public class PaymentAdd extends JFrame {
	public PaymentAdd() {
		initComponents();
		hiahia();
	}
	private void hiahia(){
	String [] info ;
	final String [] occupation;
	final String [] list;
	String money;
	JLabel lbMoney;
	JTextField tfMoney;
	
	info = new String[] {"租金","运费","人员工资"};
	cbInfo = new JComboBox<String>(info);
	occupation = new String[] {"快递员","财务人员","货车驾驶","营业厅业务员","中转中心业务员"};
	cbOccupatiom = new JComboBox<String>(occupation);
	list = new String[] {"装车单","中转单"};
	lbMoney= new JLabel("付款金额:");
	tfMoney= new JTextField();
	
	
	cbInfo.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(cbInfo.getSelectedIndex()==2){
				cbOccupatiom.removeAllItems();
			    for(int i=0;i<occupation.length;i++){
			    	cbOccupatiom.addItem(occupation[i]);
			}
			  
		}
			else if(cbInfo.getSelectedIndex()==1){
				cbOccupatiom.removeAllItems();
				for(int i=0;i<list.length;i++){
					cbOccupatiom.addItem(list[i]);
				}
			}
			else if(cbInfo.getSelectedIndex()==0||cbInfo.getSelectedIndex()==4){
				cbOccupatiom.setEnabled(true);
				
			}
		}
	});
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

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(69, 69, 69)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(lbInfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(lbPeople, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(lbAccount, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
						.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(5, 5, 5)
							.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(tfPeople, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfAccount, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(tfExinfo, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(103, Short.MAX_VALUE))
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
						.addComponent(cbOccupatiom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lbExinfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfExinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(153, Short.MAX_VALUE))
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
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
