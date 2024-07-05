package com.example.oawebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Announcement（公告）实体类
public class Announcement {
    private int id;
    private String title;
    private String content;
    private int departmentId;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
    // 省略构造函数和 getter/setter 方法
}