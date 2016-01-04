/*
 * Created by JFormDesigner on Sun Dec 13 18:33:39 CST 2015
 */

package presentation;

import java.awt.event.*;
import controller.ServerConfigurationController;
import data.Configuration;
import data.enums.DataType;
import data.factory.DataImplFactory;
import data.service.DataService;
import utils.FileSaver;
import utils.Log;
import utils.SayHelloImpl;
import utils.SayHelloService;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author mist
 */
public class MonitorUI extends JFrame {
    Registry reg = null;
    ArrayList<Thread> autos = null;
    ServerConfigurationController controller = new ServerConfigurationController();
    Thread fileAutoSaver = null;
    public MonitorUI() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        autos = new ArrayList<>();
    }

    private void buttonStartServerMouseReleased(MouseEvent e) {
        controller.applyConfig();
        reg = startServer();
        if (reg == null) {
            JOptionPane.showMessageDialog(null, "启动服务失败。请检查端口设置。");
            return;
        }
        buttonStartServer.setEnabled(false);
        buttonStopServer.setEnabled(true);
    }

    private void buttonStopServerMouseReleased(MouseEvent e) {
        Log.log("正在停止服务…");
        System.out.println("正在停止服务 - " + Calendar.getInstance().getTime());
        stopServer();
        System.out.println("服务已停止 - " + Calendar.getInstance().getTime());
        buttonStartServer.setEnabled(true);
        buttonStopServer.setEnabled(false);
        for (Thread thread: autos) {
            thread.interrupt();
        }
        autos.clear();
    }

    private void stopServer() {
        try {
            UnicastRemoteObject.unexportObject(reg, true);
        } catch (NoSuchObjectException e1) {
            e1.printStackTrace();
        }
        for (Thread tobeintr: autos) {
            tobeintr.interrupt();
        }
        reg = null;
        autos.clear();
    }

    private void btApplyConfMouseReleased(MouseEvent e) {
        if (!textRegPort.getText().matches("[0-9]+") || textRegPort.getText().length() >= 10) {
            textRegPort.requestFocus();
            return;
        }
        if (!textTransPort.getText().matches("[0-9]+") || textTransPort.getText().length() >= 10) {
            textTransPort.requestFocus();
            return;
        }
        if (!textFileUpdateFreq.getText().matches("[0-9]+") || textFileUpdateFreq.getText().length() >= 10) {
            textFileUpdateFreq.requestFocus();
            return;
        }
        int reg, data, file;
        reg = Integer.valueOf(textRegPort.getText());
        data = Integer.valueOf(textTransPort.getText());
        file = Integer.valueOf(textFileUpdateFreq.getText());
        if (file > 60) {
            file = 60;
        }
        if (file < 1) {
            file = 1;
        }
        Configuration.getInstance().autoSavingFreq = file;
        Configuration.getInstance().regPort = reg;
        Configuration.getInstance().dataTransPort = data;
        new ServerConfigurationController().saveConfig();

        if (buttonStopServer.isEnabled()) {
            int resultMsg = JOptionPane.showConfirmDialog(this, "重启服务后配置才能生效。是否立即重启服务？", "LCS物流管理系统", JOptionPane.YES_NO_OPTION);
            if (resultMsg == JOptionPane.YES_OPTION) {
                Log.log("正在重启服务……");
                buttonStopServerMouseReleased(null);
                buttonStartServerMouseReleased(null);
            }
        }
    }

    private boolean isIP(String s) {
        return s.matches("(((\\d{1,2})|(1\\d{2,2})|(2[0-4][0-9])|(25[0-5]))\\.){3,3}((\\d{1,2})|(1\\d{2,2})|(2[0-4][0-9])|(25[0-5]))");
    }

    private void button1MouseReleased(MouseEvent e) {
        textConsole.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        label1 = new JLabel();
        textRegPort = new JTextField();
        label2 = new JLabel();
        textTransPort = new JTextField();
        label3 = new JLabel();
        textFileUpdateFreq = new JTextField();
        buttonStartServer = new JButton();
        buttonStopServer = new JButton();
        label5 = new JLabel();
        btApplyConf = new JButton();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        textConsole = new JTextArea();
        button1 = new JButton();

        //======== this ========
        setTitle("\u670d\u52a1\u76d1\u89c6\u5668");
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== tabbedPane1 ========
            {
                tabbedPane1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //======== panel2 ========
                {
                    panel2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- label1 ----
                    label1.setText("\u670d\u52a1\u7aef\u53e3\uff1a");
                    label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- textRegPort ----
                    textRegPort.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- label2 ----
                    label2.setText("\u6570\u636e\u7aef\u53e3\uff1a");
                    label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- textTransPort ----
                    textTransPort.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- label3 ----
                    label3.setText("\u6587\u4ef6\u66f4\u65b0\u9891\u7387\uff1a");
                    label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- textFileUpdateFreq ----
                    textFileUpdateFreq.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- buttonStartServer ----
                    buttonStartServer.setText("\u542f\u52a8\u670d\u52a1");
                    buttonStartServer.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    buttonStartServer.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonStartServerMouseReleased(e);
                        }
                    });

                    //---- buttonStopServer ----
                    buttonStopServer.setText("\u505c\u6b62\u670d\u52a1");
                    buttonStopServer.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    buttonStopServer.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonStopServerMouseReleased(e);
                        }
                    });

                    //---- label5 ----
                    label5.setText("(\u6b21/\u5206)");
                    label5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                    //---- btApplyConf ----
                    btApplyConf.setText("\u5e94\u7528\u914d\u7f6e");
                    btApplyConf.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    btApplyConf.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            btApplyConfMouseReleased(e);
                        }
                    });

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(buttonStartServer)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 557, Short.MAX_VALUE)
                                        .addComponent(buttonStopServer))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGroup(panel2Layout.createParallelGroup()
                                                    .addGroup(panel2Layout.createSequentialGroup()
                                                        .addGap(24, 24, 24)
                                                        .addGroup(panel2Layout.createParallelGroup()
                                                            .addComponent(label2)
                                                            .addComponent(label1)))
                                                    .addComponent(label3))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(textRegPort)
                                                    .addComponent(textTransPort, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                                    .addComponent(textFileUpdateFreq, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label5))
                                            .addComponent(btApplyConf))
                                        .addGap(0, 491, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textRegPort)
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textTransPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textFileUpdateFreq)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btApplyConf)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonStartServer)
                                    .addComponent(buttonStopServer))
                                .addContainerGap())
                    );
                }
                tabbedPane1.addTab("\u8fde\u63a5\u8bbe\u7f6e", panel2);
            }

            //---- label4 ----
            label4.setText("\u63a7\u5236\u53f0\uff1a");
            label4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

            //======== scrollPane1 ========
            {
                scrollPane1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- textConsole ----
                textConsole.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                scrollPane1.setViewportView(textConsole);
            }

            //---- button1 ----
            button1.setText("\u6e05\u7a7a");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    button1MouseReleased(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(tabbedPane1)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button1)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(button1)
                                .addGap(0, 253, Short.MAX_VALUE))
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
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

        Configuration configuration = controller.getConfigToDisplay();

        textRegPort.setText(String.valueOf(configuration.regPort));
        textTransPort.setText(String.valueOf(configuration.dataTransPort));
        textFileUpdateFreq.setText(String.valueOf(configuration.autoSavingFreq));
        buttonStartServer.setEnabled(true);
        buttonStopServer.setEnabled(false);
        Log.register(textConsole);
        textConsole.setEditable(false);
    }



    public Registry startServer() {
        Log.log("正在启动服务……");
        SayHelloService hello = null;

        try {
            hello = new SayHelloImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        FileSaver fileSaver = new FileSaver();
        Registry reg = this.reg;

        if (reg == null) {
            // 注册监听的端口
            try {
                reg = LocateRegistry.createRegistry(Configuration.getInstance().regPort);
            } catch (RemoteException e) {
                e.printStackTrace();
                System.err.println("端口注册失败，可能已经被注册 - " + Calendar.getInstance().getTime());
                Log.log("启动服务失败。");
            }
        }

        try {
            for (DataType type: DataType.values()) {
                DataService tmp = DataImplFactory.getDataImpl(type);
                fileSaver.addDataService(tmp);
                reg.rebind(type.name(), tmp);
            }
            // 说实话，这部分并没有什么卵用。就是为了测试网络连接性而专门建出来的一个类。
            hello = new SayHelloImpl();
            reg.rebind("hello", hello);
        } catch (AccessException e) {
            e.printStackTrace();
            System.err.println("服务器初始化失败 - " + Calendar.getInstance().getTime());
            Log.log("启动服务失败。");
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            System.err.println("服务器初始化失败 - " + Calendar.getInstance().getTime());
            Log.log("启动服务失败。");
            return null;
        }

        // 缓一缓，等内存加载完了，再来保存
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 创建一个自动保存的线程
        fileAutoSaver = new Thread(fileSaver);

        autos.add(fileAutoSaver);
        fileAutoSaver.start();
        System.out.println("开启自动保存功能");
        System.out.println("服务器初始化成功 - " + Calendar.getInstance().getTime());
        Log.log("已成功创建服务。");
        return reg;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField textRegPort;
    private JLabel label2;
    private JTextField textTransPort;
    private JLabel label3;
    private JTextField textFileUpdateFreq;
    private JButton buttonStartServer;
    private JButton buttonStopServer;
    private JLabel label5;
    private JButton btApplyConf;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea textConsole;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
