package com.syrila.controller;

import com.syrila.entity.Order;
import com.syrila.entity.Product;
import com.syrila.mapper.OrderMapper;
import com.syrila.service.OrderService;
import com.syrila.service.ProductService;
import com.syrila.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;


    @PostMapping("/orders/{pid}/{number}")
    public ResultVO create(@PathVariable Integer pid, @PathVariable Integer number){
        Order order = orderService.create(pid, number);
        return order == null ? ResultVO.failure("下单失败！") : ResultVO.success("下单成功！", order);
    }


    @PostMapping("/orders/lb")
    public ResultVO lb(){
//        RestTemplate restTemplate = new RestTemplate();
//        List<ServiceInstance> instances = discoveryClient.getInstances("star-product");
//        String uri = instances.get(0).getUri().toString();
//        System.out.println("uri = " + uri);
//        return restTemplate.getForObject( "http://star-product/products/1", ResultVO.class);
        return productService.find(1);
    }


    @GetMapping("/orders/testObjectParam")
    public ResultVO testObjectParam(){
        Product product = new Product();
        product.setPid(3);
        product.setPname("猕猴桃");
        product.setPrice(20d);
        product.setStock(10);
        return productService.testObjectParam(product);
    }


    @GetMapping("/orders/find/{oid}")
    public ResultVO findOrderById(@PathVariable Integer oid){
        Order order = orderService.findOrderById(oid);
        return ResultVO.success("查询成功！",order);
    }


}
