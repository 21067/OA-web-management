package com.example.oawebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Role（角色）实体类
public class Role {
    private int id;
    private String roleName;
    private String description;

}