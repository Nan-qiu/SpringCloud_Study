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
@TableName(value = "star_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer oid;
    private Integer uid;
    private String username;

    private Integer pid;
    private String pname;
    private Double price;

    private Integer number; // 购买数量
}
