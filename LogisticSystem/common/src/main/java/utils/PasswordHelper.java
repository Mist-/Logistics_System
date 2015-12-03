package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成字符串的md5校验值
 * Created by Mouse on 2015/10/22 0022.
 */
public class PasswordHelper {
    private PasswordHelper() {
    }

    public static String generateMD5Checksum(String s) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] input = s.getBytes();
        if (messageDigest == null) return "";
        messageDigest.update(input);
        byte[] result = messageDigest.digest();
        String rst = "";
        for (byte b : result) {
            rst += Integer.toHexString(b);
        }
        String res = "";
        for (int i = 0; i < rst.length(); ++i) {
            if (rst.charAt(i) != 'f') {
                res += rst.charAt(i);
            }
        }
        return res;
    }
}
