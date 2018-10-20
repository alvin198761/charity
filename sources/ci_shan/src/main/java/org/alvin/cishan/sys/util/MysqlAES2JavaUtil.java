package org.alvin.cishan.sys.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * java针对mysql 的aes 加密的专用处理方法
 */
public class MysqlAES2JavaUtil {

	private static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for (byte b : key.getBytes(encoding))
				finalKey[i++ % 16] ^= b;
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * mysql aes 解密 java 实现
	 *
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String mysql_aes_decrypt(String content, String key) throws Exception {
		final Cipher decryptCipher = Cipher.getInstance("AES");
		decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
		return new String(decryptCipher.doFinal(HexUtil.decodeHex(content.toLowerCase().toCharArray())));
	}

	/**
	 * mysql aes 加密 java 实现
	 *
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String mysql_aes_encrypt(String content, String key) throws Exception {
		final Cipher encryptCipher = Cipher.getInstance("AES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
		return new String(HexUtil.encodeHex(encryptCipher.doFinal(content.getBytes("UTF-8")))).toUpperCase();
	}

//
//	public static void main(String [] args){
//		try {
//			System.out.println(mysql_aes_decrypt("A80F0FA861F2DDC67FB2951E29244CEE" ,".dl701."));
//			System.out.println(mysql_aes_encrypt_sql("13918631241" ,".dl701."));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}
