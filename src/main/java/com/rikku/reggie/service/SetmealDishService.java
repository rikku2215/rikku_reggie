package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService extends IService<SetmealDish> {
    List<SetmealDish> selectBatchByDishId(Long id);
    void removeBySetmealId(Long id);
}
