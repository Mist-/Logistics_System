/*
 * Created by JFormDesigner on Thu Dec 03 15:28:21 CST 2015
 */

package presentation.storage;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.enums.POType;
import data.message.ResultMessage;
import data.vo.StorageInVO;
import data.vo.StorageInfoVO;
import data.vo.StorageListVO;
import data.vo.StorageOutVO;
import businesslogic.service.storage.StorageOperateService;

/**
 * @author sunxu
 */
public class StorageOperatePanel extends JPanel {
	StorageOperateService storageOperate;
	StorageListVO storageList;
	StorageInVO storageInVO;
	StorageOutVO storageOutVO;
	public StorageOperatePanel(StorageOperateService storageOperate) {
		this.storageOperate = storageOperate;
		initComponents();
		setPercent();
		setStorageInfoTable();
		selectStorageListButton.setEnabled(false);
		exportExcel.setEnabled(false);
		this.validate();
		this.updateUI();
		this.setVisible(true);
	}

	private void clearInitInput() {
		numInput.setText("");
		shelfInput.setText("");
		planeInput.setText("");
		trainInput.setText("");
		truckInput.setText("");
		flexibleInput.setText("");
		alarmPercentInput.setText("0.90");
		this.updateUI();
		this.repaint();
	}

	private void setPercent(){
		double[] percent = storageOperate.showSpace();
		if (percent != null) {
			planePercent.setText(percent[0]+"");
			trainPercent.setText(percent[1]+"");
			truckPercent.setText(percent[2]+"");
		}
	}
	
	private void setStorageInfoTable(){
		StorageInfoVO storage = storageOperate.storageCheck();
		if (storage == null) {
			saveStorageCheck.setEnabled(false);
			return ;
		}
		DefaultTableModel model = new DefaultTableModel(storage.orderAndPostitionArray,storage.header);
		storageInfoTable.setModel(model);
		storageInfoTable.updateUI();
	}
	
	private boolean setInitInput() {
		int num = 0, shelf = 0, planeRow = 0, trainRow = 0, truckRow = 0, flexibleRow = 0;
		double alarmPercent = 0.0;
		try {
			num = Integer.parseInt(numInput.getText());
			shelf = Integer.parseInt(shelfInput.getText());
			planeRow = Integer.parseInt(planeInput.getText());
			trainRow = Integer.parseInt(trainInput.getText());
			truckRow = Integer.parseInt(truckInput.getText());
			flexibleRow = Integer.parseInt(flexibleInput.getText());
			alarmPercent = Double.parseDouble(alarmPercentInput.getText());
		} catch (NumberFormatException e) {
			return false;
		}
		ResultMessage result = storageOperate.inputStorageInitInfo(num, shelf,
				planeRow, trainRow, truckRow, flexibleRow, alarmPercent);
		if (result == ResultMessage.SUCCESS)
			return true;
		else
			return false;
	}

	// ==============================监听===================================
	private void storageInMouseClicked(MouseEvent e) {
		storageIn.setSelected(true);
		storageOut.setSelected(false);
	}

	private void storageOutMouseClicked(MouseEvent e) {
		storageIn.setSelected(false);
		storageOut.setSelected(true);
	}

