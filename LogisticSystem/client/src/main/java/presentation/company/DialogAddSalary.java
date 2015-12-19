package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * ���Ա������ʱ����ʱ����
 */
public class DialogAddSalary extends JDialog{
	JDialog jdialog = null;
	String  [] institutions = null;
	JComboBox<String> institution = null;
	JComboBox<String> type = null;
	String [] types = null;
	JTextField salary = null;
	JButton finish = null;
	JLabel labelInstituion = null;
	JLabel labelSalary = null;
	JLabel labelType = null;
    companyManage company = null;
	String stringSalary,stringInstitution,stringType;
	ResultMessage resultMessage = null;
	CompanyBLController controller = null;
	public DialogAddSalary(companyManage company){
		controller = new CompanyBLController();
		this.company = company;
		jdialog = new JDialog(company,"��ӹ���");
		institutions = new String[]{"���Ա","��ͨ������Ա","Ӫҵ��ҵ��Ա","��ת����ҵ��Ա","�ֿ����Ա","������ʻԱ"};
		institution = new JComboBox<String>(institutions);
		institution.setBounds(65, 35, 100, 30);
		types = new String[]{"�ƴ�","�¸�"};
		type = new JComboBox<String>(types);
		type.setBounds(330, 35, 55, 30);
		salary = new JTextField();
		salary.setBounds(205, 35, 60, 30);
		finish = new JButton("ȷ��");
		finish.setBounds(175, 85, 66, 30);
		labelInstituion = new JLabel("����ѡ��");
		labelInstituion.setBounds(5, 25, 60, 50);
		labelSalary = new JLabel("����");
		labelSalary.setBounds(175, 25, 60, 50);
		labelType = new JLabel("���㷽ʽ");
		labelType.setBounds(270, 25, 60, 50);
		// �����пؼ���ӵ�JDialog��
		jdialog.setSize(410, 200);
		jdialog.add(labelInstituion);
		jdialog.add(institution);
		jdialog.add(labelSalary);
		jdialog.add(salary);
		jdialog.add(labelType);
		jdialog.add(type);
		jdialog.add(finish);
		jdialog.setModal(true);
		jdialog.setLocationRelativeTo(null);
		jdialog.setLayout(null);
		jdialog.setResizable(false);
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonEnsure(e);
			}
		});
		jdialog.setVisible(true);	
	}
	
	private void buttonEnsure(MouseEvent e) {
		stringSalary = salary.getText();
		stringInstitution = (String) institution.getSelectedItem();
		stringType = (String) type.getSelectedItem();
		if (controller.isValidNum(stringSalary)) {
			resultMessage = controller.addSalary(stringInstitution, stringSalary, stringType);
			//����resultMessage���ͶԽ���������
			if (resultMessage == ResultMessage.SUCCESS) {
				company.getLabelSalarySuccess().setText("��ӳɹ�!");
				company.initSalaryTable();
				jdialog.dispose();
			} else if (resultMessage == ResultMessage.EXIST) {
				company.getLabelSalarySuccess().setText("");
				JOptionPane.showMessageDialog(null, "�ù��������Ѵ���,�����ظ����", "", JOptionPane.ERROR_MESSAGE);
			} else if (resultMessage == ResultMessage.NOTCONNECTED) {
				company.getLabelSalarySuccess().setText("");
				JOptionPane.showMessageDialog(null, "�������...", "", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "��������ȷ����", "", JOptionPane.ERROR_MESSAGE);
		}
	}

}
