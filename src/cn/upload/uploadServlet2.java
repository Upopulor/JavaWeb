package cn.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;



/**
 * Servlet implementation class uploadServlet2
 */
@WebServlet("/uploadServlet2")
public class uploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//要执行文件上传的操作
		//判断表单是否支持上传
		boolean ismultipartContent = ServletFileUpload.isMultipartContent(request);
		if(!ismultipartContent) {
			throw new RuntimeException("你的表单不支持上传文件");
		}
		//创建一个DiskFileItemFactory工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//factory.setRepository(new File("f:\\"));//保存临时文件的目录，一般上传大文件会传俩，传完后要删除临时文件
		//创建一个ServletFileUpload核心对象
		ServletFileUpload sf = new ServletFileUpload(factory);
		//解决中文乱码,上传表单项
		sf.setHeaderEncoding("UTF-8");
		//解析request对象，并得到一个表单项的集合
			try {
				//限制上传文件的大小
				sf.setFileSizeMax(1024*1024*3);//3mb大小
				//sf.setSizeMax(1024*1024*6);//多个上传框是，总上传大小不超过6mb
				List<FileItem> fileItmes = sf.parseRequest(request);
				//遍历表单项数据
				for (FileItem fileItem : fileItmes) {
					if(fileItem.isFormField()) {//普通表单项
						processFormField(fileItem);
					}else {//上传表单项
						processUploadField(fileItem);
					}
				}
			} catch(FileUploadBase.FileSizeLimitExceededException e1){
				System.out.println("文件超过3mb,不能上传");
			}
			catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	private void processUploadField(FileItem fileItem) {
		try {
			//得到文件流
			InputStream is = fileItem.getInputStream();
			//创建一个文件存盘的目录,设置到WEB-INF下面，防止用户上传后也可以执行。
			String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File fileDir = new File(directoryRealPath);
			if(!fileDir.exists()) {
				fileDir.mkdirs();//创建一个指定目录
			}
			//得到上传的名字,低版本的浏览器得到名字可能出问题
			String filename = fileItem.getName(); //文件项中的名字F:\asc\a.jpg 或 a.jpg
			if(filename!=null) {
				filename = FilenameUtils.getName(filename);
			}
			//解决文件重名问题
			filename = UUID.randomUUID()+"_"+filename;
			//第二种按照目录打散
			String childDirectory = makeChildDirectory(fileDir,filename);
			//这会不用流的，使用Apache提供的方法.上传文件，自动删除临时文件
			fileItem.write(new File(fileDir,childDirectory+File.separator+filename));
			fileItem.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//上传表单项
	private void processUploadField2(FileItem fileItem) {
		//得到上传的名字,低版本的浏览器得到名字可能出问题
		String filename = fileItem.getName(); //文件项中的名字F:\asc\a.jpg 或 a.jpg
		//防止文件为空
		if("".equals(filename)) {
			throw new RuntimeException("上传为空");
			
		}
		try {
			//得到文件流
			InputStream is = fileItem.getInputStream();
			//创建一个文件存盘的目录,设置到WEB-INF下面，防止用户上传后也可以执行。
			String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File fileDir = new File(directoryRealPath);
			if(!fileDir.exists()) {
				fileDir.mkdirs();//创建一个指定目录
			}
			//处理文件名 如果是第一种，返回a.jpg,若第二种lastIndexOf返回-1 加上1等于0 还是原来的a.jpg
			//这里不使用，而是使用Apache提供的工具
			//filename = filename.substring(filename.lastIndexOf(File.separator)+1);
			if(filename!=null) {
				filename = FilenameUtils.getName(filename);
			}
			//解决文件重名问题
			filename = UUID.randomUUID()+"_"+filename;
			//第一种按照日期打散
			//String childDirectory = makeChildDirectory(fileDir); // 2018-12-26
			//第二种按照目录打散
			String childDirectory = makeChildDirectory(fileDir,filename);
			//创建一个文件
			//                                      绝对目录/日期目录(目录分割目录)/文件名
			File file = new File(fileDir,childDirectory+File.separator+filename);
			//通过文件输出流将上传的文件保存到磁盘
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while((len=is.read(b))!=-1) {
				fos.write(b,0,len);
			}
			fos.close();
			is.close();
			//传完后要删除临时文件
			fileItem.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//按照目录打散,好处，按照文件名产生不同的文件目录
	private String makeChildDirectory(File fileDir, String filename) {
		int hashCode = filename.hashCode();//返回字符串转换的32位hashcode码
		System.out.println(hashCode);
		String code = Integer.toHexString(hashCode);//把hashcode转换为16进制的字符 absdsd34re
		System.out.println(code);
		String childDirectory = code.charAt(0)+File.separator+code.charAt(1); // a/b
		File file4 = new File(fileDir,childDirectory);
		//创建制定目录
		if(!file4.exists()) {
			file4.mkdirs();
		}
		return childDirectory;
	}
	//按照日期打散
	private String makeChildDirectory(File fileDir) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		//只管创建目录
		File file3 = new File(fileDir,date);
		if(!file3.exists()) {
			file3.mkdirs();
		}
		return date;
	}
	//普通表单项
	private void processFormField(FileItem fileItem) {
		try {
			String fieldName = fileItem.getFieldName();//字段名
			String fieldvalue = fileItem.getString("UTF-8");//字段值
			//fieldvalue = new String(fieldvalue.getBytes("iso-8859-1"),"utf-8");
			System.out.println(fieldName+"="+fieldvalue);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
