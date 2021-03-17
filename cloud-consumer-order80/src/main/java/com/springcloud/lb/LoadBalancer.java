package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 获取一个服务实例
     * @param instances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> instances);
}
