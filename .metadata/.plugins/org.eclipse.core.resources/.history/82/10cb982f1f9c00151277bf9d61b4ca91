package presentation.storage;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import businesslogic.impl.storage.StorageBusinessController;
import businesslogic.impl.storage.StorageIn;
import businesslogic.service.storage.StorageBusinessService;
import businesslogic.service.storage.StorageInService;
import businesslogic.service.storage.StorageOperateService;
import businesslogic.service.storage.StorageOutService;
import data.message.LoginMessage;
/*
 * Created by JFormDesigner on Thu Nov 19 14:29:33 CST 2015
 */
import data.message.ResultMessage;
/**
 * @author sunxu
 */
public class StorageFrame extends JFrame {
	StorageBusinessService  storageBusiness;
	StorageOperatePanel storageOperateVO;
	StorageOutPanel storageOutVO;
	StorageInPanel storageInVO;
	
	public StorageFrame(LoginMessage user) {
		try {
			UIManager.setLookAndFeel(new Windows2LookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		initComponents();
		try {
			storageBusiness = new StorageBusinessController(user);
		} catch (Exception e) {
			e.printStackTrace();
			dialog1.setVisible(true);
		}
		
		storageInStart();//默认显示入库界面
		this.setVisible(true);
	
	}
	

	//测试用入口
	public static void main(String[] args){

		StorageFrame storage = new StorageFrame(new LoginMessage(ResultMessage.SUCCESS, 100000));
	}
	
	

	//显示入库panel
	private boolean storageInStart(){
		storageIn.setEnabled(false);
		storageOut.setEnabled(true);
		storageOperete.setEnabled(true);
		
		storageIn.setSelected(true);
		storageOut.setSelected(false);
		storageOperete.setSelected(false);
		
		if(storageInVO == null){
			StorageInService storageIn = storageBusiness.startStorageIn();
			if(storageIn == null){
				JOptionPane.showMessageDialog(null, "仓库未初始化", "提示",	 JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			storageInVO = new StorageInPanel(storageIn);
		}
		

		
		Container container = getContentPane();
		container.remove(emptyPanel);
		if(storageOperateVO != null)
		container.remove(storageOperateVO);
		if(storageOutVO != null)
		container.remove(storageOutVO);
		container.add(storageInVO,BorderLayout.CENTER);
		
		storageInVO.setVisible(true);
		storageInVO.validate();
		storageInVO.updateUI();
		container.repaint();
		
		return true;
	}

	//显示出库panel
	private boolean storageOutStart(){
		storageIn.setEnabled(true);
		storageOut.setEnabled(false);
		storageOperete.setEnabled(true);
		
		storageIn.setSelected(false);
		storageOut.setSelected(true);
		storageOperete.setSelected(false);
		
		if(storageOutVO == null){
			StorageOutService storageOut = storageBusiness.startStorageOut();
			if(storageOut == null){
				JOptionPane.showMessageDialog(null, "仓库未初始化", "提示",	 JOptionPane.INFORMATION_MESSAGE);
				return false;
			}else{
			storageOutVO = new StorageOutPanel(storageOut);
			}
		}
		

		
		Container container = getContentPane();
		container.remove(emptyPanel);
		container.remove(storageInVO);
		if(storageOperateVO != null)
		container.remove(storageOperateVO);
		container.add(storageOutVO,BorderLayout.CENTER);
		
		storageOutVO.setVisible(true);
		storageOutVO.validate();
		storageOutVO.updateUI();
		container.repaint();
		return true;
	}

	//显示库存操作panel
	private void storageOperateStart(){
		if(storageOperateVO == null){
			try {
				storageOperateVO = new StorageOperatePanel(storageBusiness.startStorageOperate());
			} catch (RemoteException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "网络连接中断","网络异常",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		storageIn.setEnabled(true);
		storageOut.setEnabled(true);
		storageOperete.setEnabled(false);
		
		storageIn.setSelected(false);
		storageOut.setSelected(false);
		storageOperete.setSelected(true);
		
		Container container = getContentPane();
		container.remove(emptyPanel);
		container.remove(storageInVO);
		if(storageOutVO != null)
		container.remove(storageOutVO);
		container.add(storageOperateVO,BorderLayout.CENTER);
		
		storageOperateVO.setVisible(true);
		storageOperateVO.validate();
		storageOperateVO.updateUI();
		container.repaint();
	}
	

//========================监听=====================================	
	
	//storageOperate Button
	private void storageOpereteMouseClicked(MouseEvent e) {
		storageOperateStart();
	}
	//storageIn Button
	private void storageInMouseClicked(MouseEvent e) {
		storageInStart();

	}
	//storageOut Button
	private void storageOutMouseClicked(MouseEvent e) {
		storageOutStart();
	}

//========================监听=====================================
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menu2 = new JMenu();
		panel1 = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		storageOperete = new JToggleButton();
		storageOut = new JToggleButton();
		storageIn = new JToggleButton();
		emptyPanel = new JPanel();
		tabbedPane1 = new JTabbedPane();
		panel2 = new JPanel();
		label6 = new JLabel();
		dialog1 = new JDialog();
		panel3 = new JPanel();
		label7 = new JLabel();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u4e2d\u8f6c\u4e2d\u5fc3\u4ed3\u5e93\u7ba1\u7406");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("\u9009\u9879");
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("\u5e2e\u52a9");
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//======== panel1 ========
		{

			//---- label1 ----
			label1.setText("\u5165\u5e93");

			//---- label2 ----
			label2.setText("\u51fa\u5e93");

			//---- label3 ----
			label3.setText("\u5e93\u5b58\u64cd\u4f5c");

			//---- storageOperete ----
			storageOperete.setText("text");
			storageOperete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					storageOpereteMouseClicked(e);
				}
			});

			//---- storageOut ----
			storageOut.setText("text");
			storageOut.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					storageOutMouseClicked(e);
				}
			});

			//---- storageIn ----
			storageIn.setText("text");
			storageIn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					storageInMouseClicked(e);
				}
			});

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(label1, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
							.addComponent(storageIn, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(storageOut, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
							.addComponent(label2, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(label3, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
							.addComponent(storageOperete, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
						.addContainerGap(538, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(storageOperete, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
							.addComponent(storageOut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(storageIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label1)
							.addComponent(label3)
							.addComponent(label2)))
			);
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== emptyPanel ========
		{
			emptyPanel.setLayout(new BorderLayout());

			//======== tabbedPane1 ========
			{

				//======== panel2 ========
				{

					//---- label6 ----
					label6.setText("text");

					GroupLayout panel2Layout = new GroupLayout(panel2);
					panel2.setLayout(panel2Layout);
					panel2Layout.setHorizontalGroup(
						panel2Layout.createParallelGroup()
							.addComponent(label6, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
					);
					panel2Layout.setVerticalGroup(
						panel2Layout.createParallelGroup()
							.addComponent(label6, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
					);
				}
				tabbedPane1.addTab("text", panel2);
			}
			emptyPanel.add(tabbedPane1, BorderLayout.CENTER);
		}
		contentPane.add(emptyPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//======== dialog1 ========
		{
			Container dialog1ContentPane = dialog1.getContentPane();
			dialog1ContentPane.setLayout(new BorderLayout());

			//======== panel3 ========
			{

				//---- label7 ----
				label7.setText("\u672a\u67e5\u627e\u5230\u7528\u6237\u4fe1\u606f");

				GroupLayout panel3Layout = new GroupLayout(panel3);
				panel3.setLayout(panel3Layout);
				panel3Layout.setHorizontalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(panel3Layout.createSequentialGroup()
							.addGap(29, 29, 29)
							.addComponent(label7, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(36, Short.MAX_VALUE))
				);
				panel3Layout.setVerticalGroup(
					panel3Layout.createParallelGroup()
						.addGroup(panel3Layout.createSequentialGroup()
							.addGap(37, 37, 37)
							.addComponent(label7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(79, Short.MAX_VALUE))
				);
			}
			dialog1ContentPane.add(panel3, BorderLayout.CENTER);
			dialog1.pack();
			dialog1.setLocationRelativeTo(dialog1.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenu menu2;
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JToggleButton storageOperete;
	private JToggleButton storageOut;
	private JToggleButton storageIn;
	private JPanel emptyPanel;
	private JTabbedPane tabbedPane1;
	private JPanel panel2;
	private JLabel label6;
	private JDialog dialog1;
	private JPanel panel3;
	private JLabel label7;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
