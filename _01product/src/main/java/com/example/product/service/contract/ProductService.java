package com.example.product.service.contract;

import com.example.product.entity.Product;
import com.example.product.entity.dto.ProductRequest;

import java.util.List;

public interface ProductService {

    public Product insertProduct(ProductRequest product);
    public Product getProductById(Long id);
    public void deleteProductById(Long id);
    public Product updateProduct(Product newInfo);
    public List<Product> getAllProduct();
}
