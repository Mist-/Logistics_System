package businesslogic.impl.order;

import data.message.LoginMessage;
import data.message.ResultMessage;

import java.util.ArrayList;

/**
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Long> routine = new Order(new LoginMessage(ResultMessage.SUCCESS, 100001)).getRoutine("�Ͼ���-��ϼ��-��ʦ��", "�Ͼ���-�ػ���-���ط��ͽ̿����");
        for (long sn: routine) {
            System.out.println(sn);
        }

    }
}
