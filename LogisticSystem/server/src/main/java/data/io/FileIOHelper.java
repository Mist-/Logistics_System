package data.io;

import data.po.DataPO;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * Created by mist on 2015/11/25 0025.
 */
public class FileIOHelper {

    public static void SaveToFile(ArrayList<DataPO> arrayList, String filePath) {
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        try {
            fout = new FileOutputStream(new File(filePath));
            oout = new ObjectOutputStream(fout);
            oout.writeObject(arrayList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oout != null) {
                    oout.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<DataPO> getFromFile(String filePath) {
        FileInputStream fin = null;
        ObjectInputStream oin = null;
        ArrayList<DataPO> result  = null;
        try {
            fin = new FileInputStream(new File(filePath));
            oin = new ObjectInputStream(fin);
            result = (ArrayList<DataPO>) oin.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("数据文件不存在");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oin != null) {
                    oin.close();
                }
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
