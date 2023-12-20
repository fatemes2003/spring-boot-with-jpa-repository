package com.example.product.service;

import com.example.discount.CouponResponse;
import com.example.discount.DiscountClient;
import com.example.product.entity.Product;
import com.example.product.entity.dto.ProductRequest;
import com.example.product.repository.ProductRepository;
import com.example.product.service.contract.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private DiscountClient discountClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product insertProduct(ProductRequest productRequest) {
        //CouponResponse couponResponse = restTemplate.getForObject("http://localhost:8086/api/v1/coupons/get-by-code/{code}", CouponResponse.class, productRequest.getCode());
        //CouponResponse couponResponse = restTemplate.getForObject("http://DISCOUNT/api/v1/coupons/get-by-code/{code}", CouponResponse.class, productRequest.getCode());
        ResponseEntity<CouponResponse> couponByCode = discountClient.getCouponByCode(productRequest.getCode());

        Product product = modelMapper.map(productRequest, Product.class);
        BigDecimal subtract = new BigDecimal("100").subtract(Objects.requireNonNull(couponByCode.getBody()).getDiscount());
        product.setPrice(subtract.multiply(product.getPrice()).divide(new BigDecimal("100")));
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> coupon = productRepository.findById(id);
        return coupon.orElse(null);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product newInfo) {
        return productRepository.save(newInfo);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