	private void InitSureButtonMouseClicked(MouseEvent e) {
		if (InitSureButton.isEnabled()) {
			boolean r = setInitInput();
			if (r) {
				JOptionPane.showMessageDialog(null, "初始化成功", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				numInput.setEditable(false);//当前界面所有控件失效
				shelfInput.setEditable(false);
				alarmPercentInput.setEditable(false);
				planeInput.setEditable(false);
				trainInput.setEditable(false);
				truckInput.setEditable(false);
				flexibleInput.setEditable(false);
				InitSureButton.setEnabled(false);
				reInputButton.setEnabled(false);
				setStorageInfoTable();
				setPercent();
			} else {
				JOptionPane.showMessageDialog(null, "初始化失败", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void reInputButtonMouseClicked(MouseEvent e) {
		clearInitInput();
	}

	private void listTableMouseClicked(MouseEvent e) {
		selectStorageListButton.setEnabled(true);
	}

	private void searchListButtonMouseReleased(MouseEvent e) {
		int sy = -1;
		int sm = -1;
		int sd = -1;
		int ey = -1;
		int em = -1;
		int ed = -1;
		try {
			sy = Integer.parseInt(startY.getText());
			sm = Integer.parseInt(startM.getText());
			sd = Integer.parseInt(startD.getText());
			ey = Integer.parseInt(endY.getText());
			em = Integer.parseInt(endM.getText());
			ed = Integer.parseInt(endD.getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "输入格式有误", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String start = String.format("%04d", sy)+"/"+String.format("%02d", sm)+"/"+String.format("%02d", sd);
		String end = String.format("%04d", ey)+"/"+String.format("%02d", em)+"/"+String.format("%02d", ed);

		try {
			if (storageIn.isSelected()) {
				storageList = storageOperate.getStorageInList(start, end, POType.STORAGEINLIST);
			}else {
				storageList = storageOperate.getStorageInList(start, end, POType.STORAGEOUTLIST);
			}

		} catch (RemoteException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "网络连接中断", "提示",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ParseException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "时间输入有误","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		if (storageList != null) {
			setStorageList();
		} else {
			JOptionPane.showMessageDialog(null, "未能获取单据", "提示",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void setStorageList() {
		// 设置入库单，出库单列表
	}
	
	private void setStorageOutVO(){
		//设置出库单显示信息
	}

	private void setStorageInVO(){
		//设置入库单显示信息
	}
	
	private void selectStorageListButtonMouseReleased(MouseEvent e) {
		if (selectStorageListButton.isEnabled()) {
			int row = listTable.getSelectedRow();
			long id = Long.parseLong((String) listTable.getValueAt(row, 0));
			if (storageIn.isSelected()) {
				storageInVO = storageOperate.getStorageIn(id);
				if (storageInVO == null){
					JOptionPane.showMessageDialog(null, "未能正确获取入库单", "异常", JOptionPane.ERROR_MESSAGE);
					return;
				}
				setStorageInVO();
			}else{
				storageOutVO = storageOperate.getStorageOut(id);
				if (storageOutVO == null){
					JOptionPane.showMessageDialog(null, "未能正确获取出库单", "异常", JOptionPane.ERROR_MESSAGE);
					return;
				}
				setStorageOutVO();
			}
		}
	}

	private void saveStorageCheckMouseReleased(MouseEvent e) {
		if (saveStorageCheck.isEnabled()) {
			try {
				ResultMessage result = storageOperate.saveStorageInfo();
				if(result == ResultMessage.FAILED){
					JOptionPane.showMessageDialog(null, "仓库未初始化，请先初始化仓库", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "网络连接中断", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void refreshButtonMouseClicked(MouseEvent e) {
		setStorageInfoTable();
		setPercent();
	}


 	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		operatePane = new JTabbedPane();
		showPanel = new JPanel();
		label9 = new JLabel();
		label10 = new JLabel();
		startY = new JTextField();
		endY = new JTextField();
		searchListButton = new JButton();
		selectStorageListButton = new JButton();
		storageIn = new JRadioButton();
		storageOut = new JRadioButton();
		scrollPane1 = new JScrollPane();
		listTable = new JTable();
		startD = new JTextField();
		label13 = new JLabel();
		label12 = new JLabel();
		startM = new JTextField();
		label11 = new JLabel();
		label14 = new JLabel();
		endM = new JTextField();
		label15 = new JLabel();
		endD = new JTextField();
		label16 = new JLabel();
		checkPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		storageInfoTable = new JTable();
		saveStorageCheck = new JButton();
		exportExcel = new JButton();
		refreshButton = new JButton();
		adjustPanel = new JPanel();
		label17 = new JLabel();
		label18 = new JLabel();
		label19 = new JLabel();
		planePercent = new JTextField();
		trainPercent = new JTextField();
		truckPercent = new JTextField();
		enlargePlane = new JButton();
		enlargeTrain = new JButton();
		enlargeTruck = new JButton();
		initPanel = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		numInput = new JTextField();
		label4 = new JLabel();
		shelfInput = new JTextField();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		planeInput = new JTextField();
		trainInput = new JTextField();
		truckInput = new JTextField();
		flexibleInput = new JTextField();
		InitSureButton = new JButton();
		reInputButton = new JButton();
		label20 = new JLabel();
		alarmPercentInput = new JTextField();

		//======== this ========

		//======== operatePane ========
		{
			operatePane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== showPanel ========
			{
				showPanel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label9 ----
				label9.setText("\u8d77\u59cb\u65e5\u671f\uff1a");
				label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label10 ----
				label10.setText("\u7ec8\u6b62\u65e5\u671f\uff1a");
				label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- startY ----
				startY.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- endY ----
				endY.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- searchListButton ----
				searchListButton.setText("\u67e5\u627e");
				searchListButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				searchListButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						searchListButtonMouseReleased(e);
					}
				});

				//---- selectStorageListButton ----
				selectStorageListButton.setText("\u67e5\u770b");
				selectStorageListButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				selectStorageListButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						selectStorageListButtonMouseReleased(e);
					}
				});

				//---- storageIn ----
				storageIn.setText("\u5165\u5e93\u5355");
				storageIn.setSelected(true);
				storageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				storageIn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						storageInMouseClicked(e);
					}
				});

				//---- storageOut ----
				storageOut.setText("\u51fa\u5e93\u5355");
				storageOut.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				storageOut.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						storageOutMouseClicked(e);
					}
				});

				//======== scrollPane1 ========
				{

					//---- listTable ----
					listTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					listTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					listTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							listTableMouseClicked(e);
						}
					});
					scrollPane1.setViewportView(listTable);
				}

				//---- startD ----
				startD.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label13 ----
				label13.setText("\u65e5");
				label13.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label12 ----
				label12.setText("\u6708");
				label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- startM ----
				startM.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label11 ----
				label11.setText("\u5e74");
				label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label14 ----
				label14.setText("\u5e74");
				label14.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- endM ----
				endM.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label15 ----
				label15.setText("\u6708");
				label15.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- endD ----
				endD.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label16 ----
				label16.setText("\u65e5");
				label16.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				GroupLayout showPanelLayout = new GroupLayout(showPanel);
				showPanel.setLayout(showPanelLayout);
				showPanelLayout.setHorizontalGroup(
					showPanelLayout.createParallelGroup()
						.addGroup(showPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(showPanelLayout.createParallelGroup()
								.addComponent(scrollPane1)
								.addGroup(showPanelLayout.createSequentialGroup()
									.addGroup(showPanelLayout.createParallelGroup()
										.addComponent(label10)
										.addComponent(label9, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
									.addGap(13, 13, 13)
									.addGroup(showPanelLayout.createParallelGroup()
										.addGroup(showPanelLayout.createSequentialGroup()
											.addComponent(endY, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label14, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(endM, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(label15, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(endD, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label16))
										.addGroup(showPanelLayout.createSequentialGroup()
											.addComponent(startY, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(startM, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(label12, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addGap(5, 5, 5)
											.addComponent(startD, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(label13)))
									.addGap(53, 53, 53)
									.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(storageIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(searchListButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18, 18, 18)
									.addGroup(showPanelLayout.createParallelGroup()
										.addComponent(storageOut)
										.addComponent(selectStorageListButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(0, 198, Short.MAX_VALUE)))
							.addContainerGap())
				);
				showPanelLayout.setVerticalGroup(
					showPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, showPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(showPanelLayout.createParallelGroup()
								.addGroup(showPanelLayout.createSequentialGroup()
									.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(startY)
										.addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(GroupLayout.Alignment.TRAILING, showPanelLayout.createParallelGroup()
											.addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addComponent(startM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(startD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(endY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label10, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addGroup(showPanelLayout.createParallelGroup()
											.addComponent(label14, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addComponent(endM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(endD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(searchListButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
												.addComponent(selectStorageListButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(showPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(storageIn, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addComponent(storageOut, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
							.addContainerGap())
				);
			}
			operatePane.addTab("\u5e93\u5b58\u67e5\u770b", showPanel);

			//======== checkPanel ========
			{

				//======== scrollPane2 ========
				{

					//---- storageInfoTable ----
					storageInfoTable.setShowHorizontalLines(false);
					storageInfoTable.setEnabled(false);
					storageInfoTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane2.setViewportView(storageInfoTable);
				}

				//---- saveStorageCheck ----
				saveStorageCheck.setText("\u4fdd\u5b58");
				saveStorageCheck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				saveStorageCheck.setIcon(new ImageIcon(getClass().getResource("/icons/save_24x24.png")));
				saveStorageCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						saveStorageCheckMouseReleased(e);
					}
				});

				//---- exportExcel ----
				exportExcel.setText("\u5bfc\u51faexcel");
				exportExcel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				exportExcel.setIcon(new ImageIcon(getClass().getResource("/icons/excel_24x24.png")));

				//---- refreshButton ----
				refreshButton.setText("\u5237\u65b0");
				refreshButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						refreshButtonMouseClicked(e);
					}
				});

				GroupLayout checkPanelLayout = new GroupLayout(checkPanel);
				checkPanel.setLayout(checkPanelLayout);
				checkPanelLayout.setHorizontalGroup(
					checkPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, checkPanelLayout.createSequentialGroup()
							.addContainerGap(531, Short.MAX_VALUE)
							.addComponent(refreshButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(saveStorageCheck, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(exportExcel)
							.addContainerGap())
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
				);
				checkPanelLayout.setVerticalGroup(
					checkPanelLayout.createParallelGroup()
						.addGroup(checkPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(checkPanelLayout.createParallelGroup()
								.addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(exportExcel)
									.addComponent(saveStorageCheck))
								.addComponent(refreshButton, GroupLayout.Alignment.TRAILING))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
				);
			}
			operatePane.addTab("\u5e93\u5b58\u76d8\u70b9", checkPanel);

			//======== adjustPanel ========
			{
				adjustPanel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label17 ----
				label17.setText("\u822a\u8fd0\u533a\uff1a");
				label17.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label18 ----
				label18.setText("\u94c1\u8fd0\u533a\uff1a");
				label18.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label19 ----
				label19.setText("\u6c7d\u8fd0\u533a\uff1a");
				label19.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- planePercent ----
				planePercent.setEditable(false);
				planePercent.setEnabled(false);
				planePercent.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- trainPercent ----
				trainPercent.setEditable(false);
				trainPercent.setEnabled(false);
				trainPercent.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- truckPercent ----
				truckPercent.setEditable(false);
				truckPercent.setEnabled(false);
				truckPercent.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- enlargePlane ----
				enlargePlane.setText("\u6269\u5145");
				enlargePlane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- enlargeTrain ----
				enlargeTrain.setText("\u6269\u5145");
				enlargeTrain.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- enlargeTruck ----
				enlargeTruck.setText("\u6269\u5145");
				enlargeTruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				GroupLayout adjustPanelLayout = new GroupLayout(adjustPanel);
				adjustPanel.setLayout(adjustPanelLayout);
				adjustPanelLayout.setHorizontalGroup(
					adjustPanelLayout.createParallelGroup()
						.addGroup(adjustPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label17, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label18, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label19))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(planePercent)
								.addComponent(trainPercent)
								.addComponent(truckPercent, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup()
								.addComponent(enlargePlane)
								.addComponent(enlargeTrain)
								.addComponent(enlargeTruck))
							.addContainerGap(588, Short.MAX_VALUE))
				);
				adjustPanelLayout.setVerticalGroup(
					adjustPanelLayout.createParallelGroup()
						.addGroup(adjustPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label17, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(planePercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(enlargePlane))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label18, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(trainPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(enlargeTrain))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(adjustPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label19, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(truckPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(enlargeTruck))
							.addContainerGap(397, Short.MAX_VALUE))
				);
			}
			operatePane.addTab("\u5e93\u5b58\u8c03\u6574", adjustPanel);

			//======== initPanel ========
			{

				//---- label1 ----
				label1.setText("\u533a\u57df\u6570\u91cf");
				label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- textField1 ----
				textField1.setText("4");
				textField1.setEditable(false);
				textField1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label2 ----
				label2.setText("\u8d27\u67b6\u89c4\u683c\uff08\u4f4d\uff09");
				label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- numInput ----
				numInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label4 ----
				label4.setText("\u6bcf\u6392\u67b6\u6570\uff08\u67b6\uff09");
				label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- shelfInput ----
				shelfInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label5 ----
				label5.setText("\u822a\u8fd0\u533a\uff08\u6392\uff09");
				label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label6 ----
				label6.setText("\u94c1\u8fd0\u533a\uff08\u6392\uff09");
				label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label7 ----
				label7.setText("\u6c7d\u8fd0\u533a\uff08\u6392\uff09");
				label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label8 ----
				label8.setText("\u673a\u52a8\u533a\uff08\u6392\uff09");
				label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- planeInput ----
				planeInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- trainInput ----
				trainInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- truckInput ----
				truckInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- flexibleInput ----
				flexibleInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- InitSureButton ----
				InitSureButton.setText("\u786e\u8ba4");
				InitSureButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				InitSureButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						InitSureButtonMouseClicked(e);
					}
				});

				//---- reInputButton ----
				reInputButton.setText("\u91cd\u65b0\u8f93\u5165");
				reInputButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				reInputButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						reInputButtonMouseClicked(e);
					}
				});

				//---- label20 ----
				label20.setText("\u62a5\u8b66\u6bd4\u4f8b\uff080.xx\uff09");
				label20.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- alarmPercentInput ----
				alarmPercentInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				GroupLayout initPanelLayout = new GroupLayout(initPanel);
				initPanel.setLayout(initPanelLayout);
				initPanelLayout.setHorizontalGroup(
					initPanelLayout.createParallelGroup()
						.addGroup(initPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(initPanelLayout.createParallelGroup()
								.addGroup(initPanelLayout.createSequentialGroup()
									.addComponent(label1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(55, 55, 55)
									.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(19, 19, 19)
									.addComponent(label5)
									.addGap(6, 6, 6)
									.addComponent(planeInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addComponent(label2)
									.addGap(18, 18, 18)
									.addComponent(numInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(19, 19, 19)
									.addComponent(label6)
									.addGap(6, 6, 6)
									.addComponent(trainInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addComponent(label4)
									.addGap(18, 18, 18)
									.addComponent(shelfInput, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(18, 18, 18)
									.addComponent(label7)
									.addGap(6, 6, 6)
									.addComponent(truckInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addComponent(label20)
									.addGap(9, 9, 9)
									.addComponent(alarmPercentInput, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(18, 18, 18)
									.addComponent(label8)
									.addGap(6, 6, 6)
									.addComponent(flexibleInput, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(81, 81, 81)
									.addComponent(InitSureButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addGap(68, 68, 68)
									.addComponent(reInputButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(463, Short.MAX_VALUE))
				);
				initPanelLayout.setVerticalGroup(
					initPanelLayout.createParallelGroup()
						.addGroup(initPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(initPanelLayout.createParallelGroup()
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(planeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(4, 4, 4)
									.addGroup(initPanelLayout.createParallelGroup()
										.addComponent(label1)
										.addComponent(label5))))
							.addGap(6, 6, 6)
							.addGroup(initPanelLayout.createParallelGroup()
								.addComponent(numInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(trainInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(4, 4, 4)
									.addGroup(initPanelLayout.createParallelGroup()
										.addComponent(label2)
										.addComponent(label6))))
							.addGap(6, 6, 6)
							.addGroup(initPanelLayout.createParallelGroup()
								.addComponent(shelfInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(truckInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(4, 4, 4)
									.addGroup(initPanelLayout.createParallelGroup()
										.addComponent(label4)
										.addComponent(label7))))
							.addGap(6, 6, 6)
							.addGroup(initPanelLayout.createParallelGroup()
								.addComponent(label20, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(alarmPercentInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(initPanelLayout.createSequentialGroup()
									.addGap(4, 4, 4)
									.addComponent(label8))
								.addComponent(flexibleInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7, 7, 7)
							.addGroup(initPanelLayout.createParallelGroup()
								.addComponent(reInputButton)
								.addComponent(InitSureButton))
							.addContainerGap(338, Short.MAX_VALUE))
				);
			}
			operatePane.addTab("\u521d\u59cb\u5316", initPanel);
		}

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(operatePane)
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addComponent(operatePane, GroupLayout.Alignment.TRAILING)
		);
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	private JTabbedPane operatePane;
	private JPanel showPanel;
	private JLabel label9;
	private JLabel label10;
	private JTextField startY;
	private JTextField endY;
	private JButton searchListButton;
	private JButton selectStorageListButton;
	private JRadioButton storageIn;
	private JRadioButton storageOut;
	private JScrollPane scrollPane1;
	private JTable listTable;
	private JTextField startD;
	private JLabel label13;
	private JLabel label12;
	private JTextField startM;
	private JLabel label11;
	private JLabel label14;
	private JTextField endM;
	private JLabel label15;
	private JTextField endD;
	private JLabel label16;
	private JPanel checkPanel;
	private JScrollPane scrollPane2;
	private JTable storageInfoTable;
	private JButton saveStorageCheck;
	private JButton exportExcel;
	private JButton refreshButton;
	private JPanel adjustPanel;
	private JLabel label17;
	private JLabel label18;
	private JLabel label19;
	private JTextField planePercent;
	private JTextField trainPercent;
	private JTextField truckPercent;
	private JButton enlargePlane;
	private JButton enlargeTrain;
	private JButton enlargeTruck;
	private JPanel initPanel;
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JTextField numInput;
	private JLabel label4;
	private JTextField shelfInput;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JTextField planeInput;
	private JTextField trainInput;
	private JTextField truckInput;
	private JTextField flexibleInput;
	private JButton InitSureButton;
	private JButton reInputButton;
	private JLabel label20;
	private JTextField alarmPercentInput;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
