package com.example.oawebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Work 工作内容实体类
public class Work {
    private int id;
    private String title;
    private String content;
    private String status;
    private int groupId;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
}