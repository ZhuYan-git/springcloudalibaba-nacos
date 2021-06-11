package com.alididi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.alididi.pojo.Order;
import com.alididi.pojo.Product;
import com.alididi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private RestTemplate restTemplate;
//方式一： 获取服务列表，在从服务列表获取制定的服务
//    @Autowired
//    private DiscoveryClient discoveryClient;

//方式二：rabbin负载均衡器，可以根据指定的服务名称直接获取服务
   // @Autowired
   // private LoadBalancerClient loadBalancerClient;



    /**
     * 查询订单详情信息
     * @param id
     * @return
     */
    @Override
    public Order selectOrderById(Integer id) {
        log.info("订单服务查询订单信息...");
        return new Order(id, "order-001", "中国", 22788D,
                selectProductListByDiscoveryClient());
    }

    public List<Product> selectProductListByDiscoveryClient() {
        //设置请求服务连接
        String url ="http://product-service/product/list";
        log.info("订单服务调用商品服务");
        log.info("从注册中心获取的到的商品服务地址:"+url);


        ResponseEntity<List<Product>> res = restTemplate.exchange(
                 url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
                );
        //获取数据
        List<Product> list = res.getBody();

        return list;
    }

//    /**
//     * 查询所有所有商品信息
//     * @return
//     */
//    private List<Product> selectProductListByDiscoveryClient() {
//
//
//        StringBuffer sb = null;
////方式一
////        // 获取服务列表
////        List<String> serviceIds = discoveryClient.getServices();
////        if (CollectionUtils.isEmpty(serviceIds))
////            return null;
////
////
////        // 根据服务名称获取服务
////        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-service");
////        if (CollectionUtils.isEmpty(serviceInstances))
////            return null;
//
//
//        // 构建远程服务调用地址
//        //方式二，直接根据名称获取即可
//        ServiceInstance si = loadBalancerClient.choose("product-service");
//        sb = new StringBuffer();
//        sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
//        log.info("订单服务调用商品服务...");
//        log.info("从注册中心获取到的商品服务地址为：{}", sb.toString());
//
//        // 远程调用服务
//        // ResponseEntity: 封装了返回数据
//        ResponseEntity<List<Product>> response = restTemplate.exchange(
//                sb.toString(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Product>>() {});
//        log.info("商品信息查询结果为：{}", JSON.toJSONString(response.getBody()));
//        return response.getBody();
//    }
}
