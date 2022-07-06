package com.syrila.controller;

import com.syrila.entity.Product;
import com.syrila.mapper.ProductMapper;
import com.syrila.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/products/{pid}")
    public ResultVO find(@PathVariable Integer pid){
        Product product = productMapper.selectById(pid);
        return product == null ? ResultVO.failure("查询失败！") : ResultVO.success("查询成功！", product);
    }

    @PostMapping("/products/testObjectParam")
    public ResultVO testObjectParam(@RequestBody Product product, HttpServletRequest request){
        return ResultVO.success(request.getServerPort()+"成功接收对象",product);
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
