package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.entity.Product;

@Controller
public class bai3Controller {
	@GetMapping("/product/form")
	public String form(Model model) {
		model.addAttribute("product", new Product());
		return "bai3";
	}

	@PostMapping("/product/save")
	public String save(@ModelAttribute Product product, Model model) {
		model.addAttribute("product", product);
		return "bai3";
	}
}
