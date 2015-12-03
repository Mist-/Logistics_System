/*
 * Created by JFormDesigner on Tue Nov 24 22:44:48 CST 2015
 */

package presentation.storage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import data.vo.BriefTransferAndStorageOutVO;
import businesslogic.service.storage.StorageOutService;

/**
 * @author sunxu
 */
public class StorageOutPanel extends JPanel {
	StorageOutService storageOut;
	BriefTransferAndStorageOutVO briefTransferAndStorageOutVO;
	public StorageOutPanel(StorageOutService storageOut) {
		this.storageOut	 = storageOut;
		initComponents();
		setList();
		this.setVisible(true);
	}
	
	private void setList(){
		selectStorageOut.setEnabled(false);
		selectTransfer.setEnabled(false);
		briefTransferAndStorageOutVO = storageOut.newStorageOut();
		DefaultTableModel storageOutModel  = new DefaultTableModel(briefTransferAndStorageOutVO.getStorageOutList(),briefTransferAndStorageOutVO.getStorageOutListHeader());
		DefaultTableModel transferListModel = new DefaultTableModel(briefTransferAndStorageOutVO.getTransferList(),briefTransferAndStorageOutVO.getTransferListHeader());
		storageOutTable.setModel(storageOutModel);
		transferListTable.setModel(transferListModel);
		
		storageOutList.repaint();
		transferListTable.repaint();

	}

	private void doStorageOutMouseClicked(MouseEvent e) {
		//storageBusiness.doAllStorageOut();
	}

	private void storageOutTableMouseClicked(MouseEvent e) {
		selectStorageOut.setEnabled(true);
	}

