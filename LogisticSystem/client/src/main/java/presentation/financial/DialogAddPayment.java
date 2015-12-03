package presentation.financial;

import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import presentation.company.companyManage;

/*
 * 添加付款单时的临时界面
 */
public class DialogAddPayment extends JDialog{
	 JDialog jdialog = null;
	 JLabel labelMoney = null;
	 JTextField money = null;
	 JLabel labelPeople = null;
	 JTextField people = null;
	 JLabel labelAccount = null;
	 JTextField account = null;
	 JLabel labelInfo = null;
	 String [] info = null;
	 String [] occupation = null;
	 JLabel labelExinfo = null;
	 JTextField exInfo = null;
	 JComboBox<String> infos = null;
	 JComboBox<String> occupations = null;
	
	
	
	 JButton finish = null;
	 FINANCE finance = null;
	 String stringMoney,stringPeople,stringAccount,stringExinfo;
	 boolean gender;
	public DialogAddPayment(FINANCE finance){
		this.finance = finance;
		finish = new JButton("确认");
		jdialog = new JDialog(this,"新建付款单");
		labelMoney = new JLabel("付款金额:");
		labelPeople = new JLabel("付款人:");
		labelAccount = new JLabel("付款账号:");
		labelInfo = new JLabel("请选择条目：");
		labelExinfo = new JLabel("备注：");
		
		
		money = new JTextField();
		people = new JTextField();
		account = new JTextField();
		exInfo = new JTextField();
		info = new String[] {"租金","运费","人员工资"};
		infos = new JComboBox<String>(info);
		occupation = new String[] {"快递员","财务人员","货车驾驶","营业厅业务员","中转中心业务员"};
		occupations = new JComboBox<String>(occupation);
		
		labelMoney.setBounds(10, 10, 60, 30);
		money.setBounds(80,10,100,30);
		labelPeople.setBounds(10,50,60,30);
		people.setBounds(80, 50, 100, 30);
		labelAccount.setBounds(10,90,60,30);
		account.setBounds(80, 90, 100, 30);
		labelExinfo.setBounds(10,130,60,30);
		exInfo.setBounds(80, 130, 100, 30);
		
		finish.setBounds(110, 230, 60, 35);
		
		
       // 将所有控件添加到JDialog中
		jdialog.setSize(280, 350);
		jdialog.add(labelMoney);
		jdialog.add(money);
		jdialog.add(labelPeople);
		jdialog.add(people);
		jdialog.add(labelAccount);
		jdialog.add(account);
		jdialog.add(labelInfo);
		jdialog.add(infos);
		jdialog.add(labelExinfo);
		jdialog.add(exInfo);
		jdialog.add(occupations);
		
		jdialog.add(finish);
		jdialog.setModal(true);
		jdialog.setLayout(null);
		jdialog.setLocationRelativeTo(null);
		jdialog.setResizable(false);
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonEnsure(e);
			}
		});
		jdialog.setVisible(true);
	}
	
	private void buttonEnsure(MouseEvent e){
		stringMoney = money.getText();
		stringPeople = people.getText();
		stringAccount = account.getText();
		stringExinfo = exInfo.getText();
		//TODO
		
	//	System.out.println(stringName+stringSerialNum+stringPhoneNum+stringYear+stringMonth+stringDay+gender);
		jdialog.dispose();
	}
}
