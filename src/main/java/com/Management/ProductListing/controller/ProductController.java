package com.Management.ProductListing.controller;

import com.Management.ProductListing.model.Product;
import com.Management.ProductListing.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("api/v1/")
public class ProductController {

    private static final Logger logger= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?>saveProduct(@RequestBody Product product){
        logger.debug("Getting New Product {}",product.getProductId());
        ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
        logger.debug("Saving Products {}" ,product.getProductId());
        return res;
    }
    @GetMapping("/products")
    public ResponseEntity<?>getAllProduct(){
        logger.debug("Getting All Product");
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public  ResponseEntity<?>getProductById(@PathVariable String productId){
        logger.info("Getting Specific Product {}",productId);
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{productId}")
    public  ResponseEntity<?>deleteProductById(@PathVariable String productId){
        logger.debug("Deleting the Product {}",productId);
        ResponseEntity<?>res= new ResponseEntity<>(productService.deleteProductById(productId),HttpStatus.OK);
        logger.debug("Product SuccessFully deleted {}",productId);
        return res;
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product,@PathVariable String productId){
        logger.debug("Updating the Product {}",productId);
        ResponseEntity<?>res= new ResponseEntity<>(productService.updateProduct(product,productId), HttpStatus.CREATED);
        logger.debug("Product SuccessFully updated {}",productId);
        return res;

    }

}
