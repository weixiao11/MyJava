package get.imp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import get.GetData;
import gethtml.imp.GetHtmlByCrawler;

/**
 * @author Administrator
 * 通过api来获取历史上的今天
 */
public class GetHistory implements GetData{

	GetHtmlByCrawler crawler = new GetHtmlByCrawler();
	public String getData(String... agrs) {
		// TODO Auto-generated method stub
		return get();
	}

	public String get(){
		
	    Map<String, String> ps1 = new HashMap<String, String>();
		ps1.put("v","1.0");
		ps1.put("month",(Calendar.getInstance().get(Calendar.MONTH)+1)+"");
		ps1.put("day",(Calendar.getInstance().get(Calendar.DATE)+""));
		ps1.put("key","b2991dabf64407557bf97d3d17b79d9e");
		//System.out.println((Calendar.getInstance().get(Calendar.MONTH)+1)+":"+Calendar.getInstance().get(Calendar.DATE));
		return crawler.get("http://api.juheapi.com/japi/toh", ps1);
	}
}
