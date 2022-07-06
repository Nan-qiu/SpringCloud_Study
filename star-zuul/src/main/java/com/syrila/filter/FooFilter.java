package com.syrila.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

//@Component
public class FooFilter extends ZuulFilter {
    @Override
    //指定过滤器类型 前置。。。
    public String filterType(){
        return FilterConstants.PRE_TYPE;
    }

    @Override
    //指定过滤器优先级，越小越优先
    public int filterOrder(){
        return 0;
    }

    @Override
    //是否启用过滤器
    public boolean shouldFilter(){
        return false;
    }

    @Override
    public Object run() throws ZuulException{
        System.out.println("FooFilter.run");
        return null;
    }
}
