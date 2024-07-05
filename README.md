# OA管理系统功能以及测试介绍

---

### 项目功能简介

本OA管理系统可以实现基本的部门以及员工的基本管理和员工的请假，申请加入部门，转部门等请求实现
以及实现工作内容的线上创建监督和实时进度修改。

#### 使用的技术栈

* 后端开发

  * **Spring Boot**: 作为核心框架，用于快速开发和简化配置。
  * **Spring web**:作为maven架构中的spring驱动，属于springboot核心框架的一部分
  * **maven架构**：依赖管理和项目构建工具

* 数据库

  * **MySQL**：关系型数据库存储用户数据、部门信息、工作内容等。

* 安全性

  * **JWT (JSON Web Tokens)**:令牌技术，用于用户会话管理。

    > 这个还没完全实现，只能基本的令牌生成和保存，但还没学会登陆拦截

* 测试

  * **Postman**：API开发和测试工具

* 部署

  * **tomcat**：是 Apache 软件基金会的 Jakarta 项目的一部分。Tomcat 提供了一个运行 Java Web 应用程序的平台

#### 文件结构

* main
  * pom.xml
  * java
    * com.example.oawebmangement
      * controller
        * DeptController
        * EmpController
        * LoginContrller
      * mapper
        * DeptMapper
        * EmpMapper
      * pojo
        * Announcement
        * Department
        * DepartmentTransferRequest
        * JoinRequest
        * LeaveRequest
        * Result
        * Role
        * Status
        * User
        * Work
        * WorkGroup
      * service
        * impl
          * DeptServiceImpl
          * EmpServicesImpl
        * DeptService
        * EmpServices
      * OaWebManagementApplication
  * resource
    * static
    * templates
    * application.properties
    * com.example.oawebmanagement.mapper
      * DeptMapper.xml
      * EmpMapper.xml



#### 分包介绍

首先需要和前端进行连桥，那么我们必须规定一个返回给前端的数据类型Result：

``````java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
``````

这个Result类用于和前端交互时返回数据而规定的数据类型，包含三个部分

* code响应码，1代表成功，0代表失败
* msg，用于响应成功或失败的信息
* data，用于存放查询时后端返回回来的数据

接下来我们从文件包讲起



#####  controller类

在Spring Boot框架中，@Controller 及其派生注解（如 @RestController）是用于定义控制器（Controller）的。控制器是Spring MVC（Model-View-Controller）架构中的核心组件之一，它的作用主要包括：

1. **接收请求**：控制器负责接收来自客户端的HTTP请求。在Spring Boot中，这通常是通过使用@RequestMapping、@GetMapping、@PostMapping等注解来实现的，这些注解将特定的HTTP请求映射到控制器中的方法。
2. **处理业务逻辑**：控制器可以调用业务逻辑层（Service层）的方法来处理请求。业务逻辑层通常包含应用程序的核心功能和业务规则。
3. **数据转换**：控制器可能需要将接收到的数据转换为应用程序内部使用的数据格式，或者将内部数据转换为客户端所需的格式。
4. **返回响应**：处理完请求后，控制器会生成一个响应，返回给客户端。在Spring Boot中，响应通常是一个JSON对象，这可以通过使用@ResponseBody注解或@RestController注解来实现。

这里我们定义了三个控制器类，分别是部门控制器，员工控制器，和一个登陆控制器
实际上可以整合到一个控制器，只不过这样分包有助于开发效率，使人一目了然



##### service类

Service 类在Spring Boot框架中扮演着业务逻辑层的角色，它封装了应用程序的核心业务规则和操作，提供了一系列业务方法供控制器调用，以实现具体的业务功能。Service类负责调用数据访问层来执行数据库操作，并可以配置事务管理以确保数据的一致性和完整性。通过Service层，控制器与数据访问层之间的耦合度降低，提高了代码的可维护性和可测试性，同时也使得业务逻辑更加清晰和易于管理。



##### pojo类

实体类，和建立的数据库表进行对应



#### 数据库表设计

我设计了九章表，足以实现所需功能，分别是
部门公告表
角色表
用户表
部门表
转部门申请表
加入部门申请表
请假申请表
工作表
工作组表



#### 功能实现

> 查询当前部门成员

```java
 //根据部门id查询单个部门成员
    @GetMapping("/dept/{id}")
    public Result listByDept(@PathVariable Integer id){
        log.info("根据部门id查询部门员工,{}",id);
        List<User> empByDept = deptService.listByDept(id);
        return Result.success(empByDept);
    }
```

我们在数据库表中已经准备好了几个数据，我们通过Postman发送一个请求

` localhost:8080/dept/2`

查询部门id为2的所有成员

测试结果如下：

