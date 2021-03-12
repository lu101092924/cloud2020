package com.springcloud.service;

import com.springcloud.entity.Payment;

public interface PaymentService {
    /**
     * 根据id查询信息
     * @param id 支付id
     * @return
     * @throws Exception
     */
    Payment getPaymentById(Long id)throws Exception;

    /**
     * 新增信息
     * @param payment
     * @return
     * @throws Exception
     */
    int save(Payment payment)throws Exception;
}
