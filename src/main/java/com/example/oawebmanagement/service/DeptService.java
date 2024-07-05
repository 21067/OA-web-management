package com.example.oawebmanagement.service;

import com.example.oawebmanagement.pojo.*;

import java.util.List;

//部门业务规则
public interface DeptService {

    //list用法查询全部部门数据
    List<Department> list();

    //新增部门
    void add(Department dept);

    void delete(Integer id);

    void update(Department dept);

    //查单个部门的成员
    List<User> listByDept(Integer id);

    List<Announcement> listannouncements(Integer id);

    List<Work> listWork(Integer id);

    void addannouncements(Announcement announcement);

    List<JoinRequest> listjoin(Integer id);

    void joincheck(JoinRequest joinRequest, Integer id);

    void leavechack(LeaveRequest leaveRequest, Integer id);

    void oldtransfercheck(DepartmentTransferRequest departmentTransferRequest);

    void aftertansfercheck(DepartmentTransferRequest departmentTransferRequest);

    //获得一个DepartmentTransferRequest的信息
    DepartmentTransferRequest getDepartmentTransferRequest(Integer id);

    //定工作内容
    void creatework(Work work);

    //若两个部门都通过审核则给员工改部门id
    void doublecheck(DepartmentTransferRequest departmentTransferRequest);
}