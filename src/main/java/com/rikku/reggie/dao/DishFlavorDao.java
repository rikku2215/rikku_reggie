package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorDao extends BaseMapper<DishFlavor> {
    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> selectBatchByDishId(Long id);

    @Delete("delete from dish_flavor where dish_id=#{id}")
    void deleteByDishId(Long id);
}
