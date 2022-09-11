package com.rikku.reggie.dto;

import com.rikku.reggie.entity.Setmeal;
import com.rikku.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
