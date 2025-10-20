package poly.edu.DAO;
import java.util.List;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import poly.edu.entity.Product;
import poly.edu.report.Report;


@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    	
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);
    
    @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "

    + " FROM Product o "
    + " GROUP BY o.category"
    + " ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();
    
    
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}