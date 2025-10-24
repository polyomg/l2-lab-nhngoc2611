package poly.edu.controller;



import poly.edu.service.CookieService;
import poly.edu.service.ParamService;
import poly.edu.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired CookieService cookieService;
    @Autowired ParamService paramService;
    @Autowired SessionService sessionService;

    @GetMapping("/account/login")
    public String login1(Model model) {
        // Hiển thị giá trị đã nhớ (nếu có)
        String user = cookieService.getValue("user");
        model.addAttribute("userCookie", user);
        model.addAttribute("remember", !user.isBlank());
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if ("poly".equals(un) && "123".equals(pw)) {
            // lưu session
            sessionService.set("username", un);

            // remember
            if (rm) {
                cookieService.add("user", un, 24 * 10); // 10 ngày
            } else {
                cookieService.remove("user");
            }

            model.addAttribute("msg", "Đăng nhập thành công!");
        } else {
            model.addAttribute("err", "Sai username hoặc password!");
        }
        // đổ lại userCookie + remember để form hiển thị đúng
        model.addAttribute("userCookie", cookieService.getValue("user"));
        model.addAttribute("remember", rm);
        return "/account/login";
    }
}
