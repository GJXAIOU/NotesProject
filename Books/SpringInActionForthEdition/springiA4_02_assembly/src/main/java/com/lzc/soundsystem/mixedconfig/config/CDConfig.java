package com.lzc.soundsystem.mixedconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzc.soundsystem.mixedconfig.CompactDisc;
import com.lzc.soundsystem.mixedconfig.impl.SgtPeppers;

@Configuration
public class CDConfig {

	@Bean
	public CompactDisc compactDisc() {
		return new SgtPeppers();
	}
}