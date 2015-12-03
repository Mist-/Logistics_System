/*
 * Created by JFormDesigner on Mon Nov 30 23:38:21 CST 2015
 */

package presentation.transfer.hall;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.message.ResultMessage;
import data.vo.BriefEntruckListVO;
import data.vo.BriefOrderVO;
import data.vo.EntruckListVO;
import businesslogic.service.Transfer.hall.LoadAndSortService;

/**
 * @author sunxu
 */
public class LoadAndSortPanel extends JPanel {
	LoadAndSortService loadAndSort;
	BriefOrderVO briefOrder;
	BriefEntruckListVO briefEntruckList;
	EntruckListVO entruck;
	
	public LoadAndSortPanel(LoadAndSortService loadAndSort) throws RemoteException {
		this.loadAndSort = loadAndSort;

		initComponents();
		setDestination();
		setEntruckList();
		this.setVisible(true);
	}
	
//=========================…Ë÷√ΩÁ√Ê====================================================
	private void setDestination() throws RemoteException{
		
		String[] des = null;
			des = loadAndSort.getDestination();
	
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(des);
		comboBox1.setModel(model);
		comboBox1.validate();
		comboBox1.updateUI();
	}

	private void setEntruck(){
		DefaultTableModel model = new DefaultTableModel(entruck.info,entruck.header);
		entruckTable.setModel(model);
		entruckTable.validate();
		entruckTable.updateUI();
		entruckTable.setVisible(true);
		
		remove(loadAndSortPane);
		add(entruckVO);
		
		entruckVO.validate();
		entruckVO.updateUI();
		entruckVO.setVisible(true);
	}

	private void setEntruckList(){
		try {
			if(briefEntruckList == null)
			briefEntruckList = loadAndSort.getEntruckList();
		} catch (RemoteException e) {
			e.printStackTrace();
			errorDialog.setVisible(true);
		}
		DefaultTableModel model = new DefaultTableModel(briefEntruckList.info,briefEntruckList.header);
		entruckListTable.setModel(model);
		entruckListTable.validate();
		entruckListTable.updateUI();
		entruckListTable.setVisible(true);
	}
//=========================º‡Ã˝==================================================
	private void cancelLoadMouseClicked(MouseEvent e) {
		remove(entruckVO);
		add(loadAndSortPane);
		
		loadAndSortPane.validate();
		loadAndSortPane.updateUI();
		loadAndSortPane.setVisible(true);
	}

	private void errorSureMouseClicked(MouseEvent e) {
		errorDialog.setVisible(false);
	}
	
	private void sortButtonMouseClicked(MouseEvent e) {
		int index = comboBox1.getSelectedIndex();
		String des = (String) comboBox1.getItemAt(index);
		briefOrder = loadAndSort.chooseDestination(des);
		
		DefaultTableModel model = new DefaultTableModel(briefOrder.info,briefOrder.header);
		orderTable.setModel(model);
		orderTable.validate();
		orderTable.updateUI();
		orderTable.setVisible(true);
	}

	private void createEntruckMouseClicked(MouseEvent e) {
		entruck = loadAndSort.createEntruckList(briefOrder.info);
		doEntruck.setVisible(false);
		doEntruck.setEnabled(false);
		saveEntruck.setVisible(true);
		saveEntruck.setEnabled(true);
		setEntruck();
	}

	private void saveEntruckMouseClicked(MouseEvent e) {
		try {
			loadAndSort.saveEntruckList(entruck);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			errorDialog.setVisible(true);
		}
	}

	private void selectEntruckMouseClicked(MouseEvent e) {
		int row = entruckListTable.getSelectedRow();
		String id = (String) entruckListTable.getValueAt(row, 0);
		entruck = loadAndSort.chooseEntruckList(Long.parseLong(id));
		doEntruck.setVisible(true);
		doEntruck.setEnabled(true);
		saveEntruck.setVisible(false);
		saveEntruck.setEnabled(false);
		setEntruck();
	}

