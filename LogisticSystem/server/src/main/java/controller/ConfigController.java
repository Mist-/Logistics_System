package controller;

import data.Configuration;

import java.io.*;
import java.util.Scanner;

/**
 * ���õĿ�����
 *
 * Created by mist on 2015/12/13 0013.
 */
public class ConfigController {
    Configuration configuration = null;

    // �����ֳɵ�����
    public void loadConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    // �ӳ����Ŀ¼�µ�server.cfg��ȡ������Ϣ
    public Configuration readConfig() {
        configuration = new Configuration();
        File configPath = new File("server.cfg");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(configPath)));
        } catch (FileNotFoundException e) {
            System.err.println("�����ļ������ڣ��Զ������������ļ�");
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

    // Ӧ�����õ����ݲ�
    public void applyConfig() {
        if (configuration == null) configuration = new Configuration();
    }

    // ���������Ϣ
    public Configuration getConfigToDisplay() {
        if (configuration == null) {
            return readConfig();
        }
        return configuration;
    }

    // ��������Ϣ���浽�ļ���
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
