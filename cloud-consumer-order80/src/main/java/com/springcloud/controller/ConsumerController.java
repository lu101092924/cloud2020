package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    //private static final String PAYMENT_URL = "http://127.0.0.1:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    /**
     * 保存支付记录
     * @param payment
     * @return
     */
    @PostMapping("/consumer/payment/save")
    public CommonResult<Payment> paymentSave(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommonResult.class);
    }

    /**
     * 根据id查询支付记录
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/"+id,CommonResult.class);
    }

    /**
     * 根据id查询支付记录
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(444,"操作失败");
        }
    }
}
