/*
 * Created by JFormDesigner on Sat Dec 05 16:51:02 CST 2015
 */

package presentation.order;

import businesslogic.impl.order.Order;
import data.enums.DataState;
import data.enums.ServiceType;
import data.vo.OrderVO;
import utils.Timestamper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author mist
 */
public class NewOrderDlg extends JDialog {

    boolean isEditing = true;

    OrderVO orderVO = null;

    public OrderVO getNewOrderInfo() {
        this.setTitle("新建订单");
        isEditing = true;
        setEditable(true);
        orderVO = new OrderVO();
        this.setVisible(true);
        return orderVO;
    }

    /**
     * 根据已有订单信息显示窗口，并返回修改后的结果
     *
     * @param vo
     * @return
     */
    public OrderVO getModifiedOrderInfo(OrderVO vo) {
        orderVO = vo;

        textRcompany.setText(vo.rcompany);
        textRname.setText(vo.rname);
        textRphone.setText(vo.rphone);
        textSname.setText(vo.sname);
        textScompany.setText(vo.scompany);
        textSphone.setText(vo.sphone);
        String[] saddress = vo.saddress.split("[-]");
        String[] raddress = vo.raddress.split("[-]");
        cboxRcity.setSelectedItem(raddress[0]);
        cboxRcityItemStateChanged(null);
        cboxRblock.setSelectedItem(raddress[1]);
        cboxScity.setSelectedItem(saddress[0]);
        cboxScityItemStateChanged(null);
        cboxSblock.setSelectedItem(saddress[1]);

        textSaddress.setText(saddress[2]);
        textRaddress.setText(raddress[2]);

        textFee.setText(String.valueOf(vo.fee));
        textTimeEvaluated.setText(String.valueOf(vo.evaluatedTime));
        textWeight.setText(String.valueOf(vo.weight));
        textHeight.setText("1");
        textWidth.setText("1");
        textLength.setText(String.valueOf(vo.volume));

        cboxServiceType.setSelectedItem(vo.serviceType);

        if (vo.dataState == DataState.APPROVED) {
            setEditable(false);
            this.setTitle("订单查看模式");
        }
        else {
            setEditable(true);
            this.setTitle("订单编辑模式");
        }

        this.setVisible(true);

        return orderVO;
    }


    public NewOrderDlg(Frame owner) {
        super(owner);
        initComponents();
    }

