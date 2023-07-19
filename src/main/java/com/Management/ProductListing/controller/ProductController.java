package com.Management.ProductListing.controller;

import com.Management.ProductListing.model.Product;
import com.Management.ProductListing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?>saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<?>getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public  ResponseEntity<?>getProductById(@PathVariable Integer productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }
    @GetMapping("/deleteProduct/{productId}")
    public  ResponseEntity<?>deleteProductById(@PathVariable Integer productId){
        return new ResponseEntity<>(productService.deleteProductById(productId),HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{productId}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product,@PathVariable Integer productId){
        return new ResponseEntity<>(productService.updateProduct(product,productId), HttpStatus.CREATED);
    }

}
