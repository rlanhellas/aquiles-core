package br.com.aquiles.security.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Md5Utils {

    private static final Logger logger = Logger.getLogger(Md5Utils.class);

    public static String convertToMd5(String senha) {

        if (isValidMD5(senha)) {
            throw new RuntimeException("A Senha já está criptografada em MD5");
        }

        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));

            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
                        3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            return null;
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            return null;
        }
    }

    public static boolean isValidMD5(String s) {
        return s.matches("[a-fA-F0-9]{32}");
    }


}
