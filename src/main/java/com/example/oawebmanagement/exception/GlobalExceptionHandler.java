package com.example.oawebmanagement.exception;

//全局异常处理器
//当有错误时，把获取的错误以和前端约定的json格式返回给前端
import com.example.oawebmanagement.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//包含了可以把result转换为json格式的注解
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//表示所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();//捕获到异常后，输出异常的堆栈信息
        return Result.error("对不起操作失败，请联系管理员");//返回信息
    }

}
