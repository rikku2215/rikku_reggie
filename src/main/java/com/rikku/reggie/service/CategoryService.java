package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
