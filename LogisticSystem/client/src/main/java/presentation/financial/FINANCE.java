package presentation.financial;

/*
 * Created by JFormDesigner on Sat Oct 31 17:11:41 CST 2015
 */

import businesslogic.impl.company.CompanyBLController;
import businesslogic.impl.financialbl.AccountManage;
import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.service.Financial.FinancialBLService;
import data.vo.AccountVO;
import data.vo.CostBenefitVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import presentation.company.DialogAddSalary;

/**
 * @author wh
 */
public class FINANCE extends JFrame {

    FinancialBLService financialBL = new FinancialBLController();
    CompanyBLController companyBLcontroller = new CompanyBLController();
    
    public FINANCE() {
        initComponents();
        
        this.setVisible(true);
        lbAll.setVisible(false);
        lbTotal.setVisible(false);
        
    }

    private void tgZJGLMouseClicked(MouseEvent e) {
        tgZJGL.setSelected(true);
        tgQCJZ.setSelected(false);
        tgTJBB.setSelected(false);
        tgZHGL.setSelected(false);
        panelMain.remove(pnQCJZ);
        panelMain.remove(pnTJBB);
        panelMain.remove(pnZHGL);
        panelMain.remove(pnZJGL);
        panelMain.add(pnZJGL, BorderLayout.CENTER);

//        ArrayList<PaymentVO> payments = financialBL.searchAllPayment();
//        Vector data = ((DefaultTableModel)tablePayment.getModel()).getDataVector();
//        data.clear();
//        for (PaymentVO paymentVO: payments) {
//            Vector<Object> row = new Vector<>();
//            row.add(paymentVO.getDate());
//            row.add(paymentVO.getMoney());
//            row.add(paymentVO.getName());
//            row.add(paymentVO.getAccount());
//            row.add(paymentVO.getInfo());
//            row.add(paymentVO.getExInfo());
//            data.add(row);
//        }
//        tablePayment.updateUI();
//        tablePayment.repaint();
//        
//        ArrayList<ReceiptVO> receipts = financialBL.searchAllReceipt();
//        data = ((DefaultTableModel)tableReceipt.getModel()).getDataVector();
//        data.clear();
//        for (ReceiptVO receiptVO: receipts) {
//            Vector<Object> row = new Vector<>();
//            row.add(receiptVO.getDate());
//            row.add(receiptVO.getInstitution());
//            row.add(receiptVO.getPeople());
//            row.add(receiptVO.getCourierName());
//            row.add(receiptVO.getMoney());
//            row.add(receiptVO.getAddress());
//            
//            data.add(row);
//        }
//        tableReceipt.updateUI();
//        tableReceipt.repaint();
        
        
        panelMain.updateUI();
        this.repaint();
    }

    private void tgZHGLMouseClicked(MouseEvent e) {
        tgZJGL.setSelected(false);
        tgQCJZ.setSelected(false);
        tgTJBB.setSelected(false);
        tgZHGL.setSelected(true);
        panelMain.remove(pnQCJZ);
        panelMain.remove(pnTJBB);
        panelMain.remove(pnZHGL);
        panelMain.remove(pnZJGL);
        panelMain.add(pnZHGL, BorderLayout.CENTER);
        
        
//        ArrayList<AccountVO> accounts = financialBL.searchAllAccounts();
//        Vector data = ((DefaultTableModel)tableAccounts.getModel()).getDataVector();
//        data.clear();
//        for (AccountVO accountVO: accounts) {
//            Vector<Object> row = new Vector<>();
//            row.add(accountVO.getName());
//            row.add(accountVO.getMoney());
//            data.add(row);
//        }
//        tableAccounts.updateUI();
//        tableAccounts.repaint();
        
        panelMain.updateUI();
        this.repaint();
    }

    private void tgTJBBMouseClicked(MouseEvent e) {
        tgZJGL.setSelected(false);
        tgQCJZ.setSelected(false);
        tgTJBB.setSelected(true);
        tgZHGL.setSelected(false);
        panelMain.remove(pnQCJZ);
        panelMain.remove(pnTJBB);
        panelMain.remove(pnZHGL);
        panelMain.remove(pnZJGL);
        panelMain.add(pnTJBB, BorderLayout.CENTER);
        
//        //成本收益表
//        CostBenefitVO costBenefitVO = companyBLcontroller.searchCostBenefitVO();
//		Vector data = ((DefaultTableModel)tableBenefit.getModel()).getDataVector();
//        Vector<Object> row = new Vector<>();
//            row.add(costBenefitVO.getAllIncome());
//            row.add(costBenefitVO.getAllPay());
//            row.add(costBenefitVO.getAllProfit());
//
//            tableBenefit.updateUI();
//            tableBenefit.repaint();
            
        panelMain.updateUI();
        this.repaint();
    }

