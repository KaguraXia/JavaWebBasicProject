import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    // 用Map模拟商品数据库
    private static final Map<String, Book> products = new HashMap<>();

    static{
        products.put("1001",new Book("1001","Web基础",56.00));
        products.put("1002",new Book("1002","Spring Boot",60.00));
    }

    // 将数据传入cart
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个为action的请求，方便区分后续的请求操作
        String action = req.getParameter("action");

        // 创建一个Seesion的对话，用于保存购物车的状态
        HttpSession session =req.getSession();

        // 创建一个名为cart对象，值为Session中的cart，并将其强转为List<Book>类型
        List<Book> cart = (List<Book>) session.getAttribute("cart");

        // 如果cart里面内容为空，则初始化，创建一个新的"cart"存入Session
        if(cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

            // 如果返回的请求内容为“add”则获取要添加的书籍的id
            if ("add".equals(action)) {
                String bookId = req.getParameter("id");

                // 防御性检查，如果id为空，则重定向回到当前主页
                if (bookId == null){
                    resp.sendRedirect(req.getContextPath() +"/books");
                    return;
                }

                // 不为空的时候，检索数据库中的符合要求的ID加入到Session中
                cart.add(products.get(bookId));

                // 显式更新Session（可选，此处用于明确状态变更）
                // 将当前的购物车对象（cart）重新存入 Session 中。
                // "cart" 是 Session 中用于标识购物车对象的属性名称。
                // cart 是当前代码中操作的购物车对象（List<Book> 类型）。
                session.setAttribute("cart", cart);

                // 数据提交完成，重定向到回到当前主页
                resp.sendRedirect(req.getContextPath()+("/books"));
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 新建一个“books”用来存放所有的商品
        List<Book> books = new ArrayList<>();
        // 遍历map，将map中的数据存入books中
        for(Map.Entry<String, Book>entry : products.entrySet()){
            books.add(entry.getValue());
        }
        req.setAttribute("books",books);
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}
