package poly.edu.controller;

import poly.edu.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/staff2")
public class bai2 {
	// Hiển thị form
    @GetMapping("/create2/form2")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "bai2";
    }

    // Xử lý submit
    @PostMapping("/create2/save2")
    public String createSave(
            Model model,
            @Valid @ModelAttribute("staff") Staff staff,
            Errors errors,
            @RequestPart("photo_file") MultipartFile photoFile) {

        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau:");
        } else {
            model.addAttribute("message", "Dữ liệu nhập đã đúng!");
        }

        return "bai2";
    }
}
