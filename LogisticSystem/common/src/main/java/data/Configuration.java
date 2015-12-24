package data;

/**
 * 数据层的配置信息对象
 *
 * Created by mist on 2015/12/13 0013.
 */
public class Configuration {
    // 注册服务的端口 默认值32000
    public int regPort = 32000;

    // 传输数据的端口 默认值32001
    public int dataTransPort = 32001;

    // 每分钟自动保存内存信息的次数   0 - 按需 默认值 - 12
    public int autoSavingFreq = 12;

    public String ip = "127.0.0.1";

    protected static Configuration instance = new Configuration();

    private Configuration() {}

    public static Configuration getInstance() {
        return instance;
    }
}
