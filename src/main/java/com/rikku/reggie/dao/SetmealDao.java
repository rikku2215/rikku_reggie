package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDao extends BaseMapper<Setmeal> {
    @Select("select * FROM setmeal WHERE category_id=#{categoryId}")
    List<Setmeal> list(Long categoryId);
}
