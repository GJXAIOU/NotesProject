package com.lzc.soundsystem.mixedconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzc.soundsystem.mixedconfig.CompactDisc;
import com.lzc.soundsystem.mixedconfig.impl.CDPlayer;

@Configuration
public class CDPlayerConfig {

	@Bean
	public CDPlayer cdPlayer(CompactDisc compactDisc) {
		return new CDPlayer(compactDisc);
	}

}
