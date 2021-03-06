package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class DialogMoveStaff extends JDialog{
	JPanel jPanel = null;
	JDialog jdialog = null;
	JButton finish = null;
	JComboBox<String> institution = null;
	String institutions[];
	JComboBox<String> city = null;
	String citys[];
	JComboBox<String> businessOffice = null;
    ArrayList<String> businessOffices = null;
	JLabel choose = null;
	companyManage company = null;
	CompanyBLController controller = null;
	ResultMessage resultMessage = null;
	String fromInstitution,toInstitution,ID,userRole;

	  public DialogMoveStaff(companyManage company, String fromInstitution, String ID){
		this.company = company;
		this.fromInstitution = fromInstitution;
		this.ID = ID;
		controller = new CompanyBLController();
		jPanel = new JPanel();
		jdialog = new JDialog(company,"移动员工");
		choose = new JLabel("请选择部门:");
		finish = new JButton("确认");
		institutions = new String[]{"快递员","普通财务人员","高级财务人员","货车驾驶员","仓库管理员","营业厅业务员","中转中心业务员"};
		citys = controller.getCitys();
		institution = new JComboBox<String>(institutions);
		city = new JComboBox<String>();
		businessOffice = new JComboBox<String>();
		institution.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(institution.getSelectedIndex()==4||institution.getSelectedIndex()==6){
				    city.removeAllItems();
					businessOffice.removeAllItems();
				    for(int i=0;i<citys.length;i++){
					     city.addItem(citys[i]);
				}
					//获取移动机构的名称
					city.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent e) {
							toInstitution = (String) city.getSelectedItem();
							userRole = (String) institution.getSelectedItem();
						}
					});
			}
				else if(institution.getSelectedIndex()==5){
					city.removeAllItems();
					for(int i=0;i<citys.length;i++){
						city.addItem(citys[i]);
					}
					city.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent arg0) {
							businessOffice.removeAllItems();
							businessOffices = controller.getBusinessOffices((String) city.getSelectedItem());
							for(int i=0;i<businessOffices.size();i++){
								businessOffice.addItem(businessOffices.get(i));
							}
							//获取移动机构的名称
							businessOffice.addItemListener(new ItemListener() {
								@Override
								public void itemStateChanged(ItemEvent e) {
									toInstitution = (String) businessOffice.getSelectedItem();
								}
							});
							userRole = (String) institution.getSelectedItem();
						}
					});
				}
				else if(institution.getSelectedIndex()==3){
					businessOffice.removeAllItems();
					city.removeAllItems();
					//获取移动机构的名称
					toInstitution = (String) institution.getSelectedItem();
					userRole = null;
				}
				else{
					businessOffice.removeAllItems();
					city.removeAllItems();
					//获取移动机构的名称
					toInstitution = (String) institution.getSelectedItem();
					userRole = (String) institution.getSelectedItem();
				}
			}
		});

		choose.setBounds(20,30,80,30);
		institution.setBounds(110, 30, 105, 30);
		city.setBounds(215, 30, 70, 30);
		businessOffice.setBounds(285, 30, 70, 30);
		jdialog.setSize(410,200);
		jdialog.add(jPanel, BorderLayout.CENTER);
		jPanel.add(choose);
		jPanel.add(institution);
		jPanel.add(city);
		jPanel.add(businessOffice);
		jPanel.add(finish);
		jPanel.setLayout(null);
		jdialog.setModal(true);
		jdialog.setLocationRelativeTo(null);
		jdialog.setResizable(false);
		finish.setBounds(180, 90, 60, 30);
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonEnsure(e);
			}
		});
		jdialog.setVisible(true);
	  }

	  private void buttonEnsure(MouseEvent e){
		  resultMessage = controller.moveStaff(fromInstitution,toInstitution,ID,userRole);
		  //根据resultMessage类型对界面进行输出
		  if(resultMessage == ResultMessage.SUCCESS){
			  company.getLabelStaffSuccess().setText("移动成功!");
			  company.initStaff();
		   }
		  else if(resultMessage == ResultMessage.EXIST){
			  company.getLabelStaffSuccess().setText("");
			  JOptionPane.showMessageDialog(null,"该员工已存在,请勿重复添加","",JOptionPane.ERROR_MESSAGE);
		  }
		  else if(resultMessage == ResultMessage.NOTCONNECTED){
			  company.getLabelStaffSuccess().setText("");
			  JOptionPane.showMessageDialog(null,"网络错误...","",JOptionPane.ERROR_MESSAGE);
		  }
		  jdialog.dispose();
	  }
}
