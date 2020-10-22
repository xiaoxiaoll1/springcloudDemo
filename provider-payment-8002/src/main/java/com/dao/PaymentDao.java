package com.dao;

import com.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper

public interface PaymentDao {

     int create(Payment payment);

     Payment getPaymentById(@Param("id") Long id);

}
