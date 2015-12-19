package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 添加员工工资时的临时界面
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
		jdialog = new JDialog(company,"添加工资");
		institutions = new String[]{"快递员","普通财务人员","营业厅业务员","中转中心业务员","仓库管理员","货车驾驶员"};
		institution = new JComboBox<String>(institutions);
		institution.setBounds(65, 35, 100, 30);
		types = new String[]{"计次","月付"};
		type = new JComboBox<String>(types);
		type.setBounds(330, 35, 55, 30);
		salary = new JTextField();
		salary.setBounds(205, 35, 60, 30);
		finish = new JButton("确认");
		finish.setBounds(175, 85, 66, 30);
		labelInstituion = new JLabel("机构选择");
		labelInstituion.setBounds(5, 25, 60, 50);
		labelSalary = new JLabel("工资");
		labelSalary.setBounds(175, 25, 60, 50);
		labelType = new JLabel("结算方式");
		labelType.setBounds(270, 25, 60, 50);
		// 将所有控件添加到JDialog中
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
			//根据resultMessage类型对界面进行输出
			if (resultMessage == ResultMessage.SUCCESS) {
				company.getLabelSalarySuccess().setText("添加成功!");
				company.initSalaryTable();
				jdialog.dispose();
			} else if (resultMessage == ResultMessage.EXIST) {
				company.getLabelSalarySuccess().setText("");
				JOptionPane.showMessageDialog(null, "该工资类型已存在,请勿重复添加", "", JOptionPane.ERROR_MESSAGE);
			} else if (resultMessage == ResultMessage.NOTCONNECTED) {
				company.getLabelSalarySuccess().setText("");
				JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入正确工资", "", JOptionPane.ERROR_MESSAGE);
		}
	}

}
