import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements HttpSessionListener {
    // 创建一个全局变量 onlineUserCount 用于储存在线人数
    private static int onlineUserCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUserCount++;
        System.out.println("Session 创建，当前在线人数：" + onlineUserCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineUserCount--;
        System.out.println("Session 销毁，当前在线人数：" + onlineUserCount);
    }

    // 获取在线人数
    public static int getonlineUserCount() {
        return onlineUserCount;
    }
}
