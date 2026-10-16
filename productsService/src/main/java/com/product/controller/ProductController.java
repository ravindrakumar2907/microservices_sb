package com.product.controller;

import com.product.dto.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;


    public ProductController() {}

    @GetMapping
    public List<Products.Product> getProducts(){
        Products resp = restTemplate.getForObject("https://dummyjson.com/products", Products.class);
        return resp == null ? java.util.List.of() : resp.products();
    }

    @GetMapping("/rc")
    public List<Products.Product> getProductUsingRC(){
        Products resp = restClient.get().
                uri("https://dummyjson.com/products")
                .retrieve()
                .body(Products.class);
        return resp == null ? java.util.List.of() : resp.products();
    }

}