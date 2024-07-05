package com.example.oawebmanagement.controller;

import com.example.oawebmanagement.pojo.*;
import com.example.oawebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


    @GetMapping("/emps")
    public Result list() {
        log.info("查询全部员工数据");

        List<User> empList = empService.list();
        return Result.success(empList);
    }

    @PostMapping("/emps")
    public Result add(@RequestBody User emp){
        log.info("新增员工:{}",emp);

        empService.add(emp);
        return Result.success();
    }

    @DeleteMapping("/emps/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除员工:{}",id);
        empService.delete(id);
        return Result.success();
    }

    @PutMapping("/emps/{id}")
    public Result update(@PathVariable Integer id, @RequestBody User emp){
        log.info("修改员工信息：{},{}",id,emp);
        emp.setId(id); // 设置要更新的员工ID
        empService.update(emp);
        return Result.success();
    }

    //发出加入部门申请
    @PostMapping("/joindept/{id}")
    public Result join(@PathVariable Integer id,@RequestBody JoinRequest joinRequest){
        log.info("员工发出加入部门申请，{}",id);
//        if (joinRequest.getDepartmentId() == 0) {
            empService.join(id,joinRequest);
//            return Result.success();
//        } else {
//            return Result.error("已有部门，无法发出申请");
//        }
        return Result.success();
    }

    //发出请假申请6.6今晚先睡了，明天从这儿开始
    @PostMapping("/leave/{id}")
    public Result leave(@PathVariable Integer id , @RequestBody LeaveRequest leaveRequest){
        log.info("id为n的员工发出请假申请:{}",id);
        empService.leave(id,leaveRequest);
        return Result.success();
    }

    //员工发出转部门申请
    @PostMapping("/transfer/{id}")
    public Result transfer(@PathVariable Integer id, @RequestBody DepartmentTransferRequest departmentTransferRequest){
        log.info("id为{}的员工发出转部门申请",id);
        empService.transfer(id,departmentTransferRequest);
        return Result.success();
    }

    //创工作小组6.7，2:38，明天继续
    @PostMapping("/creategroup")
    public Result creategroup(@RequestBody WorkGroup workGroup){
        log.info("员工创个工作小组");
        empService.creategroup(workGroup);
        return Result.success();

    }

    //调整工作进度
    @PutMapping("/updateprogress")
    public Result updateprogress(@RequestBody Work work){
        log.info("员工调整工作额id为{}的进度");
        empService.updateprogress(work);
        return Result.success();
    }
}
