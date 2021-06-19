package com.ccg.demo;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenchonggui
 * @version 1.0
 * @date_time 2021/6/19 16:35
 */
public class RsaDemo {

    public static void main(String[] args) throws Exception {
        // testRsa();

        // 测试加解密
//        String ccgEncrypt = ccgEncrypt("欠妥右吉一一了中在也在一的地以要");
//        System.out.println("密文: " + ccgEncrypt);
//
//        String ccgDecrypt = ccgDecrypt(ccgEncrypt);
//        // String ccgDecrypt = ccgDecrypt("==");
//        System.out.println("明文: " + ccgDecrypt);

        /**
         * generate key pairs
         */
        Map<String, Key> map =  getKeyMap();
        System.out.println("map = " + map);
    }

    final static String PRIVATE_KEY = "privateKey";
    final static String PUBLIC_KEY = "publicKey";
    final static String TYPE = "RSA/ECB/PKCS1Padding";

    // 测试环境 密钥对
    final static String PRK = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI1/e7mfe1BLP4AKgytTop1SIDnVYf4U1pC2IS5in4Znl+1F96I5Uw+OOUXWyi/UKECCQTlhJX1XHDeFX92yMSF11HfLYvFWO5GUVuHNBpMV82uApLxCoLEQ5TQEYtw2MYPxDWRfF7rJM0HfBnSlCPlOzoprhie6u6KFrQQ9G01rAgMBAAECgYBrfIQIJdYO0JqUMWgi6Y2F5HUGecnOsRWtKC1chx6XguouHBBY8yLdljR7kQZV/tv05P2XEOzhoeKVaoPAbTRUCHNn7kORzgoAi02yLRFXLB2+aB2Cvm7w8f6C2eoZ1zOx6rpT+AA+WjEwqDF3sAou3ijHn6PQZ4lLLF8QmUj4AQJBAOVVlOSKqkHTBVMgrIaAt8PRyYEHhXnwYX/GH+3eBjX9D7jBPicWntp8SFX5HKZJSSZODI/OTaviiYbbQsEFpesCQQCd81hh6S9VwbNZc28Z1EjXriVxytIulALhoXXJsHa4Gmh2b0mgWVukbCFujGsy3FNZijYnMC3rqqOkOPxkYZaBAkEAruVBk7mcdm60FN1KNZlci94lyl0uEfycnpE+MG2uVuYTY1ccSzsZEo7Nq6M7kiU5wBitw84VtgwhzdLRadsg2QJATqnF56VZhK0eZDtAJq35xSiez2hCex4NvU9LOTJgPdoxEmLkWS6HnICZudPHA6KFBD/4LlYpWIg4TW5wDJIpgQJBAJGVseO78JCy4HLVKE9zJ/0ksiyoJUr/rPO+Rkvzlwrmp69cQJLWtKlkhrh8fvc0NpoISu+HJKOFyouSgRyC0j4=";
    final static String PUK = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNf3u5n3tQSz+ACoMrU6KdUiA51WH+FNaQtiEuYp+GZ5ftRfeiOVMPjjlF1sov1ChAgkE5YSV9Vxw3hV/dsjEhddR3y2LxVjuRlFbhzQaTFfNrgKS8QqCxEOU0BGLcNjGD8Q1kXxe6yTNB3wZ0pQj5Ts6Ka4Ynuruiha0EPRtNawIDAQAB";
    /**
     * 公私钥 加解密流程
     * @throws Exception
     */
    public static void testRsa()throws Exception {
        Map<String, Key> map = getKeyMap();

        // type = "RSA";
        System.out.println("\r\n公钥加密 - 私钥解密");
        String data = "这是一条测试数据.";
        byte[] enBytes = encrypt(map.get(PUBLIC_KEY), data.getBytes(StandardCharsets.UTF_8));

        String enStr = Base64.getEncoder().encodeToString(enBytes);
        System.out.println("加密后BASE64内容: " + enStr);

        byte[] deBytes = decrypt(map.get(PRIVATE_KEY), Base64.getDecoder().decode(enStr));
        String deStr = new String(deBytes, StandardCharsets.UTF_8);
        System.out.println("解密后的内容: " + deStr);


        System.out.println("\r\n私钥加密 - 公钥解密");
        String data2 = "一二三四五六七八九十零";
        byte[] enBytes2 = encrypt(map.get(PRIVATE_KEY), data2.getBytes(StandardCharsets.UTF_8));
        String enStr2 = Base64.getEncoder().encodeToString(enBytes2);
        System.out.println("加密后BASE64内容: " + enStr2);

        byte[] deBytes2 = decrypt(map.get(PUBLIC_KEY), Base64.getDecoder().decode(enStr2));
        String deStr2 = new String(deBytes2, StandardCharsets.UTF_8);
        System.out.println("解密后的内容: " + deStr2);
    }

    public static String ccgEncrypt(String data) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(PUK));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        byte[] bytes = Base64.getEncoder().encode(data.getBytes("gbk"));
        bytes = encrypt(publicKey, bytes);
        bytes = Base64.getEncoder().encode(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String ccgDecrypt(String data) throws Exception {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(PRK));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        byte[] bytes = Base64.getDecoder().decode(data);
        bytes = Base64.getDecoder().decode(bytes);
        bytes = decrypt(privateKey, bytes);
        bytes = Base64.getDecoder().decode(bytes);
        return new String(bytes, "GBK");
    }

    public static byte[] decrypt(Key deKey, byte[] bytes) throws Exception {
        // debug
//        System.out.println("decrypt: " + Arrays.toString(bytes));
        Cipher cipher2 = Cipher.getInstance(TYPE);
        cipher2.init(Cipher.DECRYPT_MODE, deKey);
        return cipher2.doFinal(bytes);
    }

    public static byte[] encrypt(Key enKey, byte[] bytes) throws Exception {
        Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, enKey);
        byte[] result = cipher.doFinal(bytes);
        // debug
        //System.out.println("encrypt: " + Arrays.toString(result));
        return result;
    }

    /**
     * 生成密钥对
     * @return
     * @throws Exception
     */
    public static Map<String, Key> getKeyMap() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed("heihei".getBytes(StandardCharsets.UTF_8));
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keypair = keyPairGenerator.generateKeyPair();

        RSAPublicKey pk1 = (RSAPublicKey) keypair.getPublic();
        String pk1Str = Base64.getEncoder().encodeToString(pk1.getEncoded());
        System.out.println("公钥: " + pk1Str);

        RSAPrivateKey pr1 = (RSAPrivateKey) keypair.getPrivate();
        String pr1Str = Base64.getEncoder().encodeToString(pr1.getEncoded());
        System.out.println("私钥: " + pr1Str);

        Map<String, Key> map = new HashMap<>(2);
        map.put(PRIVATE_KEY, pr1);
        map.put(PUBLIC_KEY, pk1);
        return map;
    }
}