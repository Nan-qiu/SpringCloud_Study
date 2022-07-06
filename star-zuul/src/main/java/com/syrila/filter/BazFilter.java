package com.syrila.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

//@Component
public class BazFilter extends ZuulFilter {
    @Override
    public String filterType(){
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder(){
        return 0;
    }

    @Override
    public boolean shouldFilter(){
        return false;
    }

    @Override
    public Object run() throws ZuulException{
        System.out.println("BazFilter.run");
        return null;
    }
}
