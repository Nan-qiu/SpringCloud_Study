package com.syrila.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syrila.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper extends BaseMapper<User> {
}
