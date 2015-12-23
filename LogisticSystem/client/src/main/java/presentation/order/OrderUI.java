package presentation.order;

import businesslogic.impl.order.Order;
import businesslogic.impl.order.OrderBLController;
import data.enums.DataType;
import data.enums.POType;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.po.OrderPO;
import data.po.StaffPO;
import data.vo.OrderVO;
import data.vo.SignVO;
import utils.DataServiceFactory;
import utils.Timestamper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;
/*
 * Created by JFormDesigner on Thu Oct 29 23:00:05 CST 2015
 */


/**
 * @author sunhao
 */
public class OrderUI extends JFrame {

    LoginMessage loginMessage = null;

    NewOrderDlg newOrderDlg = new NewOrderDlg(this, loginMessage);

    public OrderUI(LoginMessage loginMessage) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        this.loginMessage = loginMessage;
    }

    private void btOrderMngMouseClicked(MouseEvent e) {
        btOrderMng.setSelected(true);
        refresh();
    }

    private void btOrderMngMouseReleased(MouseEvent e) {
        btOrderMng.setSelected(true);
        refresh();
    }

    private void refresh() {
        ArrayList<OrderVO> displayData = new OrderBLController().getDisplayData();
        Vector tabelData = ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector();
        tabelData.clear();
        Vector row = null;
        for (OrderVO order: displayData) {
            row = new Vector();
            row.add(order.id);
            row.add(order.date);
            row.add(order.sname);
            row.add(order.sphone);
            row.add(order.saddress);
            row.add(order.scompany);
            row.add(order.rname);
            row.add(order.rphone);
            row.add(order.raddress);
            row.add(order.rcompany);
            row.add(order.serviceType);
            tabelData.add(row);
        }
        tbOrderInfo.updateUI();
    }

    /**
     * 菜单项 新建 按钮点击事件
     * @param e
     */
    private void miNewOrderMouseReleased(MouseEvent e) {
        OrderVO newOrder = new NewOrderDlg(this, loginMessage).getNewOrderInfo();
        if (newOrder == null) return;
        ResultMessage result = new OrderBLController().createOrder(newOrder);
        if (result == ResultMessage.SUCCESS) {
            JOptionPane.showMessageDialog(this, "订单保存成功。", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "订单保存失败。请检查网络连接", "LCS物流管理系统", JOptionPane.WARNING_MESSAGE);
        }
        refresh();
    }

    /**
     * 删除按钮点击事件
     * @param e
     */
    private void btDeleteMouseReleased(MouseEvent e) {
        int selected = tbOrderInfo.getSelectedRow();
        long orderSn = (long)((Vector<Object>) ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector().get(selected)).get(0);
        ResultMessage result = new Order(loginMessage).deleteOrder(orderSn);
        if (result == ResultMessage.NOTEXIST) {
            JOptionPane.showMessageDialog(this, "订单删除失败！订单可能已经被删除。", "LCS物流管理系统", JOptionPane.WARNING_MESSAGE);
        } else if (result == ResultMessage.FAILED) {
            JOptionPane.showMessageDialog(this, "订单删除失败！订单已被审批，无法完成删除操作。", "LCS物流管理系统", JOptionPane.WARNING_MESSAGE);
        }
        refresh();
    }

    private void btSign2MouseReleased(MouseEvent e) {
        miNewOrderMouseReleased(e);
    }

    private void btModifyMouseReleased(MouseEvent e) {
        int row = tbOrderInfo.getSelectedRow();
        long sn = (long) ((Vector<Object>) ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector().get(row)).get(0);
        OrderVO orderInfo = new OrderVO(new Order(loginMessage).search(sn));
        orderInfo = newOrderDlg.getModifiedOrderInfo(orderInfo);
        if (orderInfo == null) return;
        new Order(loginMessage).modify(sn, orderInfo);
        refresh();
    }

    private void miModifyMouseReleased(MouseEvent e) {
        btModifyMouseReleased(e);
    }

    private void btSearchMouseReleased(MouseEvent e) {
        if (!tfOrderInput.getText().matches("[0-9]*") || tfOrderInput.getText().length() != 10) {
            tfOrderInput.requestFocus();
            return;
        }
        long sn = Long.parseLong(tfOrderInput.getText());
        OrderPO orderPO = new OrderBLController().search(sn);
        ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector().clear();
        if (orderPO == null) {
            tbOrderInfo.updateUI();
            tbOrderInfo.repaint();
            return;
        }
        Vector<Object> row = new Vector<>();
        row.add(orderPO.getSerialNum());
        row.add(Timestamper.getTimeByDate(orderPO.getGenDate()));
        row.add(orderPO.getSname());
        row.add(orderPO.getSphone());
        row.add(orderPO.getSaddress());
        row.add(orderPO.getScompany());
        row.add(orderPO.getRname());
        row.add(orderPO.getRphone());
        row.add(orderPO.getRaddress());
        row.add(orderPO.getRcompany());
        row.add(orderPO.getServiceType());
        ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector().add(row);
        tbOrderInfo.updateUI();
        tbOrderInfo.repaint();
    }

    private void btSignMouseReleased(MouseEvent e) {

        if (tbOrderInfo.getSelectedRowCount() == 0) {
            return;
        }
        int rowSelected = tbOrderInfo.getSelectedRow();
        Vector<Object> row = (Vector<Object>) ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector().get(rowSelected);
        long snSelected = (long)row.get(0);
        SignDlg signDlg = new SignDlg(this);
        if (new Order(loginMessage).isSigned(snSelected)) {
            signDlg.showSignInfo(snSelected);
            return;
        }

        SignVO signVO = signDlg.getSignInfo(snSelected);

        ResultMessage result = new Order(loginMessage).sign(signVO);
        if (result == ResultMessage.FAILED) {
            return;
        }
        if (result == ResultMessage.SUCCESS) {
            JOptionPane.showMessageDialog(this, "签收信息保存成功");
        }
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        mnFile = new JMenu();
        miNewOrder = new JMenuItem();
        miSign = new JMenuItem();
        miFindOrder = new JMenuItem();
        miLogout = new JMenuItem();
        miQuit = new JMenuItem();
        mnEdit = new JMenu();
        miModify = new JMenuItem();
        panel2 = new JPanel();
        tfOrderInput = new JTextField();
        btSearch = new JButton();
        lbOrderNum = new JLabel();
        scrollPane1 = new JScrollPane();
        tbOrderInfo = new JTable();
        btDelete = new JButton();
        btSign = new JButton();
        label1 = new JLabel();
        btOrderMng = new JToggleButton();
        btModify = new JButton();
        btSign2 = new JButton();
        labelUserInfo = new JLabel();

        //======== this ========
        setOpacity(0.0F);
        setTitle("\u7269\u6d41\u7ba1\u7406\u7cfb\u7edf");
        setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {
            menuBar1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== mnFile ========
            {
                mnFile.setText("\u6587\u4ef6(F)");
                mnFile.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- miNewOrder ----
                miNewOrder.setText("\u65b0\u5efa\u8ba2\u5355...");
                miNewOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miNewOrder.setIcon(new ImageIcon(getClass().getResource("/icons/new_16x16.png")));
                miNewOrder.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        miNewOrderMouseReleased(e);
                    }
                });
                mnFile.add(miNewOrder);

                //---- miSign ----
                miSign.setText("\u7b7e\u6536\u8ba2\u5355...");
                miSign.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miSign.setIcon(new ImageIcon(getClass().getResource("/icons/sign_16x16.png")));
                mnFile.add(miSign);

                //---- miFindOrder ----
                miFindOrder.setText("\u67e5\u627e\u8ba2\u5355...");
                miFindOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miFindOrder.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
                mnFile.add(miFindOrder);
                mnFile.addSeparator();

                //---- miLogout ----
                miLogout.setText("\u6ce8\u9500");
                miLogout.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miLogout.setIcon(new ImageIcon(getClass().getResource("/icons/logout_16x16.png")));
                mnFile.add(miLogout);

                //---- miQuit ----
                miQuit.setText("\u9000\u51fa");
                miQuit.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miQuit.setIcon(new ImageIcon(getClass().getResource("/icons/exit_16x16.png")));
                mnFile.add(miQuit);
            }
            menuBar1.add(mnFile);

            //======== mnEdit ========
            {
                mnEdit.setText("\u7f16\u8f91(E)");
                mnEdit.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- miModify ----
                miModify.setText("\u4fee\u6539");
                miModify.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                miModify.setIcon(new ImageIcon(getClass().getResource("/icons/modify_16x16.png")));
                miModify.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        miModifyMouseReleased(e);
                    }
                });
                mnEdit.add(miModify);
            }
            menuBar1.add(mnEdit);
        }
        setJMenuBar(menuBar1);

        //======== panel2 ========
        {

            //---- tfOrderInput ----
            tfOrderInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btSearch ----
            btSearch.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
            btSearch.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btSearch.setText("\u641c\u7d22");
            btSearch.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btSearchMouseReleased(e);
                }
            });

            //---- lbOrderNum ----
            lbOrderNum.setText("\u8ba2\u5355\u7f16\u53f7\uff1a");
            lbOrderNum.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //======== scrollPane1 ========
            {
                scrollPane1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- tbOrderInfo ----
                tbOrderInfo.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                scrollPane1.setViewportView(tbOrderInfo);
            }

            //---- btDelete ----
            btDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24x24.png")));
            btDelete.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btDelete.setText("\u5220\u9664");
            btDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btDeleteMouseReleased(e);
                }
            });

            //---- btSign ----
            btSign.setIcon(new ImageIcon(getClass().getResource("/icons/sign_24x24.png")));
            btSign.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btSign.setText("\u7b7e\u6536");
            btSign.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btSignMouseReleased(e);
                }
            });

            //---- label1 ----
            label1.setText("\u8ba2\u5355\u7ba1\u7406");
            label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btOrderMng ----
            btOrderMng.setIcon(new ImageIcon(getClass().getResource("/icons/order_72x72.png")));
            btOrderMng.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btOrderMng.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btOrderMngMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    btOrderMngMouseReleased(e);
                }
            });

            //---- btModify ----
            btModify.setIcon(new ImageIcon(getClass().getResource("/icons/modify_24x24.png")));
            btModify.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btModify.setText("\u4fee\u6539");
            btModify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btModifyMouseReleased(e);
                }
            });

            //---- btSign2 ----
            btSign2.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
            btSign2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            btSign2.setText("\u65b0\u5efa");
            btSign2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btSign2MouseReleased(e);
                }
            });

            //---- labelUserInfo ----
            labelUserInfo.setText("text");

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btSign, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(btDelete, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(btModify, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(btSign2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(lbOrderNum)
                                        .addGap(6, 6, 6)
                                        .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btSearch))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(label1)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(btOrderMng)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 829, Short.MAX_VALUE)
                                .addComponent(labelUserInfo)))
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(btOrderMng, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUserInfo))
                        .addGap(6, 6, 6)
                        .addComponent(label1)
                        .addGap(6, 6, 6)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbOrderNum))
                            .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(btSign2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btSign, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btModify, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 231, Short.MAX_VALUE))
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)))
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        btOrderMng.setSelected(true);

        GroupLayout scrollPaneLayout = new GroupLayout(scrollPane1);

        KeyStroke ksLogout = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK);
        miLogout.setAccelerator(ksLogout);
        KeyStroke ksQuit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK);
        miQuit.setAccelerator(ksQuit);
        KeyStroke ksNewOrder = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
        miNewOrder.setAccelerator(ksNewOrder);
        KeyStroke ksSign = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
        miSign.setAccelerator(ksSign);
        KeyStroke ksFind = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK);
        miFindOrder.setAccelerator(ksFind);
        KeyStroke ksModify = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK);
        miModify.setAccelerator(ksModify);

        String names[] = { "订单号", "日期", "寄件人", "电话", "地址", "单位", "收件人", "电话", "地址", "单位", "服务类型" };

        for (int i = 0; i < names.length; i++) {
            tbOrderInfo.addColumn(new TableColumn(i));
            tbOrderInfo.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }

        //tbOrderInfo.setRowSorter(new TableRowSorter<TableModel>((DefaultTableModel)tbOrderInfo.getModel()));
        tbOrderInfo.getTableHeader().setFont(new Font("方正中等线简体", 1, 14));
        tbOrderInfo.setRowHeight(50);
        Vector<Object> row = new Vector<>();
        row.add(1234567890);
        row.add("2015/01/01 01:01:59");
        row.add("蜗牛梦溪");
        row.add("18362918579");
        row.add("喵窝");
        row.add("吃吃吃");
        row.add("蜗牛梦溪");
        row.add("18362918579");
        row.add("喵窝");
        row.add("吃吃吃");
        row.add("吃！");
        ((DefaultTableModel)tbOrderInfo.getModel()).getDataVector().add(row);
        row = new Vector<>();
        row.add(1234567890);
        row.add("2015/01/01 01:01:59");
        row.add("蜗牛梦溪");
        row.add("18362918579");
        row.add("喵窝");
        row.add("吃吃吃");
        row.add("蜗牛梦溪");
        row.add("18362918579");
        row.add("喵窝");
        row.add("吃吃吃");
        row.add("吃！");
        ((DefaultTableModel)tbOrderInfo.getModel()).getDataVector().add(row);
        mnFile.setMnemonic('F');
        mnEdit.setMnemonic('E');

        String name = "";
        
        labelUserInfo.setText("ID: " + loginMessage.getUserSN() + " " + name + "\n" + loginMessage.getUserRole());

        refresh();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu mnFile;
    private JMenuItem miNewOrder;
    private JMenuItem miSign;
    private JMenuItem miFindOrder;
    private JMenuItem miLogout;
    private JMenuItem miQuit;
    private JMenu mnEdit;
    private JMenuItem miModify;
    private JPanel panel2;
    private JTextField tfOrderInput;
    private JButton btSearch;
    private JLabel lbOrderNum;
    private JScrollPane scrollPane1;
    private JTable tbOrderInfo;
    private JButton btDelete;
    private JButton btSign;
    private JLabel label1;
    private JToggleButton btOrderMng;
    private JButton btModify;
    private JButton btSign2;
    private JLabel labelUserInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