    public NewOrderDlg(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void btOKMouseReleased(MouseEvent e) {
        if (!isEditing) {
            this.setVisible(false);
            return;
        }

        orderVO = new OrderVO();

        // 检查信息是否填写完整
        if (textSname.getText().equals("")) {
            textSname.requestFocus();
            return;
        }
        if (textSphone.getText().equals("")) {
            textSphone.requestFocus();
            return;
        }
        if (textScompany.getText().equals("")) {
            textScompany.requestFocus();
            return;
        }
        if (textSaddress.getText().length() < 5) {
            textSaddress.requestFocus();
            JOptionPane.showMessageDialog(this, "详细地址信息长度不能小于5个字符。", "订单信息", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (textRname.getText().equals("")) {
            textRname.requestFocus();
            return;
        }
        if (textRphone.getText().equals("")) {
            textRphone.requestFocus();
            return;
        }
        if (textRcompany.getText().equals("")) {
            textRcompany.requestFocus();
            return;
        }

        // 快件大小以及重量信息的检查
        if (!textLength.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textLength.requestFocus();
            return;
        }
        if (!textWidth.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textWidth.requestFocus();
            return;
        }
        if (!textHeight.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textHeight.requestFocus();
            return;
        }
        if (!textWeight.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textWeight.requestFocus();
            return;
        }

        if (textRaddress.getText().length() < 5) {
            textRaddress.requestFocus();
            JOptionPane.showMessageDialog(this, "详细地址信息长度不能小于5个字符。", "订单信息", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (cboxScity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxScity.requestFocus();
            return;
        }
        if (cboxSblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxSblock.requestFocus();
            return;
        }
        if (cboxRcity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRcity.requestFocus();
            return;
        }
        if (cboxRblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRblock.requestFocus();
            return;
        }
        if (cboxServiceType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的服务类型", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxServiceType.requestFocus();
            return;
        }

        // 检查电话号码格式是否正确
        if (!textRphone.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(this, "电话号码格式不正确", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textRphone.requestFocus();
            return;
        }
        if (!textSphone.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(this, "电话号码格式不正确", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textSphone.requestFocus();
            return;
        }

        orderVO.sname = textSname.getText();
        orderVO.scompany = textScompany.getText();
        orderVO.saddress = cboxScity.getSelectedItem().toString() + "-" + cboxSblock.getSelectedItem().toString() + "-" + textSaddress.getText();
        orderVO.sphone = textSphone.getText();
        orderVO.rname = textRname.getText();
        orderVO.rcompany = textRcompany.getText();
        orderVO.raddress = cboxRcity.getSelectedItem().toString() + "-" + cboxRblock.getSelectedItem().toString() + "-" + textRaddress.getText();
        orderVO.rphone = textRphone.getText();
        orderVO.date = Timestamper.getTimeByDate();
        orderVO.serviceType = (ServiceType) cboxServiceType.getSelectedItem();

        float height = Float.parseFloat(textHeight.getText());
        float width = Float.parseFloat(textWidth.getText());
        float length = Float.parseFloat(textLength.getText());
        orderVO.volume = height * width * length;
        orderVO.weight = Float.parseFloat(textWeight.getText());


        btFeeRefreshMouseReleased(e);
        btTimeRefreshMouseReleased(e);

        orderVO.fee = Float.parseFloat(textFee.getText());
        orderVO.evaluatedTime = Integer.parseInt(textTimeEvaluated.getText());


        int DlgResult = JOptionPane.showConfirmDialog(null, "报价：" + textFee.getText() + "元\n" + "预计" + textTimeEvaluated.getText() + "天内送达\n" + "是否保存订单？", "LCS物流管理系统", JOptionPane.OK_CANCEL_OPTION);
        if (DlgResult == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            return;
        }
    }

    private void cboxScityItemStateChanged(ItemEvent e) {
        cboxSblock.removeAllItems();
        cboxSblock.addItem("请选择区");
        String cityName = cboxScity.getSelectedItem().toString();
        if (cityName.equals("请选择城市")) return;
        for (String block: new Order().getBlockByCity(cityName)) {
            cboxSblock.addItem(block);
        }
        cboxSblock.updateUI();
    }

    private void cboxRcityItemStateChanged(ItemEvent e) {
        cboxRblock.removeAllItems();
        cboxRblock.addItem("请选择区");
        String cityName = cboxRcity.getSelectedItem().toString();
        if (cityName.equals("请选择城市")) return;
        for (String block: new Order().getBlockByCity(cityName)) {
            cboxRblock.addItem(block);
        }
        cboxRblock.updateUI();
    }

    private void btCancelMouseReleased(MouseEvent e) {

        orderVO = null;
        if (!isEditing) {
            this.setVisible(false);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "确定要退出编辑订单？所有信息将不被保存。", "订单信息", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            this.setVisible(false);
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        if (!isEditing) {
            this.setVisible(false);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "确定要退出编辑订单？所有信息将不被保存。", "订单信息", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            orderVO = null;
            this.setVisible(false);
        }
    }

    private void btFeeRefreshMouseReleased(MouseEvent e) {

        // 快件大小以及重量信息的检查
        if (!textLength.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textLength.requestFocus();
            return;
        }
        if (!textWidth.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textWidth.requestFocus();
            return;
        }
        if (!textHeight.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textHeight.requestFocus();
            return;
        }
        if (!textWeight.getText().matches("[0-9]*[.]?[0-9]*([Ee][-+]?[0-9]*)?")) {
            textWeight.requestFocus();
            return;
        }
        if (cboxScity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxScity.requestFocus();
            return;
        }
        if (cboxSblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxSblock.requestFocus();
            return;
        }
        if (cboxRcity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRcity.requestFocus();
            return;
        }
        if (cboxRblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRblock.requestFocus();
            return;
        }
        if (cboxServiceType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的服务类型", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxServiceType.requestFocus();
            return;
        }
        if (orderVO == null) orderVO = new OrderVO();
        textFee.setText("正在计算...");
        orderVO.saddress = cboxScity.getSelectedItem().toString() + "-" + cboxSblock.getSelectedItem().toString() + "-" + textSaddress.getText();
        orderVO.raddress = cboxRcity.getSelectedItem().toString() + "-" + cboxRblock.getSelectedItem().toString() + "-" + textRaddress.getText();
        orderVO.serviceType = (ServiceType) cboxServiceType.getSelectedItem();
        float height = Float.parseFloat(textHeight.getText());
        float width = Float.parseFloat(textWidth.getText());
        float length = Float.parseFloat(textLength.getText());
        orderVO.volume = height * width * length;
        orderVO.weight = Float.parseFloat(textWeight.getText());
        textFee.setText(String.valueOf(new Order().generateFee(orderVO)));
    }


    /**
     * 设置是否可编辑
     * @param editable
     */
    private void setEditable(boolean editable) {
        textRaddress.setEditable(editable);
        textRcompany.setEditable(editable);
        textRname.setEditable(editable);
        textRphone.setEditable(editable);
        textScompany.setEditable(editable);
        textSname.setEditable(editable);
        textSphone.setEditable(editable);
        textSaddress.setEditable(editable);
        textHeight.setEditable(editable);
        textLength.setEditable(editable);
        textWidth.setEditable(editable);
        textWeight.setEditable(editable);
        textLength.setEditable(editable);
        cboxRblock.setEnabled(editable);
        cboxScity.setEnabled(editable);
        cboxRcity.setEnabled(editable);
        cboxSblock.setEnabled(editable);
        cboxServiceType.setEnabled(editable);
        isEditing = editable;
    }

    private void btTimeRefreshMouseReleased(MouseEvent e) {
        if (cboxScity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxScity.requestFocus();
            return;
        }
        if (cboxSblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxSblock.requestFocus();
            return;
        }
        if (cboxRcity.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城市", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRcity.requestFocus();
            return;
        }
        if (cboxRblock.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的城区", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxRblock.requestFocus();
            return;
        }
        if (cboxServiceType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "请选择正确的服务类型", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxServiceType.requestFocus();
            return;
        }
        textTimeEvaluated.setText("正在计算...");
        orderVO.saddress = cboxScity.getSelectedItem().toString() + "-" + cboxSblock.getSelectedItem().toString() + "-" + textSaddress.getText();
        orderVO.raddress = cboxRcity.getSelectedItem().toString() + "-" + cboxRblock.getSelectedItem().toString() + "-" + textRaddress.getText();
        orderVO.serviceType = (ServiceType) cboxServiceType.getSelectedItem();
        textTimeEvaluated.setText(String.valueOf(new Order().evaluateTime(orderVO)));
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        lbRname = new JLabel();
        lbRphone = new JLabel();
        lbRaddress = new JLabel();
        lbRcompany = new JLabel();
        lbSname = new JLabel();
        lbSphone = new JLabel();
        lbSaddress = new JLabel();
        lbScompany = new JLabel();
        textRname = new JTextField();
        textRphone = new JTextField();
        textRcompany = new JTextField();
        textSname = new JTextField();
        textSphone = new JTextField();
        textScompany = new JTextField();
        cboxRcity = new JComboBox();
        cboxRblock = new JComboBox();
        cboxSblock = new JComboBox();
        cboxScity = new JComboBox();
        lbRaddress2 = new JLabel();
        textRaddress = new JTextField();
        textSaddress = new JTextField();
        lbSaddress2 = new JLabel();
        lbReceiver = new JLabel();
        lbSender = new JLabel();
        btOK = new JButton();
        btCancel = new JButton();
        cboxServiceType = new JComboBox();
        lbSaddress3 = new JLabel();
        lbRcompany2 = new JLabel();
        textFee = new JTextField();
        textTimeEvaluated = new JTextField();
        lbRcompany3 = new JLabel();
        lbSaddress4 = new JLabel();
        lbScompany2 = new JLabel();
        textLength = new JTextField();
        textWidth = new JTextField();
        lbScompany3 = new JLabel();
        textHeight = new JTextField();
        lbScompany4 = new JLabel();
        textWeight = new JTextField();
        lbScompany5 = new JLabel();
        btFeeRefresh = new JButton();
        btTimeRefresh = new JButton();

        //======== this ========
        setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        setTitle("\u8ba2\u5355\u4fe1\u606f");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRname ----
            lbRname.setText("\u59d3\u540d\uff1a");
            lbRname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRphone ----
            lbRphone.setText("\u7535\u8bdd\uff1a");
            lbRphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRaddress ----
            lbRaddress.setText("\u5730\u5740\uff1a");
            lbRaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRcompany ----
            lbRcompany.setText("\u5355\u4f4d\uff1a");
            lbRcompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSname ----
            lbSname.setText("\u59d3\u540d\uff1a");
            lbSname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSphone ----
            lbSphone.setText("\u7535\u8bdd\uff1a");
            lbSphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress ----
            lbSaddress.setText("\u5730\u5740\uff1a");
            lbSaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany ----
            lbScompany.setText("\u5355\u4f4d\uff1a");
            lbScompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textRname ----
            textRname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textRphone ----
            textRphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textRcompany ----
            textRcompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textSname ----
            textSname.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textSphone ----
            textSphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textScompany ----
            textScompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- cboxRcity ----
            cboxRcity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            cboxRcity.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cboxRcityItemStateChanged(e);
                }
            });

            //---- cboxRblock ----
            cboxRblock.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- cboxSblock ----
            cboxSblock.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- cboxScity ----
            cboxScity.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            cboxScity.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cboxScityItemStateChanged(e);
                }
            });

            //---- lbRaddress2 ----
            lbRaddress2.setText("\u8be6\u7ec6\u5730\u5740\uff1a");
            lbRaddress2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textRaddress ----
            textRaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textSaddress ----
            textSaddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress2 ----
            lbSaddress2.setText("\u8be6\u7ec6\u5730\u5740\uff1a");
            lbSaddress2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbReceiver ----
            lbReceiver.setText("\u6536\u4ef6\u65b9\uff1a");
            lbReceiver.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSender ----
            lbSender.setText("\u5bc4\u4ef6\u65b9\uff1a");
            lbSender.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btOK ----
            btOK.setText("\u786e\u5b9a");
            btOK.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btOKMouseReleased(e);
                }
            });

