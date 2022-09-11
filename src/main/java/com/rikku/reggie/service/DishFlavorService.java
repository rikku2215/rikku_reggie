package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService extends IService<DishFlavor> {
     List<DishFlavor> selectBatchByDishId(Long id);

     void deleteByDishId(Long id);
}
