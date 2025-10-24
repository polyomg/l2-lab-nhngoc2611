package poly.edu.service;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /** Lấy cookie theo tên */
    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    /** Lấy giá trị cookie theo tên */
    public String getValue(String name) {
        Cookie c = get(name);
        return (c == null) ? "" : c.getValue();
    }

    /** Tạo & gửi cookie về client (hours: thời hạn) */
    public Cookie add(String name, String value, int hours) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setHttpOnly(true);
        c.setMaxAge(hours * 60 * 60);
        response.addCookie(c);
        return c;
    }

    /** Xóa cookie */
    public void remove(String name) {
        Cookie c = new Cookie(name, "");
        c.setPath("/");
        c.setMaxAge(0);
        response.addCookie(c);
    }
}
