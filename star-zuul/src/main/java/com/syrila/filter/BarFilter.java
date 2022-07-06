package com.syrila.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

//@Component
public class BarFilter extends ZuulFilter {
    @Override
    public String filterType(){
        return FilterConstants.ROUTE_TYPE;
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
        System.out.println("BarFilter.run");
        return null;
    }
}