	private void transferListTableMouseClicked(MouseEvent e) {
		selectTransfer.setEnabled(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		tabbedPane1 = new JTabbedPane();
		storageOutList = new JPanel();
		scrollPane1 = new JScrollPane();
		storageOutTable = new JTable();
		doStorageOut = new JButton();
		selectStorageOut = new JButton();
		textField1 = new JTextField();
		button3 = new JButton();
		transferList = new JPanel();
		scrollPane2 = new JScrollPane();
		transferListTable = new JTable();
		selectTransfer = new JButton();
		textField2 = new JTextField();
		searchTransfer = new JButton();
		tabbedPane2 = new JTabbedPane();
		transferVO = new JPanel();
		scrollPane3 = new JScrollPane();
		orderAndPosition = new JTable();
		label1 = new JLabel();
		textField3 = new JTextField();
		label2 = new JLabel();
		textField4 = new JTextField();
		label3 = new JLabel();
		textField5 = new JTextField();
		label4 = new JLabel();
		textField6 = new JTextField();
		label5 = new JLabel();
		textField7 = new JTextField();
		label6 = new JLabel();
		textField8 = new JTextField();
		label7 = new JLabel();
		textField9 = new JTextField();
		transferCancel = new JButton();
		createStorageOut = new JButton();
		tabbedPane3 = new JTabbedPane();
		storageOutVO = new JPanel();
		scrollPane4 = new JScrollPane();
		label8 = new JLabel();
		transferCenter = new JTextField();
		transferType = new JTextField();
		label9 = new JLabel();
		label10 = new JLabel();
		transferDate = new JTextField();
		storageOutCancel = new JButton();
		storageOutSave = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== tabbedPane1 ========
		{

			//======== storageOutList ========
			{

				//======== scrollPane1 ========
				{

					//---- storageOutTable ----
					storageOutTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							storageOutTableMouseClicked(e);
						}
					});
					scrollPane1.setViewportView(storageOutTable);
				}

				//---- doStorageOut ----
				doStorageOut.setText("\u51fa\u5e93");
				doStorageOut.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						doStorageOutMouseClicked(e);
					}
				});

				//---- selectStorageOut ----
				selectStorageOut.setText("\u67e5\u770b");

				//---- button3 ----
				button3.setText("\u641c\u7d22");

				GroupLayout storageOutListLayout = new GroupLayout(storageOutList);
				storageOutList.setLayout(storageOutListLayout);
				storageOutListLayout.setHorizontalGroup(
					storageOutListLayout.createParallelGroup()
						.addGroup(storageOutListLayout.createSequentialGroup()
							.addGap(14, 14, 14)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(storageOutListLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(doStorageOut, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
								.addComponent(button3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectStorageOut, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
								.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
							.addContainerGap())
				);
				storageOutListLayout.setVerticalGroup(
					storageOutListLayout.createParallelGroup()
						.addGroup(storageOutListLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(storageOutListLayout.createParallelGroup()
								.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
								.addGroup(storageOutListLayout.createSequentialGroup()
									.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(button3)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
									.addComponent(selectStorageOut)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(doStorageOut)))
							.addContainerGap())
				);
			}
			tabbedPane1.addTab("storageOut", storageOutList);

			//======== transferList ========
			{

				//======== scrollPane2 ========
				{

					//---- transferListTable ----
					transferListTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							transferListTableMouseClicked(e);
						}
					});
					scrollPane2.setViewportView(transferListTable);
				}

				//---- selectTransfer ----
				selectTransfer.setText("select");

				//---- searchTransfer ----
				searchTransfer.setText("search");

				GroupLayout transferListLayout = new GroupLayout(transferList);
				transferList.setLayout(transferListLayout);
				transferListLayout.setHorizontalGroup(
					transferListLayout.createParallelGroup()
						.addGroup(transferListLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(transferListLayout.createParallelGroup()
								.addComponent(textField2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
								.addGroup(GroupLayout.Alignment.TRAILING, transferListLayout.createSequentialGroup()
									.addGap(0, 15, Short.MAX_VALUE)
									.addComponent(searchTransfer))
								.addComponent(selectTransfer, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
							.addContainerGap())
				);
				transferListLayout.setVerticalGroup(
					transferListLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, transferListLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(transferListLayout.createParallelGroup()
								.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
								.addGroup(transferListLayout.createSequentialGroup()
									.addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(searchTransfer)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
									.addComponent(selectTransfer, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
			}
			tabbedPane1.addTab("transferList", transferList);
		}
		add(tabbedPane1, BorderLayout.CENTER);

		//======== tabbedPane2 ========
		{

			//======== transferVO ========
			{

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(orderAndPosition);
				}

				//---- label1 ----
				label1.setText("\u4e2d\u8f6c\u4e2d\u5fc3");

				//---- label2 ----
				label2.setText("\u76ee\u6807\u673a\u6784");

				//---- label3 ----
				label3.setText("\u8fd0\u8f93\u65b9\u5f0f");

				//---- label4 ----
				label4.setText("\u76d1\u88c5\u5458");

				//---- label5 ----
				label5.setText("\u8d39\u7528");

				//---- label6 ----
				label6.setText("\u65e5\u671f");

				//---- label7 ----
				label7.setText("\u73ed\u6b21");

				//---- transferCancel ----
				transferCancel.setText("cancel");

				//---- createStorageOut ----
				createStorageOut.setText("create");

				GroupLayout transferVOLayout = new GroupLayout(transferVO);
				transferVO.setLayout(transferVOLayout);
				transferVOLayout.setHorizontalGroup(
					transferVOLayout.createParallelGroup()
						.addGroup(transferVOLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(transferVOLayout.createParallelGroup()
								.addGroup(transferVOLayout.createSequentialGroup()
									.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(transferVOLayout.createParallelGroup()
										.addComponent(transferCancel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
										.addComponent(createStorageOut, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
								.addGroup(transferVOLayout.createSequentialGroup()
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addGroup(GroupLayout.Alignment.LEADING, transferVOLayout.createSequentialGroup()
											.addComponent(label7, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(textField9, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
										.addGroup(GroupLayout.Alignment.LEADING, transferVOLayout.createSequentialGroup()
											.addComponent(label3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(textField5, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
										.addGroup(GroupLayout.Alignment.LEADING, transferVOLayout.createSequentialGroup()
											.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(label2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
												.addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(textField3, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
												.addComponent(textField4, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))))
									.addGap(99, 99, 99)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(label4, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(label5, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(label6, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(textField6, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(textField7, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(textField8, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
									.addGap(0, 0, Short.MAX_VALUE)))
							.addContainerGap())
				);
				transferVOLayout.setVerticalGroup(
					transferVOLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, transferVOLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(transferVOLayout.createSequentialGroup()
									.addGap(0, 259, Short.MAX_VALUE)
									.addComponent(createStorageOut)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(transferCancel))
								.addGroup(transferVOLayout.createSequentialGroup()
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label1)
										.addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label4)
										.addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(transferVOLayout.createParallelGroup()
										.addComponent(label2)
										.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label5)
											.addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label6)
										.addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(transferVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label7)
										.addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(38, 38, 38)
									.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
							.addContainerGap())
				);
			}
			tabbedPane2.addTab("transfer", transferVO);
		}

		//======== tabbedPane3 ========
		{

			//======== storageOutVO ========
			{

				//---- label8 ----
				label8.setText("\u4e2d\u8f6c\u4e2d\u5fc3");

				//---- label9 ----
				label9.setText("\u65e5\u671f");

				//---- label10 ----
				label10.setText("\u8fd0\u8f93\u65b9\u5f0f");

				//---- storageOutCancel ----
				storageOutCancel.setText("cancel");

				//---- storageOutSave ----
				storageOutSave.setText("save");

				GroupLayout storageOutVOLayout = new GroupLayout(storageOutVO);
				storageOutVO.setLayout(storageOutVOLayout);
				storageOutVOLayout.setHorizontalGroup(
					storageOutVOLayout.createParallelGroup()
						.addGroup(storageOutVOLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(storageOutVOLayout.createParallelGroup()
								.addGroup(storageOutVOLayout.createSequentialGroup()
									.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(storageOutVOLayout.createParallelGroup()
										.addComponent(storageOutCancel, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
										.addComponent(storageOutSave, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
								.addGroup(storageOutVOLayout.createSequentialGroup()
									.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(label9, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(label8, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(label10, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(transferCenter, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(transferType, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
										.addComponent(transferDate, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
									.addGap(0, 0, Short.MAX_VALUE)))
							.addContainerGap())
				);
				storageOutVOLayout.setVerticalGroup(
					storageOutVOLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, storageOutVOLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(storageOutVOLayout.createSequentialGroup()
									.addGap(0, 255, Short.MAX_VALUE)
									.addComponent(storageOutSave)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(storageOutCancel))
								.addGroup(storageOutVOLayout.createSequentialGroup()
									.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label8)
										.addComponent(transferCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(transferType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label10))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(storageOutVOLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label9)
										.addComponent(transferDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
									.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
				);
			}
			tabbedPane3.addTab("storageOut", storageOutVO);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane tabbedPane1;
	private JPanel storageOutList;
	private JScrollPane scrollPane1;
	private JTable storageOutTable;
	private JButton doStorageOut;
	private JButton selectStorageOut;
	private JTextField textField1;
	private JButton button3;
	private JPanel transferList;
	private JScrollPane scrollPane2;
	private JTable transferListTable;
	private JButton selectTransfer;
	private JTextField textField2;
	private JButton searchTransfer;
	private JTabbedPane tabbedPane2;
	private JPanel transferVO;
	private JScrollPane scrollPane3;
	private JTable orderAndPosition;
	private JLabel label1;
	private JTextField textField3;
	private JLabel label2;
	private JTextField textField4;
	private JLabel label3;
	private JTextField textField5;
	private JLabel label4;
	private JTextField textField6;
	private JLabel label5;
	private JTextField textField7;
	private JLabel label6;
	private JTextField textField8;
	private JLabel label7;
	private JTextField textField9;
	private JButton transferCancel;
	private JButton createStorageOut;
	private JTabbedPane tabbedPane3;
	private JPanel storageOutVO;
	private JScrollPane scrollPane4;
	private JLabel label8;
	private JTextField transferCenter;
	private JTextField transferType;
	private JLabel label9;
	private JLabel label10;
	private JTextField transferDate;
	private JButton storageOutCancel;
	private JButton storageOutSave;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
