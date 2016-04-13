package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BooksDao;
import dao.impl.BooksDaoImp;
import entity.Books;

/**
 * @author TH 负责对图书信息进行处理的服务器
 */
public class BooksServlet extends HttpServlet {

	BooksDao bd = new BooksDaoImp();
	int pagesize = 2; // 每个页面容纳几个记录

	/**
	 * Constructor of the object.
	 */
	public BooksServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("list".equals(action)) {

			BookList(request, response);
		} else if ("delete".equals(action)) {

			Delete(request, response);
		} else if ("select".equals(action)) {

			Select(request, response);
		} else if ("update".equals(action)) {

			Update(request, response);
		} else if ("add".equals(action)) {

			Add(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             分页查询图书信息
	 */
	public void BookList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int count = 0;
		int page = 1;
		int max_page = 0;
		List<Books> list = null;
		PrintWriter out = response.getWriter();
		count = bd.Count();
		if (count > 0) {

			max_page = (count % pagesize == 0) ? count / pagesize
					: (count / pagesize) + 1;

			if (request.getParameter("page") != null) {

				page = Integer.valueOf(request.getParameter("page"));
			}
			list = new ArrayList<Books>();
			list = bd.QueryAll(pagesize, page);
			request.setAttribute("booklist", list);
			request.setAttribute("page", page);
			request.setAttribute("maxpage", max_page);
			request.getRequestDispatcher("books/booklist.jsp").forward(request,
					response);
		} else {
			// 没有记录
			out.print("<script>alert('抱歉，数据库中没有可查询的数据！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			return;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             删除图书信息
	 */
	public void Delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		boolean isdelete = false;
		PrintWriter out = response.getWriter();

		isdelete = bd.Delete(id);
		if (isdelete) {

			// 删除图片
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			File f = new File(path + "/images/bookimage/" + id + ".jpg");
			if (f.exists()) {

				System.out.println(f.getName());
				f.delete();
			}
			// 删除成功
			out.print("<script>alert('删除成功！')</script>");
			out.println("<script>window.location.href='BooksServlet?action=list'</script>");
			out.close();
		} else {

			out.print("<script>alert('抱歉，删除失败！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			out.close();
			return;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             根据id来查询数据
	 */
	public void Select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		Books b = null;
		b = bd.SelectId(id);
		if (b != null) {

			// 查询成功
			request.setAttribute("b", b);
			request.getRequestDispatcher("books/updatebook.jsp").forward(
					request, response);
		} else {

			// 查询失败
			out.print("<script>alert('抱歉，数据库里面没有该条数据！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			return;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             对图书信息进行修改
	 */
	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isupdate = false;
		PrintWriter out = response.getWriter();
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String auther = request.getParameter("auther");
		String introduce = request.getParameter("introduce");
		double price = Double.parseDouble(request.getParameter("price"));

		Books b = new Books(id, name, introduce, auther, price);
		isupdate = bd.Update(b);
		if (isupdate) {

			// 修改成功
			out.print("<script>alert('图书信息修改成功！')</script>");
			out.println("<script>window.location.href='BooksServlet?action=list'</script>");
			return;
		} else {

			// 修改失败
			out.print("<script>alert('抱歉，修改失败！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			return;
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             添加图书信息
	 */
	public void Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isadd = false;
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String auther = request.getParameter("auther");
		String introduce = request.getParameter("introduce");
		double price = Double.parseDouble(request.getParameter("price"));
		Books b = new Books();
		b.setName(name);
		b.setAuther(auther);
		b.setIntroduce(introduce);
		b.setPrice(price);
		isadd = bd.Insert(b);
		if (isadd) {

			// 添加成功
			out.print("<script>alert('图书信息修改成功！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			return;
		} else {

			// 添加失败
			out.print("<script>alert('图书信息添加失败！')</script>");
			out.println("<script>window.history.go(-1)</script>");
			return;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
