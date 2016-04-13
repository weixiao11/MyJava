package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDao;
import dao.impl.ManagerDaoImp;
import entity.Manager;


/**
 * @author TH
 *负责对管理员信息进行处理的服务器
 */
public class ManagerServlet extends HttpServlet {

	ManagerDao md = new ManagerDaoImp();
	/**
	 * Constructor of the object.
	 */
	public ManagerServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if("login".equals(action)){
			
			Login(request, response);
		}else if("update".equals(action)){
			
			Update(request, response);
		}else if("add".equals(action)){
			
			Add(request, response);
		}
	}

	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 管理员登陆的检测
	 */
	public void Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		int id = Integer.valueOf(request.getParameter("id"));
		String pwd = request.getParameter("pwd");
		PrintWriter out = response.getWriter();
		Manager m = null;
		m = md.Login(id, pwd);
		if(m!=null){
			
			HttpSession session = request.getSession();
			session.setAttribute("m", m);  //将其会话数据保存
			
			request.getRequestDispatcher("main/main.jsp").forward(request, response);
		}else{
			
			//输入错误的处理
			out.print("<script>alert('您的账号或密码错误，请核对后重新输入！')</script>");
			out.println("<script> window.history.go(-1)</script>");  //返回登录页面
			return;
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 修改管理员的密码
	 */
	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Manager m = (Manager) session.getAttribute("m");
		String pwd = request.getParameter("pwdn");
		boolean isupdate = false;
		isupdate = md.Update(m.getId(), pwd);
		
		if(isupdate){
			
			//修改成功
			out.print("<script>alert('密码修改成功，请重重新登录！')</script>");
			out.println("<script> window.parent.location.href='login.jsp'</script>");  //返回登录页面
			return;
		}else{
			
			//修改失败
			out.print("<script>alert('密码修改失败，请重试！')</script>");
			out.println("<script> window.history.go(-1)</script>");  //返回登录页面
			return;
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 添加管理员信息
	 */
	public void Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		int id = Integer.valueOf(request.getParameter("id"));
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		Manager m = new Manager(id,name,pwd,sex);
		boolean isadd = false;
		isadd = md.Insert(m);
		
		if(isadd){
			
			//添加成功
			out.print("<script>alert('成功添加一个管理员！')</script>");
			out.println("<script> window.history.go(-1)</script>");  //返回登录页面
			return;
		}else{
			
			//添加失败
			out.print("<script>alert('添加管理员失败，请重试！')</script>");
			out.println("<script> window.history.go(-1)</script>");  //返回登录页面
			return;
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
