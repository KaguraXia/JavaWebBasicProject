import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/page")
public class ServletTest2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置内容类型为HTML
        resp.setContentType("text/html");

        // 获取输出流
        PrintWriter out = resp.getWriter();

        // 输出HTML头部信息
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>原神，启动！</title>");
        out.println("</head>");

        // 输出HTML主体部分
        out.println("<body>");
        out.println("<h1>扣一启动原神</h1>");
        out.println("<p>曼巴OUT !</p>");
        out.println("</body>");

        // 输出HTML结束标签
        out.println("</html>");

        // 不需要手动关闭out，因为它会在Servlet容器内部自动关闭
    }
}