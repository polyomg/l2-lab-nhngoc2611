package poly.edu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.entity.Product;
@Controller
public class bai4them {
	public List<Product> items = new ArrayList<>(
			Arrays.asList(
				new Product("Như Ngọc", 10.0),
	            new Product("Laptop", +10000.0)
			)
	);
	@GetMapping("/bai42/form2")
    public String form(Model model) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);

        model.addAttribute("product1", p); 
        model.addAttribute("items", items);
        return "/bai4"; 
    }
	@PostMapping("/bai42/save2")
    public String save(@ModelAttribute("product2") Product p, Model model) { 
        model.addAttribute("product1", new Product("iPhone 30", 5000.0)); 
        model.addAttribute("product2", p);
        //model.addAttribute("items", getItems());
        items.add(p);
        model.addAttribute("items", items);
        return "/bai4";
    }
}