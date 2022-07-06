package com.syrila.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.syrila.utils.ResultVO;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType(){
        return FilterConstants.PRE_TYPE;
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
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String token = request.getParameter("token");
        if (!"admin".equals(token)){
            //设置未认证的响应码
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");

            try {
                ResultVO failure = ResultVO.failure("未认证!");
                String json = JSON.toJSONString(failure);
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //不放行
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
