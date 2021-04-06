package com.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entity.CommonResult;

public class CustomerBlockHandler {
    //注意该方法一定要加static关键字，否则不能识别，返回值类型为调用的方法的返回值类型，参数为调用方法的参数，但是要多一个BlockException exception
    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(444, "自定义限流处理信息....CustomerBlockHandler----1111");

    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(444, "自定义限流处理信息....CustomerBlockHandler---2222");

    }
}
