package com.syrila.service;

import com.syrila.entity.Product;
import com.syrila.utils.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "star-product")
public interface ProductService {
    @GetMapping("/products/{pid}")
    ResultVO find(@PathVariable("pid") Integer pid);
    @PostMapping("/products/testObjectParam")
    ResultVO testObjectParam(@RequestBody Product product);
}
