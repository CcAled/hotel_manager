package com.zhou.interceptor;


import com.alibaba.fastjson.JSON;
import com.zhou.constants.Constants;
import com.zhou.exception.CodeMsg;
import com.zhou.pojo.Users;
import com.zhou.redis.AccessKey;
import com.zhou.redis.RedisService;
import com.zhou.service.UserService;
import com.zhou.utils.JSONResult;
import com.zhou.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class AccessInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    //拦截请求,controller调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            Users user = getUser(request, response);
//            if (user == null){
//
//            }
            UserContext.setUser(user);

            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();

            String key = request.getRequestURI();
            if(needLogin){
                if (user == null){
//                    render(response, CodeMsg.SESSION_ERROR);
//                    return false;
                    response.sendRedirect(request.getContextPath() + "/login");
                    return false;
                }
                key +="_" + user.getUserId();
            }else{
                //do nothing
            }
            //
            AccessKey ak = AccessKey.withExpire(seconds);
            Integer count = redisService.get(ak, key, Integer.class);
            if (count == null){//第一次访问
                redisService.set(ak, key, 1);
            }else if(count < maxCount){
                redisService.incr(ak, key);
            }else{
                render(response,new JSONResult(503,"访问太频繁"));
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, JSONResult jsonResult) throws Exception{
        response.setContentType("applicaiton/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(jsonResult);
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    private Users getUser(HttpServletRequest request, HttpServletResponse response){
        String paramToken = request.getParameter(Constants.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request,Constants.COOKI_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken)&& StringUtils.isEmpty(paramToken)){
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getByToken(response,token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals(cookiName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }

    //请求controller之后，渲染视图之前
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //视图渲染后
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
