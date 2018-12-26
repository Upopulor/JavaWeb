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
		//通过路径得到一个输入流
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/地平线.jpg");
		FileInputStream fs = new FileInputStream(path);
		//创建一个字节输出流
		ServletOutputStream sos = response.getOutputStream();
		//得到要下载的文件名
		String substring = path.substring(path.lastIndexOf("\\")+1);
		//设置文件名编码
		substring = URLEncoder.encode(substring,"UTF-8");//将不安全的文件改为安全的utf-8格式
		//告诉客户端要下载文件
		response.setHeader("content-disposition", "attachment;filename="+substring);
		response.setHeader("content-type", "image/jpeg");
		//执行输出操作
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
