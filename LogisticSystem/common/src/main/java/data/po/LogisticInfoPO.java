package data.po;

import data.enums.POType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogisticInfoPO extends DataPO {

    private static final long serialVersionUID = 9;

    List<LogisticInfo> infoList;

    public LogisticInfoPO(long serialNum) {
        super(POType.LOGISTICINFO);
        this.serialNum = serialNum;
        infoList = new ArrayList<>();
    }

    /**
     * ���������Ϣ�������
     *
     * @param time ʱ�䣬��ʵ���ǲ���Ӧ�ð�ʱ�����ɾ�����������ǵ���Timestamper
     * @param detailedInfo ��������ϸ��Ϣ����������Ӧ�ð����� ���ڵ����Ķ� ��
     */
    public void addInfo(String time, String detailedInfo) {
        infoList.add(new LogisticInfo(time, detailedInfo));
    }


    /**
     * ���ַ����������ʽ����������Ϣ��
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        Iterator<LogisticInfo> tmp = infoList.iterator();
        while (tmp.hasNext()) {
            result += tmp.next().toString() + "\n\n";
        }
        return result;
    }


    public long getSerialNum() {
        return serialNum;
    }

    // ������Ϣ�����ڴ˴�
    public class LogisticInfo implements Serializable {
        /**
         * FORMAT = "yyyy/mm/dd hh:mm:ss"
         */
        String timestamp;
        String location;

        public LogisticInfo(String timestamp, String location) {
            this.timestamp = timestamp;
            this.location = location;

        }

        @Override
        public String toString() {
            String result = timestamp + "�� " + location;
            return result;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}