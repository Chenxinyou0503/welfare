package com.welfare.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesUtil {

	/**
	 * Encrypt.
	 *
	 * @param cleartext the cleartext
	 * @param key the key
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static String encrypt(String cleartext, String key) throws InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;

		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(1, keySpec, ivSpec);

		byte[] results = cipher.doFinal(cleartext.getBytes("UTF-8"));

		return toHex(results);
	}

	/**
	 * Decrypt.
	 *
	 * @param encrypted the encrypted
	 * @param key the key
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static String decrypt(String encrypted, String key) throws UnsupportedEncodingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(2, keySpec, ivSpec);

		byte[] results = cipher.doFinal(toByte(encrypted));
		return new String(results, "UTF-8");
	}

	/**
	 * To hex.
	 *
	 * @param buf the buf
	 * @return the string
	 */
	private static String toHex(byte[] buf) {
		if (buf == null) {
			return "";
		}
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; ++i) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	/**
	 * Append hex.
	 *
	 * @param sb the sb
	 * @param b the b
	 */
	private static void appendHex(StringBuffer sb, byte b) {
		sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF)).append("0123456789ABCDEF".charAt(b & 0xF));
	}

	/**
	 * To byte.
	 *
	 * @param hexString the hex string
	 * @return the byte[]
	 */
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;

		byte[] result = new byte[len];
		for (int i = 0; i < len; ++i) {
			String hexStringTemp = hexString.substring(2 * i, 2 * i + 2);
			result[i] = Integer.valueOf(hexStringTemp, 16).byteValue();
		}
		return result;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException{
		String aa = "C6CBE4F6BF78FF7B078A882B38A8B4E972537482CC5A2C78FBEE47B287E5E5E288B409D3819CD8C5204FBCD42F3B08A6";
		System.out.println(AesUtil.decrypt(aa, "108846b64d4b487d0dbeb452d25u0ec9"));
		String accountNameStr = AesUtil.decrypt(aa, "108846b64d4b487d0dbeb452d25u0ec9");
		System.out.println(accountNameStr);
		String[] accountNameArray = accountNameStr.split("|");
		System.out.println(accountNameArray[1]);


		String text = "12313123123&dingna@yunz&ongnet.com";
		String pwd = "108846b64d4b487d0dbeb452d25u0ec9";
		
		String output = AesUtil.encrypt(text, pwd);
		
		String last = AesUtil.decrypt(output, pwd);
		
		System.out.println(output);
		System.out.println(last);
		String[] accountNameArray1 = last.split("&",2);
		System.out.println(accountNameArray1[1]);

//		System.out.println(AesUtil.decrypt("1561EBE3904EFA86417C4E7595D0029DB29F8DE2E6861D50CC778D5A9F4CB6E0", "108846b64d4b497d0dbab4r2d25u0ec9"));

	}
}
