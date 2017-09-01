package com.codi.base.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author shi.pengyan
 * @date 2016年10月8日 下午1:06:00
 */
public class PasswordUtil {

    /**
     * 默认公钥
     */
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCa57BhJFo03XzxlE5Umu67duEWljV2SwfHPHg1chyUTUlPNhvtwcIkaXLGbFYl/zsPzHIA9AGXxtJKd5yEbTOPsNzJSrFWYR/ZpvTqIMKAyAB5WHu8xu7omOahI5VMCYLOEHVOzstsWA5Xo5V3xDB17tyA8O3rQ1h7UdgadkfGfwIDAQAB";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("cmd: passwordUtil.java abc");
            return;
        }

        String content = args[0];

        try {
            byte[] buffer = RSAUtil.encrypt(RSAUtil.loadPublicKeyByStr(publicKey), content.getBytes());
            String encryptMsg = Base64.encodeBase64String(buffer);
            System.out.println(encryptMsg);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
