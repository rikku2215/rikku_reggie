package com.rikku.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rikku.reggie.common.R;
import com.rikku.reggie.dto.DishDto;
import com.rikku.reggie.dto.SetmealDto;
import com.rikku.reggie.entity.Category;
import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.Setmeal;
import com.rikku.reggie.entity.SetmealDish;
import com.rikku.reggie.service.CategoryService;
import com.rikku.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page(page,pageSize);
        Page<SetmealDto> setmealDtoPage = new Page();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,queryWrapper);
        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            setmealDto.setCategoryName(categoryName);
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(list);

        return R.success(setmealDtoPage);
    }

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("套餐添加成功");
    }

    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.getSetmealDtoById(id);
        return R.success(setmealDto);
    }

    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithDish(setmealDto);
        return R.success("修改套餐成功");
    }

    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable Integer status,Long[] ids){
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        for(Long id : ids){
            setmeal.setId(id);
            setmealService.updateById(setmeal);
        }
        return R.success("启/停售成功");
    }

    @GetMapping("/list")
    public R<List<SetmealDto>> list(Setmeal setmeal){
        Long categoryId = setmeal.getCategoryId();
        //查询setmealDto集合
        List<SetmealDto> setmealDtos = setmealService.list(categoryId);
        return R.success(setmealDtos);
    }

}
