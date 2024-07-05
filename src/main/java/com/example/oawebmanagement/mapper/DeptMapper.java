package com.example.oawebmanagement.mapper;
import com.example.oawebmanagement.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有部门
    @Select("select * from department")//mapper接口会把这条sql语句发送给数据库，再把查询出来的信息封装在部门集合中，最终把这个集合的数据返回给service又返回给controller，然后controller拿到返回数据再返回给前端
    List<Department> list();

    //新增部门
    @Insert("insert into department(department_name, manager_id, created_at, updated_at) " +
            "values (#{departmentName},#{managerId},#{createdAt},#{updatedAt})")
    void insert(Department dept);

    //查部门发布公告
    @Select("insert into announcement(title, content, department_id, created_at, updated_at) " +
            "values (#{title},#{content},#{departmentId},#{createdAt},#{updatedAt})")
    void addannouncements(Announcement announcement);

    //根据ID删除部门
    @Delete("delete from department where id =#{id}")
    void deleteById(Integer id);

    // 更新部门信息
    @Update("update department set department_name=#{departmentName}, " +
            "manager_id=#{managerId}, updated_at=#{updatedAt} where id=#{id}")
    void update(Department dept);

    //查单个部门成员
    @Select("select * from user where department_id = #{id}")
    List<User> listByDept(Integer id);

    //查部门公告
    @Select("select * from announcement where department_id = #{id}")
    List<Announcement> listannouncements(Integer id);

    @Select("select * from work where group_id in (select id from work_group where work_group.department_id = #{id})")
    List<Work> listWork(Integer id);

    //查询部门的加入申请
    @Select("select * from join_request where department_id = #{id}")
    List<JoinRequest> listjoin(Integer id);

    //负责人审核部门申请
    @Update("update join_request set " +
            "status=#{status},updated_at=#{updatedAt} where id = #{id}")
    void joincheck(JoinRequest joinRequest);

    //部门负责人批假
    @Update("update leave_request set status = #{status}," +
            "updated_at=#{updatedAt} where id = #{id}")
    void leavecheck(LeaveRequest leaveRequest);

    //前部门负责人进行
    @Update("update department_transfer_request set status_out = #{statusOut},updated_at=#{updatedAt} " +
            "where id = #{id}")
    void oldtransfercheck(DepartmentTransferRequest departmentTransferRequest);

    void aftertansfercheck(DepartmentTransferRequest departmentTransferRequest);

    void doublecheck(DepartmentTransferRequest departmentTransferRequest);

    //获得一个DepartmentTransferRequest的信息
    @Select("select id, user_id, current_department_id, target_department_id, application_date, status_out, status_in, reason, updated_at from department_transfer_request where id = #{id}")
    DepartmentTransferRequest getDepartmentTransferRequest(Integer id);

    @Insert("insert into work(title, content, group_id, status, created_at, updated_at)value " +
            "(#{title}, #{content},  #{groupId},#{status}, #{createdAt}, #{updatedAt})")
    void creatework(Work work);
}
