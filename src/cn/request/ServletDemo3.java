package cn.request;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.entity.User;

/**
 * Servlet implementation class ServletDemo3
 */
@WebServlet("/ServletDemo3")
public class ServletDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������
		//1-����ȡ�ز����ı��룬���߷�����ʹ��ʲô���룬����������ı��롣����һ��
		request.setCharacterEncoding("UTF-8");
		//1���ݱ���name���Ե�������ȡvalue���Ե�ֵ����
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		String[] hobbys = request.getParameterValues("hobby");
		String city = request.getParameter("city");
		System.out.println(userName);
		System.out.println(pwd);
		System.out.println(sex);
		for(int i = 0 ; (hobbys!=null)&&(i<hobbys.length);i++) {
			System.out.print(hobbys[i]+"\t");
		}
		System.out.println();
		System.out.println(city);
		
		//2��ȡ���б���name����getParameterNames() �õ����ύ������name�ķ��� 
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name =(String) names.nextElement();//�õ�ÿһ��name
			String[] values = request.getParameterValues(name);//habbyʱ�Ƕ��������������
			for(int i = 0 ;(i< values.length)&&(values!=null);i++) {
				System.out.println(name +"\t"+values[i]);
			}
		}
		//3  getParameterMap �����ύ������ֵ�ķ���
		try {
			User u = new User();
			System.out.println("��װ����ǰ��"+u);
			Map<String, String[]> map = request.getParameterMap();//��һ��String��name����userName���ڶ������Ƕ�Ӧ��ֵ������habby�ж���������Ǹ�����
			for (Map.Entry<String, String[]> m : map.entrySet()) {
				String name = m.getKey();
				String[] value = m.getValue();
				//����һ������������
				PropertyDescriptor pd = new PropertyDescriptor(name, User.class);//ͨ����ǰȡ�õ�userName��User����ȡ����Ӧ��get��set����
				//�õ�setter����
				Method setter = pd.getWriteMethod();
				if(value.length==1) {
					setter.invoke(u, value[0]);//��һ��ֵ�ñ�����ֵ
				}else {
					setter.invoke(u, (Object)value); //���ֵΪ���ò��𿪣�ǿתΪObject
				}
			}	
			System.out.println("��װ���ݺ�"+u);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//4 ʹ�ÿ�ܣ��൱��3�ļ򻯷�װ��
		try {
			User u = new User();
			System.out.println("��װ����ǰ��"+u);
			BeanUtils.populate(u, request.getParameterMap());
			System.out.println("��װ���ݺ�"+u);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		//5 getInputStream  ���ֽ����ķ�ʽ�õ����б����� �������������⣬δ���
		ServletInputStream sis = request.getInputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while((len=sis.read(b))!=-1) {
			System.out.println(new String(new String(b,0,len).getBytes("iso-8859-1"),"UTF-8"));
		}
		sis.close();
 }
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
