package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Manager;

/**
 * @author TH
 * 登录过滤器
 */
public class LoginFilter implements Filter{

	String []permitUrl = null;      //允许的url
	boolean ignore = false;        //是否开启过滤
	String gotoUrl = null;          //过滤后到达的页面
	
	public void destroy() {
		// TODO Auto-generated method stub
		permitUrl = null;
		ignore = false;
		gotoUrl = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		PrintWriter out = response.getWriter();
		
		if(!ignore){
			//1、如果过滤器没有开，就执行
			if(!isPermitUrl(req)){
				
				//2、如果当前的url不在permitUrl中
				if(sessionCheck(req)){
					
					//3、如果session里面没有数据
					out.print("<script>alert('没有登录，没有权限访问，请登录！')</script>");
					out.println("<script> window.parent.location.href='"+request.getContextPath()+gotoUrl+"'</script>");  //返回登录页面
					return;
				}
			}
		}
	    chain.doFilter(req, res);
	}
	
	/**
	 * @param req
	 * @return
	 * 查看session中是否已经有了登录数据
	 */
	public boolean sessionCheck(ServletRequest req){
		
		boolean issession = false;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		Manager m = (Manager) session.getAttribute("m");
		if(m==null)
			issession = true;
		return issession;
	}
	/**
	 * @param req
	 * @return
	 * 判断是否是被允许的url
	 */
	public boolean isPermitUrl(ServletRequest req){
		
		boolean isPermit = false;
		if(permitUrl!=null&&permitUrl.length>0){
			
			for (int i = 0; i < permitUrl.length; i++) {
				
				if(permitUrl[i].equals(currentUrl(req))){
					
					isPermit = true;
					break;
				}
			}
		}
		return isPermit;
	}
	
	/**
	 * @param req
	 * @return
	 * 获得当前的url
	 */
	public String currentUrl(ServletRequest req){
		
		HttpServletRequest request = (HttpServletRequest) req;
		String action = request.getParameter("action");  //动作action
		String path = request.getContextPath();
		String url = request.getRequestURI();
		if(action!=null){
			
			url = url.substring(path.length(), url.length())+"?action="+action;
		}else{
			
			url = url.substring(path.length(), url.length());
		}
		return url;
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		
		String permitUrl = filterconfig.getInitParameter("permitUrl");
		String ignore = filterconfig.getInitParameter("ignore");
		String gotoUrl = filterconfig.getInitParameter("gotoUrl");
		
		if("true".equals(ignore)){
			
			this.ignore = true;
		}
		if(permitUrl != null && permitUrl.length() >0){
			
			this.permitUrl = permitUrl.split(",");
		}
		this.gotoUrl = gotoUrl;
	}
}
