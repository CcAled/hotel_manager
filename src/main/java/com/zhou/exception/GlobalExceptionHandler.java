package com.zhou.exception;

import com.zhou.utils.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JSONResult exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return JSONResult.errorMap(ex.getCm());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            String msg = ex.getMessage();
            return JSONResult.errorMap(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else{
            return JSONResult.errorMap(CodeMsg.SERVER_ERROR);
        }
    }
}
