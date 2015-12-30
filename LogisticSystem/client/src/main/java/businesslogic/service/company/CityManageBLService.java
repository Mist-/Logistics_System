package businesslogic.service.company;


import data.message.ResultMessage;
import data.vo.CityTransVO;

public interface CityManageBLService {

    /**
     * 获得城市之间物流信息CityTransInfoVO数据
     *
     * @param fromcity 起始城市的名称
     * @param tocity  结束城市的名称
     * @return  获得CityTransInfoVO的引用
     */
     CityTransVO getCityTransInfo(String fromcity, String tocity);

    /**
     * 修改城市之间物流信息
     *
     * @param cityTransVO  城市之间物流信息的VO
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
     ResultMessage modifyCityInfo(CityTransVO cityTransVO);

    /**
     * 添加城市之间物流信息
     *
     * @param cityTransVO  城市之间物流信息的VO
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage addCityTransInfo(CityTransVO cityTransVO);

}
