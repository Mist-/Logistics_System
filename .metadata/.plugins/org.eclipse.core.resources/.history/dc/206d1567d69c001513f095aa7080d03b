/*
 * Created by JFormDesigner on Tue Nov 24 22:01:45 CST 2015
 */

package presentation.storage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;
import businesslogic.service.storage.StorageBusinessService;
import businesslogic.service.storage.StorageInService;

/**
 * @author sunxu
 */
public class StorageInPanel extends JPanel {
	BriefArrivalAndStorageInVO briefArrivalAndStorageInVO;
	StorageInService storageIn;
	ArrivalVO arrival;
	

	
	public StorageInPanel(StorageInService storageIn) {
		this.storageIn = storageIn;
		initComponents();
		setList();
		this.repaint();
	}
	
	

	
	
	
	private void setList(){
		selectArrival.setEnabled(false);
		selectStorageIn.setEnabled(false);
		briefArrivalAndStorageInVO = storageIn.newStorageIn();
		DefaultTableModel storageInModel = new DefaultTableModel(briefArrivalAndStorageInVO.getStorageInListInfo(), briefArrivalAndStorageInVO.getStorageInTittle());
		storageInTable.setModel(storageInModel);
		DefaultTableModel arrivalModel = new DefaultTableModel(briefArrivalAndStorageInVO.getArrivalListInfo(),briefArrivalAndStorageInVO.getArrivalTittle());
		storageInTable.setModel(storageInModel);
		arriveListTable.setModel(arrivalModel);
		
		
		storageInTable.repaint();
		arriveListTable.repaint();
		
		remove(storageInVO);
		remove(arriveList);
		add(listPane);
		
		listPane.validate();
		listPane.updateUI();
		this.repaint();
	}

	private void setStorageIn(StorageInVO vo){
		remove(listPane);
		remove(arriveList);
		add(storageInVO);
		
		storageInVO.validate();
		storageInVO.updateUI();
		this.repaint();
	}
	
	private void setArrival(ArrivalVO vo) {
		DefaultTableModel model = new DefaultTableModel(vo.getOrderAndStatus(),vo.getHeader());
		arrivalTable.setModel(model);
		arrivalTable.updateUI();
		
		remove(listPane);
		remove(storageInVO);
		add(arrivalVO);
		
		arrivalVO.validate();
		arrivalVO.updateUI();
		this.repaint();
	}

	private void selectStorageInMouseClicked(MouseEvent e) {
		int row = storageInTable.getSelectedRow();
		String info = (String) storageInTable.getValueAt(row, 0);
		long storageInID = Long.parseLong(info);
		StorageInVO storage = storageIn.getStorageIn(storageInID);
		setStorageIn(storage);
		

	}
	
	

	private void arriveListTableMouseClicked(MouseEvent e) {
		selectArrival.setEnabled(true);
	}

	private void selectArrivalMouseClicked(MouseEvent e) {
		int row = arriveListTable.getSelectedRow();
		String info = (String) arriveListTable.getValueAt(row, 0);
		long arrivalID = Long.parseLong(info);
		arrival = storageIn.getArriveList(arrivalID);
		setArrival(arrival);
		

	}

	private void createStorageInMouseClicked(MouseEvent e) {
		StorageInVO newStorageIn = storageIn.sort(arrival);
		setStorageIn(newStorageIn);		
		
		remove(listPane);
		remove(arriveList);
		add(storageInVO);
		
		storageInVO.validate();
		storageInVO.updateUI();
		this.repaint();
	}

	private void storageInTableMouseClicked(MouseEvent e) {
		selectStorageIn.setEnabled(true);
	}

	private void doStorageInMouseClicked(MouseEvent e) {
		//storageBusiness.doAllStorageIn();
	}

	private void cancelArrivalMouseClicked(MouseEvent e) {
		remove(arrivalVO);
		add(listPane);
		
		listPane.setVisible(true);
		this.updateUI();
		this.repaint();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		listPane = new JTabbedPane();
		storageInList = new JPanel();
		scrollPane4 = new JScrollPane();
		storageInTable = new JTable();
		textField9 = new JTextField();
		button5 = new JButton();
		doStorageIn = new JButton();
		selectStorageIn = new JButton();
		arriveList = new JPanel();
		scrollPane3 = new JScrollPane();
		arriveListTable = new JTable();
		arrivalID = new JTextField();
		search = new JButton();
		selectArrival = new JButton();
		storageInVO = new JTabbedPane();
		storageInPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		storageOrder = new JTable();
		label10 = new JLabel();
		storageDate = new JTextField();
		cancelStorageIn = new JButton();
		saveStorageIn = new JButton();
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
		status = new JComboBox();
		modifyStatus = new JButton();
		cancelArrival = new JButton();
		createStorageIn = new JButton();
		label4 = new JLabel();

		//======== this ========
		setLayout(new BorderLayout());

		//======== listPane ========
		{

			//======== storageInList ========
			{

				//======== scrollPane4 ========
				{

					//---- storageInTable ----
					storageInTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							storageInTableMouseClicked(e);
							storageInTableMouseClicked(e);
						}
					});
					scrollPane4.setViewportView(storageInTable);
				}

				//---- button5 ----
				button5.setText("text");

