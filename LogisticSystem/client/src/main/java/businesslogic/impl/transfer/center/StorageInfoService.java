package businesslogic.impl.transfer.center;

import data.enums.StorageArea;
import data.vo.StoragePositionAndOrderID;

/**
 * 解决循环依赖，遵循依赖倒置原则
 * @author xu
 *
 */
public interface StorageInfoService {
	public StoragePositionAndOrderID getOrderID(StorageArea transferType) ;
}
