package poly.edu.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.entity.Product;


@Controller
public class bai4Controller {
	@GetMapping("/bai4/form")
    public String form(Model model) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);

        model.addAttribute("product1", p); 
        model.addAttribute("items", getItems());
        return "bai4"; 
    }
	@PostMapping("/bai4/save")
    public String save(@ModelAttribute("product2") Product p, Model model) { 
        model.addAttribute("product1", new Product("iPhone 30", 5000.0)); 
        model.addAttribute("product2", p);
        model.addAttribute("items", getItems()); 
        return "bai4";
    }

    public List<Product> getItems() {
        return Arrays.asList(new Product("Như Ngọc", 10.0), new Product("Laptop Gigabyte GD G5", -800.0));
    }
}
