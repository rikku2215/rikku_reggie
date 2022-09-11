package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.dao.DishFlavorDao;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorDao,DishFlavor> implements DishFlavorService{
    @Autowired
    private DishFlavorDao dishFlavorDao;

    @Override
    public List<DishFlavor> selectBatchByDishId(Long id) {
        List<DishFlavor> dishFlavors= dishFlavorDao.selectBatchByDishId(id);
//        System.out.println("开始============");
//        System.out.println(dishFlavors);
//        System.out.println("结束============");
        return dishFlavors;
    }

    @Override
    public void deleteByDishId(Long id) {
        dishFlavorDao.deleteByDishId(id);
    }
}
