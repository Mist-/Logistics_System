package data.vo;

/**
 * Created by mist on 2015/12/9 0009.
 */
public class SignVO {

    public SignVO(long sn) {
        this.sn = sn;
    }
    // 签收的订单号
    public long sn;

    // 签收人姓名
    public String sname;

    // 签收人电话
    public String sphone;
}
