package poly.edu.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import poly.edu.service.MailService;
import poly.edu.service.MailService.Mail;

@Controller
@RequestMapping("/mail")
public class MailFormController {
	@Autowired
    MailService mailService;

    @GetMapping("/form")
    public String form(Model model) {
    	model.addAttribute("mail", Mail.builder().build());
        return "mail/form";
    }

    @PostMapping("/send-direct")
    public String sendDirect(@ModelAttribute("mail") Mail mail, @RequestParam("files") MultipartFile[] files, Model model) {
        attachFiles(mail, files);
        try {
            mailService.send(mail);
            model.addAttribute("message", "Đã gửi trực tiếp");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi gửi mail: " + e.getMessage());
        }
        return "mail/form";
    }

    @PostMapping("/send-queue")
    public String sendQueue(@ModelAttribute("mail") Mail mail, @RequestParam("files") MultipartFile[] files, Model model) {
        attachFiles(mail, files);
        mailService.push(mail);
        model.addAttribute("message", "Đã xếp hàng đợi");
        return "mail/form";
    }

    private void attachFiles(Mail mail, MultipartFile[] files) {
        try {
            StringBuilder filenames = new StringBuilder();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String path = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
                    File f = new File(path);
                    f.getParentFile().mkdirs();
                    Files.write(f.toPath(), file.getBytes());
                    filenames.append(path).append(";");
                }
            }
            mail.setFilenames(filenames.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
