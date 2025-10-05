package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tinhtoan")

public class ChuNhat {
		@GetMapping("/TinhHinhChuNhat")
	    public String formChuNhat() {
	        return "login/ChuNhat";
	    }
	    @PostMapping("/tinhToanChuNhat")
	    public String tinhToanChuNhat(@RequestParam double chieuDai, @RequestParam double chieuRong, Model model) {
	    double chuVi = (chieuDai + chieuRong) * 2;
	    double dienTich = chieuDai * chieuRong;
	    model.addAttribute("chieuDai", chieuDai);
	        model.addAttribute("chieuRong", chieuRong);
	        model.addAttribute("chuVi", chuVi);
	        model.addAttribute("dienTich", dienTich);
	    return "login/ChuNhat";
	    }
	}
