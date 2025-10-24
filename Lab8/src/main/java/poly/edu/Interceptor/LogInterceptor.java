package poly.edu.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import poly.edu.entity.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Account user = (Account) request.getSession().getAttribute("user");

        String log = "▶ [LOG] URI: " + request.getRequestURI()
                + " | Time: " + new Date()
                + " | User: " + (user != null ? user.getFullname() : "[Chưa đăng nhập]");

        // In ra console
        System.out.println(log);

        // Lưu log vào session để hiển thị ra giao diện
        List<String> logs = (List<String>) request.getSession().getAttribute("logs");
        if (logs == null) {
            logs = new ArrayList<>();
        }
        logs.add(0, log); // thêm log mới lên đầu danh sách
        request.getSession().setAttribute("logs", logs);

        return true;
    }
}
