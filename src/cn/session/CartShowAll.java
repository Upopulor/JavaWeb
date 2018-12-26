package cn.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Book;
import cn.historyList.DBUtil;

/**
 * Servlet implementation class CartShowAll
 */
@WebServlet("/CartShowAll")
public class CartShowAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("本站有以下书：<br/>");
		Map<String, Book> books = DBUtil.getBooks();
		for (Map.Entry<String, Book> book : books.entrySet()) {
			//out.print("<a href='"+request.getContextPath()+"/addCart?id="+book.getKey()+"'target='_blank'>"+book.getValue().getName()+"</a><br/>");
			out.print("<a href='"+request.getContextPath()+"/addCart?id="+book.getKey()+"'>"+book.getValue().getName()+"</a><br/>");
		}
		out.print("<a href='"+request.getContextPath()+"/showCart'>查看购物车</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
