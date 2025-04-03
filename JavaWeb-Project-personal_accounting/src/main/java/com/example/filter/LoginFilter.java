package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "LoginFilter",
        urlPatterns = {
                "/viewBills",
                "/editBill",
                "/deleteBill",
                "/addBill.jsp",
                "/addBill"
        }
)
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 调试输出当前请求路径
        System.out.println("[LoginFilter] 拦截请求: " + req.getRequestURI());

        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session !=  null && session.getAttribute("username") != null);

        if (isLoggedIn) {
            System.out.println("[LoginFilter] 用户已登录，允许访问: " + req.getRequestURI());
            chain.doFilter(request, response);
        } else {
            System.out.println("[LoginFilter] 用户未登录，重定向到登录页");
            String redirectPath = req.getContextPath() + "/login.jsp?error=请先登录";
            System.out.println("[LoginFilter] 重定向路径: " + redirectPath);
            res.sendRedirect(redirectPath);
        }
    }
}
