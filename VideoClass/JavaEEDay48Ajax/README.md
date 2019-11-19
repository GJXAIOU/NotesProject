## 项目描述

整个分为两个独立的项目

- 关注项目

- 登录项目


### 一、关注项目

**（一）功能**

实现在页面上点击“关注”按钮，页面弹出提示：已关注，同时按钮变为：“已关注”


**（二）包含页面**
- FollowServlet.java   用于后台数据处理
- AjaxAsynchronous.html  以异步方式实现Ajax
- AjaxSynchronization.html 以同步方式实现Ajax



### 二、登录项目

**（一）功能**

实现从login.html页面进行登录，通过验证用户名和密码（这是没有使用数据库，将用户名和密码写死了），如果验证通过，跳转到主页：
main.html，如果登录失败就提示登录失败。

**（二）包含页面**

- LoginServlet.java
- login.html
- main.html

