package com.lzc.soundsystem.javaconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzc.soundsystem.javaconfig.CompactDisc;
import com.lzc.soundsystem.javaconfig.impl.CDPlayer;
import com.lzc.soundsystem.javaconfig.impl.SgtPeppers;

@Configuration
public class CDPlayerConfig {

	@Bean
	public CompactDisc compactDisc() {
		return new SgtPeppers();
	}

	@Bean
	public CDPlayer cdPlayer(CompactDisc compactDisc) {
		return new CDPlayer(compactDisc);
	}

}
