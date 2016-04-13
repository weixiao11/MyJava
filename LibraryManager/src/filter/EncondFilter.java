package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author TH
 * 代码格式过滤器
 */
public class EncondFilter implements Filter {

	private String encode = null; // 代码格式
	private boolean ignore = false; // 过滤器开关

	public void destroy() {
		// TODO Auto-generated method stub
		encode = null;
		ignore = false;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 满足条件的情况下，过滤器启动，避免重复
		if (!ignore) {

			request.setCharacterEncoding(encode); // 输入代码格式化
			response.setContentType("text/html; charset=" + encode); // 输出代码格式化
		}
		chain.doFilter(request, response); // 过滤器链
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		// 获取过滤器中预定义字符
		String encode = filterConfig.getInitParameter("encode");
		String ignore = filterConfig.getInitParameter("ignore");

		if (this.encode == null) {

			this.encode = encode;
		}
		if ("true".equals(ignore)) {

			this.ignore = false;
		}
	}
}
