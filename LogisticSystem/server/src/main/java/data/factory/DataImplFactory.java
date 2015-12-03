package data.factory;

import data.enums.DataType;
import data.impl.*;
import data.service.DataService;

import java.rmi.RemoteException;

/**
 * Created by mist on 2015/11/30 0030.
 */
public class DataImplFactory {
    public static DataService getDataImpl(DataType type) throws RemoteException {
        switch (type) {
            case CompanyDataService:
                return new CompanyDataSerializableImpl();
            case FinancialDataService:
                return new FinancialDataSerializableImpl();
            case OrderDataService:
                return new OrderDataSerializableImpl();
            case StorageDataService:
                return new StorageDataSerializableImpl();
            case TransferDataService:
                return new TransferDataSerializableImpl();
            case UserDataService:
                return new UserDataSerializableImpl();
        }
        return null;
    }
}
