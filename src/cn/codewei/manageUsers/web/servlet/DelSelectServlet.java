package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.service.UserService;
import cn.codewei.manageUsers.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectServlet")
public class DelSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request编码
        request.setCharacterEncoding("utf8");

        String[] ids = request.getParameterValues("uid");

        // 调用Service进行用户的批量删除
        UserService userService = new UserServiceImpl();
        userService.delSelect(ids);

        // 完成删除 重定向到list.jsp页面
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
