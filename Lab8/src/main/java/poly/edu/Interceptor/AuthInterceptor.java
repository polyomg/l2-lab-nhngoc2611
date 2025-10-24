package poly.edu.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import poly.edu.entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri); 
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("message", "Vui lòng đăng nhập để tiếp tục!");
            response.sendRedirect("/auth/login");
            return false;
        }
        
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index") && !user.isAdmin()) {
            session.setAttribute("message", "Bạn không có quyền truy cập trang này!");
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }

    private boolean isAdmin(Account user) {
        return false; // TODO: sửa theo thực tế của bạn
    }
}
