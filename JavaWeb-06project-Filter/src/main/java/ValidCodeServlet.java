
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/vcode")
public class ValidCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generateCode(req, resp);
    }

    private void generateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        char sc = 'a';
        for (int i = 0; i <= 4; i++) {
            int str = random.nextInt(62);
            if (str < 10) {
                sb.append(str);
            } else if (str < 36) {
                sc = (char) ((str - 10) + 'a');
                sb.append(sc);
            } else {
                sc = (char) ((str - 36) + 'A');
                sb.append(sc);
            }
        }

        BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(Color.blue);
        graphics.setFont(new Font("Time", Font.BOLD, 18));
        graphics.drawString(sb.toString(), 3, 17);
        graphics.dispose();
        ImageIO.write(image, "JPG", response.getOutputStream());
        System.out.println("生成的验证码：" + sb);

        // 将验证码保存到session
        request.getSession().setAttribute("code", sb.toString());
    }
}
