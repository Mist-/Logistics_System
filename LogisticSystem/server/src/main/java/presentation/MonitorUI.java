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
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
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
        for (DataType dataType: DataType.values()) {
            try {
                reg.unbind(dataType.name());
            } catch (RemoteException | NotBoundException e1) {
                // don't have to deal with
            }
        }
        try {
            reg.unbind("hello");
        } catch (RemoteException | NotBoundException e1) {
            // don't have to deal with
        }
        System.out.println("服务已停止 - " + Calendar.getInstance().getTime());
        buttonStartServer.setEnabled(true);
        buttonStopServer.setEnabled(false);
        for (Thread thread: autos) {
            thread.interrupt();
        }
        autos.clear();
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
        button1 = new JButton();
        buttonStartServer = new JButton();
        buttonStopServer = new JButton();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        textConsole = new JTextArea();

        //======== this ========
        setTitle("\u670d\u52a1\u76d1\u89c6\u5668");
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== tabbedPane1 ========
            {

                //======== panel2 ========
                {

                    //---- label1 ----
                    label1.setText("\u670d\u52a1\u7aef\u53e3\uff1a");

                    //---- label2 ----
                    label2.setText("\u6570\u636e\u7aef\u53e3\uff1a");

                    //---- label3 ----
                    label3.setText("\u6587\u4ef6\u66f4\u65b0\u9891\u7387\uff1a");

                    //---- button1 ----
                    button1.setText("\u5e94\u7528\u914d\u7f6e");

                    //---- buttonStartServer ----
                    buttonStartServer.setText("\u542f\u52a8\u670d\u52a1");
                    buttonStartServer.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonStartServerMouseReleased(e);
                        }
                    });

                    //---- buttonStopServer ----
                    buttonStopServer.setText("\u505c\u6b62\u670d\u52a1");
                    buttonStopServer.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            buttonStopServerMouseReleased(e);
                        }
                    });

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(label1)
                                                    .addComponent(label2)))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label3)))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textTransPort)
                                            .addComponent(textRegPort)
                                            .addComponent(textFileUpdateFreq, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                                        .addComponent(button1))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(buttonStartServer)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
                                        .addComponent(buttonStopServer)))
                                .addContainerGap())
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(textRegPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textTransPort))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textFileUpdateFreq)
                                        .addComponent(button1))
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
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

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(textConsole);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPane1)
                            .addComponent(tabbedPane1, GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addComponent(label4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
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
        Thread fileAutoSaver = new Thread(fileSaver);
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
    private JButton button1;
    private JButton buttonStartServer;
    private JButton buttonStopServer;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea textConsole;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
