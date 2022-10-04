package com.rikku.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rikku.reggie.common.R;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.entity.Category;
import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.DishFlavor;
import com.rikku.reggie.service.CategoryService;
import com.rikku.reggie.service.DishFlavorService;
import com.rikku.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Dish> pageInfo = new Page(page,pageSize);
        Page<DishDto> dishDtoPage = new Page();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,queryWrapper);
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"1";
        redisTemplate.delete(key);
        return R.success("新增菜品成功");
    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id){
        DishDto dishDto = dishService.getDishDtoById(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"1";
        redisTemplate.delete(key);
        return R.success("修改菜品成功");
    }

//    @GetMapping("/list")
//    public R<List<Dish>> list(Dish dish){
//        Long categoryId = dish.getCategoryId();
//        List<Dish> dishes = dishService.list(categoryId);
//        return R.success(dishes);
//    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        String key = "dish_"+dish.getCategoryId()+dish.getStatus();
        List<DishDto> dishDtoList;
        log.info("222222");
        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);
        log.info("33333");
        if (dishDtoList!=null){
            //在缓存中查得到
            return R.success(dishDtoList);
        }

        Long categoryId = dish.getCategoryId();
        //查找Dish集合
        List<Dish> dishes = dishService.list(categoryId);
        //新建空DishDto集合
        List<DishDto> dishDtos = new ArrayList<>();
        //新建空DishDto作为容器
        //遍历Dish集合
        dishes.forEach((temp)->{
            DishDto dishDto = new DishDto();
            //获取Dish的id
            Long dishId = temp.getId();
            //通过Dish的id得到对应的口味集合
            List<DishFlavor> dishFlavors = dishFlavorService.selectBatchByDishId(dishId);
            //将当前dish的信息复制给dishDto
            BeanUtils.copyProperties(temp,dishDto);
            //为dishDto设置口味集合
            dishDto.setFlavors(dishFlavors);
            //将dishDto插入dishDto集合
            dishDtos.add(dishDto);

        });

        //存入redis，过期时间5分钟
        redisTemplate.opsForValue().set(key,dishDtos,5, TimeUnit.MINUTES);

        return R.success(dishDtos);
    }


    //@PathVariable+@RequestParam数组
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable Integer status,Long[] ids){
        Dish dish = new Dish();
        dish.setStatus(status);
        for(Long id : ids){
            dish.setId(id);
            dishService.updateById(dish);
        }

        return R.success("启/停售成功");
    }

    @DeleteMapping
    public R<String> delete(Long[] ids){
        DishFlavor dishFlavor = new DishFlavor();
        //遍历传入的DishId
        for (Long id : ids){
            //删除菜品相关的口味，利用的是DishId
            dishFlavor.setDishId(id);
            dishFlavorService.deleteByDishId(id);
            //删除菜品
            dishService.removeById(id);
        }

        return R.success("删除菜品成功");
    }

}
