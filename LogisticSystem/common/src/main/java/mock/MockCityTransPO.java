package mock;

import data.po.CityTransPO;

/**
 * Created by apple on 2015/11/15.
 */
public class MockCityTransPO extends CityTransPO {

    public MockCityTransPO(String fromCity, String toCity, double distance, double trunkPrice, double trainPrice, double planePrice) {
        super(fromCity, toCity, distance, trunkPrice, trainPrice, planePrice);
    }
}
