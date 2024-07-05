package com.example.oawebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
// JoinRequest（加入部门申请）实体类
public class JoinRequest {
    private int id;
    private int userId;
    private int departmentId;
    private String status;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
}
