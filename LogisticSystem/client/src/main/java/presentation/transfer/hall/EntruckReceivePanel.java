/*
 * Created by JFormDesigner on Mon Nov 30 23:08:01 CST 2015
 */

package presentation.transfer.hall;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.message.ResultMessage;
import data.vo.ArrivalListVO;
import data.vo.ArrivalVO;
import data.vo.DeliveryListVO;
import data.vo.EntruckListVO;
import data.vo.TransferListVO;
import businesslogic.service.Transfer.hall.EntruckReceiveService;

/**
 * @author sunxu
 */
public class EntruckReceivePanel extends JPanel {
	EntruckReceiveService entruckReceive;
	ArrivalVO arrival;
	TransferListVO transferList;
	EntruckListVO entruckList;

	public EntruckReceivePanel(EntruckReceiveService entruckReceive) {
		this.entruckReceive = entruckReceive;
		initComponents();
		getArrivalList();

		this.validate();
		this.updateUI();
		this.setVisible(true);
	}

	private ResultMessage getArrivalList() {
		ArrivalListVO arrivalList = null;
		try {
			arrivalList = entruckReceive.getCheckedArrivals();
		} catch (RemoteException e) {
			e.printStackTrace();
			errorDialog.validate();
			errorDialog.repaint();
			errorDialog.setVisible(true);
			return ResultMessage.FAILED;
		}
		DefaultTableModel arrivalListModel = new DefaultTableModel(
				arrivalList.info, arrivalList.header);
		arrivalTable.setModel(arrivalListModel);
		arrivalTable.validate();
		arrivalTable.updateUI();
		arrivalTable.repaint();
		return ResultMessage.SUCCESS;
	}

	private void selectArrivalMouseClicked(MouseEvent e) {
		saveArrival.setVisible(false);
		saveArrival.setEnabled(false);
		doArrive.setVisible(true);
		doArrive.setEnabled(true);
		
		
		int row = arrivalTable.getSelectedRow();
		String id = (String) arrivalTable.getValueAt(row, 0);
		long ID = Long.parseLong(id);
		arrival = entruckReceive.chooseArrival(ID);
		setArrivalVO();
	}

	private void setArrivalVO() {
		DefaultTableModel model = new DefaultTableModel(
				arrival.getOrderAndStatus(), arrival.getHeader());
		arrivalVOTable.setModel(model);
//		arrivalVOTable.validate();
//		arrivalVOTable.updateUI();
//		arrivalVOTable.repaint();
		
		remove(startPane);
		add(arrivalVO, BorderLayout.CENTER);

		arrivalVO.validate();
		arrivalVO.updateUI();
		arrivalVO.setVisible(true);
	}

	private void searchListMouseClicked(MouseEvent e) {
		String id = deliveryID.getText();
		long num = Long.parseLong(id);
		getDeliveryList(num);

	}

