package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;
import data.vo.CityTransVO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 添加城市信息时的临时界面
 */
public class DialogAddCity extends JDialog{
	JDialog jdialog = null;
	String [] citys = null;
	JComboBox<String> boxFromCity = null;
	JComboBox<String> boxToCity = null;
	JLabel labelFromCity = null;
	JLabel labelToCity = null;
	JLabel labelTrunk = null;
	JLabel labelTrain = null;
	JLabel labelPlane = null;
	JLabel labelDistance = null;
	JButton finish = null;
	JTextField trunk = null;
	JTextField train = null;
	JTextField plane = null;
	JTextField distances = null;
	companyManage company = null;
	String fromCity,toCity;
	double distance,trunkPrice,trainPrice,planePrice;
	CompanyBLController controller = null;
	ResultMessage resultMessage = null;
	public DialogAddCity(companyManage company){
		this.company = company;
		controller = new CompanyBLController();
		jdialog = new JDialog(company,"添加城市信息");
		citys = controller.getCitys();
		boxFromCity = new JComboBox<String>(citys);
		boxToCity = new JComboBox<String>(citys);
		labelFromCity = new JLabel("发车城市:");
		labelToCity = new JLabel("到达城市:");
		labelTrunk = new JLabel("货车价格:");
		labelTrain = new JLabel("火车价格:");
		labelPlane = new JLabel("飞机价格:");
		labelDistance = new JLabel("距离:");
		trunk = new JTextField();
		train = new JTextField();
		plane = new JTextField();
		distances = new JTextField();
		finish = new JButton("确认");
		labelFromCity.setBounds(25, 5, 70, 30);
		boxFromCity.setBounds(105, 5, 60, 30);
		labelToCity.setBounds(205,5,70,30);
		boxToCity.setBounds(285, 5, 60, 30);
		labelTrunk.setBounds(5,45,60,35);
		trunk.setBounds(70, 50, 35, 30);
		labelTrain.setBounds(110,45,60,35);
		train.setBounds(175,50,35,30);
		labelPlane.setBounds(215, 45, 60, 35);
		plane.setBounds(280, 50, 35, 30);
		labelDistance.setBounds(320,45,35,35);
		distances.setBounds(360, 50, 35, 30);;
		finish.setBounds(175,95,60,30);
		// 将所有控件添加到JDialog中
		jdialog.setSize(425, 195);
		jdialog.add(labelFromCity);
		jdialog.add(boxFromCity);
		jdialog.add(labelToCity);
		jdialog.add(boxToCity);
		jdialog.add(labelTrunk);
		jdialog.add(labelTrain);
		jdialog.add(labelPlane);
		jdialog.add(labelDistance);
		jdialog.add(trunk);
		jdialog.add(train);
		jdialog.add(plane);
		jdialog.add(distances);
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
		fromCity = (String) boxFromCity.getSelectedItem();
		toCity = (String) boxToCity.getSelectedItem();
		distance = Double.valueOf(distances.getText());
		trunkPrice = Double.valueOf(trunk.getText());
		trainPrice = Double.valueOf(train.getText());
		planePrice = Double.valueOf(plane.getText());
		//由界面的数据新建一个CityTransVO
		CityTransVO cityTransVO = new CityTransVO(fromCity,toCity,distance,trunkPrice,trainPrice,planePrice);
		resultMessage = controller.addCityTransInfo(cityTransVO);
		//根据resultMessage类型对界面进行输出
		if(resultMessage == ResultMessage.SUCCESS){
			company.labelStaffSuccess.setText("添加成功!");
		}
		else if(resultMessage == ResultMessage.EXIST){
			company.labelStaffSuccess.setText("");
			JOptionPane.showMessageDialog(null,"城市信息已存在,请勿重复添加","",JComponent.ERROR);
		}
		else if(resultMessage == ResultMessage.NOTCONNECTED){
			company.labelStaffSuccess.setText("");
			JOptionPane.showMessageDialog(null,"网络错误...","",JComponent.ERROR);
		}
		jdialog.dispose();
	}
}
