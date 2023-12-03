package com.example.product.service.contract;

import com.example.product.entity.Product;
import java.util.List;

public interface ProductService {

    public Product insertProduct(Product product);
    public Product getProductById(Long id);
    public void deleteProductById(Long id);
    public Product updateProduct(Product newInfo);
    public List<Product> getAllProduct();
}
