package com.example.oawebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentTransferRequest {
    private int id;
    private int userId;
    private int currentDepartmentId;//当前部门id
    private int targetDepartmentId;//目标部门id
    private LocalDateTime applicationDate;//申请日期
    private String statusOut;//转出状态
    private String statusIn;//转入状态
    private String reason;
    private LocalDateTime updatedAt; //修改时间
}
