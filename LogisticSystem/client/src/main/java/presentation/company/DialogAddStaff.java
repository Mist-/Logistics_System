package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.enums.UserRole;
import data.message.ResultMessage;
import data.vo.StaffVO;

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
	 JButton finish = null;
	 JLabel labelIdCardNum = null;
	 JTextField idCardNum = null;
	 companyManage company = null;
	 //从界面传过来机构的名称instituion
	 String stringName,stringSerialNum,stringPhoneNum,instituion,stringIdCardNum,userRole;
	 boolean gender;
	 ResultMessage resultMessage = null;
	 CompanyBLController controller = null;

	public DialogAddStaff(companyManage company, String institution, String userRole){
		this.company = company;
		this.instituion = institution;
		this.userRole = userRole;
		controller =  new CompanyBLController();
		finish = new JButton("确认");
		jdialog = new JDialog(this,"添加员工");
		labelName = new JLabel("姓名:");
		labelSerialNum = new JLabel("员工号码:");
		labelGender = new JLabel("性别:");
		labelPhoneNum = new JLabel("联系电话:");
		man = new JRadioButton("男",true);
		woman = new JRadioButton("女");
		bg = new ButtonGroup();
		name = new JTextField();
		serialNum = new JTextField();
		phoneNum = new JTextField();
		labelIdCardNum = new JLabel("身份证:");
		idCardNum = new JTextField();
		labelName.setBounds(10, 10, 60, 30);
		name.setBounds(80,10,100,30);
		labelSerialNum.setBounds(10,50,60,30);
		serialNum.setBounds(80, 50, 100, 30);
		labelGender.setBounds(10,90,60,30);
		man.setBounds(80, 90, 40, 30);
		woman.setBounds(140, 90, 40, 30);
		labelPhoneNum.setBounds(10, 130, 60, 30);
		phoneNum.setBounds(80, 130, 100, 30);
		labelIdCardNum.setBounds(10,170,60,30);
		idCardNum.setBounds(80,170,100,30);
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
		jdialog.add(labelIdCardNum);
		jdialog.add(idCardNum);
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
		stringIdCardNum = idCardNum.getText();
		if(man.isSelected()){
			gender = false;
		}
		else {
			gender = true;
		}
		if(controller.isNum(stringSerialNum)&&controller.isNum(stringPhoneNum)) {
			//根据界面信息创建一个新的staffVO
			StaffVO staffVO = new StaffVO();
			staffVO.setId(Long.valueOf(stringSerialNum));
			staffVO.setName(stringName);
			staffVO.setInstitution(controller.longInstitution(instituion));
			staffVO.setIdcardNum(stringIdCardNum);
			staffVO.setGender(gender);
			staffVO.setPhoneNum(stringPhoneNum);
			if(userRole!=null) {
				staffVO.setUserRole(UserRole.valueOf(userRole));
			}
			resultMessage = controller.addStaff(staffVO);
			//判断是否成功添加
			if (resultMessage == ResultMessage.SUCCESS) {
				company.labelStaffSuccess.setText("添加成功!");
				jdialog.dispose();
			} else if (resultMessage == ResultMessage.EXIST) {
				company.labelStaffSuccess.setText("");
				JOptionPane.showMessageDialog(null, "员工已存在!", "", JOptionPane.ERROR_MESSAGE);
			} else if (resultMessage == ResultMessage.FAILED) {
				company.labelStaffSuccess.setText("");
				JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "请输入正确信息!", "", JOptionPane.ERROR_MESSAGE);
		}
	}
}
