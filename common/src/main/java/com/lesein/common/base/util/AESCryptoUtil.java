package com.lesein.common.base.util;

import com.lesein.common.base.exception.BusinessValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author WangJie
 * @date 2023/4/24
 */
public class AESCryptoUtil {
    private static final Logger log = LoggerFactory.getLogger(AESCryptoUtil.class);

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String AES = "AES";

    /**
     * 加密
     *
     * @param content
     * @param key 必须为16的倍数
     * @return
     */
    public static String encrypt(String content, String key) {
        Key secretKey = getKey(key);
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = content.getBytes(DEFAULT_ENCODING);
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(result);
        } catch (Exception e) {
            log.warn("AES加密失败,参数:{}，错误信息:{}", content, e);
            return "";
        }
    }

    /**
     * 解密
     *
     * @param content
     * @param key 必须为16的倍数
     * @return
     */
    public static String decrypt(String content, String key) {
        Key secretKey = getKey(key);
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] c = decoder.decodeBuffer(content);
            byte[] result = cipher.doFinal(c);
            return new String(result, DEFAULT_ENCODING);
        } catch (Exception e) {
            log.warn("AES解密失败,参数:{}，错误信息:{}", content, e);
            return "";
        }
    }


    private static Key getKey(String keySeed) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new BusinessValidateException(e);
        }
    }
}
