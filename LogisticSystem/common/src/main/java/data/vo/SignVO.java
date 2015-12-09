package data.vo;

import data.po.SignPO;

/**
 * 哦多尅
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

    // 签收的订单号
    public long sn;

    // 签收人姓名
    public String sname;

    // 签收人电话
    public String sphone;
}
