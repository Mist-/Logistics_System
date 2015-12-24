/*
 * Created by JFormDesigner on Sun Nov 22 09:58:36 CST 2015
 */

package presentation.transfer.center;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import data.message.LoginMessage;
import data.message.ResultMessage;
import businesslogic.impl.transfer.center.TransferCenterController;
import businesslogic.service.Transfer.hall.TransferCenterService;

/**
 * @author sunxu
 */
public class TransferCenterFrame extends JFrame {
	
	TransferCenterService transferCenter;
	TransferReceivePanel receivePanel;//中转接收面板
	TransferLoadPanel loadPanel;//装运管理面板
	

	
	public TransferCenterFrame(LoginMessage login) {
		try {
			UIManager.setLookAndFeel(new Windows2LookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		try {
			transferCenter = new TransferCenterController(login);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "网络连接中断", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
		}
		initComponents();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog.setVisible(true);
               }
		});
		
		
		transferReceiveStart();//默认显示中转接收界面
		this.setVisible(true);
	}
	
	public void transferReceiveStart(){
		if(receivePanel == null)
			receivePanel = new TransferReceivePanel(transferCenter.startTransferReceiver());
		
		receive.setSelected(true);
		load.setSelected(false);
		receive.setEnabled(false);
		load.setEnabled(true);
		
		Container container = getContentPane();
		container.remove(emptyPanel);
		if(loadPanel != null)
		container.remove(loadPanel);
		container.add(receivePanel,BorderLayout.CENTER);
		
		receivePanel.setVisible(true);
		receivePanel.validate();
		receivePanel.updateUI();
		container.repaint();
		
	}
	
	public void transferLoadStart(){
		if(loadPanel == null)
			loadPanel = new TransferLoadPanel(transferCenter.startTransferLoad());
		
		receive.setSelected(false);
		load.setSelected(true);
		receive.setEnabled(true);
		load.setEnabled(false);
		
		Container container = getContentPane();
		container.remove(emptyPanel);
		container.remove(receivePanel);
		container.add(loadPanel,BorderLayout.CENTER);
		loadPanel.setVisible(true);
		loadPanel.validate();
		loadPanel.updateUI();
		container.repaint();
	}

	private void receiveMouseClicked(MouseEvent e) {
		transferReceiveStart();
	}

	private void loadMouseClicked(MouseEvent e) {
		transferLoadStart();
	}

	private void button1MouseReleased(MouseEvent e) {
		closeDialog.setVisible(false);
	}

	private void button2MouseReleased(MouseEvent e) {
		System.exit(DISPOSE_ON_CLOSE);
	}

	private void thisWindowClosed(WindowEvent e) {
	closeDialog.setVisible(true);
	closeDialog.repaint();
	}
	
	
	
	
//=======================================================================================================================================
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menu2 = new JMenu();
		panel1 = new JPanel();
		receive = new JButton();
		load = new JButton();
		label6 = new JLabel();
		label7 = new JLabel();
		emptyPanel = new JPanel();
		tabbedPane1 = new JTabbedPane();
		label1 = new JLabel();
		closeDialog = new JDialog();
		panel3 = new JPanel();
		label3 = new JLabel();
		label10 = new JLabel();
		button1 = new JButton();
		button2 = new JButton();

		//======== this ========
		setTitle("\u4e2d\u8f6c\u4e2d\u5fc3");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				thisWindowClosed(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("\u9009\u9879");
				menu1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("\u5e2e\u52a9");
				menu2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//======== panel1 ========
		{

			//---- receive ----
			receive.setIcon(new ImageIcon(getClass().getResource("/icons/transit_72x72.png")));
			receive.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			receive.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					receiveMouseClicked(e);
				}
			});

			//---- load ----
			load.setIcon(new ImageIcon(getClass().getResource("/icons/box_72x72.png")));
			load.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			load.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					loadMouseClicked(e);
				}
			});

			//---- label6 ----
			label6.setText("\u4e2d\u8f6c\u63a5\u6536");
			label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- label7 ----
			label7.setText("\u88c5\u8fd0\u7ba1\u7406");
			label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(receive, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(24, 24, 24)
								.addComponent(label6, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addComponent(load))
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(44, 44, 44)
								.addComponent(label7)))
						.addContainerGap(547, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(receive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(load, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(label7))
						.addGap(6, 6, 6))
			);
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== emptyPanel ========
		{

			//======== tabbedPane1 ========
			{
				tabbedPane1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- label1 ----
				label1.setText("text");
				tabbedPane1.addTab("text", label1);
			}

			GroupLayout emptyPanelLayout = new GroupLayout(emptyPanel);
			emptyPanel.setLayout(emptyPanelLayout);
			emptyPanelLayout.setHorizontalGroup(
				emptyPanelLayout.createParallelGroup()
					.addGroup(emptyPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
			);
			emptyPanelLayout.setVerticalGroup(
				emptyPanelLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, emptyPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
			);
		}
		contentPane.add(emptyPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//======== closeDialog ========
		{
			closeDialog.setTitle("\u63d0\u793a");
			Container closeDialogContentPane = closeDialog.getContentPane();
			closeDialogContentPane.setLayout(new BorderLayout());

			//======== panel3 ========
			{

				//---- label3 ----
				label3.setText("\u8bf7\u68c0\u67e5\u662f\u5426\u5df2\u7ecf\u5904\u7406");

				//---- label10 ----
				label10.setText("\u5168\u90e8\u5df2\u5ba1\u6279\u4e2d\u8f6c\u5355\u548c\u5230\u8fbe\u5355");

				//---- button1 ----
				button1.setText("\u672a\u5904\u7406\u5b8c");
				button1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						button1MouseReleased(e);
					}
				});

				//---- button2 ----
				button2.setText("\u786e\u8ba4\u5173\u95ed");
				button2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						button2MouseReleased(e);
					}
				});

				GroupLayout panel3Layout = new GroupLayout(panel3);
				panel3.setLayout(panel3Layout);
				panel3Layout.setHorizontalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
							.addContainerGap(57, Short.MAX_VALUE)
							.addGroup(panel3Layout.createParallelGroup()
								.addGroup(panel3Layout.createSequentialGroup()
									.addGap(10, 10, 10)
									.addComponent(button1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
									.addComponent(label10, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
									.addGap(50, 50, 50))))
						.addGroup(panel3Layout.createSequentialGroup()
							.addGroup(panel3Layout.createParallelGroup()
								.addGroup(panel3Layout.createSequentialGroup()
									.addGap(79, 79, 79)
									.addComponent(label3))
								.addGroup(panel3Layout.createSequentialGroup()
									.addGap(94, 94, 94)
									.addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(82, Short.MAX_VALUE))
				);
				panel3Layout.setVerticalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(panel3Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label10, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(button1, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
			}
			closeDialogContentPane.add(panel3, BorderLayout.CENTER);
			closeDialog.pack();
			closeDialog.setLocationRelativeTo(closeDialog.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenu menu2;
	private JPanel panel1;
	private JButton receive;
	private JButton load;
	private JLabel label6;
	private JLabel label7;
	private JPanel emptyPanel;
	private JTabbedPane tabbedPane1;
	private JLabel label1;
	private JDialog closeDialog;
	private JPanel panel3;
	private JLabel label3;
	private JLabel label10;
	private JButton button1;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
