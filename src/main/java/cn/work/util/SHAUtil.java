package cn.work.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static cn.work.spring.config.LibraryConfig.KEY_SHA;

public class SHAUtil {


    public static String getEncrypt(String inputStr) {
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {e.printStackTrace();}
        if (sha != null)
            return sha.toString(32);
        else
            return null;
    }
}
