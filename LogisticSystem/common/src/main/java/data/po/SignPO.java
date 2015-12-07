package data.po;

import data.enums.POType;

/**
 * Created by mist on 2015/11/16 0016.
 */
public class SignPO extends DataPO {
    private static final long serialVersionUID = 16;
    // ǩ�յĶ�����
    long order;
    // ǩ�����������绰
    String sname, sphone;
    // ǩ������ ��ʽ�� "yyyy/mm/dd hh:mm:ss"
    String sdate;

    public SignPO(long sn) {
        super(POType.SIGN);
        this.order = sn;
    }

    public long getOrder() {
        return this.order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}
