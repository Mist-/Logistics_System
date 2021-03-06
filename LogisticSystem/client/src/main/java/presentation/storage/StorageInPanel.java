/*
 * Created by JFormDesigner on Tue Nov 24 22:01:45 CST 2015
 */

package presentation.storage;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import utils.Timestamper;
import data.message.ResultMessage;
import data.vo.ArrivalVO;
import data.vo.BriefArrivalAndStorageInVO;
import data.vo.StorageInVO;
import businesslogic.service.storage.StorageInService;

/**
 * @author sunxu
 */
public class StorageInPanel extends JPanel {
	BriefArrivalAndStorageInVO briefArrivalAndStorageInVO;
	StorageInService storageInService;
	ArrivalVO arrival;
	StorageInVO storageIn;
	int storageInCounter = 0;
	int arrivalCounter = 0;
	boolean showOrStorageIn;//show = false,storageIn = true

	
	public boolean isClear(){
		if(arrivalCounter >0 || storageInCounter>0){
			return false;
		}else {
			return true;
		}
	}
	
	public StorageInPanel(StorageInService storageIn) {
		this.storageInService = storageIn;
		initComponents();
		setList();
		this.repaint();
	}
	
	private void setList(){
		selectArrival.setEnabled(false);
		selectStorageIn.setEnabled(false);
		try {
			briefArrivalAndStorageInVO = storageInService.newStorageIn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		DefaultTableModel storageInModel = new DefaultTableModel(briefArrivalAndStorageInVO.getStorageInListInfo(), briefArrivalAndStorageInVO.getStorageInTittle());
		storageInTable.setModel(storageInModel);
		storageInCounter = briefArrivalAndStorageInVO.getStorageInListInfo().length;
		DefaultTableModel arrivalModel = new DefaultTableModel(briefArrivalAndStorageInVO.getArrivalListInfo(),briefArrivalAndStorageInVO.getArrivalTittle());
		arrivalCounter = briefArrivalAndStorageInVO.getArrivalListInfo().length;
		storageInTable.setModel(storageInModel);
		arriveListTable.setModel(arrivalModel);
		
		storageInTable.updateUI();
		arriveListTable.updateUI();
		storageInTable.repaint();
		arriveListTable.repaint();
//		if(storageInVO != null)
//		remove(storageInVO);
//		if(arrivalVO != null)
//		remove(arrivalVO);
		add(listPane,BorderLayout.CENTER);
		
		listPane.validate();
		listPane.updateUI();
		this.repaint();
	}

	private void setStorageIn(){
		storageDate.setText(storageIn.getDate());
		DefaultTableModel model = new DefaultTableModel(storageIn.getInfo(),storageIn.getHeader());
		storageOrder.setModel(model);
		storageOrder.updateUI();
		storageOrder.repaint();
		storageInVO.validate();
		storageInVO.updateUI();
		
		if(listPane != null)
		remove(listPane);
		if(arrivalVO != null)
		remove(arrivalVO);
		
		add(storageInVO,BorderLayout.CENTER);
		storageInVO.updateUI();
		storageInVO.setVisible(true);
	}
	
	private void setArrival(ArrivalVO vo) {
		DefaultTableModel model = new DefaultTableModel(vo.getOrderAndStatus(),vo.getHeader());
		arrivalTable.setModel(model);
		arrivalTable.setEnabled(false);
		arrivalTable.updateUI();
		transferID.setText(vo.getDeliveryListNum());
		from.setText(vo.getFromName());
		arrivalDate.setText(vo.getDate());
		transferID.setEnabled(false);
		from.setEnabled(false);
		arrivalDate.setEnabled(false);
		
		remove(listPane);
		remove(storageInVO);
		add(arrivalVO);
		
		arrivalVO.updateUI();
		this.repaint();
	}

	private void selectStorageInMouseClicked(MouseEvent e) {
		int row = storageInTable.getSelectedRow();
		String info = (String) storageInTable.getValueAt(row, 0);
		long storageInID = Long.parseLong(info);
		storageIn = storageInService.getStorageIn(storageInID);
		saveStorageIn.setVisible(false);
		sureStorageIn.setVisible(true);
		setStorageIn();
		

	}
	
	

	private void arriveListTableMouseClicked(MouseEvent e) {
		selectArrival.setEnabled(true);
	}

	private void selectArrivalMouseClicked(MouseEvent e) {
		showOrStorageIn = false;
		sureStorageIn.setVisible(true);
		saveStorageIn.setVisible(true);
		int row = arriveListTable.getSelectedRow();
		String info = (String) arriveListTable.getValueAt(row, 0);
		System.out.println(info);
		long arrivalID = Long.parseLong(info);
		arrival = storageInService.getArriveList(arrivalID);
		setArrival(arrival);
	}

	private void createStorageInMouseClicked(MouseEvent e) {
		showOrStorageIn = true;
		sureStorageIn.setVisible(false);
		saveStorageIn.setVisible(true);
		storageIn = storageInService.sort(arrival);
		if(storageIn == null){
			JOptionPane.showMessageDialog(null, "入库失败，请检查仓库空间是否充足", "提示", JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		storageIn.setDate(Timestamper.getTimeByDate());
		setStorageIn();		
		storageInVO.validate();
		storageInVO.updateUI();
		this.setVisible(true);
	}

	private void storageInTableMouseClicked(MouseEvent e) {
		selectStorageIn.setEnabled(true);
	}


	private void cancelArrivalMouseClicked(MouseEvent e) {
		remove(arrivalVO);
		add(listPane);
		
		listPane.setVisible(true);
		this.updateUI();
		this.repaint();
	}

	private void saveStorageInMouseReleased(MouseEvent e) {
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = storageInService.saveStorageInList(storageIn);
			storageInService.doArrive();
		} catch (RemoteException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "网络连接中断", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		if (result == ResultMessage.SUCCESS) {
			JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			DefaultTableModel model = (DefaultTableModel) arriveListTable.getModel();
			model.removeRow(arriveListTable.getSelectedRow());
			arrivalCounter--;
			arriveListTable.setModel(model);
			arriveListTable.updateUI();
			arriveListTable.repaint();
			remove(storageInVO);
			remove(arrivalVO);
			add(listPane,BorderLayout.CENTER);
			listPane.updateUI();
			listPane.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void searchMouseReleased(MouseEvent e) {
		long id = Long.parseLong(arrivalID.getText());
		arrival = storageInService.getArriveList(id);
		setArrival(arrival);
	}

	private void cancelStorageInMouseReleased(MouseEvent e) {
		remove(storageInVO);
		add(listPane,BorderLayout.CENTER);
		listPane.updateUI();
		listPane.setVisible(true);
	}

	private void sureStorageInMouseReleased(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "入库完成", "提示", JOptionPane.INFORMATION_MESSAGE);
		storageInCounter--;
		int row = storageInTable.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) storageInTable.getModel();
		model.removeRow(row);
		storageInTable.updateUI();
		remove(storageInVO);
		add(listPane,BorderLayout.CENTER);
		listPane.setVisible(true);
	}



	private void refreshButtonMouseReleased(MouseEvent e) {
		if(storageInCounter <= 0 && arrivalCounter <= 0){
			System.out.println("入库刷新");
			setList();
		}
	}

	private void refreshButton2MouseReleased(MouseEvent e) {
		if(storageInCounter <= 0 && arrivalCounter <= 0){
			System.out.println("入库刷新");
			setList();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        listPane = new JTabbedPane();
        storageInList = new JPanel();
        scrollPane4 = new JScrollPane();
        storageInTable = new JTable();
        selectStorageIn = new JButton();
        refreshButton = new JButton();
        search2 = new JButton();
        arrivalID2 = new JTextField();
        arriveList = new JPanel();
        scrollPane3 = new JScrollPane();
        arriveListTable = new JTable();
        selectArrival = new JButton();
        refreshButton2 = new JButton();
        arrivalID = new JTextField();
        search = new JButton();
        storageInVO = new JTabbedPane();
        storageInPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        storageOrder = new JTable();
        label10 = new JLabel();
        storageDate = new JTextField();
        cancelStorageIn = new JButton();
        saveStorageIn = new JButton();
        sureStorageIn = new JButton();
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
        label4 = new JLabel();
        createStorageIn = new JButton();
        cancelArrival = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== listPane ========
        {
            listPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== storageInList ========
            {

                //======== scrollPane4 ========
                {

                    //---- storageInTable ----
                    storageInTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    storageInTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            storageInTableMouseClicked(e);
                            storageInTableMouseClicked(e);
                        }
                    });
                    scrollPane4.setViewportView(storageInTable);
                }

                //---- selectStorageIn ----
                selectStorageIn.setText("\u67e5\u770b");
                selectStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                selectStorageIn.setIcon(new ImageIcon(getClass().getResource("/icons/see_24x24.png")));
                selectStorageIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectStorageInMouseClicked(e);
                    }
                });

                //---- refreshButton ----
                refreshButton.setText("\u5237\u65b0");
                refreshButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                refreshButton.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
                refreshButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        refreshButtonMouseReleased(e);
                    }
                });

                //---- search2 ----
                search2.setText("\u641c\u7d22");
                search2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                search2.setIcon(new ImageIcon(getClass().getResource("/icons/search_24x24.png")));
                search2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        searchMouseReleased(e);
                    }
                });

                GroupLayout storageInListLayout = new GroupLayout(storageInList);
                storageInList.setLayout(storageInListLayout);
                storageInListLayout.setHorizontalGroup(
                    storageInListLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, storageInListLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(arrivalID2, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(search2)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(storageInListLayout.createSequentialGroup()
                            .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 794, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(storageInListLayout.createParallelGroup()
                                .addComponent(selectStorageIn, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                            .addContainerGap())
                );
                storageInListLayout.setVerticalGroup(
                    storageInListLayout.createParallelGroup()
                        .addGroup(storageInListLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(storageInListLayout.createParallelGroup()
                                .addComponent(search2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(arrivalID2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9)
                            .addGroup(storageInListLayout.createParallelGroup()
                                .addGroup(storageInListLayout.createSequentialGroup()
                                    .addComponent(selectStorageIn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(236, Short.MAX_VALUE))
                                .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)))
                );
            }
            listPane.addTab("\u5df2\u5ba1\u6279\u5165\u5e93\u5355", storageInList);

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

                //---- selectArrival ----
                selectArrival.setText("\u67e5\u770b");
                selectArrival.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                selectArrival.setIcon(new ImageIcon(getClass().getResource("/icons/see_24x24.png")));
                selectArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectArrivalMouseClicked(e);
                    }
                });

                //---- refreshButton2 ----
                refreshButton2.setText("\u5237\u65b0");
                refreshButton2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                refreshButton2.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
                refreshButton2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        refreshButton2MouseReleased(e);
                    }
                });

                //---- search ----
                search.setText("\u641c\u7d22");
                search.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                search.setIcon(new ImageIcon(getClass().getResource("/icons/search_24x24.png")));
                search.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        searchMouseReleased(e);
                    }
                });

                GroupLayout arriveListLayout = new GroupLayout(arriveList);
                arriveList.setLayout(arriveListLayout);
                arriveListLayout.setHorizontalGroup(
                    arriveListLayout.createParallelGroup()
                        .addGroup(arriveListLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(arriveListLayout.createParallelGroup()
                                .addGroup(arriveListLayout.createSequentialGroup()
                                    .addComponent(arrivalID, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(search)
                                    .addGap(129, 680, Short.MAX_VALUE))
                                .addGroup(arriveListLayout.createSequentialGroup()
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(arriveListLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(refreshButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selectArrival, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())))
                );
                arriveListLayout.setVerticalGroup(
                    arriveListLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, arriveListLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(arriveListLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(arrivalID, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(search, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(arriveListLayout.createParallelGroup()
                                .addGroup(arriveListLayout.createSequentialGroup()
                                    .addComponent(selectArrival, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(refreshButton2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(238, Short.MAX_VALUE))
                                .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
                );
            }
            listPane.addTab("\u5df2\u5ba1\u6279\u5230\u8fbe\u5355", arriveList);
        }
        add(listPane, BorderLayout.NORTH);

        //======== storageInVO ========
        {
            storageInVO.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== storageInPanel ========
            {

                //======== scrollPane2 ========
                {

                    //---- storageOrder ----
                    storageOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPane2.setViewportView(storageOrder);
                }

                //---- label10 ----
                label10.setText("\u65e5\u671f\uff1a");
                label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- storageDate ----
                storageDate.setEditable(false);
                storageDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- cancelStorageIn ----
                cancelStorageIn.setText("\u53d6\u6d88");
                cancelStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelStorageIn.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
                cancelStorageIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        cancelStorageInMouseReleased(e);
                    }
                });

                //---- saveStorageIn ----
                saveStorageIn.setText("\u4fdd\u5b58");
                saveStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                saveStorageIn.setIcon(new ImageIcon(getClass().getResource("/icons/save_24x24.png")));
                saveStorageIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        saveStorageInMouseReleased(e);
                    }
                });

                //---- sureStorageIn ----
                sureStorageIn.setText("\u786e\u8ba4\u5165\u5e93");
                sureStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                sureStorageIn.setIcon(new ImageIcon(getClass().getResource("/icons/sign_24x24.png")));
                sureStorageIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        sureStorageInMouseReleased(e);
                    }
                });

                GroupLayout storageInPanelLayout = new GroupLayout(storageInPanel);
                storageInPanel.setLayout(storageInPanelLayout);
                storageInPanelLayout.setHorizontalGroup(
                    storageInPanelLayout.createParallelGroup()
                        .addGroup(storageInPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(storageInPanelLayout.createParallelGroup()
                                .addGroup(storageInPanelLayout.createSequentialGroup()
                                    .addComponent(label10)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(storageDate, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                                .addGroup(storageInPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 765, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(storageInPanelLayout.createParallelGroup()
                                        .addGroup(storageInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sureStorageIn, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                            .addComponent(saveStorageIn, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addComponent(cancelStorageIn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                storageInPanelLayout.setVerticalGroup(
                    storageInPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, storageInPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(storageInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addComponent(storageDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(storageInPanelLayout.createParallelGroup()
                                .addGroup(storageInPanelLayout.createSequentialGroup()
                                    .addComponent(sureStorageIn)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveStorageIn)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                                    .addComponent(cancelStorageIn))
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                            .addContainerGap())
                );
            }
            storageInVO.addTab("\u5165\u5e93\u5355", storageInPanel);
        }

        //======== arrivalVO ========
        {

            //======== panel1 ========
            {

                //======== scrollPane1 ========
                {

                    //---- arrivalTable ----
                    arrivalTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPane1.setViewportView(arrivalTable);
                }

                //---- label1 ----
                label1.setText("\u8fd0\u8f93\u5355\u7f16\u53f7\uff1a");
                label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- transferID ----
                transferID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- from ----
                from.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label3 ----
                label3.setText("\u65e5\u671f\uff1a");
                label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- arrivalDate ----
                arrivalDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label4 ----
                label4.setText("\u51fa\u53d1\u5730\uff1a");
                label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- createStorageIn ----
                createStorageIn.setText("\u5165\u5e93");
                createStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                createStorageIn.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
                createStorageIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createStorageInMouseClicked(e);
                    }
                });

                //---- cancelArrival ----
                cancelArrival.setText("\u53d6\u6d88");
                cancelArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelArrival.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
                cancelArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelArrivalMouseClicked(e);
                    }
                });

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(label1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(transferID, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(label4)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(from, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label3)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(117, 117, 117))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(cancelArrival, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createStorageIn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(transferID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4)
                                .addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3)
                                .addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(createStorageIn)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                                    .addComponent(cancelArrival))
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                            .addContainerGap())
                );
            }
            arrivalVO.addTab("\u5230\u8fbe\u5355", panel1);
        }
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane listPane;
    private JPanel storageInList;
    private JScrollPane scrollPane4;
    private JTable storageInTable;
    private JButton selectStorageIn;
    private JButton refreshButton;
    private JButton search2;
    private JTextField arrivalID2;
    private JPanel arriveList;
    private JScrollPane scrollPane3;
    private JTable arriveListTable;
    private JButton selectArrival;
    private JButton refreshButton2;
    private JTextField arrivalID;
    private JButton search;
    private JTabbedPane storageInVO;
    private JPanel storageInPanel;
    private JScrollPane scrollPane2;
    private JTable storageOrder;
    private JLabel label10;
    private JTextField storageDate;
    private JButton cancelStorageIn;
    private JButton saveStorageIn;
    private JButton sureStorageIn;
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
    private JLabel label4;
    private JButton createStorageIn;
    private JButton cancelArrival;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
