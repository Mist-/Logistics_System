package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;
import data.vo.CityTransVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 添加城市信息时的临时界面
 */
public class DialogAddCity extends JDialog{
	JPanel jPanel = null;
	JDialog jDialog = null;
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
		jPanel = new JPanel();
		controller = new CompanyBLController();
		jDialog = new JDialog(company,"添加城市信息");
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
		boxFromCity.setBounds(105, 5, 80, 30);
		labelToCity.setBounds(205,5,70,30);
		boxToCity.setBounds(285, 5, 80, 30);
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
		jDialog.setSize(425, 195);
		jDialog.add(jPanel, BorderLayout.CENTER);
		jPanel.add(labelFromCity);
		jPanel.add(boxFromCity);
		jPanel.add(labelToCity);
		jPanel.add(boxToCity);
		jPanel.add(labelTrunk);
		jPanel.add(labelTrain);
		jPanel.add(labelPlane);
		jPanel.add(labelDistance);
		jPanel.add(trunk);
		jPanel.add(train);
		jPanel.add(plane);
		jPanel.add(distances);
		jPanel.add(finish);
		jPanel.setLayout(null);
		jDialog.setModal(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setResizable(false);
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonEnsure(e);
			}
		});
		jDialog.setVisible(true);
	}

	private void buttonEnsure(MouseEvent e){
		fromCity = (String) boxFromCity.getSelectedItem();
		toCity = (String) boxToCity.getSelectedItem();
		if(!fromCity.equals(toCity)) {
			if (controller.isValidNum(distances.getText()) && controller.isValidNum(trunk.getText()) && controller.isValidNum(train.getText()) && controller.isValidNum(plane.getText())) {
				distance = Double.valueOf(distances.getText());
				trunkPrice = Double.valueOf(trunk.getText());
				trainPrice = Double.valueOf(train.getText());
				planePrice = Double.valueOf(plane.getText());
				//由界面的数据新建一个CityTransVO
				CityTransVO cityTransVO = new CityTransVO(fromCity, toCity, distance, trunkPrice, trainPrice, planePrice);
				resultMessage = controller.addCityTransInfo(cityTransVO);
				//根据resultMessage类型对界面进行输出
				if (resultMessage == ResultMessage.SUCCESS) {
					company.getLabelCitySuccess().setText("添加成功!");
					jDialog.dispose();
				} else if (resultMessage == ResultMessage.EXIST) {
					company.getLabelCitySuccess().setText("");
					JOptionPane.showMessageDialog(null, "城市信息已存在,请勿重复添加", "", JOptionPane.ERROR_MESSAGE);
				} else if (resultMessage == ResultMessage.NOTCONNECTED) {
					company.getLabelCitySuccess().setText("");
					JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确数值!", "", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "请选择正确城市!", "", JOptionPane.ERROR_MESSAGE);
		}
	}
}
