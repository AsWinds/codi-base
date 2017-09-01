package com.codi.base.util;

import org.apache.commons.codec.binary.Base64;

/**
 * RSA Test
 * @author shi.pengyan
 * @date 2016年10月6日 下午2:07:21
 */
public class RSAUtilTest {

	//TODO 
//    @Test
    public void encryptAndDecrypt() throws Exception {

        String filepath = "c:/";

        // RSAUtil.genKeyPair(filepath);

        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText = "13579%^&我@#";

        plainText = "Codi123456789";

        // 公钥加密过程
        byte[] cipherData = RSAUtil.encrypt(RSAUtil.loadPublicKeyByStr(RSAUtil.loadPublicKeyByFile(filepath)),
                plainText.getBytes());
        String cipher = Base64.encodeBase64String(cipherData);
        // 私钥解密过程
        byte[] res = RSAUtil.decrypt(RSAUtil.loadPrivateKeyByStr(RSAUtil.loadPrivateKeyByFile(filepath)),
                Base64.decodeBase64(cipher));
        String restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        // 私钥加密过程
        cipherData = RSAUtil.encrypt(RSAUtil.loadPrivateKeyByStr(RSAUtil.loadPrivateKeyByFile(filepath)),
                plainText.getBytes());
        cipher = Base64.encodeBase64String(cipherData);
        // 公钥解密过程
        res = RSAUtil.decrypt(RSAUtil.loadPublicKeyByStr(RSAUtil.loadPublicKeyByFile(filepath)),
                Base64.decodeBase64(cipher));
        restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);


        System.out.println("---------------私钥签名过程------------------");
        String content = "13579abc%^&我@#";
        String signstr = RSAUtil.sign(content, RSAUtil.loadPrivateKeyByFile(filepath));
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);
        System.out.println();

        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);

        System.out.println("验签结果：" + RSAUtil.doCheck(content, signstr, RSAUtil.loadPublicKeyByFile(filepath)));
        System.out.println();
    }

}
