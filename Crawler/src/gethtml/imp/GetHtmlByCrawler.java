package gethtml.imp;

import gethtml.GetContent;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import util.ParseParameter;

public class GetHtmlByCrawler implements GetContent{

	public String getHtml(String url, Map<String, String> ps) {
		// TODO Auto-generated method stub
		return get(url, ps);
	}

	public String getHtml(String url) {
		// TODO Auto-generated method stub
		return get(url, null);
	}

	public String get(String url, Map<String, String> ps){
		
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet get = null;
		if(ps == null){
			
			get = new HttpGet(url);
		}else{
			
			get = new HttpGet(url+"?"+ParseParameter.parse(ps, "gb2312"));
		}
		try {
			
			response = client.execute(get);
			if(response.getStatusLine().getStatusCode() == 302 || response.getStatusLine().getStatusCode() == 301){
				
				String urls = response.getFirstHeader("Location").getValue();
				return get(urls, null);
			}else{
				
				HttpEntity entity = response.getEntity();
				String resource = EntityUtils.toString(entity, "gb2312");
				return resource;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				response.close();
				client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