	private void doEntruckMouseClicked(MouseEvent e) {
		ResultMessage result = loadAndSort.doEntruck();
		
		resultDialog.setVisible(true);
	}

	private void resultSureMouseClicked(MouseEvent e) {
		setEntruckList();
		resultDialog.setVisible(false);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		loadAndSortPane = new JTabbedPane();
		entruckListPanel = new JPanel();
		scrollPane3 = new JScrollPane();
		entruckListTable = new JTable();
		selectEntruck = new JButton();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		orderTable = new JTable();
		label1 = new JLabel();
		comboBox1 = new JComboBox();
		sortButton = new JButton();
		createEntruck = new JButton();
		entruckVO = new JTabbedPane();
		DeliveryListPanel = new JPanel();
		scrollPane2 = new JScrollPane();
		entruckTable = new JTable();
		cancelLoad = new JButton();
		saveEntruck = new JButton();
		doEntruck = new JButton();
		errorDialog = new JDialog();
		panel2 = new JPanel();
		label2 = new JLabel();
		errorSure = new JButton();
		resultDialog = new JDialog();
		panel3 = new JPanel();
		label3 = new JLabel();
		resultSure = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== loadAndSortPane ========
		{

			//======== entruckListPanel ========
			{

				//======== scrollPane3 ========
				{

					//---- entruckListTable ----
					entruckListTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					scrollPane3.setViewportView(entruckListTable);
				}

				//---- selectEntruck ----
				selectEntruck.setText("\u67e5\u770b");
				selectEntruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectEntruckMouseClicked(e);
					}
				});

