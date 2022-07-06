package com.syrila.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class DynamicFilter extends ZuulFilter {
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
        RequestContext ctx = RequestContext.getCurrentContext();
        //改变路由地址，只能在当前指定的微服务下，如当前只能改变star-product微服务的url
        ctx.put(FilterConstants.REQUEST_URI_KEY,"star-product/products/2");
        return null;
    }
}
