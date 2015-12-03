package mock;

import data.enums.DataState;
import data.enums.POType;
import data.po.DataPO;

/**
 * Created by apple on 2015/11/15.
 */
public class MockDataPO extends DataPO {

    public void setDataState(DataState dataState) {
        this.dataState = dataState;
    }

    private DataState dataState= null;

    public MockDataPO(POType type) {
        super(type);
    }


}
