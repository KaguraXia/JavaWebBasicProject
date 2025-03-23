import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 映射URL地址为 /login
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取用户名和密码
        String username_login = req.getParameter("username");
        String password_login = req.getParameter("password");

        // 设置一个正确的的账号密码,账号为 root，密码为：123456
        final String CORRECT_USERNAME = "root";
        final String CORRECT_PASSWORD = "123456";

        // 输出用户名和密码，用于后端调试
        System.out.println("收到用户名是："+ username_login);
        System.out.println("收到密码是："+ password_login);

        // 简单比较用户输入是否和设定的正确用户名密码相同
        if(CORRECT_USERNAME.equals(username_login) && CORRECT_PASSWORD.equals(password_login)){
            // 登录成功，重定向到"Home"页面
            resp.sendRedirect(req.getContextPath()+ "/Home.jsp");
        }
        else {
            // 登录失败时，将值为“用户名或者密码错误”，名为“errorMessage”的请求数据传递到login.jsp中进行响应
            req.setAttribute("errorMessage","用户名或者密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    //在 Login Servlet 中实现 doGet 方法，可以在地址栏直接/login 而不用/login.jsp, 但是不安全
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 GET 请求重定向到登录页面
        resp.sendRedirect("login.jsp");
    }

}
