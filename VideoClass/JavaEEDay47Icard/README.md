## 项目整体架构

### 一、项目功能

- 实现通过upload.html页面上传图片和信息；
- 实现信息提交之后跳转到index.jsp页面以列表的形式展示上传的所有页面;
- 实现点击图片之后可以下载该图片；
- 实现展示页面点击返回至上传页面；


### 二、具体实现

####（一）视图展示
- upload.html 提供上传页面；
- index.jsp 提供信息展示；
- success.html 提供上传成功跳转（已替换跳转到信息展示页面）；
- error.html 提供上传失败跳转；

#### （二）数据模型
- IdInfo 数据对象的实体类，属性和数据库中字段名相同；
- FileDaoInterface  操作数据库的方法声明；
- FileDaoImpl 操作数据库的方法的实现；

#### （三）逻辑控制
- UploadServlet.java 上传文件
- DownloadServlet.java 下载文件
- ListServlet.java 展示文件

#### （四）util
- JdbcUtil.java 为BaseDao提供数据库的连接、关闭；
- db.properties 为JdbcUtil.java提供数据库的基本信息配置
- BaseDao.java 提供数据库的增删改查方法
- c3p0-config.xml 

#### (五)思路

- 先设计用于上传信息的页面：upload.html;
- 然后设计数据库，包括表名、表数量、表之间格式、字段名、字段数量、字段格式等;
- 根据数据库设计实体类对象：IdInfo.java,保证类变量和数据库字段名相同；
- 根据项目需要数据库操作设计接口: FileDaoInterface.java
- 实现数据库操作接口：FileDaoImpl.java




### 三、jar包
- commons-beanutils.jar
- commons-fileupload.jar
- commons-io.jar
- commons-logging.jar
- jstl.jar
- mysql-connector-java.jar

