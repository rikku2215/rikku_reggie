package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
//    @Select("select * from ")
//    List<ShoppingCart> list();
    @Select("SELECT * FROM shopping_cart where name = #{name} and user_id = #{uid}")
    ShoppingCart getByNameUser(String name,Long uid);

    @Select("SELECT * FROM shopping_cart where dish_id = #{id} and user_id = #{uid}")
    ShoppingCart getByDishId(Long id,Long uid);

    @Select("SELECT * FROM shopping_cart where setmeal_id = #{id} and user_id = #{uid}")
    ShoppingCart getBySetmealId(Long id,Long uid);

    @Delete("delete from shopping_cart where user_id = #{uid}")
    void clean(Long uid);

    @Select("select * from shopping_cart where user_id = #{uid}")
    List<ShoppingCart> listByUid(Long uid);
}
