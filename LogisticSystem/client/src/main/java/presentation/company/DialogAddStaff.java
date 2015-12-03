package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 添加员工时的临时界面
 */
public class DialogAddStaff extends JDialog{
	 JDialog jdialog = null;
	 JLabel labelName = null;
	 JTextField name = null;
	 JLabel labelSerialNum = null;
	 JTextField serialNum = null;
	 JLabel labelGender = null;
	 JRadioButton man = null;
	 JRadioButton woman = null;
	 ButtonGroup bg = null;
	 JLabel labelPhoneNum = null;
	 JTextField phoneNum = null;
	 JLabel labelDate = null;
	 JTextField year = null;
	 JLabel labelYear = null;
	 JTextField month = null;
	 JLabel labelMonth = null;
	 JLabel labelDay = null;
	 JTextField day = null;
	 JButton finish = null;
	 companyManage company = null;
	 //从界面传过来机构的名称instituion
	 String stringName,stringSerialNum,stringPhoneNum,stringYear,stringMonth,stringDay,instituion;
	 boolean gender;
	 ResultMessage resultMessage = null;
	 CompanyBLController controller = null;

	public DialogAddStaff(companyManage company, String institution){
		this.company = company;
		this.instituion = institution;
		controller =  new CompanyBLController();
		finish = new JButton("确认");
		jdialog = new JDialog(this,"添加员工");
		labelName = new JLabel("姓名:");
		labelSerialNum = new JLabel("员工号码:");
		labelGender = new JLabel("性别:");
		labelPhoneNum = new JLabel("联系电话:");
		labelDate = new JLabel("出生日期:");
		labelYear = new JLabel("年");
		labelMonth = new JLabel("月");
		labelDay = new JLabel("日"); 
		man = new JRadioButton("男",true);
		woman = new JRadioButton("女");
		bg = new ButtonGroup();
		name = new JTextField();
		serialNum = new JTextField();
		phoneNum = new JTextField();
		year = new JTextField();
		month = new JTextField();
		day = new JTextField();
		labelName.setBounds(10, 10, 60, 30);
		name.setBounds(80,10,100,30);
		labelSerialNum.setBounds(10,50,60,30);
		serialNum.setBounds(80, 50, 100, 30);
		labelGender.setBounds(10,90,60,30);
		man.setBounds(80, 90, 40, 30);
		woman.setBounds(140, 90, 40, 30);
		labelPhoneNum.setBounds(10, 130, 60, 30);
		phoneNum.setBounds(80, 130, 100, 30);
		labelDate.setBounds(10,170,60,30);
		year.setBounds(80, 170, 40, 30);
		labelYear.setBounds(120, 170, 20, 30);
		month.setBounds(140, 170, 40, 30);
		labelMonth.setBounds(180, 170, 20, 30);
		day.setBounds(200, 170, 40, 30);
		labelDay.setBounds(240,170,20,30);
		finish.setBounds(110, 230, 60, 35);
		bg.add(man);
		bg.add(woman);
        // 将所有控件添加到JDialog中
		jdialog.setSize(280, 350);
		jdialog.add(labelName);
		jdialog.add(name);
		jdialog.add(labelSerialNum);
		jdialog.add(serialNum);
		jdialog.add(labelGender);
		jdialog.add(man);
		jdialog.add(woman);
		jdialog.add(labelPhoneNum);
		jdialog.add(phoneNum);
		jdialog.add(labelDate);
		jdialog.add(year);
		jdialog.add(labelYear);
		jdialog.add(month);
		jdialog.add(labelMonth);
		jdialog.add(day);
		jdialog.add(labelDay);
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
		stringName = name.getText();
		stringSerialNum = serialNum.getText();
		stringPhoneNum = phoneNum.getText();
		stringYear = year.getText();
		stringMonth = month.getText();
		stringDay = day.getText();
		if(man.isSelected()){
			gender = false;
		}
		else {
			gender = true;
		}
        resultMessage = controller.addStaff(instituion,stringSerialNum,gender,stringName,stringPhoneNum);
		if(resultMessage== ResultMessage.SUCCESS){
			company.labelStaffSuccess.setText("添加成功!");
		}
		else if(resultMessage== ResultMessage.EXIST){
			company.labelStaffSuccess.setText("已经存在!");
		}
		else if(resultMessage== ResultMessage.FAILED){
			company.labelStaffSuccess.setText("网络错误!");
		}
		jdialog.dispose();
	}
}
