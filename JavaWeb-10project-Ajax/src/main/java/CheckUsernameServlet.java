import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckUsernameServlet")
class CheckUsernameServlet extends HttpServlet {

    UserDao userDao=new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean exists=false;
        // 设置响应格式为JSON（参考网页7/8）
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        User user = userDao.getUserByName(username);
        System.out.println("db:"+user);
        if (user!=null){
            exists = true;
        }

        // 返回JSON对象（例如：{ "exists": true }）
        PrintWriter out = response.getWriter();
        out.print("{\"exists\": " + exists + "}");
        out.flush();
    }
}