package presentation.company;

import java.awt.event.*;
import businesslogic.impl.company.CompanyBLController;
import data.enums.ServiceType;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.*;
import data.vo.*;
import presentation.financial.FINANCE;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


/**
 * @author wyc
 */
public class companyManage extends JFrame {

    LoginMessage loginMessage = null;
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
    DefaultTableModel storageInModel = null;
    DefaultTableModel storageOutModel = null;
    DefaultTableModel transferModel = null;
    DefaultTableModel sFinancialModel = null;
    Vector<Vector<String>> salaryModify = new Vector<>();
    Vector<Vector<String>> cityModify = new Vector<>();
    Vector<Vector<String>> sendModify = new Vector<>();
    Vector<Vector<String>> paymentModify = new Vector<>();
    Vector<Vector<String>> receiptModify = new Vector<>();
    Vector<Vector<String>> entrukModify = new Vector<>();
    Vector<Vector<String>> arrivalModify = new Vector<>();
    Vector<Vector<String>> storageInModify = new Vector<>();
    Vector<Vector<String>> storageOutModify = new Vector<>();
    Vector<Vector<String>> transferModify = new Vector<>();
    Vector<Vector<String>> orderModify = new Vector<>();
    CompanyBLController controller = new CompanyBLController();

    public companyManage(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
        initComponents(loginMessage);
        this.setVisible(true);
    }

