package businesslogic.impl.transfer.center;

import data.enums.StorageArea;
import data.vo.StoragePositionAndOrderID;

/**
 * ���ѭ����������ѭ��������ԭ��
 * @author xu
 *
 */
public interface StorageInfoService {
	public StoragePositionAndOrderID getOrderID(StorageArea transferType) ;
}
