package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.dao.SetmealDao;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.dto.SetmealDto;
import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.entity.Setmeal;
import com.rikku.reggie.entity.SetmealDish;
import com.rikku.reggie.service.SetmealDishService;
import com.rikku.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, Setmeal> implements SetmealService {
    @Autowired
    SetmealDishService setmealDishService;

    @Autowired
    SetmealDao setmealDao;
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        Long setmealId = setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.forEach((temp)->temp.setSetmealId(setmealId));
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto getSetmealDtoById(Long id) {
        SetmealDto setmealDto = new SetmealDto();
        Setmeal setmeal =  this.getById(id);
        List<SetmealDish> setmealDishes = setmealDishService.selectBatchByDishId(id);
        BeanUtils.copyProperties(setmeal,setmealDto);
        setmealDto.setSetmealDishes(setmealDishes);
        return setmealDto;
    }


    //1、修改套餐信息 2、删除套餐相关菜品 3、新增套餐相关菜品(要给setmeal_id赋值)
    @Transactional
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        this.updateById(setmealDto);
        //套餐id
        Long setmealId = setmealDto.getId();
        //删除
        setmealDishService.removeBySetmealId(setmealId);
        //获取套餐相关菜品
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        dishes.forEach((temp)->temp.setSetmealId(setmealId));
        //新增套餐相关菜品
        setmealDishService.saveBatch(dishes);
    }

    @Override
    public List<SetmealDto> list(Long Categroyid) {
        List<Setmeal> setmeals = setmealDao.list(Categroyid);
        List<SetmealDto> setmealDtos = new ArrayList<>();
        for (Setmeal setmeal : setmeals){
            Long id = setmeal.getId();
            SetmealDto setmealDto = this.getSetmealDtoById(id);
            setmealDtos.add(setmealDto);
        }
        return setmealDtos;
    }


}
