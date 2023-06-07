package com.ensat.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ensat.entities.Product;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/api")
    public Iterable<Product> showAllProducts() {
        Iterable<Product> productList = productService.listAllProducts();
        return productList;
    }

    @GetMapping("product/api/{id}")
    public Product showProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return product;
    }

    @PostMapping("product/api/new")
    public Product createEmployee(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    @DeleteMapping("/product/api/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "Deleted the product with ID:" + id;
    }

    @PutMapping("/product/api/edit/{id}")
    public ResponseEntity<Product> edit(@PathVariable Integer id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.getProductById(id) ;

        updatedProduct.setProductId(productDetails.getProductId());
        updatedProduct.setName(productDetails.getName());
        updatedProduct.setPrice(productDetails.getPrice());

        productService.saveProduct(updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }
}