    private void tgQCJZMouseClicked(MouseEvent e) {
        tgZJGL.setSelected(false);
        tgQCJZ.setSelected(true);
        tgTJBB.setSelected(false);
        tgZHGL.setSelected(false);
        panelMain.remove(pnQCJZ);
        panelMain.remove(pnTJBB);
        panelMain.remove(pnZHGL);
        panelMain.remove(pnZJGL);
        panelMain.add(pnQCJZ, BorderLayout.CENTER);
        panelMain.updateUI();
        this.repaint();
    }
    
    //菜单栏中的关于我们
    private void menuItem3MouseReleased(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "我们是黑化肥不会发灰队（加一只狗）！");
    	}
    
    //ZHGL 查找账户
    private void btSearchMouseReleased(MouseEvent e) {
        String name = textName.getText();
       
        ArrayList<AccountVO> accounts = financialBL.findAccount(name);
        Vector data = ((DefaultTableModel)tableAccounts.getModel()).getDataVector();
        data.clear();
        for (AccountVO accountVO: accounts) {
            Vector<Object> row = new Vector<>();
            row.add(accountVO.getName());
            row.add(accountVO.getMoney());
            data.add(row);
        }
        tableAccounts.updateUI();
        tableAccounts.repaint();
    }

	

    //ZHGL 新增账户
	private void btAddMouseReleased(MouseEvent e) {
		String name = textName.getText();
        double money = Double.valueOf(textName2.getText());
        AccountVO account = financialBL.addAccount(name, money);
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        Vector<Object> row = new Vector<>();
        row.add(account.getName());
        row.add(account.getMoney());
        data.add(row);
        tableAccounts.updateUI();
        tableAccounts.repaint();
	}

	 //ZHGL 修改账户
	private void btModifyMouseReleased(MouseEvent e) {
		//please add the change
		
		
		
		
		
		
	}
	
    //ZHGL 删除账户
	private void button11MouseReleased(MouseEvent e) {
		 Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
	     long accountNum = (long)((Vector<Object>)data.get(tableAccounts.getSelectedRow())).get(0);
	        
	        financialBL.deleteAccount(accountNum);
	        // TODO: 调用逻辑层接口删除数据

	        data.remove(tableAccounts.getSelectedRow());
	        tableAccounts.updateUI();
	        tableAccounts.repaint();
	}
	
	ArrayList<ReceiptVO> re;
	//ZJGL 按天对收款单进行查看
	private void btDateMouseReleased(MouseEvent e) {
		String date = textDate.getText();
		re =  financialBL.checkFromDate(date);
		Vector data = ((DefaultTableModel) tableReceipt.getModel()).getDataVector();
		data.clear();
		for(ReceiptVO receiptVO: re){
			 Vector<Object> row = new Vector<>();
			    row.add(receiptVO.getDate());
	            row.add(receiptVO.getInstitution());
	            row.add(receiptVO.getPeople());
	            row.add(receiptVO.getSender());	            
	            row.add(receiptVO.getMoney());
	            row.add(receiptVO.getAddress());
	            
	            data.add(row);
		}
		double num = financialBL.total(re);
		lbTotal = new JLabel(num + "元");
		lbAll.setVisible(true);
        lbTotal.setVisible(true);
		tableReceipt.updateUI();
		tableReceipt.repaint();
		}

	
	//ZJGL 按营业厅对收款单进行查看
	private void btAddressMouseReleased(MouseEvent e) {
		String address = textAddress.getText();
		
		re =  financialBL.checkFromAddress(address);
		Vector data = ((DefaultTableModel) tableReceipt.getModel()).getDataVector();
		data.clear();
		for(ReceiptVO receiptVO: re){
			 Vector<Object> row = new Vector<>();
			    row.add(receiptVO.getDate());
	            row.add(receiptVO.getInstitution());
	            row.add(receiptVO.getPeople());
	            row.add(receiptVO.getSender());
	            row.add(receiptVO.getMoney());
	            row.add(receiptVO.getAddress());
	            
	            data.add(row);
		}
		double num = financialBL.total(re);
		lbTotal = new JLabel(num + "元");
		lbAll.setVisible(true);
        lbTotal.setVisible(true);
		tableReceipt.updateUI();
		tableReceipt.repaint();
		}

	ArrayList<PaymentVO> pay;
	ArrayList<ReceiptVO> rec;
		//TJBB 对经营情况表的查看
		private void btSearch2MouseReleased(MouseEvent e) {
			String BeginYear = textBeginYear.getText();
			String BeginMonth = textBeginMonth.getText();
			String BeginDay = textBeginDay.getText();
			String EndYear = textEndYear.getText();
			String EndMonth = textEndMonth.getText();
			String EndDay = textEndDay.getText();
			pay = companyBLcontroller.searchPaymentVO(BeginYear, BeginMonth, BeginDay, EndYear, EndMonth, EndDay);
			rec = companyBLcontroller.searchReceiptVO(BeginYear, BeginMonth, BeginDay, EndYear, EndMonth, EndDay);
			Vector data = ((DefaultTableModel) tableRec.getModel()).getDataVector();
			data.clear();
			for(ReceiptVO receiptVO: rec){
				 Vector<Object> row = new Vector<>();
				    row.add(receiptVO.getDate());
		            row.add(receiptVO.getInstitution());
		            row.add(receiptVO.getPeople());
		            row.add(receiptVO.getSender());
		            row.add(receiptVO.getMoney());
		            row.add(receiptVO.getAddress());
		            
		            data.add(row);
			}
			
			tableRec.updateUI();
			tableRec.repaint();
			
	        Vector data1 = ((DefaultTableModel)tablePay.getModel()).getDataVector();
	        data1.clear();
	        for (PaymentVO paymentVO: pay) {
	            Vector<Object> row = new Vector<>();
	            row.add(paymentVO.getDate());
	            row.add(paymentVO.getMoney());
	            row.add(paymentVO.getName());
	            row.add(paymentVO.getAccount());
	            row.add(paymentVO.getInfo());
	            row.add(paymentVO.getExInfo());
	            data1.add(row);
	        }
	        tablePay.updateUI();
	        tablePay.repaint();
			
		}

		//TJBB 对成本收益表的查看
		private void tpCostMouseReleased(MouseEvent e) {
			// TODO add your code here

			CostBenefitVO costBenefitVO = companyBLcontroller.searchCostBenefitVO();

			Vector data = ((DefaultTableModel)tableBenefit.getModel()).getDataVector();
            Vector<Object> row = new Vector<>();
	            row.add(costBenefitVO.getAllIncome());
	            row.add(costBenefitVO.getAllPay());
	            row.add(costBenefitVO.getAllProfit());

	            tableBenefit.updateUI();
	            tableBenefit.repaint();
		}

		 //ZJGL 新建付款单
		private void button1MouseReleased(MouseEvent e) {
			PaymentAdd dialogAddSalary = new PaymentAdd();
			dialogAddSalary.setVisible(true);
			PaymentVO paymentVO = dialogAddSalary.payment;
			Vector data = ((DefaultTableModel)tablePayment.getModel()).getDataVector();
            Vector<Object> row = new Vector<>();
	            row.add(paymentVO.getDate());
	            row.add(paymentVO.getMoney());
	            row.add(paymentVO.getName());
	            row.add(paymentVO.getAccount());
	            row.add(paymentVO.getInfo());
	            row.add(paymentVO.getExInfo());
	            data.add(row);
	         
	        tablePayment.updateUI();
	        tablePayment.repaint();
		}

		//TJBB 导出经营情况表成Excel
		private void jbExcelMouseReleased(MouseEvent e) {
			financialBL.printPayment(pay);
			financialBL.printReceipt(rec);
		}

		//ZJGL 导出收款单表成Excel
		private void btRExcelMouseReleased(MouseEvent e) {
			financialBL.printReceipt(re);
		}

		//ZJGL 导出付款单表成Excel
		private void btPExcelMouseReleased(MouseEvent e) {
			Vector data = ((DefaultTableModel)tablePayment.getModel()).getDataVector();
			ArrayList<PaymentVO> paList = null;
			for(Object row: data){
			Vector vector = (Vector) row;
			PaymentVO pa = new PaymentVO();
			pa.setDate((String) vector.get(0));
			pa.setMoney((double) vector.get(1));
			pa.setName((String) vector.get(2));
			pa.setAccount((String) vector.get(3));
			pa.setInfo((String) vector.get(4));
			pa.setExInfo((String) vector.get(5));
			paList.add(pa);
			}
			financialBL.printPayment(paList);
		}
		
		

		
	


	
	

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem4 = new JMenuItem();
		menuItem5 = new JMenuItem();
		menuItem6 = new JMenuItem();
		menu2 = new JMenu();
		menuItem3 = new JMenuItem();
		panel2 = new JPanel();
		tgQCJZ = new JToggleButton();
		label4 = new JLabel();
		tgTJBB = new JToggleButton();
		label2 = new JLabel();
		tgZHGL = new JToggleButton();
		label3 = new JLabel();
		tgZJGL = new JToggleButton();
		label1 = new JLabel();
		panelMain = new JPanel();
		pnZJGL = new JPanel();
		tabbedPane4 = new JTabbedPane();
		scrollPane14 = new JScrollPane();
		panel3 = new JPanel();
		btDate = new JButton();
		btAddress = new JButton();
		textDate = new JTextField();
		textAddress = new JTextField();
		scrollPane16 = new JScrollPane();
		tableReceipt = new JTable();
		label15 = new JLabel();
		lbAll = new JLabel();
		lbTotal = new JLabel();
		btRExcel = new JButton();
		panel1 = new JPanel();
		btAddP = new JButton();
		scrollPane15 = new JScrollPane();
		tablePayment = new JTable();
		btPExcel = new JButton();
		pnTJBB = new JPanel();
		tpCost = new JTabbedPane();
		scrollPane5 = new JScrollPane();
		panel4 = new JPanel();
		label5 = new JLabel();
		textBeginYear = new JTextField();
		label9 = new JLabel();
		textEndYear = new JTextField();
		btSearch2 = new JButton();
		scrollPane17 = new JScrollPane();
		tabbedPane5 = new JTabbedPane();
		scrollPane18 = new JScrollPane();
		tableRec = new JTable();
		scrollPane19 = new JScrollPane();
		tablePay = new JTable();
		textBeginMonth = new JTextField();
		label8 = new JLabel();
		label10 = new JLabel();
		textBeginDay = new JTextField();
		label11 = new JLabel();
		label12 = new JLabel();
		textEndMonth = new JTextField();
		textEndDay = new JTextField();
		label13 = new JLabel();
		label14 = new JLabel();
		jbExcel = new JButton();
		scrollPane7 = new JScrollPane();
		tableBenefit = new JTable();
		pnQCJZ = new JPanel();
		tp = new JTabbedPane();
		scrollPane2 = new JScrollPane();
		table4 = new JTable();
		scrollPane3 = new JScrollPane();
		table5 = new JTable();
		scrollPane4 = new JScrollPane();
		table6 = new JTable();
		scrollPane6 = new JScrollPane();
		table7 = new JTable();
		scrollPane8 = new JScrollPane();
		table8 = new JTable();
		scrollPane9 = new JScrollPane();
		table9 = new JTable();
		scrollPane10 = new JScrollPane();
		table10 = new JTable();
		scrollPane11 = new JScrollPane();
		table11 = new JTable();
		pnZHGL = new JPanel();
		scrollPane12 = new JScrollPane();
		tabbedPane3 = new JTabbedPane();
		scrollPane13 = new JScrollPane();
		tableAccounts = new JTable();
		button7 = new JButton();
		btAdd = new JButton();
		btModify = new JButton();
		button8 = new JButton();
		textName = new JTextField();
		btSearch = new JButton();
		label6 = new JLabel();
		vSpacer1 = new JPanel(null);
		btDelete = new JButton();
		label7 = new JLabel();
		textName2 = new JTextField();

		//======== this ========
		Container contentPane = getContentPane();

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("\u9009\u9879(O)");

				//---- menuItem1 ----
				menuItem1.setText("\u8d44\u91d1\u7ba1\u7406");
				menu1.add(menuItem1);

				//---- menuItem2 ----
				menuItem2.setText("\u7edf\u8ba1\u62a5\u8868");
				menu1.add(menuItem2);

				//---- menuItem4 ----
				menuItem4.setText("\u8d26\u6237\u7ba1\u7406");
				menu1.add(menuItem4);

				//---- menuItem5 ----
				menuItem5.setText("\u671f\u521d\u5efa\u8d26");
				menu1.add(menuItem5);

				//---- menuItem6 ----
				menuItem6.setText("\u4fee\u6539\u5bc6\u7801");
				menu1.add(menuItem6);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("\u5e2e\u52a9(H)");

				//---- menuItem3 ----
				menuItem3.setText("\u5173\u4e8e\u6211\u4eec");
				menuItem3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						menuItem3MouseReleased(e);
					}
				});
				menu2.add(menuItem3);
			}
			menuBar1.add(menu2);
		}
		setJMenuBar(menuBar1);

		//======== panel2 ========
		{

			//---- tgQCJZ ----
			tgQCJZ.setIcon(new ImageIcon(getClass().getResource("/icons/createbill_72x72.png")));
			tgQCJZ.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgQCJZMouseClicked(e);
				}
			});

			//---- label4 ----
			label4.setText("\u671f\u521d\u5efa\u8d26");

			//---- tgTJBB ----
			tgTJBB.setIcon(new ImageIcon(getClass().getResource("/icons/form_72x72.png")));
			tgTJBB.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgTJBBMouseClicked(e);
				}
			});

			//---- label2 ----
			label2.setText("\u7edf\u8ba1\u62a5\u8868");

			//---- tgZHGL ----
			tgZHGL.setIcon(new ImageIcon(getClass().getResource("/icons/account_72x72.png")));
			tgZHGL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgZHGLMouseClicked(e);
				}
			});

			//---- label3 ----
			label3.setText("\u8d26\u6237\u7ba1\u7406");

			//---- tgZJGL ----
			tgZJGL.setIcon(new ImageIcon(getClass().getResource("/icons/money_72x72.png")));
			tgZJGL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgZJGLMouseClicked(e);
				}
			});

			//---- label1 ----
			label1.setText("\u8d44\u91d1\u7ba1\u7406");

			//======== panelMain ========
			{
				panelMain.setLayout(new BorderLayout());
			}

			GroupLayout panel2Layout = new GroupLayout(panel2);
			panel2.setLayout(panel2Layout);
			panel2Layout.setHorizontalGroup(
				panel2Layout.createParallelGroup()
					.addGroup(panel2Layout.createSequentialGroup()
						.addGroup(panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(tgZJGL)
								.addGap(18, 18, 18)
								.addComponent(tgZHGL)
								.addGap(12, 12, 12)
								.addComponent(tgTJBB)
								.addGap(18, 18, 18)
								.addComponent(tgQCJZ))
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(33, 33, 33)
								.addComponent(label1)
								.addGap(76, 76, 76)
								.addComponent(label3)
								.addGap(60, 60, 60)
								.addComponent(label2)
								.addGap(74, 74, 74)
								.addComponent(label4)))
						.addContainerGap(311, Short.MAX_VALUE))
					.addComponent(panelMain, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
			);
			panel2Layout.setVerticalGroup(
				panel2Layout.createParallelGroup()
					.addGroup(panel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel2Layout.createParallelGroup()
							.addComponent(tgZJGL, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(tgZHGL, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(tgTJBB, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(tgQCJZ, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel2Layout.createParallelGroup()
							.addComponent(label1)
							.addGroup(panel2Layout.createParallelGroup()
								.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label2))
								.addGroup(panel2Layout.createSequentialGroup()
									.addComponent(label3)
									.addGap(0, 0, Short.MAX_VALUE))))
						.addGap(7, 7, 7)
						.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		pack();
		setLocationRelativeTo(getOwner());

		//======== pnZJGL ========
		{

			//======== tabbedPane4 ========
			{

				//======== scrollPane14 ========
				{

					//======== panel3 ========
					{

						//---- btDate ----
						btDate.setText("\u6309\u5929\u67e5\u770b");
						btDate.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								btDateMouseReleased(e);
							}
						});

						//---- btAddress ----
						btAddress.setText("\u6309\u8425\u4e1a\u5385\u67e5\u770b");
						btAddress.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								btAddressMouseReleased(e);
							}
						});

						//======== scrollPane16 ========
						{
							scrollPane16.setViewportView(tableReceipt);
						}

						//---- label15 ----
						label15.setText(" \u5982\uff1a2015/11/27");

						//---- lbAll ----
						lbAll.setText("\u5408\u8ba1\uff1a");

						//---- lbTotal ----
						lbTotal.setText("text");

						//---- btRExcel ----
						btRExcel.setText("\u5bfc\u51fa");
						btRExcel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								btRExcelMouseReleased(e);
							}
						});

						GroupLayout panel3Layout = new GroupLayout(panel3);
						panel3.setLayout(panel3Layout);
						panel3Layout.setHorizontalGroup(
							panel3Layout.createParallelGroup()
								.addGroup(panel3Layout.createSequentialGroup()
									.addComponent(scrollPane16, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(panel3Layout.createParallelGroup()
										.addGroup(panel3Layout.createSequentialGroup()
											.addGroup(panel3Layout.createParallelGroup()
												.addComponent(textAddress)
												.addComponent(btAddress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(textDate)
												.addComponent(btDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btRExcel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGap(95, 95, 95))
										.addGroup(panel3Layout.createSequentialGroup()
											.addComponent(lbAll)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(lbTotal, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						);
						panel3Layout.setVerticalGroup(
							panel3Layout.createParallelGroup()
								.addGroup(panel3Layout.createSequentialGroup()
									.addGap(32, 32, 32)
									.addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btDate)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label15, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btAddress)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lbAll, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbTotal))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btRExcel)
									.addContainerGap(719, Short.MAX_VALUE))
								.addGroup(panel3Layout.createSequentialGroup()
									.addComponent(scrollPane16, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 709, Short.MAX_VALUE))
						);
					}
					scrollPane14.setViewportView(panel3);
				}
				tabbedPane4.addTab("\u6536\u6b3e\u5355", scrollPane14);

				//======== panel1 ========
				{

					//---- btAddP ----
					btAddP.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
					btAddP.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							button1MouseReleased(e);
						}
					});

					//======== scrollPane15 ========
					{
						scrollPane15.setViewportView(tablePayment);
					}

					//---- btPExcel ----
					btPExcel.setText("\u5bfc\u51fa");
					btPExcel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							btPExcelMouseReleased(e);
						}
					});

					GroupLayout panel1Layout = new GroupLayout(panel1);
					panel1.setLayout(panel1Layout);
					panel1Layout.setHorizontalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
								.addComponent(scrollPane15, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panel1Layout.createParallelGroup()
									.addComponent(btAddP, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addComponent(btPExcel))
								.addGap(22, 22, 22))
					);
					panel1Layout.setVerticalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btAddP)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btPExcel)
								.addContainerGap(170, Short.MAX_VALUE))
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(scrollPane15, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 1, Short.MAX_VALUE))
					);
				}
				tabbedPane4.addTab("\u4ed8\u6b3e\u5355", panel1);
			}

			GroupLayout pnZJGLLayout = new GroupLayout(pnZJGL);
			pnZJGL.setLayout(pnZJGLLayout);
			pnZJGLLayout.setHorizontalGroup(
				pnZJGLLayout.createParallelGroup()
					.addComponent(tabbedPane4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
			);
			pnZJGLLayout.setVerticalGroup(
				pnZJGLLayout.createParallelGroup()
					.addComponent(tabbedPane4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
			);
		}

		//======== pnTJBB ========
		{

			//======== tpCost ========
			{
				tpCost.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						tpCostMouseReleased(e);
					}
				});

				//======== scrollPane5 ========
				{

					//======== panel4 ========
					{

						//---- label5 ----
						label5.setText("\u5f00\u59cb\u65e5\u671f");

						//---- label9 ----
						label9.setText("\u7ed3\u675f\u65e5\u671f");

						//---- btSearch2 ----
						btSearch2.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
						btSearch2.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								btSearch2MouseReleased(e);
							}
						});

						//======== scrollPane17 ========
						{

							//======== tabbedPane5 ========
							{

								//======== scrollPane18 ========
								{
									scrollPane18.setViewportView(tableRec);
								}
								tabbedPane5.addTab("\u6536\u6b3e\u5355", scrollPane18);

								//======== scrollPane19 ========
								{
									scrollPane19.setViewportView(tablePay);
								}
								tabbedPane5.addTab("\u4ed8\u6b3e\u5355", scrollPane19);
							}
							scrollPane17.setViewportView(tabbedPane5);
						}

						//---- label8 ----
						label8.setText("\u5e74");

						//---- label10 ----
						label10.setText("\u6708");

						//---- label11 ----
						label11.setText("\u65e5");

						//---- label12 ----
						label12.setText("\u5e74");

						//---- label13 ----
						label13.setText("\u6708");

						//---- label14 ----
						label14.setText("\u65e5");

						//---- jbExcel ----
						jbExcel.setText("\u5bfc\u51fa");
						jbExcel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								jbExcelMouseReleased(e);
							}
						});

						GroupLayout panel4Layout = new GroupLayout(panel4);
						panel4.setLayout(panel4Layout);
						panel4Layout.setHorizontalGroup(
							panel4Layout.createParallelGroup()
								.addGroup(panel4Layout.createSequentialGroup()
									.addComponent(label5)
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(textBeginYear, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label8)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textBeginMonth, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label10)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textBeginDay, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label11)
									.addGap(12, 12, 12)
									.addComponent(label9)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textEndYear, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label12, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addGap(3, 3, 3)
									.addComponent(textEndMonth, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label13)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(textEndDay, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label14)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btSearch2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(41, 41, 41)
									.addComponent(jbExcel)
									.addGap(0, 56, Short.MAX_VALUE))
								.addGroup(panel4Layout.createSequentialGroup()
									.addComponent(scrollPane17)
									.addContainerGap())
						);
						panel4Layout.setVerticalGroup(
							panel4Layout.createParallelGroup()
								.addGroup(panel4Layout.createSequentialGroup()
									.addGroup(panel4Layout.createParallelGroup()
										.addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(label5)
											.addComponent(textBeginYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textBeginMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label8)
											.addComponent(label10)
											.addComponent(textBeginDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label9)
											.addComponent(label11)
											.addComponent(textEndYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label12)
											.addComponent(textEndMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label13)
											.addComponent(textEndDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label14))
										.addComponent(btSearch2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(jbExcel, GroupLayout.Alignment.TRAILING))
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(scrollPane17, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 96, Short.MAX_VALUE))
						);
					}
					scrollPane5.setViewportView(panel4);
				}
				tpCost.addTab("\u7ecf\u8425\u60c5\u51b5\u8868", scrollPane5);

				//======== scrollPane7 ========
				{
					scrollPane7.setViewportView(tableBenefit);
				}
				tpCost.addTab("\u6210\u672c\u6536\u76ca\u8868", scrollPane7);
			}

			GroupLayout pnTJBBLayout = new GroupLayout(pnTJBB);
			pnTJBB.setLayout(pnTJBBLayout);
			pnTJBBLayout.setHorizontalGroup(
				pnTJBBLayout.createParallelGroup()
					.addComponent(tpCost, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
			);
			pnTJBBLayout.setVerticalGroup(
				pnTJBBLayout.createParallelGroup()
					.addGroup(pnTJBBLayout.createSequentialGroup()
						.addComponent(tpCost, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
			);
		}

		//======== pnQCJZ ========
		{

			//======== tp ========
			{

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(table4);
				}
				tp.addTab("\u673a\u6784\u4eba\u5458\u8868", scrollPane2);

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(table5);
				}
				tp.addTab("\u8f66\u8f86\u4fe1\u606f\u8868", scrollPane3);

				//======== scrollPane4 ========
				{
					scrollPane4.setViewportView(table6);
				}
				tp.addTab("\u53f8\u673a\u4fe1\u606f\u8868", scrollPane4);

				//======== scrollPane6 ========
				{
					scrollPane6.setViewportView(table7);
				}
				tp.addTab("\u5165\u5e93\u5355", scrollPane6);

				//======== scrollPane8 ========
				{
					scrollPane8.setViewportView(table8);
				}
				tp.addTab("\u51fa\u5e93\u5355", scrollPane8);

				//======== scrollPane9 ========
				{
					scrollPane9.setViewportView(table9);
				}
				tp.addTab("\u4ed8\u6b3e\u5355", scrollPane9);

				//======== scrollPane10 ========
				{
					scrollPane10.setViewportView(table10);
				}
				tp.addTab("\u6536\u6b3e\u5355", scrollPane10);

				//======== scrollPane11 ========
				{
					scrollPane11.setViewportView(table11);
				}
				tp.addTab("\u8d26\u6237\u4fe1\u606f\u8868", scrollPane11);
			}

			GroupLayout pnQCJZLayout = new GroupLayout(pnQCJZ);
			pnQCJZ.setLayout(pnQCJZLayout);
			pnQCJZLayout.setHorizontalGroup(
				pnQCJZLayout.createParallelGroup()
					.addGroup(pnQCJZLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tp, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addContainerGap())
			);
			pnQCJZLayout.setVerticalGroup(
				pnQCJZLayout.createParallelGroup()
					.addGroup(pnQCJZLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tp, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
						.addContainerGap())
			);
		}

		//======== pnZHGL ========
		{

			//======== scrollPane12 ========
			{

				//======== tabbedPane3 ========
				{

					//======== scrollPane13 ========
					{
						scrollPane13.setViewportView(tableAccounts);
					}
					tabbedPane3.addTab("\u8d26\u6237\u4f59\u989d", scrollPane13);
				}
				scrollPane12.setViewportView(tabbedPane3);
			}

			//---- button7 ----
			button7.setText("\u67e5\u770b");

			//---- btAdd ----
			btAdd.setText("\u6dfb\u52a0");
			btAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btAddMouseReleased(e);
				}
			});

			//---- btModify ----
			btModify.setText("\u786e\u8ba4");
			btModify.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btModifyMouseReleased(e);
				}
			});

			//---- button8 ----
			button8.setText("\u5220\u9664");

			//---- btSearch ----
			btSearch.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
			btSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btSearchMouseReleased(e);
					btSearchMouseReleased(e);
					btSearchMouseReleased(e);
				}
			});

			//---- label6 ----
			label6.setText("\u8d26\u6237\u540d\u79f0\uff1a");

			//---- btDelete ----
			btDelete.setText("\u5220\u9664");
			btDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					button11MouseReleased(e);
				}
			});

			//---- label7 ----
			label7.setText("\u8d26\u6237\u4f59\u989d\uff1a");

			GroupLayout pnZHGLLayout = new GroupLayout(pnZHGL);
			pnZHGL.setLayout(pnZHGLLayout);
			pnZHGLLayout.setHorizontalGroup(
				pnZHGLLayout.createParallelGroup()
					.addGroup(pnZHGLLayout.createSequentialGroup()
						.addGroup(pnZHGLLayout.createParallelGroup()
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addGap(10, 10, 10)
								.addComponent(label6)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textName, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label7)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textName2, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 13, Short.MAX_VALUE))
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane12, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnZHGLLayout.createParallelGroup()
							.addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addComponent(btAdd)
							.addComponent(btDelete)
							.addComponent(btModify))
						.addContainerGap())
			);
			pnZHGLLayout.setVerticalGroup(
				pnZHGLLayout.createParallelGroup()
					.addGroup(pnZHGLLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pnZHGLLayout.createParallelGroup()
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addGap(52, 52, 52)
								.addComponent(btAdd)
								.addGap(18, 18, 18)
								.addComponent(btDelete)
								.addGap(18, 18, 18)
								.addComponent(btModify))
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addGroup(pnZHGLLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
									.addComponent(label6)
									.addComponent(btSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textName2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scrollPane12, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		}
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        String names[] = { "收款日期", "收款单位", "收款人", "收款方", "收款金额", "收款地点"};
        for (int i = 0; i < names.length; i++) {
            tableReceipt.addColumn(new TableColumn(i));
            tableReceipt.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }
        tableReceipt.setRowHeight(50);
        
        String names1[] = { "付款日期", "付款金额", "付款人", "付款账号", "条目", "备注"};
        for (int i = 0; i < names1.length; i++) {
        	tablePayment.addColumn(new TableColumn(i));
        	tablePayment.getColumnModel().getColumn(i).setHeaderValue(names1[i]);
        }
        tablePayment.setRowHeight(50);
        
        

      
        String names3[] = { "总收入", "总支出", "总收益"};
        for (int i = 0; i < names3.length; i++) {
            tableBenefit.addColumn(new TableColumn(i));
            tableBenefit.getColumnModel().getColumn(i).setHeaderValue(names3[i]);
        }
        tableBenefit.setRowHeight(50);

        String names4[] = { "??????", "???", "????", "???????" };
        for (int i = 0; i < names4.length; i++) {
            table4.addColumn(new TableColumn(i));
            table4.getColumnModel().getColumn(i).setHeaderValue(names4[i]);
        }
        table4.setRowHeight(50);
        
        String names5[] = { "账户名称", "账户余额" };
        for (int i = 0; i < names5.length; i++) {
        	tableAccounts.addColumn(new TableColumn(i));
            tableAccounts.getColumnModel().getColumn(i).setHeaderValue(names5[i]);
        }
        tableAccounts.setRowHeight(50);
        
        String names6[] = { "收款日期", "收款单位", "收款人", "收款方", "收款金额", "收款地点"};
        for (int i = 0; i < names6.length; i++) {
            tableRec.addColumn(new TableColumn(i));
            tableRec.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }
        tableRec.setRowHeight(50);
        
        String names7[] = { "付款日期", "付款金额", "付款人", "付款账号", "条目", "备注"};
        for (int i = 0; i < names7.length; i++) {
        	tablePay.addColumn(new TableColumn(i));
        	tablePay.getColumnModel().getColumn(i).setHeaderValue(names1[i]);
        }
        tablePay.setRowHeight(50);
        
        panelMain.add(pnZJGL, BorderLayout.CENTER);

    }
    
    
   

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem6;
	private JMenu menu2;
	private JMenuItem menuItem3;
	private JPanel panel2;
	private JToggleButton tgQCJZ;
	private JLabel label4;
	private JToggleButton tgTJBB;
	private JLabel label2;
	private JToggleButton tgZHGL;
	private JLabel label3;
	private JToggleButton tgZJGL;
	private JLabel label1;
	private JPanel panelMain;
	private JPanel pnZJGL;
	private JTabbedPane tabbedPane4;
	private JScrollPane scrollPane14;
	private JPanel panel3;
	private JButton btDate;
	private JButton btAddress;
	private JTextField textDate;
	private JTextField textAddress;
	private JScrollPane scrollPane16;
	private JTable tableReceipt;
	private JLabel label15;
	private JLabel lbAll;
	private JLabel lbTotal;
	private JButton btRExcel;
	private JPanel panel1;
	private JButton btAddP;
	private JScrollPane scrollPane15;
	private JTable tablePayment;
	private JButton btPExcel;
	private JPanel pnTJBB;
	private JTabbedPane tpCost;
	private JScrollPane scrollPane5;
	private JPanel panel4;
	private JLabel label5;
	private JTextField textBeginYear;
	private JLabel label9;
	private JTextField textEndYear;
	private JButton btSearch2;
	private JScrollPane scrollPane17;
	private JTabbedPane tabbedPane5;
	private JScrollPane scrollPane18;
	private JTable tableRec;
	private JScrollPane scrollPane19;
	private JTable tablePay;
	private JTextField textBeginMonth;
	private JLabel label8;
	private JLabel label10;
	private JTextField textBeginDay;
	private JLabel label11;
	private JLabel label12;
	private JTextField textEndMonth;
	private JTextField textEndDay;
	private JLabel label13;
	private JLabel label14;
	private JButton jbExcel;
	private JScrollPane scrollPane7;
	private JTable tableBenefit;
	private JPanel pnQCJZ;
	private JTabbedPane tp;
	private JScrollPane scrollPane2;
	private JTable table4;
	private JScrollPane scrollPane3;
	private JTable table5;
	private JScrollPane scrollPane4;
	private JTable table6;
	private JScrollPane scrollPane6;
	private JTable table7;
	private JScrollPane scrollPane8;
	private JTable table8;
	private JScrollPane scrollPane9;
	private JTable table9;
	private JScrollPane scrollPane10;
	private JTable table10;
	private JScrollPane scrollPane11;
	private JTable table11;
	private JPanel pnZHGL;
	private JScrollPane scrollPane12;
	private JTabbedPane tabbedPane3;
	private JScrollPane scrollPane13;
	private JTable tableAccounts;
	private JButton button7;
	private JButton btAdd;
	private JButton btModify;
	private JButton button8;
	private JTextField textName;
	private JButton btSearch;
	private JLabel label6;
	private JPanel vSpacer1;
	private JButton btDelete;
	private JLabel label7;
	private JTextField textName2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
