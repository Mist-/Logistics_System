/*
 * Created by JFormDesigner on Tue Dec 22 19:20:42 CST 2015
 */

package presentation.transfer.hall;

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

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addComponent(year, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label3)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(month, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label4)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(day, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label5)
							.addGap(18, 18, 18)
							.addComponent(getOrder, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
							.addComponent(label6)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(fee, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addComponent(saveButton)
							.addGap(85, 85, 85))
						.addGroup(layout.createSequentialGroup()
							.addComponent(senderBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(140, 633, Short.MAX_VALUE))))
				.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(senderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label1, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label3)
						.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label4)
						.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label5)
						.addComponent(getOrder)
						.addComponent(label6)
						.addComponent(fee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(saveButton))
					.addGap(18, 18, 18)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
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
