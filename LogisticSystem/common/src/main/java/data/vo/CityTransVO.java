package data.vo;

/**
 * Created by wyc on 2015/11/15.
 */
public class CityTransVO {
    /*
    * 包含参数 起始城市、到达城市、距离、货车价格、火车价格、飞机价格
    */
    private String fromCity,toCity;
    private double distance;
    private double trunkPrice,trainPrice,planePrice;

    public CityTransVO(String fromCity, String toCity, double distance, double trunkPrice, double trainPrice, double planePrice) {

        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
        this.trunkPrice = trunkPrice;
        this.trainPrice = trainPrice;
        this.planePrice = planePrice;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTrunkPrice() {
        return trunkPrice;
    }

    public void setTrunkPrice(double trunkPrice) {
        this.trunkPrice = trunkPrice;
    }

    public double getTrainPrice() {
        return trainPrice;
    }

    public void setTrainPrice(double trainPrice) {
        this.trainPrice = trainPrice;
    }

    public double getPlanePrice() {
        return planePrice;
    }

    public void setPlanePrice(double planePrice) {
        this.planePrice = planePrice;
    }
}
