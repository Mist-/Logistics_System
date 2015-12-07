package data.po;

import data.enums.POType;

/**
 * Created by Mouse on 2015/10/24 0024.
 */
public class InstitutionPO extends DataPO {

    private static final long serialVersionUID = 8;

    /**
     * 机构的名字。因为在展示层可能直接显示营业厅的名字了所以要规定一下
     *
     * 营业厅：以所在地的区名称命名。例如“栖霞区”，“鼓楼区”
     * 中转中心：以所在城市名称命名。例如“南京市”，“上海市”
     * 财务部门：就叫财务部门=。=
     * 总经理：爱咋叫咋叫。。。
     */
    String name;
    long targetCenter = -1;    //只有营业厅有此属性


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
