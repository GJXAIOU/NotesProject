org.apache.ibatis.exceptions.PersistenceException: 
### Error building SqlSession.
### The error may exist in cn.itcast.mybatis.mapper/UserMapper.xml
### Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource cn.itcast.mybatis.mapper/UserMapper.xml
	出现这个问题大多数都是找不到映射文件，这和没有遵循mybatis的mapper代理配置规范有关，对于我这个问题仔细看java.io.IOException:Could not find resource 
cn.itcast.mybatis.mapper/UserMapper.xml,就是文件读写出现问题，系统找不到这个文件，
需要检查，mapper接口与映射的mapper.xml 的命名是否一致，是否在同一目录下。
如果还没解决就在看SqlMapConfig.xml配置文件加载mapper接口文件的路径有没有错误，一般有两种方试，第一扫描类<mapper class=:"类路径和类名"/>
第二种批量扫描加载：选择扫描包<package name="包名"/>经过不懈努力问题终于解决。我们在开发mybatis时要注意四点开发规范：
1、在mapper.xml中namespace等于mapper接口地址
2、mapper.java接口中的方法名和mapper.xml中statement的id一致
 
3、mapper.java接口中的方法输入参数类型和mapper.xml中statement的parameterType指定的类型一致。
 
4、mapper.java接口中的方法返回值类型和mapper.xml中statement的resultType指定的类型一致。



## People 使用接口绑定
 