package cn.codewei.manageUsers.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        Object manager = session.getAttribute("manager");
        String uri = request.getRequestURI();
        if (manager!=null||uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/checkCodeServlet")||uri.contains("/css/")||uri.contains("/fonts/")||uri.contains("/js/")){
            chain.doFilter(req, response);
        }else {
            request.setAttribute("login_msg","您还未登陆！请先登陆！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    public void destroy() {
    }

}
