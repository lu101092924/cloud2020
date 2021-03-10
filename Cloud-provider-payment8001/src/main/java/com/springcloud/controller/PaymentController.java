package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 新增记录
     * @param payment
     * @return
     */
    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        try{
            int result = paymentService.save(payment);
            log.info("插入结果:"+result);
            if( result == 1){
                return new CommonResult(200,"添加成功",result);
            }else{

            } return new CommonResult(4444,"添加失败",result);
        }catch (Exception e){
            return new CommonResult(500,e.getMessage(),null);
        }
    }

    /**
     * 根据id查询支付记录
     * @param id
     * @return
     */
    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        try{
            Payment payment = paymentService.getPaymentById(id);
            if(payment != null){
                return new CommonResult(200,"查询成功",payment);
            }else{
                return new CommonResult(4444,"未查询到相应记录，ID为"+id,null);
            }
        }catch (Exception e){
            return new CommonResult(500,e.getMessage(),null);
        }
    }
}
