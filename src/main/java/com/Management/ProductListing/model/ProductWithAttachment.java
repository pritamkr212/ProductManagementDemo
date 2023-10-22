package com.Management.ProductListing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.web.multipart.MultipartFile;
@Entity
public class ProductWithAttachment {
    private Product product;
    private MultipartFile file;
    @Id
    private String id;

    public ProductWithAttachment(Product product, MultipartFile file) {
        this.product = product;
        this.file = file;
    }
    public ProductWithAttachment(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductWithAttachment{" +
                "product=" + product +
                ", file=" + file +
                ", id='" + id + '\'' +
                '}';
    }
}
