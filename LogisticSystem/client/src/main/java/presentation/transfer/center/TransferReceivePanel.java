/*
 * Created by JFormDesigner on Mon Nov 30 21:05:40 CST 2015
 */

package presentation.transfer.center;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.enums.POType;
import data.message.ResultMessage;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.DeliveryListVO;
import data.vo.EntruckListVO;
import data.vo.TransferListVO;
import businesslogic.service.Transfer.center.TransferReceiveService;

/**
 * @author sunxu
 */
public class TransferReceivePanel extends JPanel {
	TransferReceiveService transferReceive;
	ArrivalListVO arriveList;
	ArrivalVO arrival;
	TransferListVO transferListVO;
	EntruckListVO entruckListVO;

	public void showArriveList() {
		arriveList = transferReceive.getCheckedArrivalList();
		if(arriveList == null){//如果没有成功获取，则跳过后面步骤
			return;
		}
		DefaultTableModel arriveListModel = new DefaultTableModel(
				arriveList.info, arriveList.header);
		arriveListTabble.setModel(arriveListModel);

		arriveListTabble.repaint();
	}

	public TransferReceivePanel(TransferReceiveService transferReceive) {
		this.transferReceive = transferReceive;
		initComponents();
		//showArriveList(); 本panel不再查找新生成的到达单，改为入库时查找
		selectArrival.setEnabled(false);
		this.setVisible(true);
	}

	private void createStorageInMouseClicked(MouseEvent e) {
		
	}



	private void selectArrivalMouseClicked(MouseEvent e) {
		if (selectArrival.isEnabled()) {
			saveArrival.setVisible(false);
			doArrive.setVisible(true);
			modifyStatus.setEnabled(false);
			statusBox.setEnabled(false);
			int row = arriveListTabble.getSelectedRow();
			String info = (String) arriveListTabble.getValueAt(row, 0);
			long id = Long.parseLong(info);
			arrival = transferReceive.chooseArrival(id);
			setArrivalVO();
			remove(startPane);
			add(arrivalVO);
			arrivalVO.validate();
			arrivalVO.updateUI();
			this.repaint();
		}

	}

	private void setArrivalVO() {
		// 设置arrivalVO
		transferID.setText(arrival.getDeliveryListNum());
		from.setText(arrival.getFromName());
		arrivalDate.setText(arrival.getDate());
		DefaultTableModel model = new DefaultTableModel(arrival.getOrderAndStatus(),arrival.getHeader());
		arrivalTable.setModel(model);
		arrivalTable.updateUI();
		String[] item = {"请选择状态","完整","破损","丢失"};
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>(item);
		statusBox.setModel(model1);
		statusBox.updateUI();
		transferID.setEnabled(false);
		from.setEnabled(false);
		arrivalDate.setEnabled(false);
		remove(startPane);
		if(deliveryVO != null)
		remove(deliveryVO);
		add(arrivalVO,BorderLayout.CENTER);
		arrivalVO.updateUI();
		arrivalVO.setVisible(true);
	}

	private void setTransferListVO() {
		// 设置中转单显示信息
		listID.setText(transferListVO.transferListID);
		fromName.setText(transferListVO.transferCenter);
		destName.setText(transferListVO.targetName);
		staffName.setText(transferListVO.staff);
		loadDate.setText(transferListVO.date);
		vehicleID.setText(transferListVO.vehicleCode);
		transferType.setText(transferListVO.transferType);
		DefaultTableModel model = new DefaultTableModel(transferListVO.orderAndPosition,transferListVO.header);
		orderInfoTable.setModel(model);
		orderInfoTable.updateUI();
		fee.setVisible(true);
		fee.setText(transferListVO.fee);
		label14.setVisible(true);
		setTextField(false);
	}

	private void setEntruckListVO() {
		// 设置装车单显示信息
		listID.setText(entruckListVO.entruckListID);
		fromName.setText(entruckListVO.fromName);
		destName.setText(entruckListVO.destName);
		staffName.setText(entruckListVO.monitorName);
		loadDate.setText(entruckListVO.loadingDate);
		vehicleID.setText(entruckListVO.vehicleID);
		transferType.setText("汽运");
		DefaultTableModel model = new DefaultTableModel(entruckListVO.info,entruckListVO.header);
		orderInfoTable.setModel(model);
		orderInfoTable.updateUI();
		fee.setVisible(false);
		label14.setVisible(false);
		setTextField(false);
	}
	
