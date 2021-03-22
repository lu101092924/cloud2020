package com.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName()+" paymentInfo_OK，id:" + id +",O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int timeNumber = 10/0;
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池:" + Thread.currentThread().getName() + " 调用支付接口异常或超时,id:" + id + "\t" +
                "o(╥﹏╥)o";
    }

    //====服务熔断
    //在10次请求中，10秒钟之类错误的请求达到60%断路器进入open状态
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开始短路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求总阀值数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//快照时间窗，时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //错误百分比阀值
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String number = IdUtil.fastSimpleUUID();
        return Thread.currentThread().getName() + "\t调用成功，流水号："+number;
    }

    public String paymentCircuitBreakerFallback(Integer id){
        return "*****id 不能为负数,请稍后重试 o(╥﹏╥)o";
    }
}
