package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.entity.dto.ProductRequest;
import com.example.product.entity.dto.ProductResponse;
import com.example.product.service.contract.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        log.info("new product created : {}", productRequest);
        Product product = productService.insertProduct(productRequest);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest)
    {
        Product product = modelMapper.map(productRequest, Product.class);
        product = productService.updateProduct(product);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProduct().stream().map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return ResponseEntity.ok().body(productResponse);
    }
}
