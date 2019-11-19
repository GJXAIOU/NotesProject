## 项目说明
整体为监听器的Demo

- ApplicationListener ： application作用域的创建、销毁的监听器；
- requestListener ： request作用域的创建、销毁的监听器；
- SessionListener : session作用域的创建、销毁的监听器；

- ApplicationAttributeListener : application域中属性值的添加、修改、删除的监听；

-----
- index.jsp  : 整个应用访问的主页
- sessionDestroy.jsp : session对象手动销毁代码


- web.xml : 项目中监听器的配置文件