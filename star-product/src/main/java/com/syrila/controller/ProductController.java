package com.syrila.controller;

import com.syrila.entity.Product;
import com.syrila.mapper.ProductMapper;
import com.syrila.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
