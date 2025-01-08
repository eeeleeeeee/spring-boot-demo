package com.ele.demo.controller;

import com.ele.demo.datasource.product.ProductRepository;
import com.ele.demo.datasource.product.pojo.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> save() {
        log.info("save");
        productRepository.save(Product.builder().name("ele_t-shirt").price(77).build());
        return new ResponseEntity<>("save success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        log.info("get");
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
}