	private void setTextField(boolean b){
		listID.setEditable(b);
		fromName.setEditable(b);
		destName.setEditable(b);
		staffName.setEditable(b);
		loadDate.setEditable(b);
		vehicleID.setEditable(b);
		transferType.setEditable(b);
		orderInfoTable.setEnabled(false);
		fee.setEditable(b);
		listID.setEnabled(b);
		fromName.setEnabled(b);
		destName.setEnabled(b);
		staffName.setEnabled(b);
		loadDate.setEnabled(b);
		vehicleID.setEnabled(b);
		transferType.setEnabled(b);
		fee.setEnabled(b);
	}
	
	
		

	private void doArriveMouseClicked(MouseEvent e) {
		try {
			ResultMessage r = transferReceive.doArrive();
			if(r == ResultMessage.SUCCESS){
			JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model = (DefaultTableModel) arriveListTabble.getModel();
			model.removeRow(arriveListTabble.getSelectedRow());
			arriveListTabble.updateUI();
			remove(arrivalVO);
			add(startPane,BorderLayout.CENTER);
			startPane.setVisible(true);}
			else{
				JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, "网络连接中断，请稍后再试", "提示", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
	}

	
	/**
	 * 根据单号搜索装车单，到达单
	 * @param e
	 */
	private void searchListMouseClicked(MouseEvent e) {
		String input = deliveryID.getText();
		long id = -1;
		try {
			id = Long.parseLong(input);
		} catch (NumberFormatException e2) {
			deliveryID.setText("单号格式错误");
			return;
		}
		if (transfer.isSelected()) {
			transferListVO = transferReceive.getTransferList(id);
			if (transferListVO == null) {
				deliveryID.setText("单号不存在");
				return;
			}else{
				setTransferListVO();
			}
		} else {
			entruckListVO = transferReceive.getEntruckList(id);
			if (entruckListVO == null) {
				deliveryID.setText("单号不存在");
				return;
			}else{
				setEntruckListVO();
			}
		}
		remove(startPane);
		if (arrivalVO != null)
			remove(arrivalVO);
		add(deliveryVO);
		deliveryVO.validate();
		deliveryVO.updateUI();
		deliveryVO.setVisible(true);
		this.repaint();
	}



	private void cancelArrivalMouseClicked(MouseEvent e) {
		remove(arrivalVO);
		add(startPane);

		startPane.validate();
		startPane.updateUI();
		this.repaint();
	}

	private void entruckMouseClicked(MouseEvent e) {
		transfer.setSelected(false);
		entruck.setSelected(true);
	}

	private void transferMouseReleased(MouseEvent e) {
		transfer.setSelected(true);
		entruck.setSelected(false);
	}

	private void deliveryIDMouseReleased(MouseEvent e) {
		deliveryID.setText("");
	}

	private void arriveListTabbleMouseReleased(MouseEvent e) {
		selectArrival.setEnabled(true);
	}

	private void transferCancelMouseReleased(MouseEvent e) {
		remove(deliveryVO);
		add(startPane);

		startPane.validate();
		startPane.updateUI();
		this.repaint();
	}

	private void createArrivalMouseReleased(MouseEvent e) {
		if (entruck.isSelected()) {
			arrival = transferReceive.createArriveList(POType.ENTRUCK, entruckListVO);
			
		}else{
			arrival = transferReceive.createArriveList(POType.TRANSFERLIST, transferListVO);
		}
		
		doArrive.setVisible(false);
		saveArrival.setVisible(true);
		statusBox.setEnabled(true);
		modifyStatus.setEnabled(true);
		setArrivalVO();
	}

	private void saveArrivalMouseReleased(MouseEvent e) {
		ResultMessage result = transferReceive.saveArriveList(arrival);
		if (result == ResultMessage.SUCCESS) {
			JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			remove(arrivalVO);
			add(startPane,BorderLayout.CENTER);
			deliveryID.setText("");
			startPane.updateUI();
			startPane.setVisible(true);
		}
	}

	/**
	 * 修改订单到达状态
	 * @param e
	 */
	private void modifyStatusMouseReleased(MouseEvent e) {
		String status = (String) statusBox.getSelectedItem();
		if(!status.equals("请选择状态")){
			int[] items = arrivalTable.getSelectedRows();
			String[][] info = arrival.getOrderAndStatus();
			for(int i = 0; i < items.length;i++){
				info[items[i]][1] = status;
			}
		}
		
		DefaultTableModel model = (DefaultTableModel) arrivalTable.getModel();
		model.setDataVector(arrival.getOrderAndStatus(), arrival.getHeader());
		arrivalTable.setModel(model);
		arrivalTable.updateUI();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		startPane = new JTabbedPane();
		searchListPanel = new JPanel();
		label5 = new JLabel();
		deliveryID = new JTextField();
		entruck = new JRadioButton();
		transfer = new JRadioButton();
		searchList = new JButton();
		arrivalVO = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		arrivalTable = new JTable();
		label1 = new JLabel();
		transferID = new JTextField();
		label2 = new JLabel();
		from = new JTextField();
		label3 = new JLabel();
		arrivalDate = new JTextField();
		statusBox = new JComboBox();
		modifyStatus = new JButton();
		label4 = new JLabel();
		cancelArrival2 = new JButton();
		saveArrival = new JButton();
		deliveryVO = new JTabbedPane();
		transferVO = new JPanel();
		scrollPane3 = new JScrollPane();
		orderInfoTable = new JTable();
		label7 = new JLabel();
		listID = new JTextField();
		label12 = new JLabel();
		fromName = new JTextField();
		label8 = new JLabel();
		fee = new JTextField();
		label9 = new JLabel();
		loadDate = new JTextField();
		label10 = new JLabel();
		vehicleID = new JTextField();
		label11 = new JLabel();
		transferType = new JTextField();
		label13 = new JLabel();
		destName = new JTextField();
		staffName = new JTextField();
		label14 = new JLabel();
		transferCancel = new JButton();
		createArrival = new JButton();
		arrivelistPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		arriveListTabble = new JTable();
		selectArrival = new JButton();
		doArrive = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== startPane ========
		{
			startPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== searchListPanel ========
			{

				//---- label5 ----
				label5.setText("\u8bf7\u8f93\u5165\u8fd0\u8f93\u5355\u53f7");
				label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- deliveryID ----
				deliveryID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				deliveryID.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						deliveryIDMouseReleased(e);
					}
				});

				//---- entruck ----
				entruck.setText("\u88c5\u8f66\u5355");
				entruck.setSelected(true);
				entruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				entruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						entruckMouseClicked(e);
					}
				});

				//---- transfer ----
				transfer.setText("\u4e2d\u8f6c\u5355");
				transfer.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				transfer.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						transferMouseReleased(e);
					}
				});

				//---- searchList ----
				searchList.setText("\u67e5\u627e");
				searchList.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				searchList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						searchListMouseClicked(e);
					}
				});

				GroupLayout searchListPanelLayout = new GroupLayout(searchListPanel);
				searchListPanel.setLayout(searchListPanelLayout);
				searchListPanelLayout.setHorizontalGroup(
					searchListPanelLayout.createParallelGroup()
						.addGroup(searchListPanelLayout.createSequentialGroup()
							.addGap(128, 128, 128)
							.addComponent(label5, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(searchListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(searchListPanelLayout.createSequentialGroup()
									.addComponent(entruck)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(transfer))
								.addComponent(deliveryID, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
							.addGap(18, 18, 18)
							.addComponent(searchList, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(314, Short.MAX_VALUE))
				);
				searchListPanelLayout.setVerticalGroup(
					searchListPanelLayout.createParallelGroup()
						.addGroup(searchListPanelLayout.createSequentialGroup()
							.addGap(95, 95, 95)
							.addGroup(searchListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(deliveryID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(searchList))
							.addGap(18, 18, 18)
							.addGroup(searchListPanelLayout.createParallelGroup()
								.addComponent(transfer)
								.addComponent(entruck))
							.addContainerGap(145, Short.MAX_VALUE))
				);
			}
			startPane.addTab("\u641c\u7d22\u8fd0\u8f93\u5355", searchListPanel);
		}
		add(startPane, BorderLayout.CENTER);

		//======== arrivalVO ========
		{
			arrivalVO.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== panel1 ========
			{
				panel1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//======== scrollPane1 ========
				{

					//---- arrivalTable ----
					arrivalTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane1.setViewportView(arrivalTable);
				}

				//---- label1 ----
				label1.setText("\u8fd0\u8f6c\u5355\u7f16\u53f7\uff1a");
				label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- transferID ----
				transferID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- from ----
				from.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label3 ----
				label3.setText("\u65e5\u671f\uff1a");
				label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- arrivalDate ----
				arrivalDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- statusBox ----
				statusBox.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- modifyStatus ----
				modifyStatus.setText("\u4fee\u6539\u72b6\u6001");
				modifyStatus.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				modifyStatus.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						modifyStatusMouseReleased(e);
					}
				});

				//---- label4 ----
				label4.setText("\u51fa\u53d1\u5730\uff1a");
				label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- cancelArrival2 ----
				cancelArrival2.setText("\u53d6\u6d88");
				cancelArrival2.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
				cancelArrival2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				cancelArrival2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelArrivalMouseClicked(e);
					}
				});

				//---- saveArrival ----
				saveArrival.setText("\u4fdd\u5b58");
				saveArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						saveArrivalMouseReleased(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addGap(11, 11, 11)
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(panel1Layout.createSequentialGroup()
									.addGroup(panel1Layout.createParallelGroup()
										.addGroup(panel1Layout.createSequentialGroup()
											.addGap(130, 130, 130)
											.addComponent(label2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
										.addGroup(panel1Layout.createSequentialGroup()
											.addComponent(label1)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(transferID, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label4)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(from, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label3)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addGap(18, 18, 18)
											.addComponent(statusBox, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(modifyStatus, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane1))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(cancelArrival2, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(saveArrival, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
							.addContainerGap(17, Short.MAX_VALUE))
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup()
								.addComponent(statusBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(panel1Layout.createSequentialGroup()
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGroup(panel1Layout.createSequentialGroup()
											.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(label1)
												.addComponent(transferID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label4)
												.addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label3)
												.addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
										.addGroup(panel1Layout.createSequentialGroup()
											.addComponent(modifyStatus, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
											.addGap(4, 4, 4)))
									.addComponent(label2)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(0, 0, Short.MAX_VALUE)
									.addComponent(saveArrival, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(cancelArrival2)
									.addContainerGap())
								.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
				);
			}
			arrivalVO.addTab("\u5230\u8fbe\u5355", panel1);
		}

		//======== deliveryVO ========
		{
			deliveryVO.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== transferVO ========
			{

				//======== scrollPane3 ========
				{

					//---- orderInfoTable ----
					orderInfoTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane3.setViewportView(orderInfoTable);
				}

				//---- label7 ----
				label7.setText("\u8fd0\u8f93\u5355\u7f16\u53f7\uff1a");
				label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- listID ----
				listID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label12 ----
				label12.setText("\u51fa\u53d1\u5730\uff1a");
				label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- fromName ----
				fromName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label8 ----
				label8.setText("\u8fd0\u8f93\u65b9\u5f0f\uff1a");
				label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- fee ----
				fee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label9 ----
				label9.setText("\u76d1\u88c5\u5458\uff1a");
				label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- loadDate ----
				loadDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label10 ----
				label10.setText("\u8f66\u8f86\u4ee3\u53f7\uff1a");
				label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- vehicleID ----
				vehicleID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label11 ----
				label11.setText("\u51fa\u53d1\u65e5\u671f\uff1a");
				label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- transferType ----
				transferType.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label13 ----
				label13.setText("\u76ee\u7684\u5730\uff1a");
				label13.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

				//---- label14 ----
				label14.setText("\u8d39\u7528\uff1a");
				label14.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

				//---- transferCancel ----
				transferCancel.setText("\u53d6\u6d88");
				transferCancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						transferCancelMouseReleased(e);
					}
				});

				//---- createArrival ----
				createArrival.setText("\u5230\u8fbe");
				createArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						createArrivalMouseReleased(e);
					}
				});

				GroupLayout transferVOLayout = new GroupLayout(transferVO);
				transferVO.setLayout(transferVOLayout);
				transferVOLayout.setHorizontalGroup(
					transferVOLayout.createParallelGroup()
						.addGroup(transferVOLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(transferVOLayout.createParallelGroup()
								.addComponent(label7)
								.addComponent(label12, GroupLayout.Alignment.TRAILING)
								.addComponent(label13, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(label9, GroupLayout.Alignment.TRAILING))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(transferVOLayout.createSequentialGroup()
									.addGroup(transferVOLayout.createParallelGroup()
										.addComponent(listID, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addComponent(fromName, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
									.addGroup(transferVOLayout.createParallelGroup()
										.addGroup(transferVOLayout.createSequentialGroup()
											.addGap(32, 32, 32)
											.addComponent(label10))
										.addGroup(GroupLayout.Alignment.TRAILING, transferVOLayout.createSequentialGroup()
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label11))))
								.addGroup(transferVOLayout.createSequentialGroup()
									.addComponent(destName, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label8))
								.addGroup(transferVOLayout.createSequentialGroup()
									.addComponent(staffName, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label14)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(fee, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
								.addComponent(transferType, GroupLayout.Alignment.TRAILING)
								.addComponent(vehicleID, GroupLayout.Alignment.TRAILING)
								.addComponent(loadDate, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
							.addGroup(transferVOLayout.createParallelGroup()
								.addComponent(transferCancel, GroupLayout.Alignment.TRAILING)
								.addComponent(createArrival, GroupLayout.Alignment.TRAILING))
							.addContainerGap())
						.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
				);
				transferVOLayout.setVerticalGroup(
					transferVOLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, transferVOLayout.createSequentialGroup()
							.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(transferVOLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(loadDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(label7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(listID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(vehicleID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(fromName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(transferType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label13)
										.addComponent(destName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(staffName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(fee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label14))
									.addGap(18, 18, 18))
								.addGroup(transferVOLayout.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(createArrival)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(transferCancel)
									.addGap(9, 9, 9)))
							.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(2, 2, 2))
				);
			}
			deliveryVO.addTab("\u8fd0\u8f93\u5355", transferVO);
		}

		//======== arrivelistPanel ========
		{

			//======== scrollPane2 ========
			{

				//---- arriveListTabble ----
				arriveListTabble.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				arriveListTabble.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				arriveListTabble.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						arriveListTabbleMouseReleased(e);
					}
				});
				scrollPane2.setViewportView(arriveListTabble);
			}

			//---- selectArrival ----
			selectArrival.setText("\u67e5\u770b");
			selectArrival.setIcon(new ImageIcon(getClass().getResource("/icons/see_24x24.png")));
			selectArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			selectArrival.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectArrivalMouseClicked(e);
				}
			});

			GroupLayout arrivelistPanelLayout = new GroupLayout(arrivelistPanel);
			arrivelistPanel.setLayout(arrivelistPanelLayout);
			arrivelistPanelLayout.setHorizontalGroup(
				arrivelistPanelLayout.createParallelGroup()
					.addGroup(arrivelistPanelLayout.createSequentialGroup()
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(selectArrival, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
			);
			arrivelistPanelLayout.setVerticalGroup(
				arrivelistPanelLayout.createParallelGroup()
					.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addGroup(arrivelistPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(selectArrival)
						.addContainerGap(275, Short.MAX_VALUE))
			);
		}

		//---- doArrive ----
		doArrive.setText("\u786e\u8ba4\u5230\u8fbe");
		doArrive.setIcon(new ImageIcon(getClass().getResource("/icons/ok_24x24.png")));
		doArrive.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
		doArrive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doArriveMouseClicked(e);
			}
		});
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JTabbedPane startPane;
	private JPanel searchListPanel;
	private JLabel label5;
	private JTextField deliveryID;
	private JRadioButton entruck;
	private JRadioButton transfer;
	private JButton searchList;
	private JTabbedPane arrivalVO;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable arrivalTable;
	private JLabel label1;
	private JTextField transferID;
	private JLabel label2;
	private JTextField from;
	private JLabel label3;
	private JTextField arrivalDate;
	private JComboBox statusBox;
	private JButton modifyStatus;
	private JLabel label4;
	private JButton cancelArrival2;
	private JButton saveArrival;
	private JTabbedPane deliveryVO;
	private JPanel transferVO;
	private JScrollPane scrollPane3;
	private JTable orderInfoTable;
	private JLabel label7;
	private JTextField listID;
	private JLabel label12;
	private JTextField fromName;
	private JLabel label8;
	private JTextField fee;
	private JLabel label9;
	private JTextField loadDate;
	private JLabel label10;
	private JTextField vehicleID;
	private JLabel label11;
	private JTextField transferType;
	private JLabel label13;
	private JTextField destName;
	private JTextField staffName;
	private JLabel label14;
	private JButton transferCancel;
	private JButton createArrival;
	private JPanel arrivelistPanel;
	private JScrollPane scrollPane2;
	private JTable arriveListTabble;
	private JButton selectArrival;
	private JButton doArrive;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
