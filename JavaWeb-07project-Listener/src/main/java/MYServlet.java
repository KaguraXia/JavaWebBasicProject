import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/onlineUsers")
public class MYServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用监听器的静态方法获取在线人数
        int onlineUserCount = MyListener.getonlineUserCount();

        // 打印日志调试
        System.out.println("Servlet 中获取的在线人数：" + onlineUserCount);

        // 将在线人数存入 request 作用域，供 JSP 页面使用
        req.setAttribute("onlineUserCount", onlineUserCount);

        // 转发到 JSP 页面
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
