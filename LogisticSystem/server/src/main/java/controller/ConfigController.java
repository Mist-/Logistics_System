package controller;

import data.Configuration;

import java.io.*;
import java.util.Scanner;

/**
 * 配置的控制类
 *
 * Created by mist on 2015/12/13 0013.
 */
public class ConfigController {
    Configuration configuration = null;

    // 加载现成的配置
    public void loadConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    // 从程序根目录下的server.cfg读取配置信息
    public Configuration readConfig() {
        configuration = new Configuration();
        File configPath = new File("server.cfg");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(configPath)));
        } catch (FileNotFoundException e) {
            System.err.println("数据文件不存在，自动生成新配置文件");
            return configuration = new Configuration();
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String conf[] = line.split("[ ]+");
            if (conf[0].equals("regPort") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length < 4 && conf.length > 6) continue;
                configuration.regPort = Integer.parseInt(conf[2]);
            }
            if (conf[0].equals("regPort") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length < 4 && conf.length > 6) continue;
                configuration.dataTransPort = Integer.parseInt(conf[2]);
            }
            if (conf[0].equals("autoSavingFreq") && conf[1].equals("=")) {
                if (!conf[2].matches("[0-9]+") && conf.length > 2) continue;
                configuration.autoSavingFreq = Integer.parseInt(conf[2]);
            }
        }
        scanner.close();
        return configuration;
    }

    // 应用配置到数据层
    public void applyConfig() {
        if (configuration == null) configuration = new Configuration();
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
        if (configuration == null) configuration = new Configuration();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File("server.cfg"))));
            writer.write("regPort = " + configuration.regPort + "\n");
            writer.write("dataTransPort = " + configuration.dataTransPort + "\n");
            writer.write("autoSavingFreq = " + configuration.autoSavingFreq + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
