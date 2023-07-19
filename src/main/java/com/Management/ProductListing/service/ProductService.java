package com.Management.ProductListing.service;

import com.Management.ProductListing.model.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);
    public List<Product> getAllProduct();
    public Product getProductById(Integer productId);
    public String deleteProductById(Integer productId);
    public Product updateProduct(Product product,Integer productId);

}



