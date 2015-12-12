package presentation.financial;

/*
 * Created by JFormDesigner on Sat Oct 31 17:11:41 CST 2015
 */

import businesslogic.impl.company.CompanyBLController;
import businesslogic.impl.financialbl.AccountManage;
import businesslogic.impl.financialbl.FinancialBLController;
import businesslogic.service.Financial.FinancialBLService;
import data.enums.POType;
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


        ArrayList<AccountVO> accounts = financialBL.searchAllAccounts();
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
        ArrayList<AccountVO> accounts = financialBL.searchAllAccounts();
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

        if (textDate.getText().charAt(4) != '\\' || textDate.getText().charAt(7) != '\\') {
            JOptionPane.showMessageDialog(this, "日期之间请用'\'隔开");
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
        lbTotal = new JLabel(num + "元");
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

        tableRec.updateUI();
        tableRec.repaint();

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
        tablePay.updateUI();
        tablePay.repaint();

    }


    //TJBB 对成本收益表的查看
    private void tpCostMouseReleased(MouseEvent e) {

        CostBenefitVO costBenefitVO = companyBLcontroller.searchCostBenefitVO();

        Vector data = ((DefaultTableModel) tableBenefit.getModel()).getDataVector();
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
        refresh();
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

    private void btAccountInitMouseReleased(MouseEvent e) {
        ArrayList<POType> typesToInit = new ArrayList<>();
        if (chkAccount.isSelected()) {
            typesToInit.add(POType.ACCOUNT);
        }
        if (chkDriverInfo.isSelected()) {
            typesToInit.add(POType.DRIVERINFO);
        }
        if (chkInstitution.isSelected()) {
            typesToInit.add(POType.INSTITUTION);
        }
        if (chkPayment.isSelected()) {
            typesToInit.add(POType.PAYMENT);
        }
        if (chkReceipt.isSelected()) {
            typesToInit.add(POType.RECEIPT);
        }
        if (chkStorageIn.isSelected()) {
            typesToInit.add(POType.STORAGEINLIST);
        }
        if (chkStorageOut.isSelected()) {
            typesToInit.add(POType.STORAGEOUTLIST);
        }
        if (chkVehicle.isSelected()) {
            typesToInit.add(POType.VEHICLEINFO);
        }
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
        // TODO!!!!
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
        chkStorageIn = new JCheckBox();
        chkStorageOut = new JCheckBox();
        chkPayment = new JCheckBox();
        chkReceipt = new JCheckBox();
        checkBox9 = new JCheckBox();
        labelInstitution = new JLabel();
        labelVehicle = new JLabel();
        labelDriverInfo = new JLabel();
        labelStorageIn = new JLabel();
        labelStorageOut = new JLabel();
        labelPayment = new JLabel();
        labelReceipt = new JLabel();
        labelAccount = new JLabel();
        labelStaff = new JLabel();
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
        panel5 = new JPanel();
        btAddP2 = new JButton();
        scrollPane20 = new JScrollPane();
        tablePayment2 = new JTable();
        btPExcel2 = new JButton();

        //======== this ========
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
                                .addGap(75, 75, 75)
                                .addComponent(label4)))
                        .addContainerGap(327, Short.MAX_VALUE))
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
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(label3))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(7, 7, 7)))
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
                                .addComponent(scrollPane15, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btPExcel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(btAddP, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addGap(8, 8, 8))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btAddP)
                                        .addGap(12, 12, 12)
                                        .addComponent(btPExcel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(scrollPane15, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
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
                                .addComponent(scrollPane16, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(textDate, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btDate, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label15, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textAddress, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btAddress, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addComponent(lbAll)
                                        .addGap(9, 9, 9)
                                        .addComponent(lbTotal, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
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
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    );
                }
                tabbedPane4.addTab("\u6536\u6b3e\u5355", panel3);
            }

            GroupLayout pnZJGLLayout = new GroupLayout(pnZJGL);
            pnZJGL.setLayout(pnZJGLLayout);
            pnZJGLLayout.setHorizontalGroup(
                pnZJGLLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, pnZJGLLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPane4)
                        .addContainerGap())
            );
            pnZJGLLayout.setVerticalGroup(
                pnZJGLLayout.createParallelGroup()
                    .addGroup(pnZJGLLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPane4)
                        .addGap(5, 5, 5))
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

                                //======== scrollPane19 ========
                                {
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
                                    .addGap(40, 40, 40)
                                    .addComponent(jbExcel)
                                    .addGap(0, 152, Short.MAX_VALUE))
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addComponent(scrollPane17, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
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
                        .addComponent(tpCost)
                        .addContainerGap())
            );
            pnTJBBLayout.setVerticalGroup(
                pnTJBBLayout.createParallelGroup()
                    .addGroup(pnTJBBLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tpCost, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== pnQCJZ ========
        {

            //======== panel6 ========
            {

                //---- chkAccount ----
                chkAccount.setText("\u8d26\u6237\u4fe1\u606f\u8868");

                //---- chkInstitution ----
                chkInstitution.setText("\u673a\u6784\u8868");

                //---- chkVehicle ----
                chkVehicle.setText("\u8f66\u8f86\u4fe1\u606f\u8868");

                //---- chkDriverInfo ----
                chkDriverInfo.setText("\u53f8\u673a\u4fe1\u606f\u8868");

                //---- chkStorageIn ----
                chkStorageIn.setText("\u5165\u5e93\u5355");

                //---- chkStorageOut ----
                chkStorageOut.setText("\u51fa\u5e93\u5355");

                //---- chkPayment ----
                chkPayment.setText("\u4ed8\u6b3e\u5355");

                //---- chkReceipt ----
                chkReceipt.setText("\u6536\u6b3e\u5355");

                //---- checkBox9 ----
                checkBox9.setText("\u4eba\u5458\u8868");

                //---- labelInstitution ----
                labelInstitution.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelVehicle ----
                labelVehicle.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelDriverInfo ----
                labelDriverInfo.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelStorageIn ----
                labelStorageIn.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelStorageOut ----
                labelStorageOut.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelPayment ----
                labelPayment.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelReceipt ----
                labelReceipt.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelAccount ----
                labelAccount.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                //---- labelStaff ----
                labelStaff.setText("\u5c1a\u672a\u8fdb\u884c\u8fc7\u671f\u521d\u5efa\u8d26");

                GroupLayout panel6Layout = new GroupLayout(panel6);
                panel6.setLayout(panel6Layout);
                panel6Layout.setHorizontalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(panel6Layout.createSequentialGroup()
                            .addGroup(panel6Layout.createParallelGroup()
                                .addComponent(chkInstitution)
                                .addComponent(chkVehicle)
                                .addComponent(chkDriverInfo)
                                .addComponent(chkStorageIn)
                                .addComponent(chkStorageOut)
                                .addComponent(chkPayment)
                                .addComponent(chkReceipt)
                                .addComponent(chkAccount)
                                .addComponent(checkBox9))
                            .addGap(42, 42, 42)
                            .addGroup(panel6Layout.createParallelGroup()
                                .addComponent(labelStaff)
                                .addComponent(labelAccount)
                                .addComponent(labelReceipt)
                                .addComponent(labelPayment)
                                .addComponent(labelStorageOut)
                                .addComponent(labelStorageIn)
                                .addComponent(labelDriverInfo)
                                .addComponent(labelVehicle)
                                .addComponent(labelInstitution))
                            .addGap(0, 202, Short.MAX_VALUE))
                );
                panel6Layout.setVerticalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(panel6Layout.createSequentialGroup()
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkInstitution)
                                .addComponent(labelInstitution))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkVehicle)
                                .addComponent(labelVehicle))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkDriverInfo)
                                .addComponent(labelDriverInfo))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkStorageIn)
                                .addComponent(labelStorageIn))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkStorageOut)
                                .addComponent(labelStorageOut))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkPayment)
                                .addComponent(labelPayment))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkReceipt)
                                .addComponent(labelReceipt))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(chkAccount)
                                .addComponent(labelAccount))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(checkBox9)
                                .addComponent(labelStaff))
                            .addGap(0, 39, Short.MAX_VALUE))
                );
            }

            //---- btAccountInit ----
            btAccountInit.setText("\u671f\u521d\u5efa\u8d26");
            btAccountInit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    button1MouseReleased(e);
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
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btAccountInit)
                        .addContainerGap())
            );
            pnQCJZLayout.setVerticalGroup(
                pnQCJZLayout.createParallelGroup()
                    .addGroup(pnQCJZLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnQCJZLayout.createParallelGroup()
                            .addGroup(pnQCJZLayout.createSequentialGroup()
                                .addComponent(btAccountInit)
                                .addGap(0, 270, Short.MAX_VALUE))
                            .addComponent(panel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            btAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btAddMouseReleased(e);
                }
            });

            //---- btModify ----
            btModify.setText("\u4fee\u6539");
            btModify.setIcon(new ImageIcon(getClass().getResource("/icons/modify_24x24.png")));
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
                }
            });

            //---- label6 ----
            label6.setText("\u8d26\u6237\u540d\u79f0\uff1a");

            //---- btDelete ----
            btDelete.setText("\u5220\u9664");
            btDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24x24.png")));
            btDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    button11MouseReleased(e);
                }
            });

            //---- label7 ----
            label7.setText("\u8d26\u6237\u4f59\u989d\uff1a");

            //======== tabbedPane3 ========
            {

                //======== scrollPane13 ========
                {
                    scrollPane13.setViewportView(tableAccounts);
                }
                tabbedPane3.addTab("\u8d26\u6237\u4f59\u989d", scrollPane13);
            }

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
                                .addComponent(textName2, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnZHGLLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabbedPane3, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnZHGLLayout.createParallelGroup()
                            .addComponent(btAdd, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDelete, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(btModify, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
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
                        .addGroup(pnZHGLLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(pnZHGLLayout.createSequentialGroup()
                                .addComponent(btAdd)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btModify)
                                .addGap(205, 205, 205)
                                .addComponent(btDelete))
                            .addComponent(tabbedPane3, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== panel5 ========
        {

            //---- btAddP2 ----
            btAddP2.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
            btAddP2.setText("\u65b0\u5efa");
            btAddP2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    button1MouseReleased(e);
                }
            });

            //======== scrollPane20 ========
            {
                scrollPane20.setViewportView(tablePayment2);
            }

            //---- btPExcel2 ----
            btPExcel2.setText("\u5bfc\u51fa");
            btPExcel2.setIcon(new ImageIcon(getClass().getResource("/icons/export_24x24.png")));
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
                        .addComponent(scrollPane20, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(btPExcel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(btAddP2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
            );
            panel5Layout.setVerticalGroup(
                panel5Layout.createParallelGroup()
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel5Layout.createParallelGroup()
                            .addComponent(scrollPane20, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(btAddP2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btPExcel2)
                                .addContainerGap(191, Short.MAX_VALUE))))
            );
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
    private JCheckBox chkStorageIn;
    private JCheckBox chkStorageOut;
    private JCheckBox chkPayment;
    private JCheckBox chkReceipt;
    private JCheckBox checkBox9;
    private JLabel labelInstitution;
    private JLabel labelVehicle;
    private JLabel labelDriverInfo;
    private JLabel labelStorageIn;
    private JLabel labelStorageOut;
    private JLabel labelPayment;
    private JLabel labelReceipt;
    private JLabel labelAccount;
    private JLabel labelStaff;
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
    private JPanel panel5;
    private JButton btAddP2;
    private JScrollPane scrollPane20;
    private JTable tablePayment2;
    private JButton btPExcel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
