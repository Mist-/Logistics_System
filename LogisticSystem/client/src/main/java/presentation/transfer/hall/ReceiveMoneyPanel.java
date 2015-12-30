/*
 * Created by JFormDesigner on Tue Dec 22 19:20:42 CST 2015
 */

package presentation.transfer.hall;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

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
			int index = senderBox.getSelectedIndex();
			String  s = (String) senderBox.getItemAt(index);
			String date = getDate();
			if(date == null){
				JOptionPane.showMessageDialog(null, "日期输入错误", "提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}
			String[][] info = receiveMoney.getOrderAndFee(s, date);
			if(info.length == 0 || info == null){
				JOptionPane.showMessageDialog(null, "没有查询到订单信息", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			saveButton.setEnabled(true);
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
		SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
		String date =  y+"/"+m+"/"+d;
		try {
			Date dt = s.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
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
			tabbedPane1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{

					//---- table ----
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setRowSelectionAllowed(false);
					table.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane1.setViewportView(table);
				}

				//---- label1 ----
				label1.setText("\u5feb\u9012\u5458\u59d3\u540d\uff1a");
				label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- senderBox ----
				senderBox.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				senderBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						senderBoxMouseClicked(e);
					}
				});

				//---- getOrder ----
				getOrder.setText("\u786e\u8ba4");
				getOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				getOrder.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						getOrderMouseReleased(e);
					}
				});

				//---- label2 ----
				label2.setText("\u65e5\u671f\uff1a");
				label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- year ----
				year.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label3 ----
				label3.setText("\u5e74");
				label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- month ----
				month.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label4 ----
				label4.setText("\u6708");
				label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- day ----
				day.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label5 ----
				label5.setText("\u65e5");
				label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- fee ----
				fee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label6 ----
				label6.setText("\u8d39\u7528\uff1a");
				label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- saveButton ----
				saveButton.setText("\u7ed3\u7b97");
				saveButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
						.addGroup(panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(label2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(year, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label3)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(month, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label4)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(day, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label5)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(getOrder)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(fee, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(label1)
									.addGap(3, 3, 3)
									.addComponent(senderBox, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(351, Short.MAX_VALUE))
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(senderBox)
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(2, 2, 2)
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(year)
								.addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(month, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(label4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(day, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(label5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(getOrder)
								.addComponent(saveButton)
								.addComponent(fee, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
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
