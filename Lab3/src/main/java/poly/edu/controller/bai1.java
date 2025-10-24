package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.entity.Staff;

@Controller
@RequestMapping("/staff")
public class bai1{
	
    @RequestMapping("/detail")
    public String detail( Model model) {
        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullname("nguyễn văn user")
                .level(2)   // 2 = Tướng
                .build();
        model.addAttribute("staff", staff);
        return "staffdetail";
    }
}
