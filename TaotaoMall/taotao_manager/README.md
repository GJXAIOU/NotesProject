
## 环境配置

### MyBatis 配置
这里使用 mybatis 的逆向工程直接生成 Mapper 以及对应的 mapper.xml；

### spring 配置
这里将 spring 的配置文件进行了拆分
- applicationContext-dao.xml
 
数据库配置以及使用 spring 容器管理 SqlsessionFactory，同时将 mapper 的代理对象放进 spring 容器中，使用
扫描包的形式加载 mapper 代理对象；

- applicationContext-service.xml

service 配置，将 service 实现类对象放进 spring 容器中管理；
 
- applicationContext-trans.xml

事务管理配置，配置注解驱动，配置视图解析器以及配置需要扫描的 controller;

**配置过程**：
- 配置 web.xml
    - 配置上下文参数，即配置 spring 配置文件位置；
    - 配置监听器，用于帮助加载 spring 配置文件；
- 配置 applicationContext.xml
    - 配置 applicationContext-dao.xml
        - 配置数据库连接池；
            - 配置 db.properties;
            - 配置加载数据库配置文件；
        - 配置 sqlSessionFactory；
            - 配置 mybatis 配置文件位置；
            - 引用上面的数据库链接信息；
        - 配置扫描包，用于加载 mapper 代理对象；    
            - 扫描 mapper 包，给对应的接口创建对象；
    - 配置 applicationContext-service.xml
        - 配置扫描包，加载 service 实现类；
    - 配置 applicationContext-trans.xml
        - 配置数据库事务管理器；  
        - 配置方法的声明式事务；
        - 配置切面；    

### SpringMVC配置
- 配置 web.xml 
    - 配置前端控制器 DispatcherServlet；
    - 配置 springmvc 配置文件的位置；
    - 配置拦截器;
    - 配置字符编码过滤器：解决 post 请求乱码；
- 配置 springmvc.xml
    - 引入 dtd；
    - 配置扫描注解；
    - 配置注解驱动；
    - 自定义视图解析器；
    - 配置放行的静态资源；    
- 编写控制器类：
    - 使用注解将该 controller 交个 springmvc 管理；
    - 设置传值方式；
    - 设置页面跳转；
