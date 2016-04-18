package util;

import java.net.URLEncoder;
import java.util.Map;

public class ParseParameter {

	public static String parse(Map<String, String> ps) {

		return parse(ps, "gb2312");
	}

	public static String parse(Map<String, String> ps, String encoding) {

		if (ps == null || ps.size() == 0) {

			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> m : ps.entrySet()) {

			try {

				builder.append(m.getKey())
						.append("=").append((URLEncoder.encode(m.getValue() + "",encoding))).append("&");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(builder.substring(0, builder.length()-1));
		return builder.substring(0, builder.length()-1);
	}
}
