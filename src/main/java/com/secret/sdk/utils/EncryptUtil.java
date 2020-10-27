package com.secret.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

@Slf4j
public class EncryptUtil {

    private static boolean initialized = false;
    public static final String AES_TYPE = "AES/CBC/PKCS7Padding";
    public static final String AES_KEY = "AES";

    private EncryptUtil() {

    }

    /**
     * 加密
     *
     * @param secretKey
     * @param iv
     * @param data
     * @return
     */
    public static String encrypt(String secretKey, String iv, String data) throws Exception {
        log.info("加密前   secretKey:{},iv:{},data:{}", secretKey, iv, data);
        byte[] dataByte = data.getBytes();
        byte[] result = encryptOrDecrypt(secretKey, iv, dataByte, Cipher.ENCRYPT_MODE);
        String cipherText = new String(Base64.encodeBase64(result));
        log.info("加密后  cipherText:{}", cipherText);
        return cipherText;
    }

    /**
     * 解密
     *
     * @param secretKey
     * @param iv
     * @param data
     * @return
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String secretKey, String iv, String data) throws Exception {
        byte[] dataByte = Base64.decodeBase64(data);
        return new String(encryptOrDecrypt(secretKey, iv, dataByte, Cipher.DECRYPT_MODE), "UTF-8");
    }

    /**
     * 加密或者解密
     *
     * @param secretKey
     * @param iv
     * @param data
     * @param cipherMode
     * @return
     * @throws Exception
     */
    public static byte[] encryptOrDecrypt(String secretKey, String iv, byte[] data, int cipherMode) throws Exception {
        initialize();
        byte[] keyByte = Base64.decodeBase64(secretKey);
        byte[] ivByte = Base64.decodeBase64(iv);
        Cipher cipher = Cipher.getInstance(AES_TYPE);

        Key sKeySpec = new SecretKeySpec(keyByte, AES_KEY);
        // 初始化
        cipher.init(cipherMode, sKeySpec, generateIV(ivByte));
        return cipher.doFinal(data);
    }

    /**
     * 生成iv
     *
     * @param iv
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidParameterSpecException
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES_KEY);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

}
