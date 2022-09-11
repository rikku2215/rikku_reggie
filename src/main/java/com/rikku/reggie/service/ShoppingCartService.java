package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {
    ShoppingCart getByNameUser(String name,Long uid);

    ShoppingCart getByDishId(Long id,Long uid);

    ShoppingCart getBySetmealId(Long id,Long uid);

    void clean(Long uid);

    List<ShoppingCart> listByUid(Long uid);
}