```java
 "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 3,
            "username": "user3",
            "password": "123456",
            "roleId": 2,
            "departmentId": 2,
            "createdAt": "2024-06-03T14:31:41",
            "updatedAt": "2024-06-07T00:15:44"
        },
        {
            "id": 6,
            "username": "user6",
            "password": "123456",
            "roleId": 3,
            "departmentId": 2,
            "createdAt": "2024-06-03T14:42:37",
            "updatedAt": "2024-06-07T19:29:59"
        },
        {
            "id": 7,
            "username": "user7",
            "password": "123456",
            "roleId": 3,
            "departmentId": 2,
            "createdAt": "2024-06-03T14:42:37",
            "updatedAt": "2024-06-07T19:29:59"
        },
        {
            "id": 8,
            "username": "user8",
            "password": "123456",
            "roleId": 3,
            "departmentId": 2,
            "createdAt": "2024-06-03T14:42:37",
            "updatedAt": "2024-06-07T19:29:59"
        }
    ]
}
```

我们的user表中部门id为2的成员信息都查出来了，当然，password本来不该明文展示和查询，涉及到加密的技术以后还会学习到

> 查询当前部门公告

```java
//查询当前部门公告
    @GetMapping("/deptannouncements/{id}")
    public Result listannouncements(@PathVariable Integer id){
        log.info("根据部门id查部门公告,{}",id);
        List<Announcement> announcements = deptService.listannouncements(id);
        return Result.success(announcements);
    }
```

在postman中发送请求` localhost:8080/deptannouncements/2`

来查询部门2的所有公告

测试结果如下

```java
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 2,
            "title": "Holiday Notice",
            "content": "The office will be closed for the holidays from December 24th to December 26th.",
            "departmentId": 2,
            "createdAt": "2024-06-03T14:31:41",
            "updatedAt": "2024-06-03T14:31:41"
        },
        {
            "id": 4,
            "title": "Training Session",
            "content": "There will be a training session on new software tools next Friday.",
            "departmentId": 2,
            "createdAt": "2024-06-03T14:42:37",
            "updatedAt": "2024-06-03T14:42:37"
        }
    ]
}
```

查询到两条部门2的公告数据封装为json格式返回给了前端



> 查询部门工作内容

```java
    //查部门的工作内容
    @GetMapping("/deptworks/{id}")
    public Result listwork(@PathVariable Integer id){
        log.info("查询单个部门的工作内容:{}",id);
        List<Work> works = deptService.listWork(id);
        return Result.success(works);
    }
```

Postman发出请求`localhost:8080/deptworks/2`
表示查询部门id为2的工作内容
同所有查询操作，首先传入一个id
最后在mapper层的查询语句将数据封装到一个Work类
返回这个work对象给控制器，最后调用Result的重载方法
将这个数据放到date中返回给前端

```java
{
    "code": 1,
    "msg": "success",
    "data": [
        {
            "id": 2,
            "title": "Task 1",
            "content": "Review the code changes and provide feedback.",
            "status": "IN_PROGRESS",
            "groupId": 2,
            "createdAt": "2024-06-03T14:31:41",
            "updatedAt": "2024-06-03T14:31:41"
        }
    ]
}
```

返回查询的结果



> 发布部门公告

```java
    //发布部门公告
    @PostMapping("/addannouncements")
    public Result addannouncements(@RequestBody Announcement announcement){
        log.info("发布新公告：{}",announcement);
        deptService.addannouncements(announcement);
        return Result.success();
    }
```

我们用Postman传入`localhost:8080/addannouncements`
json示例数据为

```java
{
  "title": "New Announcement",
  "content": "This is a test announcement for testing purposes.",
  "departmentId": 3
}
```

需要传入公告标题，内容，以及本部门id三个信息
在service曾进行对象的属性完善
在mapper层实现数据通过传入的公告类进行新增数据



> 新建工作小组

```java
 //创工作小组
    @PostMapping("/creategroup")
    public Result creategroup(@RequestBody WorkGroup workGroup){
        log.info("员工创个工作小组");
        empService.creategroup(workGroup);
        return Result.success();
    }
```

用Postman发出请求`localhost:8080/creategroup`
请求体如下

```java
{
    "groupName":"Test Team",
    "departmentId":1,
    "leaderId":4,
    "member1Id":5,
    "member2Id":6
}
```

分别传入小组名，部门id，创始人，两个成员的id
在service曾进行小组对象的属性完善
在mapper层实现数据通过传入的小组对象进行新增数据



> 定工作内容

```java
  //部门负责人定工作内容
    @PostMapping("/creatework")
    public Result creatework(@RequestBody Work work){
        log.info("部门负责人定工作内容：{}",work);
        deptService.creatework(work);
        return Result.success();
    }
```

用Postman发出请求`localhost:8080/creatework`
json示例数据如下

```java
{
    "title":"test project",
    "content":"test content",
    "groupId":"1"
}
```

需要传入标题，工作内容，和小组id
在service曾进行work对象的属性完善
在mapper层实现数据通过传入的work对象进行新增数据
将数据加入到Work表



> 小组成员更改工作进度

```java
    //调整工作进度
    @PutMapping("/updateprogress")
    public Result updateprogress(@RequestBody Work work){
        log.info("员工调整工作额id为{}的进度");
        empService.updateprogress(work);
        return Result.success();
    }
```

用Postman发出put请求`localhost:8080/updateprogress`
json示例数据如下

