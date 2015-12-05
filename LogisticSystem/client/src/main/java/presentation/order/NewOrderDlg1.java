/*
 * Created by JFormDesigner on Fri Dec 04 20:50:09 CST 2015
 */

package presentation.order;

import java.awt.event.*;
import businesslogic.impl.order.Order;
import data.enums.ServiceType;
import data.vo.OrderVO;
import utils.Timestamper;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class NewOrderDlg1 extends JDialog {

    public OrderVO orderVO = null;

    public OrderVO getNewOrderInfo() {
        this.setVisible(true);
        return orderVO;
    }

    public NewOrderDlg1(Frame owner) {
        super(owner);
        initComponents();
    }

    public NewOrderDlg1(Dialog owner) {
        super(owner);
        initComponents();
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

        // 将城区选项添加到combox中
        cboxRblock.removeAllItems();
        cboxRblock.addItem("请选择区");
        String cityName = cboxRcity.getSelectedItem().toString();
        if (cityName.equals("请选择城市")) return;
        for (String block: new Order().getBlockByCity(cityName)) {
            cboxRblock.addItem(block);
        }
        cboxRblock.updateUI();
    }

    // 确定按钮MouserRelease事件
    private void button1MouseReleased(MouseEvent e) {
        orderVO = new OrderVO();

        // 检查信息是否填写完整
        if (textSname.getText().equals("")) {
            textSname.requestFocus();
            return;
        }
        if (textsphone.getText().equals("")) {
            textsphone.requestFocus();
            return;
        }
        if (textscompany.getText().equals("")) {
            textscompany.requestFocus();
            return;
        }
        if (textSAddress.getText().length() < 5) {
            textSAddress.requestFocus();
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

        // 检查数据格式是否正确
        if (!textRphone.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(this, "电话号码格式不正确", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textRphone.requestFocus();
            return;
        }
        if (!textsphone.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(this, "电话号码格式不正确", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textsphone.requestFocus();
            return;
        }

        orderVO = new OrderVO();
        orderVO.sname = textSname.getText();
        orderVO.scompany = textscompany.getText();
        orderVO.saddress = cboxScity.getSelectedItem().toString() + "-" + cboxSblock.getSelectedItem().toString() + "-" + textSAddress.getText();
        orderVO.sphone = textsphone.getText();
        orderVO.rname = textRname.getText();
        orderVO.rcompany = textRcompany.getText();
        orderVO.raddress = cboxRcity.getSelectedItem().toString() + "-" + cboxRblock.getSelectedItem().toString() + "-" + textRaddress.getText();
        orderVO.rphone = textRphone.getText();
        orderVO.date = Timestamper.getTimeByDate();
        orderVO.serviceType = (ServiceType) cboxServiceType.getSelectedItem();
    }

    private void btCancelMouseReleased(MouseEvent e) {
        int result = JOptionPane.showConfirmDialog(this, "确定要退出编辑订单？所有信息将不被保存。", "订单信息", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            orderVO = null;
            this.setVisible(false);
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(this, "确定要退出编辑订单？所有信息将不被保存。", "订单信息", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            orderVO = null;
            this.setVisible(false);
        }
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
        textsphone = new JTextField();
        textscompany = new JTextField();
        cboxRcity = new JComboBox();
        cboxRblock = new JComboBox();
        cboxSblock = new JComboBox();
        cboxScity = new JComboBox();
        lbRaddress2 = new JLabel();
        textRaddress = new JTextField();
        textSAddress = new JTextField();
        lbSaddress2 = new JLabel();
        lbReceiver = new JLabel();
        lbSender = new JLabel();
        btOK = new JButton();
        btCancel = new JButton();
        lbSaddress3 = new JLabel();
        cboxServiceType = new JComboBox();
        lbScompany2 = new JLabel();
        textFee = new JTextField();
        textTimeEvaluated = new JTextField();
        lbScompany3 = new JLabel();

        //======== this ========
        setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        setTitle("\u521b\u5efa\u65b0\u8ba2\u5355");
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

            //---- textsphone ----
            textsphone.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textscompany ----
            textscompany.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

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

            //---- textSAddress ----
            textSAddress.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

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
                    button1MouseReleased(e);
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

            //---- lbSaddress3 ----
            lbSaddress3.setText("\u5feb\u9012\u670d\u52a1\u7c7b\u578b\uff1a");
            lbSaddress3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- cboxServiceType ----
            cboxServiceType.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            cboxServiceType.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    cboxScityItemStateChanged(e);
                }
            });

            //---- lbScompany2 ----
            lbScompany2.setText("\u62a5\u4ef7\uff1a");
            lbScompany2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textFee ----
            textFee.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textTimeEvaluated ----
            textTimeEvaluated.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbScompany3 ----
            lbScompany3.setText("\u9884\u8ba1\u9001\u8fbe\u65f6\u95f4(\u5929)\uff1a");
            lbScompany3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(btOK)
                                    .addGap(18, 18, 18)
                                    .addComponent(btCancel))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(lbSender))
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
                                                    .addComponent(textsphone, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(lbScompany)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(textscompany, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(lbSaddress)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(cboxScity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(cboxSblock, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbSaddress2)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textSAddress, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lbRaddress2)
                                            .addGap(6, 6, 6)
                                            .addComponent(textRaddress, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(lbReceiver)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(panel1Layout.createParallelGroup()
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbRname)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textRname, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbRphone)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textRphone, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbRcompany)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(textRcompany, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(lbRaddress)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(cboxRcity, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(cboxRblock, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbSaddress3)
                                .addGap(6, 6, 6)
                                .addComponent(cboxServiceType, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbScompany2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFee, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbScompany3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textTimeEvaluated, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(16, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbReceiver)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textRname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRname)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRphone))
                                    .addComponent(textRphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textRcompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRcompany)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbRaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxRcity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxRblock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(textRaddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbRaddress2))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lbSender)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textSname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbSname)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lbSphone, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textsphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(textscompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbScompany)))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbSaddress, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxScity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxSblock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSaddress2)
                                    .addComponent(textSAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lbSaddress3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxServiceType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lbScompany3)
                                    .addComponent(lbScompany2)))
                            .addComponent(textTimeEvaluated, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
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
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // 初始化地址选择框。
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
    private JTextField textsphone;
    private JTextField textscompany;
    private JComboBox cboxRcity;
    private JComboBox cboxRblock;
    private JComboBox cboxSblock;
    private JComboBox cboxScity;
    private JLabel lbRaddress2;
    private JTextField textRaddress;
    private JTextField textSAddress;
    private JLabel lbSaddress2;
    private JLabel lbReceiver;
    private JLabel lbSender;
    private JButton btOK;
    private JButton btCancel;
    private JLabel lbSaddress3;
    private JComboBox cboxServiceType;
    private JLabel lbScompany2;
    private JTextField textFee;
    private JTextField textTimeEvaluated;
    private JLabel lbScompany3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
