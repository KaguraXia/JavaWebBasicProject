import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {

    // 处理 GET 请求，直接跳转到注册页面
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取表单中提交的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 获取Session
        HttpSession session = req.getSession();

        // 检查当前 Session 中是否已有用户信息
        if(session.getAttribute("RegisterUsername") == null){
            // 如果表单中没有用户的用户名，将用户的用户名和密码存入Session中
            session.setAttribute("RegisterUsername",username);
            session.setAttribute("RegisterPassword",password);

            // 重定向到登录界面
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
        else{
            // 若存在该用户名，返回“以存在相同的用户名，请重试”
            req.setAttribute("msg","已存在相同的用户名，请修改");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }
}
