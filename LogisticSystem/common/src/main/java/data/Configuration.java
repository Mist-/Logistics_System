package data;

/**
 * ���ݲ��������Ϣ����
 *
 * Created by mist on 2015/12/13 0013.
 */
public class Configuration {
    // ע�����Ķ˿� Ĭ��ֵ32000
    public int regPort = 32000;

    // �������ݵĶ˿� Ĭ��ֵ32001
    public int dataTransPort = 32001;

    // ÿ�����Զ������ڴ���Ϣ�Ĵ���   0 - ���� Ĭ��ֵ - 12
    public int autoSavingFreq = 12;

    public String ip = "127.0.0.1";

    protected static Configuration instance = new Configuration();

    private Configuration() {}

    public static Configuration getInstance() {
        return instance;
    }
}
