package com.ming.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class UTF8StringTest {

	@Test
	public void testUTF8() {
		Charset charset = StandardCharsets.UTF_8;
		System.out.println("1".getBytes(charset).length);
		System.out.println("a".getBytes(charset).length);
		System.out.println("1234567890".getBytes(charset).length);
		System.out.println("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(charset).length);
		System.out.println("手".getBytes(charset).length);
		System.out.println("我的".getBytes(charset).length);
		System.out.println("苹果MAC".getBytes(charset).length);
		System.out.println("😳".getBytes(charset).length);
		System.out.println("\0");
		System.out.println("1".getBytes()[0]);
		System.out.println("A".getBytes()[0]);
		System.out.println("a".getBytes()[0]);
	}

	@Test
	public void testIOS88591() {
		Charset charset = StandardCharsets.ISO_8859_1;
		System.out.println("1".getBytes(charset).length);
		System.out.println("a".getBytes(charset).length);
		System.out.println("1234567890".getBytes(charset).length);
		System.out.println("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(charset).length);
		System.out.println("手".getBytes(charset).length);
		System.out.println("我的".getBytes(charset).length);
		System.out.println("苹果MAC".getBytes(charset).length);
		System.out.println("😳".getBytes(charset).length);
		System.out.println("\0");
		System.out.println("1".getBytes()[0]);
		System.out.println("A".getBytes()[0]);
		System.out.println("a".getBytes()[0]);
	}
	
	@Test
	public void testUTF16() {
		Charset charset = StandardCharsets.UTF_16;
		System.out.println("1".getBytes(charset).length);
		System.out.println("a".getBytes(charset).length);
		System.out.println("1234567890".getBytes(charset).length);
		System.out.println("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(charset).length);
		System.out.println("手".getBytes(charset).length);
		System.out.println("我的".getBytes(charset).length);
		System.out.println("苹果MAC".getBytes(charset).length);
		System.out.println("😳".getBytes(charset).length);
		System.out.println("\0");
		System.out.println("1".getBytes()[0]);
		System.out.println("A".getBytes()[0]);
		System.out.println("a".getBytes()[0]);
	}
}
