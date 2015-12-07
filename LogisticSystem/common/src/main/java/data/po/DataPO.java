package data.po;

import data.enums.DataState;
import data.enums.POType;
import utils.IDGenerator;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * Created by Mouse on 2015/10/22 0022.
 */
public class DataPO implements Serializable {

    // ���ݵ�Ψһ���
    protected long serialNum;

    POType poType;

    // ���ݵ�����״̬
    DataState state;

    Calendar genDate;

    public DataPO(POType type) {
        serialNum = IDGenerator.getNextID(type);
        setPoType(type);
        genDate = Calendar.getInstance();
        state = DataState.APPROVING;
    }

    public DataState getState() {
        return state;
    }

    public void setState(DataState dataState) {
        state = dataState;
    }

    public Calendar getGenDate() {
        return genDate;
    }

    public long getSerialNum() {
        return serialNum;
    }

    public POType getPOType() {
        return poType;
    }

    public void setPoType(POType poType) {
        this.poType = poType;
    }
}
