package utils;

import data.enums.POType;

import java.util.Random;

public class IDGenerator {

    protected IDGenerator() { }

    public static long getNextID(POType order) {
        // TODO: �ȴ����
        Random random = new Random();
        return random.nextLong();
    }


}
