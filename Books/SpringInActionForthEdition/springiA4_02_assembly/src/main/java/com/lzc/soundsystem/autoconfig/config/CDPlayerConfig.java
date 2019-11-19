package com.lzc.soundsystem.autoconfig.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.lzc.soundsystem.autoconfig.ComponentScanPoint;

/**
 * Description: Java Config，定义组件扫描路径，与autoconfig.xml功能一样  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 14:02:42  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version
 */
@Configuration
//@ComponentScan不指定路径的话，会扫描该类所在包及其子包
//@ComponentScan(basePackages="com.lzc.soundsystem.autoconfig")
@ComponentScan(basePackageClasses=ComponentScanPoint.class)//扫描ComponentScanPoint所在包及其子包；相比上一种，类型安全
public class CDPlayerConfig { 
}
