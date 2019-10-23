# 项目说明

该小项目仅仅作为自己熟悉 SSM 框架整合的 Demo，部分功能因为涉及到 SpringBoot，还在学习过程中，将在后期补充。先熟练使用，再探究原理，从实践中弄懂如何做，从源码中弄懂为什么，加油😆

## 一、整体说明

技术架构： Spring  + SpringMVC  + MyBatis + MySQL

工具：IDEA + Maven + Tomcat

## 二、系统分解

- 前端展示系统
  - 头条展示
  - 店铺类别展示
  - 区域展示
  - 店铺
    - 列表展示
    - 查询
    - 详情
  - 商品
    - 列表展示
    - 查询
    - 详情

- 店家管理系统
  - local 账号维护
  - 微信账号维护
  - 店铺信息维护
  - 权限验证
  - 商品类别维护
- 超级管理员管理系统
  - 头条信息维护
  - 店铺类别信息维护
  - 区域信息维护
  - 权限验证
  - 店铺管理
  - 用户管理

## 二、数据库建表

- 数据库名称：o2o
- 数据表：
  - tb_area：区域信息
  - tb_head_line：
  - tb_local_auth：本地用户信息，包括本地用户 id，用户 id，用户名，密码，创建时间，修改时间；
  - tb_person_info：用户信息，包括用户 id，等等信息；
  - tb_product：商品属性信息；
  - tb_product_category：
  - tb_product_img：商品图片信息；
  - tb_shop：商铺信息；
  - tb_shop_category：
  - tb_wechat_auth：



## 三、系统设计

### （一）根据数据库中表格生成对应的实体类（com.gjxaiou.entity）

- Area.java
- Headline.java
- LocalAuth.java
- PersonInfo.java
- Product.java
- ProductCategory.java
- ProductImg.java
- Shop.java
- ShopCategory.java
- WeChatAuth.java



### （二）Maven 配置

- 统一配置 Spring 版本为：5.1.19.RELEASE；
- 因为是 Demo，会经常对自己代码进行调试，需要引入：junit；
- 一定要有的日志，这里使用 logback（log4j 的改良版）；
- 数据库驱动以及 c3p0 连接池；
- Mybatis 依赖以及 Mybatis 与 Spring依赖；
- Servlet 方面的以及 jstl 以及使用 Jackson 作为 json 解析；
- Map 工具类，对标准的 Java collection 的拓展；
- Spring 的依赖；
- kaptcha：用于生成验证码；
- 文件上传的依赖；
- lombok 插件：为了自己做笔记方便，因此使用 lombok 插件实现 get、set 方法；

## （三）SSM 整合验证

这里以实现区域查找功能为例，通过配置验证SSM配置；

- 首先是 SSM 基本配置：
    - `db.properties`  配置数据库连接信息；
    - `mybatis-config`配置 MyBatis 的全局属性，包括使用 jdbc 的 getGeneratedKeys 获取数据库的自增主键值；
    - `spring-dao.xml` 配置整合 Mybatis  的过程，包括数据库相关参数配置文件（db.properties）位置，数据库连接池（数据库连接池属性， 关闭自动提交等等），配置 SqlSessionFactory 对象，配置扫描 Dao 接口包，实现交由 Spring 容器管理；
    - `spring-service.xml` 首先配置扫描 service 包下面所有使用注解的类型，然后配置事务管理器，同时配置基于注解的声明式事务；
    - `spring-web.xml` 配置 SpringMVC，开启 SpringMVC 的注解模式，配置静态资源位置，自定义视图解析器，文件上传解析器；
    - `web.xml` 配置 DispatcherServlet，即 SpringMVC 需要加载的配置文件
- 日志配置：
    - 配置日志的记录级别，保存时间，输出位置，输出格式等；
- com.gjxaiou.entity：数据库表对应的实体类；
- Dao 层：
    - 首先创建 AreaDao 接口（com.gjxaiou.dao.AreaDao.java），声明查询区域列表的方法；
    - 然后创建 AreaDao.xml（resources.mapper.AreaDao.xml），其中 `namespace` 是声明对应的 Dao 接口，因为是查询要求，因此使用 `<select/>`标签，其中 `id ` 表示 对应的方法名，`resultType`表示返回值类型，这里只需要返回 `Area`对象即可；然后最后加上对应的 SQL 语句；
- service 层： AreaService.java 与  AreaServiceImpl.java   
    - service 层一般包含一个接口和一个实现类，接口是想要执行的方法，然后在实现类中实现该方法，该方法调用 Dao 层中的查询方法；
    - 其实现类 XXXService.xml 返回想要的数据是给 controller 中的；
    - 实现类中使用 `@Service` 和 `@Autowired` 表示交给 Spring 管理；
- Controller 层: AreaController.java（com.gjxaiou.web.superadmin.AreaController.java）
    - 首先 controller 层依赖于 service 层，因此首先创建 service 对象，将 service 实体类交个 Spring 管理；
    - 定义方法接受 service 层处理之后的对象，这里接收到的是 List 集合，这里使用 Map 存放返回值，因为是 `select`，所以返回的是受影响的行数；
    - 这里使用 logback 实现运行过程中的日志输出；