            //---- btCancel ----
            btCancel.setText("\u53d6\u6d88");
            btCancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btCancelMouseReleased(e);
                }
            });

            //---- cboxServiceType ----
            cboxServiceType.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress3 ----
            lbSaddress3.setText("\u5feb\u9012\u670d\u52a1\u7c7b\u578b\uff1a");
            lbSaddress3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRcompany2 ----
            lbRcompany2.setText("\u62a5\u4ef7\uff1a");
            lbRcompany2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textFee ----
            textFee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textTimeEvaluated ----
            textTimeEvaluated.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbRcompany3 ----
            lbRcompany3.setText("\u9884\u8ba1\u9001\u8fbe(\u5929)\uff1a");
            lbRcompany3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbSaddress4 ----
            lbSaddress4.setText("\u5feb\u4ef6\u4fe1\u606f\uff1a");
            lbSaddress4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany2 ----
            lbScompany2.setText("\u957f(cm)\uff1a");
            lbScompany2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textLength ----
            textLength.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textWidth ----
            textWidth.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany3 ----
            lbScompany3.setText("\u5bbd(cm)\uff1a");
            lbScompany3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textHeight ----
            textHeight.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany4 ----
            lbScompany4.setText("\u9ad8(cm)\uff1a");
            lbScompany4.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textWeight ----
            textWeight.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany5 ----
            lbScompany5.setText("\u91cd\u91cf\uff1a");
            lbScompany5.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btFeeRefresh ----
            btFeeRefresh.setIcon(new ImageIcon(getClass().getResource("/icons/refresh_16x16.png")));
            btFeeRefresh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btFeeRefreshMouseReleased(e);
                }
            });

            //---- btTimeRefresh ----
            btTimeRefresh.setIcon(new ImageIcon(getClass().getResource("/icons/refresh_16x16.png")));
            btTimeRefresh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btTimeRefreshMouseReleased(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(lbSaddress2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textSaddress, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(panel1Layout.createParallelGroup()
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbSname)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textSname, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbSphone)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textSphone, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbScompany)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textScompany, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbSaddress)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(cboxScity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(cboxSblock, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))))
                                        .addComponent(lbSender, GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(lbReceiver)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(lbRaddress2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textRaddress, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(lbRname)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textRname, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addGroup(panel1Layout.createParallelGroup()
                                                    .addComponent(lbRphone)
                                                    .addComponent(lbRaddress)
                                                    .addComponent(lbRcompany))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(cboxRcity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cboxRblock, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(textRcompany)
                                                    .addComponent(textRphone))))))
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addComponent(lbScompany5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textWeight, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbSaddress4)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(lbScompany2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textLength, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbScompany3)
                                    .addGap(6, 6, 6)
                                    .addComponent(textWidth, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbScompany4)
                                    .addGap(6, 6, 6)
                                    .addComponent(textHeight, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbSaddress3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboxServiceType, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbRcompany2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFee, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btFeeRefresh, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRcompany3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textTimeEvaluated, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btTimeRefresh, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btOK)
                        .addGap(18, 18, 18)
                        .addComponent(btCancel)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbSender))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbReceiver)))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbRphone)
                                            .addComponent(textRphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addComponent(lbRcompany)
                                        .addGap(22, 22, 22)
                                        .addComponent(lbRaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(textRcompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(cboxRcity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboxRblock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textSname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textRname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbRname))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbSname)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lbSphone, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textSphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textScompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbScompany)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbSaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxScity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxSblock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textSaddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRaddress2)
                            .addComponent(textRaddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSaddress2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbSaddress4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(lbScompany2))
                                .addComponent(textLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbScompany5)
                                        .addComponent(textWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbScompany3))))
                            .addComponent(textHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbScompany4)))
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lbSaddress3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(btTimeRefresh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btFeeRefresh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textTimeEvaluated, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbRcompany3)
                                        .addComponent(textFee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbRcompany2)))
                                .addComponent(cboxServiceType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btCancel)
                            .addComponent(btOK))
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
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        cboxRblock.addItem("请选择区");
        cboxRcity.addItem("请选择城市");
        for (String cityName: new Order().getCityList()) {
            cboxRcity.addItem(cityName);
        }
        cboxSblock.addItem("请选择区");
        cboxScity.addItem("请选择城市");
        for (String cityName: new Order().getCityList()) {
            cboxScity.addItem(cityName);
        }

        // 设置报价文本框和时间文本框不可编辑
        textFee.setEditable(false);
        textTimeEvaluated.setEditable(false);

        // 初始化快递服务类型
        cboxServiceType.addItem("请选择服务类型");
        for (ServiceType serviceType: ServiceType.values()) {
            cboxServiceType.addItem(serviceType);
        }

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // 让整个线程暂定在这个对话框处
        this.setModal(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel lbRname;
    private JLabel lbRphone;
    private JLabel lbRaddress;
    private JLabel lbRcompany;
    private JLabel lbSname;
    private JLabel lbSphone;
    private JLabel lbSaddress;
    private JLabel lbScompany;
    private JTextField textRname;
    private JTextField textRphone;
    private JTextField textRcompany;
    private JTextField textSname;
    private JTextField textSphone;
    private JTextField textScompany;
    private JComboBox cboxRcity;
    private JComboBox cboxRblock;
    private JComboBox cboxSblock;
    private JComboBox cboxScity;
    private JLabel lbRaddress2;
    private JTextField textRaddress;
    private JTextField textSaddress;
    private JLabel lbSaddress2;
    private JLabel lbReceiver;
    private JLabel lbSender;
    private JButton btOK;
    private JButton btCancel;
    private JComboBox cboxServiceType;
    private JLabel lbSaddress3;
    private JLabel lbRcompany2;
    private JTextField textFee;
    private JTextField textTimeEvaluated;
    private JLabel lbRcompany3;
    private JLabel lbSaddress4;
    private JLabel lbScompany2;
    private JTextField textLength;
    private JTextField textWidth;
    private JLabel lbScompany3;
    private JTextField textHeight;
    private JLabel lbScompany4;
    private JTextField textWeight;
    private JLabel lbScompany5;
    private JButton btFeeRefresh;
    private JButton btTimeRefresh;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
