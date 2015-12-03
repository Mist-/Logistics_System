/*
 * Created by JFormDesigner on Mon Nov 30 21:09:47 CST 2015
 */

package presentation.transfer.center;

import java.awt.*;
import javax.swing.*;

import businesslogic.service.Transfer.center.TransferLoadService;

/**
 * @author sunxu
 */
public class TransferLoadPanel extends JPanel {
	TransferLoadService transferLoad;
	
	public TransferLoadPanel(TransferLoadService transferLoad) {
		this.transferLoad = transferLoad;
		
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		tabbedPane1 = new JTabbedPane();
		loadPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		orderTable = new JTable();
		label1 = new JLabel();
		comboBox1 = new JComboBox();
		label2 = new JLabel();
		comboBox2 = new JComboBox();
		getOrder = new JButton();
		button1 = new JButton();
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
				label1.setText("transferType");

				//---- label2 ----
				label2.setText("destination");

				//---- getOrder ----
				getOrder.setText("getOrder");

				//---- button1 ----
				button1.setText("createDeliveryList");

				GroupLayout loadPanelLayout = new GroupLayout(loadPanel);
				loadPanel.setLayout(loadPanelLayout);
				loadPanelLayout.setHorizontalGroup(
					loadPanelLayout.createParallelGroup()
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
						.addGroup(loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(comboBox1, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
								.addComponent(comboBox2, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
							.addGap(58, 58, 58)
							.addComponent(getOrder, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(button1)
							.addContainerGap(328, Short.MAX_VALUE))
				);
				loadPanelLayout.setVerticalGroup(
					loadPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, loadPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(loadPanelLayout.createParallelGroup()
								.addGroup(loadPanelLayout.createSequentialGroup()
									.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label1)
										.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(loadPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label2)
										.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(getOrder, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
				);
			}
			tabbedPane1.addTab("load", loadPanel);
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTabbedPane tabbedPane1;
	private JPanel loadPanel;
	private JScrollPane scrollPane1;
	private JTable orderTable;
	private JLabel label1;
	private JComboBox comboBox1;
	private JLabel label2;
	private JComboBox comboBox2;
	private JButton getOrder;
	private JButton button1;
	private JTabbedPane tabbedPane2;
	private JPanel DeliveryListPanel;
	private JScrollPane scrollPane2;
	private JTable table1;
	private JButton cancelLoad;
	private JButton saveList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
