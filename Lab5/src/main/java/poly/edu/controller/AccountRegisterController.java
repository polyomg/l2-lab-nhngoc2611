package poly.edu.controller;



import poly.edu.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AccountRegisterController {
    @Autowired ParamService paramService;

    @GetMapping("/account/register")
    public String view() {
        return "/register";
    }

    @PostMapping("/account/register")
    public String doRegister(Model model, MultipartFile photo) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        File saved = paramService.save(photo, "uploads"); // lưu dưới /uploads

        model.addAttribute("msg", "OK: user=" + username +
                ", photo=" + (saved != null ? saved.getName() : "no file"));
        return "/register";
    }
}
