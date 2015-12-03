/*
 * Created by JFormDesigner on Thu Nov 26 20:09:22 CST 2015
 */

package presentation.order;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class SimplifiedOrderUI extends JFrame {
    public SimplifiedOrderUI() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void btOrderMngMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        lbUserInfo = new JLabel();
        tfOrderInput = new JTextField();
        btSearch = new JButton();
        lbOrderNum = new JLabel();
        scrollPane1 = new JScrollPane();
        tbOrderInfo = new JTable();
        label1 = new JLabel();
        btOrderMng = new JToggleButton();
        separator2 = new JSeparator();
        menuBar1 = new JMenuBar();
        mnFile = new JMenu();
        miFindOrder = new JMenuItem();
        miQuit = new JMenuItem();

        //======== this ========
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== panel2 ========
            {
                panel2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- lbUserInfo ----
                lbUserInfo.setText("1000001 \u5b59\u4e5d\u65e5 \u5feb\u9012\u5458");
                lbUserInfo.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- tfOrderInput ----
                tfOrderInput.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //---- btSearch ----
                btSearch.setIcon(new ImageIcon("D:\\DATA\\Project\\GUI\\resources\\search_16x16.png"));
                btSearch.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                btSearch.setText("Search");

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
                });

                //---- separator2 ----
                separator2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(btOrderMng)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbUserInfo))
                                .addComponent(scrollPane1)
                                .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(separator2))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(lbOrderNum)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btSearch)
                                    .addGap(0, 447, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(btOrderMng, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(lbUserInfo)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(separator2, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbOrderNum)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
            }

            //======== menuBar1 ========
            {
                menuBar1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                //======== mnFile ========
                {
                    mnFile.setText("\u6587\u4ef6(F)");
                    mnFile.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

                    //---- miFindOrder ----
                    miFindOrder.setText("\u67e5\u627e\u8ba2\u5355...");
                    miFindOrder.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    miFindOrder.setIcon(new ImageIcon(getClass().getResource("/icons/search_16x16.png")));
                    mnFile.add(miFindOrder);
                    mnFile.addSeparator();

                    //---- miQuit ----
                    miQuit.setText("\u9000\u51fa");
                    miQuit.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
                    miQuit.setIcon(new ImageIcon(getClass().getResource("/icons/exit_16x16.png")));
                    mnFile.add(miQuit);
                }
                menuBar1.add(mnFile);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menuBar1, GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(menuBar1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JLabel lbUserInfo;
    private JTextField tfOrderInput;
    private JButton btSearch;
    private JLabel lbOrderNum;
    private JScrollPane scrollPane1;
    private JTable tbOrderInfo;
    private JLabel label1;
    private JToggleButton btOrderMng;
    private JSeparator separator2;
    private JMenuBar menuBar1;
    private JMenu mnFile;
    private JMenuItem miFindOrder;
    private JMenuItem miQuit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
