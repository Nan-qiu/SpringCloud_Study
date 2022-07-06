package com.syrila.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syrila.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends IService<Order> {
    Order create(Integer pid,Integer number);

    Order findOrderById(Integer oid);

}
