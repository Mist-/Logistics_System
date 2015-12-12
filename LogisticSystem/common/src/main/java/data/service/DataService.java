package data.service;

import data.enums.DataState;
import data.enums.POType;
import data.message.ResultMessage;
import data.po.DataPO;
import data.po.UserPO;
import utils.FileIOHelper;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * 所有数据服务的父接口。提供基本的增删改查服务。
 *
 * @author Mouse
 */
public interface DataService extends Remote {

    /**
     * 搜索指定数据类型的PO （提供默认实现）
     *
     * 前置条件：无
     * 后置条件：无
     *
     * @param type 描述将要搜索的数据类型，详情见<code>POType</code>
     * @param key  搜索关键字
     * @return 返回这个PO的引用。在使用时根据实际情况使用强制类型转换. null表示没有找到相关数据
     */
    default DataPO search(POType type, long key) throws RemoteException {
        ArrayList<DataPO> list = getPOList(type);
        for (DataPO data: list) {
            if (data.getSerialNum() == key) return data;
        }
        return null;
    }

    /**
     * 修改相应PO信息。匹配data.serialNum的PO项中的所有属性值全部被替换为<code>data</code>中的属性值
     *
     * 前置条件：无
     * 后置条件：无
     *
     * @param data 将要修改成的属性值集合
     * @return <code>SUCCESS</code>表示修改成功，NOTEXIST表示将要修改的PO项目不存在
     */
    default ResultMessage modify(DataPO data) throws RemoteException {
        ArrayList<DataPO> list = getPOList(data.getPOType());
        DataPO dataToModift = null;
        for (DataPO dat: list) {
            if (dat.getSerialNum() == data.getSerialNum()) {
                dataToModift = dat;
            }
        }

        if (dataToModift == null) {
            return ResultMessage.NOTEXIST;
        }
        else {
            list.remove(dataToModift);
            list.add(data);
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 增加一个PO项目到表中
     * <p>
     * 前置条件：无
     * 后置条件：无
     *
     * @param data 将要添加的数据项
     * @return EXIST表示存在相同序列号的项，添加失败；SUCCESS表示添加成功
     */
    default ResultMessage add(DataPO data) throws RemoteException {
        ArrayList<DataPO> list = getPOList(data.getPOType());
        if (list == null) return ResultMessage.FAILED;
        for (DataPO dat: list) {
            if (dat.getSerialNum() == data.getSerialNum()) {
                return ResultMessage.EXIST;
            }
        }
        list.add(data);
        return ResultMessage.SUCCESS;
    }

    /**
     * 从PO表中删除一个PO项
     * <p>
     * 前置条件：无
     * 后置条件：无
     *
     * @param data 将要删除的PO项目.
     * @return NOTEXIST表示要删除的项目不再表中。SUCCESS表示删除成功
     */
    default ResultMessage delete(DataPO data) throws RemoteException {
        ArrayList<DataPO> list = getPOList(data.getPOType());
        boolean contains = false;

        for (int i = 0; i < list.size(); ++i) {

            if (list.get(i).getSerialNum() == data.getSerialNum()) {
                list.remove(i);
                --i;
                contains = true;
            }
        }
        if (!contains) return ResultMessage.NOTEXIST;
        else return ResultMessage.SUCCESS;
    }

    default ResultMessage delete(POType type) throws RemoteException {
        getPOList(type).clear();
        return ResultMessage.SUCCESS;
    }

    /**
     * 获得PO项表的引用  （必须重写）
     * <p>
     * 前置条件：无
     * 后置条件：无
     *
     * @param type 将要获得的引用类型
     * @return 返回表的引用
     */
    ArrayList<DataPO> getPOList(POType type) throws RemoteException;

    /**
     * 从文件中读取PO到内存中  （必须重写）
     * <p>
     * 前置条件：无
     * 后置条件：无
     *
     * @param type 需要读取的PO类型
     * @param version 版本。current表示正在使用的版本，历史版本用 yyyy_mm_dd 表示。保存在相应的文件夹下
     * @return SUCCESS表示读取成功该
     */
    default ArrayList<DataPO> getPOListFromFile(POType type, String version) throws RemoteException {

        if (getPOList(type) == null) {
            return null;
        }
        ArrayList<DataPO> poListfromFile = (ArrayList<DataPO>) FileIOHelper.getFromFile(version + "/" + type.name() + ".DAT");
        if (poListfromFile == null) {
            return null;
        }
        ArrayList<DataPO> dest = getPOList(type);
        dest.addAll(poListfromFile.stream().collect(Collectors.toList()));
        System.out.println("从\"" + version + "/" + type.name() + ".DAT" + "\"中读取了" + dest.size() + "条记录 - " + Calendar.getInstance().getTime());
        return poListfromFile;
    }


    /**
     * 将内存中的PO保存到文件中  （必须重写）
     *
     * 前置条件：无
     * 后置条件：无
     *
     * @param type 需要保存的PO类型
     * @param version
     * @return SUCCESS表示保存成功
     */
    default ResultMessage savePOListToFile(POType type, String version) throws RemoteException {
        if (getPOList(type) != null) {
            System.out.println("向\"" + version + "/" + type.name() + ".DAT" + "\"中保存了" + getPOList(type).size() + "条记录 - " + Calendar.getInstance().getTime());
            FileIOHelper.saveToFile(getPOList(type), version + "/" + type.name() + ".DAT");
        }
        return ResultMessage.SUCCESS;
    }
    /**
     * 初始化操作。主要包括从文件中读取数据，让Data服务取得列表的引用。
     */
    default void init() throws RemoteException {
        for (POType po: POType.values()) {
            getPOListFromFile(po, "current");
        }
    }

    /**
     * 清理操作。主要包括将未保存的更改保存到文件中。
     */
    default void finish() throws RemoteException {
        for (POType po : POType.values()) {
            savePOListToFile(po, "current");
        }
    }

    /**
     * 按类型获取待审批的单据。
     *
     * @param type 将要获取的订单类型。
     * @return 包含所有待审批单据的列表
     * @throws RemoteException
     */
    default ArrayList<DataPO> getUnapprovedPO(POType type) throws RemoteException {
        ArrayList<DataPO> result = new ArrayList<>();
        if (!getHashMap().containsKey(type)) return null;
        else {
            ArrayList<DataPO> pos = getHashMap().get(type);
            result.addAll(pos.stream().filter(dataPO -> dataPO.getState() == DataState.APPROVING).collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * 获取刚刚被审批通过的单据。
     * 所有单据只能被这个方法获取一次。
     *
     * @param type 需要获取的po类型
     * @return 包含所有刚审批单据的列表
     * @throws RemoteException
     */
    default ArrayList<DataPO> getNewlyApproved(POType type) throws RemoteException {
        ArrayList<DataPO> result = getNewlyApproved().stream().filter(dataPO -> dataPO.getPOType() == type).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < getNewlyApproved().size(); i++) {
            if (getNewlyApproved().get(i).getPOType() == type) {
                getNewlyApproved().remove(i);
                --i;
            }
        }
        return result;
    }


    /**
     * 总经理将单据审批通过。
     * 被审批通过的单据将会可以被<code>getNewlyApproved</code>方法获取到。
     *
     * @param datapo 需要审批通过的单据
     */
    default ResultMessage approveOf(DataPO datapo) throws RemoteException {
        for (int i = 0; i < getPOList(datapo.getPOType()).size(); ++i) {
            if (datapo.getSerialNum() == datapo.getSerialNum()) {
                datapo.setState(DataState.APPROVED);
            }
        }
        getNewlyApproved().add(datapo);
        modify(datapo);
        return ResultMessage.SUCCESS;
    }

    /**
     * 禁止使用！
     * @return 禁止使用！所以不告诉你返回了什么。
     */
    HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException;

    /**
     *
     * @return 禁止使用，所以不告诉你返回了什么。
     * @throws RemoteException
     */
    ArrayList<DataPO> getNewlyApproved() throws RemoteException;
}
