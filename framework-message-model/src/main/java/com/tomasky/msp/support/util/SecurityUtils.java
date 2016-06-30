package com.tomasky.msp.support.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
	
	
	
	public static  String sha1(String str) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("sha1");
		StringBuilder sb = new StringBuilder();
		md.update(str.getBytes());
		byte[] digest = md.digest();
		for (byte b : digest) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}
