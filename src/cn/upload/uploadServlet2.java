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
		//Ҫִ���ļ��ϴ��Ĳ���
		//�жϱ��Ƿ�֧���ϴ�
		boolean ismultipartContent = ServletFileUpload.isMultipartContent(request);
		if(!ismultipartContent) {
			throw new RuntimeException("��ı���֧���ϴ��ļ�");
		}
		//����һ��DiskFileItemFactory������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//factory.setRepository(new File("f:\\"));//������ʱ�ļ���Ŀ¼��һ���ϴ����ļ��ᴫ���������Ҫɾ����ʱ�ļ�
		//����һ��ServletFileUpload���Ķ���
		ServletFileUpload sf = new ServletFileUpload(factory);
		//�����������,�ϴ�����
		sf.setHeaderEncoding("UTF-8");
		//����request���󣬲��õ�һ������ļ���
			try {
				//�����ϴ��ļ��Ĵ�С
				sf.setFileSizeMax(1024*1024*3);//3mb��С
				//sf.setSizeMax(1024*1024*6);//����ϴ����ǣ����ϴ���С������6mb
				List<FileItem> fileItmes = sf.parseRequest(request);
				//������������
				for (FileItem fileItem : fileItmes) {
					if(fileItem.isFormField()) {//��ͨ����
						processFormField(fileItem);
					}else {//�ϴ�����
						processUploadField(fileItem);
					}
				}
			} catch(FileUploadBase.FileSizeLimitExceededException e1){
				System.out.println("�ļ�����3mb,�����ϴ�");
			}
			catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	private void processUploadField(FileItem fileItem) {
		try {
			//�õ��ļ���
			InputStream is = fileItem.getInputStream();
			//����һ���ļ����̵�Ŀ¼,���õ�WEB-INF���棬��ֹ�û��ϴ���Ҳ����ִ�С�
			String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File fileDir = new File(directoryRealPath);
			if(!fileDir.exists()) {
				fileDir.mkdirs();//����һ��ָ��Ŀ¼
			}
			//�õ��ϴ�������,�Ͱ汾��������õ����ֿ��ܳ�����
			String filename = fileItem.getName(); //�ļ����е�����F:\asc\a.jpg �� a.jpg
			if(filename!=null) {
				filename = FilenameUtils.getName(filename);
			}
			//����ļ���������
			filename = UUID.randomUUID()+"_"+filename;
			//�ڶ��ְ���Ŀ¼��ɢ
			String childDirectory = makeChildDirectory(fileDir,filename);
			//��᲻�����ģ�ʹ��Apache�ṩ�ķ���.�ϴ��ļ����Զ�ɾ����ʱ�ļ�
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
	//�ϴ�����
	private void processUploadField2(FileItem fileItem) {
		//�õ��ϴ�������,�Ͱ汾��������õ����ֿ��ܳ�����
		String filename = fileItem.getName(); //�ļ����е�����F:\asc\a.jpg �� a.jpg
		//��ֹ�ļ�Ϊ��
		if("".equals(filename)) {
			throw new RuntimeException("�ϴ�Ϊ��");
			
		}
		try {
			//�õ��ļ���
			InputStream is = fileItem.getInputStream();
			//����һ���ļ����̵�Ŀ¼,���õ�WEB-INF���棬��ֹ�û��ϴ���Ҳ����ִ�С�
			String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File fileDir = new File(directoryRealPath);
			if(!fileDir.exists()) {
				fileDir.mkdirs();//����һ��ָ��Ŀ¼
			}
			//�����ļ��� ����ǵ�һ�֣�����a.jpg,���ڶ���lastIndexOf����-1 ����1����0 ����ԭ����a.jpg
			//���ﲻʹ�ã�����ʹ��Apache�ṩ�Ĺ���
			//filename = filename.substring(filename.lastIndexOf(File.separator)+1);
			if(filename!=null) {
				filename = FilenameUtils.getName(filename);
			}
			//����ļ���������
			filename = UUID.randomUUID()+"_"+filename;
			//��һ�ְ������ڴ�ɢ
			//String childDirectory = makeChildDirectory(fileDir); // 2018-12-26
			//�ڶ��ְ���Ŀ¼��ɢ
			String childDirectory = makeChildDirectory(fileDir,filename);
			//����һ���ļ�
			//                                      ����Ŀ¼/����Ŀ¼(Ŀ¼�ָ�Ŀ¼)/�ļ���
			File file = new File(fileDir,childDirectory+File.separator+filename);
			//ͨ���ļ���������ϴ����ļ����浽����
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while((len=is.read(b))!=-1) {
				fos.write(b,0,len);
			}
			fos.close();
			is.close();
			//�����Ҫɾ����ʱ�ļ�
			fileItem.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����Ŀ¼��ɢ,�ô��������ļ���������ͬ���ļ�Ŀ¼
	private String makeChildDirectory(File fileDir, String filename) {
		int hashCode = filename.hashCode();//�����ַ���ת����32λhashcode��
		System.out.println(hashCode);
		String code = Integer.toHexString(hashCode);//��hashcodeת��Ϊ16���Ƶ��ַ� absdsd34re
		System.out.println(code);
		String childDirectory = code.charAt(0)+File.separator+code.charAt(1); // a/b
		File file4 = new File(fileDir,childDirectory);
		//�����ƶ�Ŀ¼
		if(!file4.exists()) {
			file4.mkdirs();
		}
		return childDirectory;
	}
	//�������ڴ�ɢ
	private String makeChildDirectory(File fileDir) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		//ֻ�ܴ���Ŀ¼
		File file3 = new File(fileDir,date);
		if(!file3.exists()) {
			file3.mkdirs();
		}
		return date;
	}
	//��ͨ����
	private void processFormField(FileItem fileItem) {
		try {
			String fieldName = fileItem.getFieldName();//�ֶ���
			String fieldvalue = fileItem.getString("UTF-8");//�ֶ�ֵ
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
