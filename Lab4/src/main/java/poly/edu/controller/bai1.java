package poly.edu.controller;
import poly.edu.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/staff")
public class bai1 {
	// Hiển thị form
    @GetMapping("/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "staffcreate";
    }

    // Xử lý khi submit form
    @PostMapping("/create/save")
    public String createSave(Model model, 
                             @ModelAttribute("staff") Staff staff,
                             @RequestPart("photo_file") MultipartFile photoFile) {

        // Nếu người dùng upload ảnh -> gán lại tên file
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        model.addAttribute("message", "Xin chào " + staff.getFullname());
        return "staffcreate";
    }
}
