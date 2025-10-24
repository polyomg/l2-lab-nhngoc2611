package poly.edu.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.DAO.ProductDAO;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;
@Controller
public class bai4 {
	@Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;
    @GetMapping("/product/search-price")
    public String searchByPrice(@RequestParam(defaultValue = "0") double min,
                                @RequestParam(defaultValue = "200") double max,
                                Model model) {
        List<Product> list = dao.findByPriceBetween(min, max);
        model.addAttribute("items", list);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "product/search-price";
    }
}

//hiểu hong alooo???? ê giỡn mẳh
