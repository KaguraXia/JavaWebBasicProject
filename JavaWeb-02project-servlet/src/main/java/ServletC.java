import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/c")
public class ServletC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletC收到get请求");

        // 存一个名为"c"，值为"111"的req请求
        req.setAttribute("c","111");
        // 将请求重定向到ServletD
        resp.sendRedirect(req.getContextPath()+"/d");
    }
}
