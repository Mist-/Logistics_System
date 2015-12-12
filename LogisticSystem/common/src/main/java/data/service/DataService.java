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
 * �������ݷ���ĸ��ӿڡ��ṩ��������ɾ�Ĳ����
 *
 * @author Mouse
 */
public interface DataService extends Remote {

    /**
     * ����ָ���������͵�PO ���ṩĬ��ʵ�֣�
     *
     * ǰ����������
     * ������������
     *
     * @param type ������Ҫ�������������ͣ������<code>POType</code>
     * @param key  �����ؼ���
     * @return �������PO�����á���ʹ��ʱ����ʵ�����ʹ��ǿ������ת��. null��ʾû���ҵ��������
     */
    default DataPO search(POType type, long key) throws RemoteException {
        ArrayList<DataPO> list = getPOList(type);
        for (DataPO data: list) {
            if (data.getSerialNum() == key) return data;
        }
        return null;
    }

    /**
     * �޸���ӦPO��Ϣ��ƥ��data.serialNum��PO���е���������ֵȫ�����滻Ϊ<code>data</code>�е�����ֵ
     *
     * ǰ����������
     * ������������
     *
     * @param data ��Ҫ�޸ĳɵ�����ֵ����
     * @return <code>SUCCESS</code>��ʾ�޸ĳɹ���NOTEXIST��ʾ��Ҫ�޸ĵ�PO��Ŀ������
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
     * ����һ��PO��Ŀ������
     * <p>
     * ǰ����������
     * ������������
     *
     * @param data ��Ҫ��ӵ�������
     * @return EXIST��ʾ������ͬ���кŵ�����ʧ�ܣ�SUCCESS��ʾ��ӳɹ�
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
     * ��PO����ɾ��һ��PO��
     * <p>
     * ǰ����������
     * ������������
     *
     * @param data ��Ҫɾ����PO��Ŀ.
     * @return NOTEXIST��ʾҪɾ������Ŀ���ٱ��С�SUCCESS��ʾɾ���ɹ�
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
     * ���PO��������  ��������д��
     * <p>
     * ǰ����������
     * ������������
     *
     * @param type ��Ҫ��õ���������
     * @return ���ر������
     */
    ArrayList<DataPO> getPOList(POType type) throws RemoteException;

    /**
     * ���ļ��ж�ȡPO���ڴ���  ��������д��
     * <p>
     * ǰ����������
     * ������������
     *
     * @param type ��Ҫ��ȡ��PO����
     * @param version �汾��current��ʾ����ʹ�õİ汾����ʷ�汾�� yyyy_mm_dd ��ʾ����������Ӧ���ļ�����
     * @return SUCCESS��ʾ��ȡ�ɹ���
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
        System.out.println("��\"" + version + "/" + type.name() + ".DAT" + "\"�ж�ȡ��" + dest.size() + "����¼ - " + Calendar.getInstance().getTime());
        return poListfromFile;
    }


    /**
     * ���ڴ��е�PO���浽�ļ���  ��������д��
     *
     * ǰ����������
     * ������������
     *
     * @param type ��Ҫ�����PO����
     * @param version
     * @return SUCCESS��ʾ����ɹ�
     */
    default ResultMessage savePOListToFile(POType type, String version) throws RemoteException {
        if (getPOList(type) != null) {
            System.out.println("��\"" + version + "/" + type.name() + ".DAT" + "\"�б�����" + getPOList(type).size() + "����¼ - " + Calendar.getInstance().getTime());
            FileIOHelper.saveToFile(getPOList(type), version + "/" + type.name() + ".DAT");
        }
        return ResultMessage.SUCCESS;
    }
    /**
     * ��ʼ����������Ҫ�������ļ��ж�ȡ���ݣ���Data����ȡ���б�����á�
     */
    default void init() throws RemoteException {
        for (POType po: POType.values()) {
            getPOListFromFile(po, "current");
        }
    }

    /**
     * �����������Ҫ������δ����ĸ��ı��浽�ļ��С�
     */
    default void finish() throws RemoteException {
        for (POType po : POType.values()) {
            savePOListToFile(po, "current");
        }
    }

    /**
     * �����ͻ�ȡ�������ĵ��ݡ�
     *
     * @param type ��Ҫ��ȡ�Ķ������͡�
     * @return �������д��������ݵ��б�
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
     * ��ȡ�ոձ�����ͨ���ĵ��ݡ�
     * ���е���ֻ�ܱ����������ȡһ�Ρ�
     *
     * @param type ��Ҫ��ȡ��po����
     * @return �������и��������ݵ��б�
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
     * �ܾ�����������ͨ����
     * ������ͨ���ĵ��ݽ�����Ա�<code>getNewlyApproved</code>������ȡ����
     *
     * @param datapo ��Ҫ����ͨ���ĵ���
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
     * ��ֹʹ�ã�
     * @return ��ֹʹ�ã����Բ������㷵����ʲô��
     */
    HashMap<POType, ArrayList<DataPO>> getHashMap() throws RemoteException;

    /**
     *
     * @return ��ֹʹ�ã����Բ������㷵����ʲô��
     * @throws RemoteException
     */
    ArrayList<DataPO> getNewlyApproved() throws RemoteException;
}
