package businesslogic.service.company;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.vo.*;

import java.util.ArrayList;

public interface DataApproveBLService {

    //查找未审批的单据
    public ArrayList<DataPO> getUnapprovedData(POType type);

    //单据审批的过程
    public ResultMessage approveData(POType type, long id);

    public ResultMessage approveAll(POType type);

    //修改[9]种单据的数据
    public ResultMessage modifyStorageInList(StorageInVO storageInVO);

    public ResultMessage modifyStorageOutList(StorageOutVO storageOutVO);

    public ResultMessage modifyArrival(ArrivalVO arrivalVO);

    public ResultMessage modifyEntruck(EntruckListVO entruckListVO);

    public ResultMessage modifyOrder(OrderVO orderVO);

    public ResultMessage modifyPayment(PaymentVO paymentVO);

    public ResultMessage modifyReceipt(ReceiptVO receiptVO);

    public ResultMessage modifySend(SendListVO sendListVO);

    public ResultMessage modifyTransferList(TransferListVO transferListVO);

    public void endApproveData();
}
