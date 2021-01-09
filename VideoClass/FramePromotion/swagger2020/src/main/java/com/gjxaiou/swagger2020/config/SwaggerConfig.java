package com.gjxaiou.swagger2020.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author GJXAIOU
 * @Date 2021/1/1 15:55
 */
// 标识为配置类
@Configuration
// 开启 Swagger2
@EnableSwagger2
public class SwaggerConfig {

    // 1.配置 Swagger 的 Docket 的 Bean 实例
//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2);
//    }


    /**
     * 2.配置文档信息
     * 直接使用上面的 .apiInfo(ApiInfo apiInfo) 里面可以参考 ApiInfo 源码中的属性构建一个对象传入即可。
     */
//    @Bean
//    public Docket docket2() {
//        // 联系人信息
//        Contact contact = new Contact("联系人名字", "www.gjxaiou.com", "联系人邮箱");
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(
//                new ApiInfo(
//                        "自定义标题", // 标题
//                        "自定义描述 Documentation", // 描述
//                        "自定义版本号1.0",  // 版本
//                        "组织链接：www.gjxaiou.com",  // 组织链接
//                        contact,  // 联系人信息
//                        "许可：Apache 2.0",  // 许可
//                        "许可链接：http://www.apache.org/licenses/LICENSE-2.0",  // 许可连接
//                        new ArrayList<VendorExtension>())  // 扩展
//// // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//        ).select().apis(RequestHandlerSelectors.basePackage("com.gjxaiou.swagger2020
//       .controller")).build();
//    }


//    /**
//     * 配置部分路径可以扫描到
//     * @return
//     */
//    @Bean
//    public Docket docket3() {
//        // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//        // .path 配置如何通过path过滤,即这里只扫描请求以 /world 开头的接口
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors
//       .basePackage("com.gjxaiou.swagger2020.controller")).paths(PathSelectors.ant("/world"))
//      .build();
//    }


//    /**
//     * 配置不同环境是否开启
//     */
//    @Bean
//    public Docket docket4(Environment environment) {
//        // 设置要显示 swagger 的环境
//        Profiles profiles = Profiles.of("dev", "test");
//        // 判断当前是否处于该环境
//        // 通过 enable() 接收此参数判断是否要显示
//        boolean b = environment.acceptsProfiles(profiles);
//        return new Docket(DocumentationType.SWAGGER_2).enable(b).select().apis
//       (RequestHandlerSelectors.basePackage("com.gjxaiou.swagger2020.controller")).build();
//    }


    /**
     * 配置 API 分组
     */
    @Bean
    public Docket docket5() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("GJXAIOU");
    }

    @Bean
    public Docket docket6() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("All");
    }
}
