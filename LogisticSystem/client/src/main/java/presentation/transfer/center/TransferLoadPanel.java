/*
 * Created by JFormDesigner on Mon Nov 30 21:09:47 CST 2015
 */

package presentation.transfer.center;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.enums.StorageArea;
import data.message.ResultMessage;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;
import businesslogic.impl.transfer.TransferInfo;
import businesslogic.impl.transfer.center.TransferList;
import businesslogic.service.Transfer.center.TransferLoadService;

/**
 * @author sunxu
 */
public class TransferLoadPanel extends JPanel {
	TransferLoadService transferLoad;
	TransferLoadVO order;
	TransferListVO transferList;

	public TransferLoadPanel(TransferLoadService transferLoad) {
		this.transferLoad = transferLoad;
		initComponents();
		setTransferTypeBox();
		// 在未选择运输类型和目的地时，本面板上三个按钮均无效
		getOrderButton.setEnabled(false);
		removeOrderButton.setEnabled(false);
		createTransferButton.setEnabled(false);
		destBox.setEnabled(false);
	}

	private void setTransferTypeBox() {
		String[] type = TransferInfo.transferType;
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				type);
		transferTypeBox.setModel(model);
		transferTypeBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {// 监听，获取所选项，获取相应的目的地
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int item = transferTypeBox.getSelectedIndex();
					String s = (String) transferTypeBox.getItemAt(item);
					StorageArea type = TransferInfo.getTypeByString(s);
					setDestBox(type);
				}
			}
		});
		transferTypeBox.validate();
		transferTypeBox.updateUI();
		transferTypeBox.repaint();
	}

	private void setDestBox(StorageArea type) {// 根据所选运输类型，获取目的地
		ArrayList<String> des = transferLoad.chooseTransferType(type);
		String[] desArray = new String[des.size()];
		des.toArray(desArray);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				desArray);
		destBox.setModel(model);
		destBox.validate();
		destBox.updateUI();
		destBox.repaint();
		getOrderButton.setEnabled(true);// 获取订单信息按钮生效
		destBox.setEnabled(true);// 目的地列表生效
	}

	private void setOrderList() {
		DefaultTableModel model = new DefaultTableModel(order.getOrderInfo(),
				order.header);
		orderTable.setModel(model);
		orderTable.validate();
		orderTable.updateUI();
		orderTable.repaint();
	}

	private void getOrderButtonMouseReleased(MouseEvent e) {
		sort();
	}
	
	private void sort(){
		if (getOrderButton.isEnabled()) {
			int item = destBox.getSelectedIndex();
			if (item >= 0) {
				String desName = (String) destBox.getItemAt(item);
				System.out.println(desName);
				order = transferLoad.getOrder(desName);
				if (order != null) {
					setOrderList();
					removeOrderButton.setEnabled(true);// 移除按钮生效
					createTransferButton.setEnabled(true);// 生成中转单按钮生效
				} else {
					JOptionPane.showMessageDialog(null, "未能正确获取仓库信息", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	private void createTransferButtonMouseReleased(MouseEvent e) {
		if (createTransferButton.isEnabled()) {
			if (order == null) {
				JOptionPane.showMessageDialog(null, "未能正确获取仓库信息，无法生成中转单", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Vector<Vector<String>> info = order.getOrderInfo();
			if (info.size()== 0) {
				JOptionPane.showMessageDialog(null, "订单列表为空，无法生成中转单", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				transferList = transferLoad.createTransferList(order);
				if(transferList != null){
				setTransferList();
				setDisabled();
				}else{
					JOptionPane.showMessageDialog(null, "超过单次装运数量或重量上限", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private void setDisabled(){
		listID.setEnabled(false);
		centerID.setEnabled(false);
		centerName.setEnabled(false);
		destID.setEnabled(false);
		destName.setEnabled(false);
	}
	
	private void setTransferList(){
		if (transferList != null) {
			if(transferList.transferListID != null){
			listID.setText(transferList.transferListID);
			}
			else {
				listID.setText("保存后生成");
			}
			centerID.setText(transferList.transferCenterID);
			centerName.setText(transferList.transferCenter);
			destID.setText(transferList.target);
			destName.setText(transferList.targetName);
			date.setText(transferList.date);
			vehicleID.setText("请输入班次");
			staffName.setText(transferList.staff);
//			if (transferList.transferType.equals("汽运")) {
//				fee.setText("请输入司机姓名");
//				label12.setText("押运员");
//				label10.setText("车辆编号");
//			}else{
			fee.setText(transferList.fee);
//			label12.setText("费用");
//			label10.setText("班次");
//			}
			DefaultTableModel model = new DefaultTableModel(transferList.orderAndPosition,transferList.header);
			loadTable.setModel(model);
			loadTable.updateUI();
			
			remove(loadPane);
			add(transferListPane,BorderLayout.CENTER);
			transferListPane.updateUI();
			transferListPane.setVisible(true);
		}
	}

	private void cancelLoadMouseReleased(MouseEvent e) {
		remove(transferListPane);
		add(loadPane,BorderLayout.CENTER);
		loadPane.updateUI();
		loadPane.setVisible(true);
	}

	private void saveListMouseReleased(MouseEvent e) {
		transferList.fee = fee.getText();
		transferList.date = date.getText();
		transferList.vehicleCode = vehicleID.getText();
		System.out.println(transferList.fee);
		System.out.println(transferList.vehicleCode);
		if(transferList.fee.equals("") || transferList.fee.equals(0.0+"")|| transferList.vehicleCode.equals("请输入班次")|| transferList.vehicleCode.equals("")){
			JOptionPane.showMessageDialog(null, "请正确输入运费和班次", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			long result = transferLoad.saveTransferList(transferList);
			if (result != -1) {
				sort();
				JOptionPane.showMessageDialog(null, "保存成功！单号："+result, "提示", JOptionPane.INFORMATION_MESSAGE);
				remove(transferListPane);
				add(loadPane,BorderLayout.CENTER);
				loadPane.updateUI();
				loadPane.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, "网络连接中断", "提示", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
	}

	private void removeOrderButtonMouseReleased(MouseEvent e) {
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		Vector<Vector<String>> info = order.getOrderInfo();
		int[] rows = orderTable.getSelectedRows();
		for(int i = (rows.length-1) ; i >= 0  ; i--){
			System.out.println(rows[i]);
			info.remove(rows[i]);
		}
		model.setDataVector(info, order.header);
		orderTable.setModel(model);
		orderTable.updateUI();
		orderTable.repaint();
	}

	private void button1MouseReleased(MouseEvent e) {
		try {
			transferLoad.refreshStorageInfo();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		loadPane = new JTabbedPane();
		loadPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		orderTable = new JTable();
		label1 = new JLabel();
		transferTypeBox = new JComboBox();
		label2 = new JLabel();
		destBox = new JComboBox();
		createTransferButton = new JButton();
		removeOrderButton = new JButton();
		getOrderButton = new JButton();
		button1 = new JButton();
		transferListPane = new JTabbedPane();
		DeliveryListPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		loadTable = new JTable();
		listID = new JTextField();
		centerID = new JTextField();
		centerName = new JTextField();
		destID = new JTextField();
		destName = new JTextField();
		label6 = new JLabel();
		label5 = new JLabel();
		label9 = new JLabel();
		label8 = new JLabel();
		label7 = new JLabel();
		date = new JTextField();
		label4 = new JLabel();
		vehicleID = new JTextField();
		label10 = new JLabel();
		staffName = new JTextField();
		label11 = new JLabel();
		fee = new JTextField();
		label12 = new JLabel();
		saveList = new JButton();
		cancelLoad = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== loadPane ========
		{
			loadPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== loadPanel ========
			{

				//======== scrollPane1 ========
				{

					//---- orderTable ----
					orderTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane1.setViewportView(orderTable);
				}

				//---- label1 ----
				label1.setText("\u8fd0\u8f93\u7c7b\u578b\uff1a");
				label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- transferTypeBox ----
				transferTypeBox.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label2 ----
				label2.setText("\u76ee\u7684\u5730\uff1a");
				label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- destBox ----
				destBox.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- createTransferButton ----
				createTransferButton.setText("\u88c5\u8fd0");
				createTransferButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				createTransferButton.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
				createTransferButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						createTransferButtonMouseReleased(e);
					}
				});

				//---- removeOrderButton ----
				removeOrderButton.setText("\u79fb\u9664");
				removeOrderButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				removeOrderButton.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24x24.png")));
				removeOrderButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						removeOrderButtonMouseReleased(e);
					}
				});

				//---- getOrderButton ----
				getOrderButton.setText("\u641c\u7d22\u5e93\u5b58");
				getOrderButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				getOrderButton.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
				getOrderButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						getOrderButtonMouseReleased(e);
					}
				});

				//---- button1 ----
				button1.setText("\u5237\u65b0");
				button1.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
				button1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						button1MouseReleased(e);
					}
				});

				GroupLayout loadPanelLayout = new GroupLayout(loadPanel);
				loadPanel.setLayout(loadPanelLayout);
				loadPanelLayout.setHorizontalGroup(
					loadPanelLayout.createParallelGroup()
						.addGroup(loadPanelLayout.createSequentialGroup()
							.addComponent(label1)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(transferTypeBox, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label2)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(destBox, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addComponent(getOrderButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(467, Short.MAX_VALUE))
						.addGroup(loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(loadPanelLayout.createSequentialGroup()
									.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(createTransferButton, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
										.addComponent(button1, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
									.addContainerGap())
								.addComponent(removeOrderButton, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
				);
				loadPanelLayout.setVerticalGroup(
					loadPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(transferTypeBox)
								.addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(destBox, GroupLayout.Alignment.LEADING)
								.addComponent(getOrderButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(loadPanelLayout.createSequentialGroup()
									.addComponent(createTransferButton)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(button1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addGap(163, 163, 163)
									.addComponent(removeOrderButton))
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
			}
			loadPane.addTab("\u4e2d\u8f6c\u88c5\u8fd0", loadPanel);
		}
		add(loadPane, BorderLayout.CENTER);

		//======== transferListPane ========
		{
			transferListPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== DeliveryListPanel ========
			{

				//======== scrollPane2 ========
				{

					//---- loadTable ----
					loadTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane2.setViewportView(loadTable);
				}

				//---- listID ----
				listID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- centerID ----
				centerID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- centerName ----
				centerName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- destID ----
				destID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- destName ----
				destName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label6 ----
				label6.setText("\u4e2d\u8f6c\u5355\u7f16\u53f7");
				label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label5 ----
				label5.setText("\u4e2d\u8f6c\u4e2d\u5fc3\u7f16\u53f7");
				label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label9 ----
				label9.setText("\u4e2d\u8f6c\u4e2d\u5fc3");
				label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label8 ----
				label8.setText("\u76ee\u7684\u5730\u7f16\u53f7");
				label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label7 ----
				label7.setText("\u76ee\u7684\u5730");
				label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- date ----
				date.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label4 ----
				label4.setText("\u88c5\u8fd0\u65e5\u671f");
				label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- vehicleID ----
				vehicleID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label10 ----
				label10.setText("\u73ed\u6b21  ");
				label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- staffName ----
				staffName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label11 ----
				label11.setText("\u76d1\u88c5\u5458");
				label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- fee ----
				fee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label12 ----
				label12.setText("\u8d39\u7528");
				label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- saveList ----
				saveList.setText("\u4fdd\u5b58");
				saveList.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				saveList.setIcon(new ImageIcon(getClass().getResource("/icons/save_24x24.png")));
				saveList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						saveListMouseReleased(e);
					}
				});

				//---- cancelLoad ----
				cancelLoad.setText("\u53d6\u6d88");
				cancelLoad.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				cancelLoad.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
				cancelLoad.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						cancelLoadMouseReleased(e);
					}
				});

				GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
				DeliveryListPanel.setLayout(DeliveryListPanelLayout);
				DeliveryListPanelLayout.setHorizontalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(DeliveryListPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addGroup(DeliveryListPanelLayout.createSequentialGroup()
									.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGroup(DeliveryListPanelLayout.createSequentialGroup()
											.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(label9, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label5, GroupLayout.Alignment.TRAILING))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(centerID)
												.addComponent(centerName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
										.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
											.addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(listID, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
									.addGap(18, 18, 18)
									.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
											.addGroup(DeliveryListPanelLayout.createSequentialGroup()
												.addComponent(label10, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(vehicleID, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
											.addGroup(DeliveryListPanelLayout.createSequentialGroup()
												.addComponent(label4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(date, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
										.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
											.addComponent(label11, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(staffName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
									.addGap(18, 18, 18)
									.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(label7, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
										.addComponent(label8, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
										.addComponent(label12, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(fee)
										.addComponent(destID)
										.addComponent(destName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
									.addGap(0, 264, Short.MAX_VALUE))
								.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addComponent(saveList, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelLoad, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				DeliveryListPanelLayout.setVerticalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addGroup(DeliveryListPanelLayout.createSequentialGroup()
									.addGroup(DeliveryListPanelLayout.createParallelGroup()
										.addGroup(DeliveryListPanelLayout.createSequentialGroup()
											.addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(DeliveryListPanelLayout.createParallelGroup()
												.addGroup(DeliveryListPanelLayout.createSequentialGroup()
													.addComponent(centerID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
													.addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
											.addGroup(DeliveryListPanelLayout.createParallelGroup()
												.addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(centerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(DeliveryListPanelLayout.createSequentialGroup()
											.addGroup(DeliveryListPanelLayout.createParallelGroup()
												.addGroup(DeliveryListPanelLayout.createSequentialGroup()
													.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(label4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
														.addComponent(listID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
													.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addGroup(GroupLayout.Alignment.LEADING, DeliveryListPanelLayout.createSequentialGroup()
															.addComponent(vehicleID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addGap(0, 1, Short.MAX_VALUE))
														.addComponent(label10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addGroup(DeliveryListPanelLayout.createSequentialGroup()
													.addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGap(5, 5, 5)
													.addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(DeliveryListPanelLayout.createParallelGroup()
												.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(staffName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
												.addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
									.addGap(5, 5, 5))
								.addGroup(DeliveryListPanelLayout.createSequentialGroup()
									.addComponent(destID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5, 5, 5)
									.addComponent(destName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(fee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addGroup(DeliveryListPanelLayout.createSequentialGroup()
									.addComponent(saveList)
									.addGap(170, 170, 170)
									.addComponent(cancelLoad))
								.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
			}
			transferListPane.addTab("\u4e2d\u8f6c\u5355", DeliveryListPanel);
		}
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JTabbedPane loadPane;
	private JPanel loadPanel;
	private JScrollPane scrollPane1;
	private JTable orderTable;
	private JLabel label1;
	private JComboBox transferTypeBox;
	private JLabel label2;
	private JComboBox destBox;
	private JButton createTransferButton;
	private JButton removeOrderButton;
	private JButton getOrderButton;
	private JButton button1;
	private JTabbedPane transferListPane;
	private JPanel DeliveryListPanel;
	private JScrollPane scrollPane2;
	private JTable loadTable;
	private JTextField listID;
	private JTextField centerID;
	private JTextField centerName;
	private JTextField destID;
	private JTextField destName;
	private JLabel label6;
	private JLabel label5;
	private JLabel label9;
	private JLabel label8;
	private JLabel label7;
	private JTextField date;
	private JLabel label4;
	private JTextField vehicleID;
	private JLabel label10;
	private JTextField staffName;
	private JLabel label11;
	private JTextField fee;
	private JLabel label12;
	private JButton saveList;
	private JButton cancelLoad;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
