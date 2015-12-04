/*
 * Created by JFormDesigner on Tue Nov 24 20:39:09 CST 2015
 */

package presentation.user.login;

import businesslogic.impl.user.UserBLImpl;
import businesslogic.service.user.UserBLService;
import data.message.LoginMessage;
import data.message.ResultMessage;
import data.properties.RememberedUserAccount;
import utils.Connection;
import utils.FileIOHelper;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class LoginDlg extends JDialog {
    LoginMessage loginMessage;


    public LoginDlg(Frame owner, LoginMessage loginMessage) {
        super(owner, "LCS", true);
        initComponents();
        this.loginMessage = loginMessage;
        getRememberedUsers();
    }

    public LoginDlg(Dialog owner) {
        super(owner);
        initComponents();
    }

    /**
     * ”匿名登录“复选框点击事件
     * @param e
     */
    private void checkBox2MouseReleased(MouseEvent e) {
        if (this.chkAnotmousLogin.isSelected()) {
            this.cboxAccount.setEnabled(false);
            this.textPassword.setEnabled(false);
            this.chkRememberAccount.setEnabled(false);
        }
        else {
            this.cboxAccount.setEnabled(true);
            this.textPassword.setEnabled(true);
            this.chkRememberAccount.setEnabled(true);
        }
    }

    ArrayList<RememberedUserAccount> users = new ArrayList<>();

    public void getRememberedUsers() {

        users = (ArrayList<RememberedUserAccount>) FileIOHelper.getFromFile("user/SAVED.DAT");

        // 将记住的账号添加到可选的列表中
        for (RememberedUserAccount user: users) {
            cboxAccount.addItem(user.getSn());
        }

        if (users.size() > 0) {
            chkRememberAccount.setSelected(true);
        }
    }

    public void remember(RememberedUserAccount user) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getSn() == user.getSn()) {
                users.remove(i);
                i--;
            }
        }
        users.add(user);
        FileIOHelper.saveToFile(users, "user/SAVED.DAT");
    }

    /**
     * 登录按钮点击事件
     *
     * @param e
     */
    private void button1MouseReleased(MouseEvent e) {
        Connection.checkConnection();
        if (!Connection.connected) {
            JOptionPane.showMessageDialog(null, "网络未连接，登陆失败");
            return;
        }

        if (chkAnotmousLogin.isSelected()) {
            loginMessage.setResult(ResultMessage.SUCCESS);
            loginMessage.setUserSN(0);
            this.setVisible(false);
            return;
        }

        // 检查账号和密码是否为空，为空则返回继续填写
        if (cboxAccount.getSelectedItem() == null || cboxAccount.getSelectedItem().toString().length() == 0) {
            JOptionPane.showMessageDialog(this, "账号不能为空", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            cboxAccount.requestFocus();
            return;
        }

        if (textPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "密码不能为空", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textPassword.requestFocus();
            return;
        }

        // 检查账号是否是纯数字,若不是纯数字则返回继续填写
        String accountText = cboxAccount.getSelectedItem().toString();
        for (int i = 0; i < accountText.length(); i++) {
            if (accountText.charAt(i) < '0' || accountText.charAt(i) > '9') {
                JOptionPane.showMessageDialog(null, "账号只能是0~9数字", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
                cboxAccount.requestFocus();
                return;
            }
        }

        UserBLService userBLService = new UserBLImpl();
        LoginMessage loginMessage = null;

        loginMessage = userBLService.login(Long.parseLong(accountText), new String(textPassword.getPassword()));

        this.loginMessage.setResult(loginMessage.getResult());
        this.loginMessage.setUserSN(loginMessage.getUserSN());
        this.loginMessage.setUserRole(loginMessage.getUserRole());

        if (loginMessage.getResult() == ResultMessage.NOTCONNECTED) {
            System.err.println("无法连接到网络，无法完成用户身份验证");
            JOptionPane.showMessageDialog(this, "网络未连接，登陆失败.", "LCS物流管理系统",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (loginMessage.getResult() == ResultMessage.FAILED || loginMessage.getResult() == ResultMessage.NOTEXIST) {
            JOptionPane.showMessageDialog(this, "用户名或者密码错误", "LCS物流管理系统", JOptionPane.INFORMATION_MESSAGE);
            textPassword.requestFocus();
            return;
        }

        if (loginMessage.getResult() == ResultMessage.SUCCESS) {
            if (chkRememberAccount.isSelected()) {
                RememberedUserAccount userToRmb = new RememberedUserAccount();
                userToRmb.setSN(Long.parseLong(cboxAccount.getSelectedItem().toString()));
                userToRmb.setPswd_md5(textPassword.getText());
                remember(userToRmb);
            }
            else {
                forget(Long.parseLong(cboxAccount.getSelectedItem().toString()));
            }
            this.setVisible(false);
        }
    }

    private void forget(long sn) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSn() == sn) {
                users.remove(i);
                --i;
            }
        }
        FileIOHelper.saveToFile(users, "user/SAVED.DAT");
    }

    // 账号文本框的KeyPressed时间
    private void cboxAccountKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    // 登陆按钮的Clicked功能
    private void loginButtonClicked(MouseEvent e) {
        // TODO add your code here
    }

    // 登录按钮的KeyPressed事件
    private void button1KeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyChar() == '\n') {
            button1MouseReleased(null);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        cboxAccount = new JComboBox();
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        textPassword = new JPasswordField();
        chkRememberAccount = new JCheckBox();
        chkAnotmousLogin = new JCheckBox();
        button1 = new JButton();

        //======== this ========
        setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- cboxAccount ----
            cboxAccount.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            cboxAccount.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    cboxAccountKeyPressed(e);
                }
            });

            //---- label3 ----
            label3.setText("  ");
            label3.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- label1 ----
            label1.setText("\u8d26\u53f7\uff1a");
            label1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- label2 ----
            label2.setText("\u5bc6\u7801\uff1a");
            label2.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- textPassword ----
            textPassword.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- chkRememberAccount ----
            chkRememberAccount.setText("\u8bb0\u4f4f\u8d26\u53f7");
            chkRememberAccount.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));

            //---- chkAnotmousLogin ----
            chkAnotmousLogin.setText("\u533f\u540d\u767b\u5f55");
            chkAnotmousLogin.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            chkAnotmousLogin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    checkBox2MouseReleased(e);
                }
            });

            //---- button1 ----
            button1.setText("\u767b\u5f55");
            button1.setFont(new Font("\u7b49\u7ebf", Font.PLAIN, 14));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loginButtonClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    button1MouseReleased(e);
                }
            });
            button1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    button1KeyPressed(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(chkRememberAccount, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addGap(127, 127, 127)
                                        .addComponent(chkAnotmousLogin))
                                    .addComponent(cboxAccount)
                                    .addComponent(textPassword))
                                .addGap(6, 6, 6))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxAccount, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(chkAnotmousLogin)
                            .addComponent(chkRememberAccount))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button1)
                        .addContainerGap(8, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        cboxAccount.setEditable(true);

        // 当发生从列表中选择已记住的账号是，自动填写密码
        cboxAccount.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                for (RememberedUserAccount user: users) {
                    if (user.getSn() == (long)cboxAccount.getSelectedItem()) {
                        textPassword.setText(user.getPswd_md5());
                    }
                }
            }
        });

        button1.setMnemonic(KeyEvent.VK_ENTER);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JComboBox cboxAccount;
    private JLabel label3;
    private JLabel label1;
    private JLabel label2;
    private JPasswordField textPassword;
    private JCheckBox chkRememberAccount;
    private JCheckBox chkAnotmousLogin;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
