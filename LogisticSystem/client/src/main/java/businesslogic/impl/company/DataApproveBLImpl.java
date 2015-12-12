package businesslogic.impl.company;


import businesslogic.service.company.DataApproveBLService;
import data.enums.DataType;
import data.enums.POType;
import utils.DataServiceFactory;
import data.message.ResultMessage;
import data.po.*;
import data.service.*;
import data.vo.*;
import utils.Connection;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * Created by apple on 2015/11/15.
 */
public class DataApproveBLImpl implements DataApproveBLService {

    private CompanyDataService company = null;
    private FinancialDataService financial = null;
    private OrderDataService order = null;
    private StorageDataService storage = null;
    private TransferDataService transfer = null;

    private DataService dataService = null;
    private ResultMessage resultMessage = null;
    //private Vector<POType> dataType = null;

    public DataApproveBLImpl(){
        company = (CompanyDataService) DataServiceFactory.getDataServiceByType(DataType.CompanyDataService);
        financial = (FinancialDataService) DataServiceFactory.getDataServiceByType(DataType.FinancialDataService);
        order = (OrderDataService) DataServiceFactory.getDataServiceByType(DataType.OrderDataService);
        storage = (StorageDataService) DataServiceFactory.getDataServiceByType(DataType.StorageDataService);
        transfer = (TransferDataService) DataServiceFactory.getDataServiceByType(DataType.TransferDataService);

    }

    @Override
    public ArrayList<DataPO> getUnapprovedData(POType type) {
        ArrayList<DataPO> plist = new ArrayList<>();
        DataService ds = DataServiceFactory.getDataServiceByPO(type);
        try {
            plist.addAll(ds.getUnapprovedPO(type));
        } catch (RemoteException e) {
            System.err.println("获取待审批订单时发生了网络错误 - " + Calendar.getInstance().getTime());
        }
        return plist;
    }

    @Override
    public ResultMessage approveData(POType type, long id) {
        try {
            DataPO dataPO = DataServiceFactory.getDataServiceByPO(type).search(type,id);
            DataService ds = DataServiceFactory.getDataServiceByPO(type);
            resultMessage = ds.approveOf(dataPO);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.NOTCONNECTED;
        }

        return resultMessage;
    }

    @Override
    public ResultMessage approveAll(POType type) {
        ArrayList<DataPO> plist = new ArrayList<>();
        DataService ds = DataServiceFactory.getDataServiceByPO(type);
        try {
            plist.addAll(ds.getUnapprovedPO(type));
            for(int i=0;i<plist.size();i++){
                ds.approveOf(plist.get(i));
            }
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("获取待审批订单时发生了网络错误 - " + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.NOTCONNECTED;
        }
        return resultMessage;
    }

    //下面9个方法是针对不同类型的单据类型具体修改单据内容
    @Override
    public ResultMessage modifyStorageInList(StorageInVO storageInVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.STORAGEINLIST);
        try {
            StorageInListPO storageInListPO = (StorageInListPO) dataService.search(POType.STORAGEINLIST,storageInVO.getId());
            storageInListPO.setDate(storageInVO.getDate());
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
         }

        return resultMessage;
    }

    @Override
    public ResultMessage modifyStorageOutList(StorageOutVO storageOutVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.STORAGEOUTLIST);
        try {
            StorageOutListPO storageOutListPO = (StorageOutListPO) dataService.search(POType.STORAGEOUTLIST,Long.valueOf(storageOutVO.getId()));
            storageOutListPO.setTransferNum(Long.valueOf(storageOutVO.getTransferNum()));
            storageOutListPO.setTransferType(storageOutVO.getTransferType());
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifyArrival(ArrivalVO arrivalVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.ARRIVAL);
        try {
            ArrivalPO arrivalPO = (ArrivalPO) dataService.search(POType.ARRIVAL,arrivalVO.getId());
                arrivalPO.setDate(arrivalVO.getDate());
                arrivalPO.setFromName(arrivalVO.getFromName());
                arrivalPO.setDestName(arrivalVO.getDestName());
                //arrivalPO.setStockStatus(arrivalVO.getStatus()); sx注释
            resultMessage = ResultMessage.SUCCESS;

        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifyEntruck(EntruckListVO entruckListVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.ENTRUCK);
        try {
            EntruckPO entruckPO = (EntruckPO)dataService.search(POType.ENTRUCK,entruckListVO.getEntruckListID());
            entruckPO.setDestID(Long.parseLong(entruckListVO.destID));
            entruckPO.setFrom(Long.valueOf(entruckListVO.fromID));
            entruckPO.setEscortName(entruckListVO.escortName);
            entruckPO.setMonitorName(entruckListVO.monitorName);
            entruckPO.setVehicleID(Long.valueOf(entruckListVO.vehicleID));
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifyOrder(OrderVO orderVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.ORDER);
        try {
            OrderPO orderPO = (OrderPO)dataService.search(POType.ORDER,orderVO.id);
            orderPO.setFee(orderVO.fee);
            orderPO.setWeight(orderVO.weight);
            orderPO.setStockNum(orderVO.stockNum);
            orderPO.setServiceType(orderVO.serviceType);
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifyPayment(PaymentVO paymentVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.PAYMENT);
        try {
            PaymentPO paymentPO = (PaymentPO) dataService.search(POType.PAYMENT,paymentVO.getId());
            paymentPO.setAccount(paymentVO.getAccount());
            paymentPO.setDate(paymentVO.getDate());
            paymentPO.setExInfo(paymentVO.getExInfo());
            paymentPO.setInfo(paymentVO.getInfo());
            paymentPO.setMoney(paymentVO.getMoney());
            paymentPO.setName(paymentVO.getName());
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }

        return resultMessage;
    }

    @Override
    public ResultMessage modifyReceipt(ReceiptVO receiptVO) {

        dataService = DataServiceFactory.getDataServiceByPO(POType.RECEIPT);
        try {
            ReceiptPO receiptPO = (ReceiptPO) dataService.search(POType.RECEIPT,receiptVO.getId());
            receiptPO.setMoney(receiptVO.getMoney());
            receiptPO.setDate(receiptVO.getDate());
            receiptPO.setSender(receiptVO.getSender());
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifySend(SendListVO sendListVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.SEND);
        try {
            SendListPO sendListPO = (SendListPO) dataService.search(POType.SEND,sendListVO.id);
            sendListPO.setSender(sendListVO.senderName);
            resultMessage = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return resultMessage;
    }

    @Override
    public ResultMessage modifyTransferList(TransferListVO transferListVO) {
        dataService = DataServiceFactory.getDataServiceByPO(POType.TRANSFERLIST);
        try {
            TransferListPO transferListPO = (TransferListPO)dataService.search(POType.TRANSFERLIST,Long.valueOf(transferListVO.transferListID));
            transferListPO.setVehicleCode(Long.valueOf(transferListVO.vehicleCode));
            transferListPO.setFee(Double.valueOf(transferListVO.fee));
            transferListPO.setStaffName(transferListVO.staff);
            transferListPO.setTargetCenterName(transferListVO.targetName);
            transferListPO.setDate(transferListVO.date);
        } catch (RemoteException e) {
            System.err.println("与服务器(" + Connection.RMI_PREFIX + ")的连接断开 -" + Calendar.getInstance().getTime());
            resultMessage = ResultMessage.FAILED;
        }
        return null;
    }

//    @Override
//    public void endApproveData() {
//    //TODO
//    }
}
