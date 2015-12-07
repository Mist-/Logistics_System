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
     * 向该物流信息中添加项
     *
     * @param time 时间，其实我是不是应该吧时间参数删除？？反正记得用Timestamper
     * @param detailedInfo 物流的详细信息。期中至少应该包括了 现在到了哪儿 。
     */
    public void addInfo(String time, String detailedInfo) {
        infoList.add(new LogisticInfo(time, detailedInfo));
    }


    /**
     * 以字符串数组的形式返回物流信息。
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

    // 物流信息保存在此处
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
            String result = timestamp + "： " + location;
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