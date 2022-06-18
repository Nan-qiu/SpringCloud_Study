package com.syrila.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private Integer code;
    private String msg;
    private Object data;

    public ResultVO(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResultVO success(){
        return success("操作成功");
    }

    public static ResultVO success(String msg){
        return success(msg, null);
    }

    public static ResultVO success(Object data){
        return success("操作成功", data);
    }

    public static ResultVO success(String msg, Object data){
        ResultVO success = new ResultVO();
        success.setCode(HttpStatus.OK.value());
        success.setMsg(msg);
        success.setData(data);
        return success;
    }

    public static ResultVO failure(){
        return failure("操作失败");
    }

    public static ResultVO failure(Object data){
        return failure("操作失败", data);
    }

    public static ResultVO failure(String msg){
        return failure(msg, null);
    }

    public static ResultVO failure(String msg, Object data){
        ResultVO failure = new ResultVO();
        failure.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        failure.setMsg(msg);
        failure.setData(data);
        return failure;
    }
}
