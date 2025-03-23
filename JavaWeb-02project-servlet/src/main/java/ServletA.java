import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/a")
public class ServletA extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ServletA收到get请求");
        // 往req中存数据, 名为"a", 值为：111
        req.setAttribute("a","111");
        // 将请求转发到ServletB  
        req.getRequestDispatcher("/b").forward(req,resp);
    }
}