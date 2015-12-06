package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.message.ResultMessage;
import data.vo.CityTransVO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * ��ӳ�����Ϣʱ����ʱ����
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
		jdialog = new JDialog(company,"��ӳ�����Ϣ");
		citys = controller.getCitys();
		boxFromCity = new JComboBox<String>(citys);
		boxToCity = new JComboBox<String>(citys);
		labelFromCity = new JLabel("��������:");
		labelToCity = new JLabel("�������:");
		labelTrunk = new JLabel("�����۸�:");
		labelTrain = new JLabel("�𳵼۸�:");
		labelPlane = new JLabel("�ɻ��۸�:");
		labelDistance = new JLabel("����:");
		trunk = new JTextField();
		train = new JTextField();
		plane = new JTextField();
		distances = new JTextField();
		finish = new JButton("ȷ��");
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
		// �����пؼ���ӵ�JDialog��
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
		//�ɽ���������½�һ��CityTransVO
		CityTransVO cityTransVO = new CityTransVO(fromCity,toCity,distance,trunkPrice,trainPrice,planePrice);
		resultMessage = controller.addCityTransInfo(cityTransVO);
		//����resultMessage���ͶԽ���������
		if(resultMessage == ResultMessage.SUCCESS){
			company.labelStaffSuccess.setText("��ӳɹ�!");
		}
		else if(resultMessage == ResultMessage.EXIST){
			company.labelStaffSuccess.setText("");
			JOptionPane.showMessageDialog(null,"������Ϣ�Ѵ���,�����ظ����","",JComponent.ERROR);
		}
		else if(resultMessage == ResultMessage.NOTCONNECTED){
			company.labelStaffSuccess.setText("");
			JOptionPane.showMessageDialog(null,"�������...","",JComponent.ERROR);
		}
		jdialog.dispose();
	}
}
