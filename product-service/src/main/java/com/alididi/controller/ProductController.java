package com.alididi.controller;

import com.alididi.pojo.Product;
import com.alididi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    public String portOfter;


    @Value("${server.port}")
    public String port;

    @Autowired
    private ProductService productService;


    @RequestMapping("/list")
    public List<Product> selectProductList(){


        System.out.println(port);
        return productService.selectProductList();
    }

}
