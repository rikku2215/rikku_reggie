package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.dao.DishFlavorDao;
import com.rikku.reggie.dao.SetmealDishDao;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.entity.SetmealDish;
import com.rikku.reggie.service.SetmealDishService;
import com.rikku.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishDao, SetmealDish> implements SetmealDishService {

    @Autowired
    private SetmealDishDao setmealDishDao;

    @Override
    public List<SetmealDish> selectBatchByDishId(Long id) {
        List<SetmealDish> setmealDishes= setmealDishDao.selectBatchBySetmealId(id);
//        System.out.println("开始============");
//        System.out.println(dishFlavors);
//        System.out.println("结束============");
        return setmealDishes;
    }

    @Override
    public void removeBySetmealId(Long id) {
        setmealDishDao.deleteBySetmealId(id);
    }
}
