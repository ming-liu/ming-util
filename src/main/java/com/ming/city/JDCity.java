package com.ming.city;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDCity {

	public static final String file = "/Users/liuming/git/ming-util/src/main/resources/city/jd/cities";
	public static final String outfile = "/Users/liuming/git/ming-util/src/main/resources/city/jd/cities.sql";
	private static boolean exists = false;

	public static void main(String[] args) throws IOException {
		List<JDCityBean> root = new ArrayList<JDCityBean>();
		Map<String, JDCityBean> rootMap = new HashMap<String, JDCityBean>();
		FileReader fileReader = new FileReader(new File(file));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String readLine = null;

		while ((readLine = bufferedReader.readLine()) != null) {
			String[] split = readLine.split("\\.");
			String content = split[1];
			String[] split2 = content.split("->");
			String lv1 = split2[0];
			String lv2 = split2[1];
			String lv3Content = split2[2].substring(1, split2[2].length() - 1);
			String[] split3 = lv3Content.split(",");
			List<String> lv3 = new ArrayList<String>();
			for (int i = 0; i < split3.length - 1; i++) {
				lv3.add(split3[i]);
			}
			lv3.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});

			exists = false;
			root.forEach((e) -> {
				if (e.getName().equals(lv1)) {
					exists = true;
				}
			});
			if (!exists) {
				int j = root.size() + 1;
				JDCityBean e = new JDCityBean(j < 10 ? "0" + j : "" + j, lv1);
				root.add(e);
				rootMap.put(lv1, e);
			}

			JDCityBean subCity = rootMap.get(lv1).addSubCity(lv2);
			lv3.forEach((e) -> {
				subCity.addSubCity(e);
			});
		}
		bufferedReader.close();

		StringBuilder lv1Builder = new StringBuilder();
		StringBuilder lv2Builder = new StringBuilder();
		StringBuilder lv3Builder = new StringBuilder();
		root.forEach((e) -> {
			StringBuilder sb = new StringBuilder("INSERT INTO `City` (`code`, `pcode`, `name`, `level`) VALUES('");
			sb.append(e.getCode()).append("','','");
			sb.append(e.getName()).append("',1);\r\n");
			lv1Builder.append(sb.toString());
			e.getSubCities().forEach((s) -> {
				StringBuilder sb2 = new StringBuilder("INSERT INTO `City` (`code`, `pcode`, `name`, `level`) VALUES('");
				sb2.append(s.getCode()).append("','").append(s.getPcode()).append("','").append(s.getName());
				sb2.append("',2);\r\n");
				lv2Builder.append(sb2.toString());

				s.getSubCities().forEach((u) -> {
					StringBuilder sb3 = new StringBuilder("INSERT INTO `City` (`code`, `pcode`, `name`, `level`) VALUES('");
					sb3.append(u.getCode()).append("','").append(u.getPcode()).append("','").append(u.getName());
					sb3.append("',3);\r\n");
					lv3Builder.append(sb3.toString());
				});
			});
		});

		System.out.println(lv2Builder.toString());
		System.out.println(lv3Builder.toString());

		FileWriter fw = new FileWriter(new File(outfile));
		fw.write(lv1Builder.toString());
		fw.write(lv2Builder.toString());
		fw.write(lv3Builder.toString());
		fw.flush();
		fw.close();
	}

}
