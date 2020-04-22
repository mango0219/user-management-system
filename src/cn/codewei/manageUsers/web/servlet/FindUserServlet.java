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

/**
 *  回显用户信息
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要修改信息用户的id
        String i = request.getParameter("id");
        int id = Integer.parseInt(i);

        // 通过id获取用户的信息，进行回显
        // 调用Service
        UserService userService = new UserServiceImpl();
        User user = userService.findUser(id);


        // 将user存入到request域中
        request.setAttribute("user",user);

        // 转发到add.jsp页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
