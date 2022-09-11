package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.dto.SetmealDto;
import com.rikku.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    SetmealDto getSetmealDtoById(Long id);
    void updateWithDish(SetmealDto setmealDto);
    List<SetmealDto> list (Long id);
}
