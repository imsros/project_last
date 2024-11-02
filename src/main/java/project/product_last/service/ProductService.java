package project.product_last.service;

import lombok.AllArgsConstructor;
import project.product_last.model.entities.Product;
import project.product_last.repository.ProductRepository;

@AllArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;
    public boolean addProduct(Product product){
        productRepository.save(product);
    }

}
