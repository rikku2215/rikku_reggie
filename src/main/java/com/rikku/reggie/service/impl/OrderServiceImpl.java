package com.rikku.reggie.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rikku.reggie.common.BaseContext;
import com.rikku.reggie.common.CustomException;
import com.rikku.reggie.dao.OrderDao;
import com.rikku.reggie.entity.*;
import com.rikku.reggie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements OrderService {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Override
    public void submit(Orders orders) {
        Long uid = BaseContext.getCurrentId();
        List<ShoppingCart> shoppingCarts = shoppingCartService.listByUid(uid);
        if (shoppingCarts == null || shoppingCarts.size()==0){
            throw new CustomException("购物车为空，不能下单");
        }
        User user = userService.getById(uid);
        AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
        if (addressBook == null){
            throw new CustomException("用户地址有误，不能下单");
        }
        Long orderId = IdWorker.getId();
        //AtomicInteger:原子变量
        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item)->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(uid);
        orders.setNumber((String.valueOf(orderId)));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName()
            + (addressBook.getCityName()==null ? "": addressBook.getCityName())
            + (addressBook.getDistrictName()==null?"": addressBook.getDistrictName())
            + (addressBook.getDetail()==null ? "" : addressBook.getDetail())));



        this.save(orders);
        orderDetailService.saveBatch(orderDetails);

        shoppingCartService.clean(uid);

    }
}
