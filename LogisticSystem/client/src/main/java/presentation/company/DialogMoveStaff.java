package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DialogMoveStaff extends JDialog{
	public JDialog jdialog = null;
	public JButton finish = null;
	public JComboBox<String> institution = null;
	String institutions[];
	public JComboBox<String> city = null;
	String citys[];
	public JComboBox<String> num = null;
	String nums20[];
	String nums15[];
	String nums10[];
	public JLabel choose = null;
	public companyManage company = null;
	CompanyBLController controller = null;
	ResultMessage resultMessage = null;
	String fromInstitution,toInstitution,ID;
	  public DialogMoveStaff(companyManage company, String fromInstitution, String ID){
		this.company = company;
		this.fromInstitution = fromInstitution;
		this.ID = ID;
		controller = new CompanyBLController();
		jdialog = new JDialog(company,"移动员工");
		choose = new JLabel("请选择部门:");
		finish = new JButton("确认");
		institutions = new String[]{"快递员","财务人员","货车驾驶员","火车驾驶员","飞机驾驶员","仓库管理员","营业厅业务员","中转中心业务员"};
		citys = new String[]{"北京","上海","南京","广州"};
		nums20 = new String[20];
		nums15 = new String[15];
		nums10 = new String[10];
		for(int i=0;i<20;i++){
			nums20[i] = String.valueOf(i+1);
		}
		for(int i=0;i<15;i++){
			nums15[i] = String.valueOf(i+1);
		}
		for(int i=0;i<10;i++){
			nums10[i] = String.valueOf(i+1);
		}
		institution = new JComboBox<String>(institutions);
		city = new JComboBox<String>();
		num = new JComboBox<String>();
		institution.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(institution.getSelectedIndex()==5||institution.getSelectedIndex()==7){
				    city.removeAllItems();
				    for(int i=0;i<citys.length;i++){
					     city.addItem(citys[i]);
				}
				    num.removeAllItems();
			}
				else if(institution.getSelectedIndex()==6){
					city.removeAllItems();
					for(int i=0;i<citys.length;i++){
						city.addItem(citys[i]);
					}
					city.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent arg0) {
							if(city.getSelectedIndex()==0||city.getSelectedIndex()==1){
								num.removeAllItems();
								for(int i =0;i<nums20.length;i++){
									num.addItem(nums20[i]);
								}
							}
							else if(city.getSelectedIndex()==2){
								num.removeAllItems();
								for(int i =0;i<nums10.length;i++){
									num.addItem(nums10[i]);
								}
							}
							else if(city.getSelectedIndex()==3){
								num.removeAllItems();
								for(int i =0;i<nums15.length;i++){
									num.addItem(nums15[i]);
								}
							}
						}
					});
				}
				else{
					num.removeAllItems();
					city.removeAllItems();
				}
			}
		});

		choose.setBounds(20,30,80,30);
		institution.setBounds(110, 30, 105, 30);
		city.setBounds(215, 30, 70, 30);
		num.setBounds(285, 30, 70, 30);
		jdialog.setSize(410,200);
		jdialog.add(choose);
		jdialog.add(institution);
		jdialog.add(city);
		jdialog.add(num);
		jdialog.add(finish);
		jdialog.setModal(true);
		jdialog.setLayout(null);
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
		  toInstitution = (String)city.getSelectedItem()+institution.getSelectedItem()+num.getSelectedItem();
		  resultMessage = controller.moveStaff(fromInstitution,toInstitution,ID);
		  //根据resultMessage类型对界面进行输出
		  if(resultMessage == ResultMessage.SUCCESS){
			  company.labelStaffSuccess.setText("移动成功");
			  switch (company.tabbedPaneStaff.getSelectedIndex()){
				  //重绘当前表格
				  case 0:
					  company.initDeliverTable();
					  break;
				  case 1:
					  company.initFinancialTable();
					  break;
				  case 2:
					  company.initTrunkTable();
					  break;
				  case 3:
					  company.initCenterTable(fromInstitution);
					  break;
				  case 4:
					  company.initBusinessTable(fromInstitution);
					  break;
				  case 5:
					  company.initStorageTable(fromInstitution);
					  break;
			  }
		  }
		  else if(resultMessage == ResultMessage.EXIST){
			  company.labelStaffSuccess.setText("");
			  JOptionPane.showMessageDialog(null,"该员工已存在,请勿重复添加","",JComponent.ERROR);
		  }
		  else if(resultMessage == ResultMessage.NOTCONNECTED){
			  company.labelStaffSuccess.setText("");
			  JOptionPane.showMessageDialog(null,"网络错误...","",JComponent.ERROR);
		  }
		  jdialog.dispose();
	  }
}
