package presentation.user.userMngUI;

import businesslogic.impl.user.UserBLImpl;
import data.enums.DataType;
import data.factory.DataServiceFactory;
import data.message.ResultMessage;
import data.po.StaffPO;
import data.service.CompanyDataService;
import data.vo.UserVO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/*
 * Created by JFormDesigner on Fri Oct 30 14:03:00 CST 2015
 */



/**
 * @author sunhao
 */
public class UserMngUI extends JFrame {
    public UserMngUI() {
        initComponents();
        refresh(ACTIVATED);
    }

    private void tbUserInfoMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JOptionPane.showMessageDialog(null, "听说你要修改用户信息？？");
        }
    }

    private void btOrderMngMouseClicked(MouseEvent e) {
        // 抛弃
    }

    private void tbDeletedMouseClicked(MouseEvent e) {
        // 抛弃
    }

    private void scrollPane1FocusGained(FocusEvent e) {
        btDelete.setEnabled(true);
        btRe.setEnabled(true);
    }

    private void scrollPane1FocusLost(FocusEvent e) {
        btDelete.setEnabled(false);
        btRe.setEnabled(false);
    }

    private static final int ACTIVATED = 0, DELETED = 1;

    private void refresh(int accountType) {
        Vector<Object> userRecord;
        ArrayList<UserVO> userInfo = new UserBLImpl().getDisplayData();
        ((DefaultTableModel) tbUserInfo.getModel()).getDataVector().clear();
        for (UserVO userVO: userInfo) {
            if (accountType == ACTIVATED && !userVO.deleted || accountType == DELETED && userVO.deleted) {
                userRecord = new Vector<>();
                userRecord.add(userVO.staffsn);
                userRecord.add(userVO.name);
                userRecord.add(userVO.userRole);
                ((DefaultTableModel) tbUserInfo.getModel()).getDataVector().add(userRecord);
            }
        }
        tbUserInfo.updateUI();
        tbUserInfo.repaint();
    }

    private void tbActiveMouseReleased(MouseEvent e) {
        // 更新侧边功能按钮的情况
        ((JToggleButton) e.getSource()).setSelected(true);
        tbDeleted.setSelected(false);
        btRe.setVisible(false);
        btAdd.setVisible(true);
        btDelete.setVisible(true);
        if (tbUserInfo.hasFocus()) btDelete.setEnabled(true);
        else btDelete.setEnabled(false);

        // 更新表格信息
        refresh(ACTIVATED);
    }

    private void tbDeletedMouseReleased(MouseEvent e) {
        // 更新侧边功能按钮信息
        ((JToggleButton)e.getSource()).setSelected(true);
        tbActive.setSelected(false);
        btRe.setVisible(true);
        btAdd.setVisible(false);
        btDelete.setVisible(false);
        if (tbUserInfo.hasFocus()) btRe.setEnabled(true);
        else btRe.setEnabled(false);

        // 更新表格信息
        refresh(DELETED);
    }

    private void btSearchMouseReleased(MouseEvent e) {
        if (!tfOrderInput.getText().matches("[0-9]*")) {
            JOptionPane.showMessageDialog(this, "用户编号格式错误！", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            tfOrderInput.requestFocus();
            return;
        }
        ArrayList<UserVO> userInfo = new UserBLImpl().getDisplayData();
        ((DefaultTableModel) tbUserInfo.getModel()).getDataVector().clear();
        for (UserVO userVO : userInfo) {
            if (tbActive.isSelected() && !userVO.deleted || tbDeleted.isSelected() && userVO.deleted) {
                if (userVO.staffsn == Long.parseLong(tfOrderInput.getText())) {
                    Vector<Object> userRecord = new Vector<>();
                    userRecord.add(userVO.serialNum);
                    userRecord.add(userVO.name);
                    userRecord.add(userVO.userRole.name());
                    ((DefaultTableModel) tbUserInfo.getModel()).getDataVector().add(userRecord);
                }
            }
        }
    }

    private void btAddMouseReleased(MouseEvent e) {

        String staffs = JOptionPane.showInputDialog(this, "请输入员工ID：");
        if (staffs == null) return;
        if (staffs.matches("[0-9]*")) {
            long staffsn = Long.parseLong(staffs);
            ResultMessage rs = new UserBLImpl().register(staffsn);
            if (rs == ResultMessage.NOTEXIST) {
                JOptionPane.showMessageDialog(null, "没有找到员工信息，无法完成注册。", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "请输入正确的员工号！", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
        }
        refresh(ACTIVATED);
    }

    private void btDeleteMouseReleased(MouseEvent e) {
        JOptionPane.showMessageDialog(null, tbUserInfo.getSelectedRow());
        long snToDel = Long.parseLong(((Vector<Object>)((DefaultTableModel) tbUserInfo.getModel()).getDataVector().get(tbUserInfo.getSelectedRow())).get(0).toString());
        new UserBLImpl().deleteUser(snToDel);
    }

    private void scrollPane1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        mnFile = new JMenu();
        miLogout = new JMenuItem();
        miQuit = new JMenuItem();
        mnEdit = new JMenu();
        miModify = new JMenuItem();
        panel2 = new JPanel();
        tbActive = new JToggleButton();
        tbDeleted = new JToggleButton();
        label2 = new JLabel();
        label1 = new JLabel();
        panel3 = new JPanel();
        lbOrderNum = new JLabel();
        tfOrderInput = new JTextField();
        btSearch = new JButton();
        scrollPane1 = new JScrollPane();
        tbUserInfo = new JTable();
        btDelete = new JButton();
        btAdd = new JButton();
        btRe = new JButton();

        //======== this ========
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== mnFile ========
            {
                mnFile.setText("\u6587\u4ef6(F)");
                mnFile.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
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

        //======== panel2 ========
        {
            panel2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

            //---- tbActive ----
            tbActive.setIcon(new ImageIcon(getClass().getResource("/icons/account_72x72.png")));
            tbActive.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            tbActive.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btOrderMngMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    tbActiveMouseReleased(e);
                    tbActiveMouseReleased(e);
                    tbActiveMouseReleased(e);
                }
            });

            //---- tbDeleted ----
            tbDeleted.setIcon(new ImageIcon(getClass().getResource("/icons/account_72x72.png")));
            tbDeleted.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            tbDeleted.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tbDeletedMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    tbDeletedMouseReleased(e);
                    tbDeletedMouseReleased(e);
                }
            });

            //---- label2 ----
            label2.setText("\u5df2\u5220\u9664");
            label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

            //---- label1 ----
            label1.setText("\u6d3b\u52a8\u8d26\u6237");
            label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

            //======== panel3 ========
            {
                panel3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- lbOrderNum ----
                lbOrderNum.setText("\u7528\u6237\u4fe1\u606f\uff1a");
                lbOrderNum.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- tfOrderInput ----
                tfOrderInput.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- btSearch ----
                btSearch.setIcon(new ImageIcon("D:\\DATA\\Project\\GUI\\resources\\search_16x16.png"));
                btSearch.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                btSearch.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        btSearchMouseReleased(e);
                        btSearchMouseReleased(e);
                    }
                });

                //======== scrollPane1 ========
                {
                    scrollPane1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    scrollPane1.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            scrollPane1FocusGained(e);
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            scrollPane1FocusLost(e);
                        }
                    });
                    scrollPane1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            scrollPane1MouseClicked(e);
                        }
                    });

                    //---- tbUserInfo ----
                    tbUserInfo.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    tbUserInfo.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tbUserInfoMouseClicked(e);
                        }
                    });
                    scrollPane1.setViewportView(tbUserInfo);
                }

                //---- btDelete ----
                btDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24x24.png")));
                btDelete.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                btDelete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        btDeleteMouseReleased(e);
                    }
                });

                //---- btAdd ----
                btAdd.setIcon(new ImageIcon(getClass().getResource("/icons/new_24x24.png")));
                btAdd.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                btAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        btAddMouseReleased(e);
                    }
                });

                //---- btRe ----
                btRe.setIcon(new ImageIcon(getClass().getResource("/icons/recover_24x24.png")));
                btRe.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel3Layout.createParallelGroup()
                                        .addComponent(btRe, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(btDelete, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btAdd, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addComponent(lbOrderNum)
                                    .addGap(6, 6, 6)
                                    .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 447, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(btAdd, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                            .addComponent(btRe, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15))
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGroup(panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(lbOrderNum))
                                .addComponent(tfOrderInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tbActive)
                                .addGap(24, 24, 24)
                                .addComponent(tbDeleted))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(label1)
                                .addGap(80, 80, 80)
                                .addComponent(label2)))
                        .addContainerGap(685, Short.MAX_VALUE))
                    .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(tbDeleted, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbActive, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(label1)
                            .addComponent(label2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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


        String names[] = { "员工ID", "姓名", "职务" };
        for (int i = 0; i < names.length; i++) {
            tbUserInfo.addColumn(new TableColumn(i));
            tbUserInfo.getColumnModel().getColumn(i).setHeaderValue(names[i]);
        }

        tbUserInfo.setRowHeight(50);

        tbActive.setSelected(true);

        btAdd.setToolTipText("新建账户");

        btDelete.setToolTipText("删除选中账户");
        btDelete.setEnabled(false);

        btRe.setToolTipText("恢复选中账户");
        btRe.setVisible(false);

        //tbUserInfo.setRowSorter(new TableRowSorter<TableModel>((DefaultTableModel) tbUserInfo.getModel()));
        tbUserInfo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                scrollPane1FocusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                scrollPane1FocusLost(e);
            }
        });

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu mnFile;
    private JMenuItem miLogout;
    private JMenuItem miQuit;
    private JMenu mnEdit;
    private JMenuItem miModify;
    private JPanel panel2;
    private JToggleButton tbActive;
    private JToggleButton tbDeleted;
    private JLabel label2;
    private JLabel label1;
    private JPanel panel3;
    private JLabel lbOrderNum;
    private JTextField tfOrderInput;
    private JButton btSearch;
    private JScrollPane scrollPane1;
    private JTable tbUserInfo;
    private JButton btDelete;
    private JButton btAdd;
    private JButton btRe;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
