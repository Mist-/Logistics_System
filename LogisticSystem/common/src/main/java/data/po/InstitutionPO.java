package data.po;

import data.enums.POType;

/**
 * Created by Mouse on 2015/10/24 0024.
 */
public class InstitutionPO extends DataPO {

    private static final long serialVersionUID = 8;

    /**
     * ���������֡���Ϊ��չʾ�����ֱ����ʾӪҵ��������������Ҫ�涨һ��
     *
     * Ӫҵ���������ڵص����������������硰��ϼ����������¥����
     * ��ת���ģ������ڳ����������������硰�Ͼ��С������Ϻ��С�
     * �����ţ��ͽв�����=��=
     * �ܾ�����զ��զ�С�����
     */
    String name;
    long targetCenter = -1;    //ֻ��Ӫҵ���д�����


    public long getTargetCenter() {
		return targetCenter;
	}

	public void setTargetCenter(long targetCenter) {
		this.targetCenter = targetCenter;
	}

	public InstitutionPO(long sn) {
        super(POType.INSTITUTION);
        this.serialNum = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
