package businesslogic.impl.order;

import java.util.ArrayList;

/**
 * Created by mist on 2015/12/11 0011.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Long> routine = new Order().getRoutine("�Ͼ���-��ϼ��-��ʦ��", "�Ϻ���-������-���ط��ͽ̿����");
        for (long sn: routine) {
            System.out.println(sn);
        }
    }
}
