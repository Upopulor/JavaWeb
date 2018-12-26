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
		//获取表单数据
		//1-设置取回参数的编码，告诉服务器使用什么编码，参照浏览器的编码。与其一致
		request.setCharacterEncoding("UTF-8");
		//1根据表单中name属性的名，获取value属性的值方法
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
		
		//2获取所有表单的name名字getParameterNames() 得到表单提交的所有name的方法 
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name =(String) names.nextElement();//得到每一个name
			String[] values = request.getParameterValues(name);//habby时是多个，所以用数组
			for(int i = 0 ;(i< values.length)&&(values!=null);i++) {
				System.out.println(name +"\t"+values[i]);
			}
		}
		//3  getParameterMap 到表单提交的所有值的方法
		try {
			User u = new User();
			System.out.println("封装数据前："+u);
			Map<String, String[]> map = request.getParameterMap();//第一个String是name比如userName，第二个就是对应的值，又如habby有多个，所以是个数组
			for (Map.Entry<String, String[]> m : map.entrySet()) {
				String name = m.getKey();
				String[] value = m.getValue();
				//创建一个属性描述器
				PropertyDescriptor pd = new PropertyDescriptor(name, User.class);//通过当前取得的userName从User类中取得相应的get和set方法
				//得到setter属性
				Method setter = pd.getWriteMethod();
				if(value.length==1) {
					setter.invoke(u, value[0]);//给一个值得变量赋值
				}else {
					setter.invoke(u, (Object)value); //多个值为了让不拆开，强转为Object
				}
			}	
			System.out.println("封装数据后："+u);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//4 使用框架，相当于3的简化封装版
		try {
			User u = new User();
			System.out.println("封装数据前："+u);
			BeanUtils.populate(u, request.getParameterMap());
			System.out.println("封装数据后："+u);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		//5 getInputStream  以字节流的方式得到所有表单数据 但是有乱码问题，未解决
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
