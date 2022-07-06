package com.syrila.config;

import com.alibaba.fastjson.JSON;
import com.syrila.utils.ResultVO;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ProductFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute(){
        //为star-product服务提供fallback功能
        //网关的fallback方案只管超时，不管其他错误
        return "star-product";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException{
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException{
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException{
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close(){

            }

            @Override
            public InputStream getBody() throws IOException{
                ResultVO failure = ResultVO.failure("商品服务不可用，请稍后再试！");
                String s = JSON.toJSONString(failure);
                ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
                return in;
            }

            @Override
            public HttpHeaders getHeaders(){
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
