package com.syrila.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "star_product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer pid;
    private String pname;
    private Double price;
    private Integer stock;
}
