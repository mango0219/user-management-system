package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.domain.User;
import cn.codewei.manageUsers.service.UserService;
import cn.codewei.manageUsers.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有用户信息
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用Service完成查询
        UserService userService = new UserServiceImpl();
        List<User> list = userService.findAll();

        // 将list存到request域中
        request.setAttribute("list",list);
        // 转发list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
