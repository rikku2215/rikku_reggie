package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishDao extends BaseMapper<SetmealDish> {
    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> selectBatchBySetmealId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id=#{id}")
    void deleteBySetmealId(Long id);
}
