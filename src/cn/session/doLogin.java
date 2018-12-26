package cn.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class doLogin
 */
@WebServlet("/doLogin")
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//��ȡ������
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		String scode = (String) request.getSession().getAttribute("scode");
		//����ҵ��
		if("tom".equals(userName)&&"123".equals(pwd)) {
			if(!code.equalsIgnoreCase(scode)) {
				String msg = "��֤ʧ��";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			out.print("��¼�ɹ�");
		}
		//�ַ�ת��
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
