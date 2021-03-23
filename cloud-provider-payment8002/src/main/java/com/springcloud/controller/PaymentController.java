package com.springcloud.controller;

import com.springcloud.entity.CommonResult;
import com.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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
                return new CommonResult(200,"添加成功,serverPort="+serverPort,result);
            }else{

            } return new CommonResult(4444,"添加失败,serverPort="+serverPort,result);
        }catch (Exception e){
            return new CommonResult(500,e.getMessage(),null);
        }
    }

    /**
     * 根据id查询支付记录
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        try{
            Payment payment = paymentService.getPaymentById(id);
            if(payment != null){
                return new CommonResult(200,"查询成功,serverPort="+serverPort,payment);
            }else{
                return new CommonResult(4444,"未查询到相应记录，serverPort="+serverPort+",ID为"+id,null);
            }
        }catch (Exception e){
            return new CommonResult(500,e.getMessage(),null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object getDiscoveryInfo(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名称："+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务实例具体信息："+instance.getInstanceId()+ "\t服务地址："+instance.getHost()+instance.getPort()+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
