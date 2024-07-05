package com.example.oawebmanagement.service.impl;

import com.example.oawebmanagement.mapper.EmpMapper;
import com.example.oawebmanagement.pojo.*;
import com.example.oawebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmptServiceImpl implements EmpService
{

    @Autowired
    private EmpMapper empMapper;

    //查询全部用户
    @Override
    public List<User> list() {
            return empMapper.list();
        }

    //增加员工
    @Override
    public void add(User emp) {
        emp.setCreatedAt(LocalDateTime.now());
        emp.setUpdatedAt(LocalDateTime.now());
        empMapper.insert(emp);
    }

    //删除员工
    @Override
    public void delete(Integer id) {
        empMapper.deleteById(id);
    }

    @Override
    public void update(User emp) {
        emp.setUpdatedAt(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public void join(Integer id, JoinRequest joinRequest) {
        joinRequest.setUserId(id);
        joinRequest.setCreatedAt(LocalDateTime.now());
        joinRequest.setUpdatedAt(LocalDateTime.now());
        empMapper.join(joinRequest);
    }

    @Override
    public void leave(Integer id, LeaveRequest leaveRequest) {
        leaveRequest.setUserId(id);
        leaveRequest.setStatus("PENDING");
        leaveRequest.setCreatedAt(LocalDateTime.now());
        leaveRequest.setUpdatedAt(LocalDateTime.now());
        empMapper.leave(leaveRequest);
    }

    @Override
    public void transfer(Integer id, DepartmentTransferRequest departmentTransferRequest) {
        departmentTransferRequest.setUserId(id);
        departmentTransferRequest.setStatusIn("PENDING");//
        departmentTransferRequest.setStatusOut("PENDING");//
        departmentTransferRequest.setApplicationDate(LocalDateTime.now());
        departmentTransferRequest.setUpdatedAt(LocalDateTime.now());
        empMapper.transfer(departmentTransferRequest);
    }

    //创工作小组
    @Override
    public void creategroup(WorkGroup workGroup) {
        workGroup.setCreatedAt(LocalDateTime.now());
        workGroup.setUpdatedAt(LocalDateTime.now());
        empMapper.creategroup(workGroup);
    }

    //更新工作进度
    @Override
    public void updateprogress(Work work) {
        work.setUpdatedAt(LocalDateTime.now());
        empMapper.updateprogress(work);
    }

    //登陆
    @Override
    public User login(User emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

//    @Override
//    public Work getwork(Integer id) {
//        return null;
//    }
}


