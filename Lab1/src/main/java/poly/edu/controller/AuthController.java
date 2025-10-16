package poly.edu.controller;

	import jakarta.servlet.http.HttpServletRequest;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;

	@Controller
	@RequestMapping("/login")
	public class AuthController {
	    @Autowired
	    HttpServletRequest request;

	    @GetMapping("/form")
	    public String login() {
	        return "login/form";
	    }
	    @PostMapping("/check")
	    public String loginCheck(Model model) {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        if ("Như Ngọc".equals(username) && "123".equals(password)) {
	            model.addAttribute("message", "Đăng nhập thành công!");

	        } else {
	            model.addAttribute("message", "Đăng nhập thất bại");
	        }
	        return "login/form";
	    }
	}

