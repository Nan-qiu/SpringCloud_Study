package com.syrila.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
import java.util.Random;

@Service
//这个注解写在类上，这个类里面所有@HystrixCommand的方法共享相同的配置
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
        }
)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @HystrixCommand
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

    @HystrixCommand(
            fallbackMethod = "buildFallbackOrder",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolKey = "findOrderById",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "10"),//当前舱壁分得的线程数量
                    @HystrixProperty(name = "maxQueueSize",value = "5")//等待队列的容量
            }
            )
    @Override
    public Order findOrderById(Integer oid){
        randomTimeOut(); // 1/3 chance to timeout
        System.out.println(Thread.currentThread().getName());

        return orderMapper.selectById(oid);
    }

    private Order buildFallbackOrder(Integer oid){
        Order order = new Order();
        order.setPid(-1);
        order.setPname("后备数据");
        order.setUsername("后备数据");
        order.setNumber(-1);
        order.setPrice(-1d);
        order.setUid(-1);
        order.setOid(-1);
        return order;
    }


    private void randomTimeOut(){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3)*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}