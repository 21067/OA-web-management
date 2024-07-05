package com.example.oawebmanagement.controller;
import com.example.oawebmanagement.mapper.EmpMapper;
import com.example.oawebmanagement.pojo.Result;
import com.example.oawebmanagement.pojo.User;
import com.example.oawebmanagement.service.EmpService;
import com.example.oawebmanagement.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    //注入service
    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User emp)//@RequestBody会将json格式的数据封装到实体类中
    {
     log.info("员工登陆：()",emp);
     User e = empService.login(emp);

     //登陆成功，生成令牌，下发令牌
         if(e != null){

             //生成一个集合，一会要当参数传入
             Map<String,Object> claims = new HashMap<>();
             claims.put("id",e.getId());
             claims.put("username", e.getUsername());;

             String jwt = JwtUtils.generateJwt(claims);//jwt中包含了当前登陆的员工的信息
             return Result.success(jwt);
         }
     //登录失败，返回错误信息
     return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册:()",user);
        empMapper.insert(user);
        return Result.success();
    }

}
