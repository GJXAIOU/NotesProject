package com.lzc.soundsystem.mixedconfig.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:META-INF/spring/mixedconfig-cd.xml")
public class SoundSystemConfig {

}
