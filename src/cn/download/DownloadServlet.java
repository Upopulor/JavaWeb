package cn.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����һ��Ҫ���ص��ļ�
		String filename = "���۰�.csv";
		
		//�����ļ����ı���
		if(request.getHeader("user-agent").toLowerCase().contains("msie")){
			filename = URLEncoder.encode(filename, "UTF-8");//������ȫ���ļ�����ΪUTF-8��ʽ
		}else{
			filename = new String(filename.getBytes("UTF-8"),"iso-8859-1");//��������
		}
		//��֪�����Ҫ�����ļ�
		response.setHeader("content-disposition", "attachment;filename="+filename);
		//response.setHeader("content-type", "image/jpeg");
		response.setContentType(this.getServletContext().getMimeType(filename));//�����ļ����Զ�����ļ�����
		
		response.setCharacterEncoding("UTF-8");//��֪������ʹ��ʲô����
		//����һ���ļ������
		PrintWriter out = response.getWriter();
		out.write("���ӻ�,20\n");
		out.write("ϴ�»�,10\n");
		out.write("����,8\n");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
