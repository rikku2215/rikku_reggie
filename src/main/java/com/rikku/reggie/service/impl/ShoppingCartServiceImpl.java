package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.dao.ShoppingCartDao;
import com.rikku.reggie.entity.ShoppingCart;
import com.rikku.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService {
    @Autowired
    ShoppingCartDao shoppingCartDao;
    @Override
    public ShoppingCart getByNameUser(String name,Long uid) {
        return shoppingCartDao.getByNameUser(name,uid);
    }

    @Override
    public ShoppingCart getByDishId(Long id, Long uid) {
        return shoppingCartDao.getByDishId(id,uid);
    }

    @Override
    public ShoppingCart getBySetmealId(Long id, Long uid) {
        return shoppingCartDao.getBySetmealId(id,uid);
    }

    @Override
    public void clean(Long uid) {
        shoppingCartDao.clean(uid);
    }

    @Override
    public List<ShoppingCart> listByUid(Long uid) {
        return shoppingCartDao.listByUid(uid);
    }
}
