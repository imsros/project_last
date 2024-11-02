package project.product_last.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product_last.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    

}
