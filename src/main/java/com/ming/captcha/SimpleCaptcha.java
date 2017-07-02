package com.ming.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class SimpleCaptcha {

	// 去掉01iloO
	public static char[] table = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ".toCharArray();
	public static int tableLength = table.length;

	private static int getRandomIntColor(int fc, int bc) {
		Random random = ThreadLocalRandom.current();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return r << 16 | g << 8 | b;
	}

	private static Color getRandColor(int fc, int bc) {
		Random random = ThreadLocalRandom.current();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static Color getSimpleBackgroudColor() {
		Random random = ThreadLocalRandom.current();
		int r = 229 + random.nextInt(15);
		int g = 229 + random.nextInt(15);
		int b = 229 + random.nextInt(15);
		return new Color(r, g, b);
	}

	public String generate(int length, int width, int height, OutputStream os) throws IOException {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		char[] code = new char[length];
		for (int i = 0; i < length; i++) {
			code[i] = table[random.nextInt(tableLength)];
		}
		int fontSize = height - 4;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(getSimpleBackgroudColor());
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(getSimpleBackgroudColor());
		graphics.fillRect(0, 2, width, height - 4);
		// 绘制干扰线
		graphics.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			graphics.drawLine(x, y, x + xl + width / 2, y + yl + height / 2);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			image.setRGB(x, y, getRandomIntColor(0, 255));
		}

		graphics.setFont(new Font("Algerian", Font.PLAIN, fontSize));
		graphics.setColor(getRandColor(100, 160));
		for (int i = 0; i < length; i++) {
			if (i == length - 1) {
				graphics.drawChars(code, i, 1, ((width - 10) / length) * i + 2, height / 2 + fontSize / 2 - 5);
				break;
			}
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 8 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (width / length) * i + fontSize / 2, height / 2);
			graphics.setTransform(affine);
			if (i == 0) {
				graphics.drawChars(code, i, 1, 2, height / 2 + fontSize / 2 - 10);
			} else {
				graphics.drawChars(code, i, 1, ((width - 10) / length) * i + 5, height / 2 + fontSize / 2 - 10);
			}
		}
		ImageIO.write(image, "jpg", os);
		image.flush();
		graphics.dispose();
		return new String(code);
	}

	public static void main(String[] args) throws IOException {
		String generate = new SimpleCaptcha().generate(4, 70, 40, new FileOutputStream(new File("/Users/liuming/git/m-sports-web/a.jpg")));
		System.out.println(generate);
	}
}
