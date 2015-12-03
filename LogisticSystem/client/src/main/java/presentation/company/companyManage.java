package presentation.company;

import businesslogic.impl.company.CompanyBLController;
import data.enums.ServiceType;
import data.enums.StockStatus;
import data.message.ResultMessage;
import data.po.*;
import data.vo.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


/**
 * @author wyc
 */
public class companyManage extends JFrame {

    public companyManage() {
        initComponents();
        this.setVisible(true);
    }

    //显示工资管理界面
    private void buttonSalaryMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(false);
        buttonCheck.setSelected(true);
        buttonStaff.setSelected(true);
        buttonApprove.setSelected(true);
        buttonCity.setSelected(true);
        panelSalary.setBounds(0,0,792,360);
        panelSalary.setVisible(true);
        panelAll.remove(panelCity);
        panelAll.remove(panelApprove);
        panelAll.remove(panelStaff);
        panelAll.add(panelSalary);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //显示城市管理界面
    private void buttonCityMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(true);
        buttonCheck.setSelected(true);
        buttonStaff.setSelected(true);
        buttonApprove.setSelected(true);
        buttonCity.setSelected(false);
        panelCity.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelStaff);
        panelAll.remove(panelApprove);
        panelAll.add(panelCity);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //显示审批单据界面
    private void buttonApproveMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(true);
        buttonCheck.setSelected(true);
        buttonStaff.setSelected(true);
        buttonApprove.setSelected(false);
        buttonCity.setSelected(true);
        panelApprove.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelStaff);
        panelAll.remove(panelCity);
        panelAll.add(panelApprove);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //显示查看统计报表界面
    private void buttonCheckMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    //显示员工机构管理界面
    private void buttonStaffMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(true);
        buttonCheck.setSelected(true);
        buttonStaff.setSelected(false);
        buttonApprove.setSelected(true);
        buttonCheck.setSelected(true);
        panelStaff.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelCity);
        panelAll.remove(panelApprove);
        panelAll.add(panelStaff);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //添加员工工资按钮
    private void buttonAddSalaryMouseClicked(MouseEvent e) {
        DialogAddSalary dialogAddSalary = new DialogAddSalary(this);
    }

    //修改员工工资按钮
    private void buttonModifySalaryMouseClicked(MouseEvent e) {
        //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
        int row = tableSalary.getSelectedRow();
        int column = tableSalary.getSelectedColumn();
        tableSalary.getCellEditor(row, column).stopCellEditing();
        for(int i=0;i<salaryModify.size();i++){
            //获取机构和工资的信息
            Iterator<String> info = salaryModify.get(i).iterator();
            ResultMessage resultmessage = controller.modifySalary(info.next(),info.next());
            if(resultmessage == ResultMessage.SUCCESS){
                labelSalarySuccess.setText("修改成功!");
            }
            else if(resultmessage == ResultMessage.NOTCONNECTED){
                labelSalarySuccess.setText("");
                JOptionPane.showMessageDialog(null,"网络错误...","",JComponent.ERROR);
            }
            else{
                labelSalarySuccess.setText("修改失败!");
            }
        }
    }

    //退出工资管理按钮
    private void buttonExitSalaryMouseClicked(MouseEvent e) {
        this.dispose();
    }

    //确认选择城市按钮,随后显示城市之间物流信息
    private void buttonEnsureCityMouseClicked(MouseEvent e) {
        //获取出发城市和到达城市
        String fromCity = (String) comboBoxLeaveCity.getSelectedItem();
        String toCity = (String) comboBoxArrivalCity.getSelectedItem();
      //  CityTransVO cityTransVO = controller.getCityTransInfo(fromCity,toCity);
        CityTransVO cityTransVO = new CityTransVO("1","2",1,2,3,4);
        this.initCityTable(cityTransVO);
    }

    //添加城市之间物流信息按钮
    private void buttonAddCityMouseClicked(MouseEvent e) {
        DialogAddCity dialogAddCity = new DialogAddCity(this);
    }

    //修改城市之间物流信息按钮
    private void buttonModifyCityMouseClicked(MouseEvent e) {
        //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
        int column = tableCity.getSelectedColumn();
        tableCity.getCellEditor(0,column).stopCellEditing();
        for(int i=0;i<cityModify.size();i++){
            Iterator<String> info = cityModify.get(i).iterator();
            String fromCity = info.next();
            String toCity = info.next();
            double distance = Double.valueOf(info.next());
            double trunkPrice = Double.valueOf(info.next());
            double trainPrice = Double.valueOf(info.next());
            double planePrice = Double.valueOf(info.next());
            CityTransVO cityTransVO = new CityTransVO(fromCity,toCity,distance,trunkPrice,trainPrice,planePrice);
            ResultMessage resultmessage = controller.modifyCityInfo(cityTransVO);
            if(resultmessage == ResultMessage.SUCCESS){
                labelCitySuccess.setText("修改成功!");
            }
            else if(resultmessage == ResultMessage.NOTEXIST){
                JOptionPane.showMessageDialog(null,"城市信息不存在","",JComponent.ERROR);
            }
            else if(controller.modifySalary(info.next(),info.next()) == ResultMessage.NOTCONNECTED){
                JOptionPane.showMessageDialog(null,"网络错误...","",JComponent.ERROR);
            }
            else{
                labelSalarySuccess.setText("修改失败!");
            }
        }
    }

    //退出城市物流信息管理按钮
    private void buttonExitCityMouseClicked(MouseEvent e) {
        this.dispose();
    }

    //添加员工按钮
    private void buttonAddStaffMouseClicked(MouseEvent e) {
        //根据机构的不同来选择不同的方式生成机构名称
        String institution,id;
        int index= tabbedPaneStaff.getSelectedIndex();
        if(index>=0&&index<=2){
            institution = tabbedPaneStaff.getTitleAt(index);
        }
        else if(index==3){
            institution = comboBoxCenter.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
        }
        else if(index==4){
            institution = comboBoxBusinessCity.getSelectedItem()+tabbedPaneStaff.getTitleAt(index)+comboBoxBusinessNum.getSelectedItem();
        }
        else if(index==5){
            institution = comboBoxStorage.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
        }
        else{
            institution = null;
            JOptionPane.showMessageDialog(null,"请选择机构","",JOptionPane.ERROR_MESSAGE);
        }
        //添加员工面板
        DialogAddStaff dialogAddstaff = new DialogAddStaff(this,institution);
    }

    //删除员工按钮
    private void buttonDeleteStaffMouseClicked(MouseEvent e) {
        //根据机构的不同来选择不同的方式生成机构名称
        String institution,id;
        int index= tabbedPaneStaff.getSelectedIndex();
        if(index==0){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) deliverModel.getValueAt(tableDeliver.getSelectedRow(),0);
        }
        else if(index==1){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) financialModel.getValueAt(tableFinancial.getSelectedRow(),0);
        }
        else if(index==2){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) trunkModel.getValueAt(tableTrunk.getSelectedRow(),0);
        }
        else if(index==3){
            institution = comboBoxCenter.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
            id = (String) centerModel.getValueAt(tableCenter.getSelectedRow(),0);
        }
        else if(index==4){
            institution = comboBoxBusinessCity.getSelectedItem()+tabbedPaneStaff.getTitleAt(index)+comboBoxBusinessNum.getSelectedItem();
            id = (String) businessModel.getValueAt(tableBusiness.getSelectedRow(),0);
        }
        else if(index==5){
            institution = comboBoxStorage.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
            id = (String) storageModel.getValueAt(tableStorage.getSelectedRow(),0);
        }
        else{
            institution = null;
            id = null;
            JOptionPane.showMessageDialog(null,"请选择机构","",JOptionPane.ERROR_MESSAGE);
        }
        //删除员工操作
        ResultMessage resultMessage = controller.deleteStaff(institution,id);
        if(resultMessage == ResultMessage.SUCCESS){
            labelStaffSuccess.setText("删除成功!");
            //重绘当前表格
            switch (tabbedPaneStaff.getSelectedIndex()){
                case 0:
                    initDeliverTable();
                    break;
                case 1:
                    initFinancialTable();
                    break;
                case 2:
                    initTrunkTable();
                    break;
                case 3:
                    initCenterTable(institution);
                    break;
                case 4:
                   initBusinessTable(institution);
                    break;
                case 5:
                    initStorageTable(institution);
                    break;
            }
        }
        else if(resultMessage == ResultMessage.NOTEXIST){
            labelStaffSuccess.setText("员工不存在!");
        }
        else if (resultMessage == ResultMessage.FAILED) {
            labelStaffSuccess.setText("删除失败");
        }
        else if (resultMessage == ResultMessage.NOTCONNECTED) {
            labelStaffSuccess.setText("");
            JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //移动员工机构按钮
    private void buttonMoveStaffMouseClicked(MouseEvent e) {
        //根据机构的不同来选择不同的方式生成机构名称,并且获取员工的id
        String institution,id;
        int index= tabbedPaneStaff.getSelectedIndex();
        if(index==0){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) deliverModel.getValueAt(tableDeliver.getSelectedRow(),0);
        }
        else if(index==1){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) financialModel.getValueAt(tableFinancial.getSelectedRow(),0);
        }
        else if(index==2){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) trunkModel.getValueAt(tableTrunk.getSelectedRow(),0);
        }
        else if(index==3){
            institution = comboBoxCenter.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
            id = (String) centerModel.getValueAt(tableCenter.getSelectedRow(),0);
        }
        else if(index==4){
            institution = comboBoxBusinessCity.getSelectedItem()+tabbedPaneStaff.getTitleAt(index)+comboBoxBusinessNum.getSelectedItem();
            id = (String) businessModel.getValueAt(tableBusiness.getSelectedRow(),0);
        }
        else if(index==5){
            institution = comboBoxStorage.getSelectedItem()+tabbedPaneStaff.getTitleAt(index);
            id = (String) storageModel.getValueAt(tableStorage.getSelectedRow(),0);
        }
        else {
            institution = null;
            id = null;
            JOptionPane.showMessageDialog(null, "请选择机构", "", JOptionPane.ERROR_MESSAGE);
        }
        //移动员工面板
        DialogMoveStaff dialogMoveStaff = new DialogMoveStaff(this,institution,id);
    }

    //确认选择仓库所在城市的按钮,随后显示该仓库的所有管理人员
    private void buttonEnsureStorageMouseClicked(MouseEvent e) {
        String city = (String) comboBoxStorage.getSelectedItem();
        this.initStorageTable(city+tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
    }

    //确认选择营业厅中转中心所在城市按钮,随后显示该营业厅的所有业务人员
    private void buttonEnsureBusinessMouseClicked(MouseEvent e) {
        String city = (String) comboBoxBusinessCity.getSelectedItem();
        String num = (String) comboBoxBusinessNum.getSelectedItem();
        this.initBusinessTable(city+tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex())+num);
    }

    //确认选择中转中心所在城市按钮,随后显示该中转中心的所有业务人员
    private void buttonEnsureCenterMouseClicked(MouseEvent e) {
       String city = (String) comboBoxCenter.getSelectedItem();
        this.initCenterTable(city+tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
    }

    //退出员工机构管理的按钮
    private void buttonExitStaffMouseClicked(MouseEvent e) {
        this.dispose();
    }

    //审批单条单据的按钮
    private void buttonApproveDataMouseClicked(MouseEvent e) {
        //单据的类型和单号
        String dataType,id;
        int index = tabbedPaneApprove.getSelectedIndex();
        dataType = tabbedPaneApprove.getTitleAt(index);
        ResultMessage resultMessage ;
        switch(index){
            case 0:
                id = (String) entrukModel.getValueAt(tableEntruk.getSelectedRow(),0);
                break;
            case 1:
                id = (String) arrivalModel.getValueAt(tableArrival.getSelectedRow(),0);
                break;
            case 2:
                id = (String) receiptModel.getValueAt(tableReceipt.getSelectedRow(),0);
                break;
            case 3:
                id = (String) storageOutModel.getValueAt(tableStorageOut.getSelectedRow(),0);
                break;
            case 4:
                id = (String) paymentModel.getValueAt(tablePayment.getSelectedRow(),0);
                break;
            case 5:
                id = (String) sendModel.getValueAt(tableSend.getSelectedRow(),0);
                break;
            case 6:
                id = (String) storageInModel.getValueAt(tableStorageIn.getSelectedRow(),0);
                break;
            case 7:
                id = (String) orderModel.getValueAt(tableOrder.getSelectedRow(),0);
                break;
            default:
                id = null;
                break;
        }
        resultMessage = controller.approveData(dataType,id);
        if(resultMessage == ResultMessage.SUCCESS){
            labelApprove.setText("审批成功!");
            this.initOrderTable();
            this.initSendTable();
            this.initPaymentTable();
            this.initReceiptTable();
            this.initEntruckTable();
            this.initarrivalTable();
            this.initStorageInTable();
            this.initStorageOutTable();
            this.initTransferTable();
        }
        else if(resultMessage == ResultMessage.FAILED){
            labelApprove.setText("审批失败!");
        }
        else if(resultMessage == ResultMessage.NOTCONNECTED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //审批同类型所有单据的按钮
    private void buttonApproveAllMouseClicked(MouseEvent e) {
        //单据的类型
        String dataType;
        int index = tabbedPaneApprove.getSelectedIndex();
        dataType = tabbedPaneApprove.getTitleAt(index);
        ResultMessage resultMessage = controller.approveAll(dataType);
        if (resultMessage == ResultMessage.SUCCESS) {
            labelApprove.setText("全部完成审批!");
        }
        else if(resultMessage == ResultMessage.FAILED){
            labelApprove.setText("审批失败!");
        }
        else if(resultMessage == ResultMessage.NOTCONNECTED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null, "网络错误...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //确认修改单据的按钮
    private void buttonModifyDataMouseClicked(MouseEvent e) {
        boolean isSuccess = false;
        int row,column;
        int index = tabbedPaneApprove.getSelectedIndex();
        ArrayList<ResultMessage> resultMessages = null;
        //根据单据的类型不同来修改单据
        switch (index) {
            //装车单
            case 0:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到EntruckModify中
                row = tableEntruk.getSelectedRow();
                column = tableEntruk.getSelectedColumn();
                tableEntruk.getCellEditor(row, column).stopCellEditing();
                for (int i = 0; i < entrukModify.size(); i++) {
                    EntruckListVO entruckListVO = new EntruckListVO();
                    Vector<String> entruck = entrukModify.get(i);
                    entruckListVO.entruckListID = entruck.get(0);
                    entruckListVO.fromID = entruck.get(2);
                    entruckListVO.destID = Long.valueOf(entruck.get(3));
                    entruckListVO.monitorName = entruck.get(4);
                    entruckListVO.escortName = entruck.get(5);
                    entruckListVO.fee = entruck.get(6);
                    entruckListVO.vehicleID = entruck.get(7);
                    resultMessages.add(controller.modifyEntruck(entruckListVO));
                }
                break;
            //到达单
            case 1:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableArrival.getSelectedRow();
                column = tableArrival.getSelectedColumn();
                tableArrival.getCellEditor(row, column).stopCellEditing();
                for (int i = 0; i < arrivalModify.size(); i++) {
                    ArrayList<StockStatus> stockStatuses = new ArrayList<>();
                    ArrivalVO arrivalVO = new ArrivalVO();
                    Vector<String> arrival = arrivalModify.get(i);
                    arrivalVO.setId(Long.valueOf(arrival.get(0)));
                    arrivalVO.setFromName(arrival.get(2));
                    arrivalVO.setDestName(arrival.get(3));
                    arrivalVO.setDate(arrival.get(4));
                    for(int k=0;k<tableArrival.getRowCount();k++){
                        //判断到达单号和所选单号是否一样
                        if(arrivalModel.getValueAt(k,0).equals(arrival.get(0))){
                            //将所有快递单的货物状态加入
                            stockStatuses.add(StockStatus.valueOf((String)arrivalModel.getValueAt(k,5)));
                        }
                    }
                    arrivalVO.setStatus(stockStatuses);
                    resultMessages.add(controller.modifyArrival(arrivalVO));
                }
             //收款单
            case 2:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableReceipt.getSelectedRow();
                column = tableReceipt.getSelectedColumn();
                tableReceipt.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<receiptModify.size();i++){
                    ReceiptVO receiptVO = new ReceiptVO();
                    Vector<String> receipt = receiptModify.get(i);
                    receiptVO.setId(Long.valueOf(receipt.get(0)));
                    receiptVO.setCourierName(receipt.get(1));
                    receiptVO.setMoney(Double.valueOf(receipt.get(2)));
                    receiptVO.setDate(receipt.get(3));
                    resultMessages.add(controller.modifyReceipt(receiptVO));
                }
             //出库单
            case 3:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableStorageOut.getSelectedRow();
                column = tableStorageOut.getSelectedColumn();
                tableStorageOut.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<storageOutModify.size();i++){
                    StorageOutVO storageOutVO = new StorageOutVO();
                    Vector<String> storageOut = storageOutModify.get(i);
                    storageOutVO.setId(storageOut.get(0));
                    storageOutVO.setTransferNum(storageOut.get(2));
                    storageOutVO.setTransferType(storageOut.get(3));
                    storageOutVO.setTransferListNum(storageOut.get(4));
                    resultMessages.add(controller.modifyStorageOutList(storageOutVO));
                }
             //付款单
            case 4:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tablePayment.getSelectedRow();
                column = tablePayment.getSelectedColumn();
                tablePayment.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<paymentModify.size();i++){
                    PaymentVO paymentVO = new PaymentVO();
                    Vector<String> payment = paymentModify.get(i);
                    paymentVO.setId(Long.valueOf(payment.get(0)));
                    paymentVO.setAccount(payment.get(1));
                    paymentVO.setName(payment.get(2));
                    paymentVO.setInfo(payment.get(3));
                    paymentVO.setMoney(Double.valueOf(payment.get(4)));
                    paymentVO.setDate(payment.get(5));
                    paymentVO.setExInfo(payment.get(6));
                    resultMessages.add(controller.modifyPayment(paymentVO));
                }
            //派件单
            case 5:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableSend.getSelectedRow();
                column = tableSend.getSelectedColumn();
                tableSend.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<sendModify.size();i++){
                    Vector<String> sendList = sendModify.get(i);
                    long id = Long.valueOf(sendList.get(0));
                    String sender = sendList.get(2);
                    SendListVO sendListVO = new SendListVO(null,null,sender,null,id);
                    resultMessages.add(controller.modifySend(sendListVO));
                }
             //中转单
            case 6:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableTransfer.getSelectedRow();
                column = tableTransfer.getSelectedColumn();
                tableTransfer.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<transferModify.size();i++){
                    Vector<String> transfer = transferModify.get(i);
                    TransferListVO transferListVO = new TransferListVO(null);
                    transferListVO.transferListID = transfer.get(0);
                    transferListVO.targetName = transfer.get(2);
                    transferListVO.vehicleCode = transfer.get(3);
                    transferListVO.staff = transfer.get(4);
                    transferListVO.fee = transfer.get(5);
                    transferListVO.date = transfer.get(6);
                    resultMessages.add(controller.modifyTransferList(transferListVO));
                }
            //入库单
            case 7:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableStorageIn.getSelectedRow();
                column = tableStorageIn.getSelectedColumn();
                tableStorageIn.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<storageInModify.size();i++){
                    Vector<String> storageIn = storageInModify.get(i);
                    StorageInVO storageInVO = new StorageInVO(null,null);
                    storageInVO.setId(Long.valueOf(storageIn.get(0)));
                    storageInVO.setDate(storageIn.get(7));
                    resultMessages.add(controller.modifyStorageInList(storageInVO));
                }
            //寄件单
            case 8:
                resultMessages = new ArrayList<ResultMessage>();
                //将最后一个被编辑的单元格设置停止编辑状态,加入到salaryModify中
                row = tableOrder.getSelectedRow();
                column = tableOrder.getSelectedColumn();
                tableOrder.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<orderModify.size();i++){
                    Iterator<String> order = orderModify.get(i).iterator();
                    long id = Long.valueOf(order.next());
                    String sName = order.next();
                    String sAdress = order.next();
                    String sPhone = order.next();
                    String rName = order.next();
                    String rAdress = order.next();
                    String rPhone = order.next();
                    int stockNum = Integer.valueOf(order.next());
                    double weight = Double.valueOf(order.next());
                    double fee = Double.valueOf(order.next());
                    ServiceType serviceType = data.enums.ServiceType.valueOf(order.next());
                    OrderVO orderVO = new OrderVO(sName,sAdress,null,sPhone,rName,rAdress,null,rPhone,stockNum,weight,0,null,serviceType,fee,id);
                    resultMessages.add(controller.modifyOrder(orderVO));
                }
            }

        for(int i=0;i<resultMessages.size();i++){
            if(resultMessages.get(i) == ResultMessage.NOTCONNECTED){
                JOptionPane.showMessageDialog(null,"网络错误...","",JComponent.ERROR);
                isSuccess = false;
                break;
            }
            else if(resultMessages.get(i) == ResultMessage.SUCCESS){
                isSuccess = true;
            }
            else if(resultMessages.get(i) == ResultMessage.FAILED){
                isSuccess = false;
                break;
            }
        }
        if(resultMessages!=null){
        if(isSuccess){
            labelApprove.setText("修改成功!");
        }
        else{
            labelApprove.setText("修改失败!");
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"没有修改!","",JComponent.ERROR);
        }
    }

    //退出单据审批的按钮
    private void buttonExitApproveMouseClicked(MouseEvent e) {
       this.dispose();
    }
	/*
	 // 初始化表格的总方法

	private void initTable(String []columns,Vector data,JTable table,JScrollPane scrollPane,DefaultTableModel model){
		Vector<String> column = new Vector<String>();
		for(int i=0;i<columns.length;i++){
			column.add(columns[i]);
		}
		//模型
		model = new DefaultTableModel(data,column);
		//表格
		table = new JTable(model){
		private static final long serialVersionUid = 1L;

		public boolean isCellEditable(int row, int column){
		return true;
			   }
		};
	//	model = (DefaultTableModel) table.getSelectionModel();
		table.getTableHeader().setPreferredSize(new Dimension(40,30));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    scrollPane.getViewport().add(table);
	    System.out.println(model);
	}
	*/
    //有空再用 实在没时间折腾了!!!!虽然代码有点重复!!!!
    //界面全写完再来考虑!!!!!!


    /*
     * 初始化Salary表格
     */
    public void initSalaryTable(){
        //表头
        Vector<String> salaryColumns = new Vector<String>();
        salaryColumns.add("员工类型");
        salaryColumns.add("员工工资");
        salaryColumns.add("结算方式");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> salaryVO = null;
        Vector<Vector<String>> salaryData = new Vector<Vector<String>>();
     //   ArrayList<SalaryVO> salaryVOs = controller.searchAllSalary();
        ArrayList<SalaryVO> salaryVOs = new ArrayList<SalaryVO>();
        for(int i=0;i<salaryVOs.size();i++){
            salaryVO = new Vector<String>();
            salaryVO.add(salaryVOs.get(i).getInstitution());
            salaryVO.add(salaryVOs.get(i).getSalary()+"");
            salaryVO.add(salaryVOs.get(i).getType());
            salaryData.add(salaryVO);
        }
        //模型
        salaryModel = new DefaultTableModel(salaryData,salaryColumns);
        salaryModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent arg0) {
                Vector<String> salary = new Vector<String>();
                int row = tableSalary.getSelectedRow();
                for(int i=0;i<tableSalary.getColumnCount();i++){
                    salary.add((String) salaryModel.getValueAt(row, i));
                }
                salaryModify.addElement(salary);
            }
        });
        //表格
        tableSalary = new JTable(salaryModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                if(column==1||column==2)
                    return true;
                else
                    return false;
            }
        };
        tableSalary.getTableHeader().setReorderingAllowed(false);
        tableSalary.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableSalary.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelSalary.getViewport().add(tableSalary);
    }

    /*
     * 初始化City表格
     */
    public void initCityTable(CityTransVO cityTransVO){
        //表头
        Vector<String> cityColumns = new Vector<String>();
        cityColumns.add("距离(km)");
        cityColumns.add("货车价格(/公里/克)");
        cityColumns.add("火车价格(/公里/克)");
        cityColumns.add("飞机价格(/公里/克)");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> cityVO = new Vector<String>();
        Vector<Vector<String>> cityData = new Vector<Vector<String>>();
        cityVO.add(cityTransVO.getDistance()+"");
        cityVO.add(cityTransVO.getTrunkPrice()+"");
        cityVO.add(cityTransVO.getTrainPrice()+"");
        cityVO.add(cityTransVO.getPlanePrice()+"");
        cityData.add(cityVO);
        //模型
        cityModel = new DefaultTableModel(cityData,cityColumns);
        cityModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> city = new Vector<String>();
                for(int i=0;i<tableCity.getColumnCount();i++)
                    city.add((String) cityModel.getValueAt(0, i));
                cityModify.clear();
                cityModify.addElement(city);
            }
        });
        //表格
        tableCity = new JTable(cityModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return true;
            }
        };
        tableCity.getTableHeader().setReorderingAllowed(false);
        tableCity.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableCity.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelCity.getViewport().add(tableCity);
    }

    /*
     * 初始化Deliver(快递员)表格
     */
    public void initDeliverTable(){
        //表头
        Vector<String> deliverColumns = new Vector<String>();
        deliverColumns.add("id");
        deliverColumns.add("姓名");
        deliverColumns.add("性别");
        deliverColumns.add("年龄");
        deliverColumns.add("身份证号");
        deliverColumns.add("联系电话");
        //数据
        Vector<Vector<String>> deliverData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //模型
        deliverModel = new DefaultTableModel(deliverData,deliverColumns);
        //表格
        tableDeliver = new JTable(deliverModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableDeliver.getTableHeader().setReorderingAllowed(false);
        tableDeliver.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableDeliver.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelDeliver.getViewport().add(tableDeliver);
    }

    /*
     * 初始化Financial(财务人员)表格
     */
    public void initFinancialTable(){
        //表头
        Vector<String> financialColumns = new Vector<String>();
        financialColumns.add("id");
        financialColumns.add("姓名");
        financialColumns.add("性别");
        financialColumns.add("年龄");
        financialColumns.add("身份证号");
        financialColumns.add("联系电话");
        //数据
        Vector<Vector<String>> financialData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //模型
        financialModel = new DefaultTableModel(financialData,financialColumns);
        //表格
        tableFinancial = new JTable(financialModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableFinancial.getTableHeader().setReorderingAllowed(false);
        tableFinancial.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableFinancial.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelFinancial.getViewport().add(tableFinancial);
    }

    /*
     * 初始化Trunk(货车驾驶员)表格
     */
    public void initTrunkTable(){
        //表头
        Vector<String> trunkColumns = new Vector<String>();
        trunkColumns.add("id");
        trunkColumns.add("姓名");
        trunkColumns.add("性别");
        trunkColumns.add("年龄");
        trunkColumns.add("身份证号");
        trunkColumns.add("联系电话");
        //数据
        Vector<Vector<String>> trunkData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //模型
        trunkModel = new DefaultTableModel(trunkData,trunkColumns);
        //表格
        tableTrunk = new JTable(trunkModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableTrunk.getTableHeader().setReorderingAllowed(false);
        tableTrunk.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableTrunk.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelTrunk.getViewport().add(tableTrunk);
    }

    /*
     * 初始化Center(中转中心业务员)表格
     */
    public void initCenterTable(String institution){
        //表头
        Vector<String> centerColumns = new Vector<String>();
        centerColumns.add("id");
        centerColumns.add("姓名");
        centerColumns.add("性别");
        centerColumns.add("年龄");
        centerColumns.add("身份证号");
        centerColumns.add("联系电话");
        //数据
        Vector<Vector<String>> centerData = this.getStaff(institution);
        //模型
        centerModel = new DefaultTableModel(centerData,centerColumns);
        //表格
        tableCenter = new JTable(centerModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableCenter.getTableHeader().setReorderingAllowed(false);
        tableCenter.getTableHeader().setPreferredSize(new Dimension(40,30));
        tableCenter.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //注意这里是Pane不是Panel!!!
        scrollPaneCenter.getViewport().add(tableCenter);


    }

    /*
     * 初始化Business(营业厅业务员)表格
     */
    public void initBusinessTable(String institution){
        //表头
        Vector<String> businessColumns = new Vector<String>();
        businessColumns.add("id");
        businessColumns.add("姓名");
        businessColumns.add("性别");
        businessColumns.add("年龄");
        businessColumns.add("身份证号");
        businessColumns.add("联系电话");
        //数据
        Vector<Vector<String>> businessData = this.getStaff(institution);
        //模型
        businessModel = new DefaultTableModel(businessData,businessColumns);
        //表格
        tableBusiness = new JTable(businessModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableBusiness.getTableHeader().setReorderingAllowed(false);
        tableBusiness.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableBusiness.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //注意这里是Pane不是Panel!!!
        scrollPaneBusiness.getViewport().add(tableBusiness);
    }

    /*
     * 初始化Storage(仓库管理员)表格
     */
    public void initStorageTable(String institution){
        //表头
        Vector<String> storageColumns = new Vector<String>();
        storageColumns.add("id");
        storageColumns.add("姓名");
        storageColumns.add("性别");
        storageColumns.add("年龄");
        storageColumns.add("身份证号");
        storageColumns.add("联系电话");
        //数据
        Vector<Vector<String>> storageData = this.getStaff(institution);
        //模型
        storageModel = new DefaultTableModel(storageData,storageColumns);
        //表格
        tableStorage = new JTable(storageModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableStorage.getTableHeader().setReorderingAllowed(false);
        tableStorage.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableStorage.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //注意这里是Pane不是Panel!!!
        scrollPaneStorage.getViewport().add(tableStorage);
    }

    /*
     * 获取人员信息的总方法
     */
    private Vector<Vector<String>> getStaff(String institution){
        Vector<String> staffVO = null;
        Vector<Vector<String>> staffData = new Vector<Vector<String>>();
      //  ArrayList<StaffVO> staffVOs = controller.getStaffByInstitution(institution);
        ArrayList<StaffVO> staffVOs = new ArrayList<>();
        for(int i=0;i<staffVOs.size();i++){
            staffVO = new Vector<String>();
            staffVO.add(staffVOs.get(i).getId()+"");
            staffVO.add(staffVOs.get(i).getName());
            if(staffVOs.get(i).gender)
                staffVO.add("女");
            else
                staffVO.add("男");
            staffVO.add(staffVOs.get(i).getIdcardNum());
            staffVO.add(staffVOs.get(i).getPhoneNum()+"");
            staffData.add(staffVO);
        }
        return staffData;
    }

    /*
     * 初始化Order(寄件单审批)表格
     */
    private void initOrderTable(){
        //表头
        Vector<String> orderColumns = new Vector<String>();
        orderColumns.add("寄件单号");
        orderColumns.add("寄件人");
        orderColumns.add("寄件地址");
        orderColumns.add("寄件人电话");
        orderColumns.add("收件人");
        orderColumns.add("收件地址");
        orderColumns.add("收件人电话");
        orderColumns.add("物件数");
        orderColumns.add("重量");
        orderColumns.add("运费");
        orderColumns.add("运输方式");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> orderVO = null;
        Vector<Vector<String>> orderData = new Vector<Vector<String>>();
     //   ArrayList<OrderPO> orderPOs = controller.getUnapprovedOrderList();
        ArrayList<OrderPO> orderPOs = new ArrayList<>();
        for(int i=0;i<orderPOs.size();i++){
            orderVO = new Vector<String>();
            OrderPO orderPO = orderPOs.get(i);
            orderVO.add(orderPO.getSerialNum()+"");
            orderVO.add(orderPO.getSname());
            orderVO.add(orderPO.getSaddress());
            orderVO.add(orderPO.getSphone());
            orderVO.add(orderPO.getRname());
            orderVO.add(orderPO.getRaddress());
            orderVO.add(orderPO.getRphone());
            orderVO.add(orderPO.getStockNum()+"");
            orderVO.add(orderPO.getWeight()+"");
            orderVO.add(orderPO.getFee()+"");
            orderVO.add(orderPO.getServiceType().toString());
            orderData.add(orderVO);
        }
        //模型
        orderModel = new DefaultTableModel(orderData,orderColumns);
        orderModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> order = new Vector<String>();
                int row = tableOrder.getSelectedRow();
                for(int i=0;i<tableOrder.getColumnCount();i++){
                    order.add((String) orderModel.getValueAt(row, i));
                }
                orderModify.addElement(order);
            }
        });
        //表格
        tableOrder = new JTable(orderModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return true;}
        };
        tableOrder.getTableHeader().setReorderingAllowed(false);
        tableOrder.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableOrder.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelOrder.getViewport().add(tableOrder);
    }

    /*
     * 初始化Send(派件单审批)表格
     */
    private void initSendTable(){
        //表头
        Vector<String> sendColumns = new Vector<String>();
        sendColumns.add("派件单号");
        sendColumns.add("寄件单号");
        sendColumns.add("派件人");
        sendColumns.add("签收人");
        sendColumns.add("签收人电话");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> sendVO = null;
        Vector<Vector<String>> sendData = new Vector<Vector<String>>();
  //      ArrayList<SendListPO> sendListPOs = controller.getUnapprovedSendList();
        ArrayList<SendListPO> sendListPOs = new ArrayList<>();
        for(int i=0;i<sendListPOs.size();i++){
            SendListPO sendListPO = sendListPOs.get(i);
            long [] orderList = sendListPO.getOrder();
            for(int k=0;k<orderList.length;k++){
                sendVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                sendVO.add(sendListPO.getSerialNum()+"");
                sendVO.add(orderPO.getSerialNum()+"");
                sendVO.add(sendListPO.getSender());
                sendVO.add(orderPO.getRname());
                sendVO.add(orderPO.getRphone());
                sendData.add(sendVO);
            }
        }
        //模型
        sendModel = new DefaultTableModel(sendData,sendColumns);
        sendModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> send = new Vector<String>();
                int row = tableSend.getSelectedRow();
                for(int i=0;i<send.size();i++){
                    send.add((String) sendModel.getValueAt(row, i));
                }
                sendModify.addElement(send);
            }
        });
        //表格
        tableSend = new JTable(sendModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return true;}
        };
        tableSend.getTableHeader().setReorderingAllowed(false);
        tableSend.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableSend.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelSend.getViewport().add(tableSend);
    }

    /*
     * 初始化Payment(付款单审批)表格
     */
    private void initPaymentTable(){
        //表头
        Vector<String> paymentColumns = new Vector<String>();
        paymentColumns.add("付款单号");
        paymentColumns.add("付款账号");
        paymentColumns.add("付款人");
        paymentColumns.add("条目");
        paymentColumns.add("付款金额");
        paymentColumns.add("付款日期");
        paymentColumns.add("备注");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> paymentVO = null;
        Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
    //    ArrayList<PaymentPO> paymentPOs = controller.getUnapprovedPaymentList();
        ArrayList<PaymentPO> paymentPOs = new ArrayList<>();
        for(int i=0;i<paymentPOs.size();i++){
            PaymentPO paymentPO = paymentPOs.get(i);
            paymentVO = new Vector<String>();
            paymentVO.add(paymentPO.getSerialNum()+"");
            paymentVO.add(paymentPO.getAccount());
            paymentVO.add(paymentPO.getName());
            paymentVO.add(paymentPO.getInfo());
            paymentVO.add(paymentPO.getMoney()+"");
            paymentVO.add(paymentPO.getDate());
            paymentVO.add(paymentPO.getExInfo());
            paymentData.add(paymentVO);
        }
        //模型
        paymentModel = new DefaultTableModel(paymentData,paymentColumns);
        paymentModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> payment = new Vector<String>();
                int row = tablePayment.getSelectedRow();
                for(int i=0;i<tablePayment.getColumnCount();i++){
                    payment.add((String) paymentModel.getValueAt(row, i));
                }
                paymentModify.addElement(payment);
            }
        });
        //表格
        tablePayment = new JTable(paymentModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tablePayment.getTableHeader().setReorderingAllowed(false);
        tablePayment.getColumnModel().getColumn(tablePayment.getColumnCount()-1).setPreferredWidth(120);
        tablePayment.getTableHeader().setPreferredSize(new Dimension(30,30));
        tablePayment.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelPayment.getViewport().add(tablePayment);
    }

    /*
     * 初始化Receipt(收款单审批)表格
     */
    private void initReceiptTable(){
        //表头
        Vector<String> receiptColumns = new Vector<String>();
        receiptColumns.add("收款单号");
        receiptColumns.add("收款人");
        receiptColumns.add("收款金额");
        receiptColumns.add("收款日期");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> receiptVO = null;
        Vector<Vector<String>> receiptData = new Vector<Vector<String>>();
    //    ArrayList<ReceiptPO> receiptPOs = controller.getUnapprovedReceiptList();
        ArrayList<ReceiptPO> receiptPOs = new ArrayList<>();
        for(int i=0;i<receiptPOs.size();i++){
            ReceiptPO receiptPO = receiptPOs.get(i);
            receiptVO = new Vector<String>();
            receiptVO.add(receiptPO.getSerialNum()+"");
            receiptVO.add(receiptPO.getSender());
            receiptVO.add(receiptPO.getMoney()+"");
            receiptVO.add(receiptPO.getDate());
            receiptData.add(receiptVO);
        }
        //模型
        receiptModel = new DefaultTableModel(receiptData,receiptColumns);
        receiptModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> receipt = new Vector<String>();
                int row = tableReceipt.getSelectedRow();
                for(int i=0;i<tableReceipt.getColumnCount();i++){
                    receipt.add((String) receiptModel.getValueAt(row, i));
                }
                receiptModify.addElement(receipt);
            }
        });
        //表格
        tableReceipt = new JTable(receiptModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableReceipt.getTableHeader().setReorderingAllowed(false);
        tableReceipt.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableReceipt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelReceipt.getViewport().add(tableReceipt);
    }

    /*
     * 初始化Entruk(装车单审批）表格
     */
    private void initEntruckTable(){
        //表头
        Vector<String> entruckColumns = new Vector<String>();
        entruckColumns.add("装车单编号");
        entruckColumns.add("托运单号");
        entruckColumns.add("营业厅编号");
        entruckColumns.add("到达地");
        entruckColumns.add("监装员");
        entruckColumns.add("押运员");
        entruckColumns.add("运费");
        entruckColumns.add("车辆/飞机编号");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> entrukVO = null;
        Vector<Vector<String>> entrukData = new Vector<Vector<String>>();
     //   ArrayList<EntruckPO> entruckPOs = controller.getUnapprovedEntruckList();
        ArrayList<EntruckPO> entruckPOs = new ArrayList<>();
        for(int i=0;i<entruckPOs.size();i++){
            EntruckPO entruckPO = entruckPOs.get(i);
            long [] orderList = entruckPO.getOrder();
            for(int k=0;k<orderList.length;k++){
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                entrukVO.add(entruckPO.getSerialNum()+"");
                entrukVO.add(orderPO.getSerialNum()+"");
                entrukVO.add(entruckPO.getFrom()+"");
                entrukVO.add(entruckPO.getDestID()+"");
                entrukVO.add(entruckPO.getMonitorName());
                entrukVO.add(entruckPO.getEscortName());
                entrukVO.add(entruckPO.getFee()+"");
                entrukVO.add(entruckPO.getVehicleID()+"");
                entrukData.add(entrukVO);
            }
        }
        //模型
        entrukModel = new DefaultTableModel(entrukData,entruckColumns);
        entrukModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> entruk = new Vector<String>();
                int row = tableEntruk.getSelectedRow();
                for(int i=0;i<tableEntruk.getColumnCount();i++){
                    entruk.add((String) entrukModel.getValueAt(row, i));
                }
                entrukModify.addElement(entruk);
            }
        });
        //表格
        tableEntruk = new JTable(entrukModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableEntruk.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableEntruk.getTableHeader().setReorderingAllowed(false);
        tableEntruk.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelEntruk.getViewport().add(tableEntruk);
    }

    /*
     * 初始化Arrival(到达单审批)表格
     */
    private void initarrivalTable(){
        //表头
        Vector<String> arrivalColumns = new Vector<String>();
        arrivalColumns.add("到达单编号");
        arrivalColumns.add("订单编号");
        arrivalColumns.add("出发地");
        arrivalColumns.add("到达地");
        arrivalColumns.add("到达日期");
        arrivalColumns.add("货物状态");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> arrivalVO = null;
        Vector<Vector<String>> arrivalData = new Vector<Vector<String>>();
     //   ArrayList<ArrivalPO> arrivalPOs = controller.getUnapprovedarrivalList();
        ArrayList<ArrivalPO> arrivalPOs = new ArrayList<>();
        for(int i=0;i<arrivalPOs.size();i++){
            ArrivalPO arrivalPO = arrivalPOs.get(i);
            long [] orderList = arrivalPO.getOrder();
            ArrayList<StockStatus> stockStatuses = arrivalPO.getStockStatus();
            for(int k=0;k<orderList.length;k++){
                arrivalVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                arrivalVO.add(arrivalPO.getSerialNum()+"");
                arrivalVO.add(orderPO.getSerialNum()+"");
                arrivalVO.add(arrivalPO.getFromName());
                arrivalVO.add(arrivalPO.getDestName());
                arrivalVO.add(arrivalPO.getDate());
                arrivalVO.add(stockStatuses.get(i).toString());
                arrivalData.add(arrivalVO);
            }
        }
        //模型
        arrivalModel = new DefaultTableModel(arrivalData,arrivalColumns);
        arrivalModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> arrival = new Vector<String>();
                int row = tableArrival.getSelectedRow();
                for(int i=0;i<tableArrival.getColumnCount();i++){
                    arrival.add((String) arrivalModel.getValueAt(row, i));
                }
                arrivalModify.addElement(arrival);
            }
        });
        //表格
        tableArrival = new JTable(arrivalModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableArrival.getTableHeader().setReorderingAllowed(false);
        tableArrival.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableArrival.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelarrival.getViewport().add(tableArrival);
    }

   /*
     * 初始化CenterArrival(中转中心到达单审批)表格

    private void initCenterArrivalTable(){
        //表头
        Vector<String> centerArrivalColumns = new Vector<String>();
        centerArrivalColumns.add("到达单编号");
        centerArrivalColumns.add("订单编号");
        centerArrivalColumns.add("中转中心编号");
        centerArrivalColumns.add("出发地");
        centerArrivalColumns.add("到达日期");
        centerArrivalColumns.add("货物状态");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> centerArrivalVO = null;
        Vector<Vector<String>> centerArrivalData = new Vector<Vector<String>>();
   //    ArrayList<ArrivalPO> arrivalPOs = controller.getUnapprovedCenterArrivalList();
        ArrayList<ArrivalPO> arrivalPOs = new ArrayList<>();
        for(int i=0;i<arrivalPOs.size();i++){
            ArrivalPO arrivalPO = arrivalPOs.get(i);
            long [] orderList = arrivalPO.getOrder();
            ArrayList<StockStatus> stockStatuses = arrivalPO.getStockStatus();
            for(int k=0;k<orderList.length;k++) {
                centerArrivalVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                centerArrivalVO.add(arrivalPO.getSerialNum()+"");
                centerArrivalVO.add(orderPO.getSerialNum()+"");
                centerArrivalVO.add(arrivalPO.getTransferList()+"");
                centerArrivalVO.add(arrivalPO.getFromName());
                centerArrivalVO.add(arrivalPO.getDate());
                centerArrivalVO.add(stockStatuses.get(i).toString());
                centerArrivalData.add(centerArrivalVO);
            }
        }
        //模型
        centerArrivalModel = new DefaultTableModel(centerArrivalData,centerArrivalColumns);
        centerArrivalModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> centerArrival = new Vector<String>();
                int row = tableCenterArrival.getSelectedRow();
                for(int i=0;i<tableCenterArrival.getColumnCount();i++){
                    centerArrival.add((String) centerArrivalModel.getValueAt(row, i));
                }
                centerArrivalModify.addElement(centerArrival);
            }
        });
        //表格
        tableCenterArrival = new JTable(centerArrivalModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableCenterArrival.getTableHeader().setReorderingAllowed(false);
        tableCenterArrival.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableCenterArrival.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelCenterArrival.getViewport().add(tableCenterArrival);
    }
     */

    /*
     * 初始化StorageIn(入库单审批)表格
     */
    private void initStorageInTable(){
        //表头
        Vector<String> storageInColumns = new Vector<String>();
        storageInColumns.add("入库单编号");
        storageInColumns.add("快递编号");
        storageInColumns.add("目的地编号");
        storageInColumns.add("区号");
        storageInColumns.add("排号");
        storageInColumns.add("架号");
        storageInColumns.add("位号");
        storageInColumns.add("入库日期");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> storageInVO = null;
        Vector<Vector<String>> storageInData = new Vector<Vector<String>>();
    //    ArrayList<StorageInListPO> storageInListPOs = controller.getUnapprovedStorageInList();
        ArrayList<StorageInListPO> storageInListPOs = new ArrayList<>();
        for(int i=0;i<storageInListPOs.size();i++){
            StorageInListPO storageInListPO = storageInListPOs.get(i);
            long [] orderList = storageInListPO.getOrderList();
            ArrayList<String> position = storageInListPO.getStoragePosition();
            for(int k=0;k<orderList.length;k++){
                storageInVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                String []positions = position.get(i).split("[-]");
                storageInVO.add(storageInListPO.getSerialNum()+"");
                storageInVO.add(orderPO.getSerialNum()+"");
                storageInVO.add(storageInListPO.getTransferNum()+"");
                storageInVO.add(positions[0]);
                storageInVO.add(positions[1]);
                storageInVO.add(positions[2]);
                storageInVO.add(positions[3]);
                storageInVO.add(storageInListPO.getDate());
                storageInData.add(storageInVO);
            }
        }
        //模型
        storageInModel = new DefaultTableModel(storageInData,storageInColumns);
        storageInModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> storageIn = new Vector<String>();
                int row = tableStorageIn.getSelectedRow();
                for(int i=0;i<tableStorageIn.getColumnCount();i++){
                    storageIn.add((String) storageInModel.getValueAt(row, i));
                }
                storageInModify.addElement(storageIn);
            }
        });
        //表格
        tableStorageIn = new JTable(storageInModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableStorageIn.getTableHeader().setReorderingAllowed(false);
        tableStorageIn.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableStorageIn.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelStorageIn.getViewport().add(tableStorageIn);
    }

    /*
     * 初始化StorageOut(出库单审批)表格
     */
    private void initStorageOutTable(){
        //表头
        Vector<String> storageOutColumns = new Vector<String>();
        storageOutColumns.add("入库单编号");
        storageOutColumns.add("快递编号");
        storageOutColumns.add("目的地编号");
        storageOutColumns.add("装运形式");
        storageOutColumns.add("出库日期");
        storageOutColumns.add("中转单/汽运编号");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> storageOutVO = null;
        Vector<Vector<String>> storageOutData = new Vector<Vector<String>>();
    //    ArrayList<StorageOutListPO> storageOutListPOs = controller.getUnapprovedStoragedOutList();
        ArrayList<StorageOutListPO> storageOutListPOs = new ArrayList<>();
        for(int i=0;i<storageOutListPOs.size();i++){
            StorageOutListPO storageOutListPO = storageOutListPOs.get(i);
            long[] orderList = storageOutListPO.getOrder();
            for(int k=0;k<orderList.length;k++){
                storageOutVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                storageOutVO.add(storageOutListPO.getSerialNum()+"");
                storageOutVO.add(orderPO.getSerialNum()+"");
                storageOutVO.add(storageOutListPO.getTransferNum()+"");
                storageOutVO.add(storageOutListPO.getTransferType().toString());
                storageOutVO.add(storageOutListPO.getDeliveryListNum()+"");
                storageOutVO.add(storageOutListPO.getDate());
                storageOutData.add(storageOutVO);
            }
        }
        //模型
        storageOutModel = new DefaultTableModel(storageOutData,storageOutColumns);
        storageOutModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> storageOut = new Vector<String>();
                int row = tableStorageOut.getSelectedRow();
                for(int i=0;i<tableStorageOut.getColumnCount();i++){
                    storageOut.add((String) storageOutModel.getValueAt(row, i));
                }
                storageOutModify.addElement(storageOut);
            }
        });
        //表格
        tableStorageOut = new JTable(storageOutModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableStorageOut.getTableHeader().setReorderingAllowed(false);
        tableStorageOut.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableStorageOut.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelStorageOut.getViewport().add(tableStorageOut);
    }

    /*
     * 初始化Transfer(中转单审批)表格
     */
    private void initTransferTable(){
        //表头
        Vector<String> transferColumns = new Vector<String>();
        transferColumns.add("中转单编号");
        transferColumns.add("快递编号");
        transferColumns.add("目的地(营业厅)");
        transferColumns.add("车辆/飞机编号");
        transferColumns.add("监装员");
        transferColumns.add("运费");
        transferColumns.add("装车日期");
        //数据:第一个Vector用来存放一个VO,第二个Vector存放VO集合
        Vector<String> transferVO = null;
        Vector<Vector<String>> transferData = new Vector<Vector<String>>();
    //    ArrayList<TransferListPO> transferListPOs = controller.getUnapprovedTransferList();
        ArrayList<TransferListPO> transferListPOs = new ArrayList<>();
        for(int i=0;i<transferListPOs.size();i++){
            TransferListPO transferListPO = transferListPOs.get(i);
            long [] orderList = transferListPO.getOrder();
            for(int k=0;k<orderList.length;k++){
                transferVO = new Vector<String>();
                OrderPO orderPO = controller.getOrderData(orderList[k]);
                transferVO.add(transferListPO.getSerialNum()+"");
                transferVO.add(orderPO.getSerialNum()+"");
                transferVO.add(transferListPO.getTargetCenterName());
                transferVO.add(transferListPO.getVehicleCode()+"");
                transferVO.add(transferListPO.getStaffName());
                transferVO.add(transferListPO.getFee()+"");
                transferVO.add(transferListPO.getDate());
                transferData.add(transferVO);
            }
        }
        //模型
        transferModel = new DefaultTableModel(transferData,transferColumns);
        transferModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> transfer = new Vector<String>();
                int row = tableTransfer.getSelectedRow();
                for(int i=0;i<tableTransfer.getColumnCount();i++){
                    transfer.add((String) transferModel.getValueAt(row, i));
                }
                transferModify.addElement(transfer);
            }
        });
        //表格
        tableTransfer = new JTable(transferModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableTransfer.getTableHeader().setReorderingAllowed(false);
        tableTransfer.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableTransfer.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelTransfer.getViewport().add(tableTransfer);
    }

    private void initComponents() {



        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        choice = new JMenu();
        help = new JMenu();
        buttonSalary = new JToggleButton();
        buttonStaff = new JToggleButton();
        buttonCity = new JToggleButton();
        buttonApprove = new JToggleButton();
        buttonCheck = new JToggleButton();
        labelSalary = new JLabel();
        labelInstitution = new JLabel();
        labelCity = new JLabel();
        labelApprove = new JLabel();
        labelCheck = new JLabel();
        id = new JLabel();
        name = new JLabel();
        panelStaff = new JPanel();
        tabbedPaneStaff = new JTabbedPane();
        scrollPanelDeliver = new JScrollPane();
        tableDeliver = new JTable();
        scrollPanelFinancial = new JScrollPane();
        tableFinancial = new JTable();
        scrollPanelTrunk = new JScrollPane();
        tableTrunk = new JTable();
        scrollPanelTrain = new JScrollPane();
        tableTrain = new JTable();
        scrollPanelPlane = new JScrollPane();
        tablePlane = new JTable();
        scrollPanelCenter = new JPanel();
        labelCenter = new JLabel();
        comboBoxCenter = new JComboBox();
        buttonEnsureCenter = new JButton();
        scrollPaneCenter = new JScrollPane();
        tableCenter = new JTable();
        scrollPanelBusiness = new JPanel();
        labelBusiness = new JLabel();
        comboBoxBusinessCity = new JComboBox();
        comboBoxBusinessNum = new JComboBox();
        buttonEnsureBusiness = new JButton();
        scrollPaneBusiness = new JScrollPane();
        tableBusiness = new JTable();
        scrollPanelStorage = new JPanel();
        labelCenterStorage = new JLabel();
        comboBoxStorage = new JComboBox();
        buttonEnsureStorage = new JButton();
        scrollPaneStorage = new JScrollPane();
        tableStorage = new JTable();
        buttonAddStaff = new JButton();
        buttonMoveStaff = new JButton();
        buttonDeleteStaff = new JButton();
        buttonExitStaff = new JButton();
        labelStaffSuccess = new JLabel();
        panelAll = new JPanel();
        panelSalary = new JPanel();
        scrollPanelSalary = new JScrollPane();
        tableSalary = new JTable();
        buttonAddSalary = new JButton();
        buttonModifySalary = new JButton();
        buttonExitSalary = new JButton();
        labelSalarySuccess = new JLabel();
        panelCity = new JPanel();
        labelLeaveCity = new JLabel();
        comboBoxLeaveCity = new JComboBox();
        labelArrivalCity = new JLabel();
        comboBoxArrivalCity = new JComboBox();
        buttonEnsureCity = new JButton();
        scrollPanelCity = new JScrollPane();
        tableCity = new JTable();
        buttonAddCity = new JButton();
        buttonModifyCity = new JButton();
        buttonExitCity = new JButton();
        labelCitySuccess = new JLabel();
        panelApprove = new JPanel();
        tabbedPaneApprove = new JTabbedPane();
        scrollPanelEntruk = new JScrollPane();
        tableEntruk = new JTable();
        scrollPanelarrival = new JScrollPane();
        tableArrival = new JTable();
        scrollPanelReceipt = new JScrollPane();
        tableReceipt = new JTable();
        scrollPanelStorageOut = new JScrollPane();
        tableStorageOut = new JTable();
        scrollPanelPayment = new JScrollPane();
        tablePayment = new JTable();
        scrollPanelSend = new JScrollPane();
        tableSend = new JTable();
     //   scrollPanelCenterArrival = new JScrollPane();
     //   tableCenterArrival = new JTable();
        scrollPanelTransfer = new JScrollPane();
        tableTransfer = new JTable();
        scrollPanelStorageIn = new JScrollPane();
        tableStorageIn = new JTable();
        scrollPanelOrder = new JScrollPane();
        tableOrder = new JTable();
        buttonApproveData = new JButton();
        buttonModifyData = new JButton();
        buttonApproveAll = new JButton();
        buttonExitApprove = new JButton();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar ========
        {

            //======== choice ========
            {
                choice.setText("\u9009\u9879(C)");
            }
            menuBar.add(choice);

            //======== help ========
            {
                help.setText("\u5e2e\u52a9(H)");
            }
            menuBar.add(help);
        }
        setJMenuBar(menuBar);

        //---- buttonSalary ----
        buttonSalary.setText("text");
        buttonSalary.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSalaryMouseClicked(e);
            }
        });

        //---- buttonStaff ----
        buttonStaff.setText("text");
        buttonStaff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonStaffMouseClicked(e);
            }
        });

        //---- buttonCity ----
        buttonCity.setText("text");
        buttonCity.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonCityMouseClicked(e);
            }
        });

        //---- buttonApprove ----
        buttonApprove.setText("text");
        buttonApprove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonApproveMouseClicked(e);
            }
        });

        //---- buttonCheck ----
        buttonCheck.setText("text");
        buttonCheck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonCheckMouseClicked(e);
            }
        });

        //---- labelSalary ----
        labelSalary.setText("\u5de5\u8d44\u7ba1\u7406");

        //---- labelInstitution ----
        labelInstitution.setText("\u673a\u6784\u7ba1\u7406");

        //---- labelCity ----
        labelCity.setText("\u57ce\u5e02\u7ba1\u7406");

        //---- labelApprove ----
        labelApprove.setText("\u5ba1\u6279\u5355\u636e");

        //---- labelCheck ----
        labelCheck.setText("\u67e5\u770b\u62a5\u8868");

        //---- id ----
        id.setText("id:12345");

        //---- name ----
        name.setText("\u59d3\u540d:\u5f69\u7b14");


        //======== panelAll ========
        {

            GroupLayout panelAllLayout = new GroupLayout(panelAll);
            panelAll.setLayout(panelAllLayout);
            panelAllLayout.setHorizontalGroup(
                    panelAllLayout.createParallelGroup()
                            .addGap(0, 786, Short.MAX_VALUE)
            );
            panelAllLayout.setVerticalGroup(
                    panelAllLayout.createParallelGroup()
                            .addGap(0, 361, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(panelAll, GroupLayout.PREFERRED_SIZE, 782, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(labelSalary, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                                        .addComponent(buttonSalary, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(buttonStaff, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(buttonCity, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(buttonApprove, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(buttonCheck, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(labelInstitution, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(labelCity, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(labelApprove, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(labelCheck, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                .addGap(33, 33, 33))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonSalary, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonStaff, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonCity, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonApprove, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonCheck, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelSalary, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelInstitution, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCity, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelApprove, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCheck, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== panelStaff ========
        {

            //======== tabbedPaneStaff ========
            {
                tabbedPaneStaff.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        switch (tabbedPaneStaff.getSelectedIndex()){
                            case 0:
                                initDeliverTable();
                                break;
                            case 1:
                                initFinancialTable();
                                break;
                            case 2:
                                initTrunkTable();
                                break;
                        }
                    }
                });

                //======== scrollPanelDeliver ========
                {
                    scrollPanelDeliver.setViewportView(tableDeliver);
                }
                tabbedPaneStaff.addTab("\u5feb\u9012\u5458", scrollPanelDeliver);

                //======== scrollPanelFinancial ========
                {
                    scrollPanelFinancial.setViewportView(tableFinancial);
                }
                tabbedPaneStaff.addTab("\u8d22\u52a1\u4eba\u5458", scrollPanelFinancial);

                //======== scrollPanelTrunk ========
                {
                    scrollPanelTrunk.setViewportView(tableTrunk);
                }
                tabbedPaneStaff.addTab("\u8d27\u8f66\u9a7e\u9a76\u5458", scrollPanelTrunk);

                //======== scrollPanelCenter ========
                {

                    //---- labelCenter ----
                    labelCenter.setText("\u8bf7\u9009\u62e9\u4e2d\u8f6c\u4e2d\u5fc3:");

                    //---- buttonEnsureCenter ----
                    buttonEnsureCenter.setText("\u786e\u8ba4");
                    buttonEnsureCenter.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonEnsureCenterMouseClicked(e);
                        }
                    });

                    //======== scrollPaneCenter ========
                    {
                        scrollPaneCenter.setViewportView(tableCenter);
                    }

                    GroupLayout scrollPanelCenterLayout = new GroupLayout(scrollPanelCenter);
                    scrollPanelCenter.setLayout(scrollPanelCenterLayout);
                    scrollPanelCenterLayout.setHorizontalGroup(
                            scrollPanelCenterLayout.createParallelGroup()
                                    .addGroup(scrollPanelCenterLayout.createSequentialGroup()
                                            .addContainerGap(39, Short.MAX_VALUE)
                                            .addComponent(labelCenter)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(comboBoxCenter, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(buttonEnsureCenter)
                                            .addGap(142, 142, 142))
                                    .addComponent(scrollPaneCenter, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    );
                    scrollPanelCenterLayout.setVerticalGroup(
                            scrollPanelCenterLayout.createParallelGroup()
                                    .addGroup(scrollPanelCenterLayout.createSequentialGroup()
                                            .addGroup(scrollPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(buttonEnsureCenter)
                                                    .addComponent(labelCenter)
                                                    .addComponent(comboBoxCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(scrollPaneCenter, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                    );
                }
                tabbedPaneStaff.addTab("\u4e2d\u8f6c\u4e2d\u5fc3", scrollPanelCenter);

                //======== scrollPanelBusiness ========
                {

                    //---- labelBusiness ----
                    labelBusiness.setText("\u8bf7\u9009\u62e9\u8425\u4e1a\u5385:");

                    //---- buttonEnsureBusiness ----
                    buttonEnsureBusiness.setText("\u786e\u8ba4");
                    buttonEnsureBusiness.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonEnsureBusinessMouseClicked(e);
                        }
                    });

                    //======== scrollPaneBusiness ========
                    {
                        scrollPaneBusiness.setViewportView(tableBusiness);
                    }

                    GroupLayout scrollPanelBusinessLayout = new GroupLayout(scrollPanelBusiness);
                    scrollPanelBusiness.setLayout(scrollPanelBusinessLayout);
                    scrollPanelBusinessLayout.setHorizontalGroup(
                            scrollPanelBusinessLayout.createParallelGroup()
                                    .addGroup(scrollPanelBusinessLayout.createSequentialGroup()
                                            .addGap(83, 83, 83)
                                            .addComponent(labelBusiness)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboBoxBusinessCity, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboBoxBusinessNum, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(buttonEnsureBusiness)
                                            .addContainerGap(17, Short.MAX_VALUE))
                                    .addComponent(scrollPaneBusiness, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    );
                    scrollPanelBusinessLayout.setVerticalGroup(
                            scrollPanelBusinessLayout.createParallelGroup()
                                    .addGroup(scrollPanelBusinessLayout.createSequentialGroup()
                                            .addGroup(scrollPanelBusinessLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(comboBoxBusinessNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(comboBoxBusinessCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(buttonEnsureBusiness)
                                                    .addComponent(labelBusiness))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(scrollPaneBusiness, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                }
                tabbedPaneStaff.addTab("\u8425\u4e1a\u5385", scrollPanelBusiness);

                //======== scrollPanelStorage ========
                {

                    //---- labelCenterStorage ----
                    labelCenterStorage.setText("\u8bf7\u9009\u62e9\u4ed3\u5e93:");

                    //---- buttonEnsureStorage ----
                    buttonEnsureStorage.setText("\u786e\u8ba4");
                    buttonEnsureStorage.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonEnsureStorageMouseClicked(e);
                        }
                    });

                    //======== scrollPaneStorage ========
                    {
                        scrollPaneStorage.setViewportView(tableStorage);
                    }

                    GroupLayout scrollPanelStorageLayout = new GroupLayout(scrollPanelStorage);
                    scrollPanelStorage.setLayout(scrollPanelStorageLayout);
                    scrollPanelStorageLayout.setHorizontalGroup(
                            scrollPanelStorageLayout.createParallelGroup()
                                    .addGroup(scrollPanelStorageLayout.createSequentialGroup()
                                            .addContainerGap(87, Short.MAX_VALUE)
                                            .addComponent(labelCenterStorage)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(comboBoxStorage, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(buttonEnsureStorage)
                                            .addGap(142, 142, 142))
                                    .addComponent(scrollPaneStorage, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    );
                    scrollPanelStorageLayout.setVerticalGroup(
                            scrollPanelStorageLayout.createParallelGroup()
                                    .addGroup(scrollPanelStorageLayout.createSequentialGroup()
                                            .addGroup(scrollPanelStorageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(buttonEnsureStorage)
                                                    .addComponent(labelCenterStorage)
                                                    .addComponent(comboBoxStorage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(scrollPaneStorage, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                }
                tabbedPaneStaff.addTab("\u4ed3\u5e93\u7ba1\u7406\u5458", scrollPanelStorage);
            }

            //---- buttonAddStaff ----
            buttonAddStaff.setText("\u6dfb\u52a0\u5458\u5de5");
            buttonAddStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddStaffMouseClicked(e);
                }
            });

            //---- buttonMoveStaff ----
            buttonMoveStaff.setText("\u79fb\u52a8\u5458\u5de5");
            buttonMoveStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonMoveStaffMouseClicked(e);
                }
            });

            //---- buttonDeleteStaff ----
            buttonDeleteStaff.setText("\u5220\u9664\u5458\u5de5");
            buttonDeleteStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDeleteStaffMouseClicked(e);
                }
            });

            //---- buttonExitStaff ----
            buttonExitStaff.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitStaffMouseClicked(e);
                }
            });

            //---- labelStaffSuccess ----
            labelStaffSuccess.setText("\u6dfb\u52a0\u6210\u529f");

            GroupLayout panelStaffLayout = new GroupLayout(panelStaff);
            panelStaff.setLayout(panelStaffLayout);
            panelStaffLayout.setHorizontalGroup(
                    panelStaffLayout.createParallelGroup()
                            .addGroup(panelStaffLayout.createSequentialGroup()
                                    .addComponent(tabbedPaneStaff, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addGroup(panelStaffLayout.createParallelGroup()
                                            .addGroup(panelStaffLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(buttonExitStaff, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(buttonMoveStaff, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(buttonDeleteStaff, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(buttonAddStaff)
                                            .addComponent(labelStaffSuccess, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelStaffLayout.setVerticalGroup(
                    panelStaffLayout.createParallelGroup()
                            .addGroup(panelStaffLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelStaffSuccess, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonAddStaff, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonDeleteStaff, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonMoveStaff, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonExitStaff, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            .addComponent(tabbedPaneStaff, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
        }


        //======== panelSalary ========
        {

            //======== scrollPanelSalary ========
            {
                scrollPanelSalary.setViewportView(tableSalary);
            }

            //---- buttonAddSalary ----
            buttonAddSalary.setText("\u6dfb\u52a0\u5de5\u8d44");
            buttonAddSalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddSalaryMouseClicked(e);
                }
            });

            //---- buttonModifySalary ----
            buttonModifySalary.setText("\u4fdd\u5b58\u4fee\u6539");
            buttonModifySalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonModifySalaryMouseClicked(e);
                }
            });

            //---- buttonExitSalary ----
            buttonExitSalary.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitSalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitSalaryMouseClicked(e);
                }
            });

            //---- labelSalarySuccess ----
            labelSalarySuccess.setText("\u4fee\u6539\u6210\u529f");

            GroupLayout panelSalaryLayout = new GroupLayout(panelSalary);
            panelSalary.setLayout(panelSalaryLayout);
            panelSalaryLayout.setHorizontalGroup(
                    panelSalaryLayout.createParallelGroup()
                            .addGroup(panelSalaryLayout.createSequentialGroup()
                                    .addComponent(scrollPanelSalary, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelSalaryLayout.createParallelGroup()
                                            .addGroup(panelSalaryLayout.createSequentialGroup()
                                                    .addComponent(labelSalarySuccess, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(buttonExitSalary, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonModifySalary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonAddSalary, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            panelSalaryLayout.setVerticalGroup(
                    panelSalaryLayout.createParallelGroup()
                            .addGroup(panelSalaryLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(labelSalarySuccess, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonAddSalary, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(buttonModifySalary, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(buttonExitSalary, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            .addComponent(scrollPanelSalary, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
        }

        //======== panelCity ========
        {

            //---- labelLeaveCity ----
            labelLeaveCity.setText("\u51fa\u53d1\u57ce\u5e02:");

            //---- labelArrivalCity ----
            labelArrivalCity.setText("\u5230\u8fbe\u57ce\u5e02");

            //---- buttonEnsureCity ----
            buttonEnsureCity.setText("\u786e\u8ba4");
            buttonEnsureCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonEnsureCityMouseClicked(e);
                }
            });

            //======== scrollPanelCity ========
            {
                scrollPanelCity.setViewportView(tableCity);
            }

            //---- buttonAddCity ----
            buttonAddCity.setText("\u6dfb\u52a0\u4fe1\u606f");
            buttonAddCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddCityMouseClicked(e);
                }
            });

            //---- buttonModifyCity ----
            buttonModifyCity.setText("\u4fdd\u5b58\u4fee\u6539");
            buttonModifyCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonModifyCityMouseClicked(e);
                }
            });

            //---- buttonExitCity ----
            buttonExitCity.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitCityMouseClicked(e);
                }
            });

            //---- labelCitySuccess ----
            labelCitySuccess.setText("\u4fee\u6539\u6210\u529f");

            GroupLayout panelCityLayout = new GroupLayout(panelCity);
            panelCity.setLayout(panelCityLayout);
            panelCityLayout.setHorizontalGroup(
                    panelCityLayout.createParallelGroup()
                            .addGroup(panelCityLayout.createSequentialGroup()
                                    .addGroup(panelCityLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(panelCityLayout.createSequentialGroup()
                                                    .addComponent(labelLeaveCity, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(comboBoxLeaveCity, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(labelArrivalCity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(comboBoxArrivalCity, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(buttonEnsureCity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(scrollPanelCity, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelCityLayout.createParallelGroup()
                                            .addComponent(labelCitySuccess, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonAddCity, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonModifyCity, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonExitCity, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())
            );
            panelCityLayout.setVerticalGroup(
                    panelCityLayout.createParallelGroup()
                            .addGroup(panelCityLayout.createSequentialGroup()
                                    .addGroup(panelCityLayout.createParallelGroup()
                                            .addGroup(panelCityLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(panelCityLayout.createParallelGroup()
                                                            .addGroup(panelCityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                                                                    .addComponent(labelLeaveCity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(labelArrivalCity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(comboBoxLeaveCity, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(comboBoxArrivalCity, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(buttonEnsureCity, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(labelCitySuccess, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCityLayout.createParallelGroup()
                                            .addGroup(panelCityLayout.createSequentialGroup()
                                                    .addGap(37, 37, 37)
                                                    .addComponent(scrollPanelCity, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelCityLayout.createSequentialGroup()
                                                    .addGap(14, 14, 14)
                                                    .addComponent(buttonAddCity, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(buttonModifyCity, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(buttonExitCity, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(68, Short.MAX_VALUE))
            );
        }

        //======== panelApprove ========
        {

            //======== tabbedPaneApprove ========
            {

                //======== scrollPanelEntruk ========
                {
                    scrollPanelEntruk.setViewportView(tableEntruk);
                }
                tabbedPaneApprove.addTab("\u88c5\u8f66\u5355", scrollPanelEntruk);

                //======== scrollPanelarrival ========
                {
                    scrollPanelarrival.setViewportView(tableArrival);
                }
                tabbedPaneApprove.addTab("\u5230\u8fbe\u5355", scrollPanelarrival);

                //======== scrollPanelReceipt ========
                {
                    scrollPanelReceipt.setViewportView(tableReceipt);
                }
                tabbedPaneApprove.addTab("\u6536\u6b3e\u5355", scrollPanelReceipt);

                //======== scrollPanelStorageOut ========
                {
                    scrollPanelStorageOut.setViewportView(tableStorageOut);
                }
                tabbedPaneApprove.addTab("\u51fa\u5e93\u5355", scrollPanelStorageOut);

                //======== scrollPanelPayment ========
                {
                    scrollPanelPayment.setViewportView(tablePayment);
                }
                tabbedPaneApprove.addTab("\u4ed8\u6b3e\u5355", scrollPanelPayment);

                //======== scrollPanelSend ========
                {
                    scrollPanelSend.setViewportView(tableSend);
                }
                tabbedPaneApprove.addTab("\u6d3e\u4ef6\u5355", scrollPanelSend);

                //======== scrollPanelTransfer ========
                {
                    scrollPanelTransfer.setViewportView(tableTransfer);
                }
                tabbedPaneApprove.addTab("\u4e2d\u8f6c\u5355", scrollPanelTransfer);

                //======== scrollPanelStorageIn ========
                {
                    scrollPanelStorageIn.setViewportView(tableStorageIn);
                }
                tabbedPaneApprove.addTab("\u5165\u5e93\u5355", scrollPanelStorageIn);

                //======== scrollPanelOrder ========
                {
                    scrollPanelOrder.setViewportView(tableOrder);
                }
                tabbedPaneApprove.addTab("\u5bc4\u4ef6\u5355", scrollPanelOrder);
            }

            //---- buttonApproveData ----
            buttonApproveData.setText("\u5ba1\u6279\u5355\u636e");
            buttonApproveData.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonApproveDataMouseClicked(e);
                }
            });

            //---- buttonModifyData ----
            buttonModifyData.setText("\u4fee\u6539\u5355\u636e");
            buttonModifyData.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonModifyDataMouseClicked(e);
                }
            });

            //---- buttonApproveAll ----
            buttonApproveAll.setText("\u5168\u90e8\u5ba1\u6279");
            buttonApproveAll.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonApproveAllMouseClicked(e);
                }
            });

            //---- buttonExitApprove ----
            buttonExitApprove.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitApprove.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitApproveMouseClicked(e);
                }
            });

            //---- label1 ----
            label1.setText("\u5ba1\u6279\u6210\u529f");

            GroupLayout panelApproveLayout = new GroupLayout(panelApprove);
            panelApprove.setLayout(panelApproveLayout);
            panelApproveLayout.setHorizontalGroup(
                    panelApproveLayout.createParallelGroup()
                            .addGroup(panelApproveLayout.createSequentialGroup()
                                    .addComponent(tabbedPaneApprove, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panelApproveLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(buttonModifyData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonApproveAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonApproveData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonExitApprove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelApproveLayout.setVerticalGroup(
                    panelApproveLayout.createParallelGroup()
                            .addGroup(panelApproveLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonApproveData, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonApproveAll, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonModifyData, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonExitApprove, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                    .addGap(13, 13, 13))
                            .addComponent(tabbedPaneApprove, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.initSalaryTable();
        this.initOrderTable();
        this.initSendTable();
        this.initPaymentTable();
        this.initReceiptTable();
        this.initEntruckTable();
        this.initarrivalTable();
      //  this.initCenterArrivalTable();
        this.initStorageInTable();
        this.initStorageOutTable();
        this.initTransferTable();
        tabbedPaneApprove.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu choice;
    private JMenu help;
    private JToggleButton buttonSalary;
    private JToggleButton buttonStaff;
    private JToggleButton buttonCity;
    private JToggleButton buttonApprove;
    private JToggleButton buttonCheck;
    private JLabel labelSalary;
    private JLabel labelInstitution;
    private JLabel labelCity;
    private JLabel labelApprove;
    private JLabel labelCheck;
    private JLabel id;
    private JLabel name;
    private JPanel panelStaff;
    public JTabbedPane tabbedPaneStaff;
    private JScrollPane scrollPanelDeliver;
    private JTable tableDeliver;
    private JScrollPane scrollPanelFinancial;
    private JTable tableFinancial;
    private JScrollPane scrollPanelTrunk;
    private JTable tableTrunk;
    private JScrollPane scrollPanelTrain;
    private JTable tableTrain;
    private JScrollPane scrollPanelPlane;
    private JTable tablePlane;
    private JPanel scrollPanelCenter;
    private JLabel labelCenter;
    private JComboBox comboBoxCenter;
    private JButton buttonEnsureCenter;
    private JScrollPane scrollPaneCenter;
    private JTable tableCenter;
    private JPanel scrollPanelBusiness;
    private JLabel labelBusiness;
    private JComboBox comboBoxBusinessCity;
    private JComboBox comboBoxBusinessNum;
    private JButton buttonEnsureBusiness;
    private JScrollPane scrollPaneBusiness;
    private JTable tableBusiness;
    private JPanel scrollPanelStorage;
    private JLabel labelCenterStorage;
    private JComboBox comboBoxStorage;
    private JButton buttonEnsureStorage;
    private JScrollPane scrollPaneStorage;
    private JTable tableStorage;
    private JButton buttonAddStaff;
    private JButton buttonMoveStaff;
    private JButton buttonDeleteStaff;
    private JButton buttonExitStaff;
    public JLabel labelStaffSuccess;
    private JPanel panelAll;
    private JPanel panelSalary;
    private JScrollPane scrollPanelSalary;
    private JTable tableSalary;
    private JButton buttonAddSalary;
    private JButton buttonModifySalary;
    private JButton buttonExitSalary;
    public JLabel labelSalarySuccess;
    private JPanel panelCity;
    private JLabel labelLeaveCity;
    private JComboBox comboBoxLeaveCity;
    private JLabel labelArrivalCity;
    private JComboBox comboBoxArrivalCity;
    private JButton buttonEnsureCity;
    private JScrollPane scrollPanelCity;
    private JTable tableCity;
    private JButton buttonAddCity;
    private JButton buttonModifyCity;
    private JButton buttonExitCity;
    private JLabel labelCitySuccess;
    private JPanel panelApprove;
    private JTabbedPane tabbedPaneApprove;
    private JScrollPane scrollPanelEntruk;
    private JTable tableEntruk;
    private JScrollPane scrollPanelarrival;
    private JTable tableArrival;
    private JScrollPane scrollPanelReceipt;
    private JTable tableReceipt;
    private JScrollPane scrollPanelStorageOut;
    private JTable tableStorageOut;
    private JScrollPane scrollPanelPayment;
    private JTable tablePayment;
    private JScrollPane scrollPanelSend;
    private JTable tableSend;
//    private JScrollPane scrollPanelCenterArrival;
//    private JTable tableCenterArrival;
    private JScrollPane scrollPanelTransfer;
    private JTable tableTransfer;
    private JScrollPane scrollPanelStorageIn;
    private JTable tableStorageIn;
    private JScrollPane scrollPanelOrder;
    private JTable tableOrder;
    private JButton buttonApproveData;
    private JButton buttonModifyData;
    private JButton buttonApproveAll;
    private JButton buttonExitApprove;
    private JLabel label1;
    DefaultTableModel centerModel = null;
    DefaultTableModel salaryModel = null;
    DefaultTableModel cityModel  = null;
    DefaultTableModel deliverModel = null;
    DefaultTableModel financialModel = null;
    DefaultTableModel trunkModel = null;
    DefaultTableModel businessModel = null;
    DefaultTableModel storageModel = null;
    DefaultTableModel orderModel = null;
    DefaultTableModel sendModel = null;
    DefaultTableModel paymentModel = null;
    DefaultTableModel receiptModel = null;
    DefaultTableModel entrukModel = null;
    DefaultTableModel arrivalModel = null;
    DefaultTableModel centerArrivalModel = null;
    DefaultTableModel storageInModel = null;
    DefaultTableModel storageOutModel = null;
    DefaultTableModel transferModel = null;
    Vector<Vector<String>> salaryModify = new Vector<Vector<String>>();
    Vector<Vector<String>> cityModify = new Vector<Vector<String>>();
    Vector<Vector<String>> sendModify = new Vector<Vector<String>>();
    Vector<Vector<String>> paymentModify = new Vector<Vector<String>>();
    Vector<Vector<String>> receiptModify = new Vector<Vector<String>>();
    Vector<Vector<String>> entrukModify = new Vector<Vector<String>>();
    Vector<Vector<String>> arrivalModify = new Vector<Vector<String>>();
 //   Vector<Vector<String>> centerArrivalModify = new Vector<Vector<String>>();
    Vector<Vector<String>> storageInModify = new Vector<Vector<String>>();
    Vector<Vector<String>> storageOutModify = new Vector<Vector<String>>();
    Vector<Vector<String>> transferModify = new Vector<Vector<String>>();
    Vector<Vector<String>> orderModify = new Vector<Vector<String>>();
    CompanyBLController controller = new CompanyBLController();

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}



