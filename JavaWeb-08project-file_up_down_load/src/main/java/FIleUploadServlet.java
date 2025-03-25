import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


// 核心配置：声明该Servlet处理文件上传能力
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,// 当文件大于1MB时开始写入磁盘临时文件
        maxFileSize = 1024 * 1024 * 10,     // 允许的单个文件最大大小10M
        maxRequestSize = 1024 * 1024 *50    // 整个请求最大允许的内存为50M
)

@WebServlet("/upload")
public class FIleUploadServlet extends HttpServlet {
    // 定义保存文件的目录名称
    private static final String UPLOAD_DIR = "file_storage";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // 步骤1：获取服务器上的实际存储路径
        String appRealPath = req.getServletContext().getRealPath("");

        // 拼接完整上传目录路径，例如：/tomcat/webapps/yourApp/file_storage/
        String uploadPath = appRealPath + File.separator + UPLOAD_DIR;

        // 步骤2：自动创建上传目录（如果不存在）
        File uploadDir = new File(uploadPath);

        // mkdirs()会创建所有不存在的父目录
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 步骤3：获取文件上传部件，上传表单中<input type="file" name="file">的name属性值
        Part filePart = req.getPart("file");

        // 步骤4：处理原始文件名
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // 步骤5：生成唯一文件名防止重名覆盖
        // 使用UUID生成前缀，保留文件原始扩展名
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // 步骤6：执行文件写入操作
        try (InputStream fileContent = filePart.getInputStream()) {
            File targetFile = new File(uploadDir, uniqueFileName);
            Files.copy(fileContent, targetFile.toPath());
        }

        // 步骤7：返回友好提示
        PrintWriter out = resp.getWriter();
        out.println("<h3>上传成功！保存文件名：" + uniqueFileName + "</h3>");
        out.println("<a href='download.html'>点击前往下载页面</a>");
    }
}
