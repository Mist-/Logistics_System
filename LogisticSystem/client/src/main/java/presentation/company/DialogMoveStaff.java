package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class DialogMoveStaff extends JDialog{
	public JDialog jdialog = null;
	public JButton finish = null;
	public JComboBox<String> institution = null;
	String institutions[];
	public JComboBox<String> city = null;
	String citys[];
	public JComboBox<String> businessOffice = null;
    ArrayList<String> businessOffices = null;
	public JLabel choose = null;
	public companyManage company = null;
	CompanyBLController controller = null;
	ResultMessage resultMessage = null;
	String fromInstitution,toInstitution,ID,userRole;

	  public DialogMoveStaff(companyManage company, String fromInstitution, String ID){
		this.company = company;
		this.fromInstitution = fromInstitution;
		this.ID = ID;
		controller = new CompanyBLController();
		jdialog = new JDialog(company,"�ƶ�Ա��");
		choose = new JLabel("��ѡ����:");
		finish = new JButton("ȷ��");
		institutions = new String[]{"���Ա","��ͨ������Ա","�߼�������Ա","������ʻԱ","�ֿ����Ա","Ӫҵ��ҵ��Ա","��ת����ҵ��Ա"};
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
					//��ȡ�ƶ�����������
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
							//��ȡ�ƶ�����������
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
					//��ȡ�ƶ�����������
					toInstitution = (String) institution.getSelectedItem();
					userRole = null;
				}
				else{
					businessOffice.removeAllItems();
					city.removeAllItems();
					//��ȡ�ƶ�����������
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
		jdialog.add(choose);
		jdialog.add(institution);
		jdialog.add(city);
		jdialog.add(businessOffice);
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
		  resultMessage = controller.moveStaff(fromInstitution,toInstitution,ID,userRole);
		  //����resultMessage���ͶԽ���������
		  if(resultMessage == ResultMessage.SUCCESS){
			  company.labelStaffSuccess.setText("�ƶ��ɹ�!");
			  switch (company.tabbedPaneStaff.getSelectedIndex()){
				  //�ػ浱ǰ���
				  case 0:
					  company.initDeliverTable();
					  break;
				  case 1:
					  company.initFinancialTable();
					  break;
				  case 2:
					  company.initSFinancialTable();
					  break;
				  case 3:
					  company.initTrunkTable();
					  break;
				  case 4:
					  company.initCenterTable(fromInstitution);
					  break;
				  case 5:
					  company.initBusinessTable(fromInstitution);
					  break;
				  case 6:
					  company.initStorageTable(fromInstitution);
					  break;
			  }
		  }
		  else if(resultMessage == ResultMessage.EXIST){
			  company.labelStaffSuccess.setText("");
			  JOptionPane.showMessageDialog(null,"��Ա���Ѵ���,�����ظ����","",JOptionPane.ERROR_MESSAGE);
		  }
		  else if(resultMessage == ResultMessage.NOTCONNECTED){
			  company.labelStaffSuccess.setText("");
			  JOptionPane.showMessageDialog(null,"�������...","",JOptionPane.ERROR_MESSAGE);
		  }
		  jdialog.dispose();
	  }
}
