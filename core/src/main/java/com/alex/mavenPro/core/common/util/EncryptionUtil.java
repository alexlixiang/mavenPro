package com.alex.mavenPro.core.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;

public class EncryptionUtil {
	private static String algorithm_md5 = "MD5";
	private static String algorithm_sha = "SHA";
	private static String algorithm_sha256 = "SHA-256";
	private static String algorithm_sha512 = "SHA-512";

	/**
	 * 比较原文与MD5加密后信息是否一致
	 * 
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static boolean compareMD5Encode(String message, String encode) throws Exception {
		return compare(algorithm_md5, message, encode);
	}

	/**
	 * 比较原文与SHA加密后信息是否一致
	 * 
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static boolean compareSHAEncode(String message, String encode) throws Exception {
		return compare(algorithm_sha, message, encode);
	}

	/**
	 * 比较原文与SHA-256加密后信息是否一致
	 * 
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static boolean compareSHA256Encode(String message, String encode) throws Exception {
		return compare(algorithm_sha256, message, encode);
	}

	/**
	 * 比较原文与SHA-512加密后信息是否一致
	 * 
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static boolean compareSHA512Encode(String message, String encode) throws Exception {
		return compare(algorithm_sha512, message, encode);
	}

	/**
	 * 比较结果
	 * 
	 * @param algorithm
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	private static boolean compare(String algorithm, String message, String encode) throws Exception {
		if (message == null) {
			if (encode == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (encode == null) {
				return false;
			} else {
				return encode.equals(encode(algorithm, message));
			}
		}
	}

	/**
	 * 加密处理
	 * 
	 * @param code
	 * @param message
	 * @return
	 * @throws Exception
	 */
	private static String encode(String algorithm, String message) throws Exception {
		String encode = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			encode = base64en.encode(md.digest(message.getBytes()));
		} catch (Exception e) {
			throw e;
		}
		return encode;
	}

	/**
	 * 将摘要信息转换成MD5编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return MD5编码之后的字符串
	 */
	public static String md5Encode(String message) throws Exception {
		if (message == null) {
			return null;
		}
		return encode(algorithm_md5, message);
	}

	/**
	 * 将摘要信息转换成SHA编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA编码之后的字符串
	 */
	public static String shaEncode(String message) throws Exception {
		if (message == null) {
			return null;
		}
		return encode(algorithm_sha, message);
	}

	/**
	 * 将摘要信息转换成SHA-256编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA-256编码之后的字符串
	 */
	public static String sha256Encode(String message) throws Exception {
		if (message == null) {
			return null;
		}
		return encode(algorithm_sha256, message);
	}

	/**
	 * 将摘要信息转换成SHA-512编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA-512编码之后的字符串
	 */
	public static String sha512Encode(String message) throws Exception {
		if (message == null) {
			return null;
		}
		return encode(algorithm_sha512, message);
	}

	/**
	 * 将密码信息转换成SHA-512编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA-512编码之后的字符串
	 */
	public static String encodePassword(String password) throws Exception {
		return sha512Encode(password);
	}

	/**
	 * 比较密码原文与SHA-512加密后信息是否一致
	 * 
	 * @param message
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static boolean comparePasswordEncode(String password, String encode) throws Exception {
		return compareSHA512Encode(password, encode);
	}
	
	/**
     *  BASE64解密 以String密文输入,String明文输出
     * 
     * @param strMi
     * @return
     */
    public static String getDesString(String strMi) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = null;
        String a="";
        try {
            decodedBytes = decoder.decodeBuffer(strMi);
            a=new String(decodedBytes,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
}
