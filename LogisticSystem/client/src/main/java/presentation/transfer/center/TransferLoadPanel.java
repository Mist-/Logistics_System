/*
 * Created by JFormDesigner on Mon Nov 30 21:09:47 CST 2015
 */

package presentation.transfer.center;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.enums.StorageArea;
import data.po.TransferListPO;
import data.vo.BriefOrderVO;
import data.vo.TransferListVO;
import data.vo.TransferLoadVO;
import businesslogic.impl.transfer.TransferInfo;
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
		destBox.setEnabled(true);
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
		if (getOrderButton.isEnabled()) {
			int item = destBox.getSelectedIndex();
			if (item >= 0) {
				String desName = (String) destBox.getItemAt(item);
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
			String[][] info = order.getOrderInfo();
			if (info.length == 0) {
				JOptionPane.showMessageDialog(null, "仓库内订单为空，无法生成中转单", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				transferList = transferLoad.createTransferList(order);
			}
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		tabbedPane1 = new JTabbedPane();
		loadPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		orderTable = new JTable();
		label1 = new JLabel();
		transferTypeBox = new JComboBox();
		label2 = new JLabel();
		destBox = new JComboBox();
		getOrderButton = new JButton();
		createTransferButton = new JButton();
		removeOrderButton = new JButton();
		tabbedPane2 = new JTabbedPane();
		DeliveryListPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		table1 = new JTable();
		cancelLoad = new JButton();
		saveList = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== tabbedPane1 ========
		{

			//======== loadPanel ========
			{

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(orderTable);
				}

				//---- label1 ----
				label1.setText("\u8fd0\u8f93\u7c7b\u578b");

				//---- label2 ----
				label2.setText("\u76ee\u7684\u5730");

				//---- getOrderButton ----
				getOrderButton.setText("\u641c\u7d22\u5e93\u5b58");
				getOrderButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						getOrderButtonMouseReleased(e);
					}
				});

				//---- createTransferButton ----
				createTransferButton.setText("\u751f\u6210\u4e2d\u8f6c\u5355");
				createTransferButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						createTransferButtonMouseReleased(e);
					}
				});

				//---- removeOrderButton ----
				removeOrderButton.setText("\u79fb\u9664");

				GroupLayout loadPanelLayout = new GroupLayout(loadPanel);
				loadPanel.setLayout(loadPanelLayout);
				loadPanelLayout.setHorizontalGroup(
					loadPanelLayout.createParallelGroup()
						.addGroup(loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(transferTypeBox, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
								.addComponent(destBox, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(getOrderButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(removeOrderButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
							.addComponent(createTransferButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
				);
				loadPanelLayout.setVerticalGroup(
					loadPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(loadPanelLayout.createParallelGroup()
								.addGroup(loadPanelLayout.createSequentialGroup()
									.addGroup(loadPanelLayout.createParallelGroup()
										.addGroup(loadPanelLayout.createSequentialGroup()
											.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(label1)
												.addComponent(transferTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(label2)
												.addComponent(destBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addComponent(createTransferButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
									.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(loadPanelLayout.createSequentialGroup()
									.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(getOrderButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
										.addComponent(removeOrderButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)))
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
							.addContainerGap())
				);
			}
			tabbedPane1.addTab("\u4e2d\u8f6c\u88c5\u8fd0", loadPanel);
		}
		add(tabbedPane1, BorderLayout.CENTER);

		//======== tabbedPane2 ========
		{

			//======== DeliveryListPanel ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(table1);
				}

				//---- cancelLoad ----
				cancelLoad.setText("cancel");

				//---- saveList ----
				saveList.setText("save");

				GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
				DeliveryListPanel.setLayout(DeliveryListPanelLayout);
				DeliveryListPanelLayout.setHorizontalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
							.addContainerGap(707, Short.MAX_VALUE)
							.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(cancelLoad, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(saveList, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
							.addContainerGap())
				);
				DeliveryListPanelLayout.setVerticalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
							.addGap(0, 77, Short.MAX_VALUE)
							.addComponent(saveList)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(cancelLoad)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				);
			}
			tabbedPane2.addTab("DeliveryList", DeliveryListPanel);
		}
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JTabbedPane tabbedPane1;
	private JPanel loadPanel;
	private JScrollPane scrollPane1;
	private JTable orderTable;
	private JLabel label1;
	private JComboBox transferTypeBox;
	private JLabel label2;
	private JComboBox destBox;
	private JButton getOrderButton;
	private JButton createTransferButton;
	private JButton removeOrderButton;
	private JTabbedPane tabbedPane2;
	private JPanel DeliveryListPanel;
	private JScrollPane scrollPane2;
	private JTable table1;
	private JButton cancelLoad;
	private JButton saveList;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
