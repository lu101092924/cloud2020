package com.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_baidu_guonei",r -> r.path("/guonei").uri("https://news.baidu.com/guonei"));
        return routes.build();
    }

    @Bean
    public RouteLocator routeLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_baidu_guoji",r -> r.path("/guoji").uri("https://news.baidu.com/guoji"));
        return routes.build();
    }
}
