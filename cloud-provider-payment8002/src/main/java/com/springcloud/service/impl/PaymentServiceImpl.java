package com.springcloud.service.impl;

import com.springcloud.entity.Payment;
import com.springcloud.mapper.PaymentMapper;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public Payment getPaymentById(Long id) throws Exception {
        return this.paymentMapper.getPaymentById(id);
    }

    @Override
    public int save(Payment payment) throws Exception {
        return this.paymentMapper.save(payment);
    }
}
