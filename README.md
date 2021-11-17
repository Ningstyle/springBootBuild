### 1. 工程安装环境
本地安装 redis mysql jdk8+ maven

### 2. 工程打包顺序
 - 在idea中进入到 small-project 项目使用maven插件进行打包
 - 按照如下顺序依次 clean install 基础组件安装包本地maven仓库
    - small-project 
    - common
    - redis
    - db-mysql
    
 - 以上完成后可以进行 admin与business的 clean package
 
### 3. 后台管理项目 admin
 - 创建 scuser数据库，导入 resources 目录中init.sql 脚本。
 - 启动工程，访问 http://localhost:9090/admin/swagger-ui.html 
   ；看到在线接口地址；可以进行在线执行，查看接口返回结果
 - 系统默认管理员账户： admin 密码：123456
 - 访问 http://localhost:9090/admin/login.html 输入用户密码，可以获取到登录用户token，最后在swagger中点击右上角 Authorize 进行登录，可以创建用户和赋权操作；
 
### 4. 业务代码项目 business
 - 创建 test 数据库，导入 resources 目录中init.sql 脚本。 
 - 启动工程，访问 http://localhost:9090/business/swagger-ui.html 
      ；看到在线接口地址；可以进行在线执行，查看接口返回结果
 - 在swagger右上角有token验证，输入登录后获取的token可以验证用户登录；
 - 用户登录后可以获取用户信息 如下
 ```$xslt
   @Login
    @GetMapping("/needLogin")
    @ApiOperation(value = "测试需要登录")
    public ResultInfo needLogin(User user){
        log.info("登录后的用户信息 user -> {}",user);
        Test result = testService.getById("123");
        return ResultInfo.ok(result);
    }
```     
注意： 若是用户没有登录则不能获取；

添加用户使用@RequestBody注解 User user;不加@Login不出覆盖User这个类，此类为用户信息表数据，不要随意继承或使用；
解析配置在com.small.business.common.resolver.LoginUserArgumentResolver；
 
 
  
 

