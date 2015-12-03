package utils;

import data.enums.POType;

import java.util.Random;

public class IDGenerator {

    protected IDGenerator() { }

    public static long getNextID(POType order) {
        // TODO: µÈ´ýÍê³É
        Random random = new Random();
        return random.nextLong();
    }


}
