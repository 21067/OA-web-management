package com.example.oawebmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//在启动项里表示开启了对javaweb组件的支持
@SpringBootApplication
public class OaWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaWebManagementApplication.class, args);
    }

}
