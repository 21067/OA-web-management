package com.example.oawebmanagement.mapper;
import com.example.oawebmanagement.pojo.*;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    //查询员工
    @Select("select * from user where role_id = 3")
    List<User> list();

    //增加员工
    @Insert("INSERT INTO user (username, password, role_id, department_id, created_at, updated_at) " +
            "VALUES (#{username}, #{password}, 3, #{departmentId}, #{createdAt}, #{updatedAt})")
    void insert(User emp);

    //根据id删除员工
    @Delete("delete from user where id =#{id}")
    void deleteById(Integer id);

    //根据id修改员工
    @Update("update user set username = #{username}, password = #{password}, role_id = #{roleId}," +
            "department_id = #{departmentId}, updated_at = #{updatedAt} where id = #{id}")
    void update(User emp);

    //员工发出加入部门申请
    void join(JoinRequest joinRequest);

    //员工发出请假
    @Insert("insert into leave_request (user_id, start_date, end_date, reason, status, created_at, updated_at)VALUE " +
            "(#{userId},#{startDate},#{endDate},#{reason},#{status},#{createdAt}, #{updatedAt})")
    void leave(LeaveRequest leaveRequest);

    //员工发出转部门申请
    @Insert("insert into department_transfer_request (user_id, current_department_id, target_department_id, application_date, status_out, status_in, reason, updated_at) value " +
            "(#{userId},#{currentDepartmentId},#{targetDepartmentId},#{applicationDate},#{statusOut},#{statusIn},#{reason},#{updatedAt})")
    void transfer(DepartmentTransferRequest departmentTransferRequest);
    //
    @Insert("INSERT INTO work_group (group_name, department_id, leader_id, created_at, updated_at, work_id, menber_1_id, member_2_id) "
            + "VALUES (#{groupName}, #{departmentId}, #{leaderId}, #{createdAt}, #{updatedAt}, #{workId}, #{member1Id}, #{member2Id})")
    void creategroup(WorkGroup workGroup);

    //员工更新工作进度
//    @Update("update work set updated_at=#{updatedAt} , status=#{status} where id=#{id}")
    void updateprogress(Work work);

    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User emp);
}
