package cn.response;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDownload
 */
@WebServlet("/ServletDownload")
public class ServletDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͨ��·���õ�һ��������
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/��ƽ��.jpg");
		FileInputStream fs = new FileInputStream(path);
		//����һ���ֽ������
		ServletOutputStream sos = response.getOutputStream();
		//�õ�Ҫ���ص��ļ���
		String substring = path.substring(path.lastIndexOf("\\")+1);
		//�����ļ�������
		substring = URLEncoder.encode(substring,"UTF-8");//������ȫ���ļ���Ϊ��ȫ��utf-8��ʽ
		//���߿ͻ���Ҫ�����ļ�
		response.setHeader("content-disposition", "attachment;filename="+substring);
		response.setHeader("content-type", "image/jpeg");
		//ִ���������
		int len = 1;
		byte[] b = new byte[10240];
		while((len = fs.read(b))!=-1) {
			sos.write(b,0,len);
		}
		sos.close();
		fs.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
