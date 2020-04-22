package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.domain.PageBean;
import cn.codewei.manageUsers.domain.User;
import cn.codewei.manageUsers.service.UserService;
import cn.codewei.manageUsers.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request编码
        request.setCharacterEncoding("utf8");

        // 获取参数
        String currentPage = request.getParameter("currentPage");  //当前页数
        String rows = request.getParameter("rows");  //每页显示的条数
        Map<String, String[]> codition = request.getParameterMap();

        if (currentPage==null||"".equals(currentPage)){
            currentPage = "1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }

        // 调用Service查询
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBeanService = userService.findUserByPage(currentPage,rows,codition);

        // 将数据保存到request域中
        request.setAttribute("pb",pageBeanService);
        request.setAttribute("codition",codition);  // 将查询条件保存到request中 进行数据回显

        // 转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
