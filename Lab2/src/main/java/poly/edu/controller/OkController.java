package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bai1")
public class OkController {

	@RequestMapping("/ok")
	public String ok() {
		return "ok";
	}
    @PostMapping("/ok")
    public String m1(HttpServletRequest request) {
        request.setAttribute("method", "m1()");
        return "ok";
    }
    
    @GetMapping("/ok")
    public String m2(HttpServletRequest request) {
        request.setAttribute("method", "m2()");
        return "ok";
    }

    @PostMapping(value = "/ok", params = "x")
    public String m3(HttpServletRequest request) {
        request.setAttribute("method", "m3()");
        return "ok";
    }
}
