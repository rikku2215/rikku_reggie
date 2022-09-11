package com.rikku.reggie.controller;

import com.rikku.reggie.common.BaseContext;
import com.rikku.reggie.common.R;
import com.rikku.reggie.entity.ShoppingCart;
import com.rikku.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public R<List> list(){
        Long uid = BaseContext.getCurrentId();
        //要查询当前用户的购物车集合
        List<ShoppingCart> shoppingCarts = shoppingCartService.listByUid(uid);
        return R.success(shoppingCarts);
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart){
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        ShoppingCart getShoppingCart= shoppingCartService.getByNameUser(shoppingCart.getName(),userId);

        if ( getShoppingCart != null){
            Integer number = getShoppingCart.getNumber() + 1;
            getShoppingCart.setNumber(number);
            shoppingCartService.updateById(getShoppingCart);
            return R.success("新增购物车成功");
        }
        else if (getShoppingCart == null){
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            return R.success("新增购物车成功");
        }
        else{
            return R.error("新增购物车失败");
        }
    }

    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){
        Long userId = BaseContext.getCurrentId();
        if (shoppingCart.getDishId()!=null){
            Long dishId = shoppingCart.getDishId();
            ShoppingCart getShoppingCart= shoppingCartService.getByDishId(dishId,userId);
            if (getShoppingCart.getNumber()==1){
                shoppingCartService.removeById(getShoppingCart);
            }
            else{
                getShoppingCart.setNumber(getShoppingCart.getNumber()-1);
                shoppingCartService.updateById(getShoppingCart);
            }
        }
        else{
            Long setmealId = shoppingCart.getSetmealId();
            ShoppingCart getShoppingCart= shoppingCartService.getBySetmealId(setmealId,userId);
            if (getShoppingCart.getNumber()==1){
                shoppingCartService.removeById(getShoppingCart);
            }
            else{
                getShoppingCart.setNumber(getShoppingCart.getNumber()-1);
                shoppingCartService.updateById(getShoppingCart);
            }
        }
        return R.success("删除成功");
    }

    @DeleteMapping("/clean")
    public R<String> clean(){
        Long userId = BaseContext.getCurrentId();
        shoppingCartService.clean(userId);
        return R.success("清除购物车成功");
    }

}
