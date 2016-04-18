package gethtml;

import java.util.Map;

public interface GetContent {

	public String getHtml(String url, Map<String, String> ps);
	public String getHtml(String url);
}
