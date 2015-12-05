package presentation.order;

import businesslogic.impl.order.OrderBLController;
import data.message.LoginMessage;
import data.vo.OrderVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
/*
 * Created by JFormDesigner on Thu Oct 29 23:00:05 CST 2015
 */


/**
 * @author sunhao
 */
public class OrderUI extends JFrame {
    public OrderUI(LoginMessage loginMessage) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void btOrderMngMouseClicked(MouseEvent e) {
        btOrderMng.setSelected(true);
    }

    private void btOrderMngMouseReleased(MouseEvent e) {
        btOrderMng.setSelected(true);
        refresh();
    }

    private void refresh() {
        ArrayList<OrderVO> displayData = new OrderBLController().getDisplayData();
        Vector tabelData = ((DefaultTableModel) tbOrderInfo.getModel()).getDataVector();
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

    private void miNewOrderMouseReleased(MouseEvent e) {
        OrderVO newOrder = new NewOrderDlg1(this).getNewOrderInfo();
        if (newOrder == null) JOptionPane.showMessageDialog(null, "空订单");

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
        panel1 = new JPanel();
        lbUserInfo = new JLabel();
        tfOrderInput = new JTextField();
        btSearch = new JButton();
        lbOrderNum = new JLabel();
        scrollPane1 = new JScrollPane();
        tbOrderInfo = new JTable();
        btDelete = new JButton();
        btSign = new JButton();
        label1 = new JLabel();
        btOrderMng = new JToggleButton();
        separator2 = new JSeparator();

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
                mnEdit.add(miModify);
            }
            menuBar1.add(mnEdit);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- lbUserInfo ----
            lbUserInfo.setText("1000001 \u5b59\u4e5d\u65e5 \u5feb\u9012\u5458");
            lbUserInfo.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- tfOrderInput ----
            tfOrderInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btSearch ----
            btSearch.setIcon(new ImageIcon("D:\\DATA\\Project\\GUI\\resources\\search_16x16.png"));
            btSearch.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

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
            btDelete.setIcon(new ImageIcon("D:\\DATA\\Project\\GUI\\resources\\delete_24x24.png"));
            btDelete.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- btSign ----
            btSign.setIcon(new ImageIcon("D:\\DATA\\Project\\GUI\\resources\\sign_24x24.png"));
            btSign.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

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

            //---- separator2 ----
            separator2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(label1))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbOrderNum)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 487, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(btOrderMng)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbUserInfo))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(btSign, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btDelete, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(separator2, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE))
                                .addContainerGap())))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lbUserInfo)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btOrderMng, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                        .addComponent(separator2, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbOrderNum)
                                .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btSign, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(309, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                .addContainerGap())))
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
    private JPanel panel1;
    private JLabel lbUserInfo;
    private JTextField tfOrderInput;
    private JButton btSearch;
    private JLabel lbOrderNum;
    private JScrollPane scrollPane1;
    private JTable tbOrderInfo;
    private JButton btDelete;
    private JButton btSign;
    private JLabel label1;
    private JToggleButton btOrderMng;
    private JSeparator separator2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
