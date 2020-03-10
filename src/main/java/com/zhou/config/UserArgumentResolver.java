package com.zhou.config;

import com.zhou.interceptor.UserContext;
import com.zhou.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


//1.SpringMVC解析器用于解析request请求参数并绑定数据到Controller的入参上。
//2.自定义一个参数解析器需要实现HandlerMethodArgumentResolver接口，重写supportsParameter和resolveArgument方法，配置文件中加入resolver配置。(WebConfig实现)
//3.如果需要多个解析器同时生效需要在一个解析器中对其他解析器做兼容。

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    //判断是否支持要转换的参数类型
    public boolean supportsParameter(MethodParameter methodParameter) {
        //如果函数包含我们的自定义注解，那就走resolveArgument()函数
        return (methodParameter.getParameterType() == Users.class) || (methodParameter.getParameterAnnotation(CurrentUser.class) != null) ;
    }

    @Override
    //当支持后进行相应的转换
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return UserContext.getUser();

    }
}
