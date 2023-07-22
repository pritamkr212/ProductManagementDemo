package com.Management.ProductListing.service;

import com.Management.ProductListing.model.Product;
import com.Management.ProductListing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> deleteProductById(String productId) {
        try {
            Product product = productRepository.findById(productId).get();
            if (product != null) {
                productRepository.deleteById(productId);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        finally {
            return productRepository.findAll();
        }
    }

    @Override
    public Product updateProduct(Product product,String productId) {
        Product oldProduct=productRepository.findById(productId).get();
        oldProduct.setProductName(product.getProductName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());
        return productRepository.save(oldProduct);
    }

}
