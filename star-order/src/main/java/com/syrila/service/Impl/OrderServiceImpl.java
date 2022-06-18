package com.syrila.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syrila.entity.Order;
import com.syrila.entity.Product;
import com.syrila.mapper.OrderMapper;
import com.syrila.service.OrderService;
import com.syrila.service.ProductService;
import com.syrila.utils.ResultVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @Override
    public Order create(Integer pid, Integer number){
        //RestTemplate用于发起http请求
        //RestTemplate restTemplate = new RestTemplate();
        //ResultVO resultVO = restTemplate.getForObject("http://localhost:8081/products/" + pid, ResultVO.class);

        //Feign的远程调用
        ResultVO resultVO = productService.find(pid);

        Integer code = resultVO.getCode();
        if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()) return null;

        Map map = (Map) resultVO.getData();
        Product product = new Product();
        try {
            BeanUtils.populate(product, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //订单入库
        Order order = new Order();
        order.setUid(1);
        order.setUsername("老王");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPrice(product.getPrice());
        order.setNumber(number);

        orderMapper.insert(order);

        return order;
    }
}