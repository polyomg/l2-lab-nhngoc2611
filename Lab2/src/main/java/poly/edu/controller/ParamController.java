package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ParamController {

    @RequestMapping("/bai2/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/bai2/save/{x}")
    public String save(
            @PathVariable("x") String x,
            @RequestParam("y") String y,
            HttpServletRequest request) {

        request.setAttribute("x", x);
        request.setAttribute("y", y);

        return "/form.html"; 
    }
}