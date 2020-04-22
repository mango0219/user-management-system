package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.service.UserService;
import cn.codewei.manageUsers.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request编码
        request.setCharacterEncoding("utf8");

        // 获取request中存储的id
        String i = request.getParameter("id");
        int id = Integer.parseInt(i);

        // 调用Service 完成用户的删除
        UserService userService = new UserServiceImpl();
        userService.delUser(id);

        // 完成删除 重定向到list.jsp页面
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
