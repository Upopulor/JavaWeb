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
		//���ڴ��д���һ��ͼ�����
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//����һ������
		Graphics g = image.getGraphics();
		//��ͼƬ��ӱ���ɫ
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, width-2, height-2);
		//�����ı���ʽ
		g.setColor(Color.BLUE);
		g.setFont(new Font("����", Font.BOLD|Font.ITALIC, 15));
		//��ͼƬ����ı�
		Random random = new Random();
		int position = 20;
		for(int i = 0; i < 4 ; i++) {
			g.drawString(random.nextInt(10)+"", position, 20);
			position+=20;
		}
		g.drawString(random.nextInt(10)+"", 20, 20);
		//���9��������
		for (int i = 0; i < 9; i++) {
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		//��ͼ����������ķ�ʽ������ͻ���
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
