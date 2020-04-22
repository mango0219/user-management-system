package cn.codewei.manageUsers.web.servlet;

import cn.codewei.manageUsers.domain.User;
import cn.codewei.manageUsers.service.UserService;
import cn.codewei.manageUsers.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request编码
        request.setCharacterEncoding("utf8");

        // 获取表单数据
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        // 将map集合封装为集合
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 掉用Service完成用户信息添加
        UserService userService = new UserServiceImpl();
        userService.addUser(user);

        // 添加完成 重定向到list.jsp页面
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
