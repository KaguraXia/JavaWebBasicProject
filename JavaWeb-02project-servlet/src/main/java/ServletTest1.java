import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.Executor;

@WebServlet("/hello")
public class ServletTest1 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init()...");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service()...");

        // 获取请求方法和URL信息
        String method = request.getMethod();
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();


        System.out.println("请求方法：" + method);
        System.out.println("完整请求URL：" + requestURL);
        System.out.println("请求资源URI：" + requestURI);
        System.out.println("上下文路径：" + contextPath);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header Name: " + headerName);
            String headerValue = request.getHeader(headerName);
            System.out.println("Header Value: " + headerValue);
        }

        String userAgent = request.getHeader("User-Agent");
        System.out.println("本次请求的用户代理：" + userAgent);


        // 获取请求方信息
        String host =  request.getRemoteHost();//获取发起请求的客户端主机名。
        String address = request.getRemoteAddr();//获取客户端IP地址。
        int clientPort = request.getRemotePort();//获取客户端使用的网络端口。
        String serverName = request.getServerName();//获取服务器的主机名或IP地址。
        int serverPort = request.getServerPort();//获取接收请求的服务器端口。
        String scheme = request.getScheme();//获取请求使用的协议，如 "http" 或 "https"。
        System.out.println("获取发起请求的客户端主机名：" + host);
        System.out.println("获取客户端IP地址：" + address);
        System.out.println("获取客户端使用的网络端口：" + clientPort);
        System.out.println("获取服务器的主机名或IP地址：" + serverName);
        System.out.println("获取接收请求的服务器端口：" + serverPort);
        System.out.println("获取请求使用的协议：" + scheme);

        // 获取请求参数
        String param = request.getParameter("name");
        String params = Arrays.toString(request.getParameterValues("hobby"));
        String queryString = request.getQueryString();

        System.out.println("获取到的单个参数："+param);
        System.out.println("获取到的多个同名参数："+params);
        System.out.println("获取请求URL中携带的参数（？后面的参数）"+queryString);

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        // 获取字符输出流
        PrintWriter out = resp.getWriter();

        // 输出HTML内容
        out.println("<html><body>");
        out.println("<h1>你好，夏久!</h1>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}