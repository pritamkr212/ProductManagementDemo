package com.Management.ProductListing.repository;

import com.Management.ProductListing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
