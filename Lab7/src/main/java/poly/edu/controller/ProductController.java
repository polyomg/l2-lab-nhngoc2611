package poly.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.edu.DAO.ProductDAO;
import poly.edu.entity.Product;
import org.springframework.ui.Model;


@Controller
public class ProductController {
	@Autowired
	ProductDAO dao;
	
	@RequestMapping("/product/search")
	public String search(Model model,@RequestParam("min") Optional<Double> min,@RequestParam("max") Optional<Double> max) {
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		List<Product> items = dao.findByPrice(minPrice, maxPrice);
		model.addAttribute("items", items);
		return "product/search";
		}
}