package cn.response;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.prism.Image;

@WebServlet("/Servletyanzhenma")
public class Servletyanzhenma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 110;
		int height = 25;
		//在内存中创建一个图像对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//创建一个画笔
		Graphics g = image.getGraphics();
		//给图片添加背景色
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, width-2, height-2);
		//设置文本样式
		g.setColor(Color.BLUE);
		g.setFont(new Font("黑体", Font.BOLD|Font.ITALIC, 15));
		//给图片添加文本
		Random random = new Random();
		int position = 20;
		for(int i = 0; i < 4 ; i++) {
			g.drawString(random.nextInt(10)+"", position, 20);
			position+=20;
		}
		g.drawString(random.nextInt(10)+"", 20, 20);
		//添加9条干扰线
		for (int i = 0; i < 9; i++) {
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		//将图像对象以流的方式输出到客户端
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
