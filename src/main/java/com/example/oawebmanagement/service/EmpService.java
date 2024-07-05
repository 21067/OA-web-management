package com.example.oawebmanagement.service;
import com.example.oawebmanagement.pojo.*;
import java.util.List;

public interface EmpService {


    //用员工的list方法来查所有员工数据
    List<User> list();

    void add(User emp);

    void delete(Integer id);

    void update(User emp);

    void join(Integer id, JoinRequest joinRequest);

    void leave(Integer id, LeaveRequest leaveRequest);

    void transfer(Integer id, DepartmentTransferRequest departmentTransferRequest);

    //创工作小组
    void creategroup(WorkGroup workGroup);

    //更改工作进度
    void updateprogress(Work work);

    User login(User emp);
}
