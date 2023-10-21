package com.Management.ProductListing.service;

import com.Management.ProductListing.model.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product,String id);
    public List<Product> getAllProduct();
    public Product getProductById(String productId);
    public List<Product> deleteProductById(String productId);
    public Product updateProduct(Product product,String productId);

}