- 功能测试
  - 首先通过 BaseTest 类，实现初始化 Spring 容器，所有其他 Test 类都继承该类；
  - 验证 AreaDao 类，即调用查询方法，看结果和数据库中数据数目是否相同；
  - 验证 AreaServiceTest 类，可以查看查询里面具体内容是否和数据库中数据相同；

## （三）店铺商家管理系统
包括：店铺和商品模块
首先应该应有店铺然后才有商品模块，因此先从店铺商家管理系统开始设计，主要实现店铺的增删改查；

~~下面目录中仅仅实现了店铺的新增和更新功能，其他功能后续增加~~

#### 1.Dao层

- shopDao
- shopExecution ：保存返回信息
- shopStateEnume：定义所有 shop 可能的返回值

#### 2.service 层（需要事务处理）
首先需要将店铺信息插入到数据库中，然后返回这个店铺的 Id，根据该店铺的 Id 创建存储该店铺图片的文件夹，在该文件夹下面处理图片，最后将文件夹地址更新会这条数据
以上任何一步出错都要回滚 -> 需要事务处理；

#### 3.controller 层
放在包 web.shopadmin 下面，店家管理后台的 controller都放在这里

- ShopManagerController ：负责店铺管理相关逻辑
- HttpServletRequestUtil：负责解析 HttpServletRequest 请求的参数

注： pom 中 的 jackson-databind.jar 负责将实体类转换为 json或者反过来转换


#### 4.前端页面
使用 阿里巴巴的 sui mobile
使用这个 demo ：http://m.sui.taobao.org/demos/form/label-input/ ,然后右击获取源代码，并且引入静态资源（将 Link 和 script
 内容替换），参考：http://m.sui.taobao.org/getting-started/

 - 然后将页面放在 WEB-INF 下的 html/shop 下面，然后配置 shopadmin/shopAdminController.java 来访问，因为该文件夹下面资源文件不能直接访问；

 - 然后编写： webapp/resources/js/shop/shopoperation.js

 ### 需要的方法补写

 因为上面涉及到了 getshopinitinfo 方法，该方法返回区域和商铺类别相关信息，应为当前以及实现了区域列表，但是 shopCategory 还么有实现，所以从 dao层开始实现；
 - shopCategoryDao.java 和对应的 Mapper文件

 - service 层：一个接口，一个实现类
 - shopManagerController 中实现控制层： getinitinfo 方法，该方法获取区域和商铺类别相关信息然后返回给前台


### 上面 前端中的 验证码功能使用 ：
 - 导包 Kaptcha
 - web.xml 中使用servlet 生成验证码的相关设置
 - shopoperation.html 中引入验证码控件
 其中控件中有点击方法，点击就换一张验证码图片，因此使用 js/common/common.js 实现，然后在 shopoperation.js
    中将生成的验证码传入，最后在 shopoperation.HTML 中引入上面的 js
  
- 同样后端实现方法 CodeUtil.java 实现判断验证码是否正确  ;
- 最后在 ShopManagerController.java  的添加店铺之前进行验证




### thumbnailator 使用
1. 导包
2. util包下面添加 ImageUtil 方法
该方法中实现了图片的一般操作方法，这里的方法可以自定义。但是如果是批量处理图片，需要平凡的获取图片文件路径，
因此新建一个 PathUtil.java 类，里面实现获取输入文件路径和输出文件路径；







## 补充：数据库实现主从读写分离

### （一）数据库配置
MySQL 的主从复制功能不仅可以实现数据的多处自动备份，从而实现数据库的拓展。同时多个数据备份不仅可以加强数据的安全性，同时通过读写分离还能进一步提升数据库的负载性能。

在一主多从的数据库体系中，多个从服务器采用异步的方式更新主数据库的变化，**业务服务器在执行写或者相关修改数据库的操作是在主服务器上进行的，读操作则是在各从服务器上进行**。如果配置了多个从服务器或者多个主服务器又涉及到相应的负载均衡问题，关于负载均衡具体的技术细节还没有研究过，本项目中实现一主一从的主从复制功能，一主多从的复制和读写分离的模型见下：
![一主多从数据库体系]($resource/%E4%B8%80%E4%B8%BB%E5%A4%9A%E4%BB%8E%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BD%93%E7%B3%BB.jpg)

**主从同步工作过程：**

首先主服务器（Master）对数据的操作记录到二进制日志（Binary log）文件中（即在每个事务的更新事件完成之前，Master 在日志中都会记录这些改变，MySQL 串行的将事务写入二进制文件中，写入完成之后 Master 通知存储引擎提交事务），然后从服务器（Slave）开启一个 IO 线程保持与主服务器的同学，如果发现 Master 二进制日志文件发生改变， 将 binary log 拷贝然后写入从服务器的中心日志（ Relay log）中，即将主服务器的操作同步到 Relay log  中，最后从服务器重新开启一个 SQL线程，将刚才同步过来的操作在从服务器中进行执行，从而实现从数据库和主数据库的一致性，也实现了主从复制。

