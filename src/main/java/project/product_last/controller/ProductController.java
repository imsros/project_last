package project.product_last.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product_last.model.request.ProductRequest;
import project.product_last.model.response.ResponseObject;
import project.product_last.service.JwtService;
import project.product_last.service.ProductService;

import java.sql.Timestamp;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request){
        String fullToken = request.getHeader("Authorization");
        String token = fullToken.substring(7);
        int id = Integer.parseInt(jwtService.extractId(token));
        productRequest.setUser_id(id);
        productService.addProduct(productRequest);
        ResponseObject responseObject = ResponseObject.builder()
                .message("Product Added Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(responseObject, responseObject.getStatus());
    }
}
