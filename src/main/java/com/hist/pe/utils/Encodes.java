package com.hist.pe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


public class Encodes {

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {

		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static String stringToMd5(){
		String x = "02a3f0772fcca9f4";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			
			md5.update(new String(x).getBytes());
			
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}

}