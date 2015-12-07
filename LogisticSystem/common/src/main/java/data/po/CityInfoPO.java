package data.po;

/**
 * 城市信息
 */

import data.enums.POType;

import java.util.ArrayList;

public class CityInfoPO extends DataPO {

    private static final long serialVersionUID = 3;

    protected String name = null;               //城市名称

    protected ArrayList<Long> businessOfficeList;   //城市中营业厅的编号

    protected long transferCenter;

    public CityInfoPO(long sn) {
        super(POType.CITYINFO);
        businessOfficeList = new ArrayList<>();
        this.serialNum = sn;
    }
    
    /**
     * 获取中转中心编号 by sx
     * @return
     */
    public long getTransferCenterID(){
    	return transferCenter;
    }

    /**
     * 设置中转中心编号
     *
     * @param sn
     */
    public void setTransferCenter(long sn) {
        transferCenter = sn;
    }

    /**
     * 获取营业厅本城市的营业厅列表
     * @return 包含所有营业厅的ArrayList
     */
    public ArrayList<Long> getBusinessOfficeList() {
        return businessOfficeList;
    }

    /**
     * 向营业厅列表中增加一个营业厅。如果已经存在则不执行任何操作。
     * @param office 准备增加的营业厅编号
     */
    public void addBusinessOffice(long office) {
        for (Long bosn: businessOfficeList) {
            if (bosn == office) return;
        }
        businessOfficeList.add(office);
    }

    /**
     * 从营业厅列表中删除一个营业厅。如果不存在这个营业厅则不执行任何操作。
     * @param office 将要被删除的营业厅编号
     */
    public void removeOffice(long office) {
        for (Long bosn: businessOfficeList) {
            if (bosn == office) {
                businessOfficeList.remove(bosn);
            }
        }
    }

    /**
     * 获取本城市所有营业厅的数量
     * @return 营业厅的数量
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
