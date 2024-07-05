package com.example.oawebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
// LeaveRequest（请假申请）实体类
public class LeaveRequest {
    private int id;
    private int userId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间

}