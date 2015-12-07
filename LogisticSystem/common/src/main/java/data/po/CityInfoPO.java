package data.po;

/**
 * ������Ϣ
 */

import data.enums.POType;

import java.util.ArrayList;

public class CityInfoPO extends DataPO {

    private static final long serialVersionUID = 3;

    protected String name = null;               //��������

    protected ArrayList<Long> businessOfficeList;   //������Ӫҵ���ı��

    protected long transferCenter;

    public CityInfoPO(long sn) {
        super(POType.CITYINFO);
        businessOfficeList = new ArrayList<>();
        this.serialNum = sn;
    }
    
    /**
     * ��ȡ��ת���ı�� by sx
     * @return
     */
    public long getTransferCenterID(){
    	return transferCenter;
    }

    /**
     * ������ת���ı��
     *
     * @param sn
     */
    public void setTransferCenter(long sn) {
        transferCenter = sn;
    }

    /**
     * ��ȡӪҵ�������е�Ӫҵ���б�
     * @return ��������Ӫҵ����ArrayList
     */
    public ArrayList<Long> getBusinessOfficeList() {
        return businessOfficeList;
    }

    /**
     * ��Ӫҵ���б�������һ��Ӫҵ��������Ѿ�������ִ���κβ�����
     * @param office ׼�����ӵ�Ӫҵ�����
     */
    public void addBusinessOffice(long office) {
        for (Long bosn: businessOfficeList) {
            if (bosn == office) return;
        }
        businessOfficeList.add(office);
    }

    /**
     * ��Ӫҵ���б���ɾ��һ��Ӫҵ����������������Ӫҵ����ִ���κβ�����
     * @param office ��Ҫ��ɾ����Ӫҵ�����
     */
    public void removeOffice(long office) {
        for (Long bosn: businessOfficeList) {
            if (bosn == office) {
                businessOfficeList.remove(bosn);
            }
        }
    }

    /**
     * ��ȡ����������Ӫҵ��������
     * @return Ӫҵ��������
     */
    public int getBOAmount() {
        return businessOfficeList.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
