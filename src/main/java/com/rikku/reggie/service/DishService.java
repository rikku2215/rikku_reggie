package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.entity.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishService extends IService<Dish> {

    void saveWithFlavor(DishDto dishDto);
    void updateWithFlavor(DishDto dishDto);
    DishDto getDishDtoById(Long id);
    List<Dish> list(Long categoryId);
}
