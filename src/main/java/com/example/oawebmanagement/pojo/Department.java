package com.example.oawebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Department（部门）实体类
public class Department {
    private int id;
    private String departmentName;
    private int managerId;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
}