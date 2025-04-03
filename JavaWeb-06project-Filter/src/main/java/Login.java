import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

// 映射URL地址为 /login
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取用户名和密码
        String username_login = req.getParameter("username");
        String password_login = req.getParameter("password");

        // 获取已注册的用户列表,不用创建新的会话
        HttpSession session = req.getSession(false);

        // 检查是否有用户注册信息
        if(session != null){
            // 当存在用户信息时，验证用户的用户名和密码是否正确
            String RegisterUsername = (String) session.getAttribute("RegisterUsername");
            String RegisterPassword = (String) session.getAttribute("RegisterPassword");

            if(username_login.equals(RegisterUsername) && password_login.equals(RegisterPassword)){
                // 登录成功，重定向到"ViewBillsServlet"页面
                resp.sendRedirect(req.getContextPath()+ "/viewBills");
                return;
            }
            else {
                // 登录失败时，将值为“用户名或者密码错误”，名为“errorMessage”的请求数据传递到login.jsp中进行响应
                req.setAttribute("errorMessage","用户名或者密码错误");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }
    }

    //在 Login Servlet 中实现 doGet 方法，可以在地址栏直接/login 而不用/login.jsp, 但是不安全
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 GET 请求重定向到登录页面
        resp.sendRedirect("login.jsp");
    }

}
