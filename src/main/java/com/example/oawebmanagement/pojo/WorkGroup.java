package com.example.oawebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGroup {
    private int id;
    private String groupName;
    private String departmentId;
    private int leaderId;
    private LocalDateTime createdAt; //创建时间
    private LocalDateTime updatedAt; //修改时间
    private int workId;
    private int member1Id;
    private int member2Id;
}
