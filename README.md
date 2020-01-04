## 一、仓库说明📕

该仓库一共包含两个部分：

- 🎉[Notes](https://github.com/GJXAIOU/Notes) 仓库中笔记所对应的源代码；
- 🎉[SSMSSM 商城项目](https://github.com/GJXAIOU/Project/tree/master/o2o)；



## 二、目录说明📒

- **Algorithm** ：是 Notes 库中 Algorithm 笔记对应的源代码；

- **Books**：

    - BookJavaCoreAndInterview：《 Java核心技术及面试指南 》书本中的源代码，已经改成 IDEA 版本，可以正常Debug；

    - SpringInActionForthEdition：《Spring 实战（第四版）》调试源代码，进行了必要的更改和注释；

        

- **LeetCode**：LeetCode 刷题过程中的代码，进一步加深对数据结构与算法知识点的理解；

- **VideoClass**：部分重要课程知识点自己做的测试 Demo，通过这些示例可以进一步加深知识点的理解，对应的笔记在 [JavaNotes](https://github.com/GJXAIOU/Notes/tree/master/Java/JavaNotes) 中；

- **JVM**：Java 虚拟机学习过程中的调试代码；

- **DesignPatternCode**：设计模式学习过程中调试代码；

- 📌 **o2o 商城项目**：

    - 项目的技术架构为：Spring + SpringMVC + MyBatis  + MySQL + Redis + SUI Mobile + jQuery。项目分为通用模块、数据备份模块、前端展示模块、商家模块和超级管理员模块。实现的功能有店铺、商品管理，账号管理，头条、店铺、商品等详情展示等等。 
- 主要特点为：通过 MySQL 主从同步实现读写分离，使用 Redis 缓存加快数据访问速度，通过 SpringMVC 拦截器实现权限认证，并且考虑到后期项目部署安全问题，使用 DES 对关键配置信息进行加密和使用验证码组件 Kaptcha 实现验证码功能，并且使用脚本定时备份数据库文件和系统信息。
    - 技术栈：Spring、SpringMVC、Mybatis、SUI Mobile、Redis；后期改为 SpringBoot（在学习ing）
    - 工具：IDEA + Maven + Tomcat；
    - 项目具体说明详见：[README.md](https://github.com/GJXAIOU/Project/blob/master/o2o/README.md)



