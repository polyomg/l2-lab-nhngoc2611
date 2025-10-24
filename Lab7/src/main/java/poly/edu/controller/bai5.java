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
public class bai5 {
	@Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;
    @GetMapping("/product/search-page")
    public String searchPage(@RequestParam(defaultValue = "") String keywords,
                             @RequestParam(defaultValue = "0") int p,
                             Model model) {

        Pageable pageable = PageRequest.of(p, 5);
        Page<Product> page = dao.findAllByNameLike("%" + keywords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);

        return "product/search-page";
    }
}
