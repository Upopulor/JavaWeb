package cn.historyList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Book;

/**
 * Servlet implementation class ShowBookDetail
 */
@WebServlet("/ShowBookDetail")
public class ShowBookDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//显示图书的详细信息
		//获得id
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		//out.write(book.toString());
		out.print(book);
		//把当前浏览书的id写会客户端
		String history = organizeID(id,request);
		Cookie ck = new Cookie("history", history);
		ck.setPath("/");
		ck.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(ck);
	}
	/**
	 * Client                  showALL            showBookDetail
	 * 没有Cookie                   1                   history=1
	 * 有Cookie，但没有history        1                   history=1
	 * history = 1                2                  history=2-1
	 * history = 1-2              2                   history=2-1
	 * history=1-2-3              2                   history=2-1-3
	 * history=1-2-3            4                     history=4-1-2
	 */
	private String organizeID(String id, HttpServletRequest request) {
		//得到客户端的cookie
		Cookie[] cookies = request.getCookies();
		if(cookies==null) {
			return id;
		}
		//查找有没有name叫history的cookie
		Cookie historyBook = null;
		for (int i = 0; i < cookies.length; i++) {
			if("history".equals(cookies[i].getName())){
				historyBook = cookies[i];
			}
		}
		if(historyBook==null) {
			return id;
		}
		String value = historyBook.getValue();//2-1-3
		String[] values = value.split("-");
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(values));
		if(list.size()<3) {
			if(list.contains(id)) { //1 2
				list.remove(id);//如果包含当前id的值，删除此id
			}
		}else {
			if(list.contains(id)) { //1 2 3
				list.remove(id);
			}else {
				list.removeLast();//如果超过3个且不包含当前id，删除最后一个
			}	
		}
		list.addFirst(id);//把最新的书的id添加到最前面
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < list.size();i++) {
			if(i>0) {
				sb.append("-");
			}
			sb.append(list.get(i));
		}
		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
