# 项目说明

- date: 2019-8-30

## 一、项目步骤
- 设计数据库表：在数据库 lianxi 中设计数据库表 people，并新增数据字段；
- 在 pojo 包中新建实体类 people.java
- 配置 MyBatis 环境；
    - 导入相关的 jar 包；
    - 写全局配置环境；
        - 新建 MyBatis.xml 文件；
        - 配置DTD；
        - 配置 environments 里面的数据库的基本配置；
        - 配置 Mappers;
            - 需要新建 mapper 包，然后实现 实体类 Mapper.xml(注意两个 xml 文件的头部不同)
            - 在实体类Mapper.xml 中实现 namespace 以及 SQL 语句；
            - 同时将 上面文件的地址复制到 Mappers里面；
- 数据访问层写完之后，开始写：service
    - 新建 service 包，新建文件 PeopleService.java;
    - 新建 PeopleServiceImpl.java 实现上面的接口
    
- 最后写控制器


## 二、整个项目运行
客户端发送请求给控制器，控制器转发给 jsp 服务器，在服务器中 .jsp 文件会被解释为 .java 文件，然后编译称为 .class 文件，
运行 .class 文件会产生流数据，最后将流数据返回给客户端。
                
            