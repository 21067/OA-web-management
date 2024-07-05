package com.example.oawebmanagement.service.impl;
import com.example.oawebmanagement.mapper.DeptMapper;
import com.example.oawebmanagement.pojo.*;
import com.example.oawebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired/*用于自动装配 Spring 容器中的 Bean。
    当一个类中的某个成员变量需要依赖其他 Bean 对象时，可以使用 @Autowired 注解将该 Bean 注入到成员变量中。
    Spring 在启动时会自动扫描并创建 Bean，并将其注入到需要依赖的地方。*/
    private DeptMapper deptMapper;//service并不能进行数据库操作，得调用mapper接口才能操作


    @Override//告诉编译器，被注解的方法是要重写（override）父类或接口中的方法
    public List<Department> list() {
        return deptMapper.list();//此处的list方法是mapper接口中的list方法，可以直接操作数据库
    }

    //增加部门（记得同时加一个负责人，不然manager_id默认为零）
    @Override
    public void add(Department dept) {
        dept.setCreatedAt(LocalDateTime.now());
        dept.setUpdatedAt(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    //删除部门（记得同时删除部门的所有员工，这玩意有外键）
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    //更新部门
    @Override
    public void update(Department dept){
        dept.setUpdatedAt(LocalDateTime.now());
        deptMapper.update(dept);
    }

    //根据id查单个员工
    @Override
    public List<User> listByDept(Integer id) {
        return deptMapper.listByDept(id);
    }

    //获得一个DepartmentTransferRequest的信息
    @Override
    public DepartmentTransferRequest getDepartmentTransferRequest(Integer id) {
        return deptMapper.getDepartmentTransferRequest(id);
    }

    //定工作内容
    @Override
    public void creatework(Work work) {
        work.setCreatedAt(LocalDateTime.now());
        work.setUpdatedAt(LocalDateTime.now());
        work.setStatus("TODO");
        deptMapper.creatework(work);
    }

    //检查转部门申请是否通过
    @Override
    public void doublecheck(DepartmentTransferRequest departmentTransferRequest) {
        deptMapper.doublecheck(departmentTransferRequest);
    }

    //查部门公告
    @Override
    public List<Announcement> listannouncements(Integer id) {
        return deptMapper.listannouncements(id);
    }

    //查部门工作内容
    @Override
    public List<Work> listWork(Integer id) {
        return deptMapper.listWork(id);
    }

    //查询部门申请
    @Override
    public List<JoinRequest> listjoin(Integer id) {
        return deptMapper.listjoin(id);
    }

    //审核部门申请
    @Override
    public void joincheck(JoinRequest joinRequest, Integer id) {
//        if(joincheck==1){
//            joinRequest.setStatus("APPROVED");
//        }else if (joincheck==2){
//            joinRequest.setStatus("REJECTED");
//        }
        joinRequest.setId(id);
        joinRequest.setUpdatedAt(LocalDateTime.now());
        deptMapper.joincheck(joinRequest);
    }

    //部门负责人批假
    @Override
    public void leavechack(LeaveRequest leaveRequest, Integer id) {
        leaveRequest.setId(id);
        leaveRequest.setUpdatedAt(LocalDateTime.now());
        deptMapper.leavecheck(leaveRequest);
    }

    //前部门审核
    @Override
    public void oldtransfercheck(DepartmentTransferRequest departmentTransferRequest) {
        departmentTransferRequest.setUpdatedAt(LocalDateTime.now());
        deptMapper.oldtransfercheck(departmentTransferRequest);
    }

    //后部门审核
    @Override
    public void aftertansfercheck(DepartmentTransferRequest departmentTransferRequest) {
        departmentTransferRequest.setUpdatedAt(LocalDateTime.now());
        deptMapper.aftertansfercheck(departmentTransferRequest);
    }


    //新增部门公告
    @Override
    public void addannouncements(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        announcement.setUpdatedAt(LocalDateTime.now());
        deptMapper.addannouncements(announcement);
    }



}