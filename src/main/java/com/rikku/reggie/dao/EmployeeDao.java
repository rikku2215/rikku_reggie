package com.rikku.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rikku.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {

}
