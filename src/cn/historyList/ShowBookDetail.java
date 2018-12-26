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
		//��ʾͼ�����ϸ��Ϣ
		//���id
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		//out.write(book.toString());
		out.print(book);
		//�ѵ�ǰ������idд��ͻ���
		String history = organizeID(id,request);
		Cookie ck = new Cookie("history", history);
		ck.setPath("/");
		ck.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(ck);
	}
	/**
	 * Client                  showALL            showBookDetail
	 * û��Cookie                   1                   history=1
	 * ��Cookie����û��history        1                   history=1
	 * history = 1                2                  history=2-1
	 * history = 1-2              2                   history=2-1
	 * history=1-2-3              2                   history=2-1-3
	 * history=1-2-3            4                     history=4-1-2
	 */
	private String organizeID(String id, HttpServletRequest request) {
		//�õ��ͻ��˵�cookie
		Cookie[] cookies = request.getCookies();
		if(cookies==null) {
			return id;
		}
		//������û��name��history��cookie
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
				list.remove(id);//���������ǰid��ֵ��ɾ����id
			}
		}else {
			if(list.contains(id)) { //1 2 3
				list.remove(id);
			}else {
				list.removeLast();//�������3���Ҳ�������ǰid��ɾ�����һ��
			}	
		}
		list.addFirst(id);//�����µ����id��ӵ���ǰ��
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
