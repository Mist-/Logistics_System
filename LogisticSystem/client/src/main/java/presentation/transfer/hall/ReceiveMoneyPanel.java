/*
 * Created by JFormDesigner on Tue Dec 22 19:20:42 CST 2015
 */

package presentation.transfer.hall;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.message.ResultMessage;
import data.vo.ReceiptVO;
import businesslogic.service.Transfer.hall.ReceiveMoneyService;

/**
 * @author sunxu
 */
public class ReceiveMoneyPanel extends JPanel {
	ReceiveMoneyService receiveMoney;
	ReceiptVO receiptVO;
	
	public ReceiveMoneyPanel(ReceiveMoneyService receiveMoneyService) {
		receiveMoney =receiveMoneyService;
		initComponents();
		getOrder.setEnabled(false);
		saveButton.setEnabled(false);
		fee.setEnabled(false);
		setSenders();
	}
	
	private void setSenders(){
		try {
			String[] senders = receiveMoney.getSenders();
			if(senders == null){
				JOptionPane.showMessageDialog(null, "快递员列表获取失败", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(senders);
			senderBox.setModel(model);
			senderBox.updateUI();
			senderBox.repaint();
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "网络连接中断", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void senderBoxMouseClicked(MouseEvent e) {
		getOrder.setEnabled(true);
	}

	private void getOrderMouseReleased(MouseEvent e) {
		if(getOrder.isEnabled()){
			saveButton.setEnabled(true);
			int index = senderBox.getSelectedIndex();
			String  s = (String) senderBox.getItemAt(index);
			String[][] info = receiveMoney.getOrderAndFee(s, getDate());
			receiptVO = receiveMoney.createReceipt(info);
			fee.setText(receiptVO.getMoney()+"");
			String[] head = {"订单号","快递费（元）"};
			DefaultTableModel model = new DefaultTableModel(info,head);
			table.setModel(model);
			table.updateUI();
			table.repaint();
		}
	}
	
	private String getDate(){
		int year = 0,month = 0,day = 0;
		String y = this.year.getText();
		String m = this.month.getText();
		String d = this.day.getText();
		try{
		year = Integer.parseInt(y);
		month = Integer.parseInt(m);
		day = Integer.parseInt(d);
		
		if(year < 2000 || year > 3000 || month < 1 || month > 12 || day < 1 || day > 31){
			JOptionPane.showMessageDialog(null, "日期输入有误", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		}catch (NumberFormatException n){
			JOptionPane.showMessageDialog(null, "日期输入有误", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		y =  new DecimalFormat("0000").format(year);
		m = new DecimalFormat("00").format(month);
		d = new DecimalFormat("00").format(day);
		return y+"/"+m+"/"+d;
	}

	private void saveButtonMouseReleased(MouseEvent e) {
		if(saveButton.isEnabled()){
			try {
				ResultMessage result = receiveMoney.saveReceipt(receiptVO);
				if(result == ResultMessage.SUCCESS){
					JOptionPane.showMessageDialog(null, "结算完成", "提示", JOptionPane.INFORMATION_MESSAGE);
					saveButton.setEnabled(false);
					getOrder.setEnabled(false);
				}else{
					JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		table = new JTable();
		label1 = new JLabel();
		senderBox = new JComboBox();
		getOrder = new JButton();
		label2 = new JLabel();
		year = new JTextField();
		label3 = new JLabel();
		month = new JTextField();
		label4 = new JLabel();
		day = new JTextField();
		label5 = new JLabel();
		fee = new JTextField();
		label6 = new JLabel();
		saveButton = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{

					//---- table ----
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setRowSelectionAllowed(false);
					scrollPane1.setViewportView(table);
				}

				//---- label1 ----
				label1.setText("\u5feb\u9012\u5458\u59d3\u540d\uff1a");

				//---- senderBox ----
				senderBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						senderBoxMouseClicked(e);
					}
				});

				//---- getOrder ----
				getOrder.setText("\u786e\u8ba4");
				getOrder.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						getOrderMouseReleased(e);
					}
				});

				//---- label2 ----
				label2.setText("\u65e5\u671f\uff1a");

				//---- label3 ----
				label3.setText("\u5e74");

				//---- label4 ----
				label4.setText("\u6708");

				//---- label5 ----
				label5.setText("\u65e5");

				//---- label6 ----
				label6.setText("\u8d39\u7528\uff1a");

				//---- saveButton ----
				saveButton.setText("\u7ed3\u7b97");
				saveButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						saveButtonMouseReleased(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addGap(20, 20, 20)
							.addGroup(panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(label1)
									.addGap(3, 3, 3)
									.addComponent(senderBox, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(label2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(3, 3, 3)
									.addComponent(year, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(5, 5, 5)
									.addComponent(label3)
									.addGap(13, 13, 13)
									.addComponent(month, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, 0)
									.addComponent(label4)
									.addGap(8, 8, 8)
									.addComponent(day, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, 0)
									.addComponent(label5)
									.addGap(18, 18, 18)
									.addComponent(getOrder, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(272, 272, 272)
									.addComponent(label6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(fee, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
							.addGap(30, 30, Short.MAX_VALUE))
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addGroup(panel1Layout.createParallelGroup()
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(senderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11, 11, 11)
							.addGroup(panel1Layout.createParallelGroup()
								.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getOrder)
								.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(label6)
									.addComponent(fee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(saveButton))
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(5, 5, 5)
									.addGroup(panel1Layout.createParallelGroup()
										.addComponent(label2)
										.addComponent(label3)
										.addComponent(label4)
										.addComponent(label5))))
							.addGap(7, 7, 7)
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
				);
			}
			tabbedPane1.addTab("\u7ed3\u7b97\u7ba1\u7406", panel1);
		}
		add(tabbedPane1, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable table;
	private JLabel label1;
	private JComboBox senderBox;
	private JButton getOrder;
	private JLabel label2;
	private JTextField year;
	private JLabel label3;
	private JTextField month;
	private JLabel label4;
	private JTextField day;
	private JLabel label5;
	private JTextField fee;
	private JLabel label6;
	private JButton saveButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
