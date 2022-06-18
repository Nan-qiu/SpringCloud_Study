package com.syrila.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syrila.entity.Product;
import com.syrila.mapper.ProductMapper;
import com.syrila.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
