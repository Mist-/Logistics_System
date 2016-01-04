package businesslogic.service.Transfer.hall;




import businesslogic.impl.user.InstitutionInfo;
/**
 * ¿¼ÂÇÉ¾³ý¸ÃÀà
 * @author xu
 *
 */
public interface TransferHallService {

	public TruckManagementService startTruckManagement();
	public DriverManagementService startDriverManagement();
	public EntruckReceiveService startEntruckReceive() throws Exception;
	public LoadAndSortService startOrderSort();
	public ReceiveMoneyService startReceive();
	public String getUserInfo();
//	public DeliveryListVO getTransferList(long transferListCode);
//	public ResultMessage saveOrderArrival();
//	public ArrayList<SendVO> createSend();
//	public ResultMessage saveSend();
//
//	public EntruckListVO createEntruckList();
//	public ResultMessage modifyEntruckList(EntruckListVO list, ArrayList<String> input);
//	public ResultMessage deleteOrder(OrderVO order);
//	public ResultMessage addOrder(OrderVO order);
//	public EntruckListVO completeEntruckList(EntruckListVO list);
//	public EntruckListVO getEntruckList(long code);
	
	
}
