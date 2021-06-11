package com.alididi.controller;

import com.alididi.pojo.Order;
import com.alididi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id, HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()){

            System.out.println(headerNames.nextElement());
        }

        return orderService.selectOrderById(id);

    }

}