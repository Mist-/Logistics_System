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
import data.vo.SendListVO;
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
	ArrivalListVO arrivalList;
	SendListVO sendList;
	int arrivalCounter = 0;
	
	public boolean isClear(){
		if(arrivalCounter > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public EntruckReceivePanel(EntruckReceiveService entruckReceive) {
		this.entruckReceive = entruckReceive;
		initComponents();
		searchList.setEnabled(false);
		selectArrival.setEnabled(false);
		getArrivalList();//只能调用一次
		setArrivalList();
		this.validate();
		this.updateUI();
		this.setVisible(true);
	}
	
	private void setSendList(){
		if(sendList != null){
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(sendList.senders);
			senderBox.setModel(model);
			DefaultTableModel model2 = new DefaultTableModel(arrival.getOrderAndStatus(),arrival.getHeader());
			sendTable.setModel(model2);
			sendData.setText(sendList.date);
			senderBox.updateUI();
			sendTable.updateUI();
		}
		
		remove(arrivalVO);
		add(sendListPane,BorderLayout.CENTER);
		sendListPane.updateUI();
		sendListPane.setVisible(true);
	}
	
	private void setArrivalList(){
		arrivalTable.validate();
		arrivalTable.updateUI();
		arrivalTable.repaint();
		if(sendListPane != null)
			remove(sendListPane);
		if(arrivalVO != null)
			remove(arrivalVO);
		if(entruckVO != null)
			remove(entruckVO);
		add(startPane,BorderLayout.CENTER);
		startPane.updateUI();
		startPane.setVisible(true);
	}

	//设置到达单列表
	private void getArrivalList() {
		arrivalList = null;
		try {
			arrivalList = entruckReceive.getCheckedArrivals();
			if(arrivalList == null){
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			errorDialog.validate();
			errorDialog.repaint();
			errorDialog.setVisible(true);
		}
		DefaultTableModel arrivalListModel = new DefaultTableModel(
				arrivalList.info, arrivalList.header);
		arrivalCounter = arrivalList.info.length;
		arrivalTable.setModel(arrivalListModel);
		selectArrival.setEnabled(false);
	}

	//设置到达单
	private void setArrivalVO() {
		modifyStatus.setEnabled(false);
		DefaultTableModel model = new DefaultTableModel(
				arrival.getOrderAndStatus(), arrival.getHeader());
		arrivalVOTable.setModel(model);
		arrivalVOTable.validate();
		arrivalVOTable.updateUI();
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>(arrival.statusList);
		status.setModel(model1);
		status.updateUI();
		status.setEnabled(false);
		transferID.setText(arrival.getDeliveryListNum());
		from.setText(arrival.getFromName());
		arrivalDate.setText(arrival.getDate());
		remove(startPane);
		remove(entruckVO);
		add(arrivalVO, BorderLayout.CENTER);
		arrivalVO.validate();
		arrivalVO.updateUI();
		arrivalVO.setVisible(true);
	}

	//设置运输单列表
	private void getDeliveryList(long id) {

		try {
			if (transfer.isSelected()) {
				transferList = entruckReceive.searchTransferList(id);
				if(transferList == null){
					deliveryID.setText("单号不存在");
					return;
				}
				setTransferList();
			
			} else {
				entruckList = entruckReceive.searchEntruckList(id);
				if(entruckList == null){
					deliveryID.setText("单号不存在");
					return;
				}
				setEntruckList();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			errorDialog.setVisible(true);
		}
		

		remove(startPane);
		if (arrivalVO != null)
			remove(arrivalVO);
		add(entruckVO, BorderLayout.CENTER);
		deliveryID.setText("请输入单号");
		entruckVO.validate();
		entruckVO.updateUI();
		entruckVO.setVisible(true);

	}

	//设置装车单
	private void setEntruckList() {
		deliveryID.setText("请输入单号");
		DefaultTableModel model = new DefaultTableModel(entruckList.info,
				entruckList.header);
		listType.setText("装车单");
		listID.setText(entruckList.entruckListID);
		date.setText(entruckList.loadingDate);
		fromName.setText(entruckList.fromName);
		destName.setText(entruckList.destName);
		vehicleID.setText(entruckList.vehicleID);
		staff.setText(entruckList.monitorName);
		fee.setText(entruckList.fee);
		transferType.setText("汽运");
		deliveryTable.setModel(model);
		deliveryTable.validate();
		deliveryTable.updateUI();
		deliveryTable.repaint();
	}

	//设置中转单
	private void setTransferList() {
		DefaultTableModel model = new DefaultTableModel(
				transferList.orderAndPosition, transferList.header);
		deliveryTable.setModel(model);
		listType.setText("中转单");
		listID.setText(transferList.transferListID);
		fromName.setText(transferList.transferCenter);
		destName.setText(transferList.targetName);
		date.setText(transferList.date);
		vehicleID.setText(transferList.vehicleCode);
		staff.setText(transferList.staff);
		fee.setText(transferList.fee);
		transferType.setText(transferList.transferType);
		deliveryTable.validate();
		deliveryTable.updateUI();
		deliveryTable.repaint();
	}

	//===============================监听===============================
	private void cancelArrivalMouseClicked(MouseEvent e) {
		cancelDialog.setVisible(true);

	}

	private void selectArrivalMouseClicked(MouseEvent e) {
		if(arrivalList != null){
		int row = arrivalTable.getSelectedRow();
		String id = (String) arrivalTable.getValueAt(row, 0);
		long ID = Long.parseLong(id);
		arrival = entruckReceive.chooseArrival(ID);
		doArrive.setEnabled(true);
		doArrive.setVisible(true);
		saveArrival.setEnabled(false);
		saveArrival.setVisible(false);
		setArrivalVO();
		}else {
			return ;
		}
	}

	private void searchListMouseClicked(MouseEvent e){
		String id = deliveryID.getText();
		long num = -1;
		try {
		num = Long.parseLong(id);
		} catch (NumberFormatException e2) {
			deliveryID.setText("单号格式错误");
			return;
		}
		getDeliveryList(num);
	}
	
	private void deleteRow(int row){
		String[][] info = arrivalList.info;
		String[][] newInfo = new String[info.length-1][ArrivalListVO.getColumnNum()];
		for(int i = 0 ,j = 0; i < info.length;i++,j++){
			if(i == row){
				continue;
			}else{
				newInfo[j] = info[i];
			}
		}
		info = newInfo;
	}
	
	private void doArriveMouseClicked(MouseEvent e) {
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = entruckReceive.doArrive();
			sendList = entruckReceive.createSendList(arrival.getId());
		} catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, "网络连接中断", "提示", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
			return;
		}
		if (result == ResultMessage.FAILED) {
			JOptionPane.showMessageDialog(null, "操作失败，请稍后再试", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			JOptionPane.showMessageDialog(null, "操作成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			
			int row = arrivalTable.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) arrivalTable.getModel();
			model.removeRow(row);
			arrivalTable.setModel(model);
			arrivalTable.updateUI();
			arrivalCounter--;
			//setArrivalList();
			setSendList();
		}
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

	private void saveArrivalMouseClicked(MouseEvent e) {
		ResultMessage result = entruckReceive.saveArrival(arrival);
		if(result == ResultMessage.SUCCESS){
			label6.setText("操作成功");
			resultDialog.repaint();
			resultDialog.setVisible(true);
		}
	}

	private void arrivalTableMouseClicked(MouseEvent e) {
		selectArrival.setEnabled(true);
	}



	private void cancelSureButtonMouseClicked(MouseEvent e) {
		cancelDialog.setVisible(false);
		remove(arrivalVO);
		if (entruckVO != null)
			remove(entruckVO);
		add(startPane, BorderLayout.CENTER);

		startPane.validate();
		startPane.updateUI();
		startPane.setVisible(true);
		
	}

	private void notCancelButtonMouseReleased(MouseEvent e) {
		cancelDialog.setVisible(false);
	}

	private void deliveryIDMouseClicked(MouseEvent e) {
		deliveryID.setText("");
		searchList.setEnabled(true);
	}

	private void modifyStatusMouseReleased(MouseEvent e) {//修改订单状态
		if (modifyStatus.isEnabled()) {
			int index = status.getSelectedIndex();
			if(index != -1){
			String s = (String) status.getItemAt(index);
			int[] rows = arrivalVOTable.getSelectedRows();
			if (rows == null) {
				return;
			}
			String[][] info = arrival.getOrderAndStatus();
			for(int i = 0;i < rows.length;i++){
				info[rows[i]][1] = s;
			}
			DefaultTableModel model = (DefaultTableModel) arrivalVOTable.getModel();
			model.setDataVector(info, arrival.getHeader());
			arrivalVOTable.updateUI();
			arrivalVOTable.repaint();
			}
		}
	}

	private void arrivalVOTableMouseClicked(MouseEvent e) {
		status.setEnabled(true);
		modifyStatus.setEnabled(true);
	}

	private void refreshButtonMouseReleased(MouseEvent e) {
		if(arrivalCounter <= 0){
		getArrivalList();
		setArrivalList();
		}
	}
	
	private void getSendList(){
		String sender = (String) senderBox.getSelectedItem();
		sendList.senderName = sender;
	}

	private void saveSendListButtonMouseReleased(MouseEvent e) {
		getSendList();
		ResultMessage result = entruckReceive.saveSendList(sendList);
		if(result == ResultMessage.SUCCESS){
			JOptionPane.showMessageDialog(null, "保存成功","提示" ,JOptionPane.INFORMATION_MESSAGE );
			setArrivalList();
		}else{
			JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
//==============================监听=================================

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
        startPane = new JTabbedPane();
        arrivalListPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        arrivalTable = new JTable();
        selectArrival = new JButton();
        refreshButton = new JButton();
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
        label15 = new JLabel();
        entruckVO = new JTabbedPane();
        DeliveryListPanel = new JPanel();
        scrollPane3 = new JScrollPane();
        deliveryTable = new JTable();
        cancelLoad = new JButton();
        createArrival = new JButton();
        label10 = new JLabel();
        listType = new JTextField();
        label11 = new JLabel();
        listID = new JTextField();
        label12 = new JLabel();
        fromName = new JTextField();
        label13 = new JLabel();
        destName = new JTextField();
        label14 = new JLabel();
        date = new JTextField();
        vehicleLabel = new JLabel();
        vehicleID = new JTextField();
        staffLabel = new JLabel();
        staff = new JTextField();
        label18 = new JLabel();
        fee = new JTextField();
        label19 = new JLabel();
        transferTypeLabel = new JLabel();
        transferType = new JTextField();
        resultDialog = new JDialog();
        panel2 = new JPanel();
        label6 = new JLabel();
        resultSureButton = new JButton();
        errorDialog = new JDialog();
        panel4 = new JPanel();
        label7 = new JLabel();
        errorSure = new JButton();
        cancelDialog = new JDialog();
        panel = new JPanel();
        label8 = new JLabel();
        label9 = new JLabel();
        cancelSureButton = new JButton();
        notCancelButton = new JButton();
        sendListPane = new JTabbedPane();
        panel3 = new JPanel();
        scrollPane4 = new JScrollPane();
        sendTable = new JTable();
        label16 = new JLabel();
        senderBox = new JComboBox();
        label17 = new JLabel();
        sendData = new JTextField();
        saveSendListButton = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== startPane ========
        {
            startPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== arrivalListPanel ========
            {

                //======== scrollPane2 ========
                {

                    //---- arrivalTable ----
                    arrivalTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    arrivalTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            arrivalTableMouseClicked(e);
                        }
                    });
                    scrollPane2.setViewportView(arrivalTable);
                }

                //---- selectArrival ----
                selectArrival.setText("\u67e5\u770b");
                selectArrival.setIcon(new ImageIcon(getClass().getResource("/icons/see_24x24.png")));
                selectArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                selectArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectArrivalMouseClicked(e);
                    }
                });

                //---- refreshButton ----
                refreshButton.setText("\u5237\u65b0");
                refreshButton.setIcon(new ImageIcon(getClass().getResource("/icons/refresh.png")));
                refreshButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                refreshButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        refreshButtonMouseReleased(e);
                    }
                });

                GroupLayout arrivalListPanelLayout = new GroupLayout(arrivalListPanel);
                arrivalListPanel.setLayout(arrivalListPanelLayout);
                arrivalListPanelLayout.setHorizontalGroup(
                    arrivalListPanelLayout.createParallelGroup()
                        .addGroup(arrivalListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(selectArrival, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(657, Short.MAX_VALUE))
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                );
                arrivalListPanelLayout.setVerticalGroup(
                    arrivalListPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, arrivalListPanelLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(arrivalListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(selectArrival, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                );
            }
            startPane.addTab("\u5df2\u5ba1\u6279\u5230\u8fbe\u5355", arrivalListPanel);

            //======== searchListPanel ========
            {

                //---- label5 ----
                label5.setText("\u8bf7\u8f93\u5165\u8fd0\u8f93\u5355\u53f7");
                label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- deliveryID ----
                deliveryID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                deliveryID.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        deliveryIDMouseClicked(e);
                    }
                });

                //---- entruck ----
                entruck.setText("\u88c5\u8f66\u5355");
                entruck.setSelected(true);
                entruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                entruck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        entruckMouseClicked(e);
                        entruckMouseClicked(e);
                    }
                });

                //---- transfer ----
                transfer.setText("\u4e2d\u8f6c\u5355");
                transfer.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                transfer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        transferMouseClicked(e);
                    }
                });

                //---- searchList ----
                searchList.setText("\u67e5\u627e");
                searchList.setIcon(new ImageIcon(getClass().getResource("/icons/search_24x24.png")));
                searchList.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
                            .addGap(236, 236, 236)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(searchListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(searchListPanelLayout.createSequentialGroup()
                                    .addComponent(entruck)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(transfer))
                                .addComponent(deliveryID, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(searchList)
                            .addContainerGap(300, Short.MAX_VALUE))
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
                            .addContainerGap(193, Short.MAX_VALUE))
                );
            }
            startPane.addTab("\u641c\u7d22\u8fd0\u8f93\u5355", searchListPanel);
        }
        add(startPane, BorderLayout.CENTER);

        //======== arrivalVO ========
        {

            //======== panel1 ========
            {

                //======== scrollPane1 ========
                {

                    //---- arrivalVOTable ----
                    arrivalVOTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    arrivalVOTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            arrivalVOTableMouseClicked(e);
                        }
                    });
                    scrollPane1.setViewportView(arrivalVOTable);
                }

                //---- label1 ----
                label1.setText("\u8fd0\u8f6c\u5355\u7f16\u53f7");
                label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- transferID ----
                transferID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- from ----
                from.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label3 ----
                label3.setText("\u65e5\u671f");
                label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- arrivalDate ----
                arrivalDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- status ----
                status.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- modifyStatus ----
                modifyStatus.setText("\u4fee\u6539\u72b6\u6001");
                modifyStatus.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                modifyStatus.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        modifyStatusMouseReleased(e);
                    }
                });

                //---- cancelArrival ----
                cancelArrival.setText("\u53d6\u6d88");
                cancelArrival.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
                cancelArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelArrivalMouseClicked(e);
                    }
                });

                //---- doArrive ----
                doArrive.setText("\u786e\u8ba4");
                doArrive.setIcon(new ImageIcon(getClass().getResource("/icons/sign_24x24.png")));
                doArrive.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                doArrive.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        doArriveMouseClicked(e);
                    }
                });

                //---- label4 ----
                label4.setText("\u51fa\u53d1\u5730");
                label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- saveArrival ----
                saveArrival.setText("\u4fdd\u5b58");
                saveArrival.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
                saveArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                saveArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        saveArrivalMouseClicked(e);
                    }
                });

                //---- label15 ----
                label15.setText("\u8ba2\u5355\u72b6\u6001");
                label15.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 783, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(saveArrival, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                        .addComponent(doArrive, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                        .addComponent(cancelArrival, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(transferID, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addComponent(from))
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGap(84, 84, 84)
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGap(322, 322, 322)
                                            .addComponent(label15)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(status, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(modifyStatus, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap())
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(transferID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addComponent(arrivalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label15, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(status)
                                .addComponent(modifyStatus))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(saveArrival)
                                    .addGap(2, 2, 2)
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(doArrive)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelArrival))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)))
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

                    //---- deliveryTable ----
                    deliveryTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPane3.setViewportView(deliveryTable);
                }

                //---- cancelLoad ----
                cancelLoad.setText("\u53d6\u6d88");
                cancelLoad.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24x24.png")));
                cancelLoad.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelLoad.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelLoadMouseClicked(e);
                    }
                });

                //---- createArrival ----
                createArrival.setText("\u5230\u8fbe");
                createArrival.setIcon(new ImageIcon(getClass().getResource("/icons/sign_24x24.png")));
                createArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                createArrival.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createArrivalMouseClicked(e);
                    }
                });

                //---- label10 ----
                label10.setText("\u5355\u636e\u7c7b\u578b");
                label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- listType ----
                listType.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label11 ----
                label11.setText("\u5355\u53f7");
                label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- listID ----
                listID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label12 ----
                label12.setText("\u51fa\u53d1\u5730");
                label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- fromName ----
                fromName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label13 ----
                label13.setText("\u76ee\u7684\u5730");
                label13.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- destName ----
                destName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label14 ----
                label14.setText("\u88c5\u8fd0\u65e5\u671f");
                label14.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- date ----
                date.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- vehicleLabel ----
                vehicleLabel.setText("\u8f66\u8f86\u7f16\u53f7");
                vehicleLabel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- vehicleID ----
                vehicleID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- staff ----
                staff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label18 ----
                label18.setText("\u8fd0\u8d39");
                label18.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- fee ----
                fee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label19 ----
                label19.setText("\u76d1\u88c5\u5458");
                label19.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- transferTypeLabel ----
                transferTypeLabel.setText("\u8fd0\u8f93\u7c7b\u578b");
                transferTypeLabel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- transferType ----
                transferType.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
                DeliveryListPanel.setLayout(DeliveryListPanelLayout);
                DeliveryListPanelLayout.setHorizontalGroup(
                    DeliveryListPanelLayout.createParallelGroup()
                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                                .addComponent(label10)
                                                .addComponent(label11))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label12)
                                            .addGap(16, 16, 16)))
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(fromName, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                        .addComponent(listType, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                        .addComponent(listID, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                    .addGap(47, 47, 47)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vehicleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label19)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(staffLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(date, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addComponent(vehicleID, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addComponent(staff, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addComponent(transferTypeLabel)
                                        .addComponent(label18)
                                        .addComponent(label13))
                                    .addGap(18, 18, 18)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(transferType, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                        .addComponent(fee, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                        .addComponent(destName, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                                    .addGap(0, 431, Short.MAX_VALUE))
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 790, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addComponent(createArrival, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(cancelLoad, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
                            .addContainerGap())
                );
                DeliveryListPanelLayout.setVerticalGroup(
                    DeliveryListPanelLayout.createParallelGroup()
                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label10)
                                .addComponent(listType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label14)
                                .addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(fee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label18))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label11)
                                .addComponent(listID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(vehicleLabel)
                                .addComponent(vehicleID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(transferType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(transferTypeLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label12)
                                .addComponent(fromName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffLabel)
                                .addComponent(staff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label19)
                                .addComponent(label13)
                                .addComponent(destName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addComponent(createArrival, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelLoad)
                                    .addContainerGap(184, Short.MAX_VALUE))
                                .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
                );
            }
            entruckVO.addTab("\u8fd0\u8f6c\u5355", DeliveryListPanel);
        }

        //======== resultDialog ========
        {
            resultDialog.setTitle("\u64cd\u4f5c\u7ed3\u679c");
            Container resultDialogContentPane = resultDialog.getContentPane();
            resultDialogContentPane.setLayout(new BorderLayout());

            //======== panel2 ========
            {

                //---- label6 ----
                label6.setText("\u64cd\u4f5c\u6210\u529f");
                label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- resultSureButton ----
                resultSureButton.setText("\u786e\u5b9a");
                resultSureButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                resultSureButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        resultSureButtonMouseClicked(e);
                    }
                });

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGap(118, 118, 118)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addComponent(resultSureButton))
                            .addContainerGap(123, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(45, Short.MAX_VALUE)
                            .addComponent(label6)
                            .addGap(25, 25, 25)
                            .addComponent(resultSureButton)
                            .addGap(43, 43, 43))
                );
            }
            resultDialogContentPane.add(panel2, BorderLayout.CENTER);
            resultDialog.setSize(320, 200);
            resultDialog.setLocationRelativeTo(null);
        }

        //======== errorDialog ========
        {
            errorDialog.setTitle("\u5f02\u5e38");
            Container errorDialogContentPane = errorDialog.getContentPane();
            errorDialogContentPane.setLayout(new BorderLayout());

            //======== panel4 ========
            {

                //---- label7 ----
                label7.setText("\u7f51\u7edc\u8fde\u63a5\u4e2d\u65ad");
                label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- errorSure ----
                errorSure.setText("\u786e\u5b9a");
                errorSure.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                errorSure.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                        errorSureMouseClicked(e);
                    }
                });

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(errorSure)))
                            .addContainerGap(94, Short.MAX_VALUE))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                            .addContainerGap(50, Short.MAX_VALUE)
                            .addComponent(label7)
                            .addGap(20, 20, 20)
                            .addComponent(errorSure)
                            .addGap(43, 43, 43))
                );
            }
            errorDialogContentPane.add(panel4, BorderLayout.CENTER);
            errorDialog.pack();
            errorDialog.setLocationRelativeTo(errorDialog.getOwner());
        }

        //======== cancelDialog ========
        {
            cancelDialog.setTitle("\u63d0\u793a");
            Container cancelDialogContentPane = cancelDialog.getContentPane();
            cancelDialogContentPane.setLayout(new BorderLayout());

            //======== panel ========
            {

                //---- label8 ----
                label8.setText("\u53d6\u6d88\u540e\u672c\u6b21\u7f16\u8f91\u5c06\u4e0d\u80fd\u751f\u6548");
                label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label9 ----
                label9.setText("\u662f\u5426\u786e\u8ba4\u53d6\u6d88\uff1f");
                label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- cancelSureButton ----
                cancelSureButton.setText("\u662f");
                cancelSureButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelSureButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelSureButtonMouseClicked(e);
                    }
                });

                //---- notCancelButton ----
                notCancelButton.setText("\u5426");
                notCancelButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                notCancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        notCancelButtonMouseReleased(e);
                    }
                });

                GroupLayout panelLayout = new GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                    panelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                            .addContainerGap(51, Short.MAX_VALUE)
                            .addComponent(label8)
                            .addGap(40, 40, 40))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label9)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(cancelSureButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(notCancelButton)))
                            .addContainerGap(77, Short.MAX_VALUE))
                );
                panelLayout.setVerticalGroup(
                    panelLayout.createParallelGroup()
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label9)
                            .addGap(18, 18, 18)
                            .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelSureButton)
                                .addComponent(notCancelButton))
                            .addContainerGap(32, Short.MAX_VALUE))
                );
            }
            cancelDialogContentPane.add(panel, BorderLayout.SOUTH);
            cancelDialog.pack();
            cancelDialog.setLocationRelativeTo(cancelDialog.getOwner());
        }

        //======== sendListPane ========
        {
            sendListPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== panel3 ========
            {

                //======== scrollPane4 ========
                {

                    //---- sendTable ----
                    sendTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPane4.setViewportView(sendTable);
                }

                //---- label16 ----
                label16.setText("\u5feb\u9012\u5458");
                label16.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- senderBox ----
                senderBox.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label17 ----
                label17.setText("\u65e5\u671f");
                label17.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- sendData ----
                sendData.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- saveSendListButton ----
                saveSendListButton.setText("\u4fdd\u5b58");
                saveSendListButton.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                saveSendListButton.setIcon(new ImageIcon(getClass().getResource("/icons/sign_16x16.png")));
                saveSendListButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        saveSendListButtonMouseReleased(e);
                    }
                });

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label16)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(senderBox, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label17)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendData, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveSendListButton)
                            .addContainerGap(535, Short.MAX_VALUE))
                        .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(senderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label16, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label17, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(sendData)
                                    .addComponent(saveSendListButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                );
            }
            sendListPane.addTab("\u6d3e\u4ef6\u5355", panel3);
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
    private JButton refreshButton;
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
    private JLabel label15;
    private JTabbedPane entruckVO;
    private JPanel DeliveryListPanel;
    private JScrollPane scrollPane3;
    private JTable deliveryTable;
    private JButton cancelLoad;
    private JButton createArrival;
    private JLabel label10;
    private JTextField listType;
    private JLabel label11;
    private JTextField listID;
    private JLabel label12;
    private JTextField fromName;
    private JLabel label13;
    private JTextField destName;
    private JLabel label14;
    private JTextField date;
    private JLabel vehicleLabel;
    private JTextField vehicleID;
    private JLabel staffLabel;
    private JTextField staff;
    private JLabel label18;
    private JTextField fee;
    private JLabel label19;
    private JLabel transferTypeLabel;
    private JTextField transferType;
    private JDialog resultDialog;
    private JPanel panel2;
    private JLabel label6;
    private JButton resultSureButton;
    private JDialog errorDialog;
    private JPanel panel4;
    private JLabel label7;
    private JButton errorSure;
    private JDialog cancelDialog;
    private JPanel panel;
    private JLabel label8;
    private JLabel label9;
    private JButton cancelSureButton;
    private JButton notCancelButton;
    private JTabbedPane sendListPane;
    private JPanel panel3;
    private JScrollPane scrollPane4;
    private JTable sendTable;
    private JLabel label16;
    private JComboBox senderBox;
    private JLabel label17;
    private JTextField sendData;
    private JButton saveSendListButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
