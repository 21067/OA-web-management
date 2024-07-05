package com.example.oawebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private String TODO="TODO";
    private String IN_PROGRESS="IN_PROGRESS";
    private String DONE="DONE";
}
