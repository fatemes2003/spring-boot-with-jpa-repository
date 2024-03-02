package com.example.product.service;

import com.example.discount.CouponResponse;
import com.example.discount.DiscountClient;
import com.example.notification.NotificationClient;
import com.example.notification.NotificationRequest;
import com.example.product.entity.Product;
import com.example.product.entity.dto.ProductRequest;
import com.example.product.repository.ProductRepository;
import com.example.product.service.contract.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private NotificationClient notificationClient;

//    @Autowired
//    private RabbitMQMessageProducer rabbitMQMessageProducer;

    @Autowired
    private KafkaTemplate<String, NotificationRequest> kafkaTemplate;
    @Override
    public Product insertProduct(ProductRequest productRequest) {
        Product product=new Product(productRequest.getName(),productRequest.getDescription(),productRequest.getPrice());
        //CouponResponse couponResponse = restTemplate.getForObject("http://localhost:8086/api/v1/coupons/get-by-code/{code}", CouponResponse.class, productRequest.getCode());
        //CouponResponse couponResponse = restTemplate.getForObject("http://DISCOUNT/api/v1/coupons/get-by-code/{code}", CouponResponse.class, productRequest.getCode());
        //ResponseEntity<CouponResponse> couponByCode = discountClient.getCouponByCode(productRequest.getCode());
        //ResponseEntity<CouponResponse> couponByCode = getCoupon(productRequest.getCode());
        CouponResponse coupon = discountClient.getCouponByCode(productRequest.getCode());

        if (coupon!=null)
        {
            BigDecimal subtract = new BigDecimal("100").subtract(coupon.getDiscount());
            product.setPrice(subtract.multiply(product.getPrice()).divide(new BigDecimal("100")));
        }

        Product save = productRepository.save(product);


        //todo: send notification
        notificationClient.sendNotification(
                new NotificationRequest(product.getId(),String.format("product with id %s saved",save.getId()))
        );
        // todo: rabbitMQ
//        NotificationRequest notificationRequest = new NotificationRequest(product.getId(), String.format("product with id %s saved", save.getId()));
//        rabbitMQMessageProducer.publish(
//                notificationRequest,
//                "product-exchange",
//                "product.notification.routing-key"
//        );
        // todo: kafka
//        NotificationRequest notificationRequest = new NotificationRequest(product.getId(), String.format("product with id %s saved", save.getId()));
//        kafkaTemplate.send("product4","1", notificationRequest);
        return save;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> coupon = productRepository.findById(id);
        return coupon.orElseThrow();
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
        List<Product> p = productRepository.findAll();
        p.stream().sorted((o1, o2)->o1.getName().
                        compareTo(o2.getName())).
                collect(Collectors.toList());
        return productRepository.findAll();
    }

    /*public ResponseEntity<CouponResponse> getCoupon(String code) {
        return ResponseEntity.ok().body(builder.build()
                .get()
                .uri("http://DISCOUNT/api/v1/coupons/get-by-code/{code}", code)
                .retrieve()
                .bodyToMono(CouponResponse.class)
                .block());
    }*/
}
