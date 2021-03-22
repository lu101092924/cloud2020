package com.springcloud.service;

public interface PaymentService {
    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id);

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_TimeOut(Integer id);

    /**
     *
     * @param id
     * @return
     */
    public String paymentCircuitBreaker(Integer id);
}
