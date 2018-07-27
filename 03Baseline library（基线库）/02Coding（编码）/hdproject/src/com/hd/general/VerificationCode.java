package com.hd.general;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verificationCode")
public class VerificationCode extends HttpServlet {
	
	// 验证码长度
    private static final int CODE_LEN = 4;
    //图片高度
    private static final int IMG_WIDTH = 80;
    //图片宽度
    private static final int IMG_HEIGHT = 40;
    
	public VerificationCode() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("image/jpeg");
		// 用于绘制图片，设置图片的长宽和图片类型（RGB)
        BufferedImage bi = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取绘图工具
        Graphics graphics = bi.getGraphics();
        graphics.setColor(new Color(238, 247, 254)); // 使用RGB设置背景颜色
        graphics.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // 填充矩形区域

        // 验证码中所使用到的字符
        char[] codeChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        String captcha = ""; // 存放生成的验证码
        Random random = new Random();
        for(int i = 0; i < CODE_LEN; i++) { // 循环将每个验证码字符绘制到图片上
            int index = random.nextInt(codeChar.length);
            // 随机生成验证码颜色
            Color color = new Color(random.nextInt(150), random.nextInt(200), random.nextInt(255));
            graphics.setColor(color);
            // 将一个字符绘制到图片上，并制定位置（设置x,y坐标）
            graphics.drawString(codeChar[index] + "", 10 + (i * 20), 20);
            captcha += codeChar[index];
        }
        // 将生成的验证码code放入sessoin中
        req.getSession().setAttribute("verificationCode", captcha);
        
        // 通过ImageIO将图片输出
        ImageIO.write(bi, "JPG", resp.getOutputStream());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