				GroupLayout entruckListPanelLayout = new GroupLayout(entruckListPanel);
				entruckListPanel.setLayout(entruckListPanelLayout);
				entruckListPanelLayout.setHorizontalGroup(
					entruckListPanelLayout.createParallelGroup()
						.addGroup(entruckListPanelLayout.createSequentialGroup()
							.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(selectEntruck, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addContainerGap())
				);
				entruckListPanelLayout.setVerticalGroup(
					entruckListPanelLayout.createParallelGroup()
						.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.TRAILING, entruckListPanelLayout.createSequentialGroup()
							.addContainerGap(313, Short.MAX_VALUE)
							.addComponent(selectEntruck)
							.addContainerGap())
				);
			}
			loadAndSortPane.addTab("\u5df2\u5ba1\u6279\u88c5\u8f66\u5355", entruckListPanel);

			//======== panel1 ========
			{

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(orderTable);
				}

				//---- label1 ----
				label1.setText("\u76ee\u7684\u5730");

				//---- sortButton ----
				sortButton.setText("\u5206\u62e3");
				sortButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						sortButtonMouseClicked(e);
					}
				});

				//---- createEntruck ----
				createEntruck.setText("\u751f\u6210\u88c5\u8f66\u5355");
				createEntruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						createEntruckMouseClicked(e);
					}
				});

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(sortButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 468, Short.MAX_VALUE)
							.addComponent(createEntruck)
							.addContainerGap())
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
				);
				panel1Layout.setVerticalGroup(
					panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sortButton)
								.addComponent(createEntruck))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
				);
			}
			loadAndSortPane.addTab("\u5206\u62e3\u88c5\u8f66", panel1);
		}
		add(loadAndSortPane, BorderLayout.CENTER);

		//======== entruckVO ========
		{

			//======== DeliveryListPanel ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(entruckTable);
				}

				//---- cancelLoad ----
				cancelLoad.setText("cancel");
				cancelLoad.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cancelLoadMouseClicked(e);
					}
				});

				//---- saveEntruck ----
				saveEntruck.setText("save");
				saveEntruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						saveEntruckMouseClicked(e);
					}
				});

				//---- doEntruck ----
				doEntruck.setText("do");
				doEntruck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						doEntruckMouseClicked(e);
					}
				});

				GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
				DeliveryListPanel.setLayout(DeliveryListPanelLayout);
				DeliveryListPanelLayout.setHorizontalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
							.addContainerGap(707, Short.MAX_VALUE)
							.addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(cancelLoad, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(saveEntruck, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(doEntruck, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
							.addContainerGap())
				);
				DeliveryListPanelLayout.setVerticalGroup(
					DeliveryListPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
							.addGap(0, 48, Short.MAX_VALUE)
							.addComponent(doEntruck)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(saveEntruck)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(cancelLoad)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				);
			}
			entruckVO.addTab("\u88c5\u8f66\u5355", DeliveryListPanel);
		}

		//======== errorDialog ========
		{
			errorDialog.setTitle("\u7f51\u7edc\u5f02\u5e38");
			Container errorDialogContentPane = errorDialog.getContentPane();
			errorDialogContentPane.setLayout(new BorderLayout());

			//======== panel2 ========
			{

				//---- label2 ----
				label2.setText("\u7f51\u7edc\u8fde\u63a5\u4e2d\u65ad");

				//---- errorSure ----
				errorSure.setText("\u786e\u5b9a");
				errorSure.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						errorSureMouseClicked(e);
					}
				});

				GroupLayout panel2Layout = new GroupLayout(panel2);
				panel2.setLayout(panel2Layout);
				panel2Layout.setHorizontalGroup(
					panel2Layout.createParallelGroup()
						.addGroup(panel2Layout.createSequentialGroup()
							.addContainerGap(80, Short.MAX_VALUE)
							.addGroup(panel2Layout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
									.addComponent(label2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(68, 68, 68))
								.addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
									.addComponent(errorSure)
									.addGap(86, 86, 86))))
				);
				panel2Layout.setVerticalGroup(
					panel2Layout.createParallelGroup()
						.addGroup(panel2Layout.createSequentialGroup()
							.addGap(47, 47, 47)
							.addComponent(label2)
							.addGap(18, 18, 18)
							.addComponent(errorSure)
							.addContainerGap(58, Short.MAX_VALUE))
				);
			}
			errorDialogContentPane.add(panel2, BorderLayout.CENTER);
			errorDialog.setSize(260, 200);
			errorDialog.setLocationRelativeTo(null);
		}

		//======== resultDialog ========
		{
			Container resultDialogContentPane = resultDialog.getContentPane();
			resultDialogContentPane.setLayout(new BorderLayout());

			//======== panel3 ========
			{

				//---- label3 ----
				label3.setText("\u64cd\u4f5c\u6210\u529f");

				//---- resultSure ----
				resultSure.setText("\u786e\u5b9a");
				resultSure.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						resultSureMouseClicked(e);
					}
				});

				GroupLayout panel3Layout = new GroupLayout(panel3);
				panel3.setLayout(panel3Layout);
				panel3Layout.setHorizontalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(panel3Layout.createSequentialGroup()
							.addGap(89, 89, 89)
							.addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(resultSure)
								.addComponent(label3))
							.addContainerGap(98, Short.MAX_VALUE))
				);
				panel3Layout.setVerticalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(panel3Layout.createSequentialGroup()
							.addGap(59, 59, 59)
							.addComponent(label3)
							.addGap(26, 26, 26)
							.addComponent(resultSure)
							.addContainerGap(38, Short.MAX_VALUE))
				);
			}
			resultDialogContentPane.add(panel3, BorderLayout.CENTER);
			resultDialog.pack();
			resultDialog.setLocationRelativeTo(resultDialog.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane loadAndSortPane;
	private JPanel entruckListPanel;
	private JScrollPane scrollPane3;
	private JTable entruckListTable;
	private JButton selectEntruck;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable orderTable;
	private JLabel label1;
	private JComboBox comboBox1;
	private JButton sortButton;
	private JButton createEntruck;
	private JTabbedPane entruckVO;
	private JPanel DeliveryListPanel;
	private JScrollPane scrollPane2;
	private JTable entruckTable;
	private JButton cancelLoad;
	private JButton saveEntruck;
	private JButton doEntruck;
	private JDialog errorDialog;
	private JPanel panel2;
	private JLabel label2;
	private JButton errorSure;
	private JDialog resultDialog;
	private JPanel panel3;
	private JLabel label3;
	private JButton resultSure;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