```java
{
    "id":7,
    "status":"IN_PROGRESS"
}
```

表示将工作id为7的work数据的status属性更改为IN_PROGRESS
在service曾进行对传入的work对象的属性完善
在mapper层实现数据通过传入的work对象进行数据覆盖
将Work表中的数据更新



> 加入部门功能

首先员工发出申请
```java
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
```

* (此处注释掉的内容可用获取一个申请对象的方法实现，但这里我们用了xml映射文件)

Postman发出`localhost:8080/joindept/2`
表示id为2的员工申请加入部门
json示例数据如下

```java
{
    "departmentId":2
}
```

需要传入想加入的部门id
在service层中自动的将传入对象的status赋值为PENDING
然后再到mapper中对数据库表中的数据进行覆盖

接下来是部门负责人审核
```java
//部门负责人审核加入申请（同意或拒绝）
    @PutMapping("/joincheck/{id}")
    public Result joincheck(@RequestBody JoinRequest joinRequest,@PathVariable Integer id){
        log.info("部门负责人审核结果，{}",joinRequest.getStatus());
        deptService.joincheck(joinRequest,id);
        return Result.success();
    }
```

Postman发出请求`localhost:8080/joincheck/1`
json示例

```java
{
    "status":"APPROVED"
}
```

表示部门负责人审核id为1的申请加部门请求，并且同意加入
此时service层给申请表对象赋值后
在mapper中进行数据覆盖
并且再次对这个对象进行条件查询
根据审核情况决定是否更改user表中的部门id
完成审批



> 请假功能

实现方式如上，都是通过员工创建一个表对象，然后部门审核通过后

员工id为7的员工请假示例请求和json格式如下：

`localhost:8080/leave/7`

```java
{
    "reason":"headache",
    "startDate":"2024-06-05",
    "endDate":"2024-06-07"
}
```

部门负责人批假的示例请求和json如下
`localhost:8080/leavecheck/7`

```java
{
    "status":"APPROVED"
}
```

实现方法同加入部门申请类似



> 申请转部门功能

首先由员工发出转部门申请

```java
 //员工发出转部门申请
    @PostMapping("/transfer/{id}")
    public Result transfer(@PathVariable Integer id, @RequestBody DepartmentTransferRequest departmentTransferRequest){
        log.info("id为{}的员工发出转部门申请",id);
        empService.transfer(id,departmentTransferRequest);
        return Result.success();
    }
```

Postman发出请求` localhost:8080/transfer/7`id为7的员工发出了转部门申请

传入的json数据如下

```java
{
    "currentDepartmentId":"2",
    "targetDepartmentId":"1",
    "reason":"don't like old department"

}
```

即当前部门 , 转入部门还有申请原因三个参数

调用service层的代码，最后转到mapper层的transfer方法接受传来的一个转部门请求对对象,通过insert注解向转部门申请的数据库表中插入一条数据

```java
   //员工发出转部门申请
    @Insert("insert into department_transfer_request (user_id, current_department_id, target_department_id, application_date, status_out, status_in, reason, updated_at) value " +
            "(#{userId},#{currentDepartmentId},#{targetDepartmentId},#{applicationDate},#{statusOut},#{statusIn},#{reason},#{updatedAt})")
    void transfer(DepartmentTransferRequest departmentTransferRequest);
```



审核阶段：

```java
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
```

这里是前后部门负责人的两个控制器
我们首先发出传入前部门审核人的请求
`localhost:8080/oldtranceport/4`
审核第四条转部门申请，传入json为

```java
{
    "statusOut":"APPROVED"
}
```

表示前部门同意

```java
//前部门负责人进行
    @Update("update department_transfer_request set status_out = #{statusOut},updated_at=#{updatedAt} " +
            "where id = #{id}")
    void oldtransfercheck(DepartmentTransferRequest departmentTransferRequest);

```

此时我们仍用注解进行修改数据

然后后部门负责人审核
发出请求`localhost:8080/aftertranceport/4`
同样审核id为4的准部门申请表

这里我们需要用注解方式进行条件控制

```java
<mapper namespace="com.example.oawebmanagement.mapper.DeptMapper">
    <update id="aftertansfercheck" parameterType="com.example.oawebmanagement.pojo.DepartmentTransferRequest">
      <if test="statusOut == 'APPROVED' ">
          UPDATE department_transfer_request
          SET
          status_in = #{statusIn},
          updated_at = #{updatedAt}
          WHERE id = #{id}

      </if>

    </update>

    <update id="doublecheck" parameterType="com.example.oawebmanagement.pojo.DepartmentTransferRequest">
        <if test="statusIn == 'APPROVED'">
            update user
            set department_id = #{targetDepartmentId}
            where user.id = #{userId};
        </if>
    </update>
</mapper>
```

这里会检查传入的部门审核表中转出状态是否为同意，如果不同意则不会执行任何操作，同意则会覆盖数据。

同理doublecheck方法中也会检查转出状态和转出状态，如果两个状态都为同意则进行员工的部门id更新操作。



