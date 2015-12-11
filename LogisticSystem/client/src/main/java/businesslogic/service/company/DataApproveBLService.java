package businesslogic.service.company;

import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.vo.*;

import java.util.ArrayList;

public interface DataApproveBLService {

    /**
     * 获得未审批的单据DataVO表
     *
     * @param type  单据的类型
     * @return  表的引用
     */
    ArrayList<DataPO> getUnapprovedData(POType type);

    /**
     * 审批单条单据
     *
     * @param type 等待审批的单据类型
     * @param id 等待审批单据的id
     * @return  网络错误是NOTCONNECTED,其余情况由数据层决定
     */
    ResultMessage approveData(POType type, long id);

    /**
     * 审批同一类型的所有单据
     *
     * @param type 等待审批的单据类型
     * @return  审批成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage approveAll(POType type);

    /**
     * 修改入库单PO的数据
     *
     * @param storageInVO 单个入库单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED
     */
    ResultMessage modifyStorageInList(StorageInVO storageInVO);

    /**
     * 修改出库单PO的数据
     *
     * @param storageOutVO 单个出库单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED
     */
    ResultMessage modifyStorageOutList(StorageOutVO storageOutVO);

    /**
     * 修改到达单PO的数据
     *
     * @param arrivalVO 单个到达单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyArrival(ArrivalVO arrivalVO);

    /**
     * 修改装车单PO的数据
     *
     * @param entruckListVO 单个装车单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyEntruck(EntruckListVO entruckListVO);

    /**
     * 修改寄件单PO的数据
     *
     * @param orderVO 单个寄件单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyOrder(OrderVO orderVO);

    /**
     * 修改付款单PO的数据
     *
     * @param paymentVO 单个付款单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyPayment(PaymentVO paymentVO);

    /**
     * 修改收款单PO的数据
     *
     * @param receiptVO 单个收款单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyReceipt(ReceiptVO receiptVO);

    /**
     * 修改派件单PO的数据
     *
     * @param sendListVO 单个派件单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifySend(SendListVO sendListVO);

    /**
     * 修改中转单PO的数据
     *
     * @param transferListVO 单个中转单的VO
     * @return  修改成功是SUCCESS,网络错误是NOTCONNECTED,表单不存在是NOTEXIST
     */
    ResultMessage modifyTransferList(TransferListVO transferListVO);

}
