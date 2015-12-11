package businesslogic.impl.company;

import businesslogic.service.company.CityManageBLService;
import data.enums.DataType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.CityTransPO;
import data.service.CompanyDataService;
import data.vo.CityTransVO;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.Calendar;

/**
 *
 * Created by wyc on 2015/11/12.
 */
public class CityManageBLImpl implements CityManageBLService {

    private CompanyDataService company = null;
    private CityTransVO cityTransVO = null;
    private CityTransPO cityTransPO = null;
    private ResultMessage resultMessage = null;

    public CityManageBLImpl(){
        company = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
    }

    @Override
    public CityTransVO getCityTransInfo(String fromCity, String toCity) {
        try {
            cityTransPO = company.searchByCityName(fromCity, toCity);
            if(cityTransPO!=null) {
                String vFromCity = cityTransPO.getFromCity();
                String vToCity = cityTransPO.getToCity();
                double distance = cityTransPO.getDistance();
                double trunkPrice = cityTransPO.getTrunkPrice();
                double trainPrice = cityTransPO.getTrainPrice();
                double planePrice = cityTransPO.getPlanePrice();
                cityTransVO = new CityTransVO(vFromCity, vToCity, distance, trunkPrice, trainPrice, planePrice);
            }
            else {
                cityTransVO = null;
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            cityTransVO = null;
        }
        return cityTransVO;
    }

    @Override
    public ResultMessage modifyCityInfo(CityTransVO cityTransVO) {
        String fromCity = cityTransVO.getFromCity();
        String toCity = cityTransVO.getToCity();
        try {
            cityTransPO = company.searchByCityName(fromCity,toCity);
            cityTransPO.setTrunkPrice(cityTransVO.getTrunkPrice());
            cityTransPO.setTrainPrice(cityTransVO.getTrainPrice());
            cityTransPO.setPlanePrice(cityTransVO.getPlanePrice());
            cityTransPO.setDistance(cityTransVO.getDistance());
            resultMessage = company.modify(cityTransPO);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage addCityTransInfo(CityTransVO cityTransVO) {
        try {
            String fromCity = cityTransVO.getFromCity();
            String toCity = cityTransVO.getToCity();
            cityTransPO = company.searchByCityName(fromCity,toCity);
            if(cityTransPO==null){
                cityTransPO = new CityTransPO(fromCity, toCity, cityTransVO.getDistance(), cityTransVO.getTrainPrice(),
                                                                cityTransVO.getTrainPrice(), cityTransVO.getPlanePrice());
                resultMessage = company.add(cityTransPO);
            }
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    public void endCityManage() {
        //TODO
    }
}
