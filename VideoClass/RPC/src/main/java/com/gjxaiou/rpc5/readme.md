这个版本在之前通用化的基础上，连服务类型都要能改变，变成通用的，主要是将服务类型作为参数传入getStub

为了验证server和stub的通用性，这里client调用了两个不同服务的不同的接口，都可以正常运行

获取服务器端暴露的任何接口，步骤为：
首先有一个 client 端，然后 server 端有很多接口 I1， I2、、
如果 client 端要调用服务，但是不想每次都写网络细节，就生成一个代理类，代理类是动态生成的。
怎么生成：client 只要告诉工具类 stub，我要 I1 接口的 m1 方法，参数是 p1 ，然后就会生成一个代理类。
生成代理类之后，如果调用生成代理类里面的 m1 方法，代理类就会在 Invoker 中将上面信息传递到服务器，服务器读取到之后找到对应的 I1 实现类和对应的方法，执行完之后将结果写回来。
这样无论 server 增加什么接口都行。



**问题**
首先 client 需要将所有服务、方法以及参数进行虚拟化之后传输，这里使用的 Java 自带的 Serializable，缺点：只支持 Java 语言，同时效率低，并且转换的二进制长度也比较长。

其他的 RPC 序列化框架：
序列化就是将对象转换为二进制数组，其中 JSON 和 xml 就是先将对象转换为 JSON 或者 xml 格式，然后在转换为二进制
- java.io.Serializable
- Hessian
- google protobuf
- facebook Thrift
- kyro
- fst
- Json 序列化框架
    - Jackson
    - Google Gson
    - Alibaba FastJson
- xmlrpc(xstream)

 RPC 通讯协议
- HTTP
- HTTP2.0(gRPC 用的就是2.0，因为 2.0 不仅可以传输文本，同时可以传输二进制)
- TCP
 同步、异步  阻塞、非阻塞
- WebService