	private void getDeliveryList(long id) {

		try {
			if (transfer.isSelected()) {
				transferList = entruckReceive.searchTransferList(id);
				setTransferList();
			} else {
				entruckList = entruckReceive.searchEntruckList(id);
				setEntruckList();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorDialog.setVisible(true);
		}
		

		remove(startPane);
		if (arrivalVO != null)
			remove(arrivalVO);
		add(entruckVO, BorderLayout.CENTER);

		entruckVO.validate();
		entruckVO.updateUI();
		entruckVO.setVisible(true);

	}

	private void setEntruckList() {
		DefaultTableModel model = new DefaultTableModel(entruckList.info,
				entruckList.header);
		deliveryTable.setModel(model);
		deliveryTable.validate();
		deliveryTable.updateUI();
		deliveryTable.repaint();
	}

	private void setTransferList() {
		DefaultTableModel model = new DefaultTableModel(
				transferList.orderAndPosition, transferList.header);
		deliveryTable.setModel(model);
		deliveryTable.validate();
		deliveryTable.updateUI();
		deliveryTable.repaint();
	}

	private void cancelArrivalMouseClicked(MouseEvent e) {
		remove(arrivalVO);
		if (entruckVO != null)
			remove(entruckVO);
		add(startPane, BorderLayout.CENTER);

		startPane.validate();
		startPane.updateUI();
		startPane.setVisible(true);
	}



	private void doArriveMouseClicked(MouseEvent e) {
		ResultMessage result = entruckReceive.doArrive();
		if (result == ResultMessage.FAILED) {
			label6.setText("操作失败");
		}

		resultDialog.validate();
		resultDialog.repaint();
		resultDialog.setVisible(true);
	}

	private void cancelLoadMouseClicked(MouseEvent e) {
		if (arrivalVO != null)
			remove(arrivalVO);
		remove(entruckVO);
		add(startPane, BorderLayout.CENTER);

		startPane.validate();
		startPane.updateUI();
		startPane.setVisible(true);
	}

	private void button1MouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void resultSureButtonMouseClicked(MouseEvent e) {
		resultDialog.setVisible(false);
		
		remove(arrivalVO);
		remove(entruckVO);
		add(startPane);
		
		startPane.validate();
		startPane.updateUI();
		startPane.setVisible(true);
	}

	private void errorSureMouseClicked(MouseEvent e) {
		errorDialog.setVisible(false);
	}

	private void entruckMouseClicked(MouseEvent e) {
		transfer.setSelected(false);
		entruck.setSelected(true);
	}

	private void transferMouseClicked(MouseEvent e) {
		entruck.setSelected(false);
		transfer.setSelected(true);
	}

	private void createArrivalMouseClicked(MouseEvent e) {
		if (transfer.isSelected())
			arrival = entruckReceive.createArrival(transferList);
		else
			arrival = entruckReceive.createArrival(entruckList);
		
		doArrive.setVisible(false);
		doArrive.setEnabled(false);
		saveArrival.setVisible(true);
		saveArrival.setEnabled(true);
		setArrivalVO();
		
	}

	private void saveListMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

	private void createStorageInMouseClicked(MouseEvent e) {
		// TODO add your code here
		//没有这个方法！！！！
	}

	private void saveArrivalMouseClicked(MouseEvent e) {
		ResultMessage result = entruckReceive.saveArrival(arrival);
		if(result == ResultMessage.SUCCESS){
			label6.setText("操作成功");
			resultDialog.repaint();
			resultDialog.setVisible(true);
		}
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		startPane = new JTabbedPane();
		arrivalListPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		arrivalTable = new JTable();
		selectArrival = new JButton();
		searchListPanel = new JPanel();
		label5 = new JLabel();
		deliveryID = new JTextField();
		entruck = new JRadioButton();
		transfer = new JRadioButton();
		searchList = new JButton();
		arrivalVO = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		arrivalVOTable = new JTable();
		label1 = new JLabel();
		transferID = new JTextField();
		label2 = new JLabel();
		from = new JTextField();
		label3 = new JLabel();
		arrivalDate = new JTextField();
		status = new JComboBox();
		modifyStatus = new JButton();
		cancelArrival = new JButton();
		doArrive = new JButton();
		label4 = new JLabel();
		saveArrival = new JButton();
		entruckVO = new JTabbedPane();
		DeliveryListPanel = new JPanel();
		scrollPane3 = new JScrollPane();
		deliveryTable = new JTable();
		cancelLoad = new JButton();
		createArrival2 = new JButton();
		resultDialog = new JDialog();
		label6 = new JLabel();
		resultSureButton = new JButton();
		errorDialog = new JDialog();
		label7 = new JLabel();
		errorSure = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== startPane ========
		{

			//======== arrivalListPanel ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(arrivalTable);
				}

				//---- selectArrival ----
				selectArrival.setText("text");
				selectArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectArrivalMouseClicked(e);
					}
				});

				GroupLayout arrivalListPanelLayout = new GroupLayout(arrivalListPanel);
				arrivalListPanel.setLayout(arrivalListPanelLayout);
				arrivalListPanelLayout.setHorizontalGroup(
					arrivalListPanelLayout.createParallelGroup()
						.addGroup(arrivalListPanelLayout.createSequentialGroup()
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(selectArrival, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addContainerGap())
				);
				arrivalListPanelLayout.setVerticalGroup(
					arrivalListPanelLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.TRAILING, arrivalListPanelLayout.createSequentialGroup()
							.addContainerGap(304, Short.MAX_VALUE)
							.addComponent(selectArrival, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
			}
			startPane.addTab("\u5230\u8fbe\u5355\u5217\u8868", arrivalListPanel);

			//======== searchListPanel ========
			{

				//---- label5 ----
				label5.setText("\u8bf7\u8f93\u5165\u8fd0\u8f93\u5355\u53f7");

				//---- entruck ----
				entruck.setText("\u88c5\u8f66\u5355");
				entruck.setSelected(true);
				entruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						entruckMouseClicked(e);
						entruckMouseClicked(e);
					}
				});

				//---- transfer ----
				transfer.setText("\u4e2d\u8f6c\u5355");
				transfer.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						transferMouseClicked(e);
					}
				});

				//---- searchList ----
				searchList.setText("\u67e5\u627e");
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
							.addContainerGap(187, Short.MAX_VALUE))
				);
			}
			startPane.addTab("searchList", searchListPanel);
		}
		add(startPane, BorderLayout.CENTER);

		//======== arrivalVO ========
		{

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(arrivalVOTable);
				}

				//---- label1 ----
				label1.setText("\u8fd0\u8f6c\u5355\u7f16\u53f7");

				//---- label3 ----
				label3.setText("\u65e5\u671f");

				//---- modifyStatus ----
				modifyStatus.setText("text");

				//---- cancelArrival ----
				cancelArrival.setText("\u53d6\u6d88");
				cancelArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelArrivalMouseClicked(e);
						cancelArrivalMouseClicked(e);
						cancelArrivalMouseClicked(e);
						cancelArrivalMouseClicked(e);
						cancelArrivalMouseClicked(e);
					}
				});

				//---- doArrive ----
				doArrive.setText("\u786e\u8ba4\u5230\u8fbe");
				doArrive.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						doArriveMouseClicked(e);
					}
				});

				//---- label4 ----
				label4.setText("\u51fa\u53d1\u5730");

				//---- saveArrival ----
				saveArrival.setText("\u4fdd\u5b58");
				saveArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						saveArrivalMouseClicked(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(status, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(modifyStatus, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addGroup(panel1Layout.createParallelGroup()
									.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE)
									.addGroup(panel1Layout.createSequentialGroup()
										.addGroup(panel1Layout.createParallelGroup()
											.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
													.addGap(30, 30, 30)
													.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
											.addComponent(label4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
											.addComponent(transferID, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
											.addComponent(from, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
											.addComponent(arrivalDate, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(cancelArrival, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(doArrive, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(saveArrival, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(0, 168, Short.MAX_VALUE)
									.addComponent(saveArrival, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(doArrive, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(cancelArrival))
								.addGroup(panel1Layout.createSequentialGroup()
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(transferID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label1))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label2)
										.addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label4))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label3)
										.addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(11, 11, 11)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(modifyStatus))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
							.addContainerGap())
				);
			}
			arrivalVO.addTab("\u5230\u8fbe\u5355", panel1);
		}

		//======== entruckVO ========
		{

			//======== DeliveryListPanel ========
			{

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(deliveryTable);
				}

				//---- cancelLoad ----
				cancelLoad.setText("\u53d6\u6d88");
				cancelLoad.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelLoadMouseClicked(e);
						cancelLoadMouseClicked(e);
						cancelLoadMouseClicked(e);
					}
				});

				//---- createArrival2 ----
				createArrival2.setText("\u751f\u6210\u5230\u8fbe\u5355");
				createArrival2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						createArrivalMouseClicked(e);
					}
				});

				GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
				DeliveryListPanel.setLayout(DeliveryListPanelLayout);
				DeliveryListPanelLayout.setHorizontalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(DeliveryListPanelLayout.createSequentialGroup()
							.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addComponent(cancelLoad, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(DeliveryListPanelLayout.createSequentialGroup()
									.addComponent(createArrival2)
									.addGap(0, 0, Short.MAX_VALUE)))
							.addContainerGap())
				);
				DeliveryListPanelLayout.setVerticalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(DeliveryListPanelLayout.createSequentialGroup()
							.addGap(35, 135, Short.MAX_VALUE)
							.addGroup(DeliveryListPanelLayout.createParallelGroup()
								.addComponent(scrollPane3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
									.addComponent(createArrival2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(cancelLoad)
									.addContainerGap())))
				);
			}
			entruckVO.addTab("\u8fd0\u8f6c\u5355", DeliveryListPanel);
		}

		//======== resultDialog ========
		{
			resultDialog.setTitle("\u64cd\u4f5c\u7ed3\u679c");
			Container resultDialogContentPane = resultDialog.getContentPane();

			//---- label6 ----
			label6.setText("\u64cd\u4f5c\u6210\u529f");

			//---- resultSureButton ----
			resultSureButton.setText("\u786e\u5b9a");
			resultSureButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resultSureButtonMouseClicked(e);
				}
			});

			GroupLayout resultDialogContentPaneLayout = new GroupLayout(resultDialogContentPane);
			resultDialogContentPane.setLayout(resultDialogContentPaneLayout);
			resultDialogContentPaneLayout.setHorizontalGroup(
				resultDialogContentPaneLayout.createParallelGroup()
					.addGroup(resultDialogContentPaneLayout.createSequentialGroup()
						.addGap(118, 118, 118)
						.addGroup(resultDialogContentPaneLayout.createParallelGroup()
							.addComponent(resultSureButton)
							.addComponent(label6, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(123, Short.MAX_VALUE))
			);
			resultDialogContentPaneLayout.setVerticalGroup(
				resultDialogContentPaneLayout.createParallelGroup()
					.addGroup(resultDialogContentPaneLayout.createSequentialGroup()
						.addGap(51, 51, 51)
						.addComponent(label6)
						.addGap(28, 28, 28)
						.addComponent(resultSureButton)
						.addContainerGap(44, Short.MAX_VALUE))
			);
			resultDialog.setSize(320, 200);
			resultDialog.setLocationRelativeTo(null);
		}

		//======== errorDialog ========
		{
			errorDialog.setTitle("\u5f02\u5e38");
			Container errorDialogContentPane = errorDialog.getContentPane();

			//---- label7 ----
			label7.setText("\u7f51\u7edc\u8fde\u63a5\u4e2d\u65ad");

			//---- errorSure ----
			errorSure.setText("\u786e\u5b9a");
			errorSure.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					button1MouseClicked(e);
					errorSureMouseClicked(e);
				}
			});

			GroupLayout errorDialogContentPaneLayout = new GroupLayout(errorDialogContentPane);
			errorDialogContentPane.setLayout(errorDialogContentPaneLayout);
			errorDialogContentPaneLayout.setHorizontalGroup(
				errorDialogContentPaneLayout.createParallelGroup()
					.addGroup(errorDialogContentPaneLayout.createSequentialGroup()
						.addContainerGap(95, Short.MAX_VALUE)
						.addGroup(errorDialogContentPaneLayout.createParallelGroup()
							.addGroup(GroupLayout.Alignment.TRAILING, errorDialogContentPaneLayout.createSequentialGroup()
								.addComponent(label7, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(80, 80, 80))
							.addGroup(GroupLayout.Alignment.TRAILING, errorDialogContentPaneLayout.createSequentialGroup()
								.addComponent(errorSure)
								.addGap(111, 111, 111))))
			);
			errorDialogContentPaneLayout.setVerticalGroup(
				errorDialogContentPaneLayout.createParallelGroup()
					.addGroup(errorDialogContentPaneLayout.createSequentialGroup()
						.addGap(41, 41, 41)
						.addComponent(label7)
						.addGap(36, 36, 36)
						.addComponent(errorSure)
						.addContainerGap(46, Short.MAX_VALUE))
			);
			errorDialog.pack();
			errorDialog.setLocationRelativeTo(errorDialog.getOwner());
		}
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JTabbedPane startPane;
	private JPanel arrivalListPanel;
	private JScrollPane scrollPane2;
	private JTable arrivalTable;
	private JButton selectArrival;
	private JPanel searchListPanel;
	private JLabel label5;
	private JTextField deliveryID;
	private JRadioButton entruck;
	private JRadioButton transfer;
	private JButton searchList;
	private JTabbedPane arrivalVO;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable arrivalVOTable;
	private JLabel label1;
	private JTextField transferID;
	private JLabel label2;
	private JTextField from;
	private JLabel label3;
	private JTextField arrivalDate;
	private JComboBox status;
	private JButton modifyStatus;
	private JButton cancelArrival;
	private JButton doArrive;
	private JLabel label4;
	private JButton saveArrival;
	private JTabbedPane entruckVO;
	private JPanel DeliveryListPanel;
	private JScrollPane scrollPane3;
	private JTable deliveryTable;
	private JButton cancelLoad;
	private JButton createArrival2;
	private JDialog resultDialog;
	private JLabel label6;
	private JButton resultSureButton;
	private JDialog errorDialog;
	private JLabel label7;
	private JButton errorSure;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
