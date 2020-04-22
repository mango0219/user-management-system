package cn.codewei.manageUsers.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //图片的宽和高
        int width = 100;
        int height = 50;

        //  创建一对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //  美化图片
        // - 填充背景色
        Graphics g = image.getGraphics();  //  获取画笔
        g.setColor(Color.pink);     //  设置画笔的颜色
        g.fillRect(0,0,width,height);  //  进行填充

        // - 画边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);

        // - 写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        HttpSession session = request.getSession();
        String checkcode = "";
        Random r = new Random();
        for (int i = 1;i <= 4;i++){
            int index = r.nextInt(str.length());
            char ch = str.charAt(index);
            checkcode = checkcode+ch;
            g.drawString(ch+"",width/5*i,height/2);
        }
        session.setAttribute("checkcode",checkcode);

        // - 画干扰线
        for (int i = 0;i<=7;i++){
            int r1 = r.nextInt(255);
            int g1 = r.nextInt(255);
            int b = r.nextInt(255);
            Color color = new Color(r1,g1,b);
            g.setColor(color);
            int w1 = r.nextInt(width);
            int h1 = r.nextInt(height);
            int w2 = r.nextInt(width);
            int h2 = r.nextInt(height);
            g.drawLine(w1,h1,w2,h2);
        }

        //  将图片输出到页面
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
