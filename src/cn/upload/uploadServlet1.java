package cn.upload;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class uploadServlet1
 */
@WebServlet("/uploadServlet1")
public class uploadServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String name = request.getParameter("name");
//		String photo = request.getParameter("photo");
//		System.out.println(name); //tom
//		System.out.println(photo); //a.txt
		//表单中数据提交方式改为了multipart/form-data,所以request.getParameter("name")失效
		InputStream is = request.getInputStream();
		int len = 0 ;
		byte[] b = new byte[1024];
		while((len=is.read(b))!=-1) {
			System.out.println(new String(b,0,len));
		}
		is.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
