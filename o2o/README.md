# 项目说明

该小项目仅仅作为自己 SSM 框架整合的 Demo，部分功能因为涉及到 SpringBoot，还在学习过程中，将在后期补充。

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
- 





## SSM 整合验证
- entity：该包中对应的是数据库中表及其字段；
- Dao 层：  AreaDao.java   AreaDao.xml
    - Dao 层创建想要的对象和接口，同时用于定义相关的方法；
    - 然后在 resources.mapper 包下创建对应的同名 xml 文件，该文件中定义上面接口中方法对应的 SQL 语句；
- service 层： AreaService.java   AreaServiceImpl.java   
    - service 层一般包含一个接口和一个实现类，接口是想要执行的方法，然后在实现类中该方法调用 Dao 层中的方法；
    - 因为 service 层需要调用 Dao 层，因此在 service 的实现方法中首先应该创建 Dao 层对象；然后在实现类中实现接口中的方法；
    - 其实现类 XXXService.xml 返回想要的数据给 controller 中的；
- Controller 层: AreaController.java
    - 首先 controller 层依赖于 service 层，因此首先创建 service 对象；
    - 然后接受 service 层处理之后的对象，并且返回到前台展示；

## logback
logback 是 Log4j 的改良版本
一般在 service 或者 controller 中使用，用于输出日志；

## 店铺商家管理系统
包括：店铺和商品模块
首先应该应有店铺然后才有商品模块；
### 店铺的增删改查

#### Dao层
- shopDao
- shopExecution ：保存返回信息
- shopStateEnume：定义所有 shop 可能的返回值

### service 层（需要事务处理）
首先需要将店铺信息插入到数据库中，然后返回这个店铺的 Id，根据该店铺的 Id 创建存储该店铺图片的文件夹，在该文件夹下面处理图片，最后将文件夹地址更新会这条数据
以上任何一步出错都要回滚 -> 需要事务处理；

### controller 层
放在包 web.shopadmin 下面，店家管理后台的 controller都放在这里

- ShopManagerController ：负责店铺管理相关逻辑
- HttpServletRequestUtil：负责解析 HttpServletRequest 请求的参数

注： pom 中 的 jackson-databind.jar 负责将实体类转换为 json或者反过来转换


### 前端页面
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
