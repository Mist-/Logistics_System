package presentation.financial;

/*
 * Created by JFormDesigner on Sat Oct 31 17:11:41 CST 2015
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import utils.Timestamper;
import businesslogic.impl.company.CompanyBLController;
import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.impl.financialbl.InitialBuild;
import businesslogic.service.Financial.FinancialBLService;
import data.enums.POType;
import data.vo.AccountVO;
import data.vo.CostBenefitVO;
import data.vo.PaymentVO;
import data.vo.ReceiptVO;

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel getPanelCheck(){
        return this.pnTJBB;
    }

    private void setNot() {
        if (tablePayment.getRowCount() == 0) {
            btPExcel.setEnabled(false);
        }
        if (tableReceipt.getRowCount() == 0) {
            btDate.setEnabled(false);
            btAddress.setEnabled(false);
            btRExcel.setEnabled(false);
        }
        if (textBeginYear.getText().equals("") || textBeginMonth.getText().equals("") || textBeginDay.getText().equals("") || textEndYear.getText().equals("") || textEndMonth.getText().equals("") || textEndDay.getText().equals("")) {
            btSearch2.setEnabled(false);
        }
        if (tablePay.getRowCount() == 0 && tableRec.getRowCount() == 0) {
            jbExcel.setEnabled(false);
        }
        if (textName.getText().equals("")) {
            btSearch.setEnabled(false);
        }
        if (textName.getText().equals("") || textName2.getText().equals("")) {
            btAdd.setEnabled(false);
        }
        if (tableAccounts.getSelectedRow() == 0) {
            btDelete.setEnabled(false);
            btModify.setEnabled(false);
        }
    }

    //资金管理初始化
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

        refresh();

        //设置按钮不可点击，但是有bug
//        if(tablePayment.getRowCount()==0){
//    		btPExcel.setEnabled(false);
//    	}
//    	if(tableReceipt.getRowCount()==0){
//    		btDate.setEnabled(false);
//    		btAddress.setEnabled(false);
//    		btRExcel.setEnabled(false);
//    	}


        panelMain.updateUI();
        this.repaint();
    }

    //更新收款单和付款单
    private void refresh() {
        ArrayList<PaymentVO> payments = financialBL.searchAllPayment();
        Vector data = ((DefaultTableModel) tablePayment.getModel()).getDataVector();
        data.clear();
        for (PaymentVO paymentVO : payments) {
            Vector<Object> row = new Vector<>();
            row.add(paymentVO.getDate());
            row.add(paymentVO.getMoney());
            row.add(paymentVO.getName());
            row.add(paymentVO.getAccount());
            row.add(paymentVO.getInfo());
            row.add(paymentVO.getExInfo());
            data.add(row);
        }
        tablePayment.updateUI();
        tablePayment.repaint();

        ArrayList<ReceiptVO> receipts = financialBL.searchAllReceipt();
        data = ((DefaultTableModel) tableReceipt.getModel()).getDataVector();
        data.clear();
        for (ReceiptVO receiptVO : receipts) {
            Vector<Object> row = new Vector<>();
            row.add(receiptVO.getDate());
            row.add(receiptVO.getInstitution());
            row.add(receiptVO.getPeople());
            row.add(receiptVO.getSender());
            row.add(receiptVO.getMoney());
            row.add(receiptVO.getAddress());

            data.add(row);
        }
        tableReceipt.updateUI();
        tableReceipt.repaint();
    }

    //账户管理初始化
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


        ArrayList<AccountVO> accounts = financialBL.updateMoney();
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        data.clear();
        for (AccountVO accountVO : accounts) {
            Vector<Object> row = new Vector<>();
            row.add(accountVO.getAccountNum());
            row.add(accountVO.getName());
            row.add(accountVO.getMoney());
            data.add(row);
        }
        tableAccounts.updateUI();
        tableAccounts.repaint();

        //设置按钮不可点击，但是有bug
//        if(textName.getText().equals("")){
//    		btSearch.setEnabled(false);
//    	}
//    	if(textName.getText().equals("") || textName2.getText().equals("")){
//    		btAdd.setEnabled(false);
//    	}
//    	if(tableAccounts.getSelectedRow() == 0){
//    		btDelete.setEnabled(false);
//    		btModify.setEnabled(false);
//    	}


        panelMain.updateUI();
        this.repaint();
    }

    //统计报表初始化
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

        //设置按钮不可点击，但是有bug
//        if(textBeginYear.getText().equals("") || textBeginMonth.getText().equals("") || textBeginDay.getText().equals("") || textEndYear.getText().equals("") || textEndMonth.getText().equals("") || textEndDay.getText().equals("")){
//    		btSearch2.setEnabled(false);
//    	}
//    	if(tablePay.getRowCount()==0 && tableRec.getRowCount()==0){
//    		jbExcel.setEnabled(false);
//    	}
//
        panelMain.updateUI();
        this.repaint();
    }

    //期初建账初始化
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
        if (textName.getText().equals("")) {
            textName.requestFocus();
            return;
        }
        String name = textName.getText();

        ArrayList<AccountVO> accounts = financialBL.findAccount(name);
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        data.clear();
        for (AccountVO accountVO : accounts) {
            Vector<Object> row = new Vector<>();
            row.add(accountVO.getAccountNum());
            row.add(accountVO.getName());
            row.add(accountVO.getMoney());
            data.add(row);
        }
        tableAccounts.updateUI();
        tableAccounts.repaint();
    }


    //ZHGL 新增账户
    private void btAddMouseReleased(MouseEvent e) {
        if (textName.getText().equals("") || textName2.getText().equals("")) {
            textName.requestFocus();
            return;
        }
        String name = textName.getText();
        if (!textName2.getText().matches("[0-9]*[.]?[0-9]*")) {
            JOptionPane.showMessageDialog(this, "您输入的金额数据格式错误");
            textName2.requestFocus();
            return;
        }
        double money = Double.valueOf(textName2.getText());
        AccountVO account = financialBL.addAccount(name, money);
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        Vector<Object> row = new Vector<>();
        row.add(account.getAccountNum());
        row.add(account.getName());
        row.add(account.getMoney());
        data.add(row);
        tableAccounts.updateUI();
        tableAccounts.repaint();
    }

    //ZHGL 修改账户,在JTable中修改后会  新弹出窗口进行修改
    private void btModifyMouseReleased(MouseEvent e) {
        int x = tableAccounts.getSelectedRow();
        if (x == -1) {
            return;
        }
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        Vector row = (Vector<Object>) data.get(tableAccounts.getSelectedRow());

        long accountNum = (long) row.get(0);
        String name = (String) row.get(1);
        double money = (Double) row.get(2);

        ModifyAccount modifyAccount = new ModifyAccount(accountNum, name, money);
        modifyAccount.setVisible(true);

        searchAccount();
    }

    //搜索所有的AccountPO并显示
    private void searchAccount() {
        ArrayList<AccountVO> accounts = financialBL.updateMoney();
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();

        data.clear();
        for (AccountVO accountVO : accounts) {
            Vector<Object> rows = new Vector<>();
            rows.add(accountVO.getAccountNum());
            rows.add(accountVO.getName());
            rows.add(accountVO.getMoney());
            data.add(rows);
        }
        tableAccounts.updateUI();
        tableAccounts.repaint();
    }

    //ZHGL 删除账户
    private void button11MouseReleased(MouseEvent e) {
        int x = tableAccounts.getSelectedRow();
        if (x == -1) {
            return;
        }
        Vector data = ((DefaultTableModel) tableAccounts.getModel()).getDataVector();
        long accountNum = (long) ((Vector<Object>) data.get(tableAccounts.getSelectedRow())).get(0);

        financialBL.deleteAccount(accountNum);
        // TODO: 调用逻辑层接口删除数据

        data.remove(tableAccounts.getSelectedRow());

        searchAccount();

    }


    ArrayList<ReceiptVO> re;

    //ZJGL 按天对收款单进行查看
    private void btDateMouseReleased(MouseEvent e) {
        if (textDate.getText().equals("")) {
            textDate.requestFocus();
            return;
        }

        if (textDate.getText().charAt(4) != '/' || textDate.getText().charAt(7) != '/') {
            JOptionPane.showMessageDialog(this, "日期之间请用'/'隔开");
            textDate.requestFocus();
            return;
        }
        String date = textDate.getText();
        re = financialBL.checkFromDate(date);
        Vector data = ((DefaultTableModel) tableReceipt.getModel()).getDataVector();
        data.clear();
        for (ReceiptVO receiptVO : re) {
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
        lbTotal.setText(num+"元");
        lbAll.setVisible(true);
        lbTotal.setVisible(true);
        tableReceipt.updateUI();
        tableReceipt.repaint();
    }


    //ZJGL 按营业厅对收款单进行查看
    private void btAddressMouseReleased(MouseEvent e) {
        if (textAddress.getText().equals("")) {
            textAddress.requestFocus();
            return;
        }


        String address = textAddress.getText();

        re = financialBL.checkFromAddress(address);
        Vector data = ((DefaultTableModel) tableReceipt.getModel()).getDataVector();
        data.clear();
        for (ReceiptVO receiptVO : re) {
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
        lbTotal.setText(num+"元");
        lbAll.setVisible(true);
        lbTotal.setVisible(true);
        tableReceipt.updateUI();
        tableReceipt.repaint();
    }

    ArrayList<PaymentVO> pay;
    ArrayList<ReceiptVO> rec;


    //TJBB 对经营情况表的查看
    private void btSearch2MouseReleased(MouseEvent e) {
        if (textBeginYear.getText().equals("") || textBeginMonth.getText().equals("") || textBeginDay.getText().equals("") || textEndYear.getText().equals("") || textEndMonth.getText().equals("") || textEndDay.getText().equals("")) {
            return;
        }

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
        for (ReceiptVO receiptVO : rec) {
        	
            Vector<Object> row = new Vector<>();
            row.add(receiptVO.getDate());
            row.add(receiptVO.getInstitution());
            row.add(receiptVO.getPeople());
            row.add(receiptVO.getSender());
            row.add(receiptVO.getMoney());
            row.add(receiptVO.getAddress());
            data.add(row);
        }

        Vector data1 = ((DefaultTableModel) tablePay.getModel()).getDataVector();
        data1.clear();
        for (PaymentVO paymentVO : pay) {
            Vector<Object> row = new Vector<>();
            row.add(paymentVO.getDate());
            row.add(paymentVO.getMoney());
            row.add(paymentVO.getName());
            row.add(paymentVO.getAccount());
            row.add(paymentVO.getInfo());
            row.add(paymentVO.getExInfo());
            data1.add(row);
        }
        tableRec.updateUI();
        tableRec.repaint();
        
        tablePay.updateUI();
        tablePay.repaint();

    }


    //TJBB 对成本收益表的查看
    private void tpCostMouseReleased(MouseEvent e) {

        CostBenefitVO costBenefitVO = companyBLcontroller.searchCostBenefitVO();
        
        Vector data = ((DefaultTableModel) tableBenefit.getModel()).getDataVector();
        data.clear();
        Vector<Object> row = new Vector<>();
        row.add(costBenefitVO.getAllIncome());
        row.add(costBenefitVO.getAllPay());
        row.add(costBenefitVO.getAllProfit());
        data.add(row);
        
        tableBenefit.updateUI();
        tableBenefit.repaint();
    }

    //ZJGL 新建付款单
    private void button1MouseReleased(MouseEvent e) {
    	if (!e.getSource().equals(btAccountInit)) {
	        PaymentAdd dialogAddSalary = new PaymentAdd();
	        dialogAddSalary.setVisible(true);
	        refresh();
    	}
    }

    //TJBB 导出经营情况表成Excel
    private void jbExcelMouseReleased(MouseEvent e) {
        if (tablePay.getRowCount() == 0 && tableRec.getRowCount() == 0) {
            return;
        }
        financialBL.printPayment(pay);
        financialBL.printReceipt(rec);
        JOptionPane.showMessageDialog(this, "导出经营情况表Excel成功，位于client的根目录下");
    }

    //ZJGL 导出收款单表成Excel
    private void btRExcelMouseReleased(MouseEvent e) {
        if (tableReceipt.getRowCount() == 0) {
            return;
        }
        financialBL.printReceipt(re);
        JOptionPane.showMessageDialog(this, "导出收款单Excel成功，位于client的根目录下");
    }


    //ZJGL 导出付款单表成Excel
    private void btPExcelMouseReleased(MouseEvent e) {
        if (tablePayment.getRowCount() == 0) {
            return;
        }
        ArrayList<PaymentVO> payments = financialBL.searchAllPayment();
        financialBL.printPayment(payments);
        JOptionPane.showMessageDialog(this, "导出付款单Excel成功，位于client的根目录下");
    }

    private void thisWindowOpened(WindowEvent e) {
        refresh();
    }

    //QCJZ
    private void btAccountInitMouseReleased(MouseEvent e) {
        ArrayList<POType> typesToInit = new ArrayList<>();
        String result = "";
        if (chkAccount.isSelected()) {
            typesToInit.add(POType.ACCOUNT);
            result += "银行账户信息\n";
        }
        if (chkDriverInfo.isSelected()) {
            typesToInit.add(POType.DRIVERINFO);
            result += "司机信息\n";
        }
        if (chkInstitution.isSelected()) {
            typesToInit.add(POType.INSTITUTION);
            result += "机构信息\n";
        }
        if (chkPayment.isSelected()) {
            typesToInit.add(POType.PAYMENT);
            result += "付款单信息\n";
        }
        if (chkReceipt.isSelected()) {
            typesToInit.add(POType.RECEIPT);
            result += "收款单信息\n";
        }
        if (chkVehicle.isSelected()) {
            typesToInit.add(POType.VEHICLEINFO);
            result += "车辆信息\n";
        }
        if (chkStaff.isSelected()) {
            typesToInit.add(POType.STAFF);
            result += "人员信息\n";
        }
        if (chkStorage.isSelected()) {
            typesToInit.add(POType.STORAGEINFO);
            result += "库存信息\n";
        }
        if(result.equals("")){
        	return;
        }
        for (POType type: typesToInit) {
        	new InitialBuild().initAll(type);
        }
        JOptionPane.showMessageDialog(null, "以下账目完成期初建账。 " + Timestamper.getTimeByDate() + "\\ 文件夹下。\n" + result);
        refreshUI();
    }

    public void refreshUI() {
    	
    }
    
	private void miZJGLMouseReleased(MouseEvent e) {
		tgZJGLMouseClicked(e);
	}

	private void miTJBBMouseReleased(MouseEvent e) {
		tgTJBBMouseClicked(e);
	}

	private void miZHGLMouseReleased(MouseEvent e) {
		tgZHGLMouseClicked(e);
	}

	private void miQCJZMouseReleased(MouseEvent e) {
		tgQCJZMouseClicked(e);
	}

	//ZHGL 导出银行账户成Excel
    private void btAExcelMouseReleased(MouseEvent e) {
    	if (tableAccounts.getRowCount() == 0) {
            return;
        }
        ArrayList<AccountVO> accounts = financialBL.updateMoney();
        financialBL.printAccount(accounts);
        JOptionPane.showMessageDialog(this, "导出银行账户Excel成功，位于client的根目录下");
    }

    private void chkAllMouseReleased(MouseEvent e) {
        if (chkAll.isSelected()) {
            chkInstitution.setSelected(true);
            chkVehicle.setSelected(true);
            chkReceipt.setSelected(true);
            chkPayment.setSelected(true);
            chkAccount.setSelected(true);
            chkDriverInfo.setSelected(true);
            chkStaff.setSelected(true);
            chkStorage.setSelected(true);
        }
        else {
            chkInstitution.setSelected(false);
            chkVehicle.setSelected(false);
            chkReceipt.setSelected(false);
            chkPayment.setSelected(false);
            chkAccount.setSelected(false);
            chkDriverInfo.setSelected(false);
            chkStaff.setSelected(false);
            chkStorage.setSelected(false);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		miZJGL = new JMenuItem();
		miTJBB = new JMenuItem();
		miZHGL = new JMenuItem();
		miQCJZ = new JMenuItem();
		miChange = new JMenuItem();
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
		label17 = new JLabel();
		label18 = new JLabel();
		label19 = new JLabel();
		pnZJGL = new JPanel();
		tabbedPane4 = new JTabbedPane();
		panel1 = new JPanel();
		btAddP = new JButton();
		scrollPane15 = new JScrollPane();
		tablePayment = new JTable();
		btPExcel = new JButton();
		panel3 = new JPanel();
		scrollPane16 = new JScrollPane();
		tableReceipt = new JTable();
		btDate = new JButton();
		btAddress = new JButton();
		textDate = new JTextField();
		textAddress = new JTextField();
		label15 = new JLabel();
		lbTotal = new JLabel();
		btRExcel = new JButton();
		lbAll = new JLabel();
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
		scrollPane19 = new JScrollPane();
		tablePay = new JTable();
		scrollPane18 = new JScrollPane();
		tableRec = new JTable();
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
		panel6 = new JPanel();
		chkAccount = new JCheckBox();
		chkInstitution = new JCheckBox();
		chkVehicle = new JCheckBox();
		chkDriverInfo = new JCheckBox();
		chkPayment = new JCheckBox();
		chkReceipt = new JCheckBox();
		chkStaff = new JCheckBox();
		chkStorage = new JCheckBox();
		chkAll = new JCheckBox();
		btAccountInit = new JButton();
		pnZHGL = new JPanel();
		button7 = new JButton();
		btAdd = new JButton();
		btModify = new JButton();
		button8 = new JButton();
		textName = new JTextField();
		btSearch = new JButton();
		label6 = new JLabel();
		btDelete = new JButton();
		label7 = new JLabel();
		textName2 = new JTextField();
		tabbedPane3 = new JTabbedPane();
		scrollPane13 = new JScrollPane();
		tableAccounts = new JTable();
		btAExcel = new JButton();
		panel5 = new JPanel();
		btAddP2 = new JButton();
		scrollPane20 = new JScrollPane();
		tablePayment2 = new JTable();
		btPExcel2 = new JButton();
		tabbedPane6 = new JTabbedPane();
		panel7 = new JPanel();
		btAddP3 = new JButton();
		scrollPane21 = new JScrollPane();
		tablePayment3 = new JTable();
		btPExcel3 = new JButton();
		panel8 = new JPanel();
		scrollPane22 = new JScrollPane();
		tableReceipt2 = new JTable();
		btDate2 = new JButton();
		btAddress2 = new JButton();
		textDate2 = new JTextField();
		textAddress2 = new JTextField();
		label16 = new JLabel();
		lbTotal2 = new JLabel();
		btRExcel2 = new JButton();
		lbAll2 = new JLabel();

		//======== this ========
		setTitle("\u8d22\u52a1\u7ba1\u7406");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				thisWindowOpened(e);
			}
		});
		Container contentPane = getContentPane();

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("\u9009\u9879(O)");
				menu1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- miZJGL ----
				miZJGL.setText("\u8d44\u91d1\u7ba1\u7406");
				miZJGL.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				miZJGL.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						miZJGLMouseReleased(e);
					}
				});
				menu1.add(miZJGL);

				//---- miTJBB ----
				miTJBB.setText("\u7edf\u8ba1\u62a5\u8868");
				miTJBB.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				miTJBB.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						miTJBBMouseReleased(e);
					}
				});
				menu1.add(miTJBB);

				//---- miZHGL ----
				miZHGL.setText("\u8d26\u6237\u7ba1\u7406");
				miZHGL.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				miZHGL.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						miZHGLMouseReleased(e);
					}
				});
				menu1.add(miZHGL);

				//---- miQCJZ ----
				miQCJZ.setText("\u671f\u521d\u5efa\u8d26");
				miQCJZ.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				miQCJZ.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						miQCJZMouseReleased(e);
					}
				});
				menu1.add(miQCJZ);

				//---- miChange ----
				miChange.setText("\u4fee\u6539\u5bc6\u7801");
				miChange.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				menu1.add(miChange);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("\u5e2e\u52a9(H)");
				menu2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- menuItem3 ----
				menuItem3.setText("\u5173\u4e8e\u6211\u4eec");
				menuItem3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
			tgQCJZ.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			tgQCJZ.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgQCJZMouseClicked(e);
				}
			});

			//---- label4 ----
			label4.setText("\u671f\u521d\u5efa\u8d26");
			label4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- tgTJBB ----
			tgTJBB.setIcon(new ImageIcon(getClass().getResource("/icons/form_72x72.png")));
			tgTJBB.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			tgTJBB.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgTJBBMouseClicked(e);
				}
			});

			//---- label2 ----
			label2.setText("\u7edf\u8ba1\u62a5\u8868");
			label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- tgZHGL ----
			tgZHGL.setIcon(new ImageIcon(getClass().getResource("/icons/account_72x72.png")));
			tgZHGL.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			tgZHGL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgZHGLMouseClicked(e);
				}
			});

			//---- label3 ----
			label3.setText("\u8d26\u6237\u7ba1\u7406");
			label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- tgZJGL ----
			tgZJGL.setIcon(new ImageIcon(getClass().getResource("/icons/money_72x72.png")));
			tgZJGL.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			tgZJGL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tgZJGLMouseClicked(e);
				}
			});

			//---- label1 ----
			label1.setText("\u8d44\u91d1\u7ba1\u7406");
			label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== panelMain ========
			{
				panelMain.setLayout(new BorderLayout());
			}

			//---- label17 ----
			label17.setText("text");
			label17.setIcon(new ImageIcon(getClass().getResource("/icons/Telemarketer_Woman_72px_1185181_easyicon.net.png")));

			//---- label18 ----
			label18.setText("\u59d3\u540d\uff1a\u738b\u5349");
			label18.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

			//---- label19 ----
			label19.setText("ID:10004");
			label19.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

			GroupLayout panel2Layout = new GroupLayout(panel2);
			panel2.setLayout(panel2Layout);
			panel2Layout.setHorizontalGroup(
				panel2Layout.createParallelGroup()
					.addGroup(panel2Layout.createSequentialGroup()
						.addGroup(panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(tgZJGL))
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(37, 37, 37)
								.addComponent(label1)))
						.addGroup(panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addComponent(tgZHGL)
								.addGap(12, 12, 12))
							.addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label3)
								.addGap(35, 35, 35)))
						.addGroup(panel2Layout.createParallelGroup()
							.addComponent(tgTJBB)
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addComponent(label2)))
						.addGroup(panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addComponent(tgQCJZ)
								.addGap(117, 117, 117)
								.addComponent(label17, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(panel2Layout.createParallelGroup()
									.addComponent(label18)
									.addComponent(label19)))
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(41, 41, 41)
								.addComponent(label4)))
						.addContainerGap(76, Short.MAX_VALUE))
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
							.addComponent(tgQCJZ, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(panel2Layout.createSequentialGroup()
									.addComponent(label19)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(label18))
								.addComponent(label17, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel2Layout.createParallelGroup()
							.addComponent(label1)
							.addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(label2)
								.addComponent(label4)))
						.addGap(7, 7, 7)
						.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
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

				//======== panel1 ========
				{

					//---- btAddP ----
					btAddP.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
					btAddP.setText("\u65b0\u5efa");
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
					btPExcel.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
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
								.addContainerGap()
								.addComponent(scrollPane15, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panel1Layout.createParallelGroup()
									.addComponent(btAddP, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
									.addComponent(btPExcel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
								.addGap(8, 8, 8))
					);
					panel1Layout.setVerticalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(panel1Layout.createParallelGroup()
									.addGroup(panel1Layout.createSequentialGroup()
										.addComponent(btAddP)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btPExcel)
										.addGap(0, 175, Short.MAX_VALUE))
									.addComponent(scrollPane15, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
								.addContainerGap())
					);
				}
				tabbedPane4.addTab("\u4ed8\u6b3e\u5355", panel1);

				//======== panel3 ========
				{

					//======== scrollPane16 ========
					{
						scrollPane16.setViewportView(tableReceipt);
					}

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

					//---- label15 ----
					label15.setText(" \u5982\uff1a2015/11/27");

					//---- lbTotal ----
					lbTotal.setText("text");

					//---- btRExcel ----
					btRExcel.setText("\u5bfc\u51fa");
					btRExcel.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
					btRExcel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							btRExcelMouseReleased(e);
						}
					});

					//---- lbAll ----
					lbAll.setText("\u5408\u8ba1\uff1a");

					GroupLayout panel3Layout = new GroupLayout(panel3);
					panel3.setLayout(panel3Layout);
					panel3Layout.setHorizontalGroup(
						panel3Layout.createParallelGroup()
							.addGroup(panel3Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane16, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(textDate, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addComponent(btDate, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addComponent(label15, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addComponent(btAddress, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addGroup(panel3Layout.createSequentialGroup()
										.addComponent(lbAll)
										.addGap(9, 9, 9)
										.addComponent(lbTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(btRExcel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
					);
					panel3Layout.setVerticalGroup(
						panel3Layout.createParallelGroup()
							.addGroup(panel3Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(panel3Layout.createParallelGroup()
									.addComponent(scrollPane16, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
									.addGroup(panel3Layout.createSequentialGroup()
										.addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(btDate)
										.addGap(7, 7, 7)
										.addComponent(label15, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGap(9, 9, 9)
										.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(btAddress)
										.addGap(12, 12, 12)
										.addGroup(panel3Layout.createParallelGroup()
											.addComponent(lbAll, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
											.addGroup(panel3Layout.createSequentialGroup()
												.addGap(5, 5, 5)
												.addComponent(lbTotal)))
										.addGap(6, 6, 6)
										.addComponent(btRExcel)
										.addContainerGap(46, Short.MAX_VALUE))))
					);
				}
				tabbedPane4.addTab("\u6536\u6b3e\u5355", panel3);
			}

			GroupLayout pnZJGLLayout = new GroupLayout(pnZJGL);
			pnZJGL.setLayout(pnZJGLLayout);
			pnZJGLLayout.setHorizontalGroup(
				pnZJGLLayout.createParallelGroup()
					.addComponent(tabbedPane4)
			);
			pnZJGLLayout.setVerticalGroup(
				pnZJGLLayout.createParallelGroup()
					.addComponent(tabbedPane4)
			);
		}

		//======== pnTJBB ========
		{

			//======== tpCost ========
			{
				tpCost.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
						label5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- textBeginYear ----
						textBeginYear.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label9 ----
						label9.setText("\u7ed3\u675f\u65e5\u671f");
						label9.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- textEndYear ----
						textEndYear.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- btSearch2 ----
						btSearch2.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
						btSearch2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
								tabbedPane5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

								//======== scrollPane19 ========
								{

									//---- tablePay ----
									tablePay.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
									scrollPane19.setViewportView(tablePay);
								}
								tabbedPane5.addTab("\u4ed8\u6b3e\u5355", scrollPane19);

								//======== scrollPane18 ========
								{
									scrollPane18.setViewportView(tableRec);
								}
								tabbedPane5.addTab("\u6536\u6b3e\u5355", scrollPane18);
							}
							scrollPane17.setViewportView(tabbedPane5);
						}

						//---- textBeginMonth ----
						textBeginMonth.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label8 ----
						label8.setText("\u5e74");
						label8.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label10 ----
						label10.setText("\u6708");
						label10.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- textBeginDay ----
						textBeginDay.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label11 ----
						label11.setText("\u65e5");
						label11.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label12 ----
						label12.setText("\u5e74");
						label12.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- textEndMonth ----
						textEndMonth.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- textEndDay ----
						textEndDay.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label13 ----
						label13.setText("\u6708");
						label13.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- label14 ----
						label14.setText("\u65e5");
						label14.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

						//---- jbExcel ----
						jbExcel.setText("\u5bfc\u51fa");
						jbExcel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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
									.addGap(40, 40, 40)
									.addComponent(jbExcel)
									.addGap(0, 309, Short.MAX_VALUE))
								.addGroup(panel4Layout.createSequentialGroup()
									.addComponent(scrollPane17, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
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
										.addComponent(jbExcel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(scrollPane17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, Short.MAX_VALUE))
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
					.addGroup(pnTJBBLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tpCost, GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
						.addContainerGap())
			);
			pnTJBBLayout.setVerticalGroup(
				pnTJBBLayout.createParallelGroup()
					.addGroup(pnTJBBLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tpCost, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
						.addContainerGap())
			);
		}

		//======== pnQCJZ ========
		{

			//======== panel6 ========
			{

				//---- chkAccount ----
				chkAccount.setText("\u8d26\u6237\u4fe1\u606f\u8868");
				chkAccount.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkInstitution ----
				chkInstitution.setText("\u673a\u6784\u8868");
				chkInstitution.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkVehicle ----
				chkVehicle.setText("\u8f66\u8f86\u4fe1\u606f\u8868");
				chkVehicle.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkDriverInfo ----
				chkDriverInfo.setText("\u53f8\u673a\u4fe1\u606f\u8868");
				chkDriverInfo.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkPayment ----
				chkPayment.setText("\u4ed8\u6b3e\u5355");
				chkPayment.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkReceipt ----
				chkReceipt.setText("\u6536\u6b3e\u5355");
				chkReceipt.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkStaff ----
				chkStaff.setText("\u4eba\u5458\u8868");
				chkStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkStorage ----
				chkStorage.setText("\u5e93\u5b58\u4fe1\u606f\u8868");
				chkStorage.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//---- chkAll ----
				chkAll.setText("\u5168\u9009");
				chkAll.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				chkAll.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						chkAllMouseReleased(e);
					}
				});

				GroupLayout panel6Layout = new GroupLayout(panel6);
				panel6.setLayout(panel6Layout);
				panel6Layout.setHorizontalGroup(
					panel6Layout.createParallelGroup()
						.addGroup(panel6Layout.createSequentialGroup()
							.addGroup(panel6Layout.createParallelGroup()
								.addComponent(chkInstitution)
								.addComponent(chkVehicle)
								.addComponent(chkDriverInfo)
								.addComponent(chkPayment)
								.addComponent(chkReceipt)
								.addComponent(chkAccount)
								.addComponent(chkStaff)
								.addComponent(chkStorage)
								.addComponent(chkAll))
							.addGap(0, 548, Short.MAX_VALUE))
				);
				panel6Layout.setVerticalGroup(
					panel6Layout.createParallelGroup()
						.addGroup(panel6Layout.createSequentialGroup()
							.addComponent(chkInstitution)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkVehicle)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkDriverInfo)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkPayment)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkReceipt)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkAccount)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkStaff)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkStorage)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(chkAll)
							.addGap(0, 19, Short.MAX_VALUE))
				);
			}

			//---- btAccountInit ----
			btAccountInit.setText("\u671f\u521d\u5efa\u8d26");
			btAccountInit.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btAccountInit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btAccountInitMouseReleased(e);
				}
			});

			GroupLayout pnQCJZLayout = new GroupLayout(pnQCJZ);
			pnQCJZ.setLayout(pnQCJZLayout);
			pnQCJZLayout.setHorizontalGroup(
				pnQCJZLayout.createParallelGroup()
					.addGroup(pnQCJZLayout.createSequentialGroup()
						.addGap(9, 9, 9)
						.addComponent(panel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btAccountInit, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			pnQCJZLayout.setVerticalGroup(
				pnQCJZLayout.createParallelGroup()
					.addGroup(pnQCJZLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pnQCJZLayout.createParallelGroup()
							.addComponent(panel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(pnQCJZLayout.createSequentialGroup()
								.addComponent(btAccountInit)
								.addGap(0, 251, Short.MAX_VALUE)))
						.addContainerGap())
			);
		}

		//======== pnZHGL ========
		{

			//---- button7 ----
			button7.setText("\u67e5\u770b");

			//---- btAdd ----
			btAdd.setText("\u6dfb\u52a0");
			btAdd.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
			btAdd.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btAddMouseReleased(e);
				}
			});

			//---- btModify ----
			btModify.setText("\u4fee\u6539");
			btModify.setIcon(new ImageIcon(getClass().getResource("/icons/modify_24x24.png")));
			btModify.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btModify.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btModifyMouseReleased(e);
				}
			});

			//---- button8 ----
			button8.setText("\u5220\u9664");

			//---- textName ----
			textName.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- btSearch ----
			btSearch.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
			btSearch.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btSearchMouseReleased(e);
				}
			});

			//---- label6 ----
			label6.setText("\u8d26\u6237\u540d\u79f0\uff1a");
			label6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- btDelete ----
			btDelete.setText("\u5220\u9664");
			btDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24x24.png")));
			btDelete.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					button11MouseReleased(e);
				}
			});

			//---- label7 ----
			label7.setText("\u8d26\u6237\u4f59\u989d\uff1a");
			label7.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//---- textName2 ----
			textName2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== tabbedPane3 ========
			{
				tabbedPane3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

				//======== scrollPane13 ========
				{

					//---- tableAccounts ----
					tableAccounts.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane13.setViewportView(tableAccounts);
				}
				tabbedPane3.addTab("\u8d26\u6237\u4f59\u989d", scrollPane13);
			}

			//---- btAExcel ----
			btAExcel.setText("\u5bfc\u51fa");
			btAExcel.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
			btAExcel.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btAExcel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btAExcelMouseReleased(e);
				}
			});

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
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(tabbedPane3, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
						.addGroup(pnZHGLLayout.createParallelGroup()
							.addGroup(pnZHGLLayout.createParallelGroup()
								.addGroup(pnZHGLLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(btAdd, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
									.addComponent(btModify, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btDelete, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addComponent(btAExcel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			pnZHGLLayout.setVerticalGroup(
				pnZHGLLayout.createParallelGroup()
					.addGroup(pnZHGLLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pnZHGLLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
							.addComponent(label6)
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textName2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnZHGLLayout.createParallelGroup()
							.addGroup(pnZHGLLayout.createSequentialGroup()
								.addComponent(btAdd)
								.addGap(45, 45, 45)
								.addComponent(btModify)
								.addGap(42, 42, 42)
								.addComponent(btDelete)
								.addGap(38, 38, 38)
								.addComponent(btAExcel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE))
							.addComponent(tabbedPane3, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
						.addContainerGap())
			);
		}

		//======== panel5 ========
		{

			//---- btAddP2 ----
			btAddP2.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
			btAddP2.setText("\u65b0\u5efa");
			btAddP2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btAddP2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					button1MouseReleased(e);
				}
			});

			//======== scrollPane20 ========
			{

				//---- tablePayment2 ----
				tablePayment2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				scrollPane20.setViewportView(tablePayment2);
			}

			//---- btPExcel2 ----
			btPExcel2.setText("\u5bfc\u51fa");
			btPExcel2.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
			btPExcel2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
			btPExcel2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					btPExcelMouseReleased(e);
				}
			});

			GroupLayout panel5Layout = new GroupLayout(panel5);
			panel5.setLayout(panel5Layout);
			panel5Layout.setHorizontalGroup(
				panel5Layout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane20, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panel5Layout.createParallelGroup()
							.addComponent(btAddP2, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addComponent(btPExcel2, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addContainerGap())
			);
			panel5Layout.setVerticalGroup(
				panel5Layout.createParallelGroup()
					.addGroup(panel5Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel5Layout.createParallelGroup()
							.addComponent(scrollPane20, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.addGroup(panel5Layout.createSequentialGroup()
								.addComponent(btAddP2)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btPExcel2)
								.addContainerGap(213, Short.MAX_VALUE))))
			);
		}

		//======== tabbedPane6 ========
		{
			tabbedPane6.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

			//======== panel7 ========
			{

				//---- btAddP3 ----
				btAddP3.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
				btAddP3.setText("\u65b0\u5efa");
				btAddP3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				btAddP3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						button1MouseReleased(e);
					}
				});

				//======== scrollPane21 ========
				{

					//---- tablePayment3 ----
					tablePayment3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
					scrollPane21.setViewportView(tablePayment3);
				}

				//---- btPExcel3 ----
				btPExcel3.setText("\u5bfc\u51fa");
				btPExcel3.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
				btPExcel3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
				btPExcel3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						btPExcelMouseReleased(e);
					}
				});

				GroupLayout panel7Layout = new GroupLayout(panel7);
				panel7.setLayout(panel7Layout);
				panel7Layout.setHorizontalGroup(
					panel7Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane21, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(btPExcel3, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(btAddP3, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addGap(8, 8, 8))
				);
				panel7Layout.setVerticalGroup(
					panel7Layout.createParallelGroup()
						.addGroup(panel7Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel7Layout.createParallelGroup()
								.addGroup(panel7Layout.createSequentialGroup()
									.addComponent(btAddP3)
									.addGap(12, 12, 12)
									.addComponent(btPExcel3)
									.addGap(0, 158, Short.MAX_VALUE))
								.addComponent(scrollPane21, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
							.addContainerGap())
				);
			}
			tabbedPane6.addTab("\u4ed8\u6b3e\u5355", panel7);

			//======== panel8 ========
			{

				//======== scrollPane22 ========
				{
					scrollPane22.setViewportView(tableReceipt2);
				}

				//---- btDate2 ----
				btDate2.setText("\u6309\u5929\u67e5\u770b");
				btDate2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						btDateMouseReleased(e);
					}
				});

				//---- btAddress2 ----
				btAddress2.setText("\u6309\u8425\u4e1a\u5385\u67e5\u770b");
				btAddress2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						btAddressMouseReleased(e);
					}
				});

				//---- label16 ----
				label16.setText(" \u5982\uff1a2015/11/27");

				//---- lbTotal2 ----
				lbTotal2.setText("text");

				//---- btRExcel2 ----
				btRExcel2.setText("\u5bfc\u51fa");
				btRExcel2.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
				btRExcel2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						btRExcelMouseReleased(e);
					}
				});

				//---- lbAll2 ----
				lbAll2.setText("\u5408\u8ba1\uff1a");

				GroupLayout panel8Layout = new GroupLayout(panel8);
				panel8.setLayout(panel8Layout);
				panel8Layout.setHorizontalGroup(
					panel8Layout.createParallelGroup()
						.addGroup(panel8Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane22, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(panel8Layout.createParallelGroup()
								.addComponent(textDate2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(btDate2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(label16, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(textAddress2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(btAddress2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addGroup(panel8Layout.createSequentialGroup()
									.addComponent(lbAll2)
									.addGap(9, 9, 9)
									.addComponent(lbTotal2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addComponent(btRExcel2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				panel8Layout.setVerticalGroup(
					panel8Layout.createParallelGroup()
						.addGroup(panel8Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(panel8Layout.createParallelGroup()
								.addComponent(scrollPane22, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
								.addGroup(panel8Layout.createSequentialGroup()
									.addComponent(textDate2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5, 5, 5)
									.addComponent(btDate2)
									.addGap(7, 7, 7)
									.addComponent(label16, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(9, 9, 9)
									.addComponent(textAddress2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5, 5, 5)
									.addComponent(btAddress2)
									.addGap(12, 12, 12)
									.addGroup(panel8Layout.createParallelGroup()
										.addComponent(lbAll2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addGroup(panel8Layout.createSequentialGroup()
											.addGap(5, 5, 5)
											.addComponent(lbTotal2)))
									.addGap(6, 6, 6)
									.addComponent(btRExcel2)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				);
			}
			tabbedPane6.addTab("\u6536\u6b3e\u5355", panel8);
		}
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        String names[] = {"收款日期", "收款单位", "收款人", "收款方", "收款金额", "收款地点"};
        for (int i = 0; i < names.length; i++) {
            tableReceipt.addColumn(new TableColumn(i));
            tableReceipt.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }
        tableReceipt.setRowHeight(50);

        String names1[] = {"付款日期", "付款金额", "付款人", "付款账号", "条目", "备注"};
        for (int i = 0; i < names1.length; i++) {
            tablePayment.addColumn(new TableColumn(i));
            tablePayment.getColumnModel().getColumn(i).setHeaderValue(names1[i]);
        }
        tablePayment.setRowHeight(50);


        String names3[] = {"总收入", "总支出", "总收益"};
        for (int i = 0; i < names3.length; i++) {
            tableBenefit.addColumn(new TableColumn(i));
            tableBenefit.getColumnModel().getColumn(i).setHeaderValue(names3[i]);
        }
        tableBenefit.setRowHeight(50);

        String names5[] = {"ID", "账户名称", "账户余额"};
        for (int i = 0; i < names5.length; i++) {
            tableAccounts.addColumn(new TableColumn(i));
            tableAccounts.getColumnModel().getColumn(i).setHeaderValue(names5[i]);
        }
        tableAccounts.setRowHeight(50);

        String names6[] = {"收款日期", "收款单位", "收款人", "收款方", "收款金额", "收款地点"};
        for (int i = 0; i < names6.length; i++) {
            tableRec.addColumn(new TableColumn(i));
            tableRec.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }
        tableRec.setRowHeight(50);

        String names7[] = {"付款日期", "付款金额", "付款人", "付款账号", "条目", "备注"};
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
	private JMenuItem miZJGL;
	private JMenuItem miTJBB;
	private JMenuItem miZHGL;
	private JMenuItem miQCJZ;
	private JMenuItem miChange;
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
	private JLabel label17;
	private JLabel label18;
	private JLabel label19;
	private JPanel pnZJGL;
	private JTabbedPane tabbedPane4;
	private JPanel panel1;
	private JButton btAddP;
	private JScrollPane scrollPane15;
	private JTable tablePayment;
	private JButton btPExcel;
	private JPanel panel3;
	private JScrollPane scrollPane16;
	private JTable tableReceipt;
	private JButton btDate;
	private JButton btAddress;
	private JTextField textDate;
	private JTextField textAddress;
	private JLabel label15;
	private JLabel lbTotal;
	private JButton btRExcel;
	private JLabel lbAll;
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
	private JScrollPane scrollPane19;
	private JTable tablePay;
	private JScrollPane scrollPane18;
	private JTable tableRec;
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
	private JPanel panel6;
	private JCheckBox chkAccount;
	private JCheckBox chkInstitution;
	private JCheckBox chkVehicle;
	private JCheckBox chkDriverInfo;
	private JCheckBox chkPayment;
	private JCheckBox chkReceipt;
	private JCheckBox chkStaff;
	private JCheckBox chkStorage;
	private JCheckBox chkAll;
	private JButton btAccountInit;
	private JPanel pnZHGL;
	private JButton button7;
	private JButton btAdd;
	private JButton btModify;
	private JButton button8;
	private JTextField textName;
	private JButton btSearch;
	private JLabel label6;
	private JButton btDelete;
	private JLabel label7;
	private JTextField textName2;
	private JTabbedPane tabbedPane3;
	private JScrollPane scrollPane13;
	private JTable tableAccounts;
	private JButton btAExcel;
	private JPanel panel5;
	private JButton btAddP2;
	private JScrollPane scrollPane20;
	private JTable tablePayment2;
	private JButton btPExcel2;
	private JTabbedPane tabbedPane6;
	private JPanel panel7;
	private JButton btAddP3;
	private JScrollPane scrollPane21;
	private JTable tablePayment3;
	private JButton btPExcel3;
	private JPanel panel8;
	private JScrollPane scrollPane22;
	private JTable tableReceipt2;
	private JButton btDate2;
	private JButton btAddress2;
	private JTextField textDate2;
	private JTextField textAddress2;
	private JLabel label16;
	private JLabel lbTotal2;
	private JButton btRExcel2;
	private JLabel lbAll2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
