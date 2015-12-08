package businesslogic.service.Transfer.hall;
import businesslogic.service.Transfer.center.TransferLoadService;
import businesslogic.service.Transfer.center.TransferReceiveService;

/**
 * 中转中心服务 工厂
 * @author xu
 *
 */
public interface TransferCenterService {

	/**
	 * 发起一次中转装运服务
	 * @return
	 */
	public TransferLoadService startTransferLoad();
	
	/**
	 * 发起一次中转接收服务
	 * @return
	 */
	public TransferReceiveService startTransferReceiver();
	
	
}