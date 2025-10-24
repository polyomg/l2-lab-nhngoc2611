package poly.edu.controller; 


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import poly.edu.DAO.ProductDAO;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;


@Controller
public class bai2 {
    
    @Autowired
    ProductDAO dao; 
    
    @Autowired
    SessionService session;
    
    @RequestMapping("/product/bai2")
    public String searchAndPage(Model model, @RequestParam("keywords") Optional<String> kw,@RequestParam("p") Optional<Integer> p) {
    	String kwords = kw.orElse(session.get("keywords", ""));
    	session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5); 

        Page<Product> page = dao.findByKeywords("%"+kwords+"%", pageable);
        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        
        return "product/searchandpage";
    }
}