package com.rikku.reggie.dto;

import com.rikku.reggie.entity.Dish;
import com.rikku.reggie.entity.DishFlavor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