![主从数据同步]($resource/%E4%B8%BB%E4%BB%8E%E6%95%B0%E6%8D%AE%E5%90%8C%E6%AD%A5.jpg)

**具体配置：**
一共使用两台虚机实现数据库的读写分离，虚机一：CentOS7Mini：192.168.238.136，为主数据库，虚机二：CentOS7MiniClone：192.168.238.135，为从数据库；
*   主服务器：
    *   开启二进制日志
    *   配置唯一的 server-id
    *   获得 master 二进制日志文件名及位置
    *   创建一个用于 slave 和 master 通信的用户账号
*   从服务器：
    *   配置唯一的 server-id
    *   使用 master 分配的用户账号读取 master 二进制日志
    *   启用 slave 服务


- 虚拟机一：Master 主机
  - 修改 `/etc/my.cnf` 中配置 

```linux
server-id=1 # 设置 server-id
log-bin=master-bin # 开启二进制文件
log-bin-index=master-bin.index
```
- 然后进入 MySQL：
```mysql
create user 'repl'@'192.168.238.135' identified by 'GJXAIOU_o2o'; # 创建主从直接的通信账号
grant replication slave on *.* to 'repl'@'192.168.238.135';# 授予该账号读取主服务器中所有数据库的所有表的权限
```
- 然后重启数据库 ：`sudo service mysqld restart`
- 然后进入 MySQL：
```mysql
flush privileges;
show master status; # 查看二进制日志文件状态
```
这里结果如下：
```mysql
+----------+----------+--------------+------------------+-------------------+
| File  | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+--------+----------+--------------+------------------+-------------------+
| master-bin.000002 |     1265 |          |              |                 |
+-------------+----------+------------+----------------+----------------+
1 row in set (0.00 sec)
```
- 注：如果配置错误，则可以在 mysql 中删除用户：`drop user 'repl'@'192.168.238.135'` 


- 虚拟机二中同样配置
  - 首先修改 `/etc/my.cnf`
```linux
server-id=2
relay-log-index=slave-relay-bin.index
relay-log=slave-relay-bin
```
- 从库重启 mysql 之后，打开 mysql 回话，执行下面语句；
```mysql
CHANGE MASTER TO  MASTER_HOST='192.168.238.136',master_user='repl',master_password='GJXAIOU_o2o',master_log_file='master-bin.000002',master_log_pos=1265;
```
访问主库的IP地址，从 3306端口访问，使用 repl 账号，密码是 GJXAIOU_o2o，读取的文件是 `master-bin.000002`，从 1265 位置开始读取；

- 开启主从跟踪和查看从库状态
```mysql
start slave; # 开启主从跟踪
show slave status \G; # 查看从库状态
```

- 可以使用 `stop slave`关闭主从跟踪；

**补充：设置还原**
当设置有问题的时候可以使用还原设置来重新配置；
- 主库设置还原：`reset master;`
- 从库设置还原：`reset slave all;`


**补充：从 Windows 导入数据库到 CentOS 中**
- 首先将 Windows  的数据库导出
cmd 中使用：`mysqldump -u用户名 -p 数据库名 数据表名 > 导出的数据库位置和名称`，这里是：`mysqldump -uroot -p o2o > D:\o2o.sql`
- 将该数据库导入到 CentOS 中
这里使用 xshell 连接服务器，首先需要在服务器中安装文件传输工具：lrzsz，命令为：`sudo yum install -y lrzsz`；
然后在 xshell 中找到该服务器的属性，设置文件的上传下载路径；
然后进入 CentOS 中的 o2o 目录中（自己定义 ），使用命令 ：`rz`然后回车，选择需要上传的文件即可；`rz`：表示上传，`sz`：表示下载；

- 导入数据库
服务器端进入数据库，然后新建数据库：`create database o2o;`，然后`use o2o;`，最后将上传的数据库文件导入：`source o2o.sql`，后面是刚才上传文件放置的位置；


### （二）代码上实现读写分离

因为是 Dao层，因此创建包 `com.gjxaiou.dao.split`，里面放置读写分离的方法

- 新建 `DynamicDataSource.java`  方法，该方法实现 spring 中的 determineCurrentLookupKey() 方法，最终根据方法的返回值（key）的不同来区分不同的数据源；
这里调用了另一个类 DynamicDataSourceHolder 来具体设置 key 的值以及返回方法，

- 然后设置 mybatis 的拦截器，通过类 DynamicDataSourceInterceptor 完成，因为上面部分完成路由功能，但是使用该路由靠拦截器，因为拦截器会拦截 mybatis 传递进来的 SQL 信息，然后可以根据 SQL信息，如 insert 、update 则采用写的数据源，反之采用读的数据源；

然后在mybatis 配置文件中配置；mybatis-config 中配置拦截器

最后在 Spring -dao 中重写配置 DataSource，包括 db.properties