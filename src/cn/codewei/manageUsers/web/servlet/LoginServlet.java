package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.domain.Manager;
import cn.codewei.manageUsers.service.ManagerService;
import cn.codewei.manageUsers.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request字符集
        request.setCharacterEncoding("utf8");

        // 获取form提交的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifycode = request.getParameter("verifycode");

        // 判断用户名和密码是否为空
        if ("".equals(username)||"".equals(password)){
            // 用户名或密码为空
            // 设置提示信息
            request.setAttribute("login_msg","用户名或密码不能为空！");
            // 转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        // 用户名或密码不为空
        // 判断验证码是否为空
        // - 获取session对象
        HttpSession session = request.getSession();
        String checkcode = (String)session.getAttribute("checkcode");
        if (verifycode==null||"".equals(verifycode)){
            // 验证码为空
            // 设置提示信息
            request.setAttribute("login_msg","验证码不能为空！");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        // 验证码不为空
        // 判断验证码是否正确
        if (!checkcode.equalsIgnoreCase(verifycode)){
            // 验证码错误
            // 设置提示信息
            request.setAttribute("login_msg","验证码错误！");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        // 验证码正确
        // 判断用户名和密码是否正确
        // 调用service
        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        ManagerService managerService = new ManagerServiceImpl();
        boolean login = managerService.login(manager);
        if (login==false){
            // 登录失败 用户名或密码错误
            // 设置提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        // 登录成功 用户名和密码正确
        // session中存入manager
        session.setAttribute("manager",manager);
        // 重定向到index.jsp页面
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
