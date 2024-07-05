package com.example.oawebmanagement.filter;


import com.alibaba.fastjson.JSONObject;
import com.example.oawebmanagement.pojo.Result;
import com.example.oawebmanagement.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;//强转类型为http请求
        HttpServletResponse resp = (HttpServletResponse) response;//强转为http响应
        //这样就拿到了请求对象和响应对象

        //1.获取请求url。
        String url = req.getRequestURL().toString();//获取请求的url地址
        log.info("请求的url：{}",url);
        if(url.contains("login")){
            log.info("登陆操作，放行...");
            chain.doFilter(request,response);
            return;
        }
        if (url.contains("register")){
            log.info("注册操作，放行...");
            chain.doFilter(request,response);
            return;
        }

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("token");//拿到请求头里边的token信息（jwt令牌）,以字符串的形式存在jwt里
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){//判断是否有令牌，字符长度为空则返回未登录错误
            log.info("请求头token为空，返回未登录信息");//日志输出
            Result error = Result.error("NOT_LOGIN");//创建一个result对象，存错误信息
            //手动将对象转为json返回给前端,此时需要阿里巴巴包
            String notLogin = JSONObject.toJSONString(error);//把error对象转换成json字符串
            resp.getWriter().write(notLogin);//响应对象将结果返回给前端
            return;
        }//如果不为空则进行jwt令牌解析

        //5.解析token，如果解析失败，返回错误结果（未登录）。
        try {//捕获异常
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");

            Result error = Result.error("NOT_LOGIN");//创建一个result对象，存错误信息
            //手动将对象转为json返回给前端,此时需要阿里巴巴包
            String notLogin = JSONObject.toJSONString(error);//把error对象转换成json字符串
            resp.getWriter().write(notLogin);//响应对象将结果返回给前端
            return;
        }

        //6.放行。
        log.info("令牌合法，放行");
        chain.doFilter(request,response);

    }
}
