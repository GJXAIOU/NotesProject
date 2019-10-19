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
