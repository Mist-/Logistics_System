/*
 * Created by JFormDesigner on Mon Nov 30 23:38:21 CST 2015
 */

package presentation.transfer.hall;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Vector;
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
	int entruckListCounter = 0;
	
	
	public boolean isClear(){
		if(entruckListCounter>0){
			return false;
		}else{
			return true;
		}
	}

	public LoadAndSortPanel(LoadAndSortService loadAndSort)
			throws RemoteException {
		this.loadAndSort = loadAndSort;
		initComponents();
		selectEntruck.setEnabled(false);
		removeOrder.setEnabled(false);
		createEntruck.setEnabled(false);
		setDestination();
		setEntruckList();
		this.setVisible(true);
	}

	// =========================���ý���====================================================
	// ����Ŀ�ĵ�comboBox
	private void setDestination() throws RemoteException {

		String[] des = null;
		des = loadAndSort.getDestination();
		if (des != null) {// Ŀ�ĵ���Ϣ��ȡ�ɹ�ʱ��ʾ�����ɹ�����ʾ
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(des);
			comboBox1.setModel(model);
			comboBox1.validate();
			comboBox1.updateUI();
		} else {
			return;
		}
	}

	// ����װ����
	private void setEntruck() {
		if (entruck != null) {
			// ����װ���� �����б�
			DefaultTableModel model = new DefaultTableModel(entruck.info,
					entruck.header);
			entruckDate.setText(entruck.loadingDate);
			entruckTable.setModel(model);
			listID.setText("���������");
			hallID.setText(entruck.fromID);
			hallName.setText(entruck.fromName);
			destID.setText(entruck.destID + "");
			destName.setText(entruck.destName);
			truckID.setText(entruck.vehicleID);
			staffName.setText(entruck.monitorName);
			driverName.setText(entruck.escortName);
			entruckTable.validate();
			entruckTable.updateUI();
			remove(loadAndSortPane);
			add(entruckVO);

			entruckVO.validate();
			entruckVO.updateUI();
			entruckVO.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "δ���ҵ�װ������Ϣ", "�쳣",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// ����װ�������ɱ༭
	private void setDisabled() {
		entruckTable.setEnabled(false);
		listID.setEnabled(false);
		hallID.setEnabled(false);
		hallName.setEnabled(false);
		destID.setEnabled(false);
		destName.setEnabled(false);
		truckID.setEnabled(false);
		staffName.setEnabled(false);
		driverName.setEnabled(false);
		doEntruck.setVisible(true);
		entruckDate.setEnabled(false);
	}

	// ������ʾװ����
	private void setEntruckList() {
		try {
			if (briefEntruckList == null)
				briefEntruckList = loadAndSort.getEntruckList();
		} catch (RemoteException e) {
			e.printStackTrace();
			errorDialog.setVisible(true);
		}
		if (briefEntruckList != null) {// Ϊ�����ȡʧ��
			DefaultTableModel model = new DefaultTableModel(
					briefEntruckList.info, briefEntruckList.header);
			entruckListCounter = briefEntruckList.info.length;
			entruckListTable.setModel(model);
			entruckListTable.validate();
			entruckListTable.updateUI();
			entruckListTable.setVisible(true);
		}else{
			entruckListTable.setEnabled(false);
		}
	}

	// =========================����==================================================
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
		System.out.println(des);
		briefOrder = loadAndSort.chooseDestination(des);
		DefaultTableModel model = new DefaultTableModel();
		if(briefOrder != null){
		model.setDataVector(briefOrder.info, briefOrder.header);
		orderTable.setModel(model);
		orderTable.setEnabled(true);
		orderTable.updateUI();
		orderTable.setVisible(true);
		removeOrder.setEnabled(true);
		createEntruck.setEnabled(true);
		}else{
			orderTable.setEnabled(false);
		}
	}

	private void createEntruckMouseClicked(MouseEvent e) {
		// vector ת string[][] ��ʱ��ô��
		if(orderTable.isEnabled()){
		Vector<Vector<String>> v = briefOrder.info;
		if(v.size() == 0){//�����б���Ϊ0ʱ��ֻ��ʾ
			JOptionPane.showMessageDialog(null, "û�п�װ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String[][] info = new String[v.size()][BriefOrderVO.getColumnNum()];
		for (int i = 0; i < v.size(); i++) {
			Vector<String> in = v.get(i);
			String[] ins = new String[in.size()];
			ins[0] = in.get(0);
			ins[1] = in.get(1);
			info[i] = ins;
		}
		try {
			entruck = loadAndSort.createEntruckList(info);
			if (entruck == null) {
				JOptionPane.showMessageDialog(null, "���鱾Ӫҵ���Ƿ���˾���ͳ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "���������ж�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
		doEntruck.setVisible(false);
		doEntruck.setEnabled(false);
		saveEntruck.setVisible(true);
		saveEntruck.setEnabled(true);
		setEntruck();
		setDisabled();
		}
	}

	private void saveEntruckMouseClicked(MouseEvent e) {
		try {
			ResultMessage result = loadAndSort.saveEntruckList(entruck);
			if (result == ResultMessage.SUCCESS) {
				JOptionPane.showMessageDialog(null, "����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = new DefaultTableModel();
				orderTable.setModel(model);
				remove(entruckVO);
				add(loadAndSortPane, BorderLayout.CENTER);
				loadAndSortPane.updateUI();
				loadAndSortPane.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
			errorDialog.setVisible(true);
		}
	}

	private void selectEntruckMouseClicked(MouseEvent e) {
		if (entruckListTable.isEnabled()) {
			int row = entruckListTable.getSelectedRow();
			String id = (String) entruckListTable.getValueAt(row, 0);
			entruck = loadAndSort.chooseEntruckList(Long.parseLong(id));
			doEntruck.setVisible(true);
			doEntruck.setEnabled(true);
			saveEntruck.setVisible(false);
			saveEntruck.setEnabled(false);
			setEntruck();
			setDisabled();
			entruckDate.setEnabled(false);
		}
	}

	private void doEntruckMouseClicked(MouseEvent e) {
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = loadAndSort.doEntruck();
		
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		if (result == ResultMessage.SUCCESS) {
			JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			int row = entruckListTable.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) entruckListTable.getModel();
			model.removeRow(row);
			entruckListCounter--;
			entruckListTable.updateUI();
			entruckListTable.repaint();
			
			remove(entruckVO);
			add(loadAndSortPane,BorderLayout.CENTER);
			loadAndSortPane.updateUI();
			loadAndSortPane.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "����ʧ��,���Ժ�����", "��ʾ",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resultSureMouseClicked(MouseEvent e) {
		setEntruckList();
		resultDialog.setVisible(false);
	}

	private void entruckListTableMouseReleased(MouseEvent e) {
		selectEntruck.setEnabled(true);
	}

	private void orderTableMouseReleased(MouseEvent e) {
		removeOrder.setEnabled(true);
	}

	// ɾ��ѡ�ж�����
	private void removeOrderMouseReleased(MouseEvent e) {
		if(orderTable.isEnabled()){//�����б���Чʱ����Ч
		int[] rows = orderTable.getSelectedRows();
		if (rows.length == 0) {
			JOptionPane.showMessageDialog(null, "δѡ�ж���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Vector<Vector<String>> v = briefOrder.info;
		for (int i = (rows.length-1); i>= 0; i--) {
			v.remove(rows[i]);
		}
		DefaultTableModel model = new DefaultTableModel(briefOrder.info,
				briefOrder.header);
		orderTable.setModel(model);
		orderTable.updateUI();
		orderTable.repaint();
		}
	}

	private void refreshButtonMouseReleased(MouseEvent e) {
		if(entruckListCounter <= 0){
			briefEntruckList = null;
			setEntruckList();
		}
	}



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
        loadAndSortPane = new JTabbedPane();
        entruckListPanel = new JPanel();
        selectEntruck = new JButton();
        scrollPane3 = new JScrollPane();
        entruckListTable = new JTable();
        refreshButton = new JButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        orderTable = new JTable();
        label1 = new JLabel();
        comboBox1 = new JComboBox();
        sortButton = new JButton();
        createEntruck = new JButton();
        removeOrder = new JButton();
        entruckVO = new JTabbedPane();
        DeliveryListPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        entruckTable = new JTable();
        cancelLoad = new JButton();
        doEntruck = new JButton();
        label6 = new JLabel();
        label5 = new JLabel();
        listID = new JTextField();
        hallID = new JTextField();
        label9 = new JLabel();
        hallName = new JTextField();
        destID = new JTextField();
        label8 = new JLabel();
        label7 = new JLabel();
        destName = new JTextField();
        entruckDate = new JTextField();
        label4 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        truckID = new JTextField();
        staffName = new JTextField();
        driverName = new JTextField();
        saveEntruck = new JButton();
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
            loadAndSortPane.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== entruckListPanel ========
            {

                //---- selectEntruck ----
                selectEntruck.setText("\u67e5\u770b");
                selectEntruck.setIcon(new ImageIcon(getClass().getResource("/icons/see_24x24.png")));
                selectEntruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                selectEntruck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectEntruckMouseClicked(e);
                    }
                });

                //======== scrollPane3 ========
                {

                    //---- entruckListTable ----
                    entruckListTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                    entruckListTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    entruckListTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            entruckListTableMouseReleased(e);
                        }
                    });
                    scrollPane3.setViewportView(entruckListTable);
                }

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

                GroupLayout entruckListPanelLayout = new GroupLayout(entruckListPanel);
                entruckListPanel.setLayout(entruckListPanelLayout);
                entruckListPanelLayout.setHorizontalGroup(
                    entruckListPanelLayout.createParallelGroup()
                        .addGroup(entruckListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(selectEntruck, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(691, Short.MAX_VALUE))
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                );
                entruckListPanelLayout.setVerticalGroup(
                    entruckListPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, entruckListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(entruckListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(selectEntruck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                );
            }
            loadAndSortPane.addTab("\u5df2\u5ba1\u6279\u88c5\u8f66\u5355", entruckListPanel);

            //======== panel1 ========
            {

                //======== scrollPane1 ========
                {

                    //---- orderTable ----
                    orderTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            orderTableMouseReleased(e);
                        }
                    });
                    scrollPane1.setViewportView(orderTable);
                }

                //---- label1 ----
                label1.setText("\u76ee\u7684\u5730");
                label1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

                //---- sortButton ----
                sortButton.setText("\u5206\u62e3");
                sortButton.setIcon(new ImageIcon(getClass().getResource("/icons/excel_24x24.png")));
                sortButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        sortButtonMouseClicked(e);
                    }
                });

                //---- createEntruck ----
                createEntruck.setText("\u88c5\u8f66");
                createEntruck.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
                createEntruck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createEntruckMouseClicked(e);
                    }
                });

                //---- removeOrder ----
                removeOrder.setText("\u79fb\u9664");
                removeOrder.setIcon(new ImageIcon(getClass().getResource("/icons/recover_24x24.png")));
                removeOrder.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        removeOrderMouseReleased(e);
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
                            .addComponent(sortButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(removeOrder)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                            .addComponent(createEntruck, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(comboBox1, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                    .addComponent(sortButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(removeOrder, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(createEntruck, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                );
            }
            loadAndSortPane.addTab("\u5206\u62e3\u88c5\u8f66", panel1);
        }
        add(loadAndSortPane, BorderLayout.CENTER);

        //======== entruckVO ========
        {
            entruckVO.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== DeliveryListPanel ========
            {

                //======== scrollPane2 ========
                {

                    //---- entruckTable ----
                    entruckTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                    entruckTable.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPane2.setViewportView(entruckTable);
                }

                //---- cancelLoad ----
                cancelLoad.setText("\u53d6\u6d88");
                cancelLoad.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_16x16.png")));
                cancelLoad.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                cancelLoad.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelLoadMouseClicked(e);
                    }
                });

                //---- doEntruck ----
                doEntruck.setText("\u88c5\u8f66");
                doEntruck.setIcon(new ImageIcon(getClass().getResource("/icons/logout_24x24.png")));
                doEntruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                doEntruck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        doEntruckMouseClicked(e);
                    }
                });

                //---- label6 ----
                label6.setText("\u6c7d\u8fd0\u7f16\u53f7");
                label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label5 ----
                label5.setText("\u8425\u4e1a\u5385\u7f16\u53f7");
                label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- listID ----
                listID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- hallID ----
                hallID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label9 ----
                label9.setText("\u8425\u4e1a\u5385\u540d");
                label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- hallName ----
                hallName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- destID ----
                destID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label8 ----
                label8.setText("\u76ee\u7684\u5730\u7f16\u53f7");
                label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label7 ----
                label7.setText("\u76ee\u7684\u5730");
                label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- destName ----
                destName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- entruckDate ----
                entruckDate.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label4 ----
                label4.setText("\u88c5\u8f66\u65e5\u671f");
                label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label10 ----
                label10.setText("\u8f66\u8f86\u4ee3\u53f7");
                label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label11 ----
                label11.setText("\u76d1\u88c5\u5458");
                label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- label12 ----
                label12.setText("\u62bc\u8fd0\u5458");
                label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- truckID ----
                truckID.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- staffName ----
                staffName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- driverName ----
                driverName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- saveEntruck ----
                saveEntruck.setText("\u4fdd\u5b58");
                saveEntruck.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
                saveEntruck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                saveEntruck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        saveEntruckMouseClicked(e);
                    }
                });

                GroupLayout DeliveryListPanelLayout = new GroupLayout(DeliveryListPanel);
                DeliveryListPanel.setLayout(DeliveryListPanelLayout);
                DeliveryListPanelLayout.setHorizontalGroup(
                    DeliveryListPanelLayout.createParallelGroup()
                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addComponent(doEntruck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saveEntruck, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelLoad, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(listID, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label5))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                                .addComponent(hallName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hallID, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addComponent(destID)
                                        .addComponent(destName)
                                        .addComponent(entruckDate))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label10)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(truckID, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label11, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(staffName, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(driverName, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(349, 349, 349))))
                );
                DeliveryListPanelLayout.setVerticalGroup(
                    DeliveryListPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, DeliveryListPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                                .addComponent(listID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hallID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(destID))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                                .addComponent(destName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                        .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label9)
                                            .addComponent(hallName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label4)
                                            .addComponent(entruckDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(truckID)
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(staffName)
                                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(DeliveryListPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(driverName)
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(DeliveryListPanelLayout.createParallelGroup()
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addComponent(doEntruck)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveEntruck)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancelLoad))
                                .addGroup(DeliveryListPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(2, 2, 2))
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
                label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- errorSure ----
                errorSure.setText("\u786e\u5b9a");
                errorSure.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
                            .addContainerGap(48, Short.MAX_VALUE))
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
                label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- resultSure ----
                resultSure.setText("\u786e\u5b9a");
                resultSure.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
                            .addContainerGap(97, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(label3)
                            .addGap(26, 26, 26)
                            .addComponent(resultSure)
                            .addContainerGap(28, Short.MAX_VALUE))
                );
            }
            resultDialogContentPane.add(panel3, BorderLayout.CENTER);
            resultDialog.pack();
            resultDialog.setLocationRelativeTo(resultDialog.getOwner());
        }
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
    private JTabbedPane loadAndSortPane;
    private JPanel entruckListPanel;
    private JButton selectEntruck;
    private JScrollPane scrollPane3;
    private JTable entruckListTable;
    private JButton refreshButton;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable orderTable;
    private JLabel label1;
    private JComboBox comboBox1;
    private JButton sortButton;
    private JButton createEntruck;
    private JButton removeOrder;
    private JTabbedPane entruckVO;
    private JPanel DeliveryListPanel;
    private JScrollPane scrollPane2;
    private JTable entruckTable;
    private JButton cancelLoad;
    private JButton doEntruck;
    private JLabel label6;
    private JLabel label5;
    private JTextField listID;
    private JTextField hallID;
    private JLabel label9;
    private JTextField hallName;
    private JTextField destID;
    private JLabel label8;
    private JLabel label7;
    private JTextField destName;
    private JTextField entruckDate;
    private JLabel label4;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JTextField truckID;
    private JTextField staffName;
    private JTextField driverName;
    private JButton saveEntruck;
    private JDialog errorDialog;
    private JPanel panel2;
    private JLabel label2;
    private JButton errorSure;
    private JDialog resultDialog;
    private JPanel panel3;
    private JLabel label3;
    private JButton resultSure;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
