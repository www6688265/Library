package cn.work.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHAUtil {
    public static final String KEY_SHA = "SHA";

    public static  String  getEncrypt(String inputStr)
    {
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }
}