				//---- doStorageIn ----
				doStorageIn.setText("storageIn");
				doStorageIn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						doStorageInMouseClicked(e);
					}
				});

				//---- selectStorageIn ----
				selectStorageIn.setText("select");
				selectStorageIn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectStorageInMouseClicked(e);
						selectStorageInMouseClicked(e);
						selectStorageInMouseClicked(e);
					}
				});

				GroupLayout storageInListLayout = new GroupLayout(storageInList);
				storageInList.setLayout(storageInListLayout);
				storageInListLayout.setHorizontalGroup(
					storageInListLayout.createParallelGroup()
						.addGroup(storageInListLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(storageInListLayout.createParallelGroup()
								.addComponent(textField9)
								.addGroup(GroupLayout.Alignment.TRAILING, storageInListLayout.createSequentialGroup()
									.addGap(0, 0, Short.MAX_VALUE)
									.addComponent(button5))
								.addComponent(doStorageIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(selectStorageIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())
				);
				storageInListLayout.setVerticalGroup(
					storageInListLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, storageInListLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(storageInListLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
								.addGroup(storageInListLayout.createSequentialGroup()
									.addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(button5)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
									.addComponent(selectStorageIn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(doStorageIn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
			}
			listPane.addTab("storageInList", storageInList);

			//======== arriveList ========
			{

				//======== scrollPane3 ========
				{

					//---- arriveListTable ----
					arriveListTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							arriveListTableMouseClicked(e);
							arriveListTableMouseClicked(e);
							arriveListTableMouseClicked(e);
						}
					});
					scrollPane3.setViewportView(arriveListTable);
				}

				//---- search ----
				search.setText("search");

				//---- selectArrival ----
				selectArrival.setText("select");
				selectArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectArrivalMouseClicked(e);
					}
				});

				GroupLayout arriveListLayout = new GroupLayout(arriveList);
				arriveList.setLayout(arriveListLayout);
				arriveListLayout.setHorizontalGroup(
					arriveListLayout.createParallelGroup()
						.addGroup(arriveListLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(arriveListLayout.createParallelGroup()
								.addComponent(arrivalID)
								.addGroup(GroupLayout.Alignment.TRAILING, arriveListLayout.createSequentialGroup()
									.addGap(0, 62, Short.MAX_VALUE)
									.addComponent(search))
								.addComponent(selectArrival, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())
				);
				arriveListLayout.setVerticalGroup(
					arriveListLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, arriveListLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(arriveListLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
								.addGroup(arriveListLayout.createSequentialGroup()
									.addComponent(arrivalID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(search)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
									.addComponent(selectArrival, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
			}
			listPane.addTab("arriveList", arriveList);
		}
		add(listPane, BorderLayout.CENTER);

		//======== storageInVO ========
		{

			//======== storageInPanel ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(storageOrder);
				}

				//---- label10 ----
				label10.setText("\u65e5\u671f\uff1a");

				//---- storageDate ----
				storageDate.setEditable(false);

				//---- cancelStorageIn ----
				cancelStorageIn.setText("cancel");

				//---- saveStorageIn ----
				saveStorageIn.setText("save");

				GroupLayout storageInPanelLayout = new GroupLayout(storageInPanel);
				storageInPanel.setLayout(storageInPanelLayout);
				storageInPanelLayout.setHorizontalGroup(
					storageInPanelLayout.createParallelGroup()
						.addGroup(storageInPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(storageInPanelLayout.createParallelGroup()
								.addGroup(storageInPanelLayout.createSequentialGroup()
									.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(storageInPanelLayout.createParallelGroup()
										.addComponent(cancelStorageIn, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
										.addComponent(saveStorageIn, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
								.addGroup(storageInPanelLayout.createSequentialGroup()
									.addComponent(label10, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(storageDate, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 624, Short.MAX_VALUE)))
							.addContainerGap())
				);
				storageInPanelLayout.setVerticalGroup(
					storageInPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, storageInPanelLayout.createSequentialGroup()
							.addGroup(storageInPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(storageInPanelLayout.createSequentialGroup()
									.addContainerGap(309, Short.MAX_VALUE)
									.addComponent(saveStorageIn)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(cancelStorageIn))
								.addGroup(storageInPanelLayout.createSequentialGroup()
									.addGap(19, 19, 19)
									.addGroup(storageInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label10, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addComponent(storageDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(28, 28, 28)
									.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
							.addContainerGap())
				);
			}
			storageInVO.addTab("storageIn", storageInPanel);
		}

		//======== arrivalVO ========
		{

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(arrivalTable);
				}

				//---- label1 ----
				label1.setText("\u4e2d\u8f6c\u5355\u7f16\u53f7");

				//---- label3 ----
				label3.setText("\u65e5\u671f");

				//---- modifyStatus ----
				modifyStatus.setText("text");

				//---- cancelArrival ----
				cancelArrival.setText("text");
				cancelArrival.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelArrivalMouseClicked(e);
					}
				});

				//---- createStorageIn ----
				createStorageIn.setText("text");
				createStorageIn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						createStorageInMouseClicked(e);
					}
				});

				//---- label4 ----
				label4.setText("\u51fa\u53d1\u5730");

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
								.addComponent(createStorageIn, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(panel1Layout.createSequentialGroup()
									.addGap(0, 199, Short.MAX_VALUE)
									.addComponent(createStorageIn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
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
									.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
							.addContainerGap())
				);
			}
			arrivalVO.addTab("arrival", panel1);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane listPane;
	private JPanel storageInList;
	private JScrollPane scrollPane4;
	private JTable storageInTable;
	private JTextField textField9;
	private JButton button5;
	private JButton doStorageIn;
	private JButton selectStorageIn;
	private JPanel arriveList;
	private JScrollPane scrollPane3;
	private JTable arriveListTable;
	private JTextField arrivalID;
	private JButton search;
	private JButton selectArrival;
	private JTabbedPane storageInVO;
	private JPanel storageInPanel;
	private JScrollPane scrollPane2;
	private JTable storageOrder;
	private JLabel label10;
	private JTextField storageDate;
	private JButton cancelStorageIn;
	private JButton saveStorageIn;
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
	private JComboBox status;
	private JButton modifyStatus;
	private JButton cancelArrival;
	private JButton createStorageIn;
	private JLabel label4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
