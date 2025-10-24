package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.model.Model;

import org.springframework.stereotype.Controller;
import poly.edu.service.MailService;

import jakarta.mail.MessagingException;

@Controller
public class MailController {

	@Autowired
	MailService mailService;

	@ResponseBody
	@RequestMapping("/mail/send")
	public String send(Model model) {
		try {

			mailService.send("nhungocnia@gmail.com", "Subject", "hahahahahahahahahahhahaah");
			return "Mail của bạn đã được gửi đi";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	@RequestMapping("/mail/send-queue")
	public String sendqueue(Model model) {
		mailService.push("hoanganh070607@gmail.com", "Subject", "Body");
		return "Mail của bạn đã được xếp vào hàng đợi";
	}
	
}
