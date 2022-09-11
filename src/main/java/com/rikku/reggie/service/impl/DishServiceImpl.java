package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.dao.DishDao;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.service.DishFlavorService;
import com.rikku.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements DishService {

    @Autowired
    private DishDao dishDao;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach((temp)->temp.setDishId(dishId));
        dishFlavorService.saveBatch(flavors);
    }
    @Transactional
    @Override
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach((temp)->temp.setDishId(dishId));
        dishFlavorService.saveOrUpdateBatch(flavors);
    }


    @Override
    public DishDto getDishDtoById(Long id) {
        DishDto dishDto = new DishDto();
        Dish dish =  this.getById(id);
        List<DishFlavor> dishFlavors = dishFlavorService.selectBatchByDishId(id);
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(dishFlavors);
        return dishDto;
    }

    @Override
    public List<Dish> list(Long categoryId) {
        List<Dish> dishes = dishDao.list(categoryId);
        return dishes;
    }


}
