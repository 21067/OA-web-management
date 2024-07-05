package com.example.oawebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

// User（用户）实体类
public class User {
    private int id;
    private String username;
    private String password;
    private int roleId;
    private int departmentId;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
}
