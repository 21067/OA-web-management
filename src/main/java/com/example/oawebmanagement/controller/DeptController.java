package com.example.oawebmanagement.controller;
import com.example.oawebmanagement.pojo.*;
import com.example.oawebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.oawebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j//简化日志流程
@RestController//返回 JSON
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询部门
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        List<Department> deptList = deptService.list();
        return Result.success(deptList);
    }

    //根据部门id查询单个部门成员
    @GetMapping("/dept/{id}")
    public Result listByDept(@PathVariable Integer id){
        log.info("根据部门id查询部门员工,{}",id);
        List<User> empByDept = deptService.listByDept(id);
        return Result.success(empByDept);
    }

    //查询当前部门公告
    @GetMapping("/deptannouncements/{id}")
    public Result listannouncements(@PathVariable Integer id){
        log.info("根据部门id查部门公告,{}",id);
        List<Announcement> announcements = deptService.listannouncements(id);
        return Result.success(announcements);
    }

    //查部门的工作内容
    @GetMapping("/deptworks/{id}")
    public Result listwork(@PathVariable Integer id){
        log.info("查询单个部门的工作内容:{}",id);
        List<Work> works = deptService.listWork(id);
        return Result.success(works);
    }

    //查询部门加入申请
    @GetMapping("/joins/{id}")
    public Result listjoin(@PathVariable Integer id){
        log.info("查询所有部门加入申请");
        List<JoinRequest> joinRequests = deptService.listjoin(id);
        return Result.success(joinRequests);
    }

    //新增部门
    @PostMapping("/depts")
    public Result add(@RequestBody Department dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //发布部门公告
    @PostMapping("/addannouncements")
    public Result addannouncements(@RequestBody Announcement announcement){
        log.info("发布新公告：{}",announcement);
        deptService.addannouncements(announcement);
        return Result.success();
    }

    //根据id删除部门
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }

    //根据id修改部门信息
    @PutMapping("/depts/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Department dept){
        log.info("修改部门信息：{},{}",id,dept);
        dept.setId(id); // 设置要更新的部门ID
        deptService.update(dept);
        return Result.success();
    }

    //部门负责人审核加入申请（同意或拒绝）
    @PutMapping("/joincheck/{id}")
    public Result joincheck(@RequestBody JoinRequest joinRequest,@PathVariable Integer id){
        log.info("部门负责人审核结果，{}",joinRequest.getStatus());
        deptService.joincheck(joinRequest,id);
        return Result.success();
    }

    //部门负责人审核员工请假（同意拒绝或驳回）
    @PutMapping("leavecheck/{id}")
    public Result leavechack(@RequestBody LeaveRequest leaveRequest,@PathVariable Integer id){
        log.info("部门负责人批假");
        deptService.leavechack(leaveRequest,id);
        return Result.success();
    }

    //前部门负责人根据审核id为n的本部门成员的转部门申请
    @PutMapping("/oldtranceport/{id}")
    public Result oldtransfercheck(@RequestBody DepartmentTransferRequest departmentTransferRequest,@PathVariable Integer id){
        log.info("前部门负责人审核id为{}的转部门申请",id);
        departmentTransferRequest.setId(id);
        deptService.oldtransfercheck(departmentTransferRequest);
        return Result.success();
    }

    //后部门负责人根据审核id为n的本部门成员的转部门申请ing
    @PutMapping("/aftertranceport/{id}")
    public Result aftertansfercheck(@RequestBody DepartmentTransferRequest departmentTransferRequest,@PathVariable Integer id){
        log.info("后部门负责人审核id为{}的转部门申请",id);
        String a =departmentTransferRequest.getStatusIn();
        departmentTransferRequest = deptService.getDepartmentTransferRequest(id);
        departmentTransferRequest.setStatusIn(a);
        deptService.aftertansfercheck(departmentTransferRequest);
        deptService.doublecheck(departmentTransferRequest);
        return Result.success();
    }

    //部门负责人定工作内容
    @PostMapping("/creatework")
    public Result creatework(@RequestBody Work work){
        log.info("部门负责人定工作内容：{}",work);
        deptService.creatework(work);
        return Result.success();
    }
}

