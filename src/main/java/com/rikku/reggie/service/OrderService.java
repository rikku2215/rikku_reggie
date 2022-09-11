package com.rikku.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rikku.reggie.entity.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
