package poly.edu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.DAO.ProductDAO;
import poly.edu.report.Report;

@Controller
@RequestMapping("/report")
public class bai3 {

    @Autowired
    ProductDAO dao;

    @RequestMapping("/bai3")
    public String inventory(Model model) {
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "product/bai3";
    }
}
