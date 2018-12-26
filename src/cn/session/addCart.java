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
		//����id�õ���
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		//�õ�Session����
		HttpSession session = request.getSession();
		//��Session��ȡ��list�����ﳵ��
		List<Book> list = (List<Book>) session.getAttribute("cart");
		//��һ���ǿյ�
		if(list == null) {
			list = new ArrayList<Book>();
		}
		list.add(book);
		session.setAttribute("cart", list);//��list�Żص�Session��
		out.print("����ɹ�");
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/CartShowAll");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
