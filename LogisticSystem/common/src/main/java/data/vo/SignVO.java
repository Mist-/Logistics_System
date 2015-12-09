package data.vo;

import data.po.SignPO;

/**
 * Ŷ����
 * Created by mist on 2015/12/9 0009.
 */
public class SignVO {

    public SignVO(long sn) {
        this.sn = sn;
    }

    public SignVO(SignPO signPO) {
        sn = signPO.getSerialNum();
        sname = signPO.getSname();
        sphone = signPO.getSphone();
    }

    // ǩ�յĶ�����
    public long sn;

    // ǩ��������
    public String sname;

    // ǩ���˵绰
    public String sphone;
}
