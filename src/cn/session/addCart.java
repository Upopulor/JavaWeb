package cn.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.entity.Book;
import cn.historyList.DBUtil;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//根据id得到书
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		//得到Session对象
		HttpSession session = request.getSession();
		//从Session中取出list（购物车）
		List<Book> list = (List<Book>) session.getAttribute("cart");
		//第一次是空的
		if(list == null) {
			list = new ArrayList<Book>();
		}
		list.add(book);
		session.setAttribute("cart", list);//把list放回到Session中
		out.print("购买成功");
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/CartShowAll");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