    //��ʾ���ʹ������
    private void buttonSalaryMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(true);
        buttonCheck.setSelected(false);
        buttonStaff.setSelected(false);
        buttonApprove.setSelected(false);
        buttonCity.setSelected(false);
        panelSalary.setBounds(0,0,792,360);
        panelSalary.setVisible(true);
        panelAll.remove(panelCity);
        panelAll.remove(panelApprove);
        panelAll.remove(panelStaff);
        panelAll.removeAll();
        panelAll.add(panelSalary, BorderLayout.CENTER);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //��ʾ���й������
    private void buttonCityMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(false);
        buttonCheck.setSelected(false);
        buttonStaff.setSelected(false);
        buttonApprove.setSelected(false);
        buttonCity.setSelected(true);
        panelCity.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelStaff);
        panelAll.remove(panelApprove);
        panelAll.removeAll();
        panelAll.add(panelCity, BorderLayout.CENTER);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //��ʾ�������ݽ���
    private void buttonApproveMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(false);
        buttonCheck.setSelected(false);
        buttonStaff.setSelected(false);
        buttonApprove.setSelected(true);
        buttonCity.setSelected(false);
        panelApprove.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelStaff);
        panelAll.remove(panelCity);
        panelAll.removeAll();
        panelAll.add(panelApprove, BorderLayout.CENTER);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //��ʾ�鿴ͳ�Ʊ������
    private void buttonCheckMouseClicked(MouseEvent e) {
        FINANCE finance = new FINANCE();
        finance.setVisible(false);
        JPanel panelCheck = finance.getPanelCheck();
        buttonSalary.setSelected(false);
        buttonCheck.setSelected(true);
        buttonStaff.setSelected(false);
        buttonApprove.setSelected(false);
        buttonCity.setSelected(false);
        panelAll.remove(panelCity);
        panelAll.remove(panelSalary);
        panelAll.remove(panelApprove);
        panelAll.remove(panelStaff);
        panelAll.add(panelCheck,BorderLayout.CENTER);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //��ʾԱ�������������
    private void buttonStaffMouseClicked(MouseEvent e) {
        buttonSalary.setSelected(false);
        buttonCheck.setSelected(false);
        buttonStaff.setSelected(true);
        buttonApprove.setSelected(false);
        buttonCity.setSelected(false);
        panelStaff.setBounds(0,0,792,360);
        panelAll.remove(panelSalary);
        panelAll.remove(panelCity);
        panelAll.remove(panelApprove);
        panelAll.removeAll();
        panelAll.add(panelStaff, BorderLayout.CENTER);
        panelAll.updateUI();
        panelAll.repaint();
    }

    //���Ա�����ʰ�ť
    private void buttonAddSalaryMouseClicked(MouseEvent e) {
        DialogAddSalary dialogAddSalary = new DialogAddSalary(this);
    }

    //�޸�Ա�����ʰ�ť
    private void buttonModifySalaryMouseClicked(MouseEvent e) {
        //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
        int row = tableSalary.getSelectedRow();
        int column = tableSalary.getSelectedColumn();
        boolean isValid = true;
        tableSalary.getCellEditor(row, column).stopCellEditing();
        for(int i=0;i<salaryModify.size();i++){
            System.out.println(salaryModify.size());
            //��ȡ�����͹��ʵ���Ϣ
            Iterator<String> info = salaryModify.get(i).iterator();
            String institution = info.next();
            String salary = info.next();
            if(controller.isValidNum(salary)) {
                ResultMessage resultmessage = controller.modifySalary(institution, salary);
                if (resultmessage == ResultMessage.SUCCESS) {
                } else if (resultmessage == ResultMessage.NOTCONNECTED) {
                    labelSalarySuccess.setText("");
                    JOptionPane.showMessageDialog(null, "�������...", "", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    labelSalarySuccess.setText("�޸�ʧ��!");
                    break;
                }
            }
            else{
                isValid = false;
            }
        }
        //����Ѿ����޸Ĺ��Ĺ���
        salaryModify.clear();
        if(isValid == false){
            labelSalarySuccess.setText("");
            this.initSalaryTable();
            JOptionPane.showMessageDialog(null, "��������ȷ������ֵ!", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            labelSalarySuccess.setText("�޸ĳɹ�!");
        }
    }

    //�˳����ʹ���ť
    private void buttonExitSalaryMouseClicked(MouseEvent e) {
        new DialogExit(this);
    }

    //ȷ��ѡ����а�ť,�����ʾ����֮��������Ϣ
    private void buttonEnsureCityMouseClicked(MouseEvent e) {
        //��ȡ�������к͵������
        String fromCity = (String) comboBoxLeaveCity.getSelectedItem();
        String toCity = (String) comboBoxArrivalCity.getSelectedItem();
        CityTransVO cityTransVO = controller.getCityTransInfo(fromCity,toCity);
        if(cityTransVO == null){
            JOptionPane.showMessageDialog(null,"����֮����Ϣ������!","",JOptionPane.ERROR_MESSAGE);
        }
        else {
            this.initCityTable(cityTransVO);
        }
    }

    //��ӳ���֮��������Ϣ��ť
    private void buttonAddCityMouseClicked(MouseEvent e) {
        DialogAddCity dialogAddCity = new DialogAddCity(this);
    }

    //�޸ĳ���֮��������Ϣ��ť
    private void buttonModifyCityMouseClicked(MouseEvent e) {
        //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
        int column = tableCity.getSelectedColumn();
        tableCity.getCellEditor(0,column).stopCellEditing();
        for(int i=0;i<cityModify.size();i++){
            Iterator<String> info = cityModify.get(i).iterator();
            String strDistance = info.next();
            String strTrunkPrice = info.next();
            String strTrainPrice = info.next();
            String strPlanePrice = info.next();
            if(controller.isValidNum(strDistance)&&controller.isValidNum(strTrunkPrice)&&controller.isValidNum(strTrainPrice)&&controller.isValidNum(strPlanePrice)) {
                double distance = Double.valueOf(strDistance);
                double trunkPrice = Double.valueOf(strTrunkPrice);
                double trainPrice = Double.valueOf(strTrainPrice);
                double planePrice = Double.valueOf(strPlanePrice);
                String fromCity = (String) comboBoxLeaveCity.getSelectedItem();
                String toCity = (String) comboBoxArrivalCity.getSelectedItem();
                CityTransVO cityTransVO = new CityTransVO(fromCity, toCity, distance, trunkPrice, trainPrice, planePrice);
                ResultMessage resultmessage = controller.modifyCityInfo(cityTransVO);
                if (resultmessage == ResultMessage.SUCCESS) {
                    labelCitySuccess.setText("�޸ĳɹ�!");
                } else if (resultmessage == ResultMessage.NOTEXIST) {
                    JOptionPane.showMessageDialog(null, "������Ϣ������", "", JOptionPane.ERROR_MESSAGE);
                } else if (controller.modifySalary(info.next(), info.next()) == ResultMessage.NOTCONNECTED) {
                    JOptionPane.showMessageDialog(null, "�������...", "",JOptionPane.ERROR_MESSAGE);
                } else {
                    labelSalarySuccess.setText("�޸�ʧ��!");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "��������ȷ��Ϣ!", "", JOptionPane.ERROR_MESSAGE);
                //�ػ���
                buttonEnsureCityMouseClicked(null);
            }
        }
    }

    //�˳�����������Ϣ����ť
    private void buttonExitCityMouseClicked(MouseEvent e) {
        new DialogExit(this);
    }

    //��ʼ��Ա�����
    public void initStaff(){
        String institution;
        switch (tabbedPaneStaff.getSelectedIndex()){
            case 0:
                initDeliverTable();
                break;
            case 1:
                initFinancialTable();
                break;
            case 2:
                initSFinancialTable();
                break;
            case 3:
                initTrunkTable();
                break;
            case 4:
                institution = (String) comboBoxCenter.getSelectedItem();
                initCenterTable(institution);
                break;
            case 5:
                institution = (String) comboBoxBusinessNum.getSelectedItem();
                initBusinessTable(institution);
                break;
            case 6:
                institution = (String) comboBoxStorage.getSelectedItem();
                initStorageTable(institution);
                break;
        }
    }

    //���Ա����ť
    private void buttonAddStaffMouseClicked(MouseEvent e) {
        //���ݻ����Ĳ�ͬ��ѡ��ͬ�ķ�ʽ���ɻ�������
        String institution,userRole;
        int index= tabbedPaneStaff.getSelectedIndex();
        if(index>=0&&index<=2){
            institution = tabbedPaneStaff.getTitleAt(index);
            userRole = tabbedPaneStaff.getTitleAt(index);
        }
        //����ǻ�����ʻԱ��û��ʹ��Ȩ��
        else if(index==3){
            institution = tabbedPaneStaff.getTitleAt(index);
            userRole = null;
        }
        else if(index==4){
            //��ת��������(������)
            institution = (String) comboBoxCenter.getSelectedItem();
            userRole = tabbedPaneStaff.getTitleAt(index);
        }
        else if(index==5){
            //Ӫҵ������(��������)
            institution = (String) comboBoxBusinessNum.getSelectedItem();
            userRole = tabbedPaneStaff.getTitleAt(index);
        }
        else if(index==6){
            //�ֿ���(������)
            institution = (String) comboBoxStorage.getSelectedItem();
            userRole = tabbedPaneStaff.getTitleAt(index);
        }
        else{
            institution = null;
            userRole = null;
            JOptionPane.showMessageDialog(null,"��ѡ�����","",JOptionPane.ERROR_MESSAGE);
        }
        //���Ա�����
        DialogAddStaff dialogAddstaff = new DialogAddStaff(this,institution,userRole);
    }

    //ɾ��Ա����ť
    private void buttonDeleteStaffMouseClicked(MouseEvent e) {
        //���ݻ����Ĳ�ͬ��ѡ��ͬ�ķ�ʽ���ɻ�������
        String institution,id,name;
        int index= tabbedPaneStaff.getSelectedIndex();
        if(index==0){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) deliverModel.getValueAt(tableDeliver.getSelectedRow(),0);
            name = (String) deliverModel.getValueAt(tableDeliver.getSelectedRow(),1);
        }
        else if(index==1){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) financialModel.getValueAt(tableFinancial.getSelectedRow(),0);
            name = (String) financialModel.getValueAt(tableFinancial.getSelectedRow(),1);
        }
        else if(index==2){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) sFinancialModel.getValueAt(tableSFinancial.getSelectedRow(),0);
            name = (String) sFinancialModel.getValueAt(tableSFinancial.getSelectedRow(),1);
        }
        else if(index==3){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) trunkModel.getValueAt(tableTrunk.getSelectedRow(),0);
            name = (String) trunkModel.getValueAt(tableTrunk.getSelectedRow(),1);
        }
        else if(index==4){
            institution = (String) comboBoxCenter.getSelectedItem();
            id = (String) centerModel.getValueAt(tableCenter.getSelectedRow(),0);
            name = (String) centerModel.getValueAt(tableCenter.getSelectedRow(),1);
        }
        else if(index==5){
            institution = (String) comboBoxBusinessNum.getSelectedItem();
            id = (String) businessModel.getValueAt(tableBusiness.getSelectedRow(),0);
            name = (String) businessModel.getValueAt(tableBusiness.getSelectedRow(),1);
        }
        else if(index==6){
            institution = (String) comboBoxStorage.getSelectedItem();
            id = (String) storageModel.getValueAt(tableStorage.getSelectedRow(),0);
            name = (String) storageModel.getValueAt(tableStorage.getSelectedRow(),1);
        }
        else{
            institution = null;
            id = null;
            name = null;
            JOptionPane.showMessageDialog(null,"��ѡ�����","",JOptionPane.ERROR_MESSAGE);
        }
        //ȷ��ɾ��Ա������
        DialogDeleteStaff dialogDeleteStaff = new DialogDeleteStaff(this,institution,id,name);
    }

    //ɾ��Ա������
    public void buttonEnsureDeleteStaff(String institution,String id,MouseEvent e){
        ResultMessage resultMessage = controller.deleteStaff(institution,id);
        if(resultMessage == ResultMessage.SUCCESS){
            labelStaffSuccess.setText("ɾ���ɹ�!");
            //�ػ浱ǰ���
            this.initStaff();
        }
        else if(resultMessage == ResultMessage.NOTEXIST){
            labelStaffSuccess.setText("Ա��������!");
        }
        else if (resultMessage == ResultMessage.FAILED) {
            labelStaffSuccess.setText("ɾ��ʧ��");
        }
        else if (resultMessage == ResultMessage.NOTCONNECTED) {
            labelStaffSuccess.setText("");
            JOptionPane.showMessageDialog(null, "�������...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //�ƶ�Ա��������ť
    private void buttonMoveStaffMouseClicked(MouseEvent e) {
        //���ݻ����Ĳ�ͬ��ѡ��ͬ�ķ�ʽ���ɻ�������,���һ�ȡԱ����id
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
            id = (String) sFinancialModel.getValueAt(tableSFinancial.getSelectedRow(),0);
        }
        else if(index==3){
            institution = tabbedPaneStaff.getTitleAt(index);
            id = (String) trunkModel.getValueAt(tableTrunk.getSelectedRow(),0);
        }
        else if(index==4){
            institution = (String) comboBoxCenter.getSelectedItem();
            id = (String) centerModel.getValueAt(tableCenter.getSelectedRow(),0);
        }
        else if(index==5){
            institution = (String) comboBoxBusinessNum.getSelectedItem();
            id = (String) businessModel.getValueAt(tableBusiness.getSelectedRow(),0);
        }
        else if(index==6){
            institution = (String) comboBoxStorage.getSelectedItem();
            id = (String) storageModel.getValueAt(tableStorage.getSelectedRow(),0);
        }
        else {
            institution = null;
            id = null;
            JOptionPane.showMessageDialog(null, "��ѡ�����", "", JOptionPane.ERROR_MESSAGE);
        }
        //�ƶ�Ա�����
        DialogMoveStaff dialogMoveStaff = new DialogMoveStaff(this,institution,id);
    }

    //ȷ��ѡ��ֿ����ڳ��еİ�ť,�����ʾ�òֿ�����й�����Ա
    private void buttonEnsureStorageMouseClicked(MouseEvent e) {
        String city = (String) comboBoxStorage.getSelectedItem();
        this.initStorageTable(city);
    }

    //ȷ��ѡ��Ӫҵ����ת�������ڳ��а�ť,�����ʾ��Ӫҵ��������ҵ����Ա
    private void buttonEnsureBusinessMouseClicked(MouseEvent e) {
        String num = (String) comboBoxBusinessNum.getSelectedItem();
        this.initBusinessTable(num);
    }

    //ȷ��ѡ����ת�������ڳ��а�ť,�����ʾ����ת���ĵ�����ҵ����Ա
    private void buttonEnsureCenterMouseClicked(MouseEvent e) {
       String city = (String) comboBoxCenter.getSelectedItem();
        this.initCenterTable(city);
    }

    //�˳�Ա����������İ�ť
    private void buttonExitStaffMouseClicked(MouseEvent e) {
        new DialogExit(this);
    }

    //�����������ݵİ�ť
    private void buttonApproveDataMouseClicked(MouseEvent e) {
        //���ݵ����ͺ͵���
        String dataType,id;
        int index = tabbedPaneApprove.getSelectedIndex();
        System.out.println(index);
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
                id = (String) transferModel.getValueAt(tableTransfer.getSelectedRow(),0);
                break;
            case 7:
                id = (String) storageInModel.getValueAt(tableStorageIn.getSelectedRow(),0);
                break;
            case 8:
                id = (String) orderModel.getValueAt(tableOrder.getSelectedRow(),0);
                break;
            default:
                id = null;
                break;
        }
        resultMessage = controller.approveData(dataType,id);
        if(resultMessage == ResultMessage.SUCCESS){
            labelApprove.setText("�����ɹ�!");
            initData();
        }
        else if(resultMessage == ResultMessage.FAILED){
            labelApprove.setText("����ʧ��!");
        }
        else if(resultMessage == ResultMessage.NOTCONNECTED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null, "�������...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //����ͬ�������е��ݵİ�ť
    private void buttonApproveAllMouseClicked(MouseEvent e) {
        //���ݵ�����
        String dataType;
        int index = tabbedPaneApprove.getSelectedIndex();
        dataType = tabbedPaneApprove.getTitleAt(index);
        ResultMessage resultMessage = controller.approveAll(dataType);
        if (resultMessage == ResultMessage.SUCCESS) {
            labelApprove.setText("ȫ���������!");
            initData();
        }
        else if(resultMessage == ResultMessage.FAILED){
            labelApprove.setText("����ʧ��!");
        }
        else if(resultMessage == ResultMessage.NOTCONNECTED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null, "�������...", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    //��ʼ�����е���
    private void initData(){
        this.initOrderTable();
        this.initSendTable();
        this.initPaymentTable();
        this.initReceiptTable();
        this.initEntruckTable();
        this.initArrivalTable();
        this.initStorageInTable();
        this.initStorageOutTable();
        this.initTransferTable();
    }

    //ȷ���޸ĵ��ݵİ�ť
    private void buttonModifyDataMouseClicked(MouseEvent e) {
        int index = tabbedPaneApprove.getSelectedIndex();
        this.modifyData(index);
    }

    //�޸ĵ��ݵķ���
    private void modifyData(int index){
        int row,column;
        ResultMessage resultMessage;
        boolean isValidNum = true,isValidServicetype = true;
        //���ݵ��ݵ����Ͳ�ͬ���޸ĵ���
        switch (index) {
            case 0:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽EntruckModify��
                row = tableEntruk.getSelectedRow();
                column = tableEntruk.getSelectedColumn();
                tableEntruk.getCellEditor(row, column).stopCellEditing();
                for (int i = 0; i < entrukModify.size(); i++) {
                    EntruckListVO entruckListVO = new EntruckListVO();
                    Vector<String> entruck = entrukModify.get(i);
                    entruckListVO.entruckListID = entruck.get(0);
                    entruckListVO.fromID = entruck.get(1);
                    entruckListVO.destID =entruck.get(2);
                    entruckListVO.monitorName = entruck.get(3);
                    entruckListVO.escortName = entruck.get(4);
                    resultMessage = controller.modifyEntruck(entruckListVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
                break;
            case 1:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
                row = tableArrival.getSelectedRow();
                column = tableArrival.getSelectedColumn();
                tableArrival.getCellEditor(row, column).stopCellEditing();
                for (int i = 0; i < arrivalModify.size(); i++) {
                    ArrivalVO arrivalVO = new ArrivalVO();
                    Vector<String> arrival = arrivalModify.get(i);
                    arrivalVO.setId(Long.valueOf(arrival.get(0)));
                    arrivalVO.setFromName(arrival.get(1));
                    arrivalVO.setDestName(arrival.get(2));
                    arrivalVO.setDate(arrival.get(3));
                    resultMessage = controller.modifyArrival(arrivalVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 2:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
                row = tableReceipt.getSelectedRow();
                column = tableReceipt.getSelectedColumn();
                tableReceipt.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<receiptModify.size();i++){
                    ReceiptVO receiptVO = new ReceiptVO();
                    Vector<String> receipt = receiptModify.get(i);
                    receiptVO.setId(Long.valueOf(receipt.get(0)));
                    receiptVO.setSender(receipt.get(1));
                    receiptVO.setMoney(Double.valueOf(receipt.get(2)));
                    receiptVO.setDate(receipt.get(3));
                    resultMessage = controller.modifyReceipt(receiptVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 3:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
                row = tableStorageOut.getSelectedRow();
                column = tableStorageOut.getSelectedColumn();
                tableStorageOut.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<storageOutModify.size();i++){
                    StorageOutVO storageOutVO = new StorageOutVO();
                    Vector<String> storageOut = storageOutModify.get(i);
                    storageOutVO.setId(storageOut.get(0));
                    storageOutVO.setTransferNum(storageOut.get(1));
                    storageOutVO.setTransferType(storageOut.get(2));
                    storageOutVO.setDate(storageOut.get(3));
                    resultMessage = controller.modifyStorageOutList(storageOutVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 4:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
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
                    resultMessage = controller.modifyPayment(paymentVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 5:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
                row = tableSend.getSelectedRow();
                column = tableSend.getSelectedColumn();
                tableSend.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<sendModify.size();i++){
                    Vector<String> sendList = sendModify.get(i);
                    long id = Long.valueOf(sendList.get(0));
                    String sender = sendList.get(1);
                    SendListVO sendListVO = new SendListVO(sender,id);
                    resultMessage = controller.modifySend(sendListVO);
                    this.isSuccess(resultMessage);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 6:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽salaryModify��
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
                    resultMessage = controller.modifyTransferList(transferListVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 7:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽storageInModify��
                row = tableStorageIn.getSelectedRow();
                column = tableStorageIn.getSelectedColumn();
                tableStorageIn.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<storageInModify.size();i++){
                    Vector<String> storageIn = storageInModify.get(i);
                    StorageInVO storageInVO = new StorageInVO(null,null);
                    storageInVO.setId(Long.valueOf(storageIn.get(0)));
                    storageInVO.setDate(storageIn.get(2));
                    resultMessage = controller.modifyStorageInList(storageInVO);
                    this.isSuccess(resultMessage);
                    if(resultMessage!= ResultMessage.SUCCESS)
                        break;
                }
            case 8:
                //�����һ�����༭�ĵ�Ԫ������ֹͣ�༭״̬,���뵽orderModify��
                row = tableOrder.getSelectedRow();
                column = tableOrder.getSelectedColumn();
                tableOrder.getCellEditor(row, column).stopCellEditing();
                for(int i=0;i<orderModify.size();i++){
                    Iterator<String> order = orderModify.get(i).iterator();
                    long id = Long.valueOf(order.next());
                    String sStockNum = order.next();
                    String sWeight = order.next();
                    String sFee = order.next();
                    String sServiceType = order.next();
                    //�ж��޸ĺ����ֵ�Ƿ���Ϲ淶
                    if(controller.isValidNum(sStockNum)&&controller.isValidNum(sWeight)&&controller.isValidNum(sFee)) {
                        //�ж��޸ĺ�Ŀ�������Ƿ���Ϲ淶
                        if(controller.isValidType(sServiceType)) {
                            int stockNum = Integer.valueOf(sStockNum);
                            double weight = Double.valueOf(sWeight);
                            double fee = Double.valueOf(sFee);
                            ServiceType serviceType = ServiceType.valueOf(sServiceType);
                            OrderVO orderVO = new OrderVO(stockNum, weight,serviceType, fee, id);
                            resultMessage = controller.modifyOrder(orderVO);
                            this.isSuccess(resultMessage);
                            if (resultMessage != ResultMessage.SUCCESS)
                                break;
                        }
                        else{
                            isValidServicetype = false;
                        }
                    }
                    else{
                        isValidNum = false;
                    }
                }
                if(!isValidNum){
                    JOptionPane.showMessageDialog(null,"��������ȷ��ֵ!","",JOptionPane.ERROR_MESSAGE);
                    initOrderTable();
                }
                if(!isValidServicetype){
                    JOptionPane.showMessageDialog(null,"��������ȷ�������!","",JOptionPane.ERROR_MESSAGE);
                    initOrderTable();
                }
                orderModify.clear();
            }
    }

    //����resultMessage���Ͳ�����Ӧ����
    private void isSuccess(ResultMessage resultMessage){
        if(resultMessage == ResultMessage.SUCCESS){
            labelApprove.setText("�޸ĳɹ�!");
        }
        else if(resultMessage == ResultMessage.NOTCONNECTED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null,"�������...","",JOptionPane.ERROR_MESSAGE);
        }
        else if(resultMessage == ResultMessage.FAILED){
            labelApprove.setText("");
            JOptionPane.showMessageDialog(null,"�޸�ʧ��...","",JOptionPane.ERROR_MESSAGE);
        }
    }

    //�˳����������İ�ť
    private void buttonExitApproveMouseClicked(MouseEvent e) {
        new DialogExit(this);
    }
	/*
	 // ��ʼ�������ܷ���

	private void initTable(String []columns,Vector data,JTable table,JScrollPane scrollPane,DefaultTableModel model){
		Vector<String> column = new Vector<String>();
		for(int i=0;i<columns.length;i++){
			column.add(columns[i]);
		}
		//ģ��
		model = new DefaultTableModel(data,column);
		//���
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
    //�п����� ʵ��ûʱ��������!!!!��Ȼ�����е��ظ�!!!!
    //����ȫд����������!!!!!!


    /*
     * ��ʼ��Salary���
     */
    public void initSalaryTable(){
        //��ͷ
        Vector<String> salaryColumns = new Vector<String>();
        salaryColumns.add("Ա������");
        salaryColumns.add("Ա������");
        salaryColumns.add("���㷽ʽ");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> salaryVO = null;
        Vector<Vector<String>> salaryData = new Vector<Vector<String>>();
        ArrayList<SalaryVO> salaryVOs = controller.searchAllSalary();
     //   ArrayList<SalaryVO> salaryVOs = new ArrayList<SalaryVO>();
        for(int i=0;i<salaryVOs.size();i++){
            salaryVO = new Vector<String>();
            salaryVO.add(salaryVOs.get(i).getInstitution());
            salaryVO.add(salaryVOs.get(i).getSalary()+"");
            salaryVO.add(salaryVOs.get(i).getType());
            salaryData.add(salaryVO);
        }
        //ģ��
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
        //���
        tableSalary = new JTable(salaryModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                if(column==1)
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
     * ��ʼ��City���
     */
    public void initCityTable(CityTransVO cityTransVO){
        //��ͷ
        Vector<String> cityColumns = new Vector<String>();
        cityColumns.add("����(km)");
        cityColumns.add("�����۸�(/����/��)");
        cityColumns.add("�𳵼۸�(/����/��)");
        cityColumns.add("�ɻ��۸�(/����/��)");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> cityVO = new Vector<String>();
        Vector<Vector<String>> cityData = new Vector<Vector<String>>();
        cityVO.add(cityTransVO.getDistance()+"");
        cityVO.add(cityTransVO.getTrunkPrice()+"");
        cityVO.add(cityTransVO.getTrainPrice()+"");
        cityVO.add(cityTransVO.getPlanePrice()+"");
        cityData.add(cityVO);
        //ģ��
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
        //���
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
     * ��ʼ��Deliver(���Ա)���
     */
    public void initDeliverTable(){
        //��ͷ
        Vector<String> deliverColumns = new Vector<String>();
        deliverColumns.add("id");
        deliverColumns.add("����");
        deliverColumns.add("�Ա�");
        deliverColumns.add("���֤��");
        deliverColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> deliverData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //ģ��
        deliverModel = new DefaultTableModel(deliverData,deliverColumns);
        //���
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
     * ��ʼ��Financial(��ͨ������Ա)���
     */
    public void initFinancialTable(){
        //��ͷ
        Vector<String> financialColumns = new Vector<String>();
        financialColumns.add("id");
        financialColumns.add("����");
        financialColumns.add("�Ա�");
        financialColumns.add("���֤��");
        financialColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> financialData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //ģ��
        financialModel = new DefaultTableModel(financialData,financialColumns);
        //���
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
     * ��ʼ��SFinancial(�߼�������Ա)���
     */
    public void initSFinancialTable(){
        //��ͷ
        Vector<String> financialColumns = new Vector<String>();
        financialColumns.add("id");
        financialColumns.add("����");
        financialColumns.add("�Ա�");
        financialColumns.add("���֤��");
        financialColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> financialData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //ģ��
        sFinancialModel = new DefaultTableModel(financialData,financialColumns);
        //���
        tableSFinancial = new JTable(sFinancialModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableSFinancial.getTableHeader().setReorderingAllowed(false);
        tableSFinancial.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableSFinancial.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollSFinancial.getViewport().add(tableSFinancial);
    }

    /*
     * ��ʼ��Trunk(������ʻԱ)���
     */
    public void initTrunkTable(){
        //��ͷ
        Vector<String> trunkColumns = new Vector<String>();
        trunkColumns.add("id");
        trunkColumns.add("����");
        trunkColumns.add("�Ա�");
        trunkColumns.add("���֤��");
        trunkColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> trunkData = this.getStaff(tabbedPaneStaff.getTitleAt(tabbedPaneStaff.getSelectedIndex()));
        //ģ��
        trunkModel = new DefaultTableModel(trunkData,trunkColumns);
        //���
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
     * ��ʼ��Center(��ת����ҵ��Ա)���
     */
    public void initCenterTable(String institution){
        //��ͷ
        Vector<String> centerColumns = new Vector<String>();
        centerColumns.add("id");
        centerColumns.add("����");
        centerColumns.add("�Ա�");
        centerColumns.add("���֤��");
        centerColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> centerData = this.getStaff(institution);
        Vector<Vector<String>> centerStaff = new Vector<>();
        for(int i=0;i<centerData.size();i++){
            if(centerData.get(i).lastElement().equals("��ת����ҵ��Ա"))
                centerStaff.add(centerData.get(i));
        }
        //ģ��
        centerModel = new DefaultTableModel(centerStaff,centerColumns);
        //���
        tableCenter = new JTable(centerModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableCenter.getTableHeader().setReorderingAllowed(false);
        tableCenter.getTableHeader().setPreferredSize(new Dimension(40,30));
        tableCenter.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //ע��������Pane����Panel!!!
        scrollPaneCenter.getViewport().add(tableCenter);


    }

    /*
     * ��ʼ��Business(Ӫҵ��ҵ��Ա)���
     */
    public void initBusinessTable(String institution){
        //��ͷ
        Vector<String> businessColumns = new Vector<String>();
        businessColumns.add("id");
        businessColumns.add("����");
        businessColumns.add("�Ա�");
        businessColumns.add("���֤��");
        businessColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> businessData = this.getStaff(institution);
        //ģ��
        businessModel = new DefaultTableModel(businessData,businessColumns);
        //���
        tableBusiness = new JTable(businessModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableBusiness.getTableHeader().setReorderingAllowed(false);
        tableBusiness.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableBusiness.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //ע��������Pane����Panel!!!
        scrollPaneBusiness.getViewport().add(tableBusiness);
    }

    /*
     * ��ʼ��Storage(�ֿ����Ա)���
     */
    public void initStorageTable(String institution){
        //��ͷ
        Vector<String> storageColumns = new Vector<String>();
        storageColumns.add("id");
        storageColumns.add("����");
        storageColumns.add("�Ա�");
        storageColumns.add("���֤��");
        storageColumns.add("��ϵ�绰");
        //����
        Vector<Vector<String>> storageData = this.getStaff(institution);
        Vector<Vector<String>> storageStaff = new Vector<>();
        for(int i=0;i<storageData.size();i++){
            if(storageData.get(i).lastElement().equals("�ֿ����Ա"))
                storageStaff.add(storageData.get(i));
        }
        //ģ��
        storageModel = new DefaultTableModel(storageStaff,storageColumns);
        //���
        tableStorage = new JTable(storageModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableStorage.getTableHeader().setReorderingAllowed(false);
        tableStorage.getTableHeader().setPreferredSize(new Dimension(40,40));
        tableStorage.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //ע��������Pane����Panel!!!
        scrollPaneStorage.getViewport().add(tableStorage);
    }

    /*
     * ��ȡ��Ա��Ϣ���ܷ���
     */
    private Vector<Vector<String>> getStaff(String institution){
        Vector<String> staffVO = null;
        Vector<Vector<String>> staffData = new Vector<Vector<String>>();
        ArrayList<StaffVO> staffVOs = controller.getStaffByInstitution(institution);
       // ArrayList<StaffVO> staffVOs = new ArrayList<>();
        for(int i=0;i<staffVOs.size();i++){
            staffVO = new Vector<String>();
            staffVO.add(staffVOs.get(i).getId()+"");
            staffVO.add(staffVOs.get(i).getName());
            if(staffVOs.get(i).gender)
                staffVO.add("Ů");
            else
                staffVO.add("��");
            staffVO.add(staffVOs.get(i).getIdcardNum());
            staffVO.add(staffVOs.get(i).getPhoneNum()+"");
            if(staffVOs.get(i).getUserRole()!=null) {
                staffVO.add(staffVOs.get(i).getUserRole().toString());
            }
            staffData.add(staffVO);
        }
        return staffData;
    }

    /*
     * ��ʼ��Order(�ļ�������)���
     */
    private void initOrderTable(){
        //��ͷ
        Vector<String> orderColumns = new Vector<String>();
        orderColumns.add("�ļ�����");
        orderColumns.add("�����");
        orderColumns.add("����");
        orderColumns.add("�˷�");
        orderColumns.add("���䷽ʽ");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> orderVO;
        Vector<Vector<String>> orderData = new Vector<Vector<String>>();
        ArrayList<OrderPO> orderPOs = controller.getUnapprovedOrderList();
        for(int i=0;i<orderPOs.size();i++){
            orderVO = new Vector<>();
            OrderPO orderPO = orderPOs.get(i);
            orderVO.add(orderPO.getSerialNum()+"");
            orderVO.add(orderPO.getStockNum()+"");
            orderVO.add(orderPO.getWeight()+"");
            orderVO.add(orderPO.getFee()+"");
            orderVO.add(orderPO.getServiceType().toString());
            orderData.add(orderVO);
        }
        //ģ��
        orderModel = new DefaultTableModel(orderData,orderColumns);
        orderModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> order = new Vector<>();
                int row = tableOrder.getSelectedRow();
                for(int i=0;i<tableOrder.getColumnCount();i++){
                    order.add((String) orderModel.getValueAt(row, i));
                }
                orderModify.addElement(order);
            }
        });
        //���
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
     * ��ʼ��Send(�ɼ�������)���
     */
    private void initSendTable(){
        //��ͷ
        Vector<String> sendColumns = new Vector<String>();
        sendColumns.add("�ɼ�����");
        sendColumns.add("�ɼ���");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> sendVO;
        Vector<Vector<String>> sendData = new Vector<Vector<String>>();
        ArrayList<SendListPO> sendListPOs = controller.getUnapprovedSendList();
        for(int i=0;i<sendListPOs.size();i++){
            sendVO = new Vector<>();
            SendListPO sendListPO = sendListPOs.get(i);
            sendVO.add(sendListPO.getSerialNum()+"");
            sendVO.add(sendListPO.getSender());
            sendData.add(sendVO);
        }
        //ģ��
        sendModel = new DefaultTableModel(sendData,sendColumns);
        sendModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> send = new Vector<>();
                int row = tableSend.getSelectedRow();
                for(int i=0;i<send.size();i++){
                    send.add((String) sendModel.getValueAt(row, i));
                }
                sendModify.addElement(send);
            }
        });
        //���
        tableSend = new JTable(sendModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableSend.getTableHeader().setReorderingAllowed(false);
        tableSend.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableSend.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelSend.getViewport().add(tableSend);
    }

    /*
     * ��ʼ��Payment(�������)���
     */
    private void initPaymentTable(){
        //��ͷ
        Vector<String> paymentColumns = new Vector<String>();
        paymentColumns.add("�����");
        paymentColumns.add("�����˺�");
        paymentColumns.add("������");
        paymentColumns.add("��Ŀ");
        paymentColumns.add("������");
        paymentColumns.add("��������");
        paymentColumns.add("��ע");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> paymentVO;
        Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
        ArrayList<PaymentPO> paymentPOs = controller.getUnapprovedPaymentList();
        for(int i=0;i<paymentPOs.size();i++){
            PaymentPO paymentPO = paymentPOs.get(i);
            paymentVO = new Vector<>();
            paymentVO.add(paymentPO.getSerialNum()+"");
            paymentVO.add(paymentPO.getAccount());
            paymentVO.add(paymentPO.getName());
            paymentVO.add(paymentPO.getInfo());
            paymentVO.add(paymentPO.getMoney()+"");
            paymentVO.add(paymentPO.getDate());
            paymentVO.add(paymentPO.getExInfo());
            paymentData.add(paymentVO);
        }
        //ģ��
        paymentModel = new DefaultTableModel(paymentData,paymentColumns);
        paymentModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> payment = new Vector<>();
                int row = tablePayment.getSelectedRow();
                for(int i=0;i<tablePayment.getColumnCount();i++){
                    payment.add((String) paymentModel.getValueAt(row, i));
                }
                paymentModify.addElement(payment);
            }
        });
        //���
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
     * ��ʼ��Receipt(�տ����)���
     */
    private void initReceiptTable(){
        //��ͷ
        Vector<String> receiptColumns = new Vector<String>();
        receiptColumns.add("�տ��");
        receiptColumns.add("�տ���");
        receiptColumns.add("�տ���");
        receiptColumns.add("�տ�����");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> receiptVO;
        Vector<Vector<String>> receiptData = new Vector<Vector<String>>();
        ArrayList<ReceiptPO> receiptPOs = controller.getUnapprovedReceiptList();
        for(int i=0;i<receiptPOs.size();i++){
            ReceiptPO receiptPO = receiptPOs.get(i);
            receiptVO = new Vector<>();
            receiptVO.add(receiptPO.getSerialNum()+"");
            receiptVO.add(receiptPO.getSender());
            receiptVO.add(receiptPO.getMoney()+"");
            receiptVO.add(receiptPO.getDate());
            receiptData.add(receiptVO);
        }
        //ģ��
        receiptModel = new DefaultTableModel(receiptData,receiptColumns);
        receiptModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> receipt = new Vector<>();
                int row = tableReceipt.getSelectedRow();
                for(int i=0;i<tableReceipt.getColumnCount();i++){
                    receipt.add((String) receiptModel.getValueAt(row, i));
                }
                receiptModify.addElement(receipt);
            }
        });
        //���
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
     * ��ʼ��Entruk(װ�������������
     */
    private void initEntruckTable(){
        //��ͷ
        Vector<String> entruckColumns = new Vector<String>();
        entruckColumns.add("װ�������");
        entruckColumns.add("Ӫҵ�����");
        entruckColumns.add("�����");
        entruckColumns.add("��װԱ");
        entruckColumns.add("Ѻ��Ա");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> entrukVO;
        Vector<Vector<String>> entrukData = new Vector<Vector<String>>();
        ArrayList<EntruckPO> entruckPOs = controller.getUnapprovedEntruckList();
        for(int i=0;i<entruckPOs.size();i++){
            EntruckPO entruckPO = entruckPOs.get(i);
            entrukVO = new Vector<>();
            entrukVO.add(entruckPO.getSerialNum()+"");
            entrukVO.add(entruckPO.getFromID()+"");
            entrukVO.add(entruckPO.getDestID()+"");
            entrukVO.add(entruckPO.getMonitorName());
            entrukVO.add(entruckPO.getEscortName());
            entrukData.add(entrukVO);
        }
        //ģ��
        entrukModel = new DefaultTableModel(entrukData,entruckColumns);
        entrukModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> entruk = new Vector<>();
                int row = tableEntruk.getSelectedRow();
                for(int i=0;i<tableEntruk.getColumnCount();i++){
                    entruk.add((String) entrukModel.getValueAt(row, i));
                }
                entrukModify.addElement(entruk);
            }
        });
        //���
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
     * ��ʼ��arrival(���ﵥ����)���
     */
    private void initArrivalTable(){
        //��ͷ
        Vector<String> arrivalColumns = new Vector<String>();
        arrivalColumns.add("���ﵥ���");
        arrivalColumns.add("������");
        arrivalColumns.add("�����");
        arrivalColumns.add("��������");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> arrivalVO;
        Vector<Vector<String>> arrivalData = new Vector<>();
        ArrayList<ArrivalPO> arrivalPOs = controller.getUnapprovedArrivalList();
        for(int i=0;i<arrivalPOs.size();i++){
            ArrivalPO arrivalPO = arrivalPOs.get(i);
            arrivalVO = new Vector<>();
            arrivalVO.add(arrivalPO.getSerialNum()+"");
            arrivalVO.add(arrivalPO.getDestName());
            arrivalVO.add(arrivalPO.getFromName());
            arrivalVO.add(arrivalPO.getDate());
            arrivalData.add(arrivalVO);
        }
        //ģ��
        arrivalModel = new DefaultTableModel(arrivalData,arrivalColumns);
        arrivalModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> arrival = new Vector<>();
                int row = tableArrival.getSelectedRow();
                for(int i=0;i<tableArrival.getColumnCount();i++){
                    arrival.add((String) arrivalModel.getValueAt(row, i));
                }
                arrivalModify.addElement(arrival);
            }
        });
        //���
        tableArrival = new JTable(arrivalModel){
            private static final long serialVersionUid = 1L;

            public boolean isCellEditable(int row, int column){
                return false;}
        };
        tableArrival.getTableHeader().setReorderingAllowed(false);
        tableArrival.getTableHeader().setPreferredSize(new Dimension(30,30));
        tableArrival.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanelArrival.getViewport().add(tableArrival);
    }
    

    /*
     * ��ʼ��StorageIn(��ⵥ����)���
     */
    private void initStorageInTable(){
        //��ͷ
        Vector<String> storageInColumns = new Vector<>();
        storageInColumns.add("��ⵥ���");
        storageInColumns.add("Ŀ�ĵر��");
        storageInColumns.add("�������");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> storageInVO;
        Vector<Vector<String>> storageInData = new Vector<>();
        ArrayList<StorageInListPO> storageInListPOs = controller.getUnapprovedStorageInList();
        for(int i=0;i<storageInListPOs.size();i++){
            StorageInListPO storageInListPO = storageInListPOs.get(i);
            storageInVO = new Vector<>();
            storageInVO.add(storageInListPO.getTransferNum()+"");
            storageInVO.add(storageInListPO.getDate());
            storageInData.add(storageInVO);
        }
        //ģ��
        storageInModel = new DefaultTableModel(storageInData,storageInColumns);
        storageInModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> storageIn = new Vector<>();
                int row = tableStorageIn.getSelectedRow();
                for(int i=0;i<tableStorageIn.getColumnCount();i++){
                    storageIn.add((String) storageInModel.getValueAt(row, i));
                }
                storageInModify.addElement(storageIn);
            }
        });
        //���
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
     * ��ʼ��StorageOut(���ⵥ����)���
     */
    private void initStorageOutTable(){
        //��ͷ
        Vector<String> storageOutColumns = new Vector<>();
        storageOutColumns.add("��ⵥ���");
        storageOutColumns.add("Ŀ�ĵر��");
        storageOutColumns.add("װ����ʽ");
        storageOutColumns.add("��������");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> storageOutVO;
        Vector<Vector<String>> storageOutData = new Vector<>();
        ArrayList<StorageOutListPO> storageOutListPOs = controller.getUnapprovedStoragedOutList();
        for(int i=0;i<storageOutListPOs.size();i++){
            StorageOutListPO storageOutListPO = storageOutListPOs.get(i);
            storageOutVO = new Vector<>();
            storageOutVO.add(storageOutListPO.getSerialNum()+"");
            storageOutVO.add(storageOutListPO.getTransferNum()+"");
            storageOutVO.add(storageOutListPO.getTransferType().toString());
            storageOutVO.add(storageOutListPO.getDate());
            storageOutData.add(storageOutVO);
        }
        //ģ��
        storageOutModel = new DefaultTableModel(storageOutData,storageOutColumns);
        storageOutModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> storageOut = new Vector<>();
                int row = tableStorageOut.getSelectedRow();
                for(int i=0;i<tableStorageOut.getColumnCount();i++){
                    storageOut.add((String) storageOutModel.getValueAt(row, i));
                }
                storageOutModify.addElement(storageOut);
            }
        });
        //���
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
     * ��ʼ��Transfer(��ת������)���
     */
    private void initTransferTable(){
        //��ͷ
        Vector<String> transferColumns = new Vector<>();
        transferColumns.add("��ת�����");
        transferColumns.add("Ŀ�ĵ�(Ӫҵ��)");
        transferColumns.add("����/�ɻ����");
        transferColumns.add("��װԱ");
        transferColumns.add("�˷�");
        transferColumns.add("װ������");
        //����:��һ��Vector�������һ��VO,�ڶ���Vector���VO����
        Vector<String> transferVO;
        Vector<Vector<String>> transferData = new Vector<>();
        ArrayList<TransferListPO> transferListPOs = controller.getUnapprovedTransferList();
        for(int i=0;i<transferListPOs.size();i++){
            TransferListPO transferListPO = transferListPOs.get(i);
            transferVO = new Vector<>();
            transferVO.add(transferListPO.getSerialNum()+"");
            transferVO.add(transferListPO.getTargetCenterName());
            transferVO.add(transferListPO.getVehicleCode()+"");
            transferVO.add(transferListPO.getStaffName());
            transferVO.add(transferListPO.getFee()+"");
            transferVO.add(transferListPO.getDate());
            transferData.add(transferVO);
        }
        //ģ��
        transferModel = new DefaultTableModel(transferData,transferColumns);
        transferModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Vector<String> transfer = new Vector<>();
                int row = tableTransfer.getSelectedRow();
                for(int i=0;i<tableTransfer.getColumnCount();i++){
                    transfer.add((String) transferModel.getValueAt(row, i));
                }
                transferModify.addElement(transfer);
            }
        });
        //���
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

    private void thisWindowOpened(WindowEvent e) {
        this.panelAll.add(panelApprove, BorderLayout.CENTER);
    }

    private void buttonApproveAllMouseReleased(MouseEvent e) {

    }

    private void buttonModifyData2MouseReleased(MouseEvent e) {
        initData();
        tabbedPaneApprove.updateUI();
    }

    private void initComponents(LoginMessage loginMessage) {
        String [] citys = controller.getCitys();
        if(citys==null){
            JOptionPane.showMessageDialog(null,"�������...","",JOptionPane.INFORMATION_MESSAGE);
        }
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        choice = new JMenu();
        help = new JMenu();
        panel1 = new JPanel();
        panelAll = new JPanel();
        panelApprove = new JPanel();
        tabbedPaneApprove = new JTabbedPane();
        scrollPanelEntruk = new JScrollPane();
        tableEntruk = new JTable();
        scrollPanelArrival = new JScrollPane();
        tableArrival = new JTable();
        scrollPanelReceipt = new JScrollPane();
        tableReceipt = new JTable();
        scrollPanelStorageOut = new JScrollPane();
        tableStorageOut = new JTable();
        scrollPanelPayment = new JScrollPane();
        tablePayment = new JTable();
        scrollPanelSend = new JScrollPane();
        tableSend = new JTable();
        scrollPanelTransfer = new JScrollPane();
        tableTransfer = new JTable();
        scrollPanelStorageIn = new JScrollPane();
        tableStorageIn = new JTable();
        scrollPanelOrder = new JScrollPane();
        tableOrder = new JTable();
        buttonApproveData = new JButton();
        buttonApproveAll = new JButton();
        buttonModifyData = new JButton();
        buttonExitApprove = new JButton();
        buttonModifyData2 = new JButton();
        name = new JLabel();
        id = new JLabel();
        labelCheck = new JLabel();
        labelApprove = new JLabel();
        labelCity = new JLabel();
        labelInstitution = new JLabel();
        labelSalary = new JLabel();
        buttonSalary = new JToggleButton();
        buttonStaff = new JToggleButton();
        buttonCity = new JToggleButton();
        buttonApprove = new JToggleButton();
        buttonCheck = new JToggleButton();
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
        panelStaff = new JPanel();
        tabbedPaneStaff = new JTabbedPane();
        scrollPanelDeliver = new JScrollPane();
        tableDeliver = new JTable();
        scrollPanelFinancial = new JScrollPane();
        tableFinancial = new JTable();
        scrollSFinancial = new JScrollPane();
        tableSFinancial = new JTable();
        scrollPanelTrunk = new JScrollPane();
        tableTrunk = new JTable();
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

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();

        //======== menuBar ========
        {

            //======== choice ========
            {
                choice.setText("\u9009\u9879(C)");
                choice.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            }
            menuBar.add(choice);

            //======== help ========
            {
                help.setText("\u5e2e\u52a9(H)");
                help.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            }
            menuBar.add(help);
        }
        setJMenuBar(menuBar);

        //======== panel1 ========
        {

            //======== panelAll ========
            {
                panelAll.setLayout(new BorderLayout());

                //======== panelApprove ========
                {

                    //======== tabbedPaneApprove ========
                    {
                        tabbedPaneApprove.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                        //======== scrollPanelEntruk ========
                        {

                            //---- tableEntruk ----
                            tableEntruk.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                            scrollPanelEntruk.setViewportView(tableEntruk);
                        }
                        tabbedPaneApprove.addTab("\u88c5\u8f66\u5355", scrollPanelEntruk);

                        //======== scrollPanelArrival ========
                        {

                            //---- tableArrival ----
                            tableArrival.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                            scrollPanelArrival.setViewportView(tableArrival);
                        }
                        tabbedPaneApprove.addTab("\u5230\u8fbe\u5355", scrollPanelArrival);

                        //======== scrollPanelReceipt ========
                        {

                            //---- tableReceipt ----
                            tableReceipt.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                            scrollPanelReceipt.setViewportView(tableReceipt);
                        }
                        tabbedPaneApprove.addTab("\u6536\u6b3e\u5355", scrollPanelReceipt);

                        //======== scrollPanelStorageOut ========
                        {

                            //---- tableStorageOut ----
                            tableStorageOut.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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

                            //---- tableSend ----
                            tableSend.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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

                            //---- tableStorageIn ----
                            tableStorageIn.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                            scrollPanelStorageIn.setViewportView(tableStorageIn);
                        }
                        tabbedPaneApprove.addTab("\u5165\u5e93\u5355", scrollPanelStorageIn);

                        //======== scrollPanelOrder ========
                        {

                            //---- tableOrder ----
                            tableOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                            scrollPanelOrder.setViewportView(tableOrder);
                        }
                        tabbedPaneApprove.addTab("\u5bc4\u4ef6\u5355", scrollPanelOrder);
                    }

                    //---- buttonApproveData ----
                    buttonApproveData.setText("\u5ba1\u6279\u5355\u636e");
                    buttonApproveData.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    buttonApproveData.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonApproveDataMouseClicked(e);
                        }
                    });

                    //---- buttonApproveAll ----
                    buttonApproveAll.setText("\u5168\u90e8\u5ba1\u6279");
                    buttonApproveAll.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    buttonApproveAll.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonApproveAllMouseClicked(e);
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonApproveAllMouseReleased(e);
                        }
                    });

                    //---- buttonModifyData ----
                    buttonModifyData.setText("\u4fee\u6539\u5355\u636e");
                    buttonModifyData.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    buttonModifyData.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonModifyDataMouseClicked(e);
                        }
                    });

                    //---- buttonExitApprove ----
                    buttonExitApprove.setText("\u9000\u51fa\u7cfb\u7edf");
                    buttonExitApprove.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    buttonExitApprove.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            buttonExitApproveMouseClicked(e);
                        }
                    });

                    //---- buttonModifyData2 ----
                    buttonModifyData2.setText("\u5237\u65b0");
                    buttonModifyData2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    buttonModifyData2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonModifyData2MouseReleased(e);
                        }
                    });

                    GroupLayout panelApproveLayout = new GroupLayout(panelApprove);
                    panelApprove.setLayout(panelApproveLayout);
                    panelApproveLayout.setHorizontalGroup(
                        panelApproveLayout.createParallelGroup()
                            .addGroup(panelApproveLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tabbedPaneApprove, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelApproveLayout.createParallelGroup()
                                    .addGroup(panelApproveLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelApproveLayout.createParallelGroup()
                                            .addComponent(buttonApproveData, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonApproveAll, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonModifyData, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(buttonExitApprove, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonModifyData2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                    );
                    panelApproveLayout.setVerticalGroup(
                        panelApproveLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panelApproveLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelApproveLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(tabbedPaneApprove, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                                    .addGroup(panelApproveLayout.createSequentialGroup()
                                        .addComponent(buttonApproveData)
                                        .addGap(7, 7, 7)
                                        .addComponent(buttonApproveAll)
                                        .addGap(7, 7, 7)
                                        .addComponent(buttonModifyData)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonModifyData2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                                        .addComponent(buttonExitApprove)))
                                .addContainerGap())
                    );
                }
                panelAll.add(panelApprove, BorderLayout.CENTER);
            }

            //---- name ----
            name.setText("\u59d3\u540d:\u5f69\u7b14");
            name.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- id ----
            id.setText("id:12345");
            id.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelCheck ----
            labelCheck.setText("\u67e5\u770b\u62a5\u8868");
            labelCheck.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelApprove ----
            labelApprove.setText("\u5ba1\u6279\u5355\u636e");
            labelApprove.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelCity ----
            labelCity.setText("\u57ce\u5e02\u7ba1\u7406");
            labelCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelInstitution ----
            labelInstitution.setText("\u673a\u6784\u7ba1\u7406");
            labelInstitution.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelSalary ----
            labelSalary.setText("\u5de5\u8d44\u7ba1\u7406");
            labelSalary.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- buttonSalary ----
            buttonSalary.setIcon(new ImageIcon(getClass().getResource("/icons/salary_72x72.png")));
            buttonSalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonSalaryMouseClicked(e);
                }
            });

            //---- buttonStaff ----
            buttonStaff.setIcon(new ImageIcon(getClass().getResource("/icons/institution_72x72.png")));
            buttonStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonStaffMouseClicked(e);
                }
            });

            //---- buttonCity ----
            buttonCity.setIcon(new ImageIcon(getClass().getResource("/icons/map_72x72.png")));
            buttonCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonCityMouseClicked(e);
                }
            });

            //---- buttonApprove ----
            buttonApprove.setIcon(new ImageIcon(getClass().getResource("/icons/approval_72x72.png")));
            buttonApprove.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonApproveMouseClicked(e);
                }
            });

            //---- buttonCheck ----
            buttonCheck.setIcon(new ImageIcon(getClass().getResource("/icons/Sales_report_72x72.png")));
            buttonCheck.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonCheckMouseClicked(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(labelSalary)
                                .addGap(62, 62, 62)
                                .addComponent(labelInstitution)
                                .addGap(47, 47, 47)
                                .addComponent(labelCity)
                                .addGap(61, 61, 61)
                                .addComponent(labelApprove)
                                .addGap(57, 57, 57)
                                .addComponent(labelCheck)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(name, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(panelAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(buttonSalary, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonStaff, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonCity, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonApprove, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonCheck, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(id, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(buttonSalary, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSalary))
                            .addComponent(buttonStaff, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(buttonCity, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelCity)
                                    .addComponent(labelInstitution)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(buttonApprove, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelApprove))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(buttonCheck, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(name)
                                    .addComponent(labelCheck))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== panelSalary ========
        {

            //======== scrollPanelSalary ========
            {

                //---- tableSalary ----
                tableSalary.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                scrollPanelSalary.setViewportView(tableSalary);
            }

            //---- buttonAddSalary ----
            buttonAddSalary.setText("\u6dfb\u52a0\u5de5\u8d44");
            buttonAddSalary.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonAddSalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddSalaryMouseClicked(e);
                }
            });

            //---- buttonModifySalary ----
            buttonModifySalary.setText("\u4fdd\u5b58\u4fee\u6539");
            buttonModifySalary.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonModifySalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonModifySalaryMouseClicked(e);
                }
            });

            //---- buttonExitSalary ----
            buttonExitSalary.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitSalary.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonExitSalary.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitSalaryMouseClicked(e);
                }
            });

            GroupLayout panelSalaryLayout = new GroupLayout(panelSalary);
            panelSalary.setLayout(panelSalaryLayout);
            panelSalaryLayout.setHorizontalGroup(
                panelSalaryLayout.createParallelGroup()
                    .addGroup(panelSalaryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPanelSalary, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSalaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonModifySalary, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(buttonAddSalary, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(buttonExitSalary, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(labelSalarySuccess, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addContainerGap())
            );
            panelSalaryLayout.setVerticalGroup(
                panelSalaryLayout.createParallelGroup()
                    .addGroup(panelSalaryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelSalaryLayout.createParallelGroup()
                            .addGroup(panelSalaryLayout.createSequentialGroup()
                                .addComponent(scrollPanelSalary, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(panelSalaryLayout.createSequentialGroup()
                                .addComponent(labelSalarySuccess, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAddSalary)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonModifySalary)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                                .addComponent(buttonExitSalary)
                                .addGap(14, 14, 14))))
            );
        }

        //======== panelCity ========
        {

            //---- labelLeaveCity ----
            labelLeaveCity.setText("\u51fa\u53d1\u57ce\u5e02:");
            labelLeaveCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBoxLeaveCity ----
            comboBoxLeaveCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- labelArrivalCity ----
            labelArrivalCity.setText("\u5230\u8fbe\u57ce\u5e02");
            labelArrivalCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- comboBoxArrivalCity ----
            comboBoxArrivalCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- buttonEnsureCity ----
            buttonEnsureCity.setText("\u786e\u8ba4");
            buttonEnsureCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonEnsureCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonEnsureCityMouseClicked(e);
                }
            });

            //======== scrollPanelCity ========
            {

                //---- tableCity ----
                tableCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                scrollPanelCity.setViewportView(tableCity);
            }

            //---- buttonAddCity ----
            buttonAddCity.setText("\u6dfb\u52a0\u4fe1\u606f");
            buttonAddCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonAddCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddCityMouseClicked(e);
                }
            });

            //---- buttonModifyCity ----
            buttonModifyCity.setText("\u4fdd\u5b58\u4fee\u6539");
            buttonModifyCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonModifyCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonModifyCityMouseClicked(e);
                }
            });

            //---- buttonExitCity ----
            buttonExitCity.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitCity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonExitCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitCityMouseClicked(e);
                }
            });

            GroupLayout panelCityLayout = new GroupLayout(panelCity);
            panelCity.setLayout(panelCityLayout);
            panelCityLayout.setHorizontalGroup(
                panelCityLayout.createParallelGroup()
                    .addGroup(panelCityLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelCityLayout.createParallelGroup()
                            .addGroup(panelCityLayout.createSequentialGroup()
                                .addComponent(labelLeaveCity)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxLeaveCity, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelArrivalCity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxArrivalCity, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEnsureCity, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 101, Short.MAX_VALUE))
                            .addComponent(scrollPanelCity, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCityLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panelCityLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelCitySuccess, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(buttonAddCity, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(buttonModifyCity, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .addComponent(buttonExitCity, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
            );
            panelCityLayout.setVerticalGroup(
                panelCityLayout.createParallelGroup()
                    .addGroup(panelCityLayout.createSequentialGroup()
                        .addGroup(panelCityLayout.createParallelGroup()
                            .addGroup(panelCityLayout.createSequentialGroup()
                                .addComponent(labelCitySuccess, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAddCity)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonModifyCity)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                                .addComponent(buttonExitCity))
                            .addGroup(panelCityLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelCityLayout.createParallelGroup()
                                    .addGroup(panelCityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                                        .addComponent(comboBoxLeaveCity)
                                        .addComponent(comboBoxArrivalCity)
                                        .addComponent(labelLeaveCity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelArrivalCity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonEnsureCity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPanelCity, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                        .addContainerGap())
            );
        }

        //======== panelStaff ========
        {

            //======== tabbedPaneStaff ========
            {
                tabbedPaneStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //======== scrollPanelDeliver ========
                {
                    scrollPanelDeliver.setViewportView(tableDeliver);
                }
                tabbedPaneStaff.addTab("\u5feb\u9012\u5458", scrollPanelDeliver);

                //======== scrollPanelFinancial ========
                {

                    //---- tableFinancial ----
                    tableFinancial.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    scrollPanelFinancial.setViewportView(tableFinancial);
                }
                tabbedPaneStaff.addTab("\u666e\u901a\u8d22\u52a1\u4eba\u5458", scrollPanelFinancial);

                //======== scrollSFinancial ========
                {
                    scrollSFinancial.setViewportView(tableSFinancial);
                }
                tabbedPaneStaff.addTab("\u9ad8\u7ea7\u8d22\u52a1\u4eba\u5458", scrollSFinancial);

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
                                .addContainerGap(180, Short.MAX_VALUE)
                                .addComponent(labelCenter)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxCenter, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(buttonEnsureCenter)
                                .addGap(142, 142, 142))
                            .addComponent(scrollPaneCenter, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
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
                tabbedPaneStaff.addTab("\u4e2d\u8f6c\u4e2d\u5fc3\u4e1a\u52a1\u5458", scrollPanelCenter);

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
                                .addContainerGap(146, Short.MAX_VALUE))
                            .addComponent(scrollPaneBusiness, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
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
                tabbedPaneStaff.addTab("\u8425\u4e1a\u5385\u4e1a\u52a1\u5458", scrollPanelBusiness);

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
                                .addContainerGap(204, Short.MAX_VALUE)
                                .addComponent(labelCenterStorage)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxStorage, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(buttonEnsureStorage)
                                .addGap(142, 142, 142))
                            .addComponent(scrollPaneStorage, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
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
            buttonAddStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonAddStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAddStaffMouseClicked(e);
                }
            });

            //---- buttonMoveStaff ----
            buttonMoveStaff.setText("\u79fb\u52a8\u5458\u5de5");
            buttonMoveStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonMoveStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonMoveStaffMouseClicked(e);
                }
            });

            //---- buttonDeleteStaff ----
            buttonDeleteStaff.setText("\u5220\u9664\u5458\u5de5");
            buttonDeleteStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonDeleteStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDeleteStaffMouseClicked(e);
                }
            });

            //---- buttonExitStaff ----
            buttonExitStaff.setText("\u9000\u51fa\u7cfb\u7edf");
            buttonExitStaff.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            buttonExitStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonExitStaffMouseClicked(e);
                }
            });

            GroupLayout panelStaffLayout = new GroupLayout(panelStaff);
            panelStaff.setLayout(panelStaffLayout);
            panelStaffLayout.setHorizontalGroup(
                panelStaffLayout.createParallelGroup()
                    .addGroup(panelStaffLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPaneStaff)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelStaffLayout.createParallelGroup()
                            .addComponent(labelStaffSuccess, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelStaffLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(buttonAddStaff, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(buttonDeleteStaff, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(buttonMoveStaff, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                            .addComponent(buttonExitStaff, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
            );
            panelStaffLayout.setVerticalGroup(
                panelStaffLayout.createParallelGroup()
                    .addGroup(panelStaffLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelStaffLayout.createParallelGroup()
                            .addComponent(tabbedPaneStaff, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addGroup(panelStaffLayout.createSequentialGroup()
                                .addComponent(labelStaffSuccess, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonAddStaff)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDeleteStaff)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonMoveStaff)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                                .addComponent(buttonExitStaff)))
                        .addContainerGap())
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
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
                        initSFinancialTable();
                        break;
                    case 3:
                        initTrunkTable();
                        break;
                }
            }
        });
        //Ӫҵ��ҵ��Աѡ�����ʱ��ʾ������
        comboBoxBusinessCity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBoxBusinessNum.removeAllItems();
                ArrayList<String> businessOffices = controller.getBusinessOffices((String) comboBoxBusinessCity.getSelectedItem());
                for(int i=0;i<businessOffices.size();i++){
                    comboBoxBusinessNum.addItem(businessOffices.get(i));
                }
            }
        });
        this.initSalaryTable();
        this.initOrderTable();
        this.initSendTable();
        this.initPaymentTable();
        this.initReceiptTable();
        this.initEntruckTable();
        this.initArrivalTable();
        this.initStorageInTable();
        this.initStorageOutTable();
        this.initTransferTable();
        tabbedPaneApprove.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPaneStaff.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        //��ʾԱ����id������
        id.setText(loginMessage.getUserSN()+"");
        name.setText(controller.getNameById(loginMessage.getUserSN()));

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu choice;
    private JMenu help;
    private JPanel panel1;
    private JPanel panelAll;
    private JPanel panelApprove;
    private JTabbedPane tabbedPaneApprove;
    private JScrollPane scrollPanelEntruk;
    private JTable tableEntruk;
    private JScrollPane scrollPanelArrival;
    private JTable tableArrival;
    private JScrollPane scrollPanelReceipt;
    private JTable tableReceipt;
    private JScrollPane scrollPanelStorageOut;
    private JTable tableStorageOut;
    private JScrollPane scrollPanelPayment;
    private JTable tablePayment;
    private JScrollPane scrollPanelSend;
    private JTable tableSend;
    private JScrollPane scrollPanelTransfer;
    private JTable tableTransfer;
    private JScrollPane scrollPanelStorageIn;
    private JTable tableStorageIn;
    private JScrollPane scrollPanelOrder;
    private JTable tableOrder;
    private JButton buttonApproveData;
    private JButton buttonApproveAll;
    private JButton buttonModifyData;
    private JButton buttonExitApprove;
    private JButton buttonModifyData2;
    private JLabel name;
    private JLabel id;
    private JLabel labelCheck;
    private JLabel labelApprove;
    private JLabel labelCity;
    private JLabel labelInstitution;
    private JLabel labelSalary;
    private JToggleButton buttonSalary;
    private JToggleButton buttonStaff;
    private JToggleButton buttonCity;
    private JToggleButton buttonApprove;
    private JToggleButton buttonCheck;
    private JPanel panelSalary;
    private JScrollPane scrollPanelSalary;
    private JTable tableSalary;
    private JButton buttonAddSalary;
    private JButton buttonModifySalary;
    private JButton buttonExitSalary;
    private JLabel labelSalarySuccess;
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
    private JPanel panelStaff;
    private JTabbedPane tabbedPaneStaff;
    private JScrollPane scrollPanelDeliver;
    private JTable tableDeliver;
    private JScrollPane scrollPanelFinancial;
    private JTable tableFinancial;
    private JScrollPane scrollSFinancial;
    private JTable tableSFinancial;
    private JScrollPane scrollPanelTrunk;
    private JTable tableTrunk;
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
    private JLabel labelStaffSuccess;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JLabel getLabelCitySuccess() { return labelCitySuccess; }
    public JLabel getLabelSalarySuccess() { return labelSalarySuccess; }
    public JLabel getLabelStaffSuccess() { return labelStaffSuccess; }
    public JTabbedPane getTabbedPaneStaff() { return tabbedPaneStaff; }
}



