package get.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import get.GetData;
import gethtml.imp.GetHtmlByCrawler;

/**
 * @author Administrator
 * 通过爬虫，来实现爬取搜索出的电影记录
 */
public class GetMovieByDYTT implements GetData{

	GetHtmlByCrawler crawler = new GetHtmlByCrawler();
	public String getData(String... agrs) {
		// TODO Auto-generated method stub
		return crawler(agrs[0]);
	}
	
	public String crawler(String name){
		
		String url = "http://s.kujian.com/plus/search.php";
		Map<String, String> ps = new HashMap<String, String>();
		ps.put("kwtype", "0");
		ps.put("searchtype", "title");
		ps.put("keyword", name);
		String data = crawler.getHtml(url, ps);
		Document html = null;
		try {
			
			html = Jsoup.parse(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JsonObject jsObject = new JsonObject();
		if(html != null){
			
			Elements elements = html.select("div.co_content8").select("table");
			JsonArray jsArray = new JsonArray();
			for (Element element : elements) {
				
				JsonObject object = new JsonObject();
				//获得图片地址 
				String imgsrc = element.select("img").first()==null?"":element.select("img").first().attr("src");
				//获得电影的地址
				String movie_url = element.select("a").first().attr("href");
				//获得电影名称
				String movie_name = element.select("a").text();	
				//获得电影描述
				String detail = element.select("td").text();
				detail = detail.substring(movie_name.length(), detail.length());
				
				object.addProperty("img",imgsrc);
				object.addProperty("url", movie_url);
				object.addProperty("name", movie_name);
				object.addProperty("detail", detail);
				jsArray.add(object);
			}
			jsObject.addProperty("error","0");
			jsObject.add("msg",jsArray);
		}
		return jsObject.toString();
	}
}
