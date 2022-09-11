package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishDao extends BaseMapper<Dish> {

    @Select("select * FROM dish WHERE category_id=#{categoryId}")
    List<Dish> list(Long categoryId);
}
