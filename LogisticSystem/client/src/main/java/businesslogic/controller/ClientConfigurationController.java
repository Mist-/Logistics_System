package businesslogic.controller;

import data.Configuration;
import utils.CustomSocketFactory;

import java.io.*;
import java.rmi.server.RMISocketFactory;
import java.util.Scanner;

/**
 * 配置的控制类
 *
 * Created by mist on 2015/12/13 0013.
 */
public class ClientConfigurationController {
    Configuration configuration = null;

    // 加载现成的配置
    public void loadConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    // 从程序根目录下的server.cfg读取配置信息
    public Configuration readConfig() {
        configuration = Configuration.getInstance();
        File configPath = new File("client.cfg");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(configPath)));
        } catch (FileNotFoundException e) {
            System.err.println("数据文件不存在，自动生成新配置文件");
            return configuration = Configuration.getInstance();
        }

        System.out.println("------------------------------------\nreading configuration:");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String conf[] = line.split("[ ]+");
            if (conf[0].equals("regPort") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length < 4 && conf.length > 6) continue;
                configuration.regPort = Integer.parseInt(conf[2]);
                System.out.println("regPort = " + configuration.regPort);
            }
            if (conf[0].equals("dataTransPort") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length < 4 && conf.length > 6) continue;
                configuration.dataTransPort = Integer.parseInt(conf[2]);
                System.out.println("dataTransPort = " + configuration.dataTransPort);
            }
            if (conf[0].equals("autoSavingFreq") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length > 2) continue;
                configuration.autoSavingFreq = Integer.parseInt(conf[2]);
                System.out.println("autoSavingFreq = " + configuration.autoSavingFreq);
            }
            if (conf[0].equals("ip") && conf[1].equals("=")) {
                configuration.ip = conf[2];
                System.out.println("ip = " + configuration.ip);
            }
        }
        System.out.println("------------------------------------");
        scanner.close();
        return configuration;
    }

    // 应用配置到数据层
    public void applyConfig() {
        if (configuration == null) configuration = Configuration.getInstance();
        try {
            RMISocketFactory.setSocketFactory(new CustomSocketFactory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveConfig();
    }

    // 获得配置信息
    public Configuration getConfigToDisplay() {
        if (configuration == null) {
            return readConfig();
        }
        return configuration;
    }

    // 将配置信息保存到文件中
    public void saveConfig() {
        if (configuration == null) configuration = Configuration.getInstance();
        PrintWriter writer = null;

        System.out.println("------------------------------------\nwriting configuration");
        try {
            writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File("client.cfg"))));
            System.out.println("regPort = " + configuration.regPort);
            writer.write("regPort = " + configuration.regPort + "\n");
            System.out.println("dataTransPort = " + configuration.dataTransPort);
            writer.write("dataTransPort = " + configuration.dataTransPort + "\n");
            System.out.println("autoSavingFreq = " + configuration.autoSavingFreq);
            writer.write("autoSavingFreq = " + configuration.autoSavingFreq + "\n");
            System.out.println("ip = " + configuration.ip);
            writer.write("ip = " + configuration.ip + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            System.out.println("------------------------------------");
        }
    }
}
