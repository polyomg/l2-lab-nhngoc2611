package poly.edu.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bai4")
public class bai4 {
	@RequestMapping("/index")
	public String index(Model model) {
		return "/bai4/home/index";
	}
	@RequestMapping("/about")
	public String about(Model model) {
		return "/bai4/home/about";
	}
}
