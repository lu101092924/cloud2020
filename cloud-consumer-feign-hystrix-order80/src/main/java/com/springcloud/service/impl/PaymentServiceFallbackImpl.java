package com.springcloud.service.impl;

import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallbackImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PaymentServiceFallbackImpl fall back-paymentInfo_OK ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentServiceFallbackImpl fall back-paymentInfo_TimeOut";
    }
}
