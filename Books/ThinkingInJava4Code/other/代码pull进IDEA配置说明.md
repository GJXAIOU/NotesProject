# 文件导入配置
## 问题一：代码文件左下角有橘黄色的圆圈，里面有字母j
解决： File-> Project Structure -> Modules ->Sources
删除Content Root 下面的文件，然后重新添加源文件，具体见下面操作示意图；

![配置示意图](https://github.com/GJXAIOU/ThinkingInJava4thCode/blob/master/Pics/%E9%85%8D%E7%BD%AE/%E9%94%99%E8%AF%AF%E4%B8%80%EF%BC%9A%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88.png)

## 问题二：Error.java :无效的源发行版：12
![问题二](https://github.com/GJXAIOU/ThinkingInJava4thCode/blob/master/Pics/%E9%85%8D%E7%BD%AE/%E9%94%99%E8%AF%AF%E4%BA%8C%EF%BC%9A%E9%97%AE%E9%A2%98%EF%BC%9A%E6%97%A0%E6%95%88%E7%9A%84%E5%8F%91%E8%A1%8C%E7%89%88.png)

解决方案：
这主要是因为：本机的`JDK`版本与项目的语言级别不同造成的。
使用`Ctrl+Alt+Shift+s`打开`Project Structure`查看`Project Language Level`，发现是`10`，下图是已经改过之后的；
![](https://github.com/GJXAIOU/ThinkingInJava4thCode/blob/master/Pics/%E9%85%8D%E7%BD%AE/%E9%97%AE%E9%A2%98%E4%BA%8C%EF%BC%9A%E8%A7%A3%E5%86%B3%E4%B8%80.png)
然后将该项目的 jdk 版本改成一样即可；
即：打开`Project Structure`界面，选择`Project`，改变`Project Language Level`如下图所示
![](https://github.com/GJXAIOU/ThinkingInJava4thCode/blob/master/Pics/%E9%85%8D%E7%BD%AE/%E9%97%AE%E9%A2%98%E4%BA%8C%EF%BC%9A%E8%A7%A3%E5%86%B3%E4%BA%8C.png)
