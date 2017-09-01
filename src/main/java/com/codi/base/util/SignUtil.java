package com.codi.base.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * 签名验证
 *
 * @author shi.pengyan
 * @date 2016年10月6日 下午1:51:42
 */
public class SignUtil {

    private static String codiSignKey = "sign963741";

    /**
     * do not used anymore after 1.0
     */
    @Deprecated
    public static Boolean verifyCodiSign(String request, String codiSign) {
        String sign = request + codiSignKey;
        sign = MD5.MD5Encode(sign);
        if (sign.equals(codiSign)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getRequestString(TreeMap<String, Object> treeMap) {
        String request = "";
        for (String paramName : treeMap.keySet()) {
            request = request + paramName + "=" + treeMap.get(paramName) + "&";
        }

        return request;
    }

    public static boolean verifyCodiSignV2(String requestStr, String token, String sign) {
        return getSign(requestStr, getSalt(token)).equals(sign);
    }

    /**
     * 根据token得到后面需要的盐
     */
    public static byte[] getSalt(String token) {
        try {
            MessageDigest md     = MessageDigest.getInstance("SHA1");
            byte[]        hash   = md.digest(token.getBytes(StandardCharsets.UTF_8));
            int           length = 9;
            if (hash.length <= length) {
                return hash;
            } else {
                byte[] res = new byte[length];
                System.arraycopy(hash, 0, res, 0, length);
                return res;
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO this should not happen
            throw new RuntimeException(e);
        }
    }

    /**
     * HMACMD5 接受任何大小的密钥，并生成长度为 128 位的哈希序列
     */
    public static String getSign(String parmStr, byte[] salt) {
        parmStr = parmStr + codiSignKey;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(salt, "HmacMD5");
            Mac           mac       = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return Hex.encodeHexString(mac.doFinal(parmStr.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            // TODO this should not happen
            throw new RuntimeException(e);
        }
    }
}
