package cn.historyList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Book;

/**
 * Servlet implementation class showAll
 */
@WebServlet("/showAll")
public class showAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("本站有以下的书：<br/>");
		Map<String, Book> books = DBUtil.getBooks();
		for (Map.Entry<String, Book> b : books.entrySet()) {
			out.write("<a href='"+request.getContextPath()+"/ShowBookDetail?id="+b.getKey()+"' target='_blank'>"+b.getValue().getName()+"</a><br/>");
			
		}
		
		
		out.write("<hr/>您最近浏览的书：<br/>");
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies!=null&&i < cookies.length; i++) {
			String value = cookies[i].getValue();
			String[] ids = value.split("-");
			for (int j = 0; j < ids.length; j++) {
				Book book = DBUtil.findBookById(ids[j]);
				out.print(book.getName()+"<br/>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
