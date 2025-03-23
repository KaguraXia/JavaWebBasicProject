import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyStudent")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentsList = new ArrayList<Student>();

        Student student1 = new Student();
        student1.setId(1);
        student1.setNum(114514);
        student1.setName("牢大");

        Student student2 = new Student();
        student2.setId(2);
        student2.setNum(10002);
        student2.setName("胖猫");

        Student student3 = new Student();
        student3.setId(3);
        student3.setNum(10003);
        student3.setName("奶龙");

        Student student4 = new Student();
        student4.setId(4);
        student4.setNum(10004);
        student4.setName("蔡徐坤");

        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);

        req.setAttribute("students",studentsList);

        req.getRequestDispatcher("student.jsp").forward(req,resp);
    }
}
