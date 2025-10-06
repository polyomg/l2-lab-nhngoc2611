package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class bai5Controller {
	@RequestMapping("/a")
    public String m1() {
        return "bai5";
    }

    @RequestMapping("/b")
    public String m2(Model model) {
        model.addAttribute("message", "I come from b");
        return "bai5"; // ?1: forward về a và hiển thị biến message trong Model
    } 

    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        params.addAttribute("message", "I come from c");
        return "redirect:/a"; // ?2: redirect về /a và hiển thị message trong param
    }

    @ResponseBody
    @RequestMapping("/d")
    public String m4() {
        return "I come from d"; // ?3: hiển thị kết quả trả về của m4
    }
}