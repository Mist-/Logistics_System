package businesslogic.service.company;


import data.message.ResultMessage;
import data.vo.CityTransVO;

public interface CityManageBLService {

    //找到城市信息
    public CityTransVO getCityTransInfo(String fromcity, String tocity);

    //城市管理的过程
    public ResultMessage modifyCityInfo(CityTransVO cityTransVO);

    public ResultMessage addCityTransInfo(CityTransVO cityTransVO);

    public void endCityManage();
}
