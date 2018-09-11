package com.conv.util;

import java.io.UnsupportedEncodingException;

public class NameUtil {

	public static String toCN(String str) {
		if (str == null)
			return str;
		try {
			return new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}
}